package org.apache.poi.ss.formula.eval.forked;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;

final class ForkedEvaluationCell implements EvaluationCell {
    private boolean _booleanValue;
    private CellType _cellType;
    private int _errorValue;
    private final EvaluationCell _masterCell;
    private double _numberValue;
    private final EvaluationSheet _sheet;
    private String _stringValue;

    public ForkedEvaluationCell(ForkedEvaluationSheet forkedEvaluationSheet, EvaluationCell evaluationCell) {
        this._sheet = forkedEvaluationSheet;
        this._masterCell = evaluationCell;
        setValue(BlankEval.instance);
    }

    public Object getIdentityKey() {
        return this._masterCell.getIdentityKey();
    }

    public void setValue(ValueEval valueEval) {
        Class<?> cls = valueEval.getClass();
        if (cls == NumberEval.class) {
            this._cellType = CellType.NUMERIC;
            this._numberValue = ((NumberEval) valueEval).getNumberValue();
        } else if (cls == StringEval.class) {
            this._cellType = CellType.STRING;
            this._stringValue = ((StringEval) valueEval).getStringValue();
        } else if (cls == BoolEval.class) {
            this._cellType = CellType.BOOLEAN;
            this._booleanValue = ((BoolEval) valueEval).getBooleanValue();
        } else if (cls == ErrorEval.class) {
            this._cellType = CellType.ERROR;
            this._errorValue = ((ErrorEval) valueEval).getErrorCode();
        } else if (cls == BlankEval.class) {
            this._cellType = CellType.BLANK;
        } else {
            throw new IllegalArgumentException("Unexpected value class (" + cls.getName() + ")");
        }
    }

    /* renamed from: org.apache.poi.ss.formula.eval.forked.ForkedEvaluationCell$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.eval.forked.ForkedEvaluationCell.AnonymousClass1.<clinit>():void");
        }
    }

    public void copyValue(Cell cell) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 1) {
            cell.setBlank();
        } else if (i == 2) {
            cell.setCellValue(this._numberValue);
        } else if (i == 3) {
            cell.setCellValue(this._booleanValue);
        } else if (i == 4) {
            cell.setCellValue(this._stringValue);
        } else if (i == 5) {
            cell.setCellErrorValue((byte) this._errorValue);
        } else {
            throw new IllegalStateException("Unexpected data type (" + this._cellType + ")");
        }
    }

    private void checkCellType(CellType cellType) {
        if (this._cellType != cellType) {
            throw new RuntimeException("Wrong data type (" + this._cellType + ")");
        }
    }

    public CellType getCellType() {
        return this._cellType;
    }

    public boolean getBooleanCellValue() {
        checkCellType(CellType.BOOLEAN);
        return this._booleanValue;
    }

    public int getErrorCellValue() {
        checkCellType(CellType.ERROR);
        return this._errorValue;
    }

    public double getNumericCellValue() {
        checkCellType(CellType.NUMERIC);
        return this._numberValue;
    }

    public String getStringCellValue() {
        checkCellType(CellType.STRING);
        return this._stringValue;
    }

    public EvaluationSheet getSheet() {
        return this._sheet;
    }

    public int getRowIndex() {
        return this._masterCell.getRowIndex();
    }

    public int getColumnIndex() {
        return this._masterCell.getColumnIndex();
    }

    public CellRangeAddress getArrayFormulaRange() {
        return this._masterCell.getArrayFormulaRange();
    }

    public boolean isPartOfArrayFormulaGroup() {
        return this._masterCell.isPartOfArrayFormulaGroup();
    }

    public CellType getCachedFormulaResultType() {
        return this._masterCell.getCachedFormulaResultType();
    }
}
