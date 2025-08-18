package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

public enum LegendPosition {
    BOTTOM(STLegendPos.B),
    LEFT(STLegendPos.L),
    RIGHT(STLegendPos.R),
    TOP(STLegendPos.T),
    TOP_RIGHT(STLegendPos.TR);
    
    private static final HashMap<STLegendPos.Enum, LegendPosition> reverse = null;
    final STLegendPos.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LegendPosition legendPosition : values()) {
            reverse.put(legendPosition.underlying, legendPosition);
        }
    }

    private LegendPosition(STLegendPos.Enum enumR) {
        this.underlying = enumR;
    }

    static LegendPosition valueOf(STLegendPos.Enum enumR) {
        return reverse.get(enumR);
    }
}
