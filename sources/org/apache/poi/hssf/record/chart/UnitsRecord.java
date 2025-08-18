package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class UnitsRecord extends StandardRecord {
    public static final short sid = 4097;
    private short field_1_units;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public UnitsRecord() {
    }

    public UnitsRecord(UnitsRecord unitsRecord) {
        super(unitsRecord);
        this.field_1_units = unitsRecord.field_1_units;
    }

    public UnitsRecord(RecordInputStream recordInputStream) {
        this.field_1_units = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_units);
    }

    public UnitsRecord copy() {
        return new UnitsRecord(this);
    }

    public short getUnits() {
        return this.field_1_units;
    }

    public void setUnits(short s) {
        this.field_1_units = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.UNITS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("units", new UnitsRecord$$ExternalSyntheticLambda0(this));
    }
}
