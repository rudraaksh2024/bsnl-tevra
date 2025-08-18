package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;

public enum LineEndLength {
    LARGE(STLineEndLength.LG),
    MEDIUM(STLineEndLength.MED),
    SMALL(STLineEndLength.SM);
    
    private static final HashMap<STLineEndLength.Enum, LineEndLength> reverse = null;
    final STLineEndLength.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LineEndLength lineEndLength : values()) {
            reverse.put(lineEndLength.underlying, lineEndLength);
        }
    }

    private LineEndLength(STLineEndLength.Enum enumR) {
        this.underlying = enumR;
    }

    static LineEndLength valueOf(STLineEndLength.Enum enumR) {
        return reverse.get(enumR);
    }
}
