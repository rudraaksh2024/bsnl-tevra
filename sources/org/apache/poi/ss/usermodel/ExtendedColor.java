package org.apache.poi.ss.usermodel;

import java.awt.Color;
import java.util.Locale;
import kotlin.UByte;

public abstract class ExtendedColor implements Color {
    private static byte applyTint(int i, double d) {
        double d2;
        if (d > 0.0d) {
            double d3 = 1.0d - d;
            d2 = (((double) i) * d3) + (255.0d - (d3 * 255.0d));
        } else if (d >= 0.0d) {
            return (byte) i;
        } else {
            d2 = ((double) i) * (d + 1.0d);
        }
        return (byte) ((int) d2);
    }

    public abstract byte[] getARGB();

    public abstract short getIndex();

    /* access modifiers changed from: protected */
    public abstract byte[] getIndexedRGB();

    public abstract byte[] getRGB();

    /* access modifiers changed from: protected */
    public abstract byte[] getStoredRBG();

    public abstract int getTheme();

    public abstract double getTint();

    public abstract boolean isAuto();

    public abstract boolean isIndexed();

    public abstract boolean isRGB();

    public abstract boolean isThemed();

    public abstract void setRGB(byte[] bArr);

    public abstract void setTint(double d);

    /* access modifiers changed from: protected */
    public void setColor(Color color) {
        setRGB(new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()});
    }

    /* access modifiers changed from: protected */
    public byte[] getRGBOrARGB() {
        byte[] indexedRGB;
        if (!isIndexed() || getIndex() <= 0 || (indexedRGB = getIndexedRGB()) == null) {
            return getStoredRBG();
        }
        return indexedRGB;
    }

    public byte[] getRGBWithTint() {
        byte[] storedRBG = getStoredRBG();
        if (storedRBG != null) {
            if (storedRBG.length == 4) {
                byte[] bArr = new byte[3];
                System.arraycopy(storedRBG, 1, bArr, 0, 3);
                storedRBG = bArr;
            }
            double tint = getTint();
            for (int i = 0; i < storedRBG.length; i++) {
                storedRBG[i] = applyTint(storedRBG[i] & UByte.MAX_VALUE, tint);
            }
        }
        return storedRBG;
    }

    public String getARGBHex() {
        byte[] argb = getARGB();
        if (argb == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : argb) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.ROOT);
    }

    public void setARGBHex(String str) {
        if (str.length() == 6 || str.length() == 8) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, i2 * 2), 16);
                i = i2;
            }
            setRGB(bArr);
            return;
        }
        throw new IllegalArgumentException("Must be of the form 112233 or FFEEDDCC");
    }
}
