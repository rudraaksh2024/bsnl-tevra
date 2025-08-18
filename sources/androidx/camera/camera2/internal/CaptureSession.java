package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.camera.camera2.impl.CameraEventCallbacks;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow;
import androidx.camera.camera2.internal.compat.workaround.TorchStateReset;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;

final class CaptureSession implements CaptureSessionInterface {
    private static final String TAG = "CaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    CameraEventCallbacks mCameraEventCallbacks;
    Config mCameraEventOnRepeatingOptions;
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;
    private final List<CaptureConfig> mCaptureConfigs;
    private final StateCallback mCaptureSessionStateCallback;
    List<DeferrableSurface> mConfiguredDeferrableSurfaces;
    private final Map<DeferrableSurface, Surface> mConfiguredSurfaceMap;
    CallbackToFutureAdapter.Completer<Void> mReleaseCompleter;
    ListenableFuture<Void> mReleaseFuture;
    SessionConfig mSessionConfig;
    final Object mSessionLock;
    State mState;
    final StillCaptureFlow mStillCaptureFlow;
    SynchronizedCaptureSession mSynchronizedCaptureSession;
    SynchronizedCaptureSessionOpener mSynchronizedCaptureSessionOpener;
    final TorchStateReset mTorchStateReset;

    enum State {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    CaptureSession() {
        this.mSessionLock = new Object();
        this.mCaptureConfigs = new ArrayList();
        this.mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
            public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            }
        };
        this.mCameraEventOnRepeatingOptions = OptionsBundle.emptyBundle();
        this.mCameraEventCallbacks = CameraEventCallbacks.createEmptyCallback();
        this.mConfiguredSurfaceMap = new HashMap();
        this.mConfiguredDeferrableSurfaces = Collections.emptyList();
        this.mState = State.UNINITIALIZED;
        this.mStillCaptureFlow = new StillCaptureFlow();
        this.mTorchStateReset = new TorchStateReset();
        this.mState = State.INITIALIZED;
        this.mCaptureSessionStateCallback = new StateCallback();
    }

    public SessionConfig getSessionConfig() {
        SessionConfig sessionConfig;
        synchronized (this.mSessionLock) {
            sessionConfig = this.mSessionConfig;
        }
        return sessionConfig;
    }

    /* renamed from: androidx.camera.camera2.internal.CaptureSession$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State;

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
                androidx.camera.camera2.internal.CaptureSession$State[] r0 = androidx.camera.camera2.internal.CaptureSession.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State = r0
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.UNINITIALIZED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.GET_SURFACE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.OPENING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.OPENED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.CLOSED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.RELEASING     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                androidx.camera.camera2.internal.CaptureSession$State r1 = androidx.camera.camera2.internal.CaptureSession.State.RELEASED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.AnonymousClass4.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSessionConfig(androidx.camera.core.impl.SessionConfig r5) {
        /*
            r4 = this;
            java.lang.String r0 = "setSessionConfig() should not be possible in state: "
            java.lang.Object r1 = r4.mSessionLock
            monitor-enter(r1)
            int[] r2 = androidx.camera.camera2.internal.CaptureSession.AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ all -> 0x0061 }
            androidx.camera.camera2.internal.CaptureSession$State r3 = r4.mState     // Catch:{ all -> 0x0061 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x0061 }
            r2 = r2[r3]     // Catch:{ all -> 0x0061 }
            switch(r2) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0047;
                case 3: goto L_0x0047;
                case 4: goto L_0x0047;
                case 5: goto L_0x001b;
                case 6: goto L_0x0013;
                case 7: goto L_0x0013;
                case 8: goto L_0x0013;
                default: goto L_0x0012;
            }     // Catch:{ all -> 0x0061 }
        L_0x0012:
            goto L_0x005f
        L_0x0013:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = "Session configuration cannot be set on a closed/released session."
            r4.<init>(r5)     // Catch:{ all -> 0x0061 }
            throw r4     // Catch:{ all -> 0x0061 }
        L_0x001b:
            r4.mSessionConfig = r5     // Catch:{ all -> 0x0061 }
            if (r5 != 0) goto L_0x0021
            monitor-exit(r1)     // Catch:{ all -> 0x0061 }
            return
        L_0x0021:
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r0 = r4.mConfiguredSurfaceMap     // Catch:{ all -> 0x0061 }
            java.util.Set r0 = r0.keySet()     // Catch:{ all -> 0x0061 }
            java.util.List r5 = r5.getSurfaces()     // Catch:{ all -> 0x0061 }
            boolean r5 = r0.containsAll(r5)     // Catch:{ all -> 0x0061 }
            if (r5 != 0) goto L_0x003a
            java.lang.String r4 = "CaptureSession"
            java.lang.String r5 = "Does not have the proper configured lists"
            androidx.camera.core.Logger.e(r4, r5)     // Catch:{ all -> 0x0061 }
            monitor-exit(r1)     // Catch:{ all -> 0x0061 }
            return
        L_0x003a:
            java.lang.String r5 = "CaptureSession"
            java.lang.String r0 = "Attempting to submit CaptureRequest after setting"
            androidx.camera.core.Logger.d(r5, r0)     // Catch:{ all -> 0x0061 }
            androidx.camera.core.impl.SessionConfig r5 = r4.mSessionConfig     // Catch:{ all -> 0x0061 }
            r4.issueRepeatingCaptureRequests(r5)     // Catch:{ all -> 0x0061 }
            goto L_0x005f
        L_0x0047:
            r4.mSessionConfig = r5     // Catch:{ all -> 0x0061 }
            goto L_0x005f
        L_0x004a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r2.<init>(r0)     // Catch:{ all -> 0x0061 }
            androidx.camera.camera2.internal.CaptureSession$State r4 = r4.mState     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r4 = r2.append(r4)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0061 }
            r5.<init>(r4)     // Catch:{ all -> 0x0061 }
            throw r5     // Catch:{ all -> 0x0061 }
        L_0x005f:
            monitor-exit(r1)     // Catch:{ all -> 0x0061 }
            return
        L_0x0061:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0061 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.setSessionConfig(androidx.camera.core.impl.SessionConfig):void");
    }

    public ListenableFuture<Void> open(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener) {
        synchronized (this.mSessionLock) {
            if (AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()] != 2) {
                Logger.e(TAG, "Open not allowed in state: " + this.mState);
                ListenableFuture<Void> immediateFailedFuture = Futures.immediateFailedFuture(new IllegalStateException("open() should not allow the state: " + this.mState));
                return immediateFailedFuture;
            }
            this.mState = State.GET_SURFACE;
            ArrayList arrayList = new ArrayList(sessionConfig.getSurfaces());
            this.mConfiguredDeferrableSurfaces = arrayList;
            this.mSynchronizedCaptureSessionOpener = synchronizedCaptureSessionOpener;
            FutureChain<T> transformAsync = FutureChain.from(synchronizedCaptureSessionOpener.startWithDeferrableSurface(arrayList, 5000)).transformAsync(new CaptureSession$$ExternalSyntheticLambda1(this, sessionConfig, cameraDevice), this.mSynchronizedCaptureSessionOpener.getExecutor());
            Futures.addCallback(transformAsync, new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                }

                public void onFailure(Throwable th) {
                    synchronized (CaptureSession.this.mSessionLock) {
                        CaptureSession.this.mSynchronizedCaptureSessionOpener.stop();
                        int i = AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()];
                        if (i == 4 || i == 6 || i == 7) {
                            if (!(th instanceof CancellationException)) {
                                Logger.w(CaptureSession.TAG, "Opening session with fail " + CaptureSession.this.mState, th);
                                CaptureSession.this.finishClose();
                            }
                        }
                    }
                }
            }, this.mSynchronizedCaptureSessionOpener.getExecutor());
            ListenableFuture<Void> nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
            return nonCancellationPropagating;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0134, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x013a, code lost:
        return androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: openCaptureSession */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<java.lang.Void> m37lambda$open$0$androidxcameracamera2internalCaptureSession(java.util.List<android.view.Surface> r11, androidx.camera.core.impl.SessionConfig r12, android.hardware.camera2.CameraDevice r13) {
        /*
            r10 = this;
            java.lang.String r0 = "openCaptureSession() not execute in state: "
            java.lang.String r1 = "openCaptureSession() should not be possible in state: "
            java.lang.Object r2 = r10.mSessionLock
            monitor-enter(r2)
            int[] r3 = androidx.camera.camera2.internal.CaptureSession.AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.CaptureSession$State r4 = r10.mState     // Catch:{ all -> 0x0155 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0155 }
            r3 = r3[r4]     // Catch:{ all -> 0x0155 }
            r4 = 1
            if (r3 == r4) goto L_0x013b
            r5 = 2
            if (r3 == r5) goto L_0x013b
            r6 = 3
            r7 = 5
            if (r3 == r6) goto L_0x0037
            if (r3 == r7) goto L_0x013b
            java.util.concurrent.CancellationException r11 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x0155 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0155 }
            r12.<init>(r0)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.CaptureSession$State r10 = r10.mState     // Catch:{ all -> 0x0155 }
            java.lang.StringBuilder r10 = r12.append(r10)     // Catch:{ all -> 0x0155 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0155 }
            r11.<init>(r10)     // Catch:{ all -> 0x0155 }
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r11)     // Catch:{ all -> 0x0155 }
            monitor-exit(r2)     // Catch:{ all -> 0x0155 }
            return r10
        L_0x0037:
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r0 = r10.mConfiguredSurfaceMap     // Catch:{ all -> 0x0155 }
            r0.clear()     // Catch:{ all -> 0x0155 }
            r0 = 0
            r1 = r0
        L_0x003e:
            int r3 = r11.size()     // Catch:{ all -> 0x0155 }
            if (r1 >= r3) goto L_0x005a
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r3 = r10.mConfiguredSurfaceMap     // Catch:{ all -> 0x0155 }
            java.util.List<androidx.camera.core.impl.DeferrableSurface> r6 = r10.mConfiguredDeferrableSurfaces     // Catch:{ all -> 0x0155 }
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.DeferrableSurface r6 = (androidx.camera.core.impl.DeferrableSurface) r6     // Catch:{ all -> 0x0155 }
            java.lang.Object r8 = r11.get(r1)     // Catch:{ all -> 0x0155 }
            android.view.Surface r8 = (android.view.Surface) r8     // Catch:{ all -> 0x0155 }
            r3.put(r6, r8)     // Catch:{ all -> 0x0155 }
            int r1 = r1 + 1
            goto L_0x003e
        L_0x005a:
            androidx.camera.camera2.internal.CaptureSession$State r11 = androidx.camera.camera2.internal.CaptureSession.State.OPENING     // Catch:{ all -> 0x0155 }
            r10.mState = r11     // Catch:{ all -> 0x0155 }
            java.lang.String r11 = "CaptureSession"
            java.lang.String r1 = "Opening capture session."
            androidx.camera.core.Logger.d(r11, r1)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$StateCallback[] r11 = new androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback[r5]     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.CaptureSession$StateCallback r1 = r10.mCaptureSessionStateCallback     // Catch:{ all -> 0x0155 }
            r11[r0] = r1     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks$Adapter r1 = new androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks$Adapter     // Catch:{ all -> 0x0155 }
            java.util.List r3 = r12.getSessionStateCallbacks()     // Catch:{ all -> 0x0155 }
            r1.<init>((java.util.List<android.hardware.camera2.CameraCaptureSession.StateCallback>) r3)     // Catch:{ all -> 0x0155 }
            r11[r4] = r1     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$StateCallback r11 = androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks.createComboCallback(r11)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.impl.Camera2ImplConfig r1 = new androidx.camera.camera2.impl.Camera2ImplConfig     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.Config r3 = r12.getImplementationOptions()     // Catch:{ all -> 0x0155 }
            r1.<init>(r3)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.impl.CameraEventCallbacks r3 = androidx.camera.camera2.impl.CameraEventCallbacks.createEmptyCallback()     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.impl.CameraEventCallbacks r3 = r1.getCameraEventCallback(r3)     // Catch:{ all -> 0x0155 }
            r10.mCameraEventCallbacks = r3     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.impl.CameraEventCallbacks$ComboCameraEventCallback r3 = r3.createComboCallback()     // Catch:{ all -> 0x0155 }
            java.util.List r3 = r3.onInitSession()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.CaptureConfig r4 = r12.getRepeatingCaptureConfig()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.CaptureConfig$Builder r4 = androidx.camera.core.impl.CaptureConfig.Builder.from(r4)     // Catch:{ all -> 0x0155 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0155 }
        L_0x00a1:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x0155 }
            if (r5 == 0) goto L_0x00b5
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.CaptureConfig r5 = (androidx.camera.core.impl.CaptureConfig) r5     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.Config r5 = r5.getImplementationOptions()     // Catch:{ all -> 0x0155 }
            r4.addImplementationOptions(r5)     // Catch:{ all -> 0x0155 }
            goto L_0x00a1
        L_0x00b5:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0155 }
            r3.<init>()     // Catch:{ all -> 0x0155 }
            r5 = 0
            java.lang.String r1 = r1.getPhysicalCameraId(r5)     // Catch:{ all -> 0x0155 }
            java.util.List r5 = r12.getOutputConfigs()     // Catch:{ all -> 0x0155 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0155 }
        L_0x00c7:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0155 }
            if (r6 == 0) goto L_0x00fc
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.SessionConfig$OutputConfig r6 = (androidx.camera.core.impl.SessionConfig.OutputConfig) r6     // Catch:{ all -> 0x0155 }
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r8 = r10.mConfiguredSurfaceMap     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat r6 = r10.getOutputConfigurationCompat(r6, r8, r1)     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.Config r8 = r12.getImplementationOptions()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.Config$Option<java.lang.Long> r9 = androidx.camera.camera2.impl.Camera2ImplConfig.STREAM_USE_CASE_OPTION     // Catch:{ all -> 0x0155 }
            boolean r8 = r8.containsOption(r9)     // Catch:{ all -> 0x0155 }
            if (r8 == 0) goto L_0x00f8
            androidx.camera.core.impl.Config r8 = r12.getImplementationOptions()     // Catch:{ all -> 0x0155 }
            androidx.camera.core.impl.Config$Option<java.lang.Long> r9 = androidx.camera.camera2.impl.Camera2ImplConfig.STREAM_USE_CASE_OPTION     // Catch:{ all -> 0x0155 }
            java.lang.Object r8 = r8.retrieveOption(r9)     // Catch:{ all -> 0x0155 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0155 }
            long r8 = r8.longValue()     // Catch:{ all -> 0x0155 }
            r6.setStreamUseCase(r8)     // Catch:{ all -> 0x0155 }
        L_0x00f8:
            r3.add(r6)     // Catch:{ all -> 0x0155 }
            goto L_0x00c7
        L_0x00fc:
            java.util.List r1 = r10.getUniqueOutputConfigurations(r3)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r3 = r10.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat r11 = r3.createSessionConfigurationCompat(r0, r1, r11)     // Catch:{ all -> 0x0155 }
            int r0 = r12.getTemplateType()     // Catch:{ all -> 0x0155 }
            if (r0 != r7) goto L_0x011d
            android.hardware.camera2.params.InputConfiguration r0 = r12.getInputConfiguration()     // Catch:{ all -> 0x0155 }
            if (r0 == 0) goto L_0x011d
            android.hardware.camera2.params.InputConfiguration r12 = r12.getInputConfiguration()     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.compat.params.InputConfigurationCompat r12 = androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.wrap(r12)     // Catch:{ all -> 0x0155 }
            r11.setInputConfiguration(r12)     // Catch:{ all -> 0x0155 }
        L_0x011d:
            androidx.camera.core.impl.CaptureConfig r12 = r4.build()     // Catch:{ CameraAccessException -> 0x0134 }
            android.hardware.camera2.CaptureRequest r12 = androidx.camera.camera2.internal.Camera2CaptureRequestBuilder.buildWithoutTarget(r12, r13)     // Catch:{ CameraAccessException -> 0x0134 }
            if (r12 == 0) goto L_0x012a
            r11.setSessionParameters(r12)     // Catch:{ CameraAccessException -> 0x0134 }
        L_0x012a:
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r12 = r10.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x0155 }
            java.util.List<androidx.camera.core.impl.DeferrableSurface> r10 = r10.mConfiguredDeferrableSurfaces     // Catch:{ all -> 0x0155 }
            com.google.common.util.concurrent.ListenableFuture r10 = r12.openCaptureSession(r13, r11, r10)     // Catch:{ all -> 0x0155 }
            monitor-exit(r2)     // Catch:{ all -> 0x0155 }
            return r10
        L_0x0134:
            r10 = move-exception
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10)     // Catch:{ all -> 0x0155 }
            monitor-exit(r2)     // Catch:{ all -> 0x0155 }
            return r10
        L_0x013b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0155 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0155 }
            r12.<init>(r1)     // Catch:{ all -> 0x0155 }
            androidx.camera.camera2.internal.CaptureSession$State r10 = r10.mState     // Catch:{ all -> 0x0155 }
            java.lang.StringBuilder r10 = r12.append(r10)     // Catch:{ all -> 0x0155 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0155 }
            r11.<init>(r10)     // Catch:{ all -> 0x0155 }
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r11)     // Catch:{ all -> 0x0155 }
            monitor-exit(r2)     // Catch:{ all -> 0x0155 }
            return r10
        L_0x0155:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0155 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.m37lambda$open$0$androidxcameracamera2internalCaptureSession(java.util.List, androidx.camera.core.impl.SessionConfig, android.hardware.camera2.CameraDevice):com.google.common.util.concurrent.ListenableFuture");
    }

    private List<OutputConfigurationCompat> getUniqueOutputConfigurations(List<OutputConfigurationCompat> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (OutputConfigurationCompat next : list) {
            if (!arrayList.contains(next.getSurface())) {
                arrayList.add(next.getSurface());
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    private OutputConfigurationCompat getOutputConfigurationCompat(SessionConfig.OutputConfig outputConfig, Map<DeferrableSurface, Surface> map, String str) {
        Surface surface = map.get(outputConfig.getSurface());
        Preconditions.checkNotNull(surface, "Surface in OutputConfig not found in configuredSurfaceMap.");
        OutputConfigurationCompat outputConfigurationCompat = new OutputConfigurationCompat(outputConfig.getSurfaceGroupId(), surface);
        if (str != null) {
            outputConfigurationCompat.setPhysicalCameraId(str);
        } else {
            outputConfigurationCompat.setPhysicalCameraId(outputConfig.getPhysicalCameraId());
        }
        if (!outputConfig.getSharedSurfaces().isEmpty()) {
            outputConfigurationCompat.enableSurfaceSharing();
            for (DeferrableSurface deferrableSurface : outputConfig.getSharedSurfaces()) {
                Surface surface2 = map.get(deferrableSurface);
                Preconditions.checkNotNull(surface2, "Surface in OutputConfig not found in configuredSurfaceMap.");
                outputConfigurationCompat.addSurface(surface2);
            }
        }
        return outputConfigurationCompat;
    }

    public void close() {
        synchronized (this.mSessionLock) {
            int i = AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                if (this.mSessionConfig != null) {
                                    List<CaptureConfig> onDisableSession = this.mCameraEventCallbacks.createComboCallback().onDisableSession();
                                    if (!onDisableSession.isEmpty()) {
                                        try {
                                            issueCaptureRequests(setupConfiguredSurface(onDisableSession));
                                        } catch (IllegalStateException e) {
                                            Logger.e(TAG, "Unable to issue the request before close the capture session", e);
                                        }
                                    }
                                }
                            }
                        }
                        Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                        this.mSynchronizedCaptureSessionOpener.stop();
                        this.mState = State.CLOSED;
                        this.mSessionConfig = null;
                    } else {
                        Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                        this.mSynchronizedCaptureSessionOpener.stop();
                    }
                }
                this.mState = State.RELEASED;
            } else {
                throw new IllegalStateException("close() should not be possible in state: " + this.mState);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r6.mCameraEventCallbacks.createComboCallback().onDeInitSession();
        r6.mState = androidx.camera.camera2.internal.CaptureSession.State.RELEASING;
        androidx.core.util.Preconditions.checkNotNull(r6.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + r6.mState);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        if (r6.mSynchronizedCaptureSessionOpener.stop() == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        finishClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        if (r6.mReleaseFuture != null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0060, code lost:
        r6.mReleaseFuture = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(new androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        r6 = r6.mReleaseFuture;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
        r6.mState = androidx.camera.camera2.internal.CaptureSession.State.RELEASED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a8, code lost:
        return androidx.camera.core.impl.utils.futures.Futures.immediateFuture(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<java.lang.Void> release(boolean r7) {
        /*
            r6 = this;
            java.lang.String r0 = "The Opener shouldn't null in state:"
            java.lang.String r1 = "The Opener shouldn't null in state:"
            java.lang.String r2 = "release() should not be possible in state: "
            java.lang.Object r3 = r6.mSessionLock
            monitor-enter(r3)
            int[] r4 = androidx.camera.camera2.internal.CaptureSession.AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.CaptureSession$State r5 = r6.mState     // Catch:{ all -> 0x00a9 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x00a9 }
            r4 = r4[r5]     // Catch:{ all -> 0x00a9 }
            switch(r4) {
                case 1: goto L_0x008d;
                case 2: goto L_0x0088;
                case 3: goto L_0x006f;
                case 4: goto L_0x002f;
                case 5: goto L_0x0018;
                case 6: goto L_0x0018;
                case 7: goto L_0x005c;
                default: goto L_0x0016;
            }     // Catch:{ all -> 0x00a9 }
        L_0x0016:
            goto L_0x00a2
        L_0x0018:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r1 = r6.mSynchronizedCaptureSession     // Catch:{ all -> 0x00a9 }
            if (r1 == 0) goto L_0x002f
            if (r7 == 0) goto L_0x002a
            r1.abortCaptures()     // Catch:{ CameraAccessException -> 0x0022 }
            goto L_0x002a
        L_0x0022:
            r7 = move-exception
            java.lang.String r1 = "CaptureSession"
            java.lang.String r2 = "Unable to abort captures."
            androidx.camera.core.Logger.e(r1, r2, r7)     // Catch:{ all -> 0x00a9 }
        L_0x002a:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r7 = r6.mSynchronizedCaptureSession     // Catch:{ all -> 0x00a9 }
            r7.close()     // Catch:{ all -> 0x00a9 }
        L_0x002f:
            androidx.camera.camera2.impl.CameraEventCallbacks r7 = r6.mCameraEventCallbacks     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.impl.CameraEventCallbacks$ComboCameraEventCallback r7 = r7.createComboCallback()     // Catch:{ all -> 0x00a9 }
            r7.onDeInitSession()     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.CaptureSession$State r7 = androidx.camera.camera2.internal.CaptureSession.State.RELEASING     // Catch:{ all -> 0x00a9 }
            r6.mState = r7     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r7 = r6.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a9 }
            r1.<init>(r0)     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.CaptureSession$State r0 = r6.mState     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a9 }
            androidx.core.util.Preconditions.checkNotNull(r7, r0)     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r7 = r6.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x00a9 }
            boolean r7 = r7.stop()     // Catch:{ all -> 0x00a9 }
            if (r7 == 0) goto L_0x005c
            r6.finishClose()     // Catch:{ all -> 0x00a9 }
            goto L_0x00a2
        L_0x005c:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r7 = r6.mReleaseFuture     // Catch:{ all -> 0x00a9 }
            if (r7 != 0) goto L_0x006b
            androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2 r7 = new androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2     // Catch:{ all -> 0x00a9 }
            r7.<init>(r6)     // Catch:{ all -> 0x00a9 }
            com.google.common.util.concurrent.ListenableFuture r7 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r7)     // Catch:{ all -> 0x00a9 }
            r6.mReleaseFuture = r7     // Catch:{ all -> 0x00a9 }
        L_0x006b:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r6 = r6.mReleaseFuture     // Catch:{ all -> 0x00a9 }
            monitor-exit(r3)     // Catch:{ all -> 0x00a9 }
            return r6
        L_0x006f:
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r7 = r6.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a9 }
            r0.<init>(r1)     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.CaptureSession$State r1 = r6.mState     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a9 }
            androidx.core.util.Preconditions.checkNotNull(r7, r0)     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r7 = r6.mSynchronizedCaptureSessionOpener     // Catch:{ all -> 0x00a9 }
            r7.stop()     // Catch:{ all -> 0x00a9 }
        L_0x0088:
            androidx.camera.camera2.internal.CaptureSession$State r7 = androidx.camera.camera2.internal.CaptureSession.State.RELEASED     // Catch:{ all -> 0x00a9 }
            r6.mState = r7     // Catch:{ all -> 0x00a9 }
            goto L_0x00a2
        L_0x008d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a9 }
            r0.<init>(r2)     // Catch:{ all -> 0x00a9 }
            androidx.camera.camera2.internal.CaptureSession$State r6 = r6.mState     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r6 = r0.append(r6)     // Catch:{ all -> 0x00a9 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00a9 }
            r7.<init>(r6)     // Catch:{ all -> 0x00a9 }
            throw r7     // Catch:{ all -> 0x00a9 }
        L_0x00a2:
            monitor-exit(r3)     // Catch:{ all -> 0x00a9 }
            r6 = 0
            com.google.common.util.concurrent.ListenableFuture r6 = androidx.camera.core.impl.utils.futures.Futures.immediateFuture(r6)
            return r6
        L_0x00a9:
            r6 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00a9 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.release(boolean):com.google.common.util.concurrent.ListenableFuture");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$1$androidx-camera-camera2-internal-CaptureSession  reason: not valid java name */
    public /* synthetic */ Object m38lambda$release$1$androidxcameracamera2internalCaptureSession(CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.mSessionLock) {
            Preconditions.checkState(this.mReleaseCompleter == null, "Release completer expected to be null");
            this.mReleaseCompleter = completer;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    public void issueCaptureRequests(List<CaptureConfig> list) {
        synchronized (this.mSessionLock) {
            switch (AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()]) {
                case 1:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.mState);
                case 2:
                case 3:
                case 4:
                    this.mCaptureConfigs.addAll(list);
                    break;
                case 5:
                    this.mCaptureConfigs.addAll(list);
                    issuePendingCaptureRequest();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    public List<CaptureConfig> getCaptureConfigs() {
        List<CaptureConfig> unmodifiableList;
        synchronized (this.mSessionLock) {
            unmodifiableList = Collections.unmodifiableList(this.mCaptureConfigs);
        }
        return unmodifiableList;
    }

    /* access modifiers changed from: package-private */
    public State getState() {
        State state;
        synchronized (this.mSessionLock) {
            state = this.mState;
        }
        return state;
    }

    /* access modifiers changed from: package-private */
    public void finishClose() {
        if (this.mState == State.RELEASED) {
            Logger.d(TAG, "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.mState = State.RELEASED;
        this.mSynchronizedCaptureSession = null;
        CallbackToFutureAdapter.Completer<Void> completer = this.mReleaseCompleter;
        if (completer != null) {
            completer.set(null);
            this.mReleaseCompleter = null;
        }
    }

    /* access modifiers changed from: package-private */
    public int issueRepeatingCaptureRequests(SessionConfig sessionConfig) {
        synchronized (this.mSessionLock) {
            if (sessionConfig == null) {
                Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no configuration case.");
                return -1;
            }
            CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
            if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
                Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no surface.");
                try {
                    this.mSynchronizedCaptureSession.stopRepeating();
                } catch (CameraAccessException e) {
                    Logger.e(TAG, "Unable to access camera: " + e.getMessage());
                    Thread.dumpStack();
                }
            } else {
                try {
                    Logger.d(TAG, "Issuing request for session.");
                    CaptureConfig.Builder from = CaptureConfig.Builder.from(repeatingCaptureConfig);
                    Config mergeOptions = mergeOptions(this.mCameraEventCallbacks.createComboCallback().onRepeating());
                    this.mCameraEventOnRepeatingOptions = mergeOptions;
                    from.addImplementationOptions(mergeOptions);
                    CaptureRequest build = Camera2CaptureRequestBuilder.build(from.build(), this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap);
                    if (build == null) {
                        Logger.d(TAG, "Skipping issuing empty request for session.");
                        return -1;
                    }
                    int singleRepeatingRequest = this.mSynchronizedCaptureSession.setSingleRepeatingRequest(build, createCamera2CaptureCallback(repeatingCaptureConfig.getCameraCaptureCallbacks(), this.mCaptureCallback));
                    return singleRepeatingRequest;
                } catch (CameraAccessException e2) {
                    Logger.e(TAG, "Unable to access camera: " + e2.getMessage());
                    Thread.dumpStack();
                    return -1;
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void issuePendingCaptureRequest() {
        if (!this.mCaptureConfigs.isEmpty()) {
            try {
                issueBurstCaptureRequest(this.mCaptureConfigs);
            } finally {
                this.mCaptureConfigs.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int issueBurstCaptureRequest(List<CaptureConfig> list) {
        boolean z;
        synchronized (this.mSessionLock) {
            if (list.isEmpty()) {
                return -1;
            }
            try {
                CameraBurstCaptureCallback cameraBurstCaptureCallback = new CameraBurstCaptureCallback();
                ArrayList arrayList = new ArrayList();
                Logger.d(TAG, "Issuing capture request.");
                boolean z2 = false;
                for (CaptureConfig next : list) {
                    if (next.getSurfaces().isEmpty()) {
                        Logger.d(TAG, "Skipping issuing empty capture request.");
                    } else {
                        Iterator<DeferrableSurface> it = next.getSurfaces().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = true;
                                break;
                            }
                            DeferrableSurface next2 = it.next();
                            if (!this.mConfiguredSurfaceMap.containsKey(next2)) {
                                Logger.d(TAG, "Skipping capture request with invalid surface: " + next2);
                                z = false;
                                break;
                            }
                        }
                        if (z) {
                            if (next.getTemplateType() == 2) {
                                z2 = true;
                            }
                            CaptureConfig.Builder from = CaptureConfig.Builder.from(next);
                            if (next.getTemplateType() == 5 && next.getCameraCaptureResult() != null) {
                                from.setCameraCaptureResult(next.getCameraCaptureResult());
                            }
                            SessionConfig sessionConfig = this.mSessionConfig;
                            if (sessionConfig != null) {
                                from.addImplementationOptions(sessionConfig.getRepeatingCaptureConfig().getImplementationOptions());
                            }
                            from.addImplementationOptions(this.mCameraEventOnRepeatingOptions);
                            from.addImplementationOptions(next.getImplementationOptions());
                            CaptureRequest build = Camera2CaptureRequestBuilder.build(from.build(), this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap);
                            if (build == null) {
                                Logger.d(TAG, "Skipping issuing request without surface.");
                                return -1;
                            }
                            ArrayList arrayList2 = new ArrayList();
                            for (CameraCaptureCallback captureCallback : next.getCameraCaptureCallbacks()) {
                                CaptureCallbackConverter.toCaptureCallback(captureCallback, arrayList2);
                            }
                            cameraBurstCaptureCallback.addCamera2Callbacks(build, arrayList2);
                            arrayList.add(build);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    if (this.mStillCaptureFlow.shouldStopRepeatingBeforeCapture(arrayList, z2)) {
                        this.mSynchronizedCaptureSession.stopRepeating();
                        cameraBurstCaptureCallback.setCaptureSequenceCallback(new CaptureSession$$ExternalSyntheticLambda0(this));
                    }
                    if (this.mTorchStateReset.isTorchResetRequired(arrayList, z2)) {
                        cameraBurstCaptureCallback.addCamera2Callbacks((CaptureRequest) arrayList.get(arrayList.size() - 1), Collections.singletonList(new CameraCaptureSession.CaptureCallback() {
                            public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                                synchronized (CaptureSession.this.mSessionLock) {
                                    if (CaptureSession.this.mSessionConfig != null) {
                                        CaptureConfig repeatingCaptureConfig = CaptureSession.this.mSessionConfig.getRepeatingCaptureConfig();
                                        Logger.d(CaptureSession.TAG, "Submit FLASH_MODE_OFF request");
                                        CaptureSession captureSession = CaptureSession.this;
                                        captureSession.issueCaptureRequests(Collections.singletonList(captureSession.mTorchStateReset.createTorchResetRequest(repeatingCaptureConfig)));
                                    }
                                }
                            }
                        }));
                    }
                    int captureBurstRequests = this.mSynchronizedCaptureSession.captureBurstRequests(arrayList, cameraBurstCaptureCallback);
                    return captureBurstRequests;
                }
                Logger.d(TAG, "Skipping issuing burst request due to no valid request elements");
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to access camera: " + e.getMessage());
                Thread.dumpStack();
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$issueBurstCaptureRequest$2$androidx-camera-camera2-internal-CaptureSession  reason: not valid java name */
    public /* synthetic */ void m36lambda$issueBurstCaptureRequest$2$androidxcameracamera2internalCaptureSession(CameraCaptureSession cameraCaptureSession, int i, boolean z) {
        synchronized (this.mSessionLock) {
            if (this.mState == State.OPENED) {
                issueRepeatingCaptureRequests(this.mSessionConfig);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void abortCaptures() {
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                Logger.e(TAG, "Unable to abort captures. Incorrect state:" + this.mState);
                return;
            }
            try {
                this.mSynchronizedCaptureSession.abortCaptures();
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to abort captures.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void stopRepeating() {
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                Logger.e(TAG, "Unable to stop repeating. Incorrect state:" + this.mState);
                return;
            }
            try {
                this.mSynchronizedCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to stop repeating.", e);
            }
        }
    }

    public void cancelIssuedCaptureRequests() {
        ArrayList<CaptureConfig> arrayList;
        synchronized (this.mSessionLock) {
            if (!this.mCaptureConfigs.isEmpty()) {
                arrayList = new ArrayList<>(this.mCaptureConfigs);
                this.mCaptureConfigs.clear();
            } else {
                arrayList = null;
            }
        }
        if (arrayList != null) {
            for (CaptureConfig cameraCaptureCallbacks : arrayList) {
                for (CameraCaptureCallback onCaptureCancelled : cameraCaptureCallbacks.getCameraCaptureCallbacks()) {
                    onCaptureCancelled.onCaptureCancelled();
                }
            }
        }
    }

    private CameraCaptureSession.CaptureCallback createCamera2CaptureCallback(List<CameraCaptureCallback> list, CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        ArrayList arrayList = new ArrayList(list.size() + captureCallbackArr.length);
        for (CameraCaptureCallback captureCallback : list) {
            arrayList.add(CaptureCallbackConverter.toCaptureCallback(captureCallback));
        }
        Collections.addAll(arrayList, captureCallbackArr);
        return Camera2CaptureCallbacks.createComboCallback((List<CameraCaptureSession.CaptureCallback>) arrayList);
    }

    private static Config mergeOptions(List<CaptureConfig> list) {
        MutableOptionsBundle create = MutableOptionsBundle.create();
        for (CaptureConfig implementationOptions : list) {
            Config implementationOptions2 = implementationOptions.getImplementationOptions();
            for (Config.Option next : implementationOptions2.listOptions()) {
                Object retrieveOption = implementationOptions2.retrieveOption(next, null);
                if (create.containsOption(next)) {
                    Object retrieveOption2 = create.retrieveOption(next, null);
                    if (!Objects.equals(retrieveOption2, retrieveOption)) {
                        Logger.d(TAG, "Detect conflicting option " + next.getId() + " : " + retrieveOption + " != " + retrieveOption2);
                    }
                } else {
                    create.insertOption(next, retrieveOption);
                }
            }
        }
        return create;
    }

    final class StateCallback extends SynchronizedCaptureSession.StateCallback {
        StateCallback() {
        }

        public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                switch (AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + CaptureSession.this.mState);
                    case 4:
                        CaptureSession.this.mState = State.OPENED;
                        CaptureSession.this.mSynchronizedCaptureSession = synchronizedCaptureSession;
                        if (CaptureSession.this.mSessionConfig != null) {
                            List<CaptureConfig> onEnableSession = CaptureSession.this.mCameraEventCallbacks.createComboCallback().onEnableSession();
                            if (!onEnableSession.isEmpty()) {
                                CaptureSession captureSession = CaptureSession.this;
                                captureSession.issueBurstCaptureRequest(captureSession.setupConfiguredSurface(onEnableSession));
                            }
                        }
                        Logger.d(CaptureSession.TAG, "Attempting to send capture request onConfigured");
                        CaptureSession captureSession2 = CaptureSession.this;
                        captureSession2.issueRepeatingCaptureRequests(captureSession2.mSessionConfig);
                        CaptureSession.this.issuePendingCaptureRequest();
                        break;
                    case 6:
                        CaptureSession.this.mSynchronizedCaptureSession = synchronizedCaptureSession;
                        break;
                    case 7:
                        synchronizedCaptureSession.close();
                        break;
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onConfigured() mState=" + CaptureSession.this.mState);
            }
        }

        public void onReady(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                if (AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()] != 1) {
                    Logger.d(CaptureSession.TAG, "CameraCaptureSession.onReady() " + CaptureSession.this.mState);
                } else {
                    throw new IllegalStateException("onReady() should not be possible in state: " + CaptureSession.this.mState);
                }
            }
        }

        public void onSessionFinished(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                if (CaptureSession.this.mState != State.UNINITIALIZED) {
                    Logger.d(CaptureSession.TAG, "onSessionFinished()");
                    CaptureSession.this.finishClose();
                } else {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + CaptureSession.this.mState);
                }
            }
        }

        public void onConfigureFailed(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                switch (AnonymousClass4.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + CaptureSession.this.mState);
                    case 4:
                    case 6:
                    case 7:
                        CaptureSession.this.finishClose();
                        break;
                    case 8:
                        Logger.d(CaptureSession.TAG, "ConfigureFailed callback after change to RELEASED state");
                        break;
                }
                Logger.e(CaptureSession.TAG, "CameraCaptureSession.onConfigureFailed() " + CaptureSession.this.mState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<CaptureConfig> setupConfiguredSurface(List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig from : list) {
            CaptureConfig.Builder from2 = CaptureConfig.Builder.from(from);
            from2.setTemplateType(1);
            for (DeferrableSurface addSurface : this.mSessionConfig.getRepeatingCaptureConfig().getSurfaces()) {
                from2.addSurface(addSurface);
            }
            arrayList.add(from2.build());
        }
        return arrayList;
    }
}
