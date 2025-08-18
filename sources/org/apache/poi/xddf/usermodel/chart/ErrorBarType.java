package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

public enum ErrorBarType {
    BOTH(STErrBarType.BOTH),
    MINUS(STErrBarType.MINUS),
    PLUS(STErrBarType.PLUS);
    
    private static final HashMap<STErrBarType.Enum, ErrorBarType> reverse = null;
    final STErrBarType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (ErrorBarType errorBarType : values()) {
            reverse.put(errorBarType.underlying, errorBarType);
        }
    }

    private ErrorBarType(STErrBarType.Enum enumR) {
        this.underlying = enumR;
    }

    static ErrorBarType valueOf(STErrBarType.Enum enumR) {
        return reverse.get(enumR);
    }
}
