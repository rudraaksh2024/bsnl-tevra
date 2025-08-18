package androidx.camera.core;

import androidx.camera.core.VideoCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$VideoSavedListenerWrapper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoCapture.VideoSavedListenerWrapper f$0;
    public final /* synthetic */ VideoCapture.OutputFileResults f$1;

    public /* synthetic */ VideoCapture$VideoSavedListenerWrapper$$ExternalSyntheticLambda0(VideoCapture.VideoSavedListenerWrapper videoSavedListenerWrapper, VideoCapture.OutputFileResults outputFileResults) {
        this.f$0 = videoSavedListenerWrapper;
        this.f$1 = outputFileResults;
    }

    public final void run() {
        this.f$0.m146lambda$onVideoSaved$0$androidxcameracoreVideoCapture$VideoSavedListenerWrapper(this.f$1);
    }
}
