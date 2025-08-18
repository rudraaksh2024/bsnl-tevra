package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

public enum Grouping {
    STANDARD(STGrouping.STANDARD),
    STACKED(STGrouping.STACKED),
    PERCENT_STACKED(STGrouping.PERCENT_STACKED);
    
    private static final HashMap<STGrouping.Enum, Grouping> reverse = null;
    final STGrouping.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (Grouping grouping : values()) {
            reverse.put(grouping.underlying, grouping);
        }
    }

    private Grouping(STGrouping.Enum enumR) {
        this.underlying = enumR;
    }

    static Grouping valueOf(STGrouping.Enum enumR) {
        return reverse.get(enumR);
    }
}
