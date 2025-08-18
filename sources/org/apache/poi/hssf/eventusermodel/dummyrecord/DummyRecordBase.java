package org.apache.poi.hssf.eventusermodel.dummyrecord;

import org.apache.poi.hssf.record.Record;
import org.apache.poi.util.RecordFormatException;

abstract class DummyRecordBase extends Record {
    public final short getSid() {
        return -1;
    }

    protected DummyRecordBase() {
    }

    public int serialize(int i, byte[] bArr) {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }

    public final int getRecordSize() {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }
}
