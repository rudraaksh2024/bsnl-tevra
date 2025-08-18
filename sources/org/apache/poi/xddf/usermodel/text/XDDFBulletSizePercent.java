package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;

public class XDDFBulletSizePercent implements XDDFBulletSize {
    private CTTextBulletSizePercent percent;
    private Double scale;

    public XDDFBulletSizePercent(double d) {
        this(CTTextBulletSizePercent.Factory.newInstance(), (Double) null);
        setPercent(d);
    }

    @Internal
    protected XDDFBulletSizePercent(CTTextBulletSizePercent cTTextBulletSizePercent, Double d) {
        this.percent = cTTextBulletSizePercent;
        this.scale = Double.valueOf(d != null ? 0.001d * d.doubleValue() : 0.001d);
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizePercent getXmlObject() {
        return this.percent;
    }

    public double getPercent() {
        return ((double) POIXMLUnits.parsePercent(this.percent.xgetVal())) * this.scale.doubleValue();
    }

    public void setPercent(double d) {
        this.percent.setVal(Long.toString(Math.round(d * 1000.0d)));
    }
}
