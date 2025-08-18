package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartEndObjectRecord extends StandardRecord {
    public static final short sid = 2133;
    private short grbitFrt;
    private short iObjectKind;
    private byte[] reserved;
    private short rt;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 12;
    }

    public short getSid() {
        return sid;
    }

    public ChartEndObjectRecord(ChartEndObjectRecord chartEndObjectRecord) {
        super(chartEndObjectRecord);
        this.rt = chartEndObjectRecord.rt;
        this.grbitFrt = chartEndObjectRecord.grbitFrt;
        this.iObjectKind = chartEndObjectRecord.iObjectKind;
        byte[] bArr = chartEndObjectRecord.reserved;
        this.reserved = bArr == null ? null : (byte[]) bArr.clone();
    }

    public ChartEndObjectRecord(RecordInputStream recordInputStream) {
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.iObjectKind = recordInputStream.readShort();
        this.reserved = new byte[6];
        if (recordInputStream.available() != 0) {
            recordInputStream.readFully(this.reserved);
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.writeShort(this.iObjectKind);
        littleEndianOutput.write(this.reserved);
    }

    public ChartEndObjectRecord copy() {
        return new ChartEndObjectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_END_OBJECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new ChartEndObjectRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new ChartEndObjectRecord$$ExternalSyntheticLambda1(this), "iObjectKind", new ChartEndObjectRecord$$ExternalSyntheticLambda2(this), "reserved", new ChartEndObjectRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartEndObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2132lambda$getGenericProperties$0$orgapachepoihssfrecordchartChartEndObjectRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartEndObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2133lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartEndObjectRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartEndObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2134lambda$getGenericProperties$2$orgapachepoihssfrecordchartChartEndObjectRecord() {
        return Short.valueOf(this.iObjectKind);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartEndObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2135lambda$getGenericProperties$3$orgapachepoihssfrecordchartChartEndObjectRecord() {
        return this.reserved;
    }
}
