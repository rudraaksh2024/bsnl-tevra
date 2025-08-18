package org.apache.poi.hssf.eventmodel;

import java.io.InputStream;
import java.util.Arrays;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.RecordFormatException;

public final class EventRecordFactory {
    private final ERFListener _listener;
    private final short[] _sids;

    public EventRecordFactory(ERFListener eRFListener, short[] sArr) {
        this._listener = eRFListener;
        if (sArr == null) {
            this._sids = null;
            return;
        }
        short[] sArr2 = (short[]) sArr.clone();
        this._sids = sArr2;
        Arrays.sort(sArr2);
    }

    private boolean isSidIncluded(short s) {
        short[] sArr = this._sids;
        if (sArr != null && Arrays.binarySearch(sArr, s) < 0) {
            return false;
        }
        return true;
    }

    private boolean processRecord(Record record) {
        if (!isSidIncluded(record.getSid())) {
            return true;
        }
        return this._listener.processRecord(record);
    }

    public void processRecords(InputStream inputStream) throws RecordFormatException {
        RecordInputStream recordInputStream = new RecordInputStream(inputStream);
        Record record = null;
        while (recordInputStream.hasNextRecord()) {
            recordInputStream.nextRecord();
            Record[] createRecord = RecordFactory.createRecord(recordInputStream);
            int i = 0;
            if (createRecord.length > 1) {
                int length = createRecord.length;
                while (i < length) {
                    Record record2 = createRecord[i];
                    if (record == null || processRecord(record)) {
                        i++;
                        record = record2;
                    } else {
                        return;
                    }
                }
                continue;
            } else {
                Record record3 = createRecord[0];
                if (record3 == null) {
                    continue;
                } else if (record == null || processRecord(record)) {
                    record = record3;
                } else {
                    return;
                }
            }
        }
        if (record != null) {
            processRecord(record);
        }
    }
}
