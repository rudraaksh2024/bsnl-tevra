package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.util.GenericRecordUtil;

public final class MissingCellDummyRecord extends DummyRecordBase {
    private final int column;
    private final int row;

    public MissingCellDummyRecord copy() {
        return this;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return null;
    }

    public /* bridge */ /* synthetic */ int serialize(int i, byte[] bArr) {
        return super.serialize(i, bArr);
    }

    public MissingCellDummyRecord(int i, int i2) {
        this.row = i;
        this.column = i2;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new MissingCellDummyRecord$$ExternalSyntheticLambda0(this), "column", new MissingCellDummyRecord$$ExternalSyntheticLambda1(this));
    }
}
