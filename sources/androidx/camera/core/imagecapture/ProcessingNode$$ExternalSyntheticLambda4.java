package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessingNode$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ProcessingRequest f$0;
    public final /* synthetic */ ImageCapture.OutputFileResults f$1;

    public /* synthetic */ ProcessingNode$$ExternalSyntheticLambda4(ProcessingRequest processingRequest, ImageCapture.OutputFileResults outputFileResults) {
        this.f$0 = processingRequest;
        this.f$1 = outputFileResults;
    }

    public final void run() {
        this.f$0.onFinalResult(this.f$1);
    }
}
