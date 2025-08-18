package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;

public class XSLFGuide implements GuideIf {
    final CTGeomGuide guide;

    public XSLFGuide(CTGeomGuide cTGeomGuide) {
        this.guide = cTGeomGuide;
    }

    public String getName() {
        return this.guide.getName();
    }

    public void setName(String str) {
        this.guide.setName(str);
    }

    public String getFmla() {
        return this.guide.getFmla();
    }

    public void setFmla(String str) {
        this.guide.setFmla(str);
    }
}
