package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;

public class XDDFPath {
    private CTPath2D path;

    @Internal
    protected XDDFPath(CTPath2D cTPath2D) {
        this.path = cTPath2D;
    }

    @Internal
    public CTPath2D getXmlObject() {
        return this.path;
    }
}
