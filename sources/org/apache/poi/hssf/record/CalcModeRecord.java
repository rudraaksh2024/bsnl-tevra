package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CalcModeRecord extends StandardRecord {
    public static final short AUTOMATIC = 1;
    public static final short AUTOMATIC_EXCEPT_TABLES = -1;
    public static final short MANUAL = 0;
    public static final short sid = 13;
    private short field_1_calcmode;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 13;
    }

    public CalcModeRecord() {
    }

    public CalcModeRecord(CalcModeRecord calcModeRecord) {
        super(calcModeRecord);
        this.field_1_calcmode = calcModeRecord.field_1_calcmode;
    }

    public CalcModeRecord(RecordInputStream recordInputStream) {
        this.field_1_calcmode = recordInputStream.readShort();
    }

    public void setCalcMode(short s) {
        this.field_1_calcmode = s;
    }

    public short getCalcMode() {
        return this.field_1_calcmode;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getCalcMode());
    }

    public CalcModeRecord copy() {
        return new CalcModeRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CALC_MODE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("calcMode", new CalcModeRecord$$ExternalSyntheticLambda0(this));
    }
}
