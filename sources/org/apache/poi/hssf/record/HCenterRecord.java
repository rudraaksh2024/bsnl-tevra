package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class HCenterRecord extends StandardRecord {
    public static final short sid = 131;
    private short field_1_hcenter;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 131;
    }

    public HCenterRecord() {
    }

    public HCenterRecord(HCenterRecord hCenterRecord) {
        super(hCenterRecord);
        this.field_1_hcenter = hCenterRecord.field_1_hcenter;
    }

    public HCenterRecord(RecordInputStream recordInputStream) {
        this.field_1_hcenter = recordInputStream.readShort();
    }

    public void setHCenter(boolean z) {
        this.field_1_hcenter = z ? (short) 1 : 0;
    }

    public boolean getHCenter() {
        return this.field_1_hcenter == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_hcenter);
    }

    public HCenterRecord copy() {
        return new HCenterRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.H_CENTER;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("hcenter", new HCenterRecord$$ExternalSyntheticLambda0(this));
    }
}
