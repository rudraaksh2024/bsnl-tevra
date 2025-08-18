package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;

public class XDDFBulletSizePoints implements XDDFBulletSize {
    private CTTextBulletSizePoint points;

    public XDDFBulletSizePoints(double d) {
        this(CTTextBulletSizePoint.Factory.newInstance());
        setPoints(d);
    }

    @Internal
    protected XDDFBulletSizePoints(CTTextBulletSizePoint cTTextBulletSizePoint) {
        this.points = cTTextBulletSizePoint;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizePoint getXmlObject() {
        return this.points;
    }

    public double getPoints() {
        return ((double) this.points.getVal()) * 0.01d;
    }

    public void setPoints(double d) {
        this.points.setVal(Math.toIntExact(Math.round(d * 100.0d)));
    }
}
