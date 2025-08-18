package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.LittleEndianInput;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubRecord$SubRecordTypes$$ExternalSyntheticLambda5 implements SubRecord.SubRecordTypes.RecordConstructor {
    public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
        return new FtCfSubRecord(littleEndianInput, i, i2);
    }
}
