package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class IterationRecord extends StandardRecord {
    private static final BitField iterationOn = BitFieldFactory.getInstance(1);
    public static final short sid = 17;
    private int _flags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 17;
    }

    public IterationRecord(IterationRecord iterationRecord) {
        super(iterationRecord);
        this._flags = iterationRecord._flags;
    }

    public IterationRecord(boolean z) {
        this._flags = iterationOn.setBoolean(0, z);
    }

    public IterationRecord(RecordInputStream recordInputStream) {
        this._flags = recordInputStream.readShort();
    }

    public void setIteration(boolean z) {
        this._flags = iterationOn.setBoolean(this._flags, z);
    }

    public boolean getIteration() {
        return iterationOn.isSet(this._flags);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._flags);
    }

    public IterationRecord copy() {
        return new IterationRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ITERATION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", new IterationRecord$$ExternalSyntheticLambda0(this), "iteration", new IterationRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-IterationRecord  reason: not valid java name */
    public /* synthetic */ Object m2034lambda$getGenericProperties$0$orgapachepoihssfrecordIterationRecord() {
        return Integer.valueOf(this._flags);
    }
}
