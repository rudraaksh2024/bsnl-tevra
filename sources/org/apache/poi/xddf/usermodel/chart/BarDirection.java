package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;

public enum BarDirection {
    BAR(STBarDir.BAR),
    COL(STBarDir.COL);
    
    private static final HashMap<STBarDir.Enum, BarDirection> reverse = null;
    final STBarDir.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (BarDirection barDirection : values()) {
            reverse.put(barDirection.underlying, barDirection);
        }
    }

    private BarDirection(STBarDir.Enum enumR) {
        this.underlying = enumR;
    }

    static BarDirection valueOf(STBarDir.Enum enumR) {
        return reverse.get(enumR);
    }
}
