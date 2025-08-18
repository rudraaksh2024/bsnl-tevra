package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class InterfaceEndRecord extends StandardRecord {
    public static final InterfaceEndRecord instance = new InterfaceEndRecord();
    public static final short sid = 226;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public short getSid() {
        return sid;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
    }

    private InterfaceEndRecord() {
    }

    public static Record create(RecordInputStream recordInputStream) {
        int remaining = recordInputStream.remaining();
        if (remaining == 0) {
            return instance;
        }
        if (remaining == 2) {
            return new InterfaceHdrRecord(recordInputStream);
        }
        throw new RecordFormatException("Invalid record data size: " + recordInputStream.remaining());
    }

    public InterfaceEndRecord copy() {
        return instance;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.INTERFACE_END;
    }
}
