package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class AxisUsedRecord extends StandardRecord {
    public static final short sid = 4166;
    private short field_1_numAxis;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public AxisUsedRecord() {
    }

    public AxisUsedRecord(AxisUsedRecord axisUsedRecord) {
        super(axisUsedRecord);
        this.field_1_numAxis = axisUsedRecord.field_1_numAxis;
    }

    public AxisUsedRecord(RecordInputStream recordInputStream) {
        this.field_1_numAxis = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_numAxis);
    }

    public short getNumAxis() {
        return this.field_1_numAxis;
    }

    public void setNumAxis(short s) {
        this.field_1_numAxis = s;
    }

    public AxisUsedRecord copy() {
        return new AxisUsedRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS_USED;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numAxis", new AxisUsedRecord$$ExternalSyntheticLambda0(this));
    }
}
