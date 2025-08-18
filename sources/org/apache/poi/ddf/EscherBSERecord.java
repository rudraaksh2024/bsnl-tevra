package org.apache.poi.ddf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public final class EscherBSERecord extends EscherRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static int MAX_RECORD_LENGTH = 100000;
    public static final short RECORD_ID = EscherRecordTypes.BSE.typeID;
    private byte[] _remainingData;
    private byte field_10_unused2;
    private byte field_11_unused3;
    private EscherBlipRecord field_12_blipRecord;
    private byte field_1_blipTypeWin32;
    private byte field_2_blipTypeMacOS;
    private final byte[] field_3_uid;
    private short field_4_tag;
    private int field_5_size;
    private int field_6_ref;
    private int field_7_offset;
    private byte field_8_usage;
    private byte field_9_name;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherBSERecord() {
        this.field_3_uid = new byte[16];
        this._remainingData = new byte[0];
        setRecordId(RECORD_ID);
    }

    public EscherBSERecord(EscherBSERecord escherBSERecord) {
        super(escherBSERecord);
        byte[] bArr = new byte[16];
        this.field_3_uid = bArr;
        this._remainingData = new byte[0];
        this.field_1_blipTypeWin32 = escherBSERecord.field_1_blipTypeWin32;
        this.field_2_blipTypeMacOS = escherBSERecord.field_2_blipTypeMacOS;
        System.arraycopy(escherBSERecord.field_3_uid, 0, bArr, 0, bArr.length);
        this.field_4_tag = escherBSERecord.field_4_tag;
        this.field_5_size = escherBSERecord.field_5_size;
        this.field_6_ref = escherBSERecord.field_6_ref;
        this.field_7_offset = escherBSERecord.field_7_offset;
        this.field_8_usage = escherBSERecord.field_8_usage;
        this.field_9_name = escherBSERecord.field_9_name;
        this.field_10_unused2 = escherBSERecord.field_10_unused2;
        this.field_11_unused3 = escherBSERecord.field_11_unused3;
        this.field_12_blipRecord = escherBSERecord.field_12_blipRecord.copy();
        this._remainingData = (byte[]) escherBSERecord._remainingData.clone();
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int i2;
        int readHeader = readHeader(bArr, i);
        int i3 = i + 8;
        this.field_1_blipTypeWin32 = bArr[i3];
        this.field_2_blipTypeMacOS = bArr[i3 + 1];
        int i4 = 0;
        System.arraycopy(bArr, i3 + 2, this.field_3_uid, 0, 16);
        this.field_4_tag = LittleEndian.getShort(bArr, i3 + 18);
        this.field_5_size = LittleEndian.getInt(bArr, i3 + 20);
        this.field_6_ref = LittleEndian.getInt(bArr, i3 + 24);
        this.field_7_offset = LittleEndian.getInt(bArr, i3 + 28);
        this.field_8_usage = bArr[i3 + 32];
        this.field_9_name = bArr[i3 + 33];
        this.field_10_unused2 = bArr[i3 + 34];
        this.field_11_unused3 = bArr[i3 + 35];
        int i5 = readHeader - 36;
        if (i5 > 0) {
            int i6 = i3 + 36;
            EscherBlipRecord escherBlipRecord = (EscherBlipRecord) escherRecordFactory.createRecord(bArr, i6);
            this.field_12_blipRecord = escherBlipRecord;
            i2 = escherBlipRecord.fillFields(bArr, i6, escherRecordFactory);
        } else {
            i2 = 0;
        }
        int i7 = i5 - i2;
        this._remainingData = IOUtils.safelyClone(bArr, i3 + i2 + 36, i7, MAX_RECORD_LENGTH);
        int i8 = i7 + 8 + 36;
        EscherBlipRecord escherBlipRecord2 = this.field_12_blipRecord;
        if (escherBlipRecord2 != null) {
            i4 = escherBlipRecord2.getRecordSize();
        }
        return i8 + i4;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        if (this._remainingData == null) {
            this._remainingData = new byte[0];
        }
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        EscherBlipRecord escherBlipRecord = this.field_12_blipRecord;
        LittleEndian.putInt(bArr, i + 4, this._remainingData.length + 36 + (escherBlipRecord == null ? 0 : escherBlipRecord.getRecordSize()));
        int i2 = i + 8;
        bArr[i2] = this.field_1_blipTypeWin32;
        bArr[i + 9] = this.field_2_blipTypeMacOS;
        System.arraycopy(this.field_3_uid, 0, bArr, i + 10, 16);
        LittleEndian.putShort(bArr, i + 26, this.field_4_tag);
        LittleEndian.putInt(bArr, i + 28, this.field_5_size);
        LittleEndian.putInt(bArr, i + 32, this.field_6_ref);
        LittleEndian.putInt(bArr, i + 36, this.field_7_offset);
        bArr[i + 40] = this.field_8_usage;
        bArr[i + 41] = this.field_9_name;
        bArr[i + 42] = this.field_10_unused2;
        bArr[i + 43] = this.field_11_unused3;
        EscherBlipRecord escherBlipRecord2 = this.field_12_blipRecord;
        int serialize = escherBlipRecord2 != null ? escherBlipRecord2.serialize(i + 44, bArr, new NullEscherSerializationListener()) : 0;
        byte[] bArr2 = this._remainingData;
        System.arraycopy(bArr2, 0, bArr, i + 44 + serialize, bArr2.length);
        int length = i2 + 36 + this._remainingData.length + serialize;
        int i3 = length - i;
        escherSerializationListener.afterRecordSerialize(length, getRecordId(), i3, this);
        return i3;
    }

    public int getRecordSize() {
        EscherBlipRecord escherBlipRecord = this.field_12_blipRecord;
        int i = 0;
        int recordSize = escherBlipRecord != null ? escherBlipRecord.getRecordSize() : 0;
        byte[] bArr = this._remainingData;
        if (bArr != null) {
            i = bArr.length;
        }
        return recordSize + 44 + i;
    }

    public String getRecordName() {
        return EscherRecordTypes.BSE.recordName;
    }

    public byte getBlipTypeWin32() {
        return this.field_1_blipTypeWin32;
    }

    public PictureData.PictureType getPictureTypeWin32() {
        return PictureData.PictureType.forNativeID(this.field_1_blipTypeWin32);
    }

    public void setBlipTypeWin32(byte b) {
        this.field_1_blipTypeWin32 = b;
    }

    public byte getBlipTypeMacOS() {
        return this.field_2_blipTypeMacOS;
    }

    public PictureData.PictureType getPictureTypeMacOS() {
        return PictureData.PictureType.forNativeID(this.field_2_blipTypeMacOS);
    }

    public void setBlipTypeMacOS(byte b) {
        this.field_2_blipTypeMacOS = b;
    }

    public byte[] getUid() {
        return this.field_3_uid;
    }

    public void setUid(byte[] bArr) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("uid must be byte[16]");
        }
        byte[] bArr2 = this.field_3_uid;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }

    public short getTag() {
        return this.field_4_tag;
    }

    public void setTag(short s) {
        this.field_4_tag = s;
    }

    public int getSize() {
        return this.field_5_size;
    }

    public void setSize(int i) {
        this.field_5_size = i;
    }

    public int getRef() {
        return this.field_6_ref;
    }

    public void setRef(int i) {
        this.field_6_ref = i;
    }

    public int getOffset() {
        return this.field_7_offset;
    }

    public void setOffset(int i) {
        this.field_7_offset = i;
    }

    public byte getUsage() {
        return this.field_8_usage;
    }

    public void setUsage(byte b) {
        this.field_8_usage = b;
    }

    public byte getName() {
        return this.field_9_name;
    }

    public void setName(byte b) {
        this.field_9_name = b;
    }

    public byte getUnused2() {
        return this.field_10_unused2;
    }

    public void setUnused2(byte b) {
        this.field_10_unused2 = b;
    }

    public byte getUnused3() {
        return this.field_11_unused3;
    }

    public void setUnused3(byte b) {
        this.field_11_unused3 = b;
    }

    public EscherBlipRecord getBlipRecord() {
        return this.field_12_blipRecord;
    }

    public void setBlipRecord(EscherBlipRecord escherBlipRecord) {
        this.field_12_blipRecord = escherBlipRecord;
    }

    public byte[] getRemainingData() {
        return this._remainingData;
    }

    public void setRemainingData(byte[] bArr) {
        this._remainingData = bArr == null ? new byte[0] : (byte[]) bArr.clone();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(super.getGenericProperties());
        linkedHashMap.put("blipTypeWin32", new EscherBSERecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("pictureTypeWin32", new EscherBSERecord$$ExternalSyntheticLambda11(this));
        linkedHashMap.put("blipTypeMacOS", new EscherBSERecord$$ExternalSyntheticLambda12(this));
        linkedHashMap.put("pictureTypeMacOS", new EscherBSERecord$$ExternalSyntheticLambda13(this));
        linkedHashMap.put("suid", new EscherBSERecord$$ExternalSyntheticLambda14(this));
        linkedHashMap.put("tag", new EscherBSERecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("size", new EscherBSERecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("ref", new EscherBSERecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put(TypedValues.CycleType.S_WAVE_OFFSET, new EscherBSERecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("usage", new EscherBSERecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("name", new EscherBSERecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("unused2", new EscherBSERecord$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("unused3", new EscherBSERecord$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("blipRecord", new EscherBSERecord$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("remainingData", new EscherBSERecord$$ExternalSyntheticLambda10(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.BSE;
    }

    public EscherBSERecord copy() {
        return new EscherBSERecord(this);
    }
}
