package androidx.camera.core.processing;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda3 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ ShaderProvider f$1;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda3(DefaultSurfaceProcessor defaultSurfaceProcessor, ShaderProvider shaderProvider) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = shaderProvider;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m169lambda$initGlRenderer$5$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, completer);
    }
}
