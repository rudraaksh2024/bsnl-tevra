package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DBCellRecord extends StandardRecord {
    public static final int BLOCK_SIZE = 32;
    public static final short sid = 215;
    private final int field_1_row_offset;
    private final short[] field_2_cell_offsets;

    public DBCellRecord copy() {
        return this;
    }

    public short getSid() {
        return sid;
    }

    public DBCellRecord(int i, short[] sArr) {
        this.field_1_row_offset = i;
        this.field_2_cell_offsets = sArr;
    }

    public DBCellRecord(RecordInputStream recordInputStream) {
        this.field_1_row_offset = recordInputStream.readUShort();
        this.field_2_cell_offsets = new short[(recordInputStream.remaining() / 2)];
        int i = 0;
        while (true) {
            short[] sArr = this.field_2_cell_offsets;
            if (i < sArr.length) {
                sArr[i] = recordInputStream.readShort();
                i++;
            } else {
                return;
            }
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.field_1_row_offset);
        for (short writeShort : this.field_2_cell_offsets) {
            littleEndianOutput.writeShort(writeShort);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.field_2_cell_offsets.length * 2) + 4;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DB_CELL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rowOffset", new DBCellRecord$$ExternalSyntheticLambda0(this), "cellOffsets", new DBCellRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DBCellRecord  reason: not valid java name */
    public /* synthetic */ Object m1987lambda$getGenericProperties$0$orgapachepoihssfrecordDBCellRecord() {
        return Integer.valueOf(this.field_1_row_offset);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DBCellRecord  reason: not valid java name */
    public /* synthetic */ Object m1988lambda$getGenericProperties$1$orgapachepoihssfrecordDBCellRecord() {
        return this.field_2_cell_offsets;
    }
}
