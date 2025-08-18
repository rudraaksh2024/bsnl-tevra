package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;

public enum StrikeType {
    DOUBLE_STRIKE(STTextStrikeType.DBL_STRIKE),
    NO_STRIKE(STTextStrikeType.NO_STRIKE),
    SINGLE_STRIKE(STTextStrikeType.SNG_STRIKE);
    
    private static final HashMap<STTextStrikeType.Enum, StrikeType> reverse = null;
    final STTextStrikeType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (StrikeType strikeType : values()) {
            reverse.put(strikeType.underlying, strikeType);
        }
    }

    private StrikeType(STTextStrikeType.Enum enumR) {
        this.underlying = enumR;
    }

    static StrikeType valueOf(STTextStrikeType.Enum enumR) {
        return reverse.get(enumR);
    }
}
