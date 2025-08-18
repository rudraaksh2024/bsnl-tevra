package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

public enum BarGrouping {
    STANDARD(STBarGrouping.STANDARD),
    CLUSTERED(STBarGrouping.CLUSTERED),
    STACKED(STBarGrouping.STACKED),
    PERCENT_STACKED(STBarGrouping.PERCENT_STACKED);
    
    private static final HashMap<STBarGrouping.Enum, BarGrouping> reverse = null;
    final STBarGrouping.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (BarGrouping barGrouping : values()) {
            reverse.put(barGrouping.underlying, barGrouping);
        }
    }

    private BarGrouping(STBarGrouping.Enum enumR) {
        this.underlying = enumR;
    }

    static BarGrouping valueOf(STBarGrouping.Enum enumR) {
        return reverse.get(enumR);
    }
}
