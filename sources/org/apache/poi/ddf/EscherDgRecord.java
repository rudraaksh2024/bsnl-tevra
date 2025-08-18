package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public class EscherDgRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.DG.typeID;
    private int field_1_numShapes;
    private int field_2_lastMSOSPID;

    public int getRecordSize() {
        return 16;
    }

    public EscherDgRecord() {
    }

    public EscherDgRecord(EscherDgRecord escherDgRecord) {
        super(escherDgRecord);
        this.field_1_numShapes = escherDgRecord.field_1_numShapes;
        this.field_2_lastMSOSPID = escherDgRecord.field_2_lastMSOSPID;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        readHeader(bArr, i);
        int i2 = i + 8;
        this.field_1_numShapes = LittleEndian.getInt(bArr, i2 + 0);
        this.field_2_lastMSOSPID = LittleEndian.getInt(bArr, i2 + 4);
        return getRecordSize();
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, 8);
        LittleEndian.putInt(bArr, i + 8, this.field_1_numShapes);
        LittleEndian.putInt(bArr, i + 12, this.field_2_lastMSOSPID);
        escherSerializationListener.afterRecordSerialize(i + 16, getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.DG.recordName;
    }

    public int getNumShapes() {
        return this.field_1_numShapes;
    }

    public void setNumShapes(int i) {
        this.field_1_numShapes = i;
    }

    public int getLastMSOSPID() {
        return this.field_2_lastMSOSPID;
    }

    public void setLastMSOSPID(int i) {
        this.field_2_lastMSOSPID = i;
    }

    public short getDrawingGroupId() {
        return (short) (getOptions() >> 4);
    }

    public void incrementShapeCount() {
        this.field_1_numShapes++;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherDgRecord$$ExternalSyntheticLambda0(this), "numShapes", new EscherDgRecord$$ExternalSyntheticLambda1(this), "lastMSOSPID", new EscherDgRecord$$ExternalSyntheticLambda2(this), "drawingGroupId", new EscherDgRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherDgRecord  reason: not valid java name */
    public /* synthetic */ Object m1957lambda$getGenericProperties$0$orgapachepoiddfEscherDgRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.DG;
    }

    public EscherDgRecord copy() {
        return new EscherDgRecord(this);
    }
}
