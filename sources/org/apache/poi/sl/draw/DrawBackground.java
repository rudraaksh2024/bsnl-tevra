package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;

public class DrawBackground extends DrawShape {
    public DrawBackground(Background<?, ?> background) {
        super(background);
    }

    public void draw(Graphics2D graphics2D) {
        Dimension pageSize = this.shape.getSheet().getSlideShow().getPageSize();
        final Rectangle2D.Double doubleR = new Rectangle2D.Double(0.0d, 0.0d, pageSize.getWidth(), pageSize.getHeight());
        Paint paint = DrawFactory.getInstance(graphics2D).getPaint(new PlaceableShape() {
            public boolean getFlipHorizontal() {
                return false;
            }

            public boolean getFlipVertical() {
                return false;
            }

            public ShapeContainer<?, ?> getParent() {
                return null;
            }

            public double getRotation() {
                return 0.0d;
            }

            public void setAnchor(Rectangle2D rectangle2D) {
            }

            public void setFlipHorizontal(boolean z) {
            }

            public void setFlipVertical(boolean z) {
            }

            public void setRotation(double d) {
            }

            public Rectangle2D getAnchor() {
                return doubleR;
            }

            public Sheet<?, ?> getSheet() {
                return DrawBackground.this.shape.getSheet();
            }
        }).getPaint(graphics2D, getShape().getFillStyle().getPaint());
        Rectangle2D anchor = getAnchor(graphics2D, (Rectangle2D) doubleR);
        if (paint != null) {
            graphics2D.setRenderingHint(Drawable.GRADIENT_SHAPE, doubleR);
            graphics2D.setPaint(paint);
            DrawPaint.fillPaintWorkaround(graphics2D, anchor);
        }
    }

    /* access modifiers changed from: protected */
    public Background<?, ?> getShape() {
        return (Background) this.shape;
    }
}
