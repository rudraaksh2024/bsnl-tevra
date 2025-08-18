package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

public enum LayoutTarget {
    INNER(STLayoutTarget.INNER),
    OUTER(STLayoutTarget.OUTER);
    
    private static final HashMap<STLayoutTarget.Enum, LayoutTarget> reverse = null;
    final STLayoutTarget.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (LayoutTarget layoutTarget : values()) {
            reverse.put(layoutTarget.underlying, layoutTarget);
        }
    }

    private LayoutTarget(STLayoutTarget.Enum enumR) {
        this.underlying = enumR;
    }

    static LayoutTarget valueOf(STLayoutTarget.Enum enumR) {
        return reverse.get(enumR);
    }
}
