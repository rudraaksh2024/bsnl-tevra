package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.util.GenericRecordUtil;

public final class LastCellOfRowDummyRecord extends DummyRecordBase {
    private final int lastColumnNumber;
    private final int row;

    public LastCellOfRowDummyRecord copy() {
        return this;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return null;
    }

    public /* bridge */ /* synthetic */ int serialize(int i, byte[] bArr) {
        return super.serialize(i, bArr);
    }

    public LastCellOfRowDummyRecord(int i, int i2) {
        this.row = i;
        this.lastColumnNumber = i2;
    }

    public int getRow() {
        return this.row;
    }

    public int getLastColumnNumber() {
        return this.lastColumnNumber;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new LastCellOfRowDummyRecord$$ExternalSyntheticLambda0(this), "lastColumnNumber", new LastCellOfRowDummyRecord$$ExternalSyntheticLambda1(this));
    }
}
