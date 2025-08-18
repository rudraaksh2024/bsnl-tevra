package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;

public enum LayoutMode {
    EDGE(STLayoutMode.EDGE),
    FACTOR(STLayoutMode.FACTOR);
    
    private static final HashMap<STLayoutMode.Enum, LayoutMode> reverse = null;
    final STLayoutMode.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LayoutMode layoutMode : values()) {
            reverse.put(layoutMode.underlying, layoutMode);
        }
    }

    private LayoutMode(STLayoutMode.Enum enumR) {
        this.underlying = enumR;
    }

    static LayoutMode valueOf(STLayoutMode.Enum enumR) {
        return reverse.get(enumR);
    }
}
