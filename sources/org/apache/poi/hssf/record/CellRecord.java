package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public abstract class CellRecord extends StandardRecord implements CellValueRecordInterface {
    private int _columnIndex;
    private int _formatIndex;
    private int _rowIndex;

    public abstract CellRecord copy();

    /* access modifiers changed from: protected */
    public abstract String getRecordName();

    /* access modifiers changed from: protected */
    public abstract int getValueDataSize();

    /* access modifiers changed from: protected */
    public abstract void serializeValue(LittleEndianOutput littleEndianOutput);

    protected CellRecord() {
    }

    protected CellRecord(CellRecord cellRecord) {
        super(cellRecord);
        this._rowIndex = cellRecord.getRow();
        this._columnIndex = cellRecord.getColumn();
        this._formatIndex = cellRecord.getXFIndex();
    }

    protected CellRecord(RecordInputStream recordInputStream) {
        this._rowIndex = recordInputStream.readUShort();
        this._columnIndex = recordInputStream.readUShort();
        this._formatIndex = recordInputStream.readUShort();
    }

    public final void setRow(int i) {
        this._rowIndex = i;
    }

    public final void setColumn(short s) {
        this._columnIndex = s;
    }

    public final void setXFIndex(short s) {
        this._formatIndex = s;
    }

    public final int getRow() {
        return this._rowIndex;
    }

    public final short getColumn() {
        return (short) this._columnIndex;
    }

    public final short getXFIndex() {
        return (short) this._formatIndex;
    }

    public final void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getRow());
        littleEndianOutput.writeShort(getColumn());
        littleEndianOutput.writeShort(getXFIndex());
        serializeValue(littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public final int getDataSize() {
        return getValueDataSize() + 6;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new CellRecord$$ExternalSyntheticLambda0(this), "col", new CellRecord$$ExternalSyntheticLambda1(this), "xfIndex", new CellRecord$$ExternalSyntheticLambda2(this));
    }
}
