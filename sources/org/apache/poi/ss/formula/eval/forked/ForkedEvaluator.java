package org.apache.poi.ss.formula.eval.forked;

import java.util.stream.Stream;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;

public final class ForkedEvaluator {
    /* access modifiers changed from: private */
    public final WorkbookEvaluator _evaluator;
    private final ForkedEvaluationWorkbook _sewb;

    private ForkedEvaluator(EvaluationWorkbook evaluationWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        ForkedEvaluationWorkbook forkedEvaluationWorkbook = new ForkedEvaluationWorkbook(evaluationWorkbook);
        this._sewb = forkedEvaluationWorkbook;
        this._evaluator = new WorkbookEvaluator(forkedEvaluationWorkbook, iStabilityClassifier, uDFFinder);
    }

    public static ForkedEvaluator create(Workbook workbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        return new ForkedEvaluator(workbook.createEvaluationWorkbook(), iStabilityClassifier, uDFFinder);
    }

    public void updateCell(String str, int i, int i2, ValueEval valueEval) {
        ForkedEvaluationCell orCreateUpdatableCell = this._sewb.getOrCreateUpdatableCell(str, i, i2);
        orCreateUpdatableCell.setValue(valueEval);
        this._evaluator.notifyUpdateCell(orCreateUpdatableCell);
    }

    public void copyUpdatedCells(Workbook workbook) {
        this._sewb.copyUpdatedCells(workbook);
    }

    public ValueEval evaluate(String str, int i, int i2) {
        EvaluationCell evaluationCell = this._sewb.getEvaluationCell(str, i, i2);
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[evaluationCell.getCellType().ordinal()]) {
            case 1:
                return BoolEval.valueOf(evaluationCell.getBooleanCellValue());
            case 2:
                return ErrorEval.valueOf(evaluationCell.getErrorCellValue());
            case 3:
                return this._evaluator.evaluate(evaluationCell);
            case 4:
                return new NumberEval(evaluationCell.getNumericCellValue());
            case 5:
                return new StringEval(evaluationCell.getStringCellValue());
            case 6:
                return null;
            default:
                throw new IllegalStateException("Bad cell type (" + evaluationCell.getCellType() + ")");
        }
    }

    /* renamed from: org.apache.poi.ss.formula.eval.forked.ForkedEvaluator$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.eval.forked.ForkedEvaluator.AnonymousClass1.<clinit>():void");
        }
    }

    static /* synthetic */ WorkbookEvaluator[] lambda$setupEnvironment$1(int i) {
        return new WorkbookEvaluator[i];
    }

    public static void setupEnvironment(String[] strArr, ForkedEvaluator[] forkedEvaluatorArr) {
        CollaboratingWorkbooksEnvironment.setup(strArr, (WorkbookEvaluator[]) Stream.of(forkedEvaluatorArr).map(new ForkedEvaluator$$ExternalSyntheticLambda0()).toArray(new ForkedEvaluator$$ExternalSyntheticLambda1()));
    }
}
