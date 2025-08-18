package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

public enum AxisOrientation {
    MIN_MAX(STOrientation.MIN_MAX),
    MAX_MIN(STOrientation.MAX_MIN);
    
    private static final HashMap<STOrientation.Enum, AxisOrientation> reverse = null;
    final STOrientation.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisOrientation axisOrientation : values()) {
            reverse.put(axisOrientation.underlying, axisOrientation);
        }
    }

    private AxisOrientation(STOrientation.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisOrientation valueOf(STOrientation.Enum enumR) {
        return reverse.get(enumR);
    }
}
