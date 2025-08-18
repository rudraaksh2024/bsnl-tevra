package androidx.camera.camera2.internal;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.ApiCompat;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.CameraState;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.UseCaseAttachState;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

final class Camera2CameraImpl implements CameraInternal {
    private static final int ERROR_NONE = 0;
    private static final String TAG = "Camera2CameraImpl";
    private final CameraAvailability mCameraAvailability;
    private CameraConfig mCameraConfig;
    private final Camera2CameraControlImpl mCameraControlInternal;
    CameraDevice mCameraDevice;
    int mCameraDeviceError;
    final Camera2CameraInfoImpl mCameraInfoInternal;
    private final CameraManagerCompat mCameraManager;
    private final CameraStateMachine mCameraStateMachine;
    private final CameraStateRegistry mCameraStateRegistry;
    CaptureSessionInterface mCaptureSession;
    private final SynchronizedCaptureSessionOpener.Builder mCaptureSessionOpenerBuilder;
    private final CaptureSessionRepository mCaptureSessionRepository;
    final Set<CaptureSession> mConfiguringForClose;
    private final DisplayInfoManager mDisplayInfoManager;
    private final Executor mExecutor;
    boolean mIsActiveResumingMode;
    final Object mLock;
    private MeteringRepeatingSession mMeteringRepeatingSession;
    private final Set<String> mNotifyStateAttachedSet;
    private final LiveDataObservable<CameraInternal.State> mObservableState;
    final AtomicInteger mReleaseRequestCount;
    final Map<CaptureSessionInterface, ListenableFuture<Void>> mReleasedCaptureSessions;
    private final ScheduledExecutorService mScheduledExecutorService;
    private SessionProcessor mSessionProcessor;
    volatile InternalState mState = InternalState.INITIALIZED;
    private final StateCallback mStateCallback;
    private final UseCaseAttachState mUseCaseAttachState;
    ListenableFuture<Void> mUserReleaseFuture;
    CallbackToFutureAdapter.Completer<Void> mUserReleaseNotifier;

    enum InternalState {
        INITIALIZED,
        PENDING_OPEN,
        OPENING,
        OPENED,
        CLOSING,
        REOPENING,
        RELEASING,
        RELEASED
    }

    static String getErrorMessage(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    Camera2CameraImpl(CameraManagerCompat cameraManagerCompat, String str, Camera2CameraInfoImpl camera2CameraInfoImpl, CameraStateRegistry cameraStateRegistry, Executor executor, Handler handler, DisplayInfoManager displayInfoManager) throws CameraUnavailableException {
        CameraManagerCompat cameraManagerCompat2 = cameraManagerCompat;
        String str2 = str;
        Camera2CameraInfoImpl camera2CameraInfoImpl2 = camera2CameraInfoImpl;
        CameraStateRegistry cameraStateRegistry2 = cameraStateRegistry;
        LiveDataObservable<CameraInternal.State> liveDataObservable = new LiveDataObservable<>();
        this.mObservableState = liveDataObservable;
        this.mCameraDeviceError = 0;
        this.mReleaseRequestCount = new AtomicInteger(0);
        this.mReleasedCaptureSessions = new LinkedHashMap();
        this.mConfiguringForClose = new HashSet();
        this.mNotifyStateAttachedSet = new HashSet();
        this.mCameraConfig = CameraConfigs.emptyConfig();
        this.mLock = new Object();
        this.mIsActiveResumingMode = false;
        this.mCameraManager = cameraManagerCompat2;
        this.mCameraStateRegistry = cameraStateRegistry2;
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(handler);
        this.mScheduledExecutorService = newHandlerExecutor;
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mExecutor = newSequentialExecutor;
        this.mStateCallback = new StateCallback(newSequentialExecutor, newHandlerExecutor);
        this.mUseCaseAttachState = new UseCaseAttachState(str2);
        liveDataObservable.postValue(CameraInternal.State.CLOSED);
        CameraStateMachine cameraStateMachine = new CameraStateMachine(cameraStateRegistry2);
        this.mCameraStateMachine = cameraStateMachine;
        CaptureSessionRepository captureSessionRepository = new CaptureSessionRepository(newSequentialExecutor);
        this.mCaptureSessionRepository = captureSessionRepository;
        this.mDisplayInfoManager = displayInfoManager;
        this.mCaptureSession = newCaptureSession();
        try {
            Camera2CameraControlImpl camera2CameraControlImpl = new Camera2CameraControlImpl(cameraManagerCompat.getCameraCharacteristicsCompat(str), newHandlerExecutor, newSequentialExecutor, new ControlUpdateListenerInternal(), camera2CameraInfoImpl.getCameraQuirks());
            this.mCameraControlInternal = camera2CameraControlImpl;
            this.mCameraInfoInternal = camera2CameraInfoImpl2;
            camera2CameraInfoImpl2.linkWithCameraControl(camera2CameraControlImpl);
            camera2CameraInfoImpl2.setCameraStateSource(cameraStateMachine.getStateLiveData());
            this.mCaptureSessionOpenerBuilder = new SynchronizedCaptureSessionOpener.Builder(newSequentialExecutor, newHandlerExecutor, handler, captureSessionRepository, camera2CameraInfoImpl.getCameraQuirks(), DeviceQuirks.getAll());
            CameraAvailability cameraAvailability = new CameraAvailability(str2);
            this.mCameraAvailability = cameraAvailability;
            cameraStateRegistry2.registerCamera(this, newSequentialExecutor, cameraAvailability);
            cameraManagerCompat2.registerAvailabilityCallback(newSequentialExecutor, cameraAvailability);
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    private CaptureSessionInterface newCaptureSession() {
        synchronized (this.mLock) {
            if (this.mSessionProcessor == null) {
                CaptureSession captureSession = new CaptureSession();
                return captureSession;
            }
            ProcessingCaptureSession processingCaptureSession = new ProcessingCaptureSession(this.mSessionProcessor, this.mCameraInfoInternal, this.mExecutor, this.mScheduledExecutorService);
            return processingCaptureSession;
        }
    }

    public void open() {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: androidx.camera.camera2.internal.Camera2CameraImpl$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState[] r0 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState = r0
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.PENDING_OPEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.CLOSING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.OPENED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.OPENING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.REOPENING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x0054 }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.RELEASING     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState     // Catch:{ NoSuchFieldError -> 0x0060 }
                androidx.camera.camera2.internal.Camera2CameraImpl$InternalState r1 = androidx.camera.camera2.internal.Camera2CameraImpl.InternalState.RELEASED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CameraImpl.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void openInternal() {
        int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()];
        boolean z = false;
        if (i == 1 || i == 2) {
            tryForceOpenCameraDevice(false);
        } else if (i != 3) {
            debugLog("open() ignored due to being in state: " + this.mState);
        } else {
            setState(InternalState.REOPENING);
            if (!isSessionCloseComplete() && this.mCameraDeviceError == 0) {
                if (this.mCameraDevice != null) {
                    z = true;
                }
                Preconditions.checkState(z, "Camera Device should be open if session close is not complete");
                setState(InternalState.OPENED);
                openCaptureSession();
            }
        }
    }

    public void close() {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public void closeInternal() {
        debugLog("Closing camera.");
        int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()];
        boolean z = false;
        if (i == 2) {
            if (this.mCameraDevice == null) {
                z = true;
            }
            Preconditions.checkState(z);
            setState(InternalState.INITIALIZED);
        } else if (i == 4) {
            setState(InternalState.CLOSING);
            closeCamera(false);
        } else if (i == 5 || i == 6) {
            boolean cancelScheduledReopen = this.mStateCallback.cancelScheduledReopen();
            setState(InternalState.CLOSING);
            if (cancelScheduledReopen) {
                Preconditions.checkState(isSessionCloseComplete());
                finishClose();
            }
        } else {
            debugLog("close() ignored due to being in state: " + this.mState);
        }
    }

    private void configAndClose(boolean z) {
        CaptureSession captureSession = new CaptureSession();
        this.mConfiguringForClose.add(captureSession);
        resetCaptureSession(z);
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(640, 480);
        Surface surface = new Surface(surfaceTexture);
        Camera2CameraImpl$$ExternalSyntheticLambda5 camera2CameraImpl$$ExternalSyntheticLambda5 = new Camera2CameraImpl$$ExternalSyntheticLambda5(surface, surfaceTexture);
        SessionConfig.Builder builder = new SessionConfig.Builder();
        ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        builder.addNonRepeatingSurface(immediateSurface);
        builder.setTemplateType(1);
        debugLog("Start configAndClose.");
        captureSession.open(builder.build(), (CameraDevice) Preconditions.checkNotNull(this.mCameraDevice), this.mCaptureSessionOpenerBuilder.build()).addListener(new Camera2CameraImpl$$ExternalSyntheticLambda6(this, captureSession, immediateSurface, camera2CameraImpl$$ExternalSyntheticLambda5), this.mExecutor);
    }

    static /* synthetic */ void lambda$configAndClose$0(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: releaseNoOpSession */
    public void m14lambda$configAndClose$1$androidxcameracamera2internalCamera2CameraImpl(CaptureSession captureSession, DeferrableSurface deferrableSurface, Runnable runnable) {
        this.mConfiguringForClose.remove(captureSession);
        ListenableFuture<Void> releaseSession = releaseSession(captureSession, false);
        deferrableSurface.close();
        Futures.successfulAsList(Arrays.asList(new ListenableFuture[]{releaseSession, deferrableSurface.getTerminationFuture()})).addListener(runnable, CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    public boolean isSessionCloseComplete() {
        return this.mReleasedCaptureSessions.isEmpty() && this.mConfiguringForClose.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void finishClose() {
        Preconditions.checkState(this.mState == InternalState.RELEASING || this.mState == InternalState.CLOSING);
        Preconditions.checkState(this.mReleasedCaptureSessions.isEmpty());
        this.mCameraDevice = null;
        if (this.mState == InternalState.CLOSING) {
            setState(InternalState.INITIALIZED);
            return;
        }
        this.mCameraManager.unregisterAvailabilityCallback(this.mCameraAvailability);
        setState(InternalState.RELEASED);
        CallbackToFutureAdapter.Completer<Void> completer = this.mUserReleaseNotifier;
        if (completer != null) {
            completer.set(null);
            this.mUserReleaseNotifier = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void closeCamera(boolean z) {
        Preconditions.checkState(this.mState == InternalState.CLOSING || this.mState == InternalState.RELEASING || (this.mState == InternalState.REOPENING && this.mCameraDeviceError != 0), "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.mState + " (error: " + getErrorMessage(this.mCameraDeviceError) + ")");
        resetCaptureSession(z);
        this.mCaptureSession.cancelIssuedCaptureRequests();
    }

    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda11(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$3$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m24lambda$release$3$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda14(this, completer));
        return "Release[request=" + this.mReleaseRequestCount.getAndIncrement() + "]";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$2$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m23lambda$release$2$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(releaseInternal(), completer);
    }

    private ListenableFuture<Void> releaseInternal() {
        ListenableFuture<Void> orCreateUserReleaseFuture = getOrCreateUserReleaseFuture();
        boolean z = false;
        switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()]) {
            case 1:
            case 2:
                if (this.mCameraDevice == null) {
                    z = true;
                }
                Preconditions.checkState(z);
                setState(InternalState.RELEASING);
                Preconditions.checkState(isSessionCloseComplete());
                finishClose();
                break;
            case 3:
            case 5:
            case 6:
            case 7:
                boolean cancelScheduledReopen = this.mStateCallback.cancelScheduledReopen();
                setState(InternalState.RELEASING);
                if (cancelScheduledReopen) {
                    Preconditions.checkState(isSessionCloseComplete());
                    finishClose();
                    break;
                }
                break;
            case 4:
                setState(InternalState.RELEASING);
                closeCamera(false);
                break;
            default:
                debugLog("release() ignored due to being in state: " + this.mState);
                break;
        }
        return orCreateUserReleaseFuture;
    }

    private ListenableFuture<Void> getOrCreateUserReleaseFuture() {
        if (this.mUserReleaseFuture == null) {
            if (this.mState != InternalState.RELEASED) {
                this.mUserReleaseFuture = CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda9(this));
            } else {
                this.mUserReleaseFuture = Futures.immediateFuture(null);
            }
        }
        return this.mUserReleaseFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOrCreateUserReleaseFuture$4$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m16lambda$getOrCreateUserReleaseFuture$4$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        Preconditions.checkState(this.mUserReleaseNotifier == null, "Camera can only be released once, so release completer should be null on creation.");
        this.mUserReleaseNotifier = completer;
        return "Release[camera=" + this + "]";
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> releaseSession(final CaptureSessionInterface captureSessionInterface, boolean z) {
        captureSessionInterface.close();
        ListenableFuture<Void> release = captureSessionInterface.release(z);
        debugLog("Releasing session in state " + this.mState.name());
        this.mReleasedCaptureSessions.put(captureSessionInterface, release);
        Futures.addCallback(release, new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(Void voidR) {
                Camera2CameraImpl.this.mReleasedCaptureSessions.remove(captureSessionInterface);
                int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
                if (i != 3) {
                    if (i != 6) {
                        if (i != 7) {
                            return;
                        }
                    } else if (Camera2CameraImpl.this.mCameraDeviceError == 0) {
                        return;
                    }
                }
                if (Camera2CameraImpl.this.isSessionCloseComplete() && Camera2CameraImpl.this.mCameraDevice != null) {
                    ApiCompat.Api21Impl.close(Camera2CameraImpl.this.mCameraDevice);
                    Camera2CameraImpl.this.mCameraDevice = null;
                }
            }
        }, CameraXExecutors.directExecutor());
        return release;
    }

    public Observable<CameraInternal.State> getCameraState() {
        return this.mObservableState;
    }

    public void onUseCaseActive(UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda12(this, getUseCaseId(useCase), useCase.getSessionConfig(), useCase.getCurrentConfig()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseActive$5$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m19lambda$onUseCaseActive$5$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig) {
        debugLog("Use case " + str + " ACTIVE");
        this.mUseCaseAttachState.setUseCaseActive(str, sessionConfig, useCaseConfig);
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig);
        updateCaptureSessionConfig();
    }

    public void onUseCaseInactive(UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda0(this, getUseCaseId(useCase)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseInactive$6$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m20lambda$onUseCaseInactive$6$androidxcameracamera2internalCamera2CameraImpl(String str) {
        debugLog("Use case " + str + " INACTIVE");
        this.mUseCaseAttachState.setUseCaseInactive(str);
        updateCaptureSessionConfig();
    }

    public void onUseCaseUpdated(UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda15(this, getUseCaseId(useCase), useCase.getSessionConfig(), useCase.getCurrentConfig()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseUpdated$7$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m22lambda$onUseCaseUpdated$7$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig) {
        debugLog("Use case " + str + " UPDATED");
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig);
        updateCaptureSessionConfig();
    }

    public void onUseCaseReset(UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda7(this, getUseCaseId(useCase), useCase.getSessionConfig(), useCase.getCurrentConfig()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseReset$8$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m21lambda$onUseCaseReset$8$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig) {
        debugLog("Use case " + str + " RESET");
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig);
        addOrRemoveMeteringRepeatingUseCase();
        resetCaptureSession(false);
        updateCaptureSessionConfig();
        if (this.mState == InternalState.OPENED) {
            openCaptureSession();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isUseCaseAttached(UseCase useCase) {
        try {
            return ((Boolean) CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda16(this, getUseCaseId(useCase))).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to check if use case is attached.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isUseCaseAttached$10$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m17lambda$isUseCaseAttached$10$androidxcameracamera2internalCamera2CameraImpl(String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda10(this, completer, str));
            return "isUseCaseAttached";
        } catch (RejectedExecutionException unused) {
            completer.setException(new RuntimeException("Unable to check if use case is attached. Camera executor shut down."));
            return "isUseCaseAttached";
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isUseCaseAttached$9$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m18lambda$isUseCaseAttached$9$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer, String str) {
        completer.set(Boolean.valueOf(this.mUseCaseAttachState.isUseCaseAttached(str)));
    }

    public void attachUseCases(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (!arrayList.isEmpty()) {
            this.mCameraControlInternal.incrementUseCount();
            notifyStateAttachedToUseCases(new ArrayList(arrayList));
            try {
                this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda4(this, new ArrayList(toUseCaseInfos(arrayList))));
            } catch (RejectedExecutionException e) {
                debugLog("Unable to attach use cases.", e);
                this.mCameraControlInternal.decrementUseCount();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$attachUseCases$11$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m13lambda$attachUseCases$11$androidxcameracamera2internalCamera2CameraImpl(List list) {
        try {
            tryAttachUseCases(list);
        } finally {
            this.mCameraControlInternal.decrementUseCount();
        }
    }

    private void tryAttachUseCases(Collection<UseCaseInfo> collection) {
        Size surfaceResolution;
        boolean isEmpty = this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty();
        ArrayList arrayList = new ArrayList();
        Rational rational = null;
        for (UseCaseInfo next : collection) {
            if (!this.mUseCaseAttachState.isUseCaseAttached(next.getUseCaseId())) {
                this.mUseCaseAttachState.setUseCaseAttached(next.getUseCaseId(), next.getSessionConfig(), next.getUseCaseConfig());
                arrayList.add(next.getUseCaseId());
                if (next.getUseCaseType() == Preview.class && (surfaceResolution = next.getSurfaceResolution()) != null) {
                    rational = new Rational(surfaceResolution.getWidth(), surfaceResolution.getHeight());
                }
            }
        }
        if (!arrayList.isEmpty()) {
            debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
            if (isEmpty) {
                this.mCameraControlInternal.setActive(true);
                this.mCameraControlInternal.incrementUseCount();
            }
            addOrRemoveMeteringRepeatingUseCase();
            updateZslDisabledByUseCaseConfigStatus();
            updateCaptureSessionConfig();
            resetCaptureSession(false);
            if (this.mState == InternalState.OPENED) {
                openCaptureSession();
            } else {
                openInternal();
            }
            if (rational != null) {
                this.mCameraControlInternal.setPreviewAspectRatio(rational);
            }
        }
    }

    private Collection<UseCaseInfo> toUseCaseInfos(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList();
        for (UseCase from : collection) {
            arrayList.add(UseCaseInfo.from(from));
        }
        return arrayList;
    }

    public void setExtendedConfig(CameraConfig cameraConfig) {
        if (cameraConfig == null) {
            cameraConfig = CameraConfigs.emptyConfig();
        }
        SessionProcessor sessionProcessor = cameraConfig.getSessionProcessor((SessionProcessor) null);
        this.mCameraConfig = cameraConfig;
        synchronized (this.mLock) {
            this.mSessionProcessor = sessionProcessor;
        }
    }

    public CameraConfig getExtendedConfig() {
        return this.mCameraConfig;
    }

    private void notifyStateAttachedToUseCases(List<UseCase> list) {
        for (UseCase next : list) {
            String useCaseId = getUseCaseId(next);
            if (!this.mNotifyStateAttachedSet.contains(useCaseId)) {
                this.mNotifyStateAttachedSet.add(useCaseId);
                next.onStateAttached();
            }
        }
    }

    private void notifyStateDetachedToUseCases(List<UseCase> list) {
        for (UseCase next : list) {
            String useCaseId = getUseCaseId(next);
            if (this.mNotifyStateAttachedSet.contains(useCaseId)) {
                next.onStateDetached();
                this.mNotifyStateAttachedSet.remove(useCaseId);
            }
        }
    }

    public void detachUseCases(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(toUseCaseInfos(arrayList));
            notifyStateDetachedToUseCases(new ArrayList(arrayList));
            this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda2(this, arrayList2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: tryDetachUseCases */
    public void m15lambda$detachUseCases$12$androidxcameracamera2internalCamera2CameraImpl(Collection<UseCaseInfo> collection) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (UseCaseInfo next : collection) {
            if (this.mUseCaseAttachState.isUseCaseAttached(next.getUseCaseId())) {
                this.mUseCaseAttachState.removeUseCase(next.getUseCaseId());
                arrayList.add(next.getUseCaseId());
                if (next.getUseCaseType() == Preview.class) {
                    z = true;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
            if (z) {
                this.mCameraControlInternal.setPreviewAspectRatio((Rational) null);
            }
            addOrRemoveMeteringRepeatingUseCase();
            if (this.mUseCaseAttachState.getAttachedUseCaseConfigs().isEmpty()) {
                this.mCameraControlInternal.setZslDisabledByUserCaseConfig(false);
            } else {
                updateZslDisabledByUseCaseConfigStatus();
            }
            if (this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty()) {
                this.mCameraControlInternal.decrementUseCount();
                resetCaptureSession(false);
                this.mCameraControlInternal.setActive(false);
                this.mCaptureSession = newCaptureSession();
                closeInternal();
                return;
            }
            updateCaptureSessionConfig();
            resetCaptureSession(false);
            if (this.mState == InternalState.OPENED) {
                openCaptureSession();
            }
        }
    }

    private void updateZslDisabledByUseCaseConfigStatus() {
        boolean z = false;
        for (UseCaseConfig<?> isZslDisabled : this.mUseCaseAttachState.getAttachedUseCaseConfigs()) {
            z |= isZslDisabled.isZslDisabled(false);
        }
        this.mCameraControlInternal.setZslDisabledByUserCaseConfig(z);
    }

    private void addOrRemoveMeteringRepeatingUseCase() {
        SessionConfig build = this.mUseCaseAttachState.getAttachedBuilder().build();
        CaptureConfig repeatingCaptureConfig = build.getRepeatingCaptureConfig();
        int size = repeatingCaptureConfig.getSurfaces().size();
        int size2 = build.getSurfaces().size();
        if (build.getSurfaces().isEmpty()) {
            return;
        }
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            if (this.mMeteringRepeatingSession == null) {
                this.mMeteringRepeatingSession = new MeteringRepeatingSession(this.mCameraInfoInternal.getCameraCharacteristicsCompat(), this.mDisplayInfoManager);
            }
            addMeteringRepeating();
        } else if (size2 == 1 && size == 1) {
            removeMeteringRepeating();
        } else if (size >= 2) {
            removeMeteringRepeating();
        } else {
            Logger.d(TAG, "mMeteringRepeating is ATTACHED, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        }
    }

    private void removeMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            this.mUseCaseAttachState.setUseCaseDetached(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mUseCaseAttachState.setUseCaseInactive(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mMeteringRepeatingSession.clear();
            this.mMeteringRepeatingSession = null;
        }
    }

    private void addMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            this.mUseCaseAttachState.setUseCaseAttached(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.getSessionConfig(), this.mMeteringRepeatingSession.getUseCaseConfig());
            this.mUseCaseAttachState.setUseCaseActive(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.getSessionConfig(), this.mMeteringRepeatingSession.getUseCaseConfig());
        }
    }

    public CameraInfoInternal getCameraInfoInternal() {
        return this.mCameraInfoInternal;
    }

    public CameraAvailability getCameraAvailability() {
        return this.mCameraAvailability;
    }

    /* access modifiers changed from: package-private */
    public void tryForceOpenCameraDevice(boolean z) {
        debugLog("Attempting to force open the camera.");
        if (!this.mCameraStateRegistry.tryOpenCamera(this)) {
            debugLog("No cameras available. Waiting for available camera before opening camera.");
            setState(InternalState.PENDING_OPEN);
            return;
        }
        openCameraDevice(z);
    }

    /* access modifiers changed from: package-private */
    public void tryOpenCameraDevice(boolean z) {
        debugLog("Attempting to open the camera.");
        if (!(this.mCameraAvailability.isCameraAvailable() && this.mCameraStateRegistry.tryOpenCamera(this))) {
            debugLog("No cameras available. Waiting for available camera before opening camera.");
            setState(InternalState.PENDING_OPEN);
            return;
        }
        openCameraDevice(z);
    }

    public void setActiveResumingMode(boolean z) {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda8(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setActiveResumingMode$13$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m25lambda$setActiveResumingMode$13$androidxcameracamera2internalCamera2CameraImpl(boolean z) {
        this.mIsActiveResumingMode = z;
        if (z && this.mState == InternalState.PENDING_OPEN) {
            tryForceOpenCameraDevice(false);
        }
    }

    private void openCameraDevice(boolean z) {
        if (!z) {
            this.mStateCallback.resetReopenMonitor();
        }
        this.mStateCallback.cancelScheduledReopen();
        debugLog("Opening camera.");
        setState(InternalState.OPENING);
        try {
            this.mCameraManager.openCamera(this.mCameraInfoInternal.getCameraId(), this.mExecutor, createDeviceStateCallback());
        } catch (CameraAccessExceptionCompat e) {
            debugLog("Unable to open camera due to " + e.getMessage());
            if (e.getReason() == 10001) {
                setState(InternalState.INITIALIZED, CameraState.StateError.create(7, e));
            }
        } catch (SecurityException e2) {
            debugLog("Unable to open camera due to " + e2.getMessage());
            setState(InternalState.REOPENING);
            this.mStateCallback.scheduleCameraReopen();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateCaptureSessionConfig() {
        SessionConfig.ValidatingBuilder activeAndAttachedBuilder = this.mUseCaseAttachState.getActiveAndAttachedBuilder();
        if (activeAndAttachedBuilder.isValid()) {
            this.mCameraControlInternal.setTemplate(activeAndAttachedBuilder.build().getTemplateType());
            activeAndAttachedBuilder.add(this.mCameraControlInternal.getSessionConfig());
            this.mCaptureSession.setSessionConfig(activeAndAttachedBuilder.build());
            return;
        }
        this.mCameraControlInternal.resetTemplate();
        this.mCaptureSession.setSessionConfig(this.mCameraControlInternal.getSessionConfig());
    }

    /* access modifiers changed from: package-private */
    public void openCaptureSession() {
        Preconditions.checkState(this.mState == InternalState.OPENED);
        SessionConfig.ValidatingBuilder attachedBuilder = this.mUseCaseAttachState.getAttachedBuilder();
        if (!attachedBuilder.isValid()) {
            debugLog("Unable to create capture session due to conflicting configurations");
            return;
        }
        if (!attachedBuilder.build().getImplementationOptions().containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
            attachedBuilder.addImplementationOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION, Long.valueOf(StreamUseCaseUtil.getStreamUseCaseFromUseCaseConfigs(this.mUseCaseAttachState.getAttachedUseCaseConfigs(), this.mUseCaseAttachState.getAttachedSessionConfigs())));
        }
        Futures.addCallback(this.mCaptureSession.open(attachedBuilder.build(), (CameraDevice) Preconditions.checkNotNull(this.mCameraDevice), this.mCaptureSessionOpenerBuilder.build()), new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
            }

            public void onFailure(Throwable th) {
                if (th instanceof DeferrableSurface.SurfaceClosedException) {
                    SessionConfig findSessionConfigForSurface = Camera2CameraImpl.this.findSessionConfigForSurface(((DeferrableSurface.SurfaceClosedException) th).getDeferrableSurface());
                    if (findSessionConfigForSurface != null) {
                        Camera2CameraImpl.this.postSurfaceClosedError(findSessionConfigForSurface);
                    }
                } else if (th instanceof CancellationException) {
                    Camera2CameraImpl.this.debugLog("Unable to configure camera cancelled");
                } else {
                    if (Camera2CameraImpl.this.mState == InternalState.OPENED) {
                        Camera2CameraImpl.this.setState(InternalState.OPENED, CameraState.StateError.create(4, th));
                    }
                    if (th instanceof CameraAccessException) {
                        Camera2CameraImpl.this.debugLog("Unable to configure camera due to " + th.getMessage());
                    } else if (th instanceof TimeoutException) {
                        Logger.e(Camera2CameraImpl.TAG, "Unable to configure camera " + Camera2CameraImpl.this.mCameraInfoInternal.getCameraId() + ", timeout!");
                    }
                }
            }
        }, this.mExecutor);
    }

    private boolean isLegacyDevice() {
        return ((Camera2CameraInfoImpl) getCameraInfoInternal()).getSupportedHardwareLevel() == 2;
    }

    /* access modifiers changed from: package-private */
    public SessionConfig findSessionConfigForSurface(DeferrableSurface deferrableSurface) {
        for (SessionConfig next : this.mUseCaseAttachState.getAttachedSessionConfigs()) {
            if (next.getSurfaces().contains(deferrableSurface)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void postSurfaceClosedError(SessionConfig sessionConfig) {
        ScheduledExecutorService mainThreadExecutor = CameraXExecutors.mainThreadExecutor();
        List<SessionConfig.ErrorListener> errorListeners = sessionConfig.getErrorListeners();
        if (!errorListeners.isEmpty()) {
            debugLog("Posting surface closed", new Throwable());
            mainThreadExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda13(errorListeners.get(0), sessionConfig));
        }
    }

    /* access modifiers changed from: package-private */
    public void resetCaptureSession(boolean z) {
        Preconditions.checkState(this.mCaptureSession != null);
        debugLog("Resetting Capture Session");
        CaptureSessionInterface captureSessionInterface = this.mCaptureSession;
        SessionConfig sessionConfig = captureSessionInterface.getSessionConfig();
        List<CaptureConfig> captureConfigs = captureSessionInterface.getCaptureConfigs();
        CaptureSessionInterface newCaptureSession = newCaptureSession();
        this.mCaptureSession = newCaptureSession;
        newCaptureSession.setSessionConfig(sessionConfig);
        this.mCaptureSession.issueCaptureRequests(captureConfigs);
        releaseSession(captureSessionInterface, z);
    }

    private CameraDevice.StateCallback createDeviceStateCallback() {
        ArrayList arrayList = new ArrayList(this.mUseCaseAttachState.getAttachedBuilder().build().getDeviceStateCallbacks());
        arrayList.add(this.mCaptureSessionRepository.getCameraStateCallback());
        arrayList.add(this.mStateCallback);
        return CameraDeviceStateCallbacks.createComboCallback((List<CameraDevice.StateCallback>) arrayList);
    }

    private boolean checkAndAttachRepeatingSurface(CaptureConfig.Builder builder) {
        if (!builder.getSurfaces().isEmpty()) {
            Logger.w(TAG, "The capture config builder already has surface inside.");
            return false;
        }
        for (SessionConfig repeatingCaptureConfig : this.mUseCaseAttachState.getActiveAndAttachedSessionConfigs()) {
            List<DeferrableSurface> surfaces = repeatingCaptureConfig.getRepeatingCaptureConfig().getSurfaces();
            if (!surfaces.isEmpty()) {
                for (DeferrableSurface addSurface : surfaces) {
                    builder.addSurface(addSurface);
                }
            }
        }
        if (!builder.getSurfaces().isEmpty()) {
            return true;
        }
        Logger.w(TAG, "Unable to find a repeating surface to attach to CaptureConfig");
        return false;
    }

    public CameraControlInternal getCameraControlInternal() {
        return this.mCameraControlInternal;
    }

    /* access modifiers changed from: package-private */
    public void submitCaptureRequests(List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig next : list) {
            CaptureConfig.Builder from = CaptureConfig.Builder.from(next);
            if (next.getTemplateType() == 5 && next.getCameraCaptureResult() != null) {
                from.setCameraCaptureResult(next.getCameraCaptureResult());
            }
            if (!next.getSurfaces().isEmpty() || !next.isUseRepeatingSurface() || checkAndAttachRepeatingSurface(from)) {
                arrayList.add(from.build());
            }
        }
        debugLog("Issue capture request");
        this.mCaptureSession.issueCaptureRequests(arrayList);
    }

    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", new Object[]{Integer.valueOf(hashCode()), this.mCameraInfoInternal.getCameraId()});
    }

    static String getUseCaseId(UseCase useCase) {
        return useCase.getName() + useCase.hashCode();
    }

    /* access modifiers changed from: package-private */
    public void debugLog(String str) {
        debugLog(str, (Throwable) null);
    }

    private void debugLog(String str, Throwable th) {
        Logger.d(TAG, String.format("{%s} %s", new Object[]{toString(), str}), th);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState) {
        setState(internalState, (CameraState.StateError) null);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState, CameraState.StateError stateError) {
        setState(internalState, stateError, true);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState, CameraState.StateError stateError, boolean z) {
        CameraInternal.State state;
        debugLog("Transitioning camera internal state: " + this.mState + " --> " + internalState);
        this.mState = internalState;
        switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[internalState.ordinal()]) {
            case 1:
                state = CameraInternal.State.CLOSED;
                break;
            case 2:
                state = CameraInternal.State.PENDING_OPEN;
                break;
            case 3:
                state = CameraInternal.State.CLOSING;
                break;
            case 4:
                state = CameraInternal.State.OPEN;
                break;
            case 5:
            case 6:
                state = CameraInternal.State.OPENING;
                break;
            case 7:
                state = CameraInternal.State.RELEASING;
                break;
            case 8:
                state = CameraInternal.State.RELEASED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + internalState);
        }
        this.mCameraStateRegistry.markCameraState(this, state, z);
        this.mObservableState.postValue(state);
        this.mCameraStateMachine.updateState(state, stateError);
    }

    static abstract class UseCaseInfo {
        /* access modifiers changed from: package-private */
        public abstract SessionConfig getSessionConfig();

        /* access modifiers changed from: package-private */
        public abstract Size getSurfaceResolution();

        /* access modifiers changed from: package-private */
        public abstract UseCaseConfig<?> getUseCaseConfig();

        /* access modifiers changed from: package-private */
        public abstract String getUseCaseId();

        /* access modifiers changed from: package-private */
        public abstract Class<?> getUseCaseType();

        UseCaseInfo() {
        }

        static UseCaseInfo create(String str, Class<?> cls, SessionConfig sessionConfig, UseCaseConfig<?> useCaseConfig, Size size) {
            return new AutoValue_Camera2CameraImpl_UseCaseInfo(str, cls, sessionConfig, useCaseConfig, size);
        }

        static UseCaseInfo from(UseCase useCase) {
            return create(Camera2CameraImpl.getUseCaseId(useCase), useCase.getClass(), useCase.getSessionConfig(), useCase.getCurrentConfig(), useCase.getAttachedSurfaceResolution());
        }
    }

    final class StateCallback extends CameraDevice.StateCallback {
        private final CameraReopenMonitor mCameraReopenMonitor = new CameraReopenMonitor();
        private final Executor mExecutor;
        ScheduledFuture<?> mScheduledReopenHandle;
        private ScheduledReopen mScheduledReopenRunnable;
        private final ScheduledExecutorService mScheduler;

        StateCallback(Executor executor, ScheduledExecutorService scheduledExecutorService) {
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
        }

        public void onOpened(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onOpened()");
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.mCameraDeviceError = 0;
            resetReopenMonitor();
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i != 3) {
                if (i == 5 || i == 6) {
                    Camera2CameraImpl.this.setState(InternalState.OPENED);
                    Camera2CameraImpl.this.openCaptureSession();
                    return;
                } else if (i != 7) {
                    throw new IllegalStateException("onOpened() should not be possible from state: " + Camera2CameraImpl.this.mState);
                }
            }
            Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
            Camera2CameraImpl.this.mCameraDevice.close();
            Camera2CameraImpl.this.mCameraDevice = null;
        }

        public void onClosed(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onClosed()");
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDevice == null, "Unexpected onClose callback on camera device: " + cameraDevice);
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i != 3) {
                if (i != 6) {
                    if (i != 7) {
                        throw new IllegalStateException("Camera closed while in state: " + Camera2CameraImpl.this.mState);
                    }
                } else if (Camera2CameraImpl.this.mCameraDeviceError != 0) {
                    Camera2CameraImpl.this.debugLog("Camera closed due to error: " + Camera2CameraImpl.getErrorMessage(Camera2CameraImpl.this.mCameraDeviceError));
                    scheduleCameraReopen();
                    return;
                } else {
                    Camera2CameraImpl.this.tryOpenCameraDevice(false);
                    return;
                }
            }
            Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
            Camera2CameraImpl.this.finishClose();
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        public void onError(CameraDevice cameraDevice, int i) {
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.mCameraDeviceError = i;
            int i2 = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i2 != 3) {
                if (i2 == 4 || i2 == 5 || i2 == 6) {
                    Logger.d(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i), Camera2CameraImpl.this.mState.name()}));
                    handleErrorOnOpen(cameraDevice, i);
                    return;
                } else if (i2 != 7) {
                    throw new IllegalStateException("onError() should not be possible from state: " + Camera2CameraImpl.this.mState);
                }
            }
            Logger.e(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i), Camera2CameraImpl.this.mState.name()}));
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void handleErrorOnOpen(CameraDevice cameraDevice, int i) {
            Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.OPENING || Camera2CameraImpl.this.mState == InternalState.OPENED || Camera2CameraImpl.this.mState == InternalState.REOPENING, "Attempt to handle open error from non open state: " + Camera2CameraImpl.this.mState);
            if (i == 1 || i == 2 || i == 4) {
                Logger.d(Camera2CameraImpl.TAG, String.format("Attempt to reopen camera[%s] after error[%s]", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i)}));
                reopenCameraAfterError(i);
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + Camera2CameraImpl.getErrorMessage(i) + " closing camera.");
            Camera2CameraImpl.this.setState(InternalState.CLOSING, CameraState.StateError.create(i == 3 ? 5 : 6));
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void reopenCameraAfterError(int i) {
            int i2 = 1;
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDeviceError != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            if (i == 1) {
                i2 = 2;
            } else if (i != 2) {
                i2 = 3;
            }
            Camera2CameraImpl.this.setState(InternalState.REOPENING, CameraState.StateError.create(i2));
            Camera2CameraImpl.this.closeCamera(false);
        }

        /* access modifiers changed from: package-private */
        public void scheduleCameraReopen() {
            boolean z = true;
            Preconditions.checkState(this.mScheduledReopenRunnable == null);
            if (this.mScheduledReopenHandle != null) {
                z = false;
            }
            Preconditions.checkState(z);
            if (this.mCameraReopenMonitor.canScheduleCameraReopen()) {
                this.mScheduledReopenRunnable = new ScheduledReopen(this.mExecutor);
                Camera2CameraImpl.this.debugLog("Attempting camera re-open in " + this.mCameraReopenMonitor.getReopenDelayMs() + "ms: " + this.mScheduledReopenRunnable + " activeResuming = " + Camera2CameraImpl.this.mIsActiveResumingMode);
                this.mScheduledReopenHandle = this.mScheduler.schedule(this.mScheduledReopenRunnable, (long) this.mCameraReopenMonitor.getReopenDelayMs(), TimeUnit.MILLISECONDS);
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Camera reopening attempted for " + this.mCameraReopenMonitor.getReopenLimitMs() + "ms without success.");
            Camera2CameraImpl.this.setState(InternalState.PENDING_OPEN, (CameraState.StateError) null, false);
        }

        /* access modifiers changed from: package-private */
        public boolean cancelScheduledReopen() {
            if (this.mScheduledReopenHandle == null) {
                return false;
            }
            Camera2CameraImpl.this.debugLog("Cancelling scheduled re-open: " + this.mScheduledReopenRunnable);
            this.mScheduledReopenRunnable.cancel();
            this.mScheduledReopenRunnable = null;
            this.mScheduledReopenHandle.cancel(false);
            this.mScheduledReopenHandle = null;
            return true;
        }

        /* access modifiers changed from: package-private */
        public void resetReopenMonitor() {
            this.mCameraReopenMonitor.reset();
        }

        class ScheduledReopen implements Runnable {
            private boolean mCancelled = false;
            private Executor mExecutor;

            ScheduledReopen(Executor executor) {
                this.mExecutor = executor;
            }

            /* access modifiers changed from: package-private */
            public void cancel() {
                this.mCancelled = true;
            }

            public void run() {
                this.mExecutor.execute(new Camera2CameraImpl$StateCallback$ScheduledReopen$$ExternalSyntheticLambda0(this));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$run$0$androidx-camera-camera2-internal-Camera2CameraImpl$StateCallback$ScheduledReopen  reason: not valid java name */
            public /* synthetic */ void m26lambda$run$0$androidxcameracamera2internalCamera2CameraImpl$StateCallback$ScheduledReopen() {
                if (!this.mCancelled) {
                    Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.REOPENING);
                    if (StateCallback.this.shouldActiveResume()) {
                        Camera2CameraImpl.this.tryForceOpenCameraDevice(true);
                    } else {
                        Camera2CameraImpl.this.tryOpenCameraDevice(true);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldActiveResume() {
            return Camera2CameraImpl.this.mIsActiveResumingMode && (Camera2CameraImpl.this.mCameraDeviceError == 1 || Camera2CameraImpl.this.mCameraDeviceError == 2);
        }

        class CameraReopenMonitor {
            static final int ACTIVE_REOPEN_DELAY_BASE_MS = 1000;
            static final int ACTIVE_REOPEN_LIMIT_MS = 1800000;
            static final int INVALID_TIME = -1;
            static final int REOPEN_DELAY_MS = 700;
            static final int REOPEN_LIMIT_MS = 10000;
            private long mFirstReopenTime = -1;

            CameraReopenMonitor() {
            }

            /* access modifiers changed from: package-private */
            public int getReopenDelayMs() {
                if (!StateCallback.this.shouldActiveResume()) {
                    return 700;
                }
                long elapsedTime = getElapsedTime();
                if (elapsedTime <= 120000) {
                    return 1000;
                }
                return elapsedTime <= 300000 ? 2000 : 4000;
            }

            /* access modifiers changed from: package-private */
            public int getReopenLimitMs() {
                if (!StateCallback.this.shouldActiveResume()) {
                    return 10000;
                }
                return ACTIVE_REOPEN_LIMIT_MS;
            }

            /* access modifiers changed from: package-private */
            public long getElapsedTime() {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (this.mFirstReopenTime == -1) {
                    this.mFirstReopenTime = uptimeMillis;
                }
                return uptimeMillis - this.mFirstReopenTime;
            }

            /* access modifiers changed from: package-private */
            public boolean canScheduleCameraReopen() {
                if (!(getElapsedTime() >= ((long) getReopenLimitMs()))) {
                    return true;
                }
                reset();
                return false;
            }

            /* access modifiers changed from: package-private */
            public void reset() {
                this.mFirstReopenTime = -1;
            }
        }
    }

    final class CameraAvailability extends CameraManager.AvailabilityCallback implements CameraStateRegistry.OnOpenAvailableListener {
        private boolean mCameraAvailable = true;
        private final String mCameraId;

        CameraAvailability(String str) {
            this.mCameraId = str;
        }

        public void onCameraAvailable(String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = true;
                if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                    Camera2CameraImpl.this.tryOpenCameraDevice(false);
                }
            }
        }

        public void onCameraUnavailable(String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = false;
            }
        }

        public void onOpenAvailable() {
            if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                Camera2CameraImpl.this.tryOpenCameraDevice(false);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isCameraAvailable() {
            return this.mCameraAvailable;
        }
    }

    final class ControlUpdateListenerInternal implements CameraControlInternal.ControlUpdateCallback {
        ControlUpdateListenerInternal() {
        }

        public void onCameraControlUpdateSessionConfig() {
            Camera2CameraImpl.this.updateCaptureSessionConfig();
        }

        public void onCameraControlCaptureRequests(List<CaptureConfig> list) {
            Camera2CameraImpl.this.submitCaptureRequests((List) Preconditions.checkNotNull(list));
        }
    }
}
