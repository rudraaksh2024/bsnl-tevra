package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ObjectProtectRecord extends StandardRecord {
    public static final short sid = 99;
    private short field_1_protect;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 99;
    }

    public ObjectProtectRecord() {
    }

    public ObjectProtectRecord(ObjectProtectRecord objectProtectRecord) {
        super(objectProtectRecord);
        this.field_1_protect = objectProtectRecord.field_1_protect;
    }

    public ObjectProtectRecord(RecordInputStream recordInputStream) {
        this.field_1_protect = recordInputStream.readShort();
    }

    public void setProtect(boolean z) {
        this.field_1_protect = z ? (short) 1 : 0;
    }

    public boolean getProtect() {
        return this.field_1_protect == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_protect);
    }

    public ObjectProtectRecord copy() {
        return new ObjectProtectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.OBJECT_PROTECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("protect", new ObjectProtectRecord$$ExternalSyntheticLambda0(this));
    }
}
