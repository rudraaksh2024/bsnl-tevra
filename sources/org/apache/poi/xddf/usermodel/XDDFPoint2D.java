package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;

public class XDDFPoint2D {
    private CTPoint2D point;
    private long x;
    private long y;

    protected XDDFPoint2D(CTPoint2D cTPoint2D) {
        this.point = cTPoint2D;
    }

    public XDDFPoint2D(long j, long j2) {
        this.x = j;
        this.y = j2;
    }

    public long getX() {
        CTPoint2D cTPoint2D = this.point;
        if (cTPoint2D == null) {
            return this.x;
        }
        return POIXMLUnits.parseLength(cTPoint2D.xgetX());
    }

    public long getY() {
        CTPoint2D cTPoint2D = this.point;
        if (cTPoint2D == null) {
            return this.y;
        }
        return POIXMLUnits.parseLength(cTPoint2D.xgetY());
    }
}
