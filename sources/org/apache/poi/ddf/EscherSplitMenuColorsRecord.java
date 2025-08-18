package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

public class EscherSplitMenuColorsRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SPLIT_MENU_COLORS.typeID;
    private int field_1_color1;
    private int field_2_color2;
    private int field_3_color3;
    private int field_4_color4;

    public int getRecordSize() {
        return 24;
    }

    public EscherSplitMenuColorsRecord() {
    }

    public EscherSplitMenuColorsRecord(EscherSplitMenuColorsRecord escherSplitMenuColorsRecord) {
        super(escherSplitMenuColorsRecord);
        this.field_1_color1 = escherSplitMenuColorsRecord.field_1_color1;
        this.field_2_color2 = escherSplitMenuColorsRecord.field_2_color2;
        this.field_3_color3 = escherSplitMenuColorsRecord.field_3_color3;
        this.field_4_color4 = escherSplitMenuColorsRecord.field_4_color4;
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = i + 8;
        this.field_1_color1 = LittleEndian.getInt(bArr, i2 + 0);
        this.field_2_color2 = LittleEndian.getInt(bArr, i2 + 4);
        this.field_3_color3 = LittleEndian.getInt(bArr, i2 + 8);
        this.field_4_color4 = LittleEndian.getInt(bArr, i2 + 12);
        int i3 = readHeader - 16;
        if (i3 == 0) {
            return 24 + i3;
        }
        throw new RecordFormatException("Expecting no remaining data but got " + i3 + " byte(s).");
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        int i2 = i + 2;
        LittleEndian.putShort(bArr, i2, getRecordId());
        int i3 = i2 + 2;
        LittleEndian.putInt(bArr, i3, getRecordSize() - 8);
        int i4 = i3 + 4;
        LittleEndian.putInt(bArr, i4, this.field_1_color1);
        int i5 = i4 + 4;
        LittleEndian.putInt(bArr, i5, this.field_2_color2);
        int i6 = i5 + 4;
        LittleEndian.putInt(bArr, i6, this.field_3_color3);
        int i7 = i6 + 4;
        LittleEndian.putInt(bArr, i7, this.field_4_color4);
        int i8 = i7 + 4;
        escherSerializationListener.afterRecordSerialize(i8, getRecordId(), i8 - i, this);
        return getRecordSize();
    }

    public short getRecordId() {
        return RECORD_ID;
    }

    public String getRecordName() {
        return EscherRecordTypes.SPLIT_MENU_COLORS.recordName;
    }

    public int getColor1() {
        return this.field_1_color1;
    }

    public void setColor1(int i) {
        this.field_1_color1 = i;
    }

    public int getColor2() {
        return this.field_2_color2;
    }

    public void setColor2(int i) {
        this.field_2_color2 = i;
    }

    public int getColor3() {
        return this.field_3_color3;
    }

    public void setColor3(int i) {
        this.field_3_color3 = i;
    }

    public int getColor4() {
        return this.field_4_color4;
    }

    public void setColor4(int i) {
        this.field_4_color4 = i;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherSplitMenuColorsRecord$$ExternalSyntheticLambda0(this), "color1", new EscherSplitMenuColorsRecord$$ExternalSyntheticLambda1(this), "color2", new EscherSplitMenuColorsRecord$$ExternalSyntheticLambda2(this), "color3", new EscherSplitMenuColorsRecord$$ExternalSyntheticLambda3(this), "color4", new EscherSplitMenuColorsRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSplitMenuColorsRecord  reason: not valid java name */
    public /* synthetic */ Object m1963lambda$getGenericProperties$0$orgapachepoiddfEscherSplitMenuColorsRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.SPLIT_MENU_COLORS;
    }

    public EscherSplitMenuColorsRecord copy() {
        return new EscherSplitMenuColorsRecord(this);
    }
}
