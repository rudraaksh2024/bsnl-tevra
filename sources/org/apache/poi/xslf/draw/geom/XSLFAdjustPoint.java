package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;

public class XSLFAdjustPoint implements AdjustPointIf {
    private final CTAdjPoint2D pnt;

    public XSLFAdjustPoint(CTAdjPoint2D cTAdjPoint2D) {
        this.pnt = cTAdjPoint2D;
    }

    public String getX() {
        return this.pnt.xgetX().getStringValue();
    }

    public void setX(String str) {
        this.pnt.setX(str);
    }

    public boolean isSetX() {
        return this.pnt.xgetX() != null;
    }

    public String getY() {
        return this.pnt.xgetY().getStringValue();
    }

    public void setY(String str) {
        this.pnt.setY(str);
    }

    public boolean isSetY() {
        return this.pnt.xgetY() != null;
    }
}
