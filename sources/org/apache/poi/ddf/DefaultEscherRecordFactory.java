package org.apache.poi.ddf;

import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;

public class DefaultEscherRecordFactory implements EscherRecordFactory {
    private static final BitField IS_CONTAINER = BitFieldFactory.getInstance(15);

    public EscherRecord createRecord(byte[] bArr, int i) {
        short s = LittleEndian.getShort(bArr, i);
        short s2 = LittleEndian.getShort(bArr, i + 2);
        EscherRecord escherRecord = (EscherRecord) getConstructor(s, s2).get();
        escherRecord.setRecordId(s2);
        escherRecord.setOptions(s);
        return escherRecord;
    }

    /* access modifiers changed from: protected */
    public Supplier<? extends EscherRecord> getConstructor(short s, short s2) {
        EscherRecordTypes forTypeID = EscherRecordTypes.forTypeID(s2);
        if (forTypeID == EscherRecordTypes.UNKNOWN && IS_CONTAINER.isAllSet(s)) {
            return new DefaultEscherRecordFactory$$ExternalSyntheticLambda0();
        }
        if (forTypeID.constructor != null && forTypeID != EscherRecordTypes.UNKNOWN) {
            return forTypeID.constructor;
        }
        if (EscherBlipRecord.RECORD_ID_START > s2 || s2 > EscherBlipRecord.RECORD_ID_END) {
            return new DefaultEscherRecordFactory$$ExternalSyntheticLambda2();
        }
        return new DefaultEscherRecordFactory$$ExternalSyntheticLambda1();
    }
}
