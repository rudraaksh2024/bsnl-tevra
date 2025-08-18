package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class SaveRecalcRecord extends StandardRecord {
    public static final short sid = 95;
    private short field_1_recalc;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 95;
    }

    public SaveRecalcRecord() {
    }

    public SaveRecalcRecord(SaveRecalcRecord saveRecalcRecord) {
        super(saveRecalcRecord);
        this.field_1_recalc = saveRecalcRecord.field_1_recalc;
    }

    public SaveRecalcRecord(RecordInputStream recordInputStream) {
        this.field_1_recalc = recordInputStream.readShort();
    }

    public void setRecalc(boolean z) {
        this.field_1_recalc = z ? (short) 1 : 0;
    }

    public boolean getRecalc() {
        return this.field_1_recalc == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_recalc);
    }

    public SaveRecalcRecord copy() {
        return new SaveRecalcRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SAVE_RECALC;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recalc", new SaveRecalcRecord$$ExternalSyntheticLambda0(this));
    }
}
