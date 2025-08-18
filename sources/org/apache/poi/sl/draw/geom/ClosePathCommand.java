package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

public class ClosePathCommand implements ClosePathCommandIf {
    public int hashCode() {
        return 790622;
    }

    public void execute(Path2D.Double doubleR, Context context) {
        doubleR.closePath();
    }

    public boolean equals(Object obj) {
        return obj instanceof ClosePathCommand;
    }
}
