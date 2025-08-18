package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public class EscherSpRecord extends EscherRecord {
    private static final int[] FLAGS_MASKS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
    private static final String[] FLAGS_NAMES = {"GROUP", "CHILD", "PATRIARCH", "DELETED", "OLESHAPE", "HAVEMASTER", "FLIPHORIZ", "FLIPVERT", "CONNECTOR", "HAVEANCHOR", "BACKGROUND", "HASSHAPETYPE"};
    public static final int FLAG_BACKGROUND = 1024;
    public static final int FLAG_CHILD = 2;
    public static final int FLAG_CONNECTOR = 256;
    public static final int FLAG_DELETED = 8;
    public static final int FLAG_FLIPHORIZ = 64;
    public static final int FLAG_FLIPVERT = 128;
    public static final int FLAG_GROUP = 1;
    public static final int FLAG_HASSHAPETYPE = 2048;
    public static final int FLAG_HAVEANCHOR = 512;
    public static final int FLAG_HAVEMASTER = 32;
    public static final int FLAG_OLESHAPE = 16;
    public static final int FLAG_PATRIARCH = 4;
    public static final short RECORD_ID = EscherRecordTypes.SP.typeID;
    private int field_1_shapeId;
    private int field_2_flags;

    public int getRecordSize() {
        return 16;
    }

    public EscherSpRecord() {
    }

    public EscherSpRecord(EscherSpRecord escherSpRecord) {
        super(escherSpRecord);
        this.field_1_shapeId = escherSpRecord.field_1_shapeId;
        this.field_2_flags = escherSpRecord.field_2_flags;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        readHeader(bArr, i);
        int i2 = i + 8;
        this.field_1_shapeId = LittleEndian.getInt(bArr, i2 + 0);
        this.field_2_flags = LittleEndian.getInt(bArr, i2 + 4);
        return getRecordSize();
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, 8);
        LittleEndian.putInt(bArr, i + 8, this.field_1_shapeId);
        LittleEndian.putInt(bArr, i + 12, this.field_2_flags);
        escherSerializationListener.afterRecordSerialize(i + getRecordSize(), getRecordId(), getRecordSize(), this);
        return 16;
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.SP.recordName;
    }

    private String decodeFlags(int i) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append((i & 1) != 0 ? "|GROUP" : str);
        sb.append((i & 2) != 0 ? "|CHILD" : str);
        sb.append((i & 4) != 0 ? "|PATRIARCH" : str);
        sb.append((i & 8) != 0 ? "|DELETED" : str);
        sb.append((i & 16) != 0 ? "|OLESHAPE" : str);
        sb.append((i & 32) != 0 ? "|HAVEMASTER" : str);
        sb.append((i & 64) != 0 ? "|FLIPHORIZ" : str);
        sb.append((i & 128) != 0 ? "|FLIPVERT" : str);
        sb.append((i & 256) != 0 ? "|CONNECTOR" : str);
        sb.append((i & 512) != 0 ? "|HAVEANCHOR" : str);
        sb.append((i & 1024) != 0 ? "|BACKGROUND" : str);
        if ((i & 2048) != 0) {
            str = "|HASSHAPETYPE";
        }
        sb.append(str);
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public int getShapeId() {
        return this.field_1_shapeId;
    }

    public void setShapeId(int i) {
        this.field_1_shapeId = i;
    }

    public int getFlags() {
        return this.field_2_flags;
    }

    public void setFlags(int i) {
        this.field_2_flags = i;
    }

    public short getShapeType() {
        return getInstance();
    }

    public void setShapeType(short s) {
        setInstance(s);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherSpRecord$$ExternalSyntheticLambda0(this), "shapeType", new EscherSpRecord$$ExternalSyntheticLambda1(this), "shapeId", new EscherSpRecord$$ExternalSyntheticLambda2(this), "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new EscherSpRecord$$ExternalSyntheticLambda3(this), FLAGS_MASKS, FLAGS_NAMES));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSpRecord  reason: not valid java name */
    public /* synthetic */ Object m1961lambda$getGenericProperties$0$orgapachepoiddfEscherSpRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.SP;
    }

    public EscherSpRecord copy() {
        return new EscherSpRecord(this);
    }
}
