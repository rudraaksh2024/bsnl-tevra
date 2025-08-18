package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public enum BlackWhiteMode {
    AUTO(STBlackWhiteMode.AUTO),
    BLACK(STBlackWhiteMode.BLACK),
    BLACK_GRAY(STBlackWhiteMode.BLACK_GRAY),
    BLACK_WHITE(STBlackWhiteMode.BLACK_WHITE);
    
    private static final HashMap<STBlackWhiteMode.Enum, BlackWhiteMode> reverse = null;
    final STBlackWhiteMode.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (BlackWhiteMode blackWhiteMode : values()) {
            reverse.put(blackWhiteMode.underlying, blackWhiteMode);
        }
    }

    private BlackWhiteMode(STBlackWhiteMode.Enum enumR) {
        this.underlying = enumR;
    }

    static BlackWhiteMode valueOf(STBlackWhiteMode.Enum enumR) {
        return reverse.get(enumR);
    }
}
