package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DataLabelExtensionRecord extends StandardRecord {
    public static final short sid = 2154;
    private int grbitFrt;
    private int rt;
    private final byte[] unused;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 12;
    }

    public short getSid() {
        return sid;
    }

    public DataLabelExtensionRecord(DataLabelExtensionRecord dataLabelExtensionRecord) {
        super(dataLabelExtensionRecord);
        byte[] bArr = new byte[8];
        this.unused = bArr;
        this.rt = dataLabelExtensionRecord.rt;
        this.grbitFrt = dataLabelExtensionRecord.grbitFrt;
        System.arraycopy(dataLabelExtensionRecord.unused, 0, bArr, 0, bArr.length);
    }

    public DataLabelExtensionRecord(RecordInputStream recordInputStream) {
        byte[] bArr = new byte[8];
        this.unused = bArr;
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        recordInputStream.readFully(bArr);
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.write(this.unused);
    }

    public DataLabelExtensionRecord copy() {
        return new DataLabelExtensionRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATA_LABEL_EXTENSION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new DataLabelExtensionRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new DataLabelExtensionRecord$$ExternalSyntheticLambda1(this), "unused", new DataLabelExtensionRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord  reason: not valid java name */
    public /* synthetic */ Object m2156lambda$getGenericProperties$0$orgapachepoihssfrecordchartDataLabelExtensionRecord() {
        return Integer.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord  reason: not valid java name */
    public /* synthetic */ Object m2157lambda$getGenericProperties$1$orgapachepoihssfrecordchartDataLabelExtensionRecord() {
        return Integer.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord  reason: not valid java name */
    public /* synthetic */ Object m2158lambda$getGenericProperties$2$orgapachepoihssfrecordchartDataLabelExtensionRecord() {
        return this.unused;
    }
}
