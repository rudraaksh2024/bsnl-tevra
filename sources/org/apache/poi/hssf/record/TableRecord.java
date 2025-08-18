package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class TableRecord extends SharedValueRecordBase {
    private static final BitField alwaysCalc = BitFieldFactory.getInstance(1);
    private static final BitField calcOnOpen = BitFieldFactory.getInstance(2);
    private static final BitField colDeleted = BitFieldFactory.getInstance(32);
    private static final BitField oneOrTwoVar = BitFieldFactory.getInstance(8);
    private static final BitField rowDeleted = BitFieldFactory.getInstance(16);
    private static final BitField rowOrColInpCell = BitFieldFactory.getInstance(4);
    public static final short sid = 566;
    private int field_10_colInputCol;
    private int field_5_flags;
    private int field_6_res;
    private int field_7_rowInputRow;
    private int field_8_colInputRow;
    private int field_9_rowInputCol;

    /* access modifiers changed from: protected */
    public int getExtraDataSize() {
        return 10;
    }

    public short getSid() {
        return sid;
    }

    public TableRecord(TableRecord tableRecord) {
        super((SharedValueRecordBase) tableRecord);
        this.field_5_flags = tableRecord.field_5_flags;
        this.field_6_res = tableRecord.field_6_res;
        this.field_7_rowInputRow = tableRecord.field_7_rowInputRow;
        this.field_8_colInputRow = tableRecord.field_8_colInputRow;
        this.field_9_rowInputCol = tableRecord.field_9_rowInputCol;
        this.field_10_colInputCol = tableRecord.field_10_colInputCol;
    }

    public TableRecord(RecordInputStream recordInputStream) {
        super((LittleEndianInput) recordInputStream);
        this.field_5_flags = recordInputStream.readByte();
        this.field_6_res = recordInputStream.readByte();
        this.field_7_rowInputRow = recordInputStream.readShort();
        this.field_8_colInputRow = recordInputStream.readShort();
        this.field_9_rowInputCol = recordInputStream.readShort();
        this.field_10_colInputCol = recordInputStream.readShort();
    }

    public TableRecord(CellRangeAddress8Bit cellRangeAddress8Bit) {
        super(cellRangeAddress8Bit);
        this.field_6_res = 0;
    }

    public int getFlags() {
        return this.field_5_flags;
    }

    public void setFlags(int i) {
        this.field_5_flags = i;
    }

    public int getRowInputRow() {
        return this.field_7_rowInputRow;
    }

    public void setRowInputRow(int i) {
        this.field_7_rowInputRow = i;
    }

    public int getColInputRow() {
        return this.field_8_colInputRow;
    }

    public void setColInputRow(int i) {
        this.field_8_colInputRow = i;
    }

    public int getRowInputCol() {
        return this.field_9_rowInputCol;
    }

    public void setRowInputCol(int i) {
        this.field_9_rowInputCol = i;
    }

    public int getColInputCol() {
        return this.field_10_colInputCol;
    }

    public void setColInputCol(int i) {
        this.field_10_colInputCol = i;
    }

    public boolean isAlwaysCalc() {
        return alwaysCalc.isSet(this.field_5_flags);
    }

    public void setAlwaysCalc(boolean z) {
        this.field_5_flags = alwaysCalc.setBoolean(this.field_5_flags, z);
    }

    public boolean isRowOrColInpCell() {
        return rowOrColInpCell.isSet(this.field_5_flags);
    }

    public void setRowOrColInpCell(boolean z) {
        this.field_5_flags = rowOrColInpCell.setBoolean(this.field_5_flags, z);
    }

    public boolean isOneNotTwoVar() {
        return oneOrTwoVar.isSet(this.field_5_flags);
    }

    public void setOneNotTwoVar(boolean z) {
        this.field_5_flags = oneOrTwoVar.setBoolean(this.field_5_flags, z);
    }

    public boolean isColDeleted() {
        return colDeleted.isSet(this.field_5_flags);
    }

    public void setColDeleted(boolean z) {
        this.field_5_flags = colDeleted.setBoolean(this.field_5_flags, z);
    }

    public boolean isRowDeleted() {
        return rowDeleted.isSet(this.field_5_flags);
    }

    public void setRowDeleted(boolean z) {
        this.field_5_flags = rowDeleted.setBoolean(this.field_5_flags, z);
    }

    /* access modifiers changed from: protected */
    public void serializeExtraData(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this.field_5_flags);
        littleEndianOutput.writeByte(this.field_6_res);
        littleEndianOutput.writeShort(this.field_7_rowInputRow);
        littleEndianOutput.writeShort(this.field_8_colInputRow);
        littleEndianOutput.writeShort(this.field_9_rowInputCol);
        littleEndianOutput.writeShort(this.field_10_colInputCol);
    }

    public TableRecord copy() {
        return new TableRecord(this);
    }

    private static CellReference cr(int i, int i2) {
        int i3 = i2 & 255;
        boolean z = true;
        boolean z2 = (32768 & i2) == 0;
        if ((i2 & 16384) != 0) {
            z = false;
        }
        return new CellReference(i, i3, z2, z);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.TABLE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new TableRecord$$ExternalSyntheticLambda0(this), "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new TableRecord$$ExternalSyntheticLambda1(this), new BitField[]{alwaysCalc, calcOnOpen, rowOrColInpCell, oneOrTwoVar, rowDeleted, colDeleted}, new String[]{"ALWAYS_CALC", "CALC_ON_OPEN", "ROW_OR_COL_INP_CELL", "ONE_OR_TWO_VAR", "ROW_DELETED", "COL_DELETED"}), "reserved", new TableRecord$$ExternalSyntheticLambda2(this), "rowInput", new TableRecord$$ExternalSyntheticLambda3(this), "colInput", new TableRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-TableRecord  reason: not valid java name */
    public /* synthetic */ Object m2096lambda$getGenericProperties$0$orgapachepoihssfrecordTableRecord() {
        return Integer.valueOf(this.field_6_res);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-TableRecord  reason: not valid java name */
    public /* synthetic */ Object m2097lambda$getGenericProperties$1$orgapachepoihssfrecordTableRecord() {
        return cr(this.field_7_rowInputRow, this.field_8_colInputRow);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-TableRecord  reason: not valid java name */
    public /* synthetic */ Object m2098lambda$getGenericProperties$2$orgapachepoihssfrecordTableRecord() {
        return cr(this.field_9_rowInputCol, this.field_10_colInputCol);
    }
}
