package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartFRTInfoRecord extends StandardRecord {
    public static final short sid = 2128;
    private final short grbitFrt;
    private CFRTID[] rgCFRTID;
    private final short rt;
    private final byte verOriginator;
    private final byte verWriter;

    public short getSid() {
        return sid;
    }

    private static final class CFRTID {
        public static final int ENCODED_SIZE = 4;
        private final int rtFirst;
        private final int rtLast;

        public CFRTID(CFRTID cfrtid) {
            this.rtFirst = cfrtid.rtFirst;
            this.rtLast = cfrtid.rtLast;
        }

        public CFRTID(LittleEndianInput littleEndianInput) {
            this.rtFirst = littleEndianInput.readShort();
            this.rtLast = littleEndianInput.readShort();
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this.rtFirst);
            littleEndianOutput.writeShort(this.rtLast);
        }
    }

    public ChartFRTInfoRecord(ChartFRTInfoRecord chartFRTInfoRecord) {
        super(chartFRTInfoRecord);
        this.rt = chartFRTInfoRecord.rt;
        this.grbitFrt = chartFRTInfoRecord.grbitFrt;
        this.verOriginator = chartFRTInfoRecord.verOriginator;
        this.verWriter = chartFRTInfoRecord.verWriter;
        CFRTID[] cfrtidArr = chartFRTInfoRecord.rgCFRTID;
        if (cfrtidArr != null) {
            this.rgCFRTID = (CFRTID[]) Stream.of(cfrtidArr).map(new ChartFRTInfoRecord$$ExternalSyntheticLambda5()).toArray(new ChartFRTInfoRecord$$ExternalSyntheticLambda6());
        }
    }

    static /* synthetic */ CFRTID[] lambda$new$0(int i) {
        return new CFRTID[i];
    }

    public ChartFRTInfoRecord(RecordInputStream recordInputStream) {
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.verOriginator = recordInputStream.readByte();
        this.verWriter = recordInputStream.readByte();
        int readShort = recordInputStream.readShort();
        if (readShort >= 0) {
            this.rgCFRTID = new CFRTID[readShort];
            for (int i = 0; i < readShort; i++) {
                this.rgCFRTID[i] = new CFRTID((LittleEndianInput) recordInputStream);
            }
            return;
        }
        throw new IllegalArgumentException("Had negative CFRTID: " + readShort);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.rgCFRTID.length * 4) + 8;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.writeByte(this.verOriginator);
        littleEndianOutput.writeByte(this.verWriter);
        littleEndianOutput.writeShort(this.rgCFRTID.length);
        for (CFRTID serialize : this.rgCFRTID) {
            serialize.serialize(littleEndianOutput);
        }
    }

    public ChartFRTInfoRecord copy() {
        return new ChartFRTInfoRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_FRT_INFO;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new ChartFRTInfoRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new ChartFRTInfoRecord$$ExternalSyntheticLambda1(this), "verOriginator", new ChartFRTInfoRecord$$ExternalSyntheticLambda2(this), "verWriter", new ChartFRTInfoRecord$$ExternalSyntheticLambda3(this), "rgCFRTIDs", new ChartFRTInfoRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m2136lambda$getGenericProperties$1$orgapachepoihssfrecordchartChartFRTInfoRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m2137lambda$getGenericProperties$2$orgapachepoihssfrecordchartChartFRTInfoRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m2138lambda$getGenericProperties$3$orgapachepoihssfrecordchartChartFRTInfoRecord() {
        return Byte.valueOf(this.verOriginator);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m2139lambda$getGenericProperties$4$orgapachepoihssfrecordchartChartFRTInfoRecord() {
        return Byte.valueOf(this.verWriter);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m2140lambda$getGenericProperties$5$orgapachepoihssfrecordchartChartFRTInfoRecord() {
        return this.rgCFRTID;
    }
}
