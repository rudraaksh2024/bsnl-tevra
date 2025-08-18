package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

public enum PenAlignment {
    CENTER(STPenAlignment.CTR),
    IN(STPenAlignment.IN);
    
    private static final HashMap<STPenAlignment.Enum, PenAlignment> reverse = null;
    final STPenAlignment.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (PenAlignment penAlignment : values()) {
            reverse.put(penAlignment.underlying, penAlignment);
        }
    }

    private PenAlignment(STPenAlignment.Enum enumR) {
        this.underlying = enumR;
    }

    static PenAlignment valueOf(STPenAlignment.Enum enumR) {
        return reverse.get(enumR);
    }
}
