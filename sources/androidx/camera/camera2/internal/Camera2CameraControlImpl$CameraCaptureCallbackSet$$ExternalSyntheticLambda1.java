package androidx.camera.camera2.internal;

import androidx.camera.core.impl.CameraCaptureCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;

    public /* synthetic */ Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda1(CameraCaptureCallback cameraCaptureCallback) {
        this.f$0 = cameraCaptureCallback;
    }

    public final void run() {
        this.f$0.onCaptureCancelled();
    }
}
