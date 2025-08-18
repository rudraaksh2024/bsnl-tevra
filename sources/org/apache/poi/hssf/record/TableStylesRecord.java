package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class TableStylesRecord extends StandardRecord {
    public static final short sid = 2190;
    private int cts;
    private int grbitFrt;
    private String rgchDefListStyle;
    private String rgchDefPivotStyle;
    private int rt;
    private final byte[] unused;

    public short getSid() {
        return sid;
    }

    public TableStylesRecord(TableStylesRecord tableStylesRecord) {
        super(tableStylesRecord);
        byte[] bArr = new byte[8];
        this.unused = bArr;
        this.rt = tableStylesRecord.rt;
        this.grbitFrt = tableStylesRecord.grbitFrt;
        System.arraycopy(tableStylesRecord.unused, 0, bArr, 0, bArr.length);
        this.cts = tableStylesRecord.cts;
        this.rgchDefListStyle = tableStylesRecord.rgchDefListStyle;
        this.rgchDefPivotStyle = tableStylesRecord.rgchDefPivotStyle;
    }

    public TableStylesRecord(RecordInputStream recordInputStream) {
        byte[] bArr = new byte[8];
        this.unused = bArr;
        this.rt = recordInputStream.readUShort();
        this.grbitFrt = recordInputStream.readUShort();
        recordInputStream.readFully(bArr);
        this.cts = recordInputStream.readInt();
        int readUShort = recordInputStream.readUShort();
        int readUShort2 = recordInputStream.readUShort();
        this.rgchDefListStyle = recordInputStream.readUnicodeLEString(readUShort);
        this.rgchDefPivotStyle = recordInputStream.readUnicodeLEString(readUShort2);
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rt);
        littleEndianOutput.writeShort(this.grbitFrt);
        littleEndianOutput.write(this.unused);
        littleEndianOutput.writeInt(this.cts);
        littleEndianOutput.writeShort(this.rgchDefListStyle.length());
        littleEndianOutput.writeShort(this.rgchDefPivotStyle.length());
        StringUtil.putUnicodeLE(this.rgchDefListStyle, littleEndianOutput);
        StringUtil.putUnicodeLE(this.rgchDefPivotStyle, littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.rgchDefListStyle.length() * 2) + 20 + (this.rgchDefPivotStyle.length() * 2);
    }

    public TableStylesRecord copy() {
        return new TableStylesRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TABLE_STYLES;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new TableStylesRecord$$ExternalSyntheticLambda0(this), "grbitFrt", new TableStylesRecord$$ExternalSyntheticLambda1(this), "unused", new TableStylesRecord$$ExternalSyntheticLambda2(this), "cts", new TableStylesRecord$$ExternalSyntheticLambda3(this), "rgchDefListStyle", new TableStylesRecord$$ExternalSyntheticLambda4(this), "rgchDefPivotStyle", new TableStylesRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2099lambda$getGenericProperties$0$orgapachepoihssfrecordTableStylesRecord() {
        return Integer.valueOf(this.rt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2100lambda$getGenericProperties$1$orgapachepoihssfrecordTableStylesRecord() {
        return Integer.valueOf(this.grbitFrt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2101lambda$getGenericProperties$2$orgapachepoihssfrecordTableStylesRecord() {
        return this.unused;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2102lambda$getGenericProperties$3$orgapachepoihssfrecordTableStylesRecord() {
        return Integer.valueOf(this.cts);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2103lambda$getGenericProperties$4$orgapachepoihssfrecordTableStylesRecord() {
        return this.rgchDefListStyle;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-TableStylesRecord  reason: not valid java name */
    public /* synthetic */ Object m2104lambda$getGenericProperties$5$orgapachepoihssfrecordTableStylesRecord() {
        return this.rgchDefPivotStyle;
    }
}
