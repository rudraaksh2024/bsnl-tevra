package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CRNRecord extends StandardRecord {
    public static final short sid = 90;
    private int field_1_last_column_index;
    private int field_2_first_column_index;
    private int field_3_row_index;
    private Object[] field_4_constant_values;

    public short getSid() {
        return 90;
    }

    public CRNRecord(CRNRecord cRNRecord) {
        super(cRNRecord);
        this.field_1_last_column_index = cRNRecord.field_1_last_column_index;
        this.field_2_first_column_index = cRNRecord.field_2_first_column_index;
        this.field_3_row_index = cRNRecord.field_3_row_index;
        Object[] objArr = cRNRecord.field_4_constant_values;
        this.field_4_constant_values = objArr == null ? null : (Object[]) objArr.clone();
    }

    public CRNRecord(RecordInputStream recordInputStream) {
        this.field_1_last_column_index = recordInputStream.readUByte();
        this.field_2_first_column_index = recordInputStream.readUByte();
        this.field_3_row_index = recordInputStream.readShort();
        this.field_4_constant_values = ConstantValueParser.parse(recordInputStream, (this.field_1_last_column_index - this.field_2_first_column_index) + 1);
    }

    public int getNumberOfCRNs() {
        return this.field_1_last_column_index;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return ConstantValueParser.getEncodedSize(this.field_4_constant_values) + 4;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this.field_1_last_column_index);
        littleEndianOutput.writeByte(this.field_2_first_column_index);
        littleEndianOutput.writeShort(this.field_3_row_index);
        ConstantValueParser.encode(littleEndianOutput, this.field_4_constant_values);
    }

    public CRNRecord copy() {
        return new CRNRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CRN;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new CRNRecord$$ExternalSyntheticLambda0(this), "firstColumn", new CRNRecord$$ExternalSyntheticLambda1(this), "lastColumn", new CRNRecord$$ExternalSyntheticLambda2(this), "constantValues", new CRNRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CRNRecord  reason: not valid java name */
    public /* synthetic */ Object m1982lambda$getGenericProperties$0$orgapachepoihssfrecordCRNRecord() {
        return Integer.valueOf(this.field_3_row_index);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-CRNRecord  reason: not valid java name */
    public /* synthetic */ Object m1983lambda$getGenericProperties$1$orgapachepoihssfrecordCRNRecord() {
        return Integer.valueOf(this.field_2_first_column_index);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-CRNRecord  reason: not valid java name */
    public /* synthetic */ Object m1984lambda$getGenericProperties$2$orgapachepoihssfrecordCRNRecord() {
        return Integer.valueOf(this.field_1_last_column_index);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-CRNRecord  reason: not valid java name */
    public /* synthetic */ Object m1985lambda$getGenericProperties$3$orgapachepoihssfrecordCRNRecord() {
        return this.field_4_constant_values;
    }
}
