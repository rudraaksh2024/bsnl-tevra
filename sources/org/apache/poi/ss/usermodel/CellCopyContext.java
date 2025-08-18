package org.apache.poi.ss.usermodel;

import java.util.HashMap;
import java.util.Map;

public class CellCopyContext {
    private final Map<CellStyle, CellStyle> styleMap = new HashMap();

    public CellStyle getMappedStyle(CellStyle cellStyle) {
        return this.styleMap.get(cellStyle);
    }

    public void putMappedStyle(CellStyle cellStyle, CellStyle cellStyle2) {
        this.styleMap.put(cellStyle, cellStyle2);
    }
}
