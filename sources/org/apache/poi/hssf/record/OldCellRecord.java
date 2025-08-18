package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

public abstract class OldCellRecord implements GenericRecord {
    private final int field_1_row;
    private final short field_2_column;
    private int field_3_cell_attrs;
    private short field_3_xf_index;
    private final boolean isBiff2;
    private final short sid;

    protected OldCellRecord(RecordInputStream recordInputStream, boolean z) {
        this.sid = recordInputStream.getSid();
        this.isBiff2 = z;
        this.field_1_row = recordInputStream.readUShort();
        this.field_2_column = recordInputStream.readShort();
        if (z) {
            int readUShort = recordInputStream.readUShort() << 8;
            this.field_3_cell_attrs = readUShort;
            this.field_3_cell_attrs = readUShort + recordInputStream.readUByte();
            return;
        }
        this.field_3_xf_index = recordInputStream.readShort();
    }

    public final int getRow() {
        return this.field_1_row;
    }

    public final short getColumn() {
        return this.field_2_column;
    }

    public final short getXFIndex() {
        return this.field_3_xf_index;
    }

    public int getCellAttrs() {
        return this.field_3_cell_attrs;
    }

    public boolean isBiff2() {
        return this.isBiff2;
    }

    public short getSid() {
        return this.sid;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new OldCellRecord$$ExternalSyntheticLambda0(this), "column", new OldCellRecord$$ExternalSyntheticLambda1(this), "biff2", new OldCellRecord$$ExternalSyntheticLambda2(this), "biff2CellAttrs", new OldCellRecord$$ExternalSyntheticLambda3(this), "xfIndex", new OldCellRecord$$ExternalSyntheticLambda4(this));
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
