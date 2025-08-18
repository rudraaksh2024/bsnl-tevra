package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public class EscherBitmapBlip extends EscherBlipRecord {
    private static final int HEADER_SIZE = 8;
    public static final short RECORD_ID_DIB = EscherRecordTypes.BLIP_DIB.typeID;
    public static final short RECORD_ID_JPEG = EscherRecordTypes.BLIP_JPEG.typeID;
    public static final short RECORD_ID_PNG = EscherRecordTypes.BLIP_PNG.typeID;
    private final byte[] field_1_UID;
    private byte field_2_marker;

    public EscherBitmapBlip() {
        this.field_1_UID = new byte[16];
        this.field_2_marker = -1;
    }

    public EscherBitmapBlip(EscherBitmapBlip escherBitmapBlip) {
        super(escherBitmapBlip);
        byte[] bArr = new byte[16];
        this.field_1_UID = bArr;
        this.field_2_marker = -1;
        System.arraycopy(escherBitmapBlip.field_1_UID, 0, bArr, 0, bArr.length);
        this.field_2_marker = escherBitmapBlip.field_2_marker;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = i + 8;
        System.arraycopy(bArr, i2, this.field_1_UID, 0, 16);
        int i3 = i2 + 16;
        this.field_2_marker = bArr[i3];
        setPictureData(bArr, i3 + 1, readHeader - 17);
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, getRecordSize() - 8);
        int i2 = i + 8;
        System.arraycopy(this.field_1_UID, 0, bArr, i2, 16);
        bArr[i2 + 16] = this.field_2_marker;
        byte[] picturedata = getPicturedata();
        System.arraycopy(picturedata, 0, bArr, i2 + 17, picturedata.length);
        escherSerializationListener.afterRecordSerialize(i + getRecordSize(), getRecordId(), getRecordSize(), this);
        return picturedata.length + 25;
    }

    public int getRecordSize() {
        return (getPicturedata() == null ? 0 : getPicturedata().length) + 25;
    }

    public byte[] getUID() {
        return this.field_1_UID;
    }

    public void setUID(byte[] bArr) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("field_1_UID must be byte[16]");
        }
        System.arraycopy(bArr, 0, this.field_1_UID, 0, 16);
    }

    public byte getMarker() {
        return this.field_2_marker;
    }

    public void setMarker(byte b) {
        this.field_2_marker = b;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherBitmapBlip$$ExternalSyntheticLambda0(this), "uid", new EscherBitmapBlip$$ExternalSyntheticLambda1(this), "marker", new EscherBitmapBlip$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherBitmapBlip  reason: not valid java name */
    public /* synthetic */ Object m1951lambda$getGenericProperties$0$orgapachepoiddfEscherBitmapBlip() {
        return super.getGenericProperties();
    }

    public EscherBitmapBlip copy() {
        return new EscherBitmapBlip(this);
    }
}
