package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;

public enum CompoundLine {
    DOUBLE(STCompoundLine.DBL),
    SINGLE(STCompoundLine.SNG),
    THICK_THIN(STCompoundLine.THICK_THIN),
    THIN_THICK(STCompoundLine.THIN_THICK),
    TRIPLE(STCompoundLine.TRI);
    
    private static final HashMap<STCompoundLine.Enum, CompoundLine> reverse = null;
    final STCompoundLine.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (CompoundLine compoundLine : values()) {
            reverse.put(compoundLine.underlying, compoundLine);
        }
    }

    private CompoundLine(STCompoundLine.Enum enumR) {
        this.underlying = enumR;
    }

    static CompoundLine valueOf(STCompoundLine.Enum enumR) {
        return reverse.get(enumR);
    }
}
