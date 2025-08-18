package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

public interface MoveToCommandIf extends PathCommand {
    AdjustPointIf getPt();

    void setPt(AdjustPointIf adjustPointIf);

    void execute(Path2D.Double doubleR, Context context) {
        AdjustPointIf pt = getPt();
        doubleR.moveTo(context.getValue(pt.getX()), context.getValue(pt.getY()));
    }
}
