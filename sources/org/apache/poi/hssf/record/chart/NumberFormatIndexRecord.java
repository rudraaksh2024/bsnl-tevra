package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class NumberFormatIndexRecord extends StandardRecord {
    public static final short sid = 4174;
    private short field_1_formatIndex;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public NumberFormatIndexRecord() {
    }

    public NumberFormatIndexRecord(NumberFormatIndexRecord numberFormatIndexRecord) {
        super(numberFormatIndexRecord);
        this.field_1_formatIndex = numberFormatIndexRecord.field_1_formatIndex;
    }

    public NumberFormatIndexRecord(RecordInputStream recordInputStream) {
        this.field_1_formatIndex = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_formatIndex);
    }

    public NumberFormatIndexRecord copy() {
        return new NumberFormatIndexRecord(this);
    }

    public short getFormatIndex() {
        return this.field_1_formatIndex;
    }

    public void setFormatIndex(short s) {
        this.field_1_formatIndex = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NUMBER_FORMAT_INDEX;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formatIndex", new NumberFormatIndexRecord$$ExternalSyntheticLambda0(this));
    }
}
