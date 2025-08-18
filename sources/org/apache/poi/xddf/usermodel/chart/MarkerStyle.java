package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

public enum MarkerStyle {
    CIRCLE(STMarkerStyle.CIRCLE),
    DASH(STMarkerStyle.DASH),
    DIAMOND(STMarkerStyle.DIAMOND),
    DOT(STMarkerStyle.DOT),
    NONE(STMarkerStyle.NONE),
    PICTURE(STMarkerStyle.PICTURE),
    PLUS(STMarkerStyle.PLUS),
    SQUARE(STMarkerStyle.SQUARE),
    STAR(STMarkerStyle.STAR),
    TRIANGLE(STMarkerStyle.TRIANGLE),
    X(STMarkerStyle.X);
    
    private static final HashMap<STMarkerStyle.Enum, MarkerStyle> reverse = null;
    final STMarkerStyle.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (MarkerStyle markerStyle : values()) {
            reverse.put(markerStyle.underlying, markerStyle);
        }
    }

    private MarkerStyle(STMarkerStyle.Enum enumR) {
        this.underlying = enumR;
    }

    static MarkerStyle valueOf(STMarkerStyle.Enum enumR) {
        return reverse.get(enumR);
    }
}
