package org.apache.poi.xdgf.usermodel.shape;

import org.apache.poi.xdgf.usermodel.XDGFShape;

public interface ShapeVisitorAcceptor {
    boolean accept(XDGFShape xDGFShape);
}
