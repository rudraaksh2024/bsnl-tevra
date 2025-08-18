package androidx.camera.camera2.interop;

import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public final class Camera2CameraControl {
    public static final String TAG_KEY = "Camera2CameraControl";
    private Camera2ImplConfig.Builder mBuilder = new Camera2ImplConfig.Builder();
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private final Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener = new Camera2CameraControl$$ExternalSyntheticLambda4(this);
    CallbackToFutureAdapter.Completer<Void> mCompleter;
    final Executor mExecutor;
    private boolean mIsActive = false;
    final Object mLock = new Object();
    private boolean mPendingUpdate = false;

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* renamed from: lambda$new$0$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean m99lambda$new$0$androidxcameracamera2interopCamera2CameraControl(android.hardware.camera2.TotalCaptureResult r3) {
        /*
            r2 = this;
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r2.mCompleter
            r1 = 0
            if (r0 == 0) goto L_0x0032
            android.hardware.camera2.CaptureRequest r3 = r3.getRequest()
            java.lang.Object r3 = r3.getTag()
            boolean r0 = r3 instanceof androidx.camera.core.impl.TagBundle
            if (r0 == 0) goto L_0x0032
            androidx.camera.core.impl.TagBundle r3 = (androidx.camera.core.impl.TagBundle) r3
            java.lang.String r0 = "Camera2CameraControl"
            java.lang.Object r3 = r3.getTag(r0)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 == 0) goto L_0x0032
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r2.mCompleter
            int r0 = r0.hashCode()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r3 = r2.mCompleter
            r2.mCompleter = r1
            goto L_0x0033
        L_0x0032:
            r3 = r1
        L_0x0033:
            if (r3 == 0) goto L_0x0038
            r3.set(r1)
        L_0x0038:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.interop.Camera2CameraControl.m99lambda$new$0$androidxcameracamera2interopCamera2CameraControl(android.hardware.camera2.TotalCaptureResult):boolean");
    }

    public Camera2CameraControl(Camera2CameraControlImpl camera2CameraControlImpl, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
    }

    public Camera2CameraControlImpl.CaptureResultListener getCaptureRequestListener() {
        return this.mCaptureResultListener;
    }

    public static Camera2CameraControl from(CameraControl cameraControl) {
        Preconditions.checkArgument(cameraControl instanceof Camera2CameraControlImpl, "CameraControl doesn't contain Camera2 implementation.");
        return ((Camera2CameraControlImpl) cameraControl).getCamera2CameraControl();
    }

    public ListenableFuture<Void> setCaptureRequestOptions(CaptureRequestOptions captureRequestOptions) {
        clearCaptureRequestOptionsInternal();
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda5(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCaptureRequestOptions$2$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m102lambda$setCaptureRequestOptions$2$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda7(this, completer));
        return "setCaptureRequestOptions";
    }

    public ListenableFuture<Void> addCaptureRequestOptions(CaptureRequestOptions captureRequestOptions) {
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda3(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addCaptureRequestOptions$4$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m96lambda$addCaptureRequestOptions$4$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda0(this, completer));
        return "addCaptureRequestOptions";
    }

    public CaptureRequestOptions getCaptureRequestOptions() {
        CaptureRequestOptions build;
        synchronized (this.mLock) {
            build = CaptureRequestOptions.Builder.from(this.mBuilder.build()).build();
        }
        return build;
    }

    public ListenableFuture<Void> clearCaptureRequestOptions() {
        clearCaptureRequestOptionsInternal();
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda2(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearCaptureRequestOptions$6$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m98lambda$clearCaptureRequestOptions$6$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda6(this, completer));
        return "clearCaptureRequestOptions";
    }

    public Camera2ImplConfig getCamera2ImplConfig() {
        Camera2ImplConfig build;
        synchronized (this.mLock) {
            if (this.mCompleter != null) {
                this.mBuilder.getMutableConfig().insertOption(Camera2ImplConfig.CAPTURE_REQUEST_TAG_OPTION, Integer.valueOf(this.mCompleter.hashCode()));
            }
            build = this.mBuilder.build();
        }
        return build;
    }

    private void addCaptureRequestOptionsInternal(CaptureRequestOptions captureRequestOptions) {
        synchronized (this.mLock) {
            for (Config.Option next : captureRequestOptions.listOptions()) {
                this.mBuilder.getMutableConfig().insertOption(next, captureRequestOptions.retrieveOption(next));
            }
        }
    }

    private void clearCaptureRequestOptionsInternal() {
        synchronized (this.mLock) {
            this.mBuilder = new Camera2ImplConfig.Builder();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateConfig */
    public void m101lambda$setCaptureRequestOptions$1$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer<Void> completer) {
        this.mPendingUpdate = true;
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mCompleter;
        if (completer2 == null) {
            completer2 = null;
        }
        this.mCompleter = completer;
        if (this.mIsActive) {
            updateSession();
        }
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("Camera2CameraControl was updated with new options."));
        }
    }

    private void updateSession() {
        this.mCamera2CameraControlImpl.updateSessionConfig();
        this.mPendingUpdate = false;
    }

    public void setActive(boolean z) {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda1(this, z));
    }

    /* access modifiers changed from: private */
    /* renamed from: setActiveInternal */
    public void m100lambda$setActive$7$androidxcameracamera2interopCamera2CameraControl(boolean z) {
        if (this.mIsActive != z) {
            this.mIsActive = z;
            if (!z) {
                CallbackToFutureAdapter.Completer<Void> completer = this.mCompleter;
                if (completer != null) {
                    completer.setException(new CameraControl.OperationCanceledException("The camera control has became inactive."));
                    this.mCompleter = null;
                }
            } else if (this.mPendingUpdate) {
                updateSession();
            }
        }
    }
}
