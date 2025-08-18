package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

public enum PathShadeType {
    CIRCLE(STPathShadeType.CIRCLE),
    RECTANGLE(STPathShadeType.RECT),
    SHAPE(STPathShadeType.SHAPE);
    
    private static final HashMap<STPathShadeType.Enum, PathShadeType> reverse = null;
    final STPathShadeType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (PathShadeType pathShadeType : values()) {
            reverse.put(pathShadeType.underlying, pathShadeType);
        }
    }

    private PathShadeType(STPathShadeType.Enum enumR) {
        this.underlying = enumR;
    }

    static PathShadeType valueOf(STPathShadeType.Enum enumR) {
        return reverse.get(enumR);
    }
}
