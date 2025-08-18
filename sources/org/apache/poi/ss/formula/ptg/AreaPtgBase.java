package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class AreaPtgBase extends OperandPtg implements AreaI {
    private static final BitField colRelative = BitFieldFactory.getInstance(16384);
    private static final BitField columnMask = BitFieldFactory.getInstance(16383);
    private static final BitField rowRelative = BitFieldFactory.getInstance(32768);
    private int field_1_first_row;
    private int field_2_last_row;
    private int field_3_first_column;
    private int field_4_last_column;

    public byte getDefaultOperandClass() {
        return 0;
    }

    protected AreaPtgBase() {
    }

    protected AreaPtgBase(AreaPtgBase areaPtgBase) {
        super(areaPtgBase);
        this.field_1_first_row = areaPtgBase.field_1_first_row;
        this.field_2_last_row = areaPtgBase.field_2_last_row;
        this.field_3_first_column = areaPtgBase.field_3_first_column;
        this.field_4_last_column = areaPtgBase.field_4_last_column;
    }

    protected AreaPtgBase(AreaReference areaReference) {
        CellReference firstCell = areaReference.getFirstCell();
        CellReference lastCell = areaReference.getLastCell();
        setFirstRow(firstCell.getRow());
        setFirstColumn(firstCell.getCol() == -1 ? 0 : firstCell.getCol());
        setLastRow(lastCell.getRow());
        setLastColumn(lastCell.getCol() == -1 ? 255 : lastCell.getCol());
        setFirstColRelative(!firstCell.isColAbsolute());
        setLastColRelative(!lastCell.isColAbsolute());
        setFirstRowRelative(!firstCell.isRowAbsolute());
        setLastRowRelative(!lastCell.isRowAbsolute());
    }

    protected AreaPtgBase(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i2 >= i) {
            setFirstRow(i);
            setLastRow(i2);
            setFirstRowRelative(z);
            setLastRowRelative(z2);
        } else {
            setFirstRow(i2);
            setLastRow(i);
            setFirstRowRelative(z2);
            setLastRowRelative(z);
        }
        if (i4 >= i3) {
            setFirstColumn(i3);
            setLastColumn(i4);
            setFirstColRelative(z3);
            setLastColRelative(z4);
            return;
        }
        setFirstColumn(i4);
        setLastColumn(i3);
        setFirstColRelative(z4);
        setLastColRelative(z3);
    }

    public void sortTopLeftToBottomRight() {
        if (getFirstRow() > getLastRow()) {
            int firstRow = getFirstRow();
            boolean isFirstRowRelative = isFirstRowRelative();
            setFirstRow(getLastRow());
            setFirstRowRelative(isLastRowRelative());
            setLastRow(firstRow);
            setLastRowRelative(isFirstRowRelative);
        }
        if (getFirstColumn() > getLastColumn()) {
            int firstColumn = getFirstColumn();
            boolean isFirstColRelative = isFirstColRelative();
            setFirstColumn(getLastColumn());
            setFirstColRelative(isLastColRelative());
            setLastColumn(firstColumn);
            setLastColRelative(isFirstColRelative);
        }
    }

    /* access modifiers changed from: protected */
    public final void readCoordinates(LittleEndianInput littleEndianInput) {
        this.field_1_first_row = littleEndianInput.readUShort();
        this.field_2_last_row = littleEndianInput.readUShort();
        this.field_3_first_column = littleEndianInput.readUShort();
        this.field_4_last_column = littleEndianInput.readUShort();
    }

    /* access modifiers changed from: protected */
    public final void writeCoordinates(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_first_row);
        littleEndianOutput.writeShort(this.field_2_last_row);
        littleEndianOutput.writeShort(this.field_3_first_column);
        littleEndianOutput.writeShort(this.field_4_last_column);
    }

    public final int getFirstRow() {
        return this.field_1_first_row;
    }

    public final void setFirstRow(int i) {
        this.field_1_first_row = i;
    }

    public final int getLastRow() {
        return this.field_2_last_row;
    }

    public final void setLastRow(int i) {
        this.field_2_last_row = i;
    }

    public final int getFirstColumn() {
        return columnMask.getValue(this.field_3_first_column);
    }

    public final short getFirstColumnRaw() {
        return (short) this.field_3_first_column;
    }

    public final boolean isFirstRowRelative() {
        return rowRelative.isSet(this.field_3_first_column);
    }

    public final void setFirstRowRelative(boolean z) {
        this.field_3_first_column = rowRelative.setBoolean(this.field_3_first_column, z);
    }

    public final boolean isFirstColRelative() {
        return colRelative.isSet(this.field_3_first_column);
    }

    public final void setFirstColRelative(boolean z) {
        this.field_3_first_column = colRelative.setBoolean(this.field_3_first_column, z);
    }

    public final void setFirstColumn(int i) {
        this.field_3_first_column = columnMask.setValue(this.field_3_first_column, i);
    }

    public final void setFirstColumnRaw(int i) {
        this.field_3_first_column = i;
    }

    public final int getLastColumn() {
        return columnMask.getValue(this.field_4_last_column);
    }

    public final short getLastColumnRaw() {
        return (short) this.field_4_last_column;
    }

    public final boolean isLastRowRelative() {
        return rowRelative.isSet(this.field_4_last_column);
    }

    public final void setLastRowRelative(boolean z) {
        this.field_4_last_column = rowRelative.setBoolean(this.field_4_last_column, z);
    }

    public final boolean isLastColRelative() {
        return colRelative.isSet(this.field_4_last_column);
    }

    public final void setLastColRelative(boolean z) {
        this.field_4_last_column = colRelative.setBoolean(this.field_4_last_column, z);
    }

    public final void setLastColumn(int i) {
        this.field_4_last_column = columnMask.setValue(this.field_4_last_column, i);
    }

    public final void setLastColumnRaw(short s) {
        this.field_4_last_column = s;
    }

    /* access modifiers changed from: protected */
    public final String formatReferenceAsString() {
        CellReference cellReference = new CellReference(getFirstRow(), getFirstColumn(), !isFirstRowRelative(), !isFirstColRelative());
        CellReference cellReference2 = new CellReference(getLastRow(), getLastColumn(), !isLastRowRelative(), !isLastColRelative());
        if (AreaReference.isWholeColumnReference(SpreadsheetVersion.EXCEL97, cellReference, cellReference2)) {
            return new AreaReference(cellReference, cellReference2, SpreadsheetVersion.EXCEL97).formatAsString();
        }
        if (AreaReference.isWholeColumnReference(SpreadsheetVersion.EXCEL2007, cellReference, cellReference2)) {
            return new AreaReference(cellReference, cellReference2, SpreadsheetVersion.EXCEL2007).formatAsString();
        }
        return cellReference.formatAsString() + ParameterizedMessage.ERROR_MSG_SEPARATOR + cellReference2.formatAsString();
    }

    public String toFormulaString() {
        return formatReferenceAsString();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        AreaPtgBase$$ExternalSyntheticLambda0 areaPtgBase$$ExternalSyntheticLambda0 = r3;
        AreaPtgBase$$ExternalSyntheticLambda0 areaPtgBase$$ExternalSyntheticLambda02 = new AreaPtgBase$$ExternalSyntheticLambda0(this);
        AreaPtgBase$$ExternalSyntheticLambda1 areaPtgBase$$ExternalSyntheticLambda1 = r5;
        AreaPtgBase$$ExternalSyntheticLambda1 areaPtgBase$$ExternalSyntheticLambda12 = new AreaPtgBase$$ExternalSyntheticLambda1(this);
        AreaPtgBase$$ExternalSyntheticLambda2 areaPtgBase$$ExternalSyntheticLambda2 = r7;
        AreaPtgBase$$ExternalSyntheticLambda2 areaPtgBase$$ExternalSyntheticLambda22 = new AreaPtgBase$$ExternalSyntheticLambda2(this);
        AreaPtgBase$$ExternalSyntheticLambda3 areaPtgBase$$ExternalSyntheticLambda3 = r9;
        AreaPtgBase$$ExternalSyntheticLambda3 areaPtgBase$$ExternalSyntheticLambda32 = new AreaPtgBase$$ExternalSyntheticLambda3(this);
        AreaPtgBase$$ExternalSyntheticLambda4 areaPtgBase$$ExternalSyntheticLambda4 = r11;
        AreaPtgBase$$ExternalSyntheticLambda4 areaPtgBase$$ExternalSyntheticLambda42 = new AreaPtgBase$$ExternalSyntheticLambda4(this);
        AreaPtgBase$$ExternalSyntheticLambda5 areaPtgBase$$ExternalSyntheticLambda5 = r13;
        AreaPtgBase$$ExternalSyntheticLambda5 areaPtgBase$$ExternalSyntheticLambda52 = new AreaPtgBase$$ExternalSyntheticLambda5(this);
        AreaPtgBase$$ExternalSyntheticLambda6 areaPtgBase$$ExternalSyntheticLambda6 = r15;
        AreaPtgBase$$ExternalSyntheticLambda6 areaPtgBase$$ExternalSyntheticLambda62 = new AreaPtgBase$$ExternalSyntheticLambda6(this);
        AreaPtgBase$$ExternalSyntheticLambda7 areaPtgBase$$ExternalSyntheticLambda7 = r1;
        AreaPtgBase$$ExternalSyntheticLambda7 areaPtgBase$$ExternalSyntheticLambda72 = new AreaPtgBase$$ExternalSyntheticLambda7(this);
        AreaPtgBase$$ExternalSyntheticLambda8 areaPtgBase$$ExternalSyntheticLambda8 = r1;
        AreaPtgBase$$ExternalSyntheticLambda8 areaPtgBase$$ExternalSyntheticLambda82 = new AreaPtgBase$$ExternalSyntheticLambda8(this);
        return GenericRecordUtil.getGenericProperties("firstRow", areaPtgBase$$ExternalSyntheticLambda0, "firstRowRelative", areaPtgBase$$ExternalSyntheticLambda1, "firstColumn", areaPtgBase$$ExternalSyntheticLambda2, "firstColRelative", areaPtgBase$$ExternalSyntheticLambda3, "lastRow", areaPtgBase$$ExternalSyntheticLambda4, "lastRowRelative", areaPtgBase$$ExternalSyntheticLambda5, "lastColumn", areaPtgBase$$ExternalSyntheticLambda6, "lastColRelative", areaPtgBase$$ExternalSyntheticLambda7, "formatReference", areaPtgBase$$ExternalSyntheticLambda8);
    }
}
