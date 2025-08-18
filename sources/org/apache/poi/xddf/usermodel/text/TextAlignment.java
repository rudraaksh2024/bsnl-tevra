package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;

public enum TextAlignment {
    CENTER(STTextAlignType.CTR),
    DISTRIBUTED(STTextAlignType.DIST),
    JUSTIFIED(STTextAlignType.JUST),
    JUSTIFIED_LOW(STTextAlignType.JUST_LOW),
    LEFT(STTextAlignType.L),
    RIGHT(STTextAlignType.R),
    THAI_DISTRIBUTED(STTextAlignType.THAI_DIST);
    
    private static final HashMap<STTextAlignType.Enum, TextAlignment> reverse = null;
    final STTextAlignType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (TextAlignment textAlignment : values()) {
            reverse.put(textAlignment.underlying, textAlignment);
        }
    }

    private TextAlignment(STTextAlignType.Enum enumR) {
        this.underlying = enumR;
    }

    static TextAlignment valueOf(STTextAlignType.Enum enumR) {
        return reverse.get(enumR);
    }
}
