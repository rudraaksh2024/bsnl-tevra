package org.apache.poi.ss.formula;

import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class BaseFormulaEvaluator implements FormulaEvaluator, WorkbookEvaluatorProvider {
    protected final WorkbookEvaluator _bookEvaluator;

    /* access modifiers changed from: protected */
    public abstract RichTextString createRichTextString(String str);

    /* access modifiers changed from: protected */
    public abstract CellValue evaluateFormulaCellValue(Cell cell);

    protected BaseFormulaEvaluator(WorkbookEvaluator workbookEvaluator) {
        this._bookEvaluator = workbookEvaluator;
    }

    public static void setupEnvironment(String[] strArr, BaseFormulaEvaluator[] baseFormulaEvaluatorArr) {
        int length = baseFormulaEvaluatorArr.length;
        WorkbookEvaluator[] workbookEvaluatorArr = new WorkbookEvaluator[length];
        for (int i = 0; i < length; i++) {
            workbookEvaluatorArr[i] = baseFormulaEvaluatorArr[i]._bookEvaluator;
        }
        CollaboratingWorkbooksEnvironment.setup(strArr, workbookEvaluatorArr);
    }

    public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> map) {
        CollaboratingWorkbooksEnvironment.setupFormulaEvaluator(map);
    }

    public WorkbookEvaluator _getWorkbookEvaluator() {
        return this._bookEvaluator;
    }

    /* access modifiers changed from: protected */
    public EvaluationWorkbook getEvaluationWorkbook() {
        return this._bookEvaluator.getWorkbook();
    }

    public void clearAllCachedResultValues() {
        this._bookEvaluator.clearAllCachedResultValues();
    }

    /* renamed from: org.apache.poi.ss.formula.BaseFormulaEvaluator$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.BaseFormulaEvaluator.AnonymousClass1.<clinit>():void");
        }
    }

    public CellValue evaluate(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cell.getCellType().ordinal()]) {
            case 1:
                return CellValue.valueOf(cell.getBooleanCellValue());
            case 2:
                return CellValue.getError(cell.getErrorCellValue());
            case 3:
                return evaluateFormulaCellValue(cell);
            case 4:
                return new CellValue(cell.getNumericCellValue());
            case 5:
                return new CellValue(cell.getRichStringCellValue().getString());
            case 6:
                return null;
            default:
                throw new IllegalStateException("Bad cell type (" + cell.getCellType() + ")");
        }
    }

    public Cell evaluateInCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.FORMULA) {
            CellValue evaluateFormulaCellValue = evaluateFormulaCellValue(cell);
            setCellValue(cell, evaluateFormulaCellValue);
            setCellType(cell, evaluateFormulaCellValue);
            setCellValue(cell, evaluateFormulaCellValue);
        }
        return cell;
    }

    public CellType evaluateFormulaCell(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.FORMULA) {
            return CellType._NONE;
        }
        CellValue evaluateFormulaCellValue = evaluateFormulaCellValue(cell);
        setCellValue(cell, evaluateFormulaCellValue);
        return evaluateFormulaCellValue.getCellType();
    }

    /* access modifiers changed from: protected */
    public void setCellType(Cell cell, CellValue cellValue) {
        CellType cellType = cellValue.getCellType();
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
            case 1:
            case 2:
            case 4:
            case 5:
                setCellType(cell, cellType);
                return;
            case 3:
                throw new IllegalArgumentException("This should never happen. Formulas should have already been evaluated.");
            case 6:
                throw new IllegalArgumentException("This should never happen. Blanks eventually get translated to zero.");
            default:
                throw new IllegalStateException("Unexpected cell value type (" + cellType + ")");
        }
    }

    /* access modifiers changed from: protected */
    public void setCellType(Cell cell, CellType cellType) {
        cell.setCellType(cellType);
    }

    /* access modifiers changed from: protected */
    public void setCellValue(Cell cell, CellValue cellValue) {
        CellType cellType = cellValue.getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            cell.setCellValue(cellValue.getBooleanValue());
        } else if (i == 2) {
            cell.setCellErrorValue(cellValue.getErrorValue());
        } else if (i == 4) {
            cell.setCellValue(cellValue.getNumberValue());
        } else if (i == 5) {
            cell.setCellValue(createRichTextString(cellValue.getStringValue()));
        } else {
            throw new IllegalStateException("Unexpected cell value type (" + cellType + ")");
        }
    }

    public static void evaluateAllFormulaCells(Workbook workbook) {
        evaluateAllFormulaCells(workbook, workbook.getCreationHelper().createFormulaEvaluator());
    }

    protected static void evaluateAllFormulaCells(Workbook workbook, FormulaEvaluator formulaEvaluator) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            for (Row it : workbook.getSheetAt(i)) {
                for (Cell next : it) {
                    if (next.getCellType() == CellType.FORMULA) {
                        formulaEvaluator.evaluateFormulaCell(next);
                    }
                }
            }
        }
    }

    public void setIgnoreMissingWorkbooks(boolean z) {
        this._bookEvaluator.setIgnoreMissingWorkbooks(z);
    }

    public void setDebugEvaluationOutputForNextEval(boolean z) {
        this._bookEvaluator.setDebugEvaluationOutputForNextEval(z);
    }
}
