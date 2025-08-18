package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;

public class DrawMasterSheet extends DrawSheet {
    public DrawMasterSheet(MasterSheet<?, ?> masterSheet) {
        super(masterSheet);
    }

    /* access modifiers changed from: protected */
    public boolean canDraw(Graphics2D graphics2D, Shape<?, ?> shape) {
        Slide slide = (Slide) graphics2D.getRenderingHint(Drawable.CURRENT_SLIDE);
        if (shape instanceof SimpleShape) {
            SimpleShape simpleShape = (SimpleShape) shape;
            if (simpleShape.getPlaceholder() != null) {
                return slide.getDisplayPlaceholder((SimpleShape<?, ?>) simpleShape);
            }
        }
        return slide.getFollowMasterGraphics();
    }
}
