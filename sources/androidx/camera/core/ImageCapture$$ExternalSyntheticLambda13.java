package androidx.camera.core;

import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda13 implements SessionConfig.ErrorListener {
    public final /* synthetic */ ImageCapture f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda13(ImageCapture imageCapture, String str) {
        this.f$0 = imageCapture;
        this.f$1 = str;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m118lambda$createPipelineWithNode$11$androidxcameracoreImageCapture(this.f$1, sessionConfig, sessionError);
    }
}
