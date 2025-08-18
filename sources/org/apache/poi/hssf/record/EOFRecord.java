package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public final class EOFRecord extends StandardRecord {
    public static final int ENCODED_SIZE = 4;
    public static final EOFRecord instance = new EOFRecord();
    public static final short sid = 10;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public short getSid() {
        return 10;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
    }

    private EOFRecord() {
    }

    public EOFRecord(RecordInputStream recordInputStream) {
    }

    public EOFRecord copy() {
        return instance;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EOF;
    }
}
