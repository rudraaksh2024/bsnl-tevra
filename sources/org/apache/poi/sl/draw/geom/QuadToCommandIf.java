package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

public interface QuadToCommandIf extends PathCommand {
    AdjustPointIf getPt1();

    AdjustPointIf getPt2();

    void setPt1(AdjustPointIf adjustPointIf);

    void setPt2(AdjustPointIf adjustPointIf);

    void execute(Path2D.Double doubleR, Context context) {
        AdjustPointIf pt1 = getPt1();
        double value = context.getValue(pt1.getX());
        double value2 = context.getValue(pt1.getY());
        AdjustPointIf pt2 = getPt2();
        doubleR.quadTo(value, value2, context.getValue(pt2.getX()), context.getValue(pt2.getY()));
    }
}
