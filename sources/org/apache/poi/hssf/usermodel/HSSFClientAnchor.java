package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.ClientAnchor;

public final class HSSFClientAnchor extends HSSFAnchor implements ClientAnchor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_COL = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
    public static final int MAX_ROW = SpreadsheetVersion.EXCEL97.getLastRowIndex();
    private EscherClientAnchorRecord _escherClientAnchor;

    private static int unsignedValue(short s) {
        return s < 0 ? s + 65536 : s;
    }

    public int hashCode() {
        return 42;
    }

    public HSSFClientAnchor(EscherClientAnchorRecord escherClientAnchorRecord) {
        this._escherClientAnchor = escherClientAnchorRecord;
    }

    public HSSFClientAnchor() {
    }

    public HSSFClientAnchor(int i, int i2, int i3, int i4, short s, int i5, short s2, int i6) {
        super(i, i2, i3, i4);
        checkRange(i, 0, IEEEDouble.EXPONENT_BIAS, "dx1");
        checkRange(i3, 0, IEEEDouble.EXPONENT_BIAS, "dx2");
        checkRange(i2, 0, 255, "dy1");
        checkRange(i4, 0, 255, "dy2");
        int i7 = MAX_COL;
        checkRange(s, 0, i7, "col1");
        checkRange(s2, 0, i7, "col2");
        int i8 = MAX_ROW;
        checkRange(i5, 0, i8, "row1");
        checkRange(i6, 0, i8, "row2");
        setCol1((short) Math.min(s, s2));
        setCol2((short) Math.max(s, s2));
        setRow1(Math.min(i5, i6));
        setRow2(Math.max(i5, i6));
        if (s > s2) {
            this._isHorizontallyFlipped = true;
        }
        if (i5 > i6) {
            this._isVerticallyFlipped = true;
        }
    }

    public float getAnchorHeightInPoints(HSSFSheet hSSFSheet) {
        int dy1 = getDy1();
        int dy2 = getDy2();
        int min = Math.min(getRow1(), getRow2());
        int max = Math.max(getRow1(), getRow2());
        if (min == max) {
            return (((float) (dy2 - dy1)) / 256.0f) * getRowHeightInPoints(hSSFSheet, max);
        }
        float rowHeightInPoints = ((256.0f - ((float) dy1)) / 256.0f) * getRowHeightInPoints(hSSFSheet, min);
        float f = 0.0f;
        while (true) {
            rowHeightInPoints += f;
            min++;
            if (min >= max) {
                return rowHeightInPoints + ((((float) dy2) / 256.0f) * getRowHeightInPoints(hSSFSheet, max));
            }
            f = getRowHeightInPoints(hSSFSheet, min);
        }
    }

    private float getRowHeightInPoints(HSSFSheet hSSFSheet, int i) {
        HSSFRow row = hSSFSheet.getRow(i);
        if (row == null) {
            return hSSFSheet.getDefaultRowHeightInPoints();
        }
        return row.getHeightInPoints();
    }

    public short getCol1() {
        return this._escherClientAnchor.getCol1();
    }

    public void setCol1(short s) {
        checkRange(s, 0, MAX_COL, "col1");
        this._escherClientAnchor.setCol1(s);
    }

    public void setCol1(int i) {
        setCol1((short) i);
    }

    public short getCol2() {
        return this._escherClientAnchor.getCol2();
    }

    public void setCol2(short s) {
        checkRange(s, 0, MAX_COL, "col2");
        this._escherClientAnchor.setCol2(s);
    }

    public void setCol2(int i) {
        setCol2((short) i);
    }

    public int getRow1() {
        return unsignedValue(this._escherClientAnchor.getRow1());
    }

    public void setRow1(int i) {
        checkRange(i, 0, MAX_ROW, "row1");
        this._escherClientAnchor.setRow1((short) i);
    }

    public int getRow2() {
        return unsignedValue(this._escherClientAnchor.getRow2());
    }

    public void setRow2(int i) {
        checkRange(i, 0, MAX_ROW, "row2");
        this._escherClientAnchor.setRow2((short) i);
    }

    public void setAnchor(short s, int i, int i2, int i3, short s2, int i4, int i5, int i6) {
        checkRange(getDx1(), 0, IEEEDouble.EXPONENT_BIAS, "dx1");
        checkRange(getDx2(), 0, IEEEDouble.EXPONENT_BIAS, "dx2");
        checkRange(getDy1(), 0, 255, "dy1");
        checkRange(getDy2(), 0, 255, "dy2");
        short col1 = getCol1();
        int i7 = MAX_COL;
        checkRange(col1, 0, i7, "col1");
        checkRange(getCol2(), 0, i7, "col2");
        int row1 = getRow1();
        int i8 = MAX_ROW;
        checkRange(row1, 0, i8, "row1");
        checkRange(getRow2(), 0, i8, "row2");
        setCol1(s);
        setRow1(i);
        setDx1(i2);
        setDy1(i3);
        setCol2(s2);
        setRow2(i4);
        setDx2(i5);
        setDy2(i6);
    }

    public boolean isHorizontallyFlipped() {
        return this._isHorizontallyFlipped;
    }

    public boolean isVerticallyFlipped() {
        return this._isVerticallyFlipped;
    }

    /* access modifiers changed from: protected */
    public EscherRecord getEscherAnchor() {
        return this._escherClientAnchor;
    }

    /* access modifiers changed from: protected */
    public void createEscherAnchor() {
        this._escherClientAnchor = new EscherClientAnchorRecord();
    }

    public ClientAnchor.AnchorType getAnchorType() {
        return ClientAnchor.AnchorType.byId(this._escherClientAnchor.getFlag());
    }

    public void setAnchorType(ClientAnchor.AnchorType anchorType) {
        this._escherClientAnchor.setFlag(anchorType.value);
    }

    private void checkRange(int i, int i2, int i3, String str) {
        if (i < i2 || i > i3) {
            throw new IllegalArgumentException(str + " must be between " + i2 + " and " + i3 + ", but was: " + i);
        }
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
        HSSFClientAnchor hSSFClientAnchor = (HSSFClientAnchor) obj;
        if (hSSFClientAnchor.getCol1() == getCol1() && hSSFClientAnchor.getCol2() == getCol2() && hSSFClientAnchor.getDx1() == getDx1() && hSSFClientAnchor.getDx2() == getDx2() && hSSFClientAnchor.getDy1() == getDy1() && hSSFClientAnchor.getDy2() == getDy2() && hSSFClientAnchor.getRow1() == getRow1() && hSSFClientAnchor.getRow2() == getRow2() && hSSFClientAnchor.getAnchorType() == getAnchorType()) {
            return true;
        }
        return false;
    }

    public int getDx1() {
        return this._escherClientAnchor.getDx1();
    }

    public void setDx1(int i) {
        this._escherClientAnchor.setDx1((short) i);
    }

    public int getDy1() {
        return this._escherClientAnchor.getDy1();
    }

    public void setDy1(int i) {
        this._escherClientAnchor.setDy1((short) i);
    }

    public int getDy2() {
        return this._escherClientAnchor.getDy2();
    }

    public void setDy2(int i) {
        this._escherClientAnchor.setDy2((short) i);
    }

    public int getDx2() {
        return this._escherClientAnchor.getDx2();
    }

    public void setDx2(int i) {
        this._escherClientAnchor.setDx2((short) i);
    }
}
