package org.apache.poi.hssf.eventusermodel.dummyrecord;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.util.GenericRecordUtil;

public final class MissingRowDummyRecord extends DummyRecordBase {
    private final int rowNumber;

    public MissingRowDummyRecord copy() {
        return this;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return null;
    }

    public /* bridge */ /* synthetic */ int serialize(int i, byte[] bArr) {
        return super.serialize(i, bArr);
    }

    public MissingRowDummyRecord(int i) {
        this.rowNumber = i;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rowNumber", new MissingRowDummyRecord$$ExternalSyntheticLambda0(this));
    }
}
