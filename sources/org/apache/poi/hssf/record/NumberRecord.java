package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class NumberRecord extends CellRecord {
    public static final short sid = 515;
    private double field_4_value;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "NUMBER";
    }

    public short getSid() {
        return sid;
    }

    /* access modifiers changed from: protected */
    public int getValueDataSize() {
        return 8;
    }

    public NumberRecord() {
    }

    public NumberRecord(NumberRecord numberRecord) {
        super((CellRecord) numberRecord);
        this.field_4_value = numberRecord.field_4_value;
    }

    public NumberRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
        this.field_4_value = recordInputStream.readDouble();
    }

    public void setValue(double d) {
        this.field_4_value = d;
    }

    public double getValue() {
        return this.field_4_value;
    }

    /* access modifiers changed from: protected */
    public void serializeValue(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeDouble(getValue());
    }

    public NumberRecord copy() {
        return new NumberRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NUMBER;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new NumberRecord$$ExternalSyntheticLambda0(this), "value", new NumberRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NumberRecord  reason: not valid java name */
    public /* synthetic */ Object m2061lambda$getGenericProperties$0$orgapachepoihssfrecordNumberRecord() {
        return super.getGenericProperties();
    }
}
