package androidx.camera.core;

import android.view.Surface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ Surface f$0;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda8(Surface surface) {
        this.f$0 = surface;
    }

    public final void run() {
        this.f$0.release();
    }
}
