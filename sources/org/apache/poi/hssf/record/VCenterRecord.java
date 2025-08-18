package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class VCenterRecord extends StandardRecord {
    public static final short sid = 132;
    private int field_1_vcenter;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 132;
    }

    public VCenterRecord() {
    }

    public VCenterRecord(VCenterRecord vCenterRecord) {
        super(vCenterRecord);
        this.field_1_vcenter = vCenterRecord.field_1_vcenter;
    }

    public VCenterRecord(RecordInputStream recordInputStream) {
        this.field_1_vcenter = recordInputStream.readShort();
    }

    public void setVCenter(boolean z) {
        this.field_1_vcenter = z ? 1 : 0;
    }

    public boolean getVCenter() {
        return this.field_1_vcenter == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_vcenter);
    }

    public VCenterRecord copy() {
        return new VCenterRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.V_CENTER;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("vcenter", new VCenterRecord$$ExternalSyntheticLambda0(this));
    }
}
