package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.QuadToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;

public class XSLFQuadTo implements QuadToCommandIf {
    private final CTPath2DQuadBezierTo bezier;

    public void setPt1(AdjustPointIf adjustPointIf) {
    }

    public void setPt2(AdjustPointIf adjustPointIf) {
    }

    public XSLFQuadTo(CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
        this.bezier = cTPath2DQuadBezierTo;
    }

    public AdjustPointIf getPt1() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(0));
    }

    public AdjustPointIf getPt2() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(1));
    }
}
