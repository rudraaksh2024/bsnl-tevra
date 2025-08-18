package org.apache.poi.ddf;

public class EscherTertiaryOptRecord extends AbstractEscherOptRecord {
    public static final short RECORD_ID = EscherRecordTypes.USER_DEFINED.typeID;

    public EscherTertiaryOptRecord() {
    }

    public EscherTertiaryOptRecord(EscherTertiaryOptRecord escherTertiaryOptRecord) {
        super(escherTertiaryOptRecord);
    }

    public String getRecordName() {
        return EscherRecordTypes.USER_DEFINED.recordName;
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.USER_DEFINED;
    }

    public EscherTertiaryOptRecord copy() {
        return new EscherTertiaryOptRecord(this);
    }
}
