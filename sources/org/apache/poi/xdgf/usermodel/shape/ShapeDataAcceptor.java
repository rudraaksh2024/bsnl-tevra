package org.apache.poi.xdgf.usermodel.shape;

import org.apache.poi.xdgf.usermodel.XDGFShape;

public class ShapeDataAcceptor implements ShapeVisitorAcceptor {
    public boolean accept(XDGFShape xDGFShape) {
        if (xDGFShape.isDeleted()) {
            return false;
        }
        if ((xDGFShape.hasText() && xDGFShape.getTextAsString().length() != 0) || xDGFShape.isShape1D()) {
            return true;
        }
        if (!xDGFShape.hasMaster() && !xDGFShape.hasMasterShape()) {
            return true;
        }
        if (xDGFShape.hasMaster() && !xDGFShape.hasMasterShape()) {
            return true;
        }
        if (!xDGFShape.hasMasterShape() || !xDGFShape.getMasterShape().isTopmost()) {
            return false;
        }
        return true;
    }
}
