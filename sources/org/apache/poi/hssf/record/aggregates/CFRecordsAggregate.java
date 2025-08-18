package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CFHeader12Record;
import org.apache.poi.hssf.record.CFHeaderBase;
import org.apache.poi.hssf.record.CFHeaderRecord;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.RecordFormatException;

public final class CFRecordsAggregate extends RecordAggregate implements GenericRecord {
    private static final Logger LOG = LogManager.getLogger((Class<?>) CFRecordsAggregate.class);
    private static final int MAX_97_2003_CONDTIONAL_FORMAT_RULES = 3;
    private final CFHeaderBase header;
    private final List<CFRuleBase> rules;

    public CFRecordsAggregate(CFRecordsAggregate cFRecordsAggregate) {
        ArrayList arrayList = new ArrayList();
        this.rules = arrayList;
        this.header = cFRecordsAggregate.header.copy();
        cFRecordsAggregate.rules.stream().map(new CFRecordsAggregate$$ExternalSyntheticLambda2()).forEach(new CFRecordsAggregate$$ExternalSyntheticLambda3(arrayList));
    }

    private CFRecordsAggregate(CFHeaderBase cFHeaderBase, CFRuleBase[] cFRuleBaseArr) {
        this.rules = new ArrayList();
        if (cFHeaderBase == null) {
            throw new IllegalArgumentException("header must not be null");
        } else if (cFRuleBaseArr != null) {
            if (cFRuleBaseArr.length > 3) {
                LOG.atWarn().log("Excel versions before 2007 require that No more than 3 rules may be specified, {} were found, this file will cause problems with old Excel versions", (Object) Unbox.box(cFRuleBaseArr.length));
            }
            if (cFRuleBaseArr.length == cFHeaderBase.getNumberOfConditionalFormats()) {
                this.header = cFHeaderBase;
                for (CFRuleBase cFRuleBase : cFRuleBaseArr) {
                    checkRuleType(cFRuleBase);
                    this.rules.add(cFRuleBase);
                }
                return;
            }
            throw new RecordFormatException("Mismatch number of rules");
        } else {
            throw new IllegalArgumentException("rules must not be null");
        }
    }

    public CFRecordsAggregate(CellRangeAddress[] cellRangeAddressArr, CFRuleBase[] cFRuleBaseArr) {
        this(createHeader(cellRangeAddressArr, cFRuleBaseArr), cFRuleBaseArr);
    }

    private static CFHeaderBase createHeader(CellRangeAddress[] cellRangeAddressArr, CFRuleBase[] cFRuleBaseArr) {
        CFHeaderBase cFHeaderBase;
        if (cFRuleBaseArr.length == 0 || (cFRuleBaseArr[0] instanceof CFRuleRecord)) {
            cFHeaderBase = new CFHeaderRecord(cellRangeAddressArr, cFRuleBaseArr.length);
        } else {
            cFHeaderBase = new CFHeader12Record(cellRangeAddressArr, cFRuleBaseArr.length);
        }
        cFHeaderBase.setNeedRecalculation(true);
        return cFHeaderBase;
    }

    public static CFRecordsAggregate createCFAggregate(RecordStream recordStream) {
        Record next = recordStream.getNext();
        if (next.getSid() == 432 || next.getSid() == 2169) {
            CFHeaderBase cFHeaderBase = (CFHeaderBase) next;
            int numberOfConditionalFormats = cFHeaderBase.getNumberOfConditionalFormats();
            CFRuleBase[] cFRuleBaseArr = new CFRuleBase[numberOfConditionalFormats];
            for (int i = 0; i < numberOfConditionalFormats; i++) {
                cFRuleBaseArr[i] = (CFRuleBase) recordStream.getNext();
            }
            return new CFRecordsAggregate(cFHeaderBase, cFRuleBaseArr);
        }
        throw new IllegalStateException("next record sid was " + next.getSid() + " instead of 432 or 2169 as expected");
    }

    public CFRecordsAggregate cloneCFAggregate() {
        return new CFRecordsAggregate(this);
    }

    public CFHeaderBase getHeader() {
        return this.header;
    }

    private void checkRuleIndex(int i) {
        if (i < 0 || i >= this.rules.size()) {
            throw new IllegalArgumentException("Bad rule record index (" + i + ") nRules=" + this.rules.size());
        }
    }

    private void checkRuleType(CFRuleBase cFRuleBase) {
        CFHeaderBase cFHeaderBase = this.header;
        if ((cFHeaderBase instanceof CFHeaderRecord) && (cFRuleBase instanceof CFRuleRecord)) {
            return;
        }
        if (!(cFHeaderBase instanceof CFHeader12Record) || !(cFRuleBase instanceof CFRule12Record)) {
            throw new IllegalArgumentException("Header and Rule must both be CF or both be CF12, can't mix");
        }
    }

    public CFRuleBase getRule(int i) {
        checkRuleIndex(i);
        return this.rules.get(i);
    }

    public void setRule(int i, CFRuleBase cFRuleBase) {
        if (cFRuleBase != null) {
            checkRuleIndex(i);
            checkRuleType(cFRuleBase);
            this.rules.set(i, cFRuleBase);
            return;
        }
        throw new IllegalArgumentException("r must not be null");
    }

    public void addRule(CFRuleBase cFRuleBase) {
        if (cFRuleBase != null) {
            if (this.rules.size() >= 3) {
                LOG.atWarn().log("Excel versions before 2007 cannot cope with any more than 3 - this file will cause problems with old Excel versions");
            }
            checkRuleType(cFRuleBase);
            this.rules.add(cFRuleBase);
            this.header.setNumberOfConditionalFormats(this.rules.size());
            return;
        }
        throw new IllegalArgumentException("r must not be null");
    }

    public int getNumberOfRules() {
        return this.rules.size();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("header", new CFRecordsAggregate$$ExternalSyntheticLambda0(this), "rules", new CFRecordsAggregate$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-aggregates-CFRecordsAggregate  reason: not valid java name */
    public /* synthetic */ Object m2116lambda$getGenericProperties$1$orgapachepoihssfrecordaggregatesCFRecordsAggregate() {
        return this.rules;
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        recordVisitor.visitRecord(this.header);
        for (CFRuleBase visitRecord : this.rules) {
            recordVisitor.visitRecord(visitRecord);
        }
    }

    public boolean updateFormulasAfterCellShift(FormulaShifter formulaShifter, int i) {
        CFRule12Record cFRule12Record;
        Ptg[] parsedExpressionScale;
        CellRangeAddress[] cellRanges = this.header.getCellRanges();
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (CellRangeAddress cellRangeAddress : cellRanges) {
            CellRangeAddress shiftRange = BaseRowColShifter.shiftRange(formulaShifter, cellRangeAddress, i);
            if (shiftRange != null) {
                arrayList.add(shiftRange);
                if (shiftRange == cellRangeAddress) {
                }
            }
            z = true;
        }
        if (z) {
            int size = arrayList.size();
            if (size == 0) {
                return false;
            }
            CellRangeAddress[] cellRangeAddressArr = new CellRangeAddress[size];
            arrayList.toArray(cellRangeAddressArr);
            this.header.setCellRanges(cellRangeAddressArr);
        }
        for (CFRuleBase next : this.rules) {
            Ptg[] parsedExpression1 = next.getParsedExpression1();
            if (parsedExpression1 != null && formulaShifter.adjustFormula(parsedExpression1, i)) {
                next.setParsedExpression1(parsedExpression1);
            }
            Ptg[] parsedExpression2 = next.getParsedExpression2();
            if (parsedExpression2 != null && formulaShifter.adjustFormula(parsedExpression2, i)) {
                next.setParsedExpression2(parsedExpression2);
            }
            if ((next instanceof CFRule12Record) && (parsedExpressionScale = cFRule12Record.getParsedExpressionScale()) != null && formulaShifter.adjustFormula(parsedExpressionScale, i)) {
                (cFRule12Record = (CFRule12Record) next).setParsedExpressionScale(parsedExpressionScale);
            }
        }
        return true;
    }
}
