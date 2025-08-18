package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;

public enum RectangleAlignment {
    BOTTOM(STRectAlignment.B),
    BOTTOM_LEFT(STRectAlignment.BL),
    BOTTOM_RIGHT(STRectAlignment.BR),
    CENTER(STRectAlignment.CTR),
    LEFT(STRectAlignment.L),
    RIGHT(STRectAlignment.R),
    TOP(STRectAlignment.T),
    TOP_LEFT(STRectAlignment.TL),
    TOP_RIGHT(STRectAlignment.TR);
    
    private static final HashMap<STRectAlignment.Enum, RectangleAlignment> reverse = null;
    final STRectAlignment.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (RectangleAlignment rectangleAlignment : values()) {
            reverse.put(rectangleAlignment.underlying, rectangleAlignment);
        }
    }

    private RectangleAlignment(STRectAlignment.Enum enumR) {
        this.underlying = enumR;
    }

    static RectangleAlignment valueOf(STRectAlignment.Enum enumR) {
        return reverse.get(enumR);
    }
}
