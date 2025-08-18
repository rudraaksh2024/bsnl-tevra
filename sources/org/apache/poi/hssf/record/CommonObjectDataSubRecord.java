package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class CommonObjectDataSubRecord extends SubRecord {
    public static final short OBJECT_TYPE_ARC = 4;
    public static final short OBJECT_TYPE_BUTTON = 7;
    public static final short OBJECT_TYPE_CHART = 5;
    public static final short OBJECT_TYPE_CHECKBOX = 11;
    public static final short OBJECT_TYPE_COMBO_BOX = 20;
    public static final short OBJECT_TYPE_COMMENT = 25;
    public static final short OBJECT_TYPE_DIALOG_BOX = 15;
    public static final short OBJECT_TYPE_EDIT_BOX = 13;
    public static final short OBJECT_TYPE_GROUP = 0;
    public static final short OBJECT_TYPE_GROUP_BOX = 19;
    public static final short OBJECT_TYPE_LABEL = 14;
    public static final short OBJECT_TYPE_LINE = 1;
    public static final short OBJECT_TYPE_LIST_BOX = 18;
    public static final short OBJECT_TYPE_MICROSOFT_OFFICE_DRAWING = 30;
    public static final short OBJECT_TYPE_OPTION_BUTTON = 12;
    public static final short OBJECT_TYPE_OVAL = 3;
    public static final short OBJECT_TYPE_PICTURE = 8;
    public static final short OBJECT_TYPE_POLYGON = 9;
    public static final short OBJECT_TYPE_RECTANGLE = 2;
    public static final short OBJECT_TYPE_RESERVED1 = 10;
    public static final short OBJECT_TYPE_RESERVED2 = 21;
    public static final short OBJECT_TYPE_RESERVED3 = 22;
    public static final short OBJECT_TYPE_RESERVED4 = 23;
    public static final short OBJECT_TYPE_RESERVED5 = 24;
    public static final short OBJECT_TYPE_RESERVED6 = 26;
    public static final short OBJECT_TYPE_RESERVED7 = 27;
    public static final short OBJECT_TYPE_RESERVED8 = 28;
    public static final short OBJECT_TYPE_RESERVED9 = 29;
    public static final short OBJECT_TYPE_SCROLL_BAR = 17;
    public static final short OBJECT_TYPE_SPINNER = 16;
    public static final short OBJECT_TYPE_TEXT = 6;
    private static final BitField autofill = BitFieldFactory.getInstance(8192);
    private static final BitField autoline = BitFieldFactory.getInstance(16384);
    private static final BitField locked = BitFieldFactory.getInstance(1);
    private static final BitField printable = BitFieldFactory.getInstance(16);
    public static final short sid = 21;
    private short field_1_objectType;
    private int field_2_objectId;
    private short field_3_option;
    private int field_4_reserved1;
    private int field_5_reserved2;
    private int field_6_reserved3;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return 21;
    }

    public CommonObjectDataSubRecord() {
    }

    public CommonObjectDataSubRecord(CommonObjectDataSubRecord commonObjectDataSubRecord) {
        super(commonObjectDataSubRecord);
        this.field_1_objectType = commonObjectDataSubRecord.field_1_objectType;
        this.field_2_objectId = commonObjectDataSubRecord.field_2_objectId;
        this.field_3_option = commonObjectDataSubRecord.field_3_option;
        this.field_4_reserved1 = commonObjectDataSubRecord.field_4_reserved1;
        this.field_5_reserved2 = commonObjectDataSubRecord.field_5_reserved2;
        this.field_6_reserved3 = commonObjectDataSubRecord.field_6_reserved3;
    }

    public CommonObjectDataSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    CommonObjectDataSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if (i == 18) {
            this.field_1_objectType = littleEndianInput.readShort();
            this.field_2_objectId = littleEndianInput.readUShort();
            this.field_3_option = littleEndianInput.readShort();
            this.field_4_reserved1 = littleEndianInput.readInt();
            this.field_5_reserved2 = littleEndianInput.readInt();
            this.field_6_reserved3 = littleEndianInput.readInt();
            return;
        }
        throw new RecordFormatException("Expected size 18 but got (" + i + ")");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(21);
        littleEndianOutput.writeShort(getDataSize());
        littleEndianOutput.writeShort(this.field_1_objectType);
        littleEndianOutput.writeShort(this.field_2_objectId);
        littleEndianOutput.writeShort(this.field_3_option);
        littleEndianOutput.writeInt(this.field_4_reserved1);
        littleEndianOutput.writeInt(this.field_5_reserved2);
        littleEndianOutput.writeInt(this.field_6_reserved3);
    }

    public CommonObjectDataSubRecord copy() {
        return new CommonObjectDataSubRecord(this);
    }

    public short getObjectType() {
        return this.field_1_objectType;
    }

    public void setObjectType(short s) {
        this.field_1_objectType = s;
    }

    public int getObjectId() {
        return this.field_2_objectId;
    }

    public void setObjectId(int i) {
        this.field_2_objectId = i;
    }

    public short getOption() {
        return this.field_3_option;
    }

    public void setOption(short s) {
        this.field_3_option = s;
    }

    public int getReserved1() {
        return this.field_4_reserved1;
    }

    public void setReserved1(int i) {
        this.field_4_reserved1 = i;
    }

    public int getReserved2() {
        return this.field_5_reserved2;
    }

    public void setReserved2(int i) {
        this.field_5_reserved2 = i;
    }

    public int getReserved3() {
        return this.field_6_reserved3;
    }

    public void setReserved3(int i) {
        this.field_6_reserved3 = i;
    }

    public void setLocked(boolean z) {
        this.field_3_option = locked.setShortBoolean(this.field_3_option, z);
    }

    public boolean isLocked() {
        return locked.isSet(this.field_3_option);
    }

    public void setPrintable(boolean z) {
        this.field_3_option = printable.setShortBoolean(this.field_3_option, z);
    }

    public boolean isPrintable() {
        return printable.isSet(this.field_3_option);
    }

    public void setAutofill(boolean z) {
        this.field_3_option = autofill.setShortBoolean(this.field_3_option, z);
    }

    public boolean isAutofill() {
        return autofill.isSet(this.field_3_option);
    }

    public void setAutoline(boolean z) {
        this.field_3_option = autoline.setShortBoolean(this.field_3_option, z);
    }

    public boolean isAutoline() {
        return autoline.isSet(this.field_3_option);
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.COMMON_OBJECT_DATA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("objectType", new CommonObjectDataSubRecord$$ExternalSyntheticLambda0(this), "objectId", new CommonObjectDataSubRecord$$ExternalSyntheticLambda1(this), "option", GenericRecordUtil.getBitsAsString((Supplier<Number>) new CommonObjectDataSubRecord$$ExternalSyntheticLambda2(this), new BitField[]{locked, printable, autofill, autoline}, new String[]{"LOCKED", "PRINTABLE", "AUTOFILL", "AUTOLINE"}), "reserved1", new CommonObjectDataSubRecord$$ExternalSyntheticLambda3(this), "reserved2", new CommonObjectDataSubRecord$$ExternalSyntheticLambda4(this), "reserved3", new CommonObjectDataSubRecord$$ExternalSyntheticLambda5(this));
    }
}
