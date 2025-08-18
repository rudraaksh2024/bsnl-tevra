package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.DVALRecord;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

public final class DataValidityTable extends RecordAggregate {
    private final DVALRecord _headerRec;
    private final List<DVRecord> _validationList;

    public DataValidityTable(RecordStream recordStream) {
        this._headerRec = (DVALRecord) recordStream.getNext();
        ArrayList arrayList = new ArrayList();
        while (recordStream.peekNextClass() == DVRecord.class) {
            arrayList.add((DVRecord) recordStream.getNext());
        }
        this._validationList = arrayList;
    }

    public DataValidityTable() {
        this._headerRec = new DVALRecord();
        this._validationList = new ArrayList();
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        if (!this._validationList.isEmpty()) {
            recordVisitor.visitRecord(this._headerRec);
            List<DVRecord> list = this._validationList;
            recordVisitor.getClass();
            list.forEach(new DataValidityTable$$ExternalSyntheticLambda0(recordVisitor));
        }
    }

    public void addDataValidation(DVRecord dVRecord) {
        this._validationList.add(dVRecord);
        this._headerRec.setDVRecNo(this._validationList.size());
    }
}
