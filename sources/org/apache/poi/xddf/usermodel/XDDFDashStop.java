package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;

public class XDDFDashStop {
    private CTDashStop stop;

    @Internal
    protected XDDFDashStop(CTDashStop cTDashStop) {
        this.stop = cTDashStop;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTDashStop getXmlObject() {
        return this.stop;
    }

    public int getDashLength() {
        return POIXMLUnits.parsePercent(this.stop.xgetD());
    }

    public void setDashLength(int i) {
        this.stop.setD(Integer.valueOf(i));
    }

    public int getSpaceLength() {
        return POIXMLUnits.parsePercent(this.stop.xgetSp());
    }

    public void setSpaceLength(int i) {
        this.stop.setSp(Integer.valueOf(i));
    }
}
