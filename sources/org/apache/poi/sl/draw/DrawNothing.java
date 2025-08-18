package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.Shape;

public class DrawNothing implements Drawable {
    protected final Shape<?, ?> shape;

    public void applyTransform(Graphics2D graphics2D) {
    }

    public void draw(Graphics2D graphics2D) {
    }

    public void drawContent(Graphics2D graphics2D) {
    }

    public DrawNothing(Shape<?, ?> shape2) {
        this.shape = shape2;
    }
}
