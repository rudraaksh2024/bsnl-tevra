package androidx.camera.core.processing;

import androidx.camera.core.ImageProcessor;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InternalImageProcessor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ InternalImageProcessor f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ ImageProcessor.Request f$2;

    public /* synthetic */ InternalImageProcessor$$ExternalSyntheticLambda0(InternalImageProcessor internalImageProcessor, CallbackToFutureAdapter.Completer completer, ImageProcessor.Request request) {
        this.f$0 = internalImageProcessor;
        this.f$1 = completer;
        this.f$2 = request;
    }

    public final void run() {
        this.f$0.m174lambda$safeProcess$0$androidxcameracoreprocessingInternalImageProcessor(this.f$1, this.f$2);
    }
}
