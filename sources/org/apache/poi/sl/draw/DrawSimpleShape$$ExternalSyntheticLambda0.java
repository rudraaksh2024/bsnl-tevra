package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.function.Consumer;
import org.apache.poi.sl.usermodel.PaintStyle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawSimpleShape$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ DrawSimpleShape f$0;
    public final /* synthetic */ Graphics2D f$1;
    public final /* synthetic */ Path2D f$2;

    public /* synthetic */ DrawSimpleShape$$ExternalSyntheticLambda0(DrawSimpleShape drawSimpleShape, Graphics2D graphics2D, Path2D path2D) {
        this.f$0 = drawSimpleShape;
        this.f$1 = graphics2D;
        this.f$2 = path2D;
    }

    public final void accept(Object obj) {
        this.f$0.m2233lambda$draw$0$orgapachepoisldrawDrawSimpleShape(this.f$1, this.f$2, (PaintStyle.PaintModifier) obj);
    }
}
