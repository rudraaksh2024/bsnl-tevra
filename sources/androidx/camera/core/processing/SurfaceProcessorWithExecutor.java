package androidx.camera.core.processing;

import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceProcessor;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

public class SurfaceProcessorWithExecutor implements SurfaceProcessorInternal {
    private final Executor mExecutor;
    private final SurfaceProcessor mSurfaceProcessor;

    public void release() {
    }

    public SurfaceProcessorWithExecutor(SurfaceProcessor surfaceProcessor, Executor executor) {
        Preconditions.checkState(!(surfaceProcessor instanceof SurfaceProcessorInternal), "SurfaceProcessorInternal should always be thread safe. Do not wrap.");
        this.mSurfaceProcessor = surfaceProcessor;
        this.mExecutor = executor;
    }

    public SurfaceProcessor getProcessor() {
        return this.mSurfaceProcessor;
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$0$androidx-camera-core-processing-SurfaceProcessorWithExecutor  reason: not valid java name */
    public /* synthetic */ void m182lambda$onInputSurface$0$androidxcameracoreprocessingSurfaceProcessorWithExecutor(SurfaceRequest surfaceRequest) {
        this.mSurfaceProcessor.onInputSurface(surfaceRequest);
    }

    public void onInputSurface(SurfaceRequest surfaceRequest) {
        this.mExecutor.execute(new SurfaceProcessorWithExecutor$$ExternalSyntheticLambda0(this, surfaceRequest));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$1$androidx-camera-core-processing-SurfaceProcessorWithExecutor  reason: not valid java name */
    public /* synthetic */ void m183lambda$onOutputSurface$1$androidxcameracoreprocessingSurfaceProcessorWithExecutor(SurfaceOutput surfaceOutput) {
        this.mSurfaceProcessor.onOutputSurface(surfaceOutput);
    }

    public void onOutputSurface(SurfaceOutput surfaceOutput) {
        this.mExecutor.execute(new SurfaceProcessorWithExecutor$$ExternalSyntheticLambda1(this, surfaceOutput));
    }
}
