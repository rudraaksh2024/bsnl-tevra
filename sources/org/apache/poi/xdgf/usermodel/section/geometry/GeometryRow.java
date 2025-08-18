package org.apache.poi.xdgf.usermodel.section.geometry;

import java.awt.geom.Path2D;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public interface GeometryRow {
    void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape);

    void setupMaster(GeometryRow geometryRow);
}
