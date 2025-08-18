package androidx.camera.core.processing;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceTexture f$1;
    public final /* synthetic */ Surface f$2;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda6(DefaultSurfaceProcessor defaultSurfaceProcessor, SurfaceTexture surfaceTexture, Surface surface) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = surfaceTexture;
        this.f$2 = surface;
    }

    public final void accept(Object obj) {
        this.f$0.m170lambda$onInputSurface$0$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, this.f$2, (SurfaceRequest.Result) obj);
    }
}
