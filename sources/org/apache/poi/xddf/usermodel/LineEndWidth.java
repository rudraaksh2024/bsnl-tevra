package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

public enum LineEndWidth {
    LARGE(STLineEndWidth.LG),
    MEDIUM(STLineEndWidth.MED),
    SMALL(STLineEndWidth.SM);
    
    private static final HashMap<STLineEndWidth.Enum, LineEndWidth> reverse = null;
    final STLineEndWidth.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LineEndWidth lineEndWidth : values()) {
            reverse.put(lineEndWidth.underlying, lineEndWidth);
        }
    }

    private LineEndWidth(STLineEndWidth.Enum enumR) {
        this.underlying = enumR;
    }

    static LineEndWidth valueOf(STLineEndWidth.Enum enumR) {
        return reverse.get(enumR);
    }
}
