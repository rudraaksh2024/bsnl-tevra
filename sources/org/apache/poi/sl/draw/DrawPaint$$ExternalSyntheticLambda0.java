package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.function.BiFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawPaint$$ExternalSyntheticLambda0 implements BiFunction {
    public final /* synthetic */ Point2D f$0;
    public final /* synthetic */ Point2D f$1;

    public /* synthetic */ DrawPaint$$ExternalSyntheticLambda0(Point2D point2D, Point2D point2D2) {
        this.f$0 = point2D;
        this.f$1 = point2D2;
    }

    public final Object apply(Object obj, Object obj2) {
        return DrawPaint.lambda$createLinearGradientPaint$1(this.f$0, this.f$1, (float[]) obj, (Color[]) obj2);
    }
}
