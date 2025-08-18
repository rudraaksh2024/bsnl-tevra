package androidx.camera.core.processing;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ ShaderProvider f$1;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$2;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda0(DefaultSurfaceProcessor defaultSurfaceProcessor, ShaderProvider shaderProvider, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = shaderProvider;
        this.f$2 = completer;
    }

    public final void run() {
        this.f$0.m168lambda$initGlRenderer$4$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, this.f$2);
    }
}
