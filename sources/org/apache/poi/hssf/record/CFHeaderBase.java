package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public abstract class CFHeaderBase extends StandardRecord {
    private int field_1_numcf;
    private int field_2_need_recalculation_and_id;
    private CellRangeAddress field_3_enclosing_cell_range;
    private CellRangeAddressList field_4_cell_ranges;

    public abstract CFHeaderBase copy();

    /* access modifiers changed from: protected */
    public abstract String getRecordName();

    protected CFHeaderBase() {
    }

    protected CFHeaderBase(CFHeaderBase cFHeaderBase) {
        super(cFHeaderBase);
        this.field_1_numcf = cFHeaderBase.field_1_numcf;
        this.field_2_need_recalculation_and_id = cFHeaderBase.field_2_need_recalculation_and_id;
        this.field_3_enclosing_cell_range = cFHeaderBase.field_3_enclosing_cell_range.copy();
        this.field_4_cell_ranges = cFHeaderBase.field_4_cell_ranges.copy();
    }

    protected CFHeaderBase(CellRangeAddress[] cellRangeAddressArr, int i) {
        setCellRanges(CellRangeUtil.mergeCellRanges(cellRangeAddressArr));
        this.field_1_numcf = i;
    }

    /* access modifiers changed from: protected */
    public void createEmpty() {
        this.field_3_enclosing_cell_range = new CellRangeAddress(0, 0, 0, 0);
        this.field_4_cell_ranges = new CellRangeAddressList();
    }

    /* access modifiers changed from: protected */
    public void read(RecordInputStream recordInputStream) {
        this.field_1_numcf = recordInputStream.readShort();
        this.field_2_need_recalculation_and_id = recordInputStream.readShort();
        this.field_3_enclosing_cell_range = new CellRangeAddress(recordInputStream);
        this.field_4_cell_ranges = new CellRangeAddressList(recordInputStream);
    }

    public int getNumberOfConditionalFormats() {
        return this.field_1_numcf;
    }

    public void setNumberOfConditionalFormats(int i) {
        this.field_1_numcf = i;
    }

    public boolean getNeedRecalculation() {
        return (this.field_2_need_recalculation_and_id & 1) == 1;
    }

    public void setNeedRecalculation(boolean z) {
        if (z != getNeedRecalculation()) {
            if (z) {
                this.field_2_need_recalculation_and_id++;
            } else {
                this.field_2_need_recalculation_and_id--;
            }
        }
    }

    public int getID() {
        return this.field_2_need_recalculation_and_id >> 1;
    }

    public void setID(int i) {
        boolean needRecalculation = getNeedRecalculation();
        int i2 = i << 1;
        this.field_2_need_recalculation_and_id = i2;
        if (needRecalculation) {
            this.field_2_need_recalculation_and_id = i2 + 1;
        }
    }

    public CellRangeAddress getEnclosingCellRange() {
        return this.field_3_enclosing_cell_range;
    }

    public void setEnclosingCellRange(CellRangeAddress cellRangeAddress) {
        this.field_3_enclosing_cell_range = cellRangeAddress;
    }

    public void setCellRanges(CellRangeAddress[] cellRangeAddressArr) {
        if (cellRangeAddressArr != null) {
            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList();
            CellRangeAddress cellRangeAddress = null;
            for (CellRangeAddress cellRangeAddress2 : cellRangeAddressArr) {
                cellRangeAddress = CellRangeUtil.createEnclosingCellRange(cellRangeAddress2, cellRangeAddress);
                cellRangeAddressList.addCellRangeAddress(cellRangeAddress2);
            }
            this.field_3_enclosing_cell_range = cellRangeAddress;
            this.field_4_cell_ranges = cellRangeAddressList;
            return;
        }
        throw new IllegalArgumentException("cellRanges must not be null");
    }

    public CellRangeAddress[] getCellRanges() {
        return this.field_4_cell_ranges.getCellRangeAddresses();
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.field_4_cell_ranges.getSize() + 12;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_numcf);
        littleEndianOutput.writeShort(this.field_2_need_recalculation_and_id);
        this.field_3_enclosing_cell_range.serialize(littleEndianOutput);
        this.field_4_cell_ranges.serialize(littleEndianOutput);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new CFHeaderBase$$ExternalSyntheticLambda0(this), "numCF", new CFHeaderBase$$ExternalSyntheticLambda1(this), "needRecalculationAndId", new CFHeaderBase$$ExternalSyntheticLambda2(this), "enclosingCellRange", new CFHeaderBase$$ExternalSyntheticLambda3(this), "cfRanges", new CFHeaderBase$$ExternalSyntheticLambda4(this));
    }
}
