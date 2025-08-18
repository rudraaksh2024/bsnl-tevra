package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherRecord;

public final class HSSFChildAnchor extends HSSFAnchor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private EscherChildAnchorRecord _escherChildAnchor;

    public int hashCode() {
        return 42;
    }

    public HSSFChildAnchor(EscherChildAnchorRecord escherChildAnchorRecord) {
        this._escherChildAnchor = escherChildAnchorRecord;
    }

    public HSSFChildAnchor() {
        this._escherChildAnchor = new EscherChildAnchorRecord();
    }

    public HSSFChildAnchor(int i, int i2, int i3, int i4) {
        super(Math.min(i, i3), Math.min(i2, i4), Math.max(i, i3), Math.max(i2, i4));
        if (i > i3) {
            this._isHorizontallyFlipped = true;
        }
        if (i2 > i4) {
            this._isVerticallyFlipped = true;
        }
    }

    public int getDx1() {
        return this._escherChildAnchor.getDx1();
    }

    public void setDx1(int i) {
        this._escherChildAnchor.setDx1(i);
    }

    public int getDy1() {
        return this._escherChildAnchor.getDy1();
    }

    public void setDy1(int i) {
        this._escherChildAnchor.setDy1(i);
    }

    public int getDy2() {
        return this._escherChildAnchor.getDy2();
    }

    public void setDy2(int i) {
        this._escherChildAnchor.setDy2(i);
    }

    public int getDx2() {
        return this._escherChildAnchor.getDx2();
    }

    public void setDx2(int i) {
        this._escherChildAnchor.setDx2(i);
    }

    public void setAnchor(int i, int i2, int i3, int i4) {
        setDx1(Math.min(i, i3));
        setDy1(Math.min(i2, i4));
        setDx2(Math.max(i, i3));
        setDy2(Math.max(i2, i4));
    }

    public boolean isHorizontallyFlipped() {
        return this._isHorizontallyFlipped;
    }

    public boolean isVerticallyFlipped() {
        return this._isVerticallyFlipped;
    }

    /* access modifiers changed from: protected */
    public EscherRecord getEscherAnchor() {
        return this._escherChildAnchor;
    }

    /* access modifiers changed from: protected */
    public void createEscherAnchor() {
        this._escherChildAnchor = new EscherChildAnchorRecord();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        HSSFChildAnchor hSSFChildAnchor = (HSSFChildAnchor) obj;
        if (hSSFChildAnchor.getDx1() == getDx1() && hSSFChildAnchor.getDx2() == getDx2() && hSSFChildAnchor.getDy1() == getDy1() && hSSFChildAnchor.getDy2() == getDy2()) {
            return true;
        }
        return false;
    }
}
