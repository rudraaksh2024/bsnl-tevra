package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;

public class XDDFGeometryGuide {
    private CTGeomGuide guide;

    @Internal
    protected XDDFGeometryGuide(CTGeomGuide cTGeomGuide) {
        this.guide = cTGeomGuide;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTGeomGuide getXmlObject() {
        return this.guide;
    }

    public String getFormula() {
        return this.guide.getFmla();
    }

    public void setFormula(String str) {
        this.guide.setFmla(str);
    }

    public String getName() {
        return this.guide.getName();
    }

    public void setName(String str) {
        this.guide.setName(str);
    }
}
