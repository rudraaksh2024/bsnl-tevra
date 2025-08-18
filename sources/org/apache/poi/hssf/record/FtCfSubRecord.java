package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class FtCfSubRecord extends SubRecord {
    public static final short BITMAP_BIT = 9;
    public static final short METAFILE_BIT = 2;
    public static final short UNSPECIFIED_BIT = -1;
    public static final short length = 2;
    public static final short sid = 7;
    private short flags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 7;
    }

    public FtCfSubRecord() {
    }

    public FtCfSubRecord(FtCfSubRecord ftCfSubRecord) {
        super(ftCfSubRecord);
        this.flags = ftCfSubRecord.flags;
    }

    public FtCfSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    FtCfSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if (i == 2) {
            this.flags = littleEndianInput.readShort();
            return;
        }
        throw new RecordFormatException("Unexpected size (" + i + ")");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(7);
        littleEndianOutput.writeShort(2);
        littleEndianOutput.writeShort(this.flags);
    }

    public FtCfSubRecord copy() {
        return new FtCfSubRecord(this);
    }

    public short getFlags() {
        return this.flags;
    }

    public void setFlags(short s) {
        this.flags = s;
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.FT_CF;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", GenericRecordUtil.getEnumBitsAsString(new FtCfSubRecord$$ExternalSyntheticLambda0(this), new int[]{2, 9, -1}, new String[]{"METAFILE", "BITMAP", "UNSPECIFIED"}));
    }
}
