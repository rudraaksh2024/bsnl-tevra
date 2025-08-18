package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;

public enum AxisCrossBetween {
    BETWEEN(STCrossBetween.BETWEEN),
    MIDPOINT_CATEGORY(STCrossBetween.MID_CAT);
    
    private static final HashMap<STCrossBetween.Enum, AxisCrossBetween> reverse = null;
    final STCrossBetween.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisCrossBetween axisCrossBetween : values()) {
            reverse.put(axisCrossBetween.underlying, axisCrossBetween);
        }
    }

    private AxisCrossBetween(STCrossBetween.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisCrossBetween valueOf(STCrossBetween.Enum enumR) {
        return reverse.get(enumR);
    }
}
