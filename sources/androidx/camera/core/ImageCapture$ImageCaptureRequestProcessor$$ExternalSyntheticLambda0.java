package androidx.camera.core;

import androidx.camera.core.ImageCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$ImageCaptureRequestProcessor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageCapture.ImageCaptureRequestProcessor f$0;

    public /* synthetic */ ImageCapture$ImageCaptureRequestProcessor$$ExternalSyntheticLambda0(ImageCapture.ImageCaptureRequestProcessor imageCaptureRequestProcessor) {
        this.f$0 = imageCaptureRequestProcessor;
    }

    public final void run() {
        this.f$0.processNextRequest();
    }
}
