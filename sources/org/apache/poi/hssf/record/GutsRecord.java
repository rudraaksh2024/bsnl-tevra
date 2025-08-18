package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class GutsRecord extends StandardRecord {
    public static final short sid = 128;
    private short field_1_left_row_gutter;
    private short field_2_top_col_gutter;
    private short field_3_row_level_max;
    private short field_4_col_level_max;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 8;
    }

    public short getSid() {
        return 128;
    }

    public GutsRecord() {
    }

    public GutsRecord(GutsRecord gutsRecord) {
        super(gutsRecord);
        this.field_1_left_row_gutter = gutsRecord.field_1_left_row_gutter;
        this.field_2_top_col_gutter = gutsRecord.field_2_top_col_gutter;
        this.field_3_row_level_max = gutsRecord.field_3_row_level_max;
        this.field_4_col_level_max = gutsRecord.field_4_col_level_max;
    }

    public GutsRecord(RecordInputStream recordInputStream) {
        this.field_1_left_row_gutter = recordInputStream.readShort();
        this.field_2_top_col_gutter = recordInputStream.readShort();
        this.field_3_row_level_max = recordInputStream.readShort();
        this.field_4_col_level_max = recordInputStream.readShort();
    }

    public void setLeftRowGutter(short s) {
        this.field_1_left_row_gutter = s;
    }

    public void setTopColGutter(short s) {
        this.field_2_top_col_gutter = s;
    }

    public void setRowLevelMax(short s) {
        this.field_3_row_level_max = s;
    }

    public void setColLevelMax(short s) {
        this.field_4_col_level_max = s;
    }

    public short getLeftRowGutter() {
        return this.field_1_left_row_gutter;
    }

    public short getTopColGutter() {
        return this.field_2_top_col_gutter;
    }

    public short getRowLevelMax() {
        return this.field_3_row_level_max;
    }

    public short getColLevelMax() {
        return this.field_4_col_level_max;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getLeftRowGutter());
        littleEndianOutput.writeShort(getTopColGutter());
        littleEndianOutput.writeShort(getRowLevelMax());
        littleEndianOutput.writeShort(getColLevelMax());
    }

    public GutsRecord copy() {
        return new GutsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.GUTS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("leftGutter", new GutsRecord$$ExternalSyntheticLambda0(this), "topGutter", new GutsRecord$$ExternalSyntheticLambda1(this), "rowLevelMax", new GutsRecord$$ExternalSyntheticLambda2(this), "colLevelMax", new GutsRecord$$ExternalSyntheticLambda3(this));
    }
}
