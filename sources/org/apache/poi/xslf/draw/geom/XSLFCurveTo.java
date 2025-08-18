package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.CurveToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;

public class XSLFCurveTo implements CurveToCommandIf {
    private final CTPath2DCubicBezierTo bezier;

    public XSLFCurveTo(CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
        this.bezier = cTPath2DCubicBezierTo;
    }

    public XSLFAdjustPoint getPt1() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(0));
    }

    public void setPt1(AdjustPointIf adjustPointIf) {
        CTAdjPoint2D orCreate = getOrCreate(0);
        orCreate.setX(adjustPointIf.getX());
        orCreate.setY(adjustPointIf.getY());
    }

    public XSLFAdjustPoint getPt2() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(1));
    }

    public void setPt2(AdjustPointIf adjustPointIf) {
        CTAdjPoint2D orCreate = getOrCreate(1);
        orCreate.setX(adjustPointIf.getX());
        orCreate.setY(adjustPointIf.getY());
    }

    public XSLFAdjustPoint getPt3() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(2));
    }

    public void setPt3(AdjustPointIf adjustPointIf) {
        CTAdjPoint2D orCreate = getOrCreate(2);
        orCreate.setX(adjustPointIf.getX());
        orCreate.setY(adjustPointIf.getY());
    }

    private CTAdjPoint2D getOrCreate(int i) {
        for (int sizeOfPtArray = (i + 1) - this.bezier.sizeOfPtArray(); sizeOfPtArray > 0; sizeOfPtArray--) {
            this.bezier.addNewPt();
        }
        return this.bezier.getPtArray(i);
    }
}
