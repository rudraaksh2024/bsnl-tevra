package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class EndRecord extends StandardRecord {
    public static final short sid = 4148;

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

    public EndRecord() {
    }

    public EndRecord(RecordInputStream recordInputStream) {
    }

    public EndRecord copy() {
        return new EndRecord();
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.END;
    }
}
