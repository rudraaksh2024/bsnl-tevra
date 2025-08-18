package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class BlankRecord extends StandardRecord implements CellValueRecordInterface {
    public static final short sid = 513;
    private int field_1_row;
    private short field_2_col;
    private short field_3_xf;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 6;
    }

    public short getSid() {
        return sid;
    }

    public BlankRecord() {
    }

    public BlankRecord(BlankRecord blankRecord) {
        super(blankRecord);
        this.field_1_row = blankRecord.field_1_row;
        this.field_2_col = blankRecord.field_2_col;
        this.field_3_xf = blankRecord.field_3_xf;
    }

    public BlankRecord(RecordInputStream recordInputStream) {
        this.field_1_row = recordInputStream.readUShort();
        this.field_2_col = recordInputStream.readShort();
        this.field_3_xf = recordInputStream.readShort();
    }

    public void setRow(int i) {
        this.field_1_row = i;
    }

    public int getRow() {
        return this.field_1_row;
    }

    public short getColumn() {
        return this.field_2_col;
    }

    public void setXFIndex(short s) {
        this.field_3_xf = s;
    }

    public short getXFIndex() {
        return this.field_3_xf;
    }

    public void setColumn(short s) {
        this.field_2_col = s;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getRow());
        littleEndianOutput.writeShort(getColumn());
        littleEndianOutput.writeShort(getXFIndex());
    }

    public BlankRecord copy() {
        return new BlankRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BLANK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new BlankRecord$$ExternalSyntheticLambda0(this), "col", new BlankRecord$$ExternalSyntheticLambda1(this), "xfIndex", new BlankRecord$$ExternalSyntheticLambda2(this));
    }
}
