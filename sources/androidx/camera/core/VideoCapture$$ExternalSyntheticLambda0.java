package androidx.camera.core;

import androidx.camera.core.VideoCapture;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoCapture f$0;
    public final /* synthetic */ VideoCapture.OutputFileOptions f$1;
    public final /* synthetic */ Executor f$2;
    public final /* synthetic */ VideoCapture.OnVideoSavedCallback f$3;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda0(VideoCapture videoCapture, VideoCapture.OutputFileOptions outputFileOptions, Executor executor, VideoCapture.OnVideoSavedCallback onVideoSavedCallback) {
        this.f$0 = videoCapture;
        this.f$1 = outputFileOptions;
        this.f$2 = executor;
        this.f$3 = onVideoSavedCallback;
    }

    public final void run() {
        this.f$0.m140lambda$startRecording$0$androidxcameracoreVideoCapture(this.f$1, this.f$2, this.f$3);
    }
}
