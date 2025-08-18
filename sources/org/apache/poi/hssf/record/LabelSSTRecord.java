package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class LabelSSTRecord extends CellRecord {
    public static final short sid = 253;
    private int field_4_sst_index;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "LABELSST";
    }

    public short getSid() {
        return sid;
    }

    /* access modifiers changed from: protected */
    public int getValueDataSize() {
        return 4;
    }

    public LabelSSTRecord() {
    }

    public LabelSSTRecord(LabelSSTRecord labelSSTRecord) {
        super((CellRecord) labelSSTRecord);
        this.field_4_sst_index = labelSSTRecord.field_4_sst_index;
    }

    public LabelSSTRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
        this.field_4_sst_index = recordInputStream.readInt();
    }

    public void setSSTIndex(int i) {
        this.field_4_sst_index = i;
    }

    public int getSSTIndex() {
        return this.field_4_sst_index;
    }

    /* access modifiers changed from: protected */
    public void serializeValue(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(getSSTIndex());
    }

    public LabelSSTRecord copy() {
        return new LabelSSTRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL_SST;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new LabelSSTRecord$$ExternalSyntheticLambda0(this), "sstIndex", new LabelSSTRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LabelSSTRecord  reason: not valid java name */
    public /* synthetic */ Object m2035lambda$getGenericProperties$0$orgapachepoihssfrecordLabelSSTRecord() {
        return super.getGenericProperties();
    }
}
