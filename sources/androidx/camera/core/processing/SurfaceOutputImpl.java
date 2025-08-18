package androidx.camera.core.processing;

import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.Matrix;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.utils.MatrixExt;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

final class SurfaceOutputImpl implements SurfaceOutput {
    private static final String TAG = "SurfaceOutputImpl";
    private final ListenableFuture<Void> mCloseFuture;
    private CallbackToFutureAdapter.Completer<Void> mCloseFutureCompleter;
    private Consumer<SurfaceOutput.Event> mEventListener;
    private Executor mExecutor;
    private final int mFormat;
    private final float[] mGlTransform = new float[16];
    private final SurfaceOutput.GlTransformOptions mGlTransformOptions;
    private boolean mHasPendingCloseRequest = false;
    private final Rect mInputCropRect;
    private final Size mInputSize;
    private boolean mIsClosed = false;
    private final Object mLock = new Object();
    private final boolean mMirroring;
    private final int mRotationDegrees;
    private final Size mSize;
    private final Surface mSurface;
    private final int mTargets;

    SurfaceOutputImpl(Surface surface, int i, int i2, Size size, SurfaceOutput.GlTransformOptions glTransformOptions, Size size2, Rect rect, int i3, boolean z) {
        this.mSurface = surface;
        this.mTargets = i;
        this.mFormat = i2;
        this.mSize = size;
        this.mGlTransformOptions = glTransformOptions;
        this.mInputSize = size2;
        this.mInputCropRect = new Rect(rect);
        this.mMirroring = z;
        if (glTransformOptions == SurfaceOutput.GlTransformOptions.APPLY_CROP_ROTATE_AND_MIRRORING) {
            this.mRotationDegrees = i3;
            calculateGlTransform();
        } else {
            this.mRotationDegrees = 0;
        }
        this.mCloseFuture = CallbackToFutureAdapter.getFuture(new SurfaceOutputImpl$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-processing-SurfaceOutputImpl  reason: not valid java name */
    public /* synthetic */ Object m179lambda$new$0$androidxcameracoreprocessingSurfaceOutputImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mCloseFutureCompleter = completer;
        return "SurfaceOutputImpl close future complete";
    }

    public Surface getSurface(Executor executor, Consumer<SurfaceOutput.Event> consumer) {
        boolean z;
        synchronized (this.mLock) {
            this.mExecutor = executor;
            this.mEventListener = consumer;
            z = this.mHasPendingCloseRequest;
        }
        if (z) {
            requestClose();
        }
        return this.mSurface;
    }

    public void requestClose() {
        Executor executor;
        AtomicReference atomicReference = new AtomicReference();
        synchronized (this.mLock) {
            if (this.mExecutor != null) {
                Consumer<SurfaceOutput.Event> consumer = this.mEventListener;
                if (consumer != null) {
                    if (!this.mIsClosed) {
                        atomicReference.set(consumer);
                        executor = this.mExecutor;
                        this.mHasPendingCloseRequest = false;
                    }
                    executor = null;
                }
            }
            this.mHasPendingCloseRequest = true;
            executor = null;
        }
        if (executor != null) {
            try {
                executor.execute(new SurfaceOutputImpl$$ExternalSyntheticLambda1(this, atomicReference));
            } catch (RejectedExecutionException e) {
                Logger.d(TAG, "Processor executor closed. Close request not posted.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestClose$1$androidx-camera-core-processing-SurfaceOutputImpl  reason: not valid java name */
    public /* synthetic */ void m180lambda$requestClose$1$androidxcameracoreprocessingSurfaceOutputImpl(AtomicReference atomicReference) {
        ((Consumer) atomicReference.get()).accept(SurfaceOutput.Event.of(0, this));
    }

    public int getTargets() {
        return this.mTargets;
    }

    public Size getSize() {
        return this.mSize;
    }

    public int getFormat() {
        return this.mFormat;
    }

    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mIsClosed) {
                this.mIsClosed = true;
            }
        }
        this.mCloseFutureCompleter.set(null);
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mIsClosed;
        }
        return z;
    }

    public ListenableFuture<Void> getCloseFuture() {
        return this.mCloseFuture;
    }

    /* renamed from: androidx.camera.core.processing.SurfaceOutputImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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
                androidx.camera.core.SurfaceOutput$GlTransformOptions r1 = androidx.camera.core.SurfaceOutput.GlTransformOptions.USE_SURFACE_TEXTURE_TRANSFORM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.core.SurfaceOutput$GlTransformOptions r1 = androidx.camera.core.SurfaceOutput.GlTransformOptions.APPLY_CROP_ROTATE_AND_MIRRORING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.SurfaceOutputImpl.AnonymousClass1.<clinit>():void");
        }
    }

    public void updateTransformMatrix(float[] fArr, float[] fArr2) {
        int i = AnonymousClass1.$SwitchMap$androidx$camera$core$SurfaceOutput$GlTransformOptions[this.mGlTransformOptions.ordinal()];
        if (i == 1) {
            System.arraycopy(fArr2, 0, fArr, 0, 16);
        } else if (i == 2) {
            System.arraycopy(this.mGlTransform, 0, fArr, 0, 16);
        } else {
            throw new AssertionError("Unknown GlTransformOptions: " + this.mGlTransformOptions);
        }
    }

    private void calculateGlTransform() {
        Matrix.setIdentityM(this.mGlTransform, 0);
        Matrix.translateM(this.mGlTransform, 0, 0.0f, 1.0f, 0.0f);
        Matrix.scaleM(this.mGlTransform, 0, 1.0f, -1.0f, 1.0f);
        MatrixExt.preRotate(this.mGlTransform, (float) this.mRotationDegrees, 0.5f, 0.5f);
        if (this.mMirroring) {
            Matrix.translateM(this.mGlTransform, 0, 1.0f, 0.0f, 0.0f);
            Matrix.scaleM(this.mGlTransform, 0, -1.0f, 1.0f, 1.0f);
        }
        Size rotateSize = TransformUtils.rotateSize(this.mInputSize, this.mRotationDegrees);
        android.graphics.Matrix rectToRect = TransformUtils.getRectToRect(TransformUtils.sizeToRectF(this.mInputSize), TransformUtils.sizeToRectF(rotateSize), this.mRotationDegrees, this.mMirroring);
        RectF rectF = new RectF(this.mInputCropRect);
        rectToRect.mapRect(rectF);
        float width = rectF.left / ((float) rotateSize.getWidth());
        float height = ((((float) rotateSize.getHeight()) - rectF.height()) - rectF.top) / ((float) rotateSize.getHeight());
        float height2 = rectF.height() / ((float) rotateSize.getHeight());
        Matrix.translateM(this.mGlTransform, 0, width, height, 0.0f);
        Matrix.scaleM(this.mGlTransform, 0, rectF.width() / ((float) rotateSize.getWidth()), height2, 1.0f);
    }
}
