package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FeatHdrRecord extends StandardRecord {
    public static final int SHAREDFEATURES_ISFFACTOID = 4;
    public static final int SHAREDFEATURES_ISFFEC2 = 3;
    public static final int SHAREDFEATURES_ISFLIST = 5;
    public static final int SHAREDFEATURES_ISFPROTECTION = 2;
    public static final short sid = 2151;
    private long cbHdrData;
    private final FtrHeader futureHeader;
    private int isf_sharedFeatureType;
    private byte reserved;
    private byte[] rgbHdrData;

    public short getSid() {
        return sid;
    }

    public FeatHdrRecord() {
        FtrHeader ftrHeader = new FtrHeader();
        this.futureHeader = ftrHeader;
        ftrHeader.setRecordType(sid);
    }

    public FeatHdrRecord(FeatHdrRecord featHdrRecord) {
        super(featHdrRecord);
        this.futureHeader = featHdrRecord.futureHeader.copy();
        this.isf_sharedFeatureType = featHdrRecord.isf_sharedFeatureType;
        this.reserved = featHdrRecord.reserved;
        this.cbHdrData = featHdrRecord.cbHdrData;
        byte[] bArr = featHdrRecord.rgbHdrData;
        this.rgbHdrData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public FeatHdrRecord(RecordInputStream recordInputStream) {
        this.futureHeader = new FtrHeader(recordInputStream);
        this.isf_sharedFeatureType = recordInputStream.readShort();
        this.reserved = recordInputStream.readByte();
        this.cbHdrData = (long) recordInputStream.readInt();
        this.rgbHdrData = recordInputStream.readRemainder();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this.futureHeader.serialize(littleEndianOutput);
        littleEndianOutput.writeShort(this.isf_sharedFeatureType);
        littleEndianOutput.writeByte(this.reserved);
        littleEndianOutput.writeInt((int) this.cbHdrData);
        littleEndianOutput.write(this.rgbHdrData);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.rgbHdrData.length + 19;
    }

    public FeatHdrRecord copy() {
        return new FeatHdrRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FEAT_HDR;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("futureHeader", new FeatHdrRecord$$ExternalSyntheticLambda0(this), "isf_sharedFeatureType", new FeatHdrRecord$$ExternalSyntheticLambda1(this), "reserved", new FeatHdrRecord$$ExternalSyntheticLambda2(this), "cbHdrData", new FeatHdrRecord$$ExternalSyntheticLambda3(this), "rgbHdrData", new FeatHdrRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FeatHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2014lambda$getGenericProperties$0$orgapachepoihssfrecordFeatHdrRecord() {
        return this.futureHeader;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FeatHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2015lambda$getGenericProperties$1$orgapachepoihssfrecordFeatHdrRecord() {
        return Integer.valueOf(this.isf_sharedFeatureType);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FeatHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2016lambda$getGenericProperties$2$orgapachepoihssfrecordFeatHdrRecord() {
        return Byte.valueOf(this.reserved);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-FeatHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2017lambda$getGenericProperties$3$orgapachepoihssfrecordFeatHdrRecord() {
        return Long.valueOf(this.cbHdrData);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-FeatHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2018lambda$getGenericProperties$4$orgapachepoihssfrecordFeatHdrRecord() {
        return this.rgbHdrData;
    }
}
