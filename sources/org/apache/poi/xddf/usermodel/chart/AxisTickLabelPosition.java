package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

public enum AxisTickLabelPosition {
    HIGH(STTickLblPos.HIGH),
    LOW(STTickLblPos.LOW),
    NEXT_TO(STTickLblPos.NEXT_TO),
    NONE(STTickLblPos.NONE);
    
    private static final HashMap<STTickLblPos.Enum, AxisTickLabelPosition> reverse = null;
    final STTickLblPos.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (AxisTickLabelPosition axisTickLabelPosition : values()) {
            reverse.put(axisTickLabelPosition.underlying, axisTickLabelPosition);
        }
    }

    private AxisTickLabelPosition(STTickLblPos.Enum enumR) {
        this.underlying = enumR;
    }

    static AxisTickLabelPosition valueOf(STTickLblPos.Enum enumR) {
        return reverse.get(enumR);
    }
}
