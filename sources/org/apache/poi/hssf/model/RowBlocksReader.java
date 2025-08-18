package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SharedFormulaRecord;
import org.apache.poi.hssf.record.TableRecord;
import org.apache.poi.hssf.record.aggregates.SharedValueManager;
import org.apache.poi.ss.util.CellReference;

public final class RowBlocksReader {
    private final MergeCellsRecord[] _mergedCellsRecords;
    private final List<Record> _plainRecords;
    private final SharedValueManager _sfm;

    public RowBlocksReader(RecordStream recordStream) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        FormulaRecord formulaRecord = null;
        while (!RecordOrderer.isEndOfRowBlock(recordStream.peekNextSid())) {
            if (recordStream.hasNext()) {
                Record next = recordStream.getNext();
                short sid = next.getSid();
                if (sid == 229) {
                    arrayList = arrayList7;
                } else if (sid == 545) {
                    arrayList = arrayList5;
                } else if (sid == 566) {
                    arrayList = arrayList6;
                } else if (sid != 1212) {
                    arrayList = arrayList2;
                } else if (formulaRecord instanceof FormulaRecord) {
                    FormulaRecord formulaRecord2 = formulaRecord;
                    arrayList4.add(new CellReference(formulaRecord2.getRow(), formulaRecord2.getColumn()));
                    arrayList = arrayList3;
                } else {
                    throw new RuntimeException("Shared formula record should follow a FormulaRecord");
                }
                arrayList.add(next);
                formulaRecord = next;
            } else {
                throw new RuntimeException("Failed to find end of row/cell records");
            }
        }
        SharedFormulaRecord[] sharedFormulaRecordArr = new SharedFormulaRecord[arrayList3.size()];
        CellReference[] cellReferenceArr = new CellReference[arrayList4.size()];
        ArrayRecord[] arrayRecordArr = new ArrayRecord[arrayList5.size()];
        TableRecord[] tableRecordArr = new TableRecord[arrayList6.size()];
        arrayList3.toArray(sharedFormulaRecordArr);
        arrayList4.toArray(cellReferenceArr);
        arrayList5.toArray(arrayRecordArr);
        arrayList6.toArray(tableRecordArr);
        this._plainRecords = arrayList2;
        this._sfm = SharedValueManager.create(sharedFormulaRecordArr, cellReferenceArr, arrayRecordArr, tableRecordArr);
        MergeCellsRecord[] mergeCellsRecordArr = new MergeCellsRecord[arrayList7.size()];
        this._mergedCellsRecords = mergeCellsRecordArr;
        arrayList7.toArray(mergeCellsRecordArr);
    }

    public MergeCellsRecord[] getLooseMergedCells() {
        return this._mergedCellsRecords;
    }

    public SharedValueManager getSharedFormulaManager() {
        return this._sfm;
    }

    public RecordStream getPlainRecordStream() {
        return new RecordStream(this._plainRecords, 0);
    }
}
