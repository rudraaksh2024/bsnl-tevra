package androidx.camera.core;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ SafeCloseImageReaderProxy f$0;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda10(SafeCloseImageReaderProxy safeCloseImageReaderProxy) {
        this.f$0 = safeCloseImageReaderProxy;
    }

    public final void run() {
        this.f$0.safeClose();
    }
}
