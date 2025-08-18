package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CalcCountRecord extends StandardRecord {
    public static final short sid = 12;
    private short field_1_iterations;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 12;
    }

    public CalcCountRecord() {
    }

    public CalcCountRecord(CalcCountRecord calcCountRecord) {
        super(calcCountRecord);
        this.field_1_iterations = calcCountRecord.field_1_iterations;
    }

    public CalcCountRecord(RecordInputStream recordInputStream) {
        this.field_1_iterations = recordInputStream.readShort();
    }

    public void setIterations(short s) {
        this.field_1_iterations = s;
    }

    public short getIterations() {
        return this.field_1_iterations;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getIterations());
    }

    public CalcCountRecord copy() {
        return new CalcCountRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CALC_COUNT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("iterations", new CalcCountRecord$$ExternalSyntheticLambda0(this));
    }
}
