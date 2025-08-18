package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

public interface PathCommand {
    void execute(Path2D.Double doubleR, Context context);
}
