package androidx.camera.core;

import androidx.camera.core.VideoCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$VideoSavedListenerWrapper$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ VideoCapture.VideoSavedListenerWrapper f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Throwable f$3;

    public /* synthetic */ VideoCapture$VideoSavedListenerWrapper$$ExternalSyntheticLambda1(VideoCapture.VideoSavedListenerWrapper videoSavedListenerWrapper, int i, String str, Throwable th) {
        this.f$0 = videoSavedListenerWrapper;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = th;
    }

    public final void run() {
        this.f$0.m145lambda$onError$1$androidxcameracoreVideoCapture$VideoSavedListenerWrapper(this.f$1, this.f$2, this.f$3);
    }
}
