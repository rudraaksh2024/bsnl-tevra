package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CatLabRecord extends StandardRecord {
    public static final short sid = 2134;
    private short at;
    private short grbit;
    private short grbitFrt;
    private short rt;
    private Short unused;
    private short wOffset;

    public short getSid() {
        return sid;
    }

    public CatLabRecord(CatLabRecord catLabRecord) {
        super(catLabRecord);
        this.rt = catLabRecord.rt;
        this.grbitFrt = catLabRecord.grbitFrt;
        this.wOffset = catLabRecord.wOffset;
        this.at = catLabRecord.at;
        this.grbit = catLabRecord.grbit;
        this.unused = catLabRecord.unused;
    }

    public CatLabRecord(RecordInputStream recordInputStream) {
        this.rt = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.wOffset = recordInputStream.readShort();
        this.at = recordInputStream.readShort();
        this.grbit = recordInputStream.readShort();
        if (recordInputStream.available() == 0) {
            this.unused = null;
        } else {
            this.unused = Short.valueOf(recordInputStream.readShort());
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.unused == null ? 0 : 2) + 10;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.writeShort(this.wOffset);
        littleEndianOutput.writeShort(this.at);
        littleEndianOutput.writeShort(this.grbit);
        Short sh = this.unused;
        if (sh != null) {
            littleEndianOutput.writeShort(sh.shortValue());
        }
    }

    public CatLabRecord copy() {
        return new CatLabRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CAT_LAB;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new CatLabRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new CatLabRecord$$ExternalSyntheticLambda1(this), "wOffset", new CatLabRecord$$ExternalSyntheticLambda2(this), "at", new CatLabRecord$$ExternalSyntheticLambda3(this), "grbit", new CatLabRecord$$ExternalSyntheticLambda4(this), "unused", new CatLabRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2122lambda$getGenericProperties$0$orgapachepoihssfrecordchartCatLabRecord() {
        return Short.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2123lambda$getGenericProperties$1$orgapachepoihssfrecordchartCatLabRecord() {
        return Short.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2124lambda$getGenericProperties$2$orgapachepoihssfrecordchartCatLabRecord() {
        return Short.valueOf(this.wOffset);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2125lambda$getGenericProperties$3$orgapachepoihssfrecordchartCatLabRecord() {
        return Short.valueOf(this.at);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2126lambda$getGenericProperties$4$orgapachepoihssfrecordchartCatLabRecord() {
        return Short.valueOf(this.grbit);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-CatLabRecord  reason: not valid java name */
    public /* synthetic */ Object m2127lambda$getGenericProperties$5$orgapachepoihssfrecordchartCatLabRecord() {
        return this.unused;
    }
}
