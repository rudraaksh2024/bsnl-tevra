package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STScatterStyle;

public enum ScatterStyle {
    LINE(STScatterStyle.LINE),
    LINE_MARKER(STScatterStyle.LINE_MARKER),
    MARKER(STScatterStyle.MARKER),
    NONE(STScatterStyle.NONE),
    SMOOTH(STScatterStyle.SMOOTH),
    SMOOTH_MARKER(STScatterStyle.SMOOTH_MARKER);
    
    private static final HashMap<STScatterStyle.Enum, ScatterStyle> reverse = null;
    final STScatterStyle.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (ScatterStyle scatterStyle : values()) {
            reverse.put(scatterStyle.underlying, scatterStyle);
        }
    }

    private ScatterStyle(STScatterStyle.Enum enumR) {
        this.underlying = enumR;
    }

    static ScatterStyle valueOf(STScatterStyle.Enum enumR) {
        return reverse.get(enumR);
    }
}
