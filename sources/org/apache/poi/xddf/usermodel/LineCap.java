package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;

public enum LineCap {
    FLAT(STLineCap.FLAT),
    ROUND(STLineCap.RND),
    SQUARE(STLineCap.SQ);
    
    private static final HashMap<STLineCap.Enum, LineCap> reverse = null;
    final STLineCap.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LineCap lineCap : values()) {
            reverse.put(lineCap.underlying, lineCap);
        }
    }

    private LineCap(STLineCap.Enum enumR) {
        this.underlying = enumR;
    }

    static LineCap valueOf(STLineCap.Enum enumR) {
        return reverse.get(enumR);
    }
}
