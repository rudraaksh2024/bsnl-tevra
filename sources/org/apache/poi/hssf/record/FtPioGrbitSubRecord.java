package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class FtPioGrbitSubRecord extends SubRecord {
    public static final int AUTO_LOAD_BIT = 512;
    public static final int AUTO_PICT_BIT = 1;
    public static final int CAMERA_BIT = 128;
    public static final int CTL_BIT = 16;
    public static final int DDE_BIT = 2;
    public static final int DEFAULT_SIZE_BIT = 256;
    public static final int ICON_BIT = 8;
    public static final int PRINT_CALC_BIT = 4;
    public static final int PRSTM_BIT = 32;
    public static final short length = 2;
    public static final short sid = 8;
    private short flags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 8;
    }

    public FtPioGrbitSubRecord() {
    }

    public FtPioGrbitSubRecord(FtPioGrbitSubRecord ftPioGrbitSubRecord) {
        super(ftPioGrbitSubRecord);
        this.flags = ftPioGrbitSubRecord.flags;
    }

    public FtPioGrbitSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    FtPioGrbitSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if (i == 2) {
            this.flags = littleEndianInput.readShort();
            return;
        }
        throw new RecordFormatException("Unexpected size (" + i + ")");
    }

    public void setFlagByBit(int i, boolean z) {
        if (z) {
            this.flags = (short) (i | this.flags);
            return;
        }
        this.flags = (short) ((i ^ 65535) & this.flags);
    }

    public boolean getFlagByBit(int i) {
        return (this.flags & i) != 0;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(8);
        littleEndianOutput.writeShort(2);
        littleEndianOutput.writeShort(this.flags);
    }

    public FtPioGrbitSubRecord copy() {
        return new FtPioGrbitSubRecord(this);
    }

    public short getFlags() {
        return this.flags;
    }

    public void setFlags(short s) {
        this.flags = s;
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.FT_PIO_GRBIT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new FtPioGrbitSubRecord$$ExternalSyntheticLambda0(this), new int[]{1, 2, 4, 8, 16, 32, 128, 256, 512}, new String[]{"AUTO_PICT", "DDE", "PRINT_CALC", "ICON", "CTL", "PRSTM", "CAMERA", "DEFAULT_SIZE", "AUTO_LOAD"}));
    }
}
