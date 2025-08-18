package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ViewSourceRecord extends StandardRecord {
    public static final short sid = 227;
    private int vs;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public ViewSourceRecord(ViewSourceRecord viewSourceRecord) {
        super(viewSourceRecord);
        this.vs = viewSourceRecord.vs;
    }

    public ViewSourceRecord(RecordInputStream recordInputStream) {
        this.vs = recordInputStream.readShort();
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.vs);
    }

    public ViewSourceRecord copy() {
        return new ViewSourceRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VIEW_SOURCE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("vs", new ViewSourceRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ViewSourceRecord  reason: not valid java name */
    public /* synthetic */ Object m2210lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableViewSourceRecord() {
        return Integer.valueOf(this.vs);
    }
}
