package androidx.camera.core.processing;

import androidx.camera.core.SurfaceOutput;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceOutput f$1;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda5(DefaultSurfaceProcessor defaultSurfaceProcessor, SurfaceOutput surfaceOutput) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = surfaceOutput;
    }

    public final void accept(Object obj) {
        this.f$0.m172lambda$onOutputSurface$2$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, (SurfaceOutput.Event) obj);
    }
}
