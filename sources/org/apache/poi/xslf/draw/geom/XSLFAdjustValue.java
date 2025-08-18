package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustValueIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;

public class XSLFAdjustValue extends XSLFGuide implements AdjustValueIf {
    public XSLFAdjustValue(CTGeomGuide cTGeomGuide) {
        super(cTGeomGuide);
    }
}
