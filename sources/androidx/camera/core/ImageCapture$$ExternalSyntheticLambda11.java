package androidx.camera.core;

import android.util.Size;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda11 implements SessionConfig.ErrorListener {
    public final /* synthetic */ ImageCapture f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ImageCaptureConfig f$2;
    public final /* synthetic */ Size f$3;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda11(ImageCapture imageCapture, String str, ImageCaptureConfig imageCaptureConfig, Size size) {
        this.f$0 = imageCapture;
        this.f$1 = str;
        this.f$2 = imageCaptureConfig;
        this.f$3 = size;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m117lambda$createPipeline$2$androidxcameracoreImageCapture(this.f$1, this.f$2, this.f$3, sessionConfig, sessionError);
    }
}
