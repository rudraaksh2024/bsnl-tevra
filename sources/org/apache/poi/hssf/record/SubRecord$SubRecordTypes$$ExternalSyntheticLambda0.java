package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.LittleEndianInput;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubRecord$SubRecordTypes$$ExternalSyntheticLambda0 implements SubRecord.SubRecordTypes.RecordConstructor {
    public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
        return new SubRecord.UnknownSubRecord(littleEndianInput, i, i2);
    }
}
