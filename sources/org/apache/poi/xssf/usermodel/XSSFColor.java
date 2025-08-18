package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

public class XSSFColor extends ExtendedColor {
    private final CTColor ctColor;
    private final IndexedColorMap indexedColorMap;

    public static XSSFColor from(CTColor cTColor, IndexedColorMap indexedColorMap2) {
        if (cTColor == null) {
            return null;
        }
        return new XSSFColor(cTColor, indexedColorMap2);
    }

    public static XSSFColor from(CTColor cTColor) {
        if (cTColor == null) {
            return null;
        }
        return new XSSFColor(cTColor, (IndexedColorMap) null);
    }

    private XSSFColor(CTColor cTColor, IndexedColorMap indexedColorMap2) {
        this.ctColor = cTColor;
        this.indexedColorMap = indexedColorMap2;
    }

    public XSSFColor() {
        this(CTColor.Factory.newInstance(), (IndexedColorMap) null);
    }

    public XSSFColor(IndexedColorMap indexedColorMap2) {
        this(CTColor.Factory.newInstance(), indexedColorMap2);
    }

    public XSSFColor(Color color, IndexedColorMap indexedColorMap2) {
        this(indexedColorMap2);
        setColor(color);
    }

    public XSSFColor(byte[] bArr, IndexedColorMap indexedColorMap2) {
        this(CTColor.Factory.newInstance(), indexedColorMap2);
        this.ctColor.setRgb(bArr);
    }

    public XSSFColor(byte[] bArr) {
        this(bArr, (IndexedColorMap) null);
    }

    public XSSFColor(IndexedColors indexedColors, IndexedColorMap indexedColorMap2) {
        this(CTColor.Factory.newInstance(), indexedColorMap2);
        this.ctColor.setIndexed((long) indexedColors.index);
    }

    public boolean isAuto() {
        return this.ctColor.getAuto();
    }

    public void setAuto(boolean z) {
        this.ctColor.setAuto(z);
    }

    public boolean isIndexed() {
        return this.ctColor.isSetIndexed();
    }

    public boolean isRGB() {
        return this.ctColor.isSetRgb();
    }

    public boolean isThemed() {
        return this.ctColor.isSetTheme();
    }

    public boolean hasAlpha() {
        return this.ctColor.isSetRgb() && this.ctColor.getRgb().length == 4;
    }

    public boolean hasTint() {
        return this.ctColor.isSetTint() && this.ctColor.getTint() != 0.0d;
    }

    public short getIndex() {
        return (short) ((int) this.ctColor.getIndexed());
    }

    public short getIndexed() {
        return getIndex();
    }

    public void setIndexed(int i) {
        this.ctColor.setIndexed((long) i);
    }

    public byte[] getRGB() {
        byte[] rGBOrARGB = getRGBOrARGB();
        if (rGBOrARGB == null) {
            return null;
        }
        return rGBOrARGB.length == 4 ? Arrays.copyOfRange(rGBOrARGB, 1, 4) : rGBOrARGB;
    }

    public byte[] getARGB() {
        byte[] rGBOrARGB = getRGBOrARGB();
        if (rGBOrARGB == null) {
            return null;
        }
        if (rGBOrARGB.length != 3) {
            return rGBOrARGB;
        }
        byte[] bArr = new byte[4];
        bArr[0] = -1;
        System.arraycopy(rGBOrARGB, 0, bArr, 1, 3);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public byte[] getStoredRBG() {
        return this.ctColor.getRgb();
    }

    /* access modifiers changed from: protected */
    public byte[] getIndexedRGB() {
        if (!isIndexed()) {
            return null;
        }
        IndexedColorMap indexedColorMap2 = this.indexedColorMap;
        if (indexedColorMap2 != null) {
            return indexedColorMap2.getRGB(getIndex());
        }
        return DefaultIndexedColorMap.getDefaultRGB(getIndex());
    }

    public void setRGB(byte[] bArr) {
        this.ctColor.setRgb(bArr);
    }

    public int getTheme() {
        return (int) this.ctColor.getTheme();
    }

    public void setTheme(int i) {
        this.ctColor.setTheme((long) i);
    }

    public double getTint() {
        return this.ctColor.getTint();
    }

    public void setTint(double d) {
        this.ctColor.setTint(d);
    }

    @Internal
    public CTColor getCTColor() {
        return this.ctColor;
    }

    public static XSSFColor toXSSFColor(org.apache.poi.ss.usermodel.Color color) {
        if (color == null || (color instanceof XSSFColor)) {
            return (XSSFColor) color;
        }
        throw new IllegalArgumentException("Only XSSFColor objects are supported, but had " + color.getClass());
    }

    public int hashCode() {
        return this.ctColor.toString().hashCode();
    }

    private boolean sameIndexed(XSSFColor xSSFColor) {
        if (isIndexed() != xSSFColor.isIndexed()) {
            return false;
        }
        if (!isIndexed() || getIndexed() == xSSFColor.getIndexed()) {
            return true;
        }
        return false;
    }

    private boolean sameARGB(XSSFColor xSSFColor) {
        if (isRGB() != xSSFColor.isRGB()) {
            return false;
        }
        if (!isRGB() || Arrays.equals(getARGB(), xSSFColor.getARGB())) {
            return true;
        }
        return false;
    }

    private boolean sameTheme(XSSFColor xSSFColor) {
        if (isThemed() != xSSFColor.isThemed()) {
            return false;
        }
        if (!isThemed() || getTheme() == xSSFColor.getTheme()) {
            return true;
        }
        return false;
    }

    private boolean sameTint(XSSFColor xSSFColor) {
        if (hasTint() != xSSFColor.hasTint()) {
            return false;
        }
        if (!hasTint() || getTint() == xSSFColor.getTint()) {
            return true;
        }
        return false;
    }

    private boolean sameAuto(XSSFColor xSSFColor) {
        return isAuto() == xSSFColor.isAuto();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFColor)) {
            return false;
        }
        XSSFColor xSSFColor = (XSSFColor) obj;
        if (!sameARGB(xSSFColor) || !sameTheme(xSSFColor) || !sameIndexed(xSSFColor) || !sameTint(xSSFColor) || !sameAuto(xSSFColor)) {
            return false;
        }
        return true;
    }
}
