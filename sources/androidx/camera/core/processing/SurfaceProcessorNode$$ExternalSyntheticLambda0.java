package androidx.camera.core.processing;

import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceProcessorNode$$ExternalSyntheticLambda0 implements SurfaceRequest.TransformationInfoListener {
    public final /* synthetic */ SurfaceOutput f$0;
    public final /* synthetic */ SettableSurface f$1;
    public final /* synthetic */ SettableSurface f$2;

    public /* synthetic */ SurfaceProcessorNode$$ExternalSyntheticLambda0(SurfaceOutput surfaceOutput, SettableSurface settableSurface, SettableSurface settableSurface2) {
        this.f$0 = surfaceOutput;
        this.f$1 = settableSurface;
        this.f$2 = settableSurface2;
    }

    public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo transformationInfo) {
        SurfaceProcessorNode.lambda$setupSurfaceUpdatePipeline$0(this.f$0, this.f$1, this.f$2, transformationInfo);
    }
}
