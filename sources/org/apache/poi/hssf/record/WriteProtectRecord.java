package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public final class WriteProtectRecord extends StandardRecord {
    public static final short sid = 134;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public short getSid() {
        return 134;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
    }

    public WriteProtectRecord() {
    }

    public WriteProtectRecord(RecordInputStream recordInputStream) {
        if (recordInputStream.remaining() == 2) {
            recordInputStream.readShort();
        }
    }

    public WriteProtectRecord copy() {
        return new WriteProtectRecord();
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WRITE_PROTECT;
    }
}
