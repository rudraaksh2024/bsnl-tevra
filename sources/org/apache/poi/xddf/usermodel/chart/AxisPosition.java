package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

public enum AxisPosition {
    BOTTOM(STAxPos.B),
    LEFT(STAxPos.L),
    RIGHT(STAxPos.R),
    TOP(STAxPos.T);
    
    private static final HashMap<STAxPos.Enum, AxisPosition> reverse = null;
    final STAxPos.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisPosition axisPosition : values()) {
            reverse.put(axisPosition.underlying, axisPosition);
        }
    }

    private AxisPosition(STAxPos.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisPosition valueOf(STAxPos.Enum enumR) {
        return reverse.get(enumR);
    }
}
