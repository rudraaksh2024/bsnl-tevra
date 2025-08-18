package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class GridsetRecord extends StandardRecord {
    public static final short sid = 130;
    private short field_1_gridset_flag;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 130;
    }

    public GridsetRecord() {
    }

    public GridsetRecord(GridsetRecord gridsetRecord) {
        super(gridsetRecord);
        this.field_1_gridset_flag = gridsetRecord.field_1_gridset_flag;
    }

    public GridsetRecord(RecordInputStream recordInputStream) {
        this.field_1_gridset_flag = recordInputStream.readShort();
    }

    public void setGridset(boolean z) {
        if (z) {
            this.field_1_gridset_flag = 1;
        } else {
            this.field_1_gridset_flag = 0;
        }
    }

    public boolean getGridset() {
        return this.field_1_gridset_flag == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_gridset_flag);
    }

    public GridsetRecord copy() {
        return new GridsetRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.GRIDSET;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("gridset", new GridsetRecord$$ExternalSyntheticLambda0(this));
    }
}
