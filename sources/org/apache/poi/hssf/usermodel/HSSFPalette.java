package org.apache.poi.hssf.usermodel;

import java.util.Locale;
import kotlin.UByte;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.hssf.util.HSSFColor;

public final class HSSFPalette {
    private PaletteRecord _palette;

    private int unsignedInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    protected HSSFPalette(PaletteRecord paletteRecord) {
        this._palette = paletteRecord;
    }

    public HSSFColor getColor(short s) {
        if (s == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) {
            return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
        }
        byte[] color = this._palette.getColor(s);
        if (color == null) {
            return null;
        }
        return new CustomColor(s, color);
    }

    public HSSFColor getColor(int i) {
        return getColor((short) i);
    }

    public HSSFColor findColor(byte b, byte b2, byte b3) {
        short s = 8;
        byte[] color = this._palette.getColor(8);
        while (color != null) {
            if (color[0] == b && color[1] == b2 && color[2] == b3) {
                return new CustomColor(s, color);
            }
            s = (short) (s + 1);
            color = this._palette.getColor(s);
        }
        return null;
    }

    public HSSFColor findSimilarColor(byte b, byte b2, byte b3) {
        return findSimilarColor(unsignedInt(b), unsignedInt(b2), unsignedInt(b3));
    }

    public HSSFColor findSimilarColor(int i, int i2, int i3) {
        short s = 8;
        byte[] color = this._palette.getColor(8);
        HSSFColor hSSFColor = null;
        int i4 = Integer.MAX_VALUE;
        while (color != null) {
            int abs = Math.abs(i - unsignedInt(color[0])) + Math.abs(i2 - unsignedInt(color[1])) + Math.abs(i3 - unsignedInt(color[2]));
            if (abs < i4) {
                hSSFColor = getColor(s);
                i4 = abs;
            }
            s = (short) (s + 1);
            color = this._palette.getColor(s);
        }
        return hSSFColor;
    }

    public void setColorAtIndex(short s, byte b, byte b2, byte b3) {
        this._palette.setColor(s, b, b2, b3);
    }

    public HSSFColor addColor(byte b, byte b2, byte b3) {
        short s = 8;
        byte[] color = this._palette.getColor(8);
        while (s < 64) {
            if (color == null) {
                setColorAtIndex(s, b, b2, b3);
                return getColor(s);
            }
            s = (short) (s + 1);
            color = this._palette.getColor(s);
        }
        throw new RuntimeException("Could not find free color index");
    }

    private static final class CustomColor extends HSSFColor {
        private byte _blue;
        private short _byteOffset;
        private byte _green;
        private byte _red;

        public CustomColor(short s, byte[] bArr) {
            this(s, bArr[0], bArr[1], bArr[2]);
        }

        private CustomColor(short s, byte b, byte b2, byte b3) {
            this._byteOffset = s;
            this._red = b;
            this._green = b2;
            this._blue = b3;
        }

        public short getIndex() {
            return this._byteOffset;
        }

        public short[] getTriplet() {
            return new short[]{(short) (this._red & UByte.MAX_VALUE), (short) (this._green & UByte.MAX_VALUE), (short) (this._blue & UByte.MAX_VALUE)};
        }

        public String getHexString() {
            return getGnumericPart(this._red) + ParameterizedMessage.ERROR_MSG_SEPARATOR + getGnumericPart(this._green) + ParameterizedMessage.ERROR_MSG_SEPARATOR + getGnumericPart(this._blue);
        }

        private String getGnumericPart(byte b) {
            if (b == 0) {
                return "0";
            }
            byte b2 = b & UByte.MAX_VALUE;
            String upperCase = Integer.toHexString(b2 | (b2 << 8)).toUpperCase(Locale.ROOT);
            while (upperCase.length() < 4) {
                upperCase = "0" + upperCase;
            }
            return upperCase;
        }
    }
}
