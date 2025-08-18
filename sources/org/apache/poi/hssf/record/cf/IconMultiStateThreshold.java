package org.apache.poi.hssf.record.cf;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class IconMultiStateThreshold extends Threshold implements Duplicatable {
    public static final byte EQUALS_EXCLUDE = 0;
    public static final byte EQUALS_INCLUDE = 1;
    private byte equals;

    public IconMultiStateThreshold() {
        this.equals = 1;
    }

    public IconMultiStateThreshold(IconMultiStateThreshold iconMultiStateThreshold) {
        super((Threshold) iconMultiStateThreshold);
        this.equals = iconMultiStateThreshold.equals;
    }

    public IconMultiStateThreshold(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
        this.equals = littleEndianInput.readByte();
        littleEndianInput.readInt();
    }

    public byte getEquals() {
        return this.equals;
    }

    public void setEquals(byte b) {
        this.equals = b;
    }

    public int getDataLength() {
        return super.getDataLength() + 5;
    }

    public IconMultiStateThreshold copy() {
        return new IconMultiStateThreshold(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        super.serialize(littleEndianOutput);
        littleEndianOutput.writeByte(this.equals);
        littleEndianOutput.writeInt(0);
    }
}
