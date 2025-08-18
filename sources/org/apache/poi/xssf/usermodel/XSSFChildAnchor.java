package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

public final class XSSFChildAnchor extends XSSFAnchor {
    private CTTransform2D t2d;

    public XSSFChildAnchor(int i, int i2, int i3, int i4) {
        CTTransform2D newInstance = CTTransform2D.Factory.newInstance();
        this.t2d = newInstance;
        CTPoint2D addNewOff = newInstance.addNewOff();
        CTPositiveSize2D addNewExt = this.t2d.addNewExt();
        addNewOff.setX(Integer.valueOf(i));
        addNewOff.setY(Integer.valueOf(i2));
        addNewExt.setCx((long) Math.abs(i3 - i));
        addNewExt.setCy((long) Math.abs(i4 - i2));
        if (i > i3) {
            this.t2d.setFlipH(true);
        }
        if (i2 > i4) {
            this.t2d.setFlipV(true);
        }
    }

    public XSSFChildAnchor(CTTransform2D cTTransform2D) {
        this.t2d = cTTransform2D;
    }

    @Internal
    public CTTransform2D getCTTransform2D() {
        return this.t2d;
    }

    public int getDx1() {
        return ((Integer) this.t2d.getOff().getX()).intValue();
    }

    public void setDx1(int i) {
        this.t2d.getOff().setX(Integer.valueOf(i));
    }

    public int getDy1() {
        return ((Integer) this.t2d.getOff().getY()).intValue();
    }

    public void setDy1(int i) {
        this.t2d.getOff().setY(Integer.valueOf(i));
    }

    public int getDy2() {
        return (int) (((long) getDy1()) + this.t2d.getExt().getCy());
    }

    public void setDy2(int i) {
        this.t2d.getExt().setCy(((long) i) - ((long) getDy1()));
    }

    public int getDx2() {
        return (int) (((long) getDx1()) + this.t2d.getExt().getCx());
    }

    public void setDx2(int i) {
        this.t2d.getExt().setCx(((long) i) - ((long) getDx1()));
    }
}
