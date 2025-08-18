package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.Shape;

public class DrawGroupShape extends DrawShape {
    public DrawGroupShape(GroupShape<?, ?> groupShape) {
        super(groupShape);
    }

    public void draw(Graphics2D graphics2D) {
        Rectangle2D interiorAnchor = getShape().getInteriorAnchor();
        Rectangle2D anchor = getShape().getAnchor();
        AffineTransform affineTransform = (AffineTransform) graphics2D.getRenderingHint(Drawable.GROUP_TRANSFORM);
        AffineTransform affineTransform2 = new AffineTransform(affineTransform);
        double d = 1.0d;
        double width = interiorAnchor.getWidth() == 0.0d ? 1.0d : anchor.getWidth() / interiorAnchor.getWidth();
        if (interiorAnchor.getHeight() != 0.0d) {
            d = anchor.getHeight() / interiorAnchor.getHeight();
        }
        affineTransform.translate(anchor.getX(), anchor.getY());
        affineTransform.scale(width, d);
        affineTransform.translate(-interiorAnchor.getX(), -interiorAnchor.getY());
        DrawFactory instance = DrawFactory.getInstance(graphics2D);
        AffineTransform transform = graphics2D.getTransform();
        Iterator it = getShape().iterator();
        while (it.hasNext()) {
            AffineTransform transform2 = graphics2D.getTransform();
            graphics2D.setRenderingHint(Drawable.GSAVE, true);
            Drawable drawable = instance.getDrawable((Shape<?, ?>) (Shape) it.next());
            drawable.applyTransform(graphics2D);
            drawable.draw(graphics2D);
            graphics2D.setTransform(transform2);
            graphics2D.setRenderingHint(Drawable.GRESTORE, true);
        }
        graphics2D.setTransform(transform);
        graphics2D.setRenderingHint(Drawable.GROUP_TRANSFORM, affineTransform2);
    }

    /* access modifiers changed from: protected */
    public GroupShape<?, ?> getShape() {
        return (GroupShape) this.shape;
    }
}
