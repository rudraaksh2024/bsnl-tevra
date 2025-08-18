package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IntList;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class IndexRecord extends StandardRecord {
    public static final short sid = 523;
    private int field_2_first_row;
    private int field_3_last_row_add1;
    private int field_4_zero;
    private IntList field_5_dbcells;

    public static int getRecordSizeForBlockCount(int i) {
        return (i * 4) + 20;
    }

    static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }

    public short getSid() {
        return sid;
    }

    public IndexRecord() {
    }

    public IndexRecord(IndexRecord indexRecord) {
        super(indexRecord);
        this.field_2_first_row = indexRecord.field_2_first_row;
        this.field_3_last_row_add1 = indexRecord.field_3_last_row_add1;
        this.field_4_zero = indexRecord.field_4_zero;
        this.field_5_dbcells = indexRecord.field_5_dbcells == null ? null : new IntList(indexRecord.field_5_dbcells);
    }

    public IndexRecord(RecordInputStream recordInputStream) {
        int readInt = recordInputStream.readInt();
        if (readInt == 0) {
            this.field_2_first_row = recordInputStream.readInt();
            this.field_3_last_row_add1 = recordInputStream.readInt();
            this.field_4_zero = recordInputStream.readInt();
            int remaining = recordInputStream.remaining() / 4;
            this.field_5_dbcells = new IntList(remaining);
            for (int i = 0; i < remaining; i++) {
                this.field_5_dbcells.add(recordInputStream.readInt());
            }
            return;
        }
        throw new RecordFormatException("Expected zero for field 1 but got " + readInt);
    }

    public void setFirstRow(int i) {
        this.field_2_first_row = i;
    }

    public void setLastRowAdd1(int i) {
        this.field_3_last_row_add1 = i;
    }

    public void addDbcell(int i) {
        if (this.field_5_dbcells == null) {
            this.field_5_dbcells = new IntList();
        }
        this.field_5_dbcells.add(i);
    }

    public void setDbcell(int i, int i2) {
        this.field_5_dbcells.set(i, i2);
    }

    public int getFirstRow() {
        return this.field_2_first_row;
    }

    public int getLastRowAdd1() {
        return this.field_3_last_row_add1;
    }

    public int getNumDbcells() {
        IntList intList = this.field_5_dbcells;
        if (intList == null) {
            return 0;
        }
        return intList.size();
    }

    public int getDbcellAt(int i) {
        return this.field_5_dbcells.get(i);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(0);
        littleEndianOutput.writeInt(getFirstRow());
        littleEndianOutput.writeInt(getLastRowAdd1());
        littleEndianOutput.writeInt(this.field_4_zero);
        for (int i = 0; i < getNumDbcells(); i++) {
            littleEndianOutput.writeInt(getDbcellAt(i));
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (getNumDbcells() * 4) + 16;
    }

    public IndexRecord copy() {
        return new IndexRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.INDEX;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        Supplier supplier;
        IndexRecord$$ExternalSyntheticLambda0 indexRecord$$ExternalSyntheticLambda0 = new IndexRecord$$ExternalSyntheticLambda0(this);
        IndexRecord$$ExternalSyntheticLambda1 indexRecord$$ExternalSyntheticLambda1 = new IndexRecord$$ExternalSyntheticLambda1(this);
        IntList intList = this.field_5_dbcells;
        if (intList == null) {
            supplier = new IndexRecord$$ExternalSyntheticLambda2();
        } else {
            intList.getClass();
            supplier = new IndexRecord$$ExternalSyntheticLambda3(intList);
        }
        return GenericRecordUtil.getGenericProperties("firstRow", indexRecord$$ExternalSyntheticLambda0, "lastRowAdd1", indexRecord$$ExternalSyntheticLambda1, "dbcell_", supplier);
    }
}
