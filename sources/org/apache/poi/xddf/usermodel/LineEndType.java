package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;

public enum LineEndType {
    ARROW(STLineEndType.ARROW),
    DIAMOND(STLineEndType.DIAMOND),
    NONE(STLineEndType.NONE),
    OVAL(STLineEndType.OVAL),
    STEALTH(STLineEndType.STEALTH),
    TRIANGLE(STLineEndType.TRIANGLE);
    
    private static final HashMap<STLineEndType.Enum, LineEndType> reverse = null;
    final STLineEndType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LineEndType lineEndType : values()) {
            reverse.put(lineEndType.underlying, lineEndType);
        }
    }

    private LineEndType(STLineEndType.Enum enumR) {
        this.underlying = enumR;
    }

    static LineEndType valueOf(STLineEndType.Enum enumR) {
        return reverse.get(enumR);
    }
}
