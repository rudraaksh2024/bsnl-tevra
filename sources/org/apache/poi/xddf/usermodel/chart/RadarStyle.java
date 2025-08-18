package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

public enum RadarStyle {
    FILLED(STRadarStyle.FILLED),
    MARKER(STRadarStyle.MARKER),
    STANDARD(STRadarStyle.STANDARD);
    
    private static final HashMap<STRadarStyle.Enum, RadarStyle> reverse = null;
    final STRadarStyle.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (RadarStyle radarStyle : values()) {
            reverse.put(radarStyle.underlying, radarStyle);
        }
    }

    private RadarStyle(STRadarStyle.Enum enumR) {
        this.underlying = enumR;
    }

    static RadarStyle valueOf(STRadarStyle.Enum enumR) {
        return reverse.get(enumR);
    }
}
