package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class MMSRecord extends StandardRecord {
    public static final short sid = 193;
    private byte field_1_addMenuCount;
    private byte field_2_delMenuCount;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 193;
    }

    public MMSRecord() {
    }

    public MMSRecord(MMSRecord mMSRecord) {
        this.field_1_addMenuCount = mMSRecord.field_1_addMenuCount;
        this.field_2_delMenuCount = mMSRecord.field_2_delMenuCount;
    }

    public MMSRecord(RecordInputStream recordInputStream) {
        if (recordInputStream.remaining() != 0) {
            this.field_1_addMenuCount = recordInputStream.readByte();
            this.field_2_delMenuCount = recordInputStream.readByte();
        }
    }

    public void setAddMenuCount(byte b) {
        this.field_1_addMenuCount = b;
    }

    public void setDelMenuCount(byte b) {
        this.field_2_delMenuCount = b;
    }

    public byte getAddMenuCount() {
        return this.field_1_addMenuCount;
    }

    public byte getDelMenuCount() {
        return this.field_2_delMenuCount;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getAddMenuCount());
        littleEndianOutput.writeByte(getDelMenuCount());
    }

    public MMSRecord copy() {
        return new MMSRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MMS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("addMenuCount", new MMSRecord$$ExternalSyntheticLambda0(this), "delMenuCount", new MMSRecord$$ExternalSyntheticLambda1(this));
    }
}
