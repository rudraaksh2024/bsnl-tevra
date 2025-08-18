package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartEndBlockRecord extends StandardRecord {
    public static final short sid = 2131;
    private short grbitFrt;
    private short iObjectKind;
    private short rt;
    private byte[] unused;

    public short getSid() {
        return sid;
    }

    public ChartEndBlockRecord() {
    }

    public ChartEndBlockRecord(ChartEndBlockRecord chartEndBlockRecord) {
        super(chartEndBlockRecord);
        this.rt = chartEndBlockRecord.rt;
        this.grbitFrt = chartEndBlockRecord.grbitFrt;
        this.iObjectKind = chartEndBlockRecord.iObjectKind;
        byte[] bArr = chartEndBlockRecord.unused;
        this.unused = bArr == null ? null : (byte[]) bArr.clone();
    }

    public ChartEndBlockRecord(RecordInputStream recordInputStream) {
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.iObjectKind = recordInputStream.readShort();
        if (recordInputStream.available() == 0) {
            this.unused = new byte[0];
            return;
        }
        byte[] bArr = new byte[6];
        this.unused = bArr;
        recordInputStream.readFully(bArr);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.unused.length + 6;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.writeShort(this.iObjectKind);
        littleEndianOutput.write(this.unused);
    }

    public ChartEndBlockRecord copy() {
        return new ChartEndBlockRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_END_BLOCK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new ChartEndBlockRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new ChartEndBlockRecord$$ExternalSyntheticLambda1(this), "iObjectKind", new ChartEndBlockRecord$$ExternalSyntheticLambda2(this), "unused", new ChartEndBlockRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartEndBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2128lambda$getGenericProperties$0$orgapachepoihssfrecordchartChartEndBlockRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartEndBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2129lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartEndBlockRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartEndBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2130lambda$getGenericProperties$2$orgapachepoihssfrecordchartChartEndBlockRecord() {
        return Short.valueOf(this.iObjectKind);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartEndBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2131lambda$getGenericProperties$3$orgapachepoihssfrecordchartChartEndBlockRecord() {
        return this.unused;
    }
}
