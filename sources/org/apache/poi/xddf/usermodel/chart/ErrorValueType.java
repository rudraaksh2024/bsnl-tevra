package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrValType;

public enum ErrorValueType {
    CUSTOM(STErrValType.CUST),
    FIXED_VALUE(STErrValType.FIXED_VAL),
    PERCENTAGE(STErrValType.PERCENTAGE),
    STANDARD_DEVIATION(STErrValType.STD_DEV),
    STANDARD_ERROR(STErrValType.STD_ERR);
    
    private static final HashMap<STErrValType.Enum, ErrorValueType> reverse = null;
    final STErrValType.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (ErrorValueType errorValueType : values()) {
            reverse.put(errorValueType.underlying, errorValueType);
        }
    }

    private ErrorValueType(STErrValType.Enum enumR) {
        this.underlying = enumR;
    }

    static ErrorValueType valueOf(STErrValType.Enum enumR) {
        return reverse.get(enumR);
    }
}
