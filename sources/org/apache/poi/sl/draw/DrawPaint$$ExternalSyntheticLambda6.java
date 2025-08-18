package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.function.BiFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawPaint$$ExternalSyntheticLambda6 implements BiFunction {
    public final /* synthetic */ Point2D f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ Point2D f$2;
    public final /* synthetic */ AffineTransform f$3;

    public /* synthetic */ DrawPaint$$ExternalSyntheticLambda6(Point2D point2D, float f, Point2D point2D2, AffineTransform affineTransform) {
        this.f$0 = point2D;
        this.f$1 = f;
        this.f$2 = point2D2;
        this.f$3 = affineTransform;
    }

    public final Object apply(Object obj, Object obj2) {
        return DrawPaint.lambda$createRadialGradientPaint$2(this.f$0, this.f$1, this.f$2, this.f$3, (float[]) obj, (Color[]) obj2);
    }
}
