package org.apache.poi.xddf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;

public class XDDFPositiveSize2D {
    private CTPositiveSize2D size;
    private long x;
    private long y;

    protected XDDFPositiveSize2D(CTPositiveSize2D cTPositiveSize2D) {
        this.size = cTPositiveSize2D;
    }

    public XDDFPositiveSize2D(long j, long j2) {
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("x and y must be positive");
        }
        this.x = j;
        this.y = j2;
    }

    public long getX() {
        CTPositiveSize2D cTPositiveSize2D = this.size;
        if (cTPositiveSize2D == null) {
            return this.x;
        }
        return cTPositiveSize2D.getCx();
    }

    public long getY() {
        CTPositiveSize2D cTPositiveSize2D = this.size;
        if (cTPositiveSize2D == null) {
            return this.y;
        }
        return cTPositiveSize2D.getCy();
    }
}
