package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class SelectionRecord extends StandardRecord {
    public static final short sid = 29;
    private byte field_1_pane;
    private int field_2_row_active_cell;
    private int field_3_col_active_cell;
    private int field_4_active_cell_ref_index;
    private CellRangeAddress8Bit[] field_6_refs;

    public short getSid() {
        return 29;
    }

    public SelectionRecord(SelectionRecord selectionRecord) {
        super(selectionRecord);
        CellRangeAddress8Bit[] cellRangeAddress8BitArr;
        this.field_1_pane = selectionRecord.field_1_pane;
        this.field_2_row_active_cell = selectionRecord.field_2_row_active_cell;
        this.field_3_col_active_cell = selectionRecord.field_3_col_active_cell;
        this.field_4_active_cell_ref_index = selectionRecord.field_4_active_cell_ref_index;
        CellRangeAddress8Bit[] cellRangeAddress8BitArr2 = selectionRecord.field_6_refs;
        if (cellRangeAddress8BitArr2 == null) {
            cellRangeAddress8BitArr = null;
        } else {
            cellRangeAddress8BitArr = (CellRangeAddress8Bit[]) Stream.of(cellRangeAddress8BitArr2).map(new SelectionRecord$$ExternalSyntheticLambda0()).toArray(new SelectionRecord$$ExternalSyntheticLambda1());
        }
        this.field_6_refs = cellRangeAddress8BitArr;
    }

    static /* synthetic */ CellRangeAddress8Bit[] lambda$new$0(int i) {
        return new CellRangeAddress8Bit[i];
    }

    public SelectionRecord(int i, int i2) {
        this.field_1_pane = 3;
        this.field_2_row_active_cell = i;
        this.field_3_col_active_cell = i2;
        this.field_4_active_cell_ref_index = 0;
        this.field_6_refs = new CellRangeAddress8Bit[]{new CellRangeAddress8Bit(i, i, i2, i2)};
    }

    public SelectionRecord(RecordInputStream recordInputStream) {
        this.field_1_pane = recordInputStream.readByte();
        this.field_2_row_active_cell = recordInputStream.readUShort();
        this.field_3_col_active_cell = recordInputStream.readShort();
        this.field_4_active_cell_ref_index = recordInputStream.readShort();
        this.field_6_refs = new CellRangeAddress8Bit[recordInputStream.readUShort()];
        int i = 0;
        while (true) {
            CellRangeAddress8Bit[] cellRangeAddress8BitArr = this.field_6_refs;
            if (i < cellRangeAddress8BitArr.length) {
                cellRangeAddress8BitArr[i] = new CellRangeAddress8Bit(recordInputStream);
                i++;
            } else {
                return;
            }
        }
    }

    public void setPane(byte b) {
        this.field_1_pane = b;
    }

    public void setActiveCellRow(int i) {
        this.field_2_row_active_cell = i;
        resetField6();
    }

    public void setActiveCellCol(short s) {
        this.field_3_col_active_cell = s;
        resetField6();
    }

    private void resetField6() {
        int i = this.field_2_row_active_cell;
        int i2 = this.field_3_col_active_cell;
        this.field_6_refs = new CellRangeAddress8Bit[]{new CellRangeAddress8Bit(i, i, i2, i2)};
    }

    public void setActiveCellRef(short s) {
        this.field_4_active_cell_ref_index = s;
    }

    public byte getPane() {
        return this.field_1_pane;
    }

    public int getActiveCellRow() {
        return this.field_2_row_active_cell;
    }

    public int getActiveCellCol() {
        return this.field_3_col_active_cell;
    }

    public int getActiveCellRef() {
        return this.field_4_active_cell_ref_index;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return CellRangeAddress8Bit.getEncodedSize(this.field_6_refs.length) + 9;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPane());
        littleEndianOutput.writeShort(getActiveCellRow());
        littleEndianOutput.writeShort(getActiveCellCol());
        littleEndianOutput.writeShort(getActiveCellRef());
        littleEndianOutput.writeShort(this.field_6_refs.length);
        for (CellRangeAddress8Bit serialize : this.field_6_refs) {
            serialize.serialize(littleEndianOutput);
        }
    }

    public SelectionRecord copy() {
        return new SelectionRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SELECTION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("pane", new SelectionRecord$$ExternalSyntheticLambda2(this), "activeCellRow", new SelectionRecord$$ExternalSyntheticLambda3(this), "activeCellCol", new SelectionRecord$$ExternalSyntheticLambda4(this), "activeCellRef", new SelectionRecord$$ExternalSyntheticLambda5(this), "refs", new SelectionRecord$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m2086lambda$getGenericProperties$1$orgapachepoihssfrecordSelectionRecord() {
        return this.field_6_refs;
    }
}
