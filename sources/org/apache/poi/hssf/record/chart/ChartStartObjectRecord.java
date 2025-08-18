package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartStartObjectRecord extends StandardRecord {
    public static final short sid = 2132;
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

    public ChartStartObjectRecord(ChartStartObjectRecord chartStartObjectRecord) {
        super(chartStartObjectRecord);
        this.rt = chartStartObjectRecord.rt;
        this.grbitFrt = chartStartObjectRecord.grbitFrt;
        this.iObjectKind = chartStartObjectRecord.iObjectKind;
        this.iObjectContext = chartStartObjectRecord.iObjectContext;
        this.iObjectInstance1 = chartStartObjectRecord.iObjectInstance1;
        this.iObjectInstance2 = chartStartObjectRecord.iObjectInstance2;
    }

    public ChartStartObjectRecord(RecordInputStream recordInputStream) {
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

    public ChartStartObjectRecord copy() {
        return new ChartStartObjectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_START_OBJECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new ChartStartObjectRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new ChartStartObjectRecord$$ExternalSyntheticLambda1(this), "iObjectKind", new ChartStartObjectRecord$$ExternalSyntheticLambda2(this), "iObjectContext", new ChartStartObjectRecord$$ExternalSyntheticLambda3(this), "iObjectInstance1", new ChartStartObjectRecord$$ExternalSyntheticLambda4(this), "iObjectInstance2", new ChartStartObjectRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2149lambda$getGenericProperties$0$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2150lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2151lambda$getGenericProperties$2$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.iObjectKind);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2152lambda$getGenericProperties$3$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.iObjectContext);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2153lambda$getGenericProperties$4$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.iObjectInstance1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartStartObjectRecord  reason: not valid java name */
    public /* synthetic */ Object m2154lambda$getGenericProperties$5$orgapachepoihssfrecordchartChartStartObjectRecord() {
        return Short.valueOf(this.iObjectInstance2);
    }
}
