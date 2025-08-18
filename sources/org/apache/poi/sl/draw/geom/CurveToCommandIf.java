package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

public interface CurveToCommandIf extends PathCommand {
    AdjustPointIf getPt1();

    AdjustPointIf getPt2();

    AdjustPointIf getPt3();

    void setPt1(AdjustPointIf adjustPointIf);

    void setPt2(AdjustPointIf adjustPointIf);

    void setPt3(AdjustPointIf adjustPointIf);

    void execute(Path2D.Double doubleR, Context context) {
        Context context2 = context;
        AdjustPointIf pt1 = getPt1();
        double value = context2.getValue(pt1.getX());
        double value2 = context2.getValue(pt1.getY());
        AdjustPointIf pt2 = getPt2();
        double value3 = context2.getValue(pt2.getX());
        double value4 = context2.getValue(pt2.getY());
        AdjustPointIf pt3 = getPt3();
        doubleR.curveTo(value, value2, value3, value4, context2.getValue(pt3.getX()), context2.getValue(pt3.getY()));
    }
}
