package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir;

public enum ErrorDirection {
    X(STErrDir.X),
    Y(STErrDir.Y);
    
    private static final HashMap<STErrDir.Enum, ErrorDirection> reverse = null;
    final STErrDir.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (ErrorDirection errorDirection : values()) {
            reverse.put(errorDirection.underlying, errorDirection);
        }
    }

    private ErrorDirection(STErrDir.Enum enumR) {
        this.underlying = enumR;
    }

    static ErrorDirection valueOf(STErrDir.Enum enumR) {
        return reverse.get(enumR);
    }
}
