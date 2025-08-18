package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ExtendedColor;

public class HSSFExtendedColor extends ExtendedColor {
    private org.apache.poi.hssf.record.common.ExtendedColor color;

    public HSSFExtendedColor(org.apache.poi.hssf.record.common.ExtendedColor extendedColor) {
        this.color = extendedColor;
    }

    /* access modifiers changed from: protected */
    public org.apache.poi.hssf.record.common.ExtendedColor getExtendedColor() {
        return this.color;
    }

    public boolean isAuto() {
        return this.color.getType() == 0;
    }

    public boolean isIndexed() {
        return this.color.getType() == 1;
    }

    public boolean isRGB() {
        return this.color.getType() == 2;
    }

    public boolean isThemed() {
        return this.color.getType() == 3;
    }

    public short getIndex() {
        return (short) this.color.getColorIndex();
    }

    public int getTheme() {
        return this.color.getThemeIndex();
    }

    public byte[] getRGB() {
        byte[] bArr = new byte[3];
        byte[] rgba = this.color.getRGBA();
        if (rgba == null) {
            return null;
        }
        System.arraycopy(rgba, 0, bArr, 0, 3);
        return bArr;
    }

    public byte[] getARGB() {
        byte[] bArr = new byte[4];
        byte[] rgba = this.color.getRGBA();
        if (rgba == null) {
            return null;
        }
        System.arraycopy(rgba, 0, bArr, 1, 3);
        bArr[0] = rgba[3];
        return bArr;
    }

    /* access modifiers changed from: protected */
    public byte[] getStoredRBG() {
        return getARGB();
    }

    public void setRGB(byte[] bArr) {
        if (bArr.length == 3) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, 0, bArr2, 0, 3);
            bArr2[3] = -1;
        } else {
            byte b = bArr[0];
            bArr[0] = bArr[1];
            bArr[1] = bArr[2];
            bArr[2] = bArr[3];
            bArr[3] = b;
            this.color.setRGBA(bArr);
        }
        this.color.setType(2);
    }

    public double getTint() {
        return this.color.getTint();
    }

    public void setTint(double d) {
        this.color.setTint(d);
    }

    /* access modifiers changed from: protected */
    public byte[] getIndexedRGB() {
        if (!isIndexed() || getIndex() <= 0) {
            return null;
        }
        HSSFColor hSSFColor = HSSFColor.getIndexHash().get(Integer.valueOf(getIndex()));
        if (hSSFColor == null) {
            return null;
        }
        return new byte[]{(byte) hSSFColor.getTriplet()[0], (byte) hSSFColor.getTriplet()[1], (byte) hSSFColor.getTriplet()[2]};
    }
}
