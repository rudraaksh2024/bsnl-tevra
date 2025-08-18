package org.apache.poi.hssf.record;

import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class SharedValueRecordBase extends StandardRecord {
    private CellRangeAddress8Bit _range;

    /* access modifiers changed from: protected */
    public abstract int getExtraDataSize();

    /* access modifiers changed from: protected */
    public abstract void serializeExtraData(LittleEndianOutput littleEndianOutput);

    protected SharedValueRecordBase(SharedValueRecordBase sharedValueRecordBase) {
        super(sharedValueRecordBase);
        CellRangeAddress8Bit cellRangeAddress8Bit = sharedValueRecordBase._range;
        this._range = cellRangeAddress8Bit == null ? null : cellRangeAddress8Bit.copy();
    }

    protected SharedValueRecordBase(CellRangeAddress8Bit cellRangeAddress8Bit) {
        if (cellRangeAddress8Bit != null) {
            this._range = cellRangeAddress8Bit;
            return;
        }
        throw new IllegalArgumentException("range must be supplied.");
    }

    protected SharedValueRecordBase() {
        this(new CellRangeAddress8Bit(0, 0, 0, 0));
    }

    public SharedValueRecordBase(LittleEndianInput littleEndianInput) {
        this._range = new CellRangeAddress8Bit(littleEndianInput);
    }

    public final CellRangeAddress8Bit getRange() {
        return this._range;
    }

    public final int getFirstRow() {
        return this._range.getFirstRow();
    }

    public final int getLastRow() {
        return this._range.getLastRow();
    }

    public final int getFirstColumn() {
        return (short) this._range.getFirstColumn();
    }

    public final int getLastColumn() {
        return (short) this._range.getLastColumn();
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return getExtraDataSize() + 6;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this._range.serialize(littleEndianOutput);
        serializeExtraData(littleEndianOutput);
    }

    public final boolean isInRange(int i, int i2) {
        CellRangeAddress8Bit cellRangeAddress8Bit = this._range;
        return cellRangeAddress8Bit.getFirstRow() <= i && cellRangeAddress8Bit.getLastRow() >= i && cellRangeAddress8Bit.getFirstColumn() <= i2 && cellRangeAddress8Bit.getLastColumn() >= i2;
    }

    public final boolean isFirstCell(int i, int i2) {
        CellRangeAddress8Bit range = getRange();
        return range.getFirstRow() == i && range.getFirstColumn() == i2;
    }
}
