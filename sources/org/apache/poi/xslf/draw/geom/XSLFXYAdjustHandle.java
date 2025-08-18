package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class XSLFXYAdjustHandle implements AdjustHandle {
    final CTXYAdjustHandle xobj;

    public XSLFXYAdjustHandle(CTXYAdjustHandle cTXYAdjustHandle) {
        this.xobj = cTXYAdjustHandle;
    }
}
