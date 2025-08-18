package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class StreamIDRecord extends StandardRecord {
    public static final short sid = 213;
    private int idstm;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public StreamIDRecord(StreamIDRecord streamIDRecord) {
        super(streamIDRecord);
        this.idstm = streamIDRecord.idstm;
    }

    public StreamIDRecord(RecordInputStream recordInputStream) {
        this.idstm = recordInputStream.readShort();
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.idstm);
    }

    public StreamIDRecord copy() {
        return new StreamIDRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STREAM_ID;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("idstm", new StreamIDRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-StreamIDRecord  reason: not valid java name */
    public /* synthetic */ Object m2182lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableStreamIDRecord() {
        return Integer.valueOf(this.idstm);
    }
}
