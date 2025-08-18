package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;

public enum FontAlignment {
    AUTOMATIC(STTextFontAlignType.AUTO),
    BOTTOM(STTextFontAlignType.B),
    BASELINE(STTextFontAlignType.BASE),
    CENTER(STTextFontAlignType.CTR),
    TOP(STTextFontAlignType.T);
    
    private static final HashMap<STTextFontAlignType.Enum, FontAlignment> reverse = null;
    final STTextFontAlignType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (FontAlignment fontAlignment : values()) {
            reverse.put(fontAlignment.underlying, fontAlignment);
        }
    }

    private FontAlignment(STTextFontAlignType.Enum enumR) {
        this.underlying = enumR;
    }

    static FontAlignment valueOf(STTextFontAlignType.Enum enumR) {
        return reverse.get(enumR);
    }
}
