package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import org.apache.poi.sl.usermodel.PaintStyle;

public interface PathIf {
    void addCommand(PathCommand pathCommand);

    PaintStyle.PaintModifier getFill();

    long getH();

    Path2D.Double getPath(Context context);

    long getW();

    boolean isExtrusionOk();

    boolean isFilled();

    boolean isStroked();

    void setExtrusionOk(boolean z);

    void setFill(PaintStyle.PaintModifier paintModifier);

    void setH(long j);

    void setStroke(boolean z);

    void setW(long j);
}
