package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FnGroupCountRecord extends StandardRecord {
    public static final short COUNT = 14;
    public static final short sid = 156;
    private short field_1_count;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 156;
    }

    public FnGroupCountRecord() {
    }

    public FnGroupCountRecord(FnGroupCountRecord fnGroupCountRecord) {
        super(fnGroupCountRecord);
        this.field_1_count = fnGroupCountRecord.field_1_count;
    }

    public FnGroupCountRecord(RecordInputStream recordInputStream) {
        this.field_1_count = recordInputStream.readShort();
    }

    public void setCount(short s) {
        this.field_1_count = s;
    }

    public short getCount() {
        return this.field_1_count;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getCount());
    }

    public FnGroupCountRecord copy() {
        return new FnGroupCountRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FN_GROUP_COUNT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("count", new FnGroupCountRecord$$ExternalSyntheticLambda0(this));
    }
}
