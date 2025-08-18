package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class ConditionalFormattingEvaluator {
    private final Map<String, List<EvaluationConditionalFormatRule>> formats = new HashMap();
    private final Map<CellReference, List<EvaluationConditionalFormatRule>> values = new HashMap();
    private final Workbook workbook;
    private final WorkbookEvaluator workbookEvaluator;

    public ConditionalFormattingEvaluator(Workbook workbook2, WorkbookEvaluatorProvider workbookEvaluatorProvider) {
        this.workbook = workbook2;
        this.workbookEvaluator = workbookEvaluatorProvider._getWorkbookEvaluator();
    }

    /* access modifiers changed from: protected */
    public WorkbookEvaluator getWorkbookEvaluator() {
        return this.workbookEvaluator;
    }

    public void clearAllCachedFormats() {
        this.formats.clear();
    }

    public void clearAllCachedValues() {
        this.values.clear();
    }

    /* access modifiers changed from: protected */
    public List<EvaluationConditionalFormatRule> getRules(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        List list = this.formats.get(sheetName);
        if (list == null) {
            if (this.formats.containsKey(sheetName)) {
                return Collections.emptyList();
            }
            SheetConditionalFormatting sheetConditionalFormatting = sheet.getSheetConditionalFormatting();
            int numConditionalFormattings = sheetConditionalFormatting.getNumConditionalFormattings();
            ArrayList arrayList = new ArrayList(numConditionalFormattings);
            this.formats.put(sheetName, arrayList);
            for (int i = 0; i < numConditionalFormattings; i++) {
                ConditionalFormatting conditionalFormattingAt = sheetConditionalFormatting.getConditionalFormattingAt(i);
                CellRangeAddress[] formattingRanges = conditionalFormattingAt.getFormattingRanges();
                for (int i2 = 0; i2 < conditionalFormattingAt.getNumberOfRules(); i2++) {
                    EvaluationConditionalFormatRule evaluationConditionalFormatRule = r5;
                    EvaluationConditionalFormatRule evaluationConditionalFormatRule2 = new EvaluationConditionalFormatRule(this.workbookEvaluator, sheet, conditionalFormattingAt, i, conditionalFormattingAt.getRule(i2), i2, formattingRanges);
                    arrayList.add(evaluationConditionalFormatRule);
                }
            }
            Collections.sort(arrayList);
            list = arrayList;
        }
        return Collections.unmodifiableList(list);
    }

    public List<EvaluationConditionalFormatRule> getConditionalFormattingForCell(CellReference cellReference) {
        Sheet sheet;
        List list = this.values.get(cellReference);
        if (list == null) {
            list = new ArrayList();
            if (cellReference.getSheetName() != null) {
                sheet = this.workbook.getSheet(cellReference.getSheetName());
            } else {
                Workbook workbook2 = this.workbook;
                sheet = workbook2.getSheetAt(workbook2.getActiveSheetIndex());
            }
            boolean z = false;
            for (EvaluationConditionalFormatRule next : getRules(sheet)) {
                if (!z && next.matches(cellReference)) {
                    list.add(next);
                    z = next.getRule().getStopIfTrue();
                }
            }
            Collections.sort(list);
            this.values.put(cellReference, list);
        }
        return Collections.unmodifiableList(list);
    }

    public List<EvaluationConditionalFormatRule> getConditionalFormattingForCell(Cell cell) {
        return getConditionalFormattingForCell(getRef(cell));
    }

    public static CellReference getRef(Cell cell) {
        return new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false);
    }

    public List<EvaluationConditionalFormatRule> getFormatRulesForSheet(String str) {
        return getFormatRulesForSheet(this.workbook.getSheet(str));
    }

    public List<EvaluationConditionalFormatRule> getFormatRulesForSheet(Sheet sheet) {
        return getRules(sheet);
    }

    public List<Cell> getMatchingCells(Sheet sheet, int i, int i2) {
        for (EvaluationConditionalFormatRule next : getRules(sheet)) {
            if (next.getSheet().equals(sheet) && next.getFormattingIndex() == i && next.getRuleIndex() == i2) {
                return getMatchingCells(next);
            }
        }
        return Collections.emptyList();
    }

    public List<Cell> getMatchingCells(EvaluationConditionalFormatRule evaluationConditionalFormatRule) {
        ArrayList arrayList = new ArrayList();
        Sheet sheet = evaluationConditionalFormatRule.getSheet();
        for (CellRangeAddress cellRangeAddress : evaluationConditionalFormatRule.getRegions()) {
            for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= cellRangeAddress.getLastRow(); firstRow++) {
                Row row = sheet.getRow(firstRow);
                if (row != null) {
                    for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= cellRangeAddress.getLastColumn(); firstColumn++) {
                        Cell cell = row.getCell(firstColumn);
                        if (cell != null && getConditionalFormattingForCell(cell).contains(evaluationConditionalFormatRule)) {
                            arrayList.add(cell);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
