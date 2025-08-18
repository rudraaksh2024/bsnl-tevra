package androidx.camera.core;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.internal.YuvToJpegProcessor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda9 implements ImageCapture.ImageCaptureRequestProcessor.RequestProcessCallback {
    public final /* synthetic */ YuvToJpegProcessor f$0;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda9(YuvToJpegProcessor yuvToJpegProcessor) {
        this.f$0 = yuvToJpegProcessor;
    }

    public final void onPreProcessRequest(ImageCapture.ImageCaptureRequest imageCaptureRequest) {
        ImageCapture.lambda$createPipeline$1(this.f$0, imageCaptureRequest);
    }
}
