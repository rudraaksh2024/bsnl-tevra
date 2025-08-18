package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;

public enum AnchorType {
    BOTTOM(STTextAnchoringType.B),
    CENTER(STTextAnchoringType.CTR),
    DISTRIBUTED(STTextAnchoringType.DIST),
    JUSTIFIED(STTextAnchoringType.JUST),
    TOP(STTextAnchoringType.T);
    
    private static final HashMap<STTextAnchoringType.Enum, AnchorType> reverse = null;
    final STTextAnchoringType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AnchorType anchorType : values()) {
            reverse.put(anchorType.underlying, anchorType);
        }
    }

    private AnchorType(STTextAnchoringType.Enum enumR) {
        this.underlying = enumR;
    }

    static AnchorType valueOf(STTextAnchoringType.Enum enumR) {
        return reverse.get(enumR);
    }
}
