package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.ArcToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;

public class XSLFArcTo implements ArcToCommandIf {
    private final CTPath2DArcTo arc;

    public XSLFArcTo(CTPath2DArcTo cTPath2DArcTo) {
        this.arc = cTPath2DArcTo;
    }

    public String getHR() {
        return this.arc.xgetHR().getStringValue();
    }

    public void setHR(String str) {
        this.arc.setHR(str);
    }

    public String getWR() {
        return this.arc.xgetHR().getStringValue();
    }

    public void setWR(String str) {
        this.arc.setWR(str);
    }

    public String getStAng() {
        return this.arc.xgetStAng().getStringValue();
    }

    public void setStAng(String str) {
        this.arc.setStAng(str);
    }

    public String getSwAng() {
        return this.arc.xgetSwAng().getStringValue();
    }

    public void setSwAng(String str) {
        this.arc.setSwAng(str);
    }
}
