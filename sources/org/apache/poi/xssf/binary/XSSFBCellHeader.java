package org.apache.poi.xssf.binary;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

@Internal
class XSSFBCellHeader {
    public static final int length = 8;
    private int colNum;
    private int rowNum;
    private boolean showPhonetic;
    private int styleIdx;

    XSSFBCellHeader() {
    }

    public static void parse(byte[] bArr, int i, int i2, XSSFBCellHeader xSSFBCellHeader) {
        xSSFBCellHeader.reset(i2, XSSFBUtils.castToInt(LittleEndian.getUInt(bArr, i)), XSSFBUtils.get24BitInt(bArr, i + 4), false);
    }

    public void reset(int i, int i2, int i3, boolean z) {
        this.rowNum = i;
        this.colNum = i2;
        this.styleIdx = i3;
        this.showPhonetic = z;
    }

    /* access modifiers changed from: package-private */
    public int getColNum() {
        return this.colNum;
    }

    /* access modifiers changed from: package-private */
    public int getStyleIdx() {
        return this.styleIdx;
    }
}
