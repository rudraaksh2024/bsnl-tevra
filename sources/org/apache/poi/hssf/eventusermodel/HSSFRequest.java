package org.apache.poi.hssf.eventusermodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;

public class HSSFRequest {
    private final Map<Short, List<HSSFListener>> _records = new HashMap(50);

    static /* synthetic */ List lambda$addListener$0(Short sh) {
        return new ArrayList(1);
    }

    public void addListener(HSSFListener hSSFListener, short s) {
        this._records.computeIfAbsent(Short.valueOf(s), new HSSFRequest$$ExternalSyntheticLambda0()).add(hSSFListener);
    }

    public void addListenerForAllRecords(HSSFListener hSSFListener) {
        for (short addListener : RecordFactory.getAllKnownRecordSIDs()) {
            addListener(hSSFListener, addListener);
        }
    }

    /* access modifiers changed from: protected */
    public short processRecord(Record record) throws HSSFUserException {
        List list = this._records.get(Short.valueOf(record.getSid()));
        if (list == null) {
            return 0;
        }
        short s = 0;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof AbortableHSSFListener) {
                s = ((AbortableHSSFListener) obj).abortableProcessRecord(record);
                if (s != 0) {
                    break;
                }
            } else {
                ((HSSFListener) obj).processRecord(record);
            }
        }
        return s;
    }
}
