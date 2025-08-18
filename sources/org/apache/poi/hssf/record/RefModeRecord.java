package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RefModeRecord extends StandardRecord {
    public static final short USE_A1_MODE = 1;
    public static final short USE_R1C1_MODE = 0;
    public static final short sid = 15;
    private short field_1_mode;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 15;
    }

    public RefModeRecord() {
    }

    public RefModeRecord(RefModeRecord refModeRecord) {
        this.field_1_mode = refModeRecord.field_1_mode;
    }

    public RefModeRecord(RecordInputStream recordInputStream) {
        this.field_1_mode = recordInputStream.readShort();
    }

    public void setMode(short s) {
        this.field_1_mode = s;
    }

    public short getMode() {
        return this.field_1_mode;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getMode());
    }

    public RefModeRecord copy() {
        return new RefModeRecord();
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.REF_MODE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("mode", new RefModeRecord$$ExternalSyntheticLambda0(this));
    }
}
