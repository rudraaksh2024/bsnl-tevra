package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STShape;

public enum Shape {
    BOX(STShape.BOX),
    CONE(STShape.CONE),
    CONE_TO_MAX(STShape.CONE_TO_MAX),
    CYLINDER(STShape.CYLINDER),
    PYRAMID(STShape.PYRAMID),
    PYRAMID_TO_MAX(STShape.PYRAMID_TO_MAX);
    
    private static final HashMap<STShape.Enum, Shape> reverse = null;
    final STShape.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (Shape shape : values()) {
            reverse.put(shape.underlying, shape);
        }
    }

    private Shape(STShape.Enum enumR) {
        this.underlying = enumR;
    }

    static Shape valueOf(STShape.Enum enumR) {
        return reverse.get(enumR);
    }
}
