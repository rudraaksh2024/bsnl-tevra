package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartStartBlockRecord extends StandardRecord {
    public static final short sid = 2130;
    private short grbitFrt;
    private short iObjectContext;
    private short iObjectInstance1;
    private short iObjectInstance2;
    private short iObjectKind;
    private short rt;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 12;
    }

    public short getSid() {
        return sid;
    }

    public ChartStartBlockRecord() {
    }

    public ChartStartBlockRecord(ChartStartBlockRecord chartStartBlockRecord) {
        super(chartStartBlockRecord);
        this.rt = chartStartBlockRecord.rt;
        this.grbitFrt = chartStartBlockRecord.grbitFrt;
        this.iObjectKind = chartStartBlockRecord.iObjectKind;
        this.iObjectContext = chartStartBlockRecord.iObjectContext;
        this.iObjectInstance1 = chartStartBlockRecord.iObjectInstance1;
        this.iObjectInstance2 = chartStartBlockRecord.iObjectInstance2;
    }

    public ChartStartBlockRecord(RecordInputStream recordInputStream) {
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.iObjectKind = recordInputStream.readShort();
        this.iObjectContext = recordInputStream.readShort();
        this.iObjectInstance1 = recordInputStream.readShort();
        this.iObjectInstance2 = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.writeShort(this.iObjectKind);
        littleEndianOutput.writeShort(this.iObjectContext);
        littleEndianOutput.writeShort(this.iObjectInstance1);
        littleEndianOutput.writeShort(this.iObjectInstance2);
    }

    public ChartStartBlockRecord copy() {
        return new ChartStartBlockRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_START_BLOCK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new ChartStartBlockRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new ChartStartBlockRecord$$ExternalSyntheticLambda1(this), "iObjectKind", new ChartStartBlockRecord$$ExternalSyntheticLambda2(this), "iObjectContext", new ChartStartBlockRecord$$ExternalSyntheticLambda3(this), "iObjectInstance1", new ChartStartBlockRecord$$ExternalSyntheticLambda4(this), "iObjectInstance2", new ChartStartBlockRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2143lambda$getGenericProperties$0$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2144lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2145lambda$getGenericProperties$2$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.iObjectKind);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2146lambda$getGenericProperties$3$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.iObjectContext);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2147lambda$getGenericProperties$4$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.iObjectInstance1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartStartBlockRecord  reason: not valid java name */
    public /* synthetic */ Object m2148lambda$getGenericProperties$5$orgapachepoihssfrecordchartChartStartBlockRecord() {
        return Short.valueOf(this.iObjectInstance2);
    }
}
