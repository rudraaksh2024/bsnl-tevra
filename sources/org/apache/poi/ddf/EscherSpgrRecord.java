package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

public class EscherSpgrRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SPGR.typeID;
    private int field_1_rectX1;
    private int field_2_rectY1;
    private int field_3_rectX2;
    private int field_4_rectY2;

    public int getRecordSize() {
        return 24;
    }

    public EscherSpgrRecord() {
    }

    public EscherSpgrRecord(EscherSpgrRecord escherSpgrRecord) {
        super(escherSpgrRecord);
        this.field_1_rectX1 = escherSpgrRecord.field_1_rectX1;
        this.field_2_rectY1 = escherSpgrRecord.field_2_rectY1;
        this.field_3_rectX2 = escherSpgrRecord.field_3_rectX2;
        this.field_4_rectY2 = escherSpgrRecord.field_4_rectY2;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = i + 8;
        this.field_1_rectX1 = LittleEndian.getInt(bArr, i2 + 0);
        this.field_2_rectY1 = LittleEndian.getInt(bArr, i2 + 4);
        this.field_3_rectX2 = LittleEndian.getInt(bArr, i2 + 8);
        this.field_4_rectY2 = LittleEndian.getInt(bArr, i2 + 12);
        int i3 = readHeader - 16;
        if (i3 == 0) {
            return 24 + i3;
        }
        throw new RecordFormatException("Expected no remaining bytes but got " + i3);
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, 16);
        LittleEndian.putInt(bArr, i + 8, this.field_1_rectX1);
        LittleEndian.putInt(bArr, i + 12, this.field_2_rectY1);
        LittleEndian.putInt(bArr, i + 16, this.field_3_rectX2);
        LittleEndian.putInt(bArr, i + 20, this.field_4_rectY2);
        escherSerializationListener.afterRecordSerialize(getRecordSize() + i, getRecordId(), i + getRecordSize(), this);
        return 24;
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.SPGR.recordName;
    }

    public int getRectX1() {
        return this.field_1_rectX1;
    }

    public void setRectX1(int i) {
        this.field_1_rectX1 = i;
    }

    public int getRectY1() {
        return this.field_2_rectY1;
    }

    public void setRectY1(int i) {
        this.field_2_rectY1 = i;
    }

    public int getRectX2() {
        return this.field_3_rectX2;
    }

    public void setRectX2(int i) {
        this.field_3_rectX2 = i;
    }

    public int getRectY2() {
        return this.field_4_rectY2;
    }

    public void setRectY2(int i) {
        this.field_4_rectY2 = i;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherSpgrRecord$$ExternalSyntheticLambda0(this), "rectX1", new EscherSpgrRecord$$ExternalSyntheticLambda1(this), "rectY1", new EscherSpgrRecord$$ExternalSyntheticLambda2(this), "rectX2", new EscherSpgrRecord$$ExternalSyntheticLambda3(this), "rectY2", new EscherSpgrRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSpgrRecord  reason: not valid java name */
    public /* synthetic */ Object m1962lambda$getGenericProperties$0$orgapachepoiddfEscherSpgrRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.SPGR;
    }

    public EscherSpgrRecord copy() {
        return new EscherSpgrRecord(this);
    }
}
