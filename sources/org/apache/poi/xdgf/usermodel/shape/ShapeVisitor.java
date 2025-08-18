package org.apache.poi.xdgf.usermodel.shape;

import java.awt.geom.AffineTransform;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public abstract class ShapeVisitor {
    protected ShapeVisitorAcceptor _acceptor = getAcceptor();

    public abstract void visit(XDGFShape xDGFShape, AffineTransform affineTransform, int i);

    /* access modifiers changed from: protected */
    public ShapeVisitorAcceptor getAcceptor() {
        return new ShapeVisitorAcceptor() {
            public boolean accept(XDGFShape xDGFShape) {
                return !xDGFShape.isDeleted();
            }
        };
    }

    public void setAcceptor(ShapeVisitorAcceptor shapeVisitorAcceptor) {
        this._acceptor = shapeVisitorAcceptor;
    }

    public boolean accept(XDGFShape xDGFShape) {
        return this._acceptor.accept(xDGFShape);
    }
}
