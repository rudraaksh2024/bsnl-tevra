package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;

public enum AxisCrosses {
    AUTO_ZERO(STCrosses.AUTO_ZERO),
    MAX(STCrosses.MAX),
    MIN(STCrosses.MIN);
    
    private static final HashMap<STCrosses.Enum, AxisCrosses> reverse = null;
    final STCrosses.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisCrosses axisCrosses : values()) {
            reverse.put(axisCrosses.underlying, axisCrosses);
        }
    }

    private AxisCrosses(STCrosses.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisCrosses valueOf(STCrosses.Enum enumR) {
        return reverse.get(enumR);
    }
}
