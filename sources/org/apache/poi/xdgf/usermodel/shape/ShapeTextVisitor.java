package org.apache.poi.xdgf.usermodel.shape;

import java.awt.geom.AffineTransform;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class ShapeTextVisitor extends ShapeVisitor {
    protected StringBuilder text = new StringBuilder();

    public static class TextAcceptor implements ShapeVisitorAcceptor {
        public boolean accept(XDGFShape xDGFShape) {
            return xDGFShape.hasText();
        }
    }

    /* access modifiers changed from: protected */
    public ShapeVisitorAcceptor getAcceptor() {
        return new TextAcceptor();
    }

    public void visit(XDGFShape xDGFShape, AffineTransform affineTransform, int i) {
        this.text.append(xDGFShape.getText().getTextContent().trim());
        this.text.append(10);
    }

    public String getText() {
        return this.text.toString();
    }
}
