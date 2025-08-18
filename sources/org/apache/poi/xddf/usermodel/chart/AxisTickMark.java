package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

public enum AxisTickMark {
    CROSS(STTickMark.CROSS),
    IN(STTickMark.IN),
    NONE(STTickMark.NONE),
    OUT(STTickMark.OUT);
    
    private static final HashMap<STTickMark.Enum, AxisTickMark> reverse = null;
    final STTickMark.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisTickMark axisTickMark : values()) {
            reverse.put(axisTickMark.underlying, axisTickMark);
        }
    }

    private AxisTickMark(STTickMark.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisTickMark valueOf(STTickMark.Enum enumR) {
        return reverse.get(enumR);
    }
}
