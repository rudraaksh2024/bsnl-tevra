package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public class EscherClientDataRecord extends EscherRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final byte[] EMPTY = new byte[0];
    private static int MAX_RECORD_LENGTH = 100000;
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_DATA.typeID;
    private byte[] remainingData;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherClientDataRecord() {
    }

    public EscherClientDataRecord(EscherClientDataRecord escherClientDataRecord) {
        super(escherClientDataRecord);
        byte[] bArr = escherClientDataRecord.remainingData;
        this.remainingData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        this.remainingData = readHeader == 0 ? EMPTY : IOUtils.safelyClone(bArr, i + 8, readHeader, MAX_RECORD_LENGTH);
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        if (this.remainingData == null) {
            this.remainingData = EMPTY;
        }
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, this.remainingData.length);
        byte[] bArr2 = this.remainingData;
        int i2 = i + 8;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        int length = i2 + this.remainingData.length;
        int i3 = length - i;
        escherSerializationListener.afterRecordSerialize(length, getRecordId(), i3, this);
        return i3;
    }

    public int getRecordSize() {
        byte[] bArr = this.remainingData;
        return (bArr == null ? 0 : bArr.length) + 8;
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.CLIENT_DATA.recordName;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    public void setRemainingData(byte[] bArr) {
        this.remainingData = bArr == null ? new byte[0] : (byte[]) bArr.clone();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherClientDataRecord$$ExternalSyntheticLambda0(this), "remainingData", new EscherClientDataRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherClientDataRecord  reason: not valid java name */
    public /* synthetic */ Object m1954lambda$getGenericProperties$0$orgapachepoiddfEscherClientDataRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_DATA;
    }

    public EscherClientDataRecord copy() {
        return new EscherClientDataRecord(this);
    }
}
