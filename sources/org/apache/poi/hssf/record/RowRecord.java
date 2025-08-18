package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class RowRecord extends StandardRecord {
    public static final int ENCODED_SIZE = 20;
    private static final int OPTION_BITS_ALWAYS_SET = 256;
    private static final BitField badFontHeight = BitFieldFactory.getInstance(64);
    private static final BitField bottomBorder = BitFieldFactory.getInstance(8192);
    private static final BitField collapsed = BitFieldFactory.getInstance(16);
    private static final BitField formatted = BitFieldFactory.getInstance(128);
    private static final BitField outlineLevel = BitFieldFactory.getInstance(7);
    private static final BitField phoneticGuide = BitFieldFactory.getInstance(16384);
    public static final short sid = 520;
    private static final BitField topBorder = BitFieldFactory.getInstance(4096);
    private static final BitField xfIndex = BitFieldFactory.getInstance(4095);
    private static final BitField zeroHeight = BitFieldFactory.getInstance(32);
    private int field_1_row_number;
    private int field_2_first_col;
    private int field_3_last_col;
    private short field_4_height;
    private short field_5_optimize;
    private short field_6_reserved;
    private int field_7_option_flags;
    private int field_8_option_flags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 16;
    }

    public short getSid() {
        return sid;
    }

    public RowRecord(RowRecord rowRecord) {
        super(rowRecord);
        this.field_1_row_number = rowRecord.field_1_row_number;
        this.field_2_first_col = rowRecord.field_2_first_col;
        this.field_3_last_col = rowRecord.field_3_last_col;
        this.field_4_height = rowRecord.field_4_height;
        this.field_5_optimize = rowRecord.field_5_optimize;
        this.field_6_reserved = rowRecord.field_6_reserved;
        this.field_7_option_flags = rowRecord.field_7_option_flags;
        this.field_8_option_flags = rowRecord.field_8_option_flags;
    }

    public RowRecord(int i) {
        if (i >= 0) {
            this.field_1_row_number = i;
            this.field_4_height = 255;
            this.field_5_optimize = 0;
            this.field_6_reserved = 0;
            this.field_7_option_flags = 256;
            this.field_8_option_flags = 15;
            setEmpty();
            return;
        }
        throw new IllegalArgumentException("Invalid row number (" + i + ")");
    }

    public RowRecord(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        this.field_1_row_number = readUShort;
        if (readUShort >= 0) {
            this.field_2_first_col = recordInputStream.readShort();
            this.field_3_last_col = recordInputStream.readShort();
            this.field_4_height = recordInputStream.readShort();
            this.field_5_optimize = recordInputStream.readShort();
            this.field_6_reserved = recordInputStream.readShort();
            this.field_7_option_flags = recordInputStream.readShort();
            this.field_8_option_flags = recordInputStream.readShort();
            return;
        }
        throw new IllegalArgumentException("Invalid row number " + this.field_1_row_number + " found in InputStream");
    }

    public void setEmpty() {
        this.field_2_first_col = 0;
        this.field_3_last_col = 0;
    }

    public boolean isEmpty() {
        return (this.field_3_last_col | this.field_2_first_col) == 0;
    }

    public void setRowNumber(int i) {
        this.field_1_row_number = i;
    }

    public void setFirstCol(int i) {
        this.field_2_first_col = i;
    }

    public void setLastCol(int i) {
        this.field_3_last_col = i;
    }

    public void setHeight(short s) {
        this.field_4_height = s;
    }

    public void setOptimize(short s) {
        this.field_5_optimize = s;
    }

    public void setOutlineLevel(short s) {
        this.field_7_option_flags = outlineLevel.setValue(this.field_7_option_flags, s);
    }

    public void setColapsed(boolean z) {
        this.field_7_option_flags = collapsed.setBoolean(this.field_7_option_flags, z);
    }

    public void setZeroHeight(boolean z) {
        this.field_7_option_flags = zeroHeight.setBoolean(this.field_7_option_flags, z);
    }

    public void setBadFontHeight(boolean z) {
        this.field_7_option_flags = badFontHeight.setBoolean(this.field_7_option_flags, z);
    }

    public void setFormatted(boolean z) {
        this.field_7_option_flags = formatted.setBoolean(this.field_7_option_flags, z);
    }

    public void setXFIndex(short s) {
        this.field_8_option_flags = xfIndex.setValue(this.field_8_option_flags, s);
    }

    public void setTopBorder(boolean z) {
        this.field_8_option_flags = topBorder.setBoolean(this.field_8_option_flags, z);
    }

    public void setBottomBorder(boolean z) {
        this.field_8_option_flags = bottomBorder.setBoolean(this.field_8_option_flags, z);
    }

    public void setPhoeneticGuide(boolean z) {
        this.field_8_option_flags = phoneticGuide.setBoolean(this.field_8_option_flags, z);
    }

    public int getRowNumber() {
        return this.field_1_row_number;
    }

    public int getFirstCol() {
        return this.field_2_first_col;
    }

    public int getLastCol() {
        return this.field_3_last_col;
    }

    public short getHeight() {
        return this.field_4_height;
    }

    public short getOptimize() {
        return this.field_5_optimize;
    }

    public short getOptionFlags() {
        return (short) this.field_7_option_flags;
    }

    public short getOutlineLevel() {
        return (short) outlineLevel.getValue(this.field_7_option_flags);
    }

    public boolean getColapsed() {
        return collapsed.isSet(this.field_7_option_flags);
    }

    public boolean getZeroHeight() {
        return zeroHeight.isSet(this.field_7_option_flags);
    }

    public boolean getBadFontHeight() {
        return badFontHeight.isSet(this.field_7_option_flags);
    }

    public boolean getFormatted() {
        return formatted.isSet(this.field_7_option_flags);
    }

    public short getOptionFlags2() {
        return (short) this.field_8_option_flags;
    }

    public short getXFIndex() {
        return xfIndex.getShortValue((short) this.field_8_option_flags);
    }

    public boolean getTopBorder() {
        return topBorder.isSet(this.field_8_option_flags);
    }

    public boolean getBottomBorder() {
        return bottomBorder.isSet(this.field_8_option_flags);
    }

    public boolean getPhoeneticGuide() {
        return phoneticGuide.isSet(this.field_8_option_flags);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getRowNumber());
        int i = 0;
        littleEndianOutput.writeShort(getFirstCol() == -1 ? 0 : getFirstCol());
        if (getLastCol() != -1) {
            i = getLastCol();
        }
        littleEndianOutput.writeShort(i);
        littleEndianOutput.writeShort(getHeight());
        littleEndianOutput.writeShort(getOptimize());
        littleEndianOutput.writeShort(this.field_6_reserved);
        littleEndianOutput.writeShort(getOptionFlags());
        littleEndianOutput.writeShort(getOptionFlags2());
    }

    public RowRecord copy() {
        return new RowRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ROW;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("rowNumber", new RowRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("firstCol", new RowRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("lastCol", new RowRecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("height", new RowRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("optimized", new RowRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("reserved", new RowRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new RowRecord$$ExternalSyntheticLambda6(this), new BitField[]{collapsed, zeroHeight, badFontHeight, formatted}, new String[]{"COLAPSED", "ZERO_HEIGHT", "BAD_FONT_HEIGHT", "FORMATTED"}));
        linkedHashMap.put("outlineLevel", new RowRecord$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("optionFlags2", GenericRecordUtil.getBitsAsString((Supplier<Number>) new RowRecord$$ExternalSyntheticLambda8(this), new BitField[]{topBorder, bottomBorder, phoneticGuide}, new String[]{"TOP_BORDER", "BOTTOM_BORDER", "PHOENETIC_GUIDE"}));
        linkedHashMap.put("xfIndex", new RowRecord$$ExternalSyntheticLambda9(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RowRecord  reason: not valid java name */
    public /* synthetic */ Object m2083lambda$getGenericProperties$0$orgapachepoihssfrecordRowRecord() {
        return Short.valueOf(this.field_6_reserved);
    }
}
