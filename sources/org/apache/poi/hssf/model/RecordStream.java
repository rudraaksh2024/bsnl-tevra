package org.apache.poi.hssf.model;

import java.util.List;
import org.apache.poi.hssf.record.Record;

public final class RecordStream {
    private int _countRead;
    private final int _endIx;
    private final List<Record> _list;
    private int _nextIndex;

    public RecordStream(List<Record> list, int i, int i2) {
        this._list = list;
        this._nextIndex = i;
        this._endIx = i2;
        this._countRead = 0;
    }

    public RecordStream(List<Record> list, int i) {
        this(list, i, list.size());
    }

    public boolean hasNext() {
        return this._nextIndex < this._endIx;
    }

    public Record getNext() {
        if (hasNext()) {
            this._countRead++;
            List<Record> list = this._list;
            int i = this._nextIndex;
            this._nextIndex = i + 1;
            return list.get(i);
        }
        throw new RuntimeException("Attempt to read past end of record stream");
    }

    public Class<? extends Record> peekNextClass() {
        if (!hasNext()) {
            return null;
        }
        return this._list.get(this._nextIndex).getClass();
    }

    public Record peekNextRecord() {
        if (hasNext()) {
            return this._list.get(this._nextIndex);
        }
        return null;
    }

    public int peekNextSid() {
        if (!hasNext()) {
            return -1;
        }
        return this._list.get(this._nextIndex).getSid();
    }

    public int getCountRead() {
        return this._countRead;
    }
}
