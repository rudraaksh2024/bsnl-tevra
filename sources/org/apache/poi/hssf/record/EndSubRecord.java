package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class EndSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 0;
    public static final short sid = 0;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public short getSid() {
        return 0;
    }

    public boolean isTerminating() {
        return true;
    }

    public EndSubRecord() {
    }

    public EndSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    EndSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if ((i & 255) != 0) {
            throw new RecordFormatException("Unexpected size (" + i + ")");
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(0);
        littleEndianOutput.writeShort(0);
    }

    public EndSubRecord copy() {
        return new EndSubRecord();
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.END;
    }
}
