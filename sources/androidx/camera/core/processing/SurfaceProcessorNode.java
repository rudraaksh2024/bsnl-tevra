package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Preconditions;
import java.util.Collections;

public class SurfaceProcessorNode implements Node<SurfaceEdge, SurfaceEdge> {
    final CameraInternal mCameraInternal;
    private final SurfaceOutput.GlTransformOptions mGlTransformOptions;
    private SurfaceEdge mInputEdge;
    private SurfaceEdge mOutputEdge;
    final SurfaceProcessorInternal mSurfaceProcessor;

    public SurfaceProcessorNode(CameraInternal cameraInternal, SurfaceOutput.GlTransformOptions glTransformOptions, SurfaceProcessorInternal surfaceProcessorInternal) {
        this.mCameraInternal = cameraInternal;
        this.mGlTransformOptions = glTransformOptions;
        this.mSurfaceProcessor = surfaceProcessorInternal;
    }

    public SurfaceEdge transform(SurfaceEdge surfaceEdge) {
        Threads.checkMainThread();
        boolean z = true;
        if (surfaceEdge.getSurfaces().size() != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Multiple input stream not supported yet.");
        this.mInputEdge = surfaceEdge;
        SettableSurface settableSurface = surfaceEdge.getSurfaces().get(0);
        SettableSurface createOutputSurface = createOutputSurface(settableSurface);
        sendSurfacesToProcessorWhenReady(settableSurface, createOutputSurface);
        SurfaceEdge create = SurfaceEdge.create(Collections.singletonList(createOutputSurface));
        this.mOutputEdge = create;
        return create;
    }

    /* renamed from: androidx.camera.core.processing.SurfaceProcessorNode$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                androidx.camera.core.SurfaceOutput$GlTransformOptions[] r0 = androidx.camera.core.SurfaceOutput.GlTransformOptions.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions = r0
                androidx.camera.core.SurfaceOutput$GlTransformOptions r1 = androidx.camera.core.SurfaceOutput.GlTransformOptions.APPLY_CROP_ROTATE_AND_MIRRORING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.core.SurfaceOutput$GlTransformOptions r1 = androidx.camera.core.SurfaceOutput.GlTransformOptions.USE_SURFACE_TEXTURE_TRANSFORM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.SurfaceProcessorNode.AnonymousClass2.<clinit>():void");
        }
    }

    private SettableSurface createOutputSurface(SettableSurface settableSurface) {
        Size size;
        int i = AnonymousClass2.$SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions[this.mGlTransformOptions.ordinal()];
        if (i == 1) {
            Size size2 = settableSurface.getSize();
            Rect cropRect = settableSurface.getCropRect();
            int rotationDegrees = settableSurface.getRotationDegrees();
            boolean mirroring = settableSurface.getMirroring();
            if (TransformUtils.is90or270(rotationDegrees)) {
                size = new Size(cropRect.height(), cropRect.width());
            } else {
                size = TransformUtils.rectToSize(cropRect);
            }
            Size size3 = size;
            Matrix matrix = new Matrix(settableSurface.getSensorToBufferTransform());
            matrix.postConcat(TransformUtils.getRectToRect(TransformUtils.sizeToRectF(size2), new RectF(cropRect), rotationDegrees, mirroring));
            return new SettableSurface(settableSurface.getTargets(), size3, settableSurface.getFormat(), matrix, false, TransformUtils.sizeToRect(size3), 0, false);
        } else if (i == 2) {
            return new SettableSurface(settableSurface.getTargets(), settableSurface.getSize(), settableSurface.getFormat(), settableSurface.getSensorToBufferTransform(), false, settableSurface.getCropRect(), settableSurface.getRotationDegrees(), settableSurface.getMirroring());
        } else {
            throw new AssertionError("Unknown GlTransformOptions: " + this.mGlTransformOptions);
        }
    }

    private void sendSurfacesToProcessorWhenReady(final SettableSurface settableSurface, final SettableSurface settableSurface2) {
        final SurfaceRequest createSurfaceRequest = settableSurface.createSurfaceRequest(this.mCameraInternal);
        Futures.addCallback(settableSurface2.createSurfaceOutputFuture(this.mGlTransformOptions, settableSurface.getSize(), settableSurface.getCropRect(), settableSurface.getRotationDegrees(), settableSurface.getMirroring()), new FutureCallback<SurfaceOutput>() {
            public void onSuccess(SurfaceOutput surfaceOutput) {
                Preconditions.checkNotNull(surfaceOutput);
                SurfaceProcessorNode.this.mSurfaceProcessor.onOutputSurface(surfaceOutput);
                SurfaceProcessorNode.this.mSurfaceProcessor.onInputSurface(createSurfaceRequest);
                SurfaceProcessorNode.this.setupSurfaceUpdatePipeline(settableSurface, createSurfaceRequest, settableSurface2, surfaceOutput);
            }

            public void onFailure(Throwable th) {
                createSurfaceRequest.willNotProvideSurface();
            }
        }, CameraXExecutors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    public void setupSurfaceUpdatePipeline(SettableSurface settableSurface, SurfaceRequest surfaceRequest, SettableSurface settableSurface2, SurfaceOutput surfaceOutput) {
        surfaceRequest.setTransformationInfoListener(CameraXExecutors.mainThreadExecutor(), new SurfaceProcessorNode$$ExternalSyntheticLambda0(surfaceOutput, settableSurface, settableSurface2));
    }

    static /* synthetic */ void lambda$setupSurfaceUpdatePipeline$0(SurfaceOutput surfaceOutput, SettableSurface settableSurface, SettableSurface settableSurface2, SurfaceRequest.TransformationInfo transformationInfo) {
        int rotationDegrees = transformationInfo.getRotationDegrees() - surfaceOutput.getRotationDegrees();
        if (settableSurface.getMirroring()) {
            rotationDegrees = -rotationDegrees;
        }
        settableSurface2.setRotationDegrees(TransformUtils.within360(rotationDegrees));
    }

    public void release() {
        this.mSurfaceProcessor.release();
        CameraXExecutors.mainThreadExecutor().execute(new SurfaceProcessorNode$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$1$androidx-camera-core-processing-SurfaceProcessorNode  reason: not valid java name */
    public /* synthetic */ void m181lambda$release$1$androidxcameracoreprocessingSurfaceProcessorNode() {
        SurfaceEdge surfaceEdge = this.mOutputEdge;
        if (surfaceEdge != null) {
            for (SettableSurface close : surfaceEdge.getSurfaces()) {
                close.close();
            }
        }
    }
}
