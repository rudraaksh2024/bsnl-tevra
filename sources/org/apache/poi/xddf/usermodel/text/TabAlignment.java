package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

public enum TabAlignment {
    CENTER(STTextTabAlignType.CTR),
    DECIMAL(STTextTabAlignType.DEC),
    LEFT(STTextTabAlignType.L),
    RIGHT(STTextTabAlignType.R);
    
    private static final HashMap<STTextTabAlignType.Enum, TabAlignment> reverse = null;
    final STTextTabAlignType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (TabAlignment tabAlignment : values()) {
            reverse.put(tabAlignment.underlying, tabAlignment);
        }
    }

    private TabAlignment(STTextTabAlignType.Enum enumR) {
        this.underlying = enumR;
    }

    static TabAlignment valueOf(STTextTabAlignType.Enum enumR) {
        return reverse.get(enumR);
    }
}
