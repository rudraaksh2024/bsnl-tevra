package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CFHeaderBase;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.ss.formula.FormulaShifter;

public final class ConditionalFormattingTable extends RecordAggregate {
    private final List<CFRecordsAggregate> _cfHeaders = new ArrayList();

    public ConditionalFormattingTable() {
    }

    public ConditionalFormattingTable(RecordStream recordStream) {
        while (recordStream.peekNextRecord() instanceof CFHeaderBase) {
            this._cfHeaders.add(CFRecordsAggregate.createCFAggregate(recordStream));
        }
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        for (CFRecordsAggregate visitContainedRecords : this._cfHeaders) {
            visitContainedRecords.visitContainedRecords(recordVisitor);
        }
    }

    public int add(CFRecordsAggregate cFRecordsAggregate) {
        cFRecordsAggregate.getHeader().setID(this._cfHeaders.size());
        this._cfHeaders.add(cFRecordsAggregate);
        return this._cfHeaders.size() - 1;
    }

    public int size() {
        return this._cfHeaders.size();
    }

    public CFRecordsAggregate get(int i) {
        checkIndex(i);
        return this._cfHeaders.get(i);
    }

    public void remove(int i) {
        checkIndex(i);
        this._cfHeaders.remove(i);
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= this._cfHeaders.size()) {
            throw new IllegalArgumentException("Specified CF index " + i + " is outside the allowable range (0.." + (this._cfHeaders.size() - 1) + ")");
        }
    }

    public void updateFormulasAfterCellShift(FormulaShifter formulaShifter, int i) {
        int i2 = 0;
        while (i2 < this._cfHeaders.size()) {
            if (!this._cfHeaders.get(i2).updateFormulasAfterCellShift(formulaShifter, i)) {
                this._cfHeaders.remove(i2);
                i2--;
            }
            i2++;
        }
    }
}
