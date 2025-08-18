package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;

public enum CapsType {
    ALL(STTextCapsType.ALL),
    NONE(STTextCapsType.NONE),
    SMALL(STTextCapsType.SMALL);
    
    private static final HashMap<STTextCapsType.Enum, CapsType> reverse = null;
    final STTextCapsType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (CapsType capsType : values()) {
            reverse.put(capsType.underlying, capsType);
        }
    }

    private CapsType(STTextCapsType.Enum enumR) {
        this.underlying = enumR;
    }

    static CapsType valueOf(STTextCapsType.Enum enumR) {
        return reverse.get(enumR);
    }
}
