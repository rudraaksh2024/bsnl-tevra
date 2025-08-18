package org.apache.poi.ss.formula.eval.forked;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;

@Internal
final class ForkedEvaluationWorkbook implements EvaluationWorkbook {
    private final EvaluationWorkbook _masterBook;
    private final Map<String, ForkedEvaluationSheet> _sharedSheetsByName = new HashMap();

    public ForkedEvaluationWorkbook(EvaluationWorkbook evaluationWorkbook) {
        this._masterBook = evaluationWorkbook;
    }

    public ForkedEvaluationCell getOrCreateUpdatableCell(String str, int i, int i2) {
        return getSharedSheet(str).getOrCreateUpdatableCell(i, i2);
    }

    public EvaluationCell getEvaluationCell(String str, int i, int i2) {
        return getSharedSheet(str).getCell(i, i2);
    }

    private ForkedEvaluationSheet getSharedSheet(String str) {
        ForkedEvaluationSheet forkedEvaluationSheet = this._sharedSheetsByName.get(str);
        if (forkedEvaluationSheet != null) {
            return forkedEvaluationSheet;
        }
        EvaluationWorkbook evaluationWorkbook = this._masterBook;
        ForkedEvaluationSheet forkedEvaluationSheet2 = new ForkedEvaluationSheet(evaluationWorkbook.getSheet(evaluationWorkbook.getSheetIndex(str)));
        this._sharedSheetsByName.put(str, forkedEvaluationSheet2);
        return forkedEvaluationSheet2;
    }

    public void copyUpdatedCells(Workbook workbook) {
        int size = this._sharedSheetsByName.size();
        String[] strArr = new String[size];
        this._sharedSheetsByName.keySet().toArray(strArr);
        for (int i = 0; i < size; i++) {
            String str = strArr[i];
            this._sharedSheetsByName.get(str).copyUpdatedCells(workbook.getSheet(str));
        }
    }

    public int convertFromExternSheetIndex(int i) {
        return this._masterBook.convertFromExternSheetIndex(i);
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(int i) {
        return this._masterBook.getExternalSheet(i);
    }

    public EvaluationWorkbook.ExternalSheet getExternalSheet(String str, String str2, int i) {
        return this._masterBook.getExternalSheet(str, str2, i);
    }

    public Ptg[] getFormulaTokens(EvaluationCell evaluationCell) {
        if (!(evaluationCell instanceof ForkedEvaluationCell)) {
            return this._masterBook.getFormulaTokens(evaluationCell);
        }
        throw new RuntimeException("Updated formulas not supported yet");
    }

    public EvaluationName getName(NamePtg namePtg) {
        return this._masterBook.getName(namePtg);
    }

    public EvaluationName getName(String str, int i) {
        return this._masterBook.getName(str, i);
    }

    public EvaluationSheet getSheet(int i) {
        return getSharedSheet(getSheetName(i));
    }

    public EvaluationWorkbook.ExternalName getExternalName(int i, int i2) {
        return this._masterBook.getExternalName(i, i2);
    }

    public EvaluationWorkbook.ExternalName getExternalName(String str, String str2, int i) {
        return this._masterBook.getExternalName(str, str2, i);
    }

    public int getSheetIndex(EvaluationSheet evaluationSheet) {
        if (evaluationSheet instanceof ForkedEvaluationSheet) {
            return ((ForkedEvaluationSheet) evaluationSheet).getSheetIndex(this._masterBook);
        }
        return this._masterBook.getSheetIndex(evaluationSheet);
    }

    public int getSheetIndex(String str) {
        return this._masterBook.getSheetIndex(str);
    }

    public String getSheetName(int i) {
        return this._masterBook.getSheetName(i);
    }

    public String resolveNameXText(NameXPtg nameXPtg) {
        return this._masterBook.resolveNameXText(nameXPtg);
    }

    public UDFFinder getUDFFinder() {
        return this._masterBook.getUDFFinder();
    }

    public SpreadsheetVersion getSpreadsheetVersion() {
        return this._masterBook.getSpreadsheetVersion();
    }

    public void clearAllCachedResultValues() {
        this._masterBook.clearAllCachedResultValues();
    }
}
