package androidx.camera.core.processing;

import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SettableSurface$$ExternalSyntheticLambda4 implements AsyncFunction {
    public final /* synthetic */ SettableSurface f$0;
    public final /* synthetic */ SurfaceOutput.GlTransformOptions f$1;
    public final /* synthetic */ Size f$2;
    public final /* synthetic */ Rect f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ SettableSurface$$ExternalSyntheticLambda4(SettableSurface settableSurface, SurfaceOutput.GlTransformOptions glTransformOptions, Size size, Rect rect, int i, boolean z) {
        this.f$0 = settableSurface;
        this.f$1 = glTransformOptions;
        this.f$2 = size;
        this.f$3 = rect;
        this.f$4 = i;
        this.f$5 = z;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m177lambda$createSurfaceOutputFuture$2$androidxcameracoreprocessingSettableSurface(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Surface) obj);
    }
}
