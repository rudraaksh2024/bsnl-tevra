package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

public enum DisplayBlanks {
    GAP(STDispBlanksAs.GAP),
    SPAN(STDispBlanksAs.SPAN),
    ZERO(STDispBlanksAs.ZERO);
    
    private static final HashMap<STDispBlanksAs.Enum, DisplayBlanks> reverse = null;
    final STDispBlanksAs.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (DisplayBlanks displayBlanks : values()) {
            reverse.put(displayBlanks.underlying, displayBlanks);
        }
    }

    private DisplayBlanks(STDispBlanksAs.Enum enumR) {
        this.underlying = enumR;
    }

    static DisplayBlanks valueOf(STDispBlanksAs.Enum enumR) {
        return reverse.get(enumR);
    }
}
