package androidx.camera.core.imagecapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TakePictureManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ TakePictureManager f$0;

    public /* synthetic */ TakePictureManager$$ExternalSyntheticLambda2(TakePictureManager takePictureManager) {
        this.f$0 = takePictureManager;
    }

    public final void run() {
        this.f$0.issueNextRequest();
    }
}
