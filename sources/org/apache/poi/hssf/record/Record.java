package org.apache.poi.hssf.record;

import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;

public abstract class Record extends RecordBase implements Duplicatable, GenericRecord {
    public abstract Record copy();

    public abstract HSSFRecordTypes getGenericRecordType();

    public abstract short getSid();

    protected Record() {
    }

    protected Record(Record record) {
    }

    public final byte[] serialize() {
        byte[] bArr = new byte[getRecordSize()];
        serialize(0, bArr);
        return bArr;
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public Record cloneViaReserialise() {
        RecordInputStream recordInputStream = new RecordInputStream(new UnsynchronizedByteArrayInputStream(serialize()));
        recordInputStream.nextRecord();
        Record[] createRecord = RecordFactory.createRecord(recordInputStream);
        if (createRecord.length == 1) {
            return createRecord[0];
        }
        throw new IllegalStateException("Re-serialised a record to clone it, but got " + createRecord.length + " records back!");
    }
}
