package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;

public class XDDFTabStop {
    private CTTextTabStop stop;

    @Internal
    protected XDDFTabStop(CTTextTabStop cTTextTabStop) {
        this.stop = cTTextTabStop;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextTabStop getXmlObject() {
        return this.stop;
    }

    public TabAlignment getAlignment() {
        if (this.stop.isSetAlgn()) {
            return TabAlignment.valueOf(this.stop.getAlgn());
        }
        return null;
    }

    public void setAlignment(TabAlignment tabAlignment) {
        if (tabAlignment != null) {
            this.stop.setAlgn(tabAlignment.underlying);
        } else if (this.stop.isSetAlgn()) {
            this.stop.unsetAlgn();
        }
    }

    public Double getPosition() {
        if (this.stop.isSetPos()) {
            return Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.stop.xgetPos())));
        }
        return null;
    }

    public void setPosition(Double d) {
        if (d != null) {
            this.stop.setPos(Integer.valueOf(Units.toEMU(d.doubleValue())));
        } else if (this.stop.isSetPos()) {
            this.stop.unsetPos();
        }
    }
}
