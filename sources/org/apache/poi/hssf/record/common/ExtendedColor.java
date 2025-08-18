package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ExtendedColor implements Duplicatable, GenericRecord {
    public static final int THEME_ACCENT_1 = 4;
    public static final int THEME_ACCENT_2 = 5;
    public static final int THEME_ACCENT_3 = 6;
    public static final int THEME_ACCENT_4 = 7;
    public static final int THEME_ACCENT_5 = 8;
    public static final int THEME_ACCENT_6 = 9;
    public static final int THEME_DARK_1 = 0;
    public static final int THEME_DARK_2 = 2;
    public static final int THEME_FOLLOWED_HYPERLINK = 11;
    public static final int THEME_HYPERLINK = 10;
    public static final int THEME_LIGHT_1 = 1;
    public static final int THEME_LIGHT_2 = 3;
    public static final int TYPE_AUTO = 0;
    public static final int TYPE_INDEXED = 1;
    public static final int TYPE_RGB = 2;
    public static final int TYPE_THEMED = 3;
    public static final int TYPE_UNSET = 4;
    private int colorIndex;
    private byte[] rgba;
    private int themeIndex;
    private double tint;
    private int type;

    public int getDataLength() {
        return 16;
    }

    public ExtendedColor() {
        this.type = 1;
        this.colorIndex = 0;
        this.tint = 0.0d;
    }

    public ExtendedColor(ExtendedColor extendedColor) {
        this.type = extendedColor.type;
        this.tint = extendedColor.tint;
        this.colorIndex = extendedColor.colorIndex;
        byte[] bArr = extendedColor.rgba;
        this.rgba = bArr == null ? null : (byte[]) bArr.clone();
        this.themeIndex = extendedColor.themeIndex;
    }

    public ExtendedColor(LittleEndianInput littleEndianInput) {
        int readInt = littleEndianInput.readInt();
        this.type = readInt;
        if (readInt == 1) {
            this.colorIndex = littleEndianInput.readInt();
        } else if (readInt == 2) {
            byte[] bArr = new byte[4];
            this.rgba = bArr;
            littleEndianInput.readFully(bArr);
        } else if (readInt == 3) {
            this.themeIndex = littleEndianInput.readInt();
        } else {
            littleEndianInput.readInt();
        }
        this.tint = littleEndianInput.readDouble();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getColorIndex() {
        return this.colorIndex;
    }

    public void setColorIndex(int i) {
        this.colorIndex = i;
    }

    public byte[] getRGBA() {
        return this.rgba;
    }

    public void setRGBA(byte[] bArr) {
        this.rgba = bArr == null ? null : (byte[]) bArr.clone();
    }

    public int getThemeIndex() {
        return this.themeIndex;
    }

    public void setThemeIndex(int i) {
        this.themeIndex = i;
    }

    public double getTint() {
        return this.tint;
    }

    public void setTint(double d) {
        if (d < -1.0d || d > 1.0d) {
            throw new IllegalArgumentException("Tint/Shade must be between -1 and +1");
        }
        this.tint = d;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("type", new ExtendedColor$$ExternalSyntheticLambda0(this), "tint", new ExtendedColor$$ExternalSyntheticLambda1(this), "colorIndex", new ExtendedColor$$ExternalSyntheticLambda2(this), "rgba", new ExtendedColor$$ExternalSyntheticLambda3(this), "themeIndex", new ExtendedColor$$ExternalSyntheticLambda4(this));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public ExtendedColor copy() {
        return new ExtendedColor(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.type);
        int i = this.type;
        if (i == 1) {
            littleEndianOutput.writeInt(this.colorIndex);
        } else if (i == 2) {
            littleEndianOutput.write(this.rgba);
        } else if (i == 3) {
            littleEndianOutput.writeInt(this.themeIndex);
        } else {
            littleEndianOutput.writeInt(0);
        }
        littleEndianOutput.writeDouble(this.tint);
    }
}
