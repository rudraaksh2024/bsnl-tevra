package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

public enum AxisLabelAlignment {
    CENTER(STLblAlgn.CTR),
    LEFT(STLblAlgn.L),
    RIGHT(STLblAlgn.R);
    
    private static final HashMap<STLblAlgn.Enum, AxisLabelAlignment> reverse = null;
    final STLblAlgn.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisLabelAlignment axisLabelAlignment : values()) {
            reverse.put(axisLabelAlignment.underlying, axisLabelAlignment);
        }
    }

    private AxisLabelAlignment(STLblAlgn.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisLabelAlignment valueOf(STLblAlgn.Enum enumR) {
        return reverse.get(enumR);
    }
}
