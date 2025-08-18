package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ScenarioProtectRecord extends StandardRecord {
    public static final short sid = 221;
    private short field_1_protect;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public ScenarioProtectRecord() {
    }

    public ScenarioProtectRecord(ScenarioProtectRecord scenarioProtectRecord) {
        super(scenarioProtectRecord);
        this.field_1_protect = scenarioProtectRecord.field_1_protect;
    }

    public ScenarioProtectRecord(RecordInputStream recordInputStream) {
        this.field_1_protect = recordInputStream.readShort();
    }

    public void setProtect(boolean z) {
        if (z) {
            this.field_1_protect = 1;
        } else {
            this.field_1_protect = 0;
        }
    }

    public boolean getProtect() {
        return this.field_1_protect == 1;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_protect);
    }

    public ScenarioProtectRecord copy() {
        return new ScenarioProtectRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SCENARIO_PROTECT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("protect", new ScenarioProtectRecord$$ExternalSyntheticLambda0(this));
    }
}
