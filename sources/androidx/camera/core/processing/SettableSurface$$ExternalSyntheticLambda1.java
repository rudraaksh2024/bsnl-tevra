package androidx.camera.core.processing;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SettableSurface$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SettableSurface f$0;

    public /* synthetic */ SettableSurface$$ExternalSyntheticLambda1(SettableSurface settableSurface) {
        this.f$0 = settableSurface;
    }

    public final void run() {
        this.f$0.decrementUseCount();
    }
}
