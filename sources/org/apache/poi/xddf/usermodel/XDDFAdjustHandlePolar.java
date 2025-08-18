package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;

public class XDDFAdjustHandlePolar {
    private CTPolarAdjustHandle handle;

    @Internal
    public XDDFAdjustHandlePolar(CTPolarAdjustHandle cTPolarAdjustHandle) {
        this.handle = cTPolarAdjustHandle;
    }

    @Internal
    public CTPolarAdjustHandle getXmlObject() {
        return this.handle;
    }
}
