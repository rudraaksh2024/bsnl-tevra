package org.apache.poi.ddf;

import org.apache.poi.util.Internal;

public class EscherOptRecord extends AbstractEscherOptRecord {
    public static final String RECORD_DESCRIPTION = EscherRecordTypes.OPT.description;
    public static final short RECORD_ID = EscherRecordTypes.OPT.typeID;

    public EscherOptRecord() {
    }

    public EscherOptRecord(EscherOptRecord escherOptRecord) {
        super(escherOptRecord);
    }

    public short getInstance() {
        setInstance((short) getEscherProperties().size());
        return super.getInstance();
    }

    @Internal
    public short getOptions() {
        getInstance();
        getVersion();
        return super.getOptions();
    }

    public String getRecordName() {
        return EscherRecordTypes.OPT.recordName;
    }

    public short getVersion() {
        setVersion(3);
        return super.getVersion();
    }

    public void setVersion(short s) {
        if (s == 3) {
            super.setVersion(s);
            return;
        }
        throw new IllegalArgumentException(RECORD_DESCRIPTION + " can have only '0x3' version");
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.OPT;
    }

    public EscherOptRecord copy() {
        return new EscherOptRecord(this);
    }
}
