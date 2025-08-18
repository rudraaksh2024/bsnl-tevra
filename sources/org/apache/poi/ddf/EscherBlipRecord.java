package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public class EscherBlipRecord extends EscherRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 104857600;
    private static final int HEADER_SIZE = 8;
    private static int MAX_RECORD_LENGTH = 104857600;
    public static final short RECORD_ID_END = EscherRecordTypes.BLIP_END.typeID;
    public static final short RECORD_ID_START = EscherRecordTypes.BLIP_START.typeID;
    private byte[] field_pictureData;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherBlipRecord() {
    }

    public EscherBlipRecord(EscherBlipRecord escherBlipRecord) {
        super(escherBlipRecord);
        byte[] bArr = escherBlipRecord.field_pictureData;
        this.field_pictureData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        this.field_pictureData = IOUtils.safelyClone(bArr, i + 8, readHeader, MAX_RECORD_LENGTH);
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        byte[] bArr2 = this.field_pictureData;
        int i2 = i + 4;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        escherSerializationListener.afterRecordSerialize(i2 + this.field_pictureData.length, getRecordId(), this.field_pictureData.length + 4, this);
        return this.field_pictureData.length + 4;
    }

    public int getRecordSize() {
        return this.field_pictureData.length + 8;
    }

    public String getRecordName() {
        EscherRecordTypes forTypeID = EscherRecordTypes.forTypeID(getRecordId());
        if (forTypeID == EscherRecordTypes.UNKNOWN) {
            forTypeID = EscherRecordTypes.BLIP_START;
        }
        return forTypeID.recordName;
    }

    public byte[] getPicturedata() {
        return this.field_pictureData;
    }

    public void setPictureData(byte[] bArr) {
        setPictureData(bArr, 0, bArr == null ? 0 : bArr.length);
    }

    public void setPictureData(byte[] bArr, int i, int i2) {
        if (bArr == null || i < 0 || i2 < 0 || bArr.length < i + i2) {
            throw new IllegalArgumentException("picture data can't be null");
        }
        this.field_pictureData = IOUtils.safelyClone(bArr, i, i2, MAX_RECORD_LENGTH);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherBlipRecord$$ExternalSyntheticLambda0(this), "pictureData", new EscherBlipRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherBlipRecord  reason: not valid java name */
    public /* synthetic */ Object m1952lambda$getGenericProperties$0$orgapachepoiddfEscherBlipRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        EscherRecordTypes forTypeID = EscherRecordTypes.forTypeID(getRecordId());
        return forTypeID != EscherRecordTypes.UNKNOWN ? forTypeID : EscherRecordTypes.BLIP_START;
    }

    public EscherBlipRecord copy() {
        return new EscherBlipRecord(this);
    }
}
