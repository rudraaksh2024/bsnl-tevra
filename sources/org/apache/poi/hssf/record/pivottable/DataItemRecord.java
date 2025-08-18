package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class DataItemRecord extends StandardRecord {
    public static final short sid = 197;
    private int df;
    private int ifmt;
    private int iiftab;
    private int isxvd;
    private int isxvdData;
    private int isxvi;
    private String name;

    public short getSid() {
        return 197;
    }

    public DataItemRecord(DataItemRecord dataItemRecord) {
        super(dataItemRecord);
        this.isxvdData = dataItemRecord.isxvdData;
        this.iiftab = dataItemRecord.iiftab;
        this.df = dataItemRecord.df;
        this.isxvd = dataItemRecord.isxvd;
        this.isxvi = dataItemRecord.isxvi;
        this.ifmt = dataItemRecord.ifmt;
        this.name = dataItemRecord.name;
    }

    public DataItemRecord(RecordInputStream recordInputStream) {
        this.isxvdData = recordInputStream.readUShort();
        this.iiftab = recordInputStream.readUShort();
        this.df = recordInputStream.readUShort();
        this.isxvd = recordInputStream.readUShort();
        this.isxvi = recordInputStream.readUShort();
        this.ifmt = recordInputStream.readUShort();
        this.name = recordInputStream.readString();
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.isxvdData);
        littleEndianOutput.writeShort(this.iiftab);
        littleEndianOutput.writeShort(this.df);
        littleEndianOutput.writeShort(this.isxvd);
        littleEndianOutput.writeShort(this.isxvi);
        littleEndianOutput.writeShort(this.ifmt);
        StringUtil.writeUnicodeString(littleEndianOutput, this.name);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return StringUtil.getEncodedSize(this.name) + 12;
    }

    public DataItemRecord copy() {
        return new DataItemRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATA_ITEM;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("isxvdData", new DataItemRecord$$ExternalSyntheticLambda0(this), "iiftab", new DataItemRecord$$ExternalSyntheticLambda1(this), "df", new DataItemRecord$$ExternalSyntheticLambda2(this), "isxvd", new DataItemRecord$$ExternalSyntheticLambda3(this), "isxvi", new DataItemRecord$$ExternalSyntheticLambda4(this), "ifmt", new DataItemRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2166lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.isxvdData);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2167lambda$getGenericProperties$1$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.iiftab);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2168lambda$getGenericProperties$2$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.df);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2169lambda$getGenericProperties$3$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.isxvd);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2170lambda$getGenericProperties$4$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.isxvi);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-DataItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2171lambda$getGenericProperties$5$orgapachepoihssfrecordpivottableDataItemRecord() {
        return Integer.valueOf(this.ifmt);
    }
}
