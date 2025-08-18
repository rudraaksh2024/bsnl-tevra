package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;

public class XDDFConnectionSite {
    private CTConnectionSite site;

    @Internal
    protected XDDFConnectionSite(CTConnectionSite cTConnectionSite) {
        this.site = cTConnectionSite;
    }

    @Internal
    public CTConnectionSite getXmlObject() {
        return this.site;
    }
}
