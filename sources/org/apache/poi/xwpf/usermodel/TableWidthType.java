package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public enum TableWidthType {
    AUTO(STTblWidth.AUTO),
    DXA(STTblWidth.DXA),
    NIL(STTblWidth.NIL),
    PCT(STTblWidth.PCT);
    
    private STTblWidth.Enum type;

    private TableWidthType(STTblWidth.Enum enumR) {
        STTblWidth.Enum enumR2 = STTblWidth.NIL;
        this.type = enumR;
    }

    @Internal
    public STTblWidth.Enum getStWidthType() {
        return this.type;
    }
}
