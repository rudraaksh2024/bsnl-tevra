package androidx.camera.core.processing;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultSurfaceProcessor implements SurfaceProcessorInternal, SurfaceTexture.OnFrameAvailableListener {
    private final Executor mGlExecutor;
    final Handler mGlHandler;
    private final OpenGlRenderer mGlRenderer;
    final HandlerThread mGlThread;
    private int mInputSurfaceCount;
    private final AtomicBoolean mIsReleased;
    final Map<SurfaceOutput, Surface> mOutputSurfaces;
    private final float[] mSurfaceOutputMatrix;
    private final float[] mTextureMatrix;

    public DefaultSurfaceProcessor() {
        this(ShaderProvider.DEFAULT);
    }

    public DefaultSurfaceProcessor(ShaderProvider shaderProvider) {
        this.mIsReleased = new AtomicBoolean(false);
        this.mTextureMatrix = new float[16];
        this.mSurfaceOutputMatrix = new float[16];
        this.mOutputSurfaces = new LinkedHashMap();
        this.mInputSurfaceCount = 0;
        HandlerThread handlerThread = new HandlerThread("GL Thread");
        this.mGlThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.mGlHandler = handler;
        this.mGlExecutor = CameraXExecutors.newHandlerExecutor(handler);
        this.mGlRenderer = new OpenGlRenderer();
        try {
            initGlRenderer(shaderProvider);
        } catch (RuntimeException e) {
            release();
            throw e;
        }
    }

    public void onInputSurface(SurfaceRequest surfaceRequest) {
        if (this.mIsReleased.get()) {
            surfaceRequest.willNotProvideSurface();
        } else {
            this.mGlExecutor.execute(new DefaultSurfaceProcessor$$ExternalSyntheticLambda2(this, surfaceRequest));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$1$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m171lambda$onInputSurface$1$androidxcameracoreprocessingDefaultSurfaceProcessor(SurfaceRequest surfaceRequest) {
        this.mInputSurfaceCount++;
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mGlRenderer.getTextureName());
        surfaceTexture.setDefaultBufferSize(surfaceRequest.getResolution().getWidth(), surfaceRequest.getResolution().getHeight());
        Surface surface = new Surface(surfaceTexture);
        surfaceRequest.provideSurface(surface, this.mGlExecutor, new DefaultSurfaceProcessor$$ExternalSyntheticLambda6(this, surfaceTexture, surface));
        surfaceTexture.setOnFrameAvailableListener(this, this.mGlHandler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$0$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m170lambda$onInputSurface$0$androidxcameracoreprocessingDefaultSurfaceProcessor(SurfaceTexture surfaceTexture, Surface surface, SurfaceRequest.Result result) {
        surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
        surfaceTexture.release();
        surface.release();
        this.mInputSurfaceCount--;
        checkReadyToRelease();
    }

    public void onOutputSurface(SurfaceOutput surfaceOutput) {
        if (this.mIsReleased.get()) {
            surfaceOutput.close();
        } else {
            this.mGlExecutor.execute(new DefaultSurfaceProcessor$$ExternalSyntheticLambda1(this, surfaceOutput));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$3$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m173lambda$onOutputSurface$3$androidxcameracoreprocessingDefaultSurfaceProcessor(SurfaceOutput surfaceOutput) {
        this.mOutputSurfaces.put(surfaceOutput, surfaceOutput.getSurface(this.mGlExecutor, new DefaultSurfaceProcessor$$ExternalSyntheticLambda5(this, surfaceOutput)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$2$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m172lambda$onOutputSurface$2$androidxcameracoreprocessingDefaultSurfaceProcessor(SurfaceOutput surfaceOutput, SurfaceOutput.Event event) {
        surfaceOutput.close();
        this.mOutputSurfaces.remove(surfaceOutput);
    }

    public void release() {
        if (!this.mIsReleased.getAndSet(true)) {
            this.mGlExecutor.execute(new DefaultSurfaceProcessor$$ExternalSyntheticLambda4(this));
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (!this.mIsReleased.get()) {
            surfaceTexture.updateTexImage();
            surfaceTexture.getTransformMatrix(this.mTextureMatrix);
            for (Map.Entry next : this.mOutputSurfaces.entrySet()) {
                this.mGlRenderer.setOutputSurface((Surface) next.getValue());
                ((SurfaceOutput) next.getKey()).updateTransformMatrix(this.mSurfaceOutputMatrix, this.mTextureMatrix);
                this.mGlRenderer.render(surfaceTexture.getTimestamp(), this.mSurfaceOutputMatrix);
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkReadyToRelease() {
        if (this.mIsReleased.get() && this.mInputSurfaceCount == 0) {
            for (SurfaceOutput close : this.mOutputSurfaces.keySet()) {
                close.close();
            }
            this.mOutputSurfaces.clear();
            this.mGlRenderer.release();
            this.mGlThread.quit();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.RuntimeException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.RuntimeException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initGlRenderer(androidx.camera.core.processing.ShaderProvider r2) {
        /*
            r1 = this;
            androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda3 r0 = new androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda3
            r0.<init>(r1, r2)
            com.google.common.util.concurrent.ListenableFuture r1 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r0)
            r1.get()     // Catch:{ InterruptedException | ExecutionException -> 0x000d }
            return
        L_0x000d:
            r1 = move-exception
            boolean r2 = r1 instanceof java.util.concurrent.ExecutionException
            if (r2 == 0) goto L_0x0016
            java.lang.Throwable r1 = r1.getCause()
        L_0x0016:
            boolean r2 = r1 instanceof java.lang.RuntimeException
            if (r2 == 0) goto L_0x001d
            java.lang.RuntimeException r1 = (java.lang.RuntimeException) r1
            throw r1
        L_0x001d:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r0 = "Failed to create DefaultSurfaceProcessor"
            r2.<init>(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.DefaultSurfaceProcessor.initGlRenderer(androidx.camera.core.processing.ShaderProvider):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initGlRenderer$5$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ Object m169lambda$initGlRenderer$5$androidxcameracoreprocessingDefaultSurfaceProcessor(ShaderProvider shaderProvider, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mGlExecutor.execute(new DefaultSurfaceProcessor$$ExternalSyntheticLambda0(this, shaderProvider, completer));
        return "Init GlRenderer";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initGlRenderer$4$androidx-camera-core-processing-DefaultSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m168lambda$initGlRenderer$4$androidxcameracoreprocessingDefaultSurfaceProcessor(ShaderProvider shaderProvider, CallbackToFutureAdapter.Completer completer) {
        try {
            this.mGlRenderer.init(shaderProvider);
            completer.set(null);
        } catch (RuntimeException e) {
            completer.setException(e);
        }
    }
}
