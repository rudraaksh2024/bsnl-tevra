package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.SessionProcessorSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

final class ProcessingCaptureSession implements CaptureSessionInterface {
    private static final String TAG = "ProcessingCaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    private static List<DeferrableSurface> sHeldProcessorSurfaces = new ArrayList();
    private static int sNextInstanceId = 0;
    private final Camera2CameraInfoImpl mCamera2CameraInfoImpl;
    private final CaptureSession mCaptureSession = new CaptureSession();
    final Executor mExecutor;
    private int mInstanceId = 0;
    volatile boolean mIsExecutingStillCaptureRequest = false;
    private List<DeferrableSurface> mOutputSurfaces = new ArrayList();
    private volatile CaptureConfig mPendingCaptureConfig = null;
    private SessionConfig mProcessorSessionConfig;
    private ProcessorState mProcessorState;
    private Camera2RequestProcessor mRequestProcessor;
    private final ScheduledExecutorService mScheduledExecutorService;
    private SessionConfig mSessionConfig;
    private CaptureRequestOptions mSessionOptions = new CaptureRequestOptions.Builder().build();
    private final SessionProcessor mSessionProcessor;
    private final SessionProcessorCaptureCallback mSessionProcessorCaptureCallback;
    private CaptureRequestOptions mStillCaptureOptions = new CaptureRequestOptions.Builder().build();

    private enum ProcessorState {
        UNINITIALIZED,
        SESSION_INITIALIZED,
        ON_CAPTURE_SESSION_STARTED,
        ON_CAPTURE_SESSION_ENDED,
        CLOSED
    }

    ProcessingCaptureSession(SessionProcessor sessionProcessor, Camera2CameraInfoImpl camera2CameraInfoImpl, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.mSessionProcessor = sessionProcessor;
        this.mCamera2CameraInfoImpl = camera2CameraInfoImpl;
        this.mExecutor = executor;
        this.mScheduledExecutorService = scheduledExecutorService;
        this.mProcessorState = ProcessorState.UNINITIALIZED;
        this.mSessionProcessorCaptureCallback = new SessionProcessorCaptureCallback();
        int i = sNextInstanceId;
        sNextInstanceId = i + 1;
        this.mInstanceId = i;
        Logger.d(TAG, "New ProcessingCaptureSession (id=" + this.mInstanceId + ")");
    }

    public ListenableFuture<Void> open(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener) {
        Preconditions.checkArgument(this.mProcessorState == ProcessorState.UNINITIALIZED, "Invalid state state:" + this.mProcessorState);
        Preconditions.checkArgument(!sessionConfig.getSurfaces().isEmpty(), "SessionConfig contains no surfaces");
        Logger.d(TAG, "open (id=" + this.mInstanceId + ")");
        List<DeferrableSurface> surfaces = sessionConfig.getSurfaces();
        this.mOutputSurfaces = surfaces;
        return FutureChain.from(DeferrableSurfaces.surfaceListWithTimeout(surfaces, false, 5000, this.mExecutor, this.mScheduledExecutorService)).transformAsync(new ProcessingCaptureSession$$ExternalSyntheticLambda2(this, sessionConfig, cameraDevice, synchronizedCaptureSessionOpener), this.mExecutor).transform(new ProcessingCaptureSession$$ExternalSyntheticLambda3(this), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$2$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ ListenableFuture m52lambda$open$2$androidxcameracamera2internalProcessingCaptureSession(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener, List list) throws Exception {
        Logger.d(TAG, "-- getSurfaces done, start init (id=" + this.mInstanceId + ")");
        if (this.mProcessorState == ProcessorState.CLOSED) {
            return Futures.immediateFailedFuture(new IllegalStateException("SessionProcessorCaptureSession is closed."));
        }
        OutputSurface outputSurface = null;
        if (list.contains((Object) null)) {
            return Futures.immediateFailedFuture(new DeferrableSurface.SurfaceClosedException("Surface closed", sessionConfig.getSurfaces().get(list.indexOf((Object) null))));
        }
        try {
            DeferrableSurfaces.incrementAll(this.mOutputSurfaces);
            OutputSurface outputSurface2 = null;
            OutputSurface outputSurface3 = null;
            for (int i = 0; i < sessionConfig.getSurfaces().size(); i++) {
                DeferrableSurface deferrableSurface = sessionConfig.getSurfaces().get(i);
                if (Objects.equals(deferrableSurface.getContainerClass(), Preview.class)) {
                    outputSurface = OutputSurface.create((Surface) deferrableSurface.getSurface().get(), new Size(deferrableSurface.getPrescribedSize().getWidth(), deferrableSurface.getPrescribedSize().getHeight()), deferrableSurface.getPrescribedStreamFormat());
                } else if (Objects.equals(deferrableSurface.getContainerClass(), ImageCapture.class)) {
                    outputSurface2 = OutputSurface.create((Surface) deferrableSurface.getSurface().get(), new Size(deferrableSurface.getPrescribedSize().getWidth(), deferrableSurface.getPrescribedSize().getHeight()), deferrableSurface.getPrescribedStreamFormat());
                } else if (Objects.equals(deferrableSurface.getContainerClass(), ImageAnalysis.class)) {
                    outputSurface3 = OutputSurface.create((Surface) deferrableSurface.getSurface().get(), new Size(deferrableSurface.getPrescribedSize().getWidth(), deferrableSurface.getPrescribedSize().getHeight()), deferrableSurface.getPrescribedStreamFormat());
                }
            }
            this.mProcessorState = ProcessorState.SESSION_INITIALIZED;
            Logger.w(TAG, "== initSession (id=" + this.mInstanceId + ")");
            SessionConfig initSession = this.mSessionProcessor.initSession(this.mCamera2CameraInfoImpl, outputSurface, outputSurface2, outputSurface3);
            this.mProcessorSessionConfig = initSession;
            initSession.getSurfaces().get(0).getTerminationFuture().addListener(new ProcessingCaptureSession$$ExternalSyntheticLambda0(this), CameraXExecutors.directExecutor());
            for (DeferrableSurface next : this.mProcessorSessionConfig.getSurfaces()) {
                sHeldProcessorSurfaces.add(next);
                next.getTerminationFuture().addListener(new ProcessingCaptureSession$$ExternalSyntheticLambda1(next), this.mExecutor);
            }
            SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
            validatingBuilder.add(sessionConfig);
            validatingBuilder.clearSurfaces();
            validatingBuilder.add(this.mProcessorSessionConfig);
            Preconditions.checkArgument(validatingBuilder.isValid(), "Cannot transform the SessionConfig");
            ListenableFuture<Void> open = this.mCaptureSession.open(validatingBuilder.build(), (CameraDevice) Preconditions.checkNotNull(cameraDevice), synchronizedCaptureSessionOpener);
            Futures.addCallback(open, new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                }

                public void onFailure(Throwable th) {
                    Logger.e(ProcessingCaptureSession.TAG, "open session failed ", th);
                    ProcessingCaptureSession.this.close();
                }
            }, this.mExecutor);
            return open;
        } catch (DeferrableSurface.SurfaceClosedException e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$0$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ void m51lambda$open$0$androidxcameracamera2internalProcessingCaptureSession() {
        DeferrableSurfaces.decrementAll(this.mOutputSurfaces);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$3$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ Void m53lambda$open$3$androidxcameracamera2internalProcessingCaptureSession(Void voidR) {
        onConfigured(this.mCaptureSession);
        return null;
    }

    private static void cancelRequests(List<CaptureConfig> list) {
        for (CaptureConfig cameraCaptureCallbacks : list) {
            for (CameraCaptureCallback onCaptureCancelled : cameraCaptureCallbacks.getCameraCaptureCallbacks()) {
                onCaptureCancelled.onCaptureCancelled();
            }
        }
    }

    private boolean isStillCapture(List<CaptureConfig> list) {
        if (list.isEmpty()) {
            return false;
        }
        for (CaptureConfig templateType : list) {
            if (templateType.getTemplateType() != 2) {
                return false;
            }
        }
        return true;
    }

    public void issueCaptureRequests(List<CaptureConfig> list) {
        if (!list.isEmpty()) {
            if (list.size() > 1 || !isStillCapture(list)) {
                cancelRequests(list);
            } else if (this.mPendingCaptureConfig != null || this.mIsExecutingStillCaptureRequest) {
                cancelRequests(list);
            } else {
                final CaptureConfig captureConfig = list.get(0);
                Logger.d(TAG, "issueCaptureRequests (id=" + this.mInstanceId + ") + state =" + this.mProcessorState);
                int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState[this.mProcessorState.ordinal()];
                if (i == 1 || i == 2) {
                    this.mPendingCaptureConfig = captureConfig;
                } else if (i == 3) {
                    this.mIsExecutingStillCaptureRequest = true;
                    CaptureRequestOptions.Builder from = CaptureRequestOptions.Builder.from(captureConfig.getImplementationOptions());
                    if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_ROTATION)) {
                        from.setCaptureRequestOption(CaptureRequest.JPEG_ORIENTATION, (Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_ROTATION));
                    }
                    if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_JPEG_QUALITY)) {
                        from.setCaptureRequestOption(CaptureRequest.JPEG_QUALITY, Byte.valueOf(((Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_JPEG_QUALITY)).byteValue()));
                    }
                    CaptureRequestOptions build = from.build();
                    this.mStillCaptureOptions = build;
                    updateParameters(this.mSessionOptions, build);
                    this.mSessionProcessor.startCapture(new SessionProcessor.CaptureCallback() {
                        public void onCaptureCompleted(long j, int i, Map<CaptureResult.Key, Object> map) {
                        }

                        public void onCaptureProcessStarted(int i) {
                        }

                        public void onCaptureSequenceAborted(int i) {
                        }

                        public void onCaptureStarted(int i, long j) {
                        }

                        public void onCaptureFailed(int i) {
                            ProcessingCaptureSession.this.mExecutor.execute(new ProcessingCaptureSession$2$$ExternalSyntheticLambda1(this, captureConfig));
                        }

                        /* access modifiers changed from: package-private */
                        /* renamed from: lambda$onCaptureFailed$0$androidx-camera-camera2-internal-ProcessingCaptureSession$2  reason: not valid java name */
                        public /* synthetic */ void m54lambda$onCaptureFailed$0$androidxcameracamera2internalProcessingCaptureSession$2(CaptureConfig captureConfig) {
                            for (CameraCaptureCallback onCaptureFailed : captureConfig.getCameraCaptureCallbacks()) {
                                onCaptureFailed.onCaptureFailed(new CameraCaptureFailure(CameraCaptureFailure.Reason.ERROR));
                            }
                            ProcessingCaptureSession.this.mIsExecutingStillCaptureRequest = false;
                        }

                        public void onCaptureSequenceCompleted(int i) {
                            ProcessingCaptureSession.this.mExecutor.execute(new ProcessingCaptureSession$2$$ExternalSyntheticLambda0(this, captureConfig));
                        }

                        /* access modifiers changed from: package-private */
                        /* renamed from: lambda$onCaptureSequenceCompleted$1$androidx-camera-camera2-internal-ProcessingCaptureSession$2  reason: not valid java name */
                        public /* synthetic */ void m55lambda$onCaptureSequenceCompleted$1$androidxcameracamera2internalProcessingCaptureSession$2(CaptureConfig captureConfig) {
                            for (CameraCaptureCallback onCaptureCompleted : captureConfig.getCameraCaptureCallbacks()) {
                                onCaptureCompleted.onCaptureCompleted(new CameraCaptureResult.EmptyCameraCaptureResult());
                            }
                            ProcessingCaptureSession.this.mIsExecutingStillCaptureRequest = false;
                        }
                    });
                } else if (i == 4 || i == 5) {
                    Logger.d(TAG, "Run issueCaptureRequests in wrong state, state = " + this.mProcessorState);
                    cancelRequests(list);
                }
            }
        }
    }

    /* renamed from: androidx.camera.camera2.internal.ProcessingCaptureSession$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState[] r0 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState = r0
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState r1 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.UNINITIALIZED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState r1 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.SESSION_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState r1 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.ON_CAPTURE_SESSION_STARTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState r1 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.ON_CAPTURE_SESSION_ENDED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.camera.camera2.internal.ProcessingCaptureSession$ProcessorState r1 = androidx.camera.camera2.internal.ProcessingCaptureSession.ProcessorState.CLOSED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.ProcessingCaptureSession.AnonymousClass3.<clinit>():void");
        }
    }

    public ListenableFuture<Void> release(boolean z) {
        Preconditions.checkState(this.mProcessorState == ProcessorState.CLOSED, "release() can only be called in CLOSED state");
        Logger.d(TAG, "release (id=" + this.mInstanceId + ")");
        return this.mCaptureSession.release(z);
    }

    private static List<SessionProcessorSurface> getSessionProcessorSurfaceList(List<DeferrableSurface> list) {
        ArrayList arrayList = new ArrayList();
        for (DeferrableSurface next : list) {
            Preconditions.checkArgument(next instanceof SessionProcessorSurface, "Surface must be SessionProcessorSurface");
            arrayList.add((SessionProcessorSurface) next);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void onConfigured(CaptureSession captureSession) {
        Preconditions.checkArgument(this.mProcessorState == ProcessorState.SESSION_INITIALIZED, "Invalid state state:" + this.mProcessorState);
        Camera2RequestProcessor camera2RequestProcessor = new Camera2RequestProcessor(captureSession, getSessionProcessorSurfaceList(this.mProcessorSessionConfig.getSurfaces()));
        this.mRequestProcessor = camera2RequestProcessor;
        this.mSessionProcessor.onCaptureSessionStart(camera2RequestProcessor);
        this.mProcessorState = ProcessorState.ON_CAPTURE_SESSION_STARTED;
        SessionConfig sessionConfig = this.mSessionConfig;
        if (sessionConfig != null) {
            setSessionConfig(sessionConfig);
        }
        if (this.mPendingCaptureConfig != null) {
            List asList = Arrays.asList(new CaptureConfig[]{this.mPendingCaptureConfig});
            this.mPendingCaptureConfig = null;
            issueCaptureRequests(asList);
        }
    }

    public SessionConfig getSessionConfig() {
        return this.mSessionConfig;
    }

    public List<CaptureConfig> getCaptureConfigs() {
        if (this.mPendingCaptureConfig == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(new CaptureConfig[]{this.mPendingCaptureConfig});
    }

    public void cancelIssuedCaptureRequests() {
        Logger.d(TAG, "cancelIssuedCaptureRequests (id=" + this.mInstanceId + ")");
        if (this.mPendingCaptureConfig != null) {
            for (CameraCaptureCallback onCaptureCancelled : this.mPendingCaptureConfig.getCameraCaptureCallbacks()) {
                onCaptureCancelled.onCaptureCancelled();
            }
            this.mPendingCaptureConfig = null;
        }
    }

    public void close() {
        Logger.d(TAG, "close (id=" + this.mInstanceId + ") state=" + this.mProcessorState);
        int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$ProcessingCaptureSession$ProcessorState[this.mProcessorState.ordinal()];
        if (i != 2) {
            if (i == 3) {
                this.mSessionProcessor.onCaptureSessionEnd();
                Camera2RequestProcessor camera2RequestProcessor = this.mRequestProcessor;
                if (camera2RequestProcessor != null) {
                    camera2RequestProcessor.close();
                }
                this.mProcessorState = ProcessorState.ON_CAPTURE_SESSION_ENDED;
            } else if (i != 4) {
                if (i == 5) {
                    return;
                }
                this.mProcessorState = ProcessorState.CLOSED;
                this.mCaptureSession.close();
            }
        }
        this.mSessionProcessor.deInitSession();
        this.mProcessorState = ProcessorState.CLOSED;
        this.mCaptureSession.close();
    }

    public void setSessionConfig(SessionConfig sessionConfig) {
        Logger.d(TAG, "setSessionConfig (id=" + this.mInstanceId + ")");
        this.mSessionConfig = sessionConfig;
        if (sessionConfig != null) {
            Camera2RequestProcessor camera2RequestProcessor = this.mRequestProcessor;
            if (camera2RequestProcessor != null) {
                camera2RequestProcessor.updateSessionConfig(sessionConfig);
            }
            if (this.mProcessorState == ProcessorState.ON_CAPTURE_SESSION_STARTED) {
                CaptureRequestOptions build = CaptureRequestOptions.Builder.from(sessionConfig.getImplementationOptions()).build();
                this.mSessionOptions = build;
                updateParameters(build, this.mStillCaptureOptions);
                this.mSessionProcessor.startRepeating(this.mSessionProcessorCaptureCallback);
            }
        }
    }

    private void updateParameters(CaptureRequestOptions captureRequestOptions, CaptureRequestOptions captureRequestOptions2) {
        Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
        builder.insertAllOptions(captureRequestOptions);
        builder.insertAllOptions(captureRequestOptions2);
        this.mSessionProcessor.setParameters(builder.build());
    }

    private static class SessionProcessorCaptureCallback implements SessionProcessor.CaptureCallback {
        public void onCaptureCompleted(long j, int i, Map<CaptureResult.Key, Object> map) {
        }

        public void onCaptureFailed(int i) {
        }

        public void onCaptureProcessStarted(int i) {
        }

        public void onCaptureSequenceAborted(int i) {
        }

        public void onCaptureSequenceCompleted(int i) {
        }

        public void onCaptureStarted(int i, long j) {
        }

        SessionProcessorCaptureCallback() {
        }
    }
}
