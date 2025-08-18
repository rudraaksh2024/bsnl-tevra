package org.apache.poi.ss.formula;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.atp.AnalysisToolPak;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnknownPtg;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
public final class WorkbookEvaluator {
    private static final Logger LOG = LogManager.getLogger((Class<?>) WorkbookEvaluator.class);
    private final Logger EVAL_LOG;
    private EvaluationCache _cache;
    private CollaboratingWorkbooksEnvironment _collaboratingWorkbookEnvironment;
    private final IEvaluationListener _evaluationListener;
    private boolean _ignoreMissingWorkbooks;
    private final Map<String, Integer> _sheetIndexesByName;
    private final Map<EvaluationSheet, Integer> _sheetIndexesBySheet;
    private final IStabilityClassifier _stabilityClassifier;
    private final AggregatingUDFFinder _udfFinder;
    private final EvaluationWorkbook _workbook;
    private int _workbookIx;
    private boolean dbgEvaluationOutputForNextEval;
    private int dbgEvaluationOutputIndent;

    public WorkbookEvaluator(EvaluationWorkbook evaluationWorkbook, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        this(evaluationWorkbook, (IEvaluationListener) null, iStabilityClassifier, uDFFinder);
    }

    WorkbookEvaluator(EvaluationWorkbook evaluationWorkbook, IEvaluationListener iEvaluationListener, IStabilityClassifier iStabilityClassifier, UDFFinder uDFFinder) {
        AggregatingUDFFinder aggregatingUDFFinder;
        this.EVAL_LOG = LogManager.getLogger("POI.FormulaEval");
        this.dbgEvaluationOutputIndent = -1;
        this._workbook = evaluationWorkbook;
        this._evaluationListener = iEvaluationListener;
        this._cache = new EvaluationCache(iEvaluationListener);
        this._sheetIndexesBySheet = new IdentityHashMap();
        this._sheetIndexesByName = new IdentityHashMap();
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._workbookIx = 0;
        this._stabilityClassifier = iStabilityClassifier;
        if (evaluationWorkbook == null) {
            aggregatingUDFFinder = null;
        } else {
            aggregatingUDFFinder = (AggregatingUDFFinder) evaluationWorkbook.getUDFFinder();
        }
        if (!(aggregatingUDFFinder == null || uDFFinder == null)) {
            aggregatingUDFFinder.add(uDFFinder);
        }
        this._udfFinder = aggregatingUDFFinder;
    }

    /* access modifiers changed from: package-private */
    public String getSheetName(int i) {
        return this._workbook.getSheetName(i);
    }

    /* access modifiers changed from: package-private */
    public EvaluationSheet getSheet(int i) {
        return this._workbook.getSheet(i);
    }

    /* access modifiers changed from: package-private */
    public EvaluationWorkbook getWorkbook() {
        return this._workbook;
    }

    /* access modifiers changed from: package-private */
    public EvaluationName getName(String str, int i) {
        return this._workbook.getName(str, i);
    }

    /* access modifiers changed from: package-private */
    public void attachToEnvironment(CollaboratingWorkbooksEnvironment collaboratingWorkbooksEnvironment, EvaluationCache evaluationCache, int i) {
        this._collaboratingWorkbookEnvironment = collaboratingWorkbooksEnvironment;
        this._cache = evaluationCache;
        this._workbookIx = i;
    }

    /* access modifiers changed from: package-private */
    public CollaboratingWorkbooksEnvironment getEnvironment() {
        return this._collaboratingWorkbookEnvironment;
    }

    /* access modifiers changed from: package-private */
    public void detachFromEnvironment() {
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._cache = new EvaluationCache(this._evaluationListener);
        this._workbookIx = 0;
    }

    /* access modifiers changed from: package-private */
    public WorkbookEvaluator getOtherWorkbookEvaluator(String str) throws CollaboratingWorkbooksEnvironment.WorkbookNotFoundException {
        return this._collaboratingWorkbookEnvironment.getWorkbookEvaluator(str);
    }

    /* access modifiers changed from: package-private */
    public IEvaluationListener getEvaluationListener() {
        return this._evaluationListener;
    }

    public void clearAllCachedResultValues() {
        this._cache.clear();
        this._sheetIndexesBySheet.clear();
        this._workbook.clearAllCachedResultValues();
    }

    public void notifyUpdateCell(EvaluationCell evaluationCell) {
        this._cache.notifyUpdateCell(this._workbookIx, getSheetIndex(evaluationCell.getSheet()), evaluationCell);
    }

    public void notifyDeleteCell(EvaluationCell evaluationCell) {
        this._cache.notifyDeleteCell(this._workbookIx, getSheetIndex(evaluationCell.getSheet()), evaluationCell);
    }

    private int getSheetIndex(EvaluationSheet evaluationSheet) {
        Integer num = this._sheetIndexesBySheet.get(evaluationSheet);
        if (num == null) {
            int sheetIndex = this._workbook.getSheetIndex(evaluationSheet);
            if (sheetIndex >= 0) {
                num = Integer.valueOf(sheetIndex);
                this._sheetIndexesBySheet.put(evaluationSheet, num);
            } else {
                throw new RuntimeException("Specified sheet from a different book");
            }
        }
        return num.intValue();
    }

    public ValueEval evaluate(EvaluationCell evaluationCell) {
        return evaluateAny(evaluationCell, getSheetIndex(evaluationCell.getSheet()), evaluationCell.getRowIndex(), evaluationCell.getColumnIndex(), new EvaluationTracker(this._cache));
    }

    /* access modifiers changed from: package-private */
    public int getSheetIndex(String str) {
        Integer num = this._sheetIndexesByName.get(str);
        if (num == null) {
            int sheetIndex = this._workbook.getSheetIndex(str);
            if (sheetIndex < 0) {
                return -1;
            }
            num = Integer.valueOf(sheetIndex);
            this._sheetIndexesByName.put(str, num);
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getSheetIndexByExternIndex(int i) {
        return this._workbook.convertFromExternSheetIndex(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x009b A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.eval.ValueEval evaluateAny(org.apache.poi.ss.formula.EvaluationCell r17, int r18, int r19, int r20, org.apache.poi.ss.formula.EvaluationTracker r21) {
        /*
            r16 = this;
            r8 = r16
            r9 = r17
            r10 = r18
            r11 = r19
            r12 = r20
            r13 = r21
            java.lang.String r14 = "Unexpected cell type '"
            org.apache.poi.ss.formula.IStabilityClassifier r0 = r8._stabilityClassifier
            if (r0 == 0) goto L_0x001b
            boolean r0 = r0.isCellFinal(r10, r11, r12)
            if (r0 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r0 = 0
            goto L_0x001c
        L_0x001b:
            r0 = 1
        L_0x001c:
            if (r9 == 0) goto L_0x0150
            org.apache.poi.ss.usermodel.CellType r1 = r17.getCellType()
            org.apache.poi.ss.usermodel.CellType r2 = org.apache.poi.ss.usermodel.CellType.FORMULA
            if (r1 == r2) goto L_0x0028
            goto L_0x0150
        L_0x0028:
            org.apache.poi.ss.formula.EvaluationCache r1 = r8._cache
            org.apache.poi.ss.formula.FormulaCellCacheEntry r7 = r1.getOrCreateFormulaCellEntry(r9)
            if (r0 != 0) goto L_0x0036
            boolean r0 = r7.isInputSensitive()
            if (r0 == 0) goto L_0x0039
        L_0x0036:
            r13.acceptFormulaDependency(r7)
        L_0x0039:
            org.apache.poi.ss.formula.IEvaluationListener r0 = r8._evaluationListener
            org.apache.poi.ss.formula.eval.ValueEval r1 = r7.getValue()
            if (r1 != 0) goto L_0x013f
            boolean r1 = r13.startEvaluate(r7)
            if (r1 != 0) goto L_0x004a
            org.apache.poi.ss.formula.eval.ErrorEval r0 = org.apache.poi.ss.formula.eval.ErrorEval.CIRCULAR_REF_ERROR
            return r0
        L_0x004a:
            org.apache.poi.ss.formula.EvaluationWorkbook r1 = r8._workbook     // Catch:{ NotImplementedException -> 0x012f, RuntimeException -> 0x008d, all -> 0x0089 }
            org.apache.poi.ss.formula.ptg.Ptg[] r6 = r1.getFormulaTokens(r9)     // Catch:{ NotImplementedException -> 0x012f, RuntimeException -> 0x008d, all -> 0x0089 }
            org.apache.poi.ss.formula.OperationEvaluationContext r5 = new org.apache.poi.ss.formula.OperationEvaluationContext     // Catch:{ NotImplementedException -> 0x012f, RuntimeException -> 0x008d, all -> 0x0089 }
            org.apache.poi.ss.formula.EvaluationWorkbook r3 = r8._workbook     // Catch:{ NotImplementedException -> 0x012f, RuntimeException -> 0x008d, all -> 0x0089 }
            r1 = r5
            r2 = r16
            r4 = r18
            r15 = r5
            r5 = r19
            r10 = r6
            r6 = r20
            r11 = r7
            r7 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
            if (r0 != 0) goto L_0x006c
            org.apache.poi.ss.formula.eval.ValueEval r0 = r8.evaluateFormula(r15, r10)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
            goto L_0x0077
        L_0x006c:
            r0.onStartEvaluate(r9, r11)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
            org.apache.poi.ss.formula.eval.ValueEval r1 = r8.evaluateFormula(r15, r10)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
            r0.onEndEvaluate(r11, r1)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
            r0 = r1
        L_0x0077:
            r13.updateCacheResult(r0)     // Catch:{ NotImplementedException -> 0x0081, RuntimeException -> 0x007f }
        L_0x007a:
            r13.endEvaluate(r11)
            goto L_0x0112
        L_0x007f:
            r0 = move-exception
            goto L_0x008f
        L_0x0081:
            r0 = move-exception
            r4 = r18
            r5 = r19
            r1 = r11
            goto L_0x0135
        L_0x0089:
            r0 = move-exception
            r1 = r7
            goto L_0x013b
        L_0x008d:
            r0 = move-exception
            r11 = r7
        L_0x008f:
            java.lang.Throwable r1 = r0.getCause()     // Catch:{ all -> 0x012c }
            boolean r1 = r1 instanceof org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment.WorkbookNotFoundException     // Catch:{ all -> 0x012c }
            if (r1 == 0) goto L_0x012b
            boolean r1 = r8._ignoreMissingWorkbooks     // Catch:{ all -> 0x012c }
            if (r1 == 0) goto L_0x012b
            org.apache.logging.log4j.Logger r1 = LOG     // Catch:{ all -> 0x012c }
            org.apache.logging.log4j.LogBuilder r1 = r1.atInfo()     // Catch:{ all -> 0x012c }
            java.lang.String r2 = "{} - Continuing with cached value!"
            java.lang.Throwable r0 = r0.getCause()     // Catch:{ all -> 0x012c }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x012c }
            r1.log((java.lang.String) r2, (java.lang.Object) r0)     // Catch:{ all -> 0x012c }
            int[] r0 = org.apache.poi.ss.formula.WorkbookEvaluator.AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ all -> 0x012c }
            org.apache.poi.ss.usermodel.CellType r1 = r17.getCachedFormulaResultType()     // Catch:{ all -> 0x012c }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x012c }
            r0 = r0[r1]     // Catch:{ all -> 0x012c }
            r1 = 1
            if (r0 == r1) goto L_0x0107
            r1 = 2
            if (r0 == r1) goto L_0x00fc
            r1 = 3
            if (r0 == r1) goto L_0x00f8
            r1 = 4
            if (r0 == r1) goto L_0x00ef
            r1 = 5
            if (r0 != r1) goto L_0x00d2
            int r0 = r17.getErrorCellValue()     // Catch:{ all -> 0x012c }
            org.apache.poi.ss.formula.eval.ErrorEval r0 = org.apache.poi.ss.formula.eval.ErrorEval.valueOf(r0)     // Catch:{ all -> 0x012c }
            goto L_0x007a
        L_0x00d2:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x012c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x012c }
            r1.<init>(r14)     // Catch:{ all -> 0x012c }
            org.apache.poi.ss.usermodel.CellType r2 = r17.getCellType()     // Catch:{ all -> 0x012c }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x012c }
            java.lang.String r2 = "' found!"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x012c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x012c }
            r0.<init>(r1)     // Catch:{ all -> 0x012c }
            throw r0     // Catch:{ all -> 0x012c }
        L_0x00ef:
            boolean r0 = r17.getBooleanCellValue()     // Catch:{ all -> 0x012c }
            org.apache.poi.ss.formula.eval.BoolEval r0 = org.apache.poi.ss.formula.eval.BoolEval.valueOf(r0)     // Catch:{ all -> 0x012c }
            goto L_0x007a
        L_0x00f8:
            org.apache.poi.ss.formula.eval.BlankEval r0 = org.apache.poi.ss.formula.eval.BlankEval.instance     // Catch:{ all -> 0x012c }
            goto L_0x007a
        L_0x00fc:
            org.apache.poi.ss.formula.eval.StringEval r0 = new org.apache.poi.ss.formula.eval.StringEval     // Catch:{ all -> 0x012c }
            java.lang.String r1 = r17.getStringCellValue()     // Catch:{ all -> 0x012c }
            r0.<init>((java.lang.String) r1)     // Catch:{ all -> 0x012c }
            goto L_0x007a
        L_0x0107:
            org.apache.poi.ss.formula.eval.NumberEval r0 = new org.apache.poi.ss.formula.eval.NumberEval     // Catch:{ all -> 0x012c }
            double r1 = r17.getNumericCellValue()     // Catch:{ all -> 0x012c }
            r0.<init>((double) r1)     // Catch:{ all -> 0x012c }
            goto L_0x007a
        L_0x0112:
            org.apache.logging.log4j.Logger r1 = LOG
            org.apache.logging.log4j.LogBuilder r7 = r1.atDebug()
            org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda0 r9 = new org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda0
            r1 = r9
            r2 = r16
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            r7.log((org.apache.logging.log4j.util.Supplier<org.apache.logging.log4j.message.Message>) r9)
            return r0
        L_0x012b:
            throw r0     // Catch:{ all -> 0x012c }
        L_0x012c:
            r0 = move-exception
            r1 = r11
            goto L_0x013b
        L_0x012f:
            r0 = move-exception
            r4 = r18
            r5 = r19
            r1 = r7
        L_0x0135:
            org.apache.poi.ss.formula.eval.NotImplementedException r0 = r8.addExceptionInfo(r0, r4, r5, r12)     // Catch:{ all -> 0x013a }
            throw r0     // Catch:{ all -> 0x013a }
        L_0x013a:
            r0 = move-exception
        L_0x013b:
            r13.endEvaluate(r1)
            throw r0
        L_0x013f:
            r1 = r7
            r4 = r10
            r5 = r11
            if (r0 == 0) goto L_0x014b
            org.apache.poi.ss.formula.eval.ValueEval r2 = r1.getValue()
            r0.onCacheHit(r4, r5, r12, r2)
        L_0x014b:
            org.apache.poi.ss.formula.eval.ValueEval r0 = r1.getValue()
            return r0
        L_0x0150:
            r4 = r10
            r5 = r11
            org.apache.poi.ss.formula.eval.ValueEval r9 = getValueFromNonFormulaCell(r17)
            if (r0 == 0) goto L_0x0168
            org.apache.poi.ss.formula.EvaluationWorkbook r2 = r8._workbook
            int r3 = r8._workbookIx
            r1 = r21
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r9
            r1.acceptPlainValueDependency(r2, r3, r4, r5, r6, r7)
        L_0x0168:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.WorkbookEvaluator.evaluateAny(org.apache.poi.ss.formula.EvaluationCell, int, int, int, org.apache.poi.ss.formula.EvaluationTracker):org.apache.poi.ss.formula.eval.ValueEval");
    }

    /* renamed from: org.apache.poi.ss.formula.WorkbookEvaluator$1  reason: invalid class name */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0033 }
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
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.WorkbookEvaluator.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateAny$0$org-apache-poi-ss-formula-WorkbookEvaluator  reason: not valid java name */
    public /* synthetic */ Message m2249lambda$evaluateAny$0$orgapachepoissformulaWorkbookEvaluator(int i, int i2, int i3, ValueEval valueEval) {
        return new SimpleMessage("Evaluated " + getSheetName(i) + "!" + new CellReference(i2, i3).formatAsString() + " to " + valueEval);
    }

    private NotImplementedException addExceptionInfo(NotImplementedException notImplementedException, int i, int i2, int i3) {
        try {
            return new NotImplementedException("Error evaluating cell " + new CellReference(this._workbook.getSheetName(i), i2, i3, false, false).formatAsString(), notImplementedException);
        } catch (Exception e) {
            LOG.atError().withThrowable(e).log("Can't add exception info");
            return notImplementedException;
        }
    }

    static ValueEval getValueFromNonFormulaCell(EvaluationCell evaluationCell) {
        if (evaluationCell == null) {
            return BlankEval.instance;
        }
        CellType cellType = evaluationCell.getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return new NumberEval(evaluationCell.getNumericCellValue());
        }
        if (i == 2) {
            return new StringEval(evaluationCell.getStringCellValue());
        }
        if (i == 3) {
            return BlankEval.instance;
        }
        if (i == 4) {
            return BoolEval.valueOf(evaluationCell.getBooleanCellValue());
        }
        if (i == 5) {
            return ErrorEval.valueOf(evaluationCell.getErrorCellValue());
        }
        throw new RuntimeException("Unexpected cell type (" + cellType + ")");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        r6 = org.apache.poi.ss.formula.eval.FunctionEval.getBasicFunction(((org.apache.poi.ss.formula.ptg.FuncVarPtg) r6).getFunctionIndex()) instanceof org.apache.poi.ss.formula.functions.ArrayMode;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.formula.eval.ValueEval evaluateFormula(org.apache.poi.ss.formula.OperationEvaluationContext r17, org.apache.poi.ss.formula.ptg.Ptg[] r18) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            boolean r0 = r1.dbgEvaluationOutputForNextEval
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L_0x0010
            r1.dbgEvaluationOutputIndent = r5
            r1.dbgEvaluationOutputForNextEval = r4
        L_0x0010:
            int r0 = r1.dbgEvaluationOutputIndent
            if (r0 <= 0) goto L_0x0036
            r6 = 100
            int r0 = r0 * 2
            int r0 = java.lang.Math.min(r6, r0)
            java.lang.String r6 = "                                                                                                    "
            java.lang.String r0 = r6.substring(r4, r0)
            org.apache.logging.log4j.Logger r6 = r1.EVAL_LOG
            org.apache.logging.log4j.LogBuilder r6 = r6.atWarn()
            org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda1 r7 = new org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda1
            r7.<init>(r0, r2, r3)
            r6.log((org.apache.logging.log4j.util.Supplier<org.apache.logging.log4j.message.Message>) r7)
            int r6 = r1.dbgEvaluationOutputIndent
            int r6 = r6 + r5
            r1.dbgEvaluationOutputIndent = r6
            goto L_0x0038
        L_0x0036:
            java.lang.String r0 = ""
        L_0x0038:
            r12 = r0
            org.apache.poi.ss.formula.EvaluationWorkbook r0 = r17.getWorkbook()
            int r6 = r17.getSheetIndex()
            org.apache.poi.ss.formula.EvaluationSheet r0 = r0.getSheet(r6)
            int r6 = r17.getRowIndex()
            int r7 = r17.getColumnIndex()
            org.apache.poi.ss.formula.EvaluationCell r13 = r0.getCell(r6, r7)
            java.util.Stack r14 = new java.util.Stack
            r14.<init>()
            int r15 = r3.length
            r11 = r4
        L_0x0058:
            if (r11 >= r15) goto L_0x0206
            r0 = r3[r11]
            int r6 = r1.dbgEvaluationOutputIndent
            if (r6 <= 0) goto L_0x0074
            org.apache.logging.log4j.Logger r6 = r1.EVAL_LOG
            org.apache.logging.log4j.LogBuilder r6 = r6.atInfo()
            java.lang.String r7 = "{}  * ptg {}: {}, stack: {}"
            java.lang.StringBuilder r9 = org.apache.logging.log4j.util.Unbox.box((int) r11)
            r8 = r12
            r10 = r0
            r4 = r11
            r11 = r14
            r6.log(r7, r8, r9, r10, r11)
            goto L_0x0075
        L_0x0074:
            r4 = r11
        L_0x0075:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.AttrPtg
            if (r6 == 0) goto L_0x0166
            r6 = r0
            org.apache.poi.ss.formula.ptg.AttrPtg r6 = (org.apache.poi.ss.formula.ptg.AttrPtg) r6
            boolean r7 = r6.isSum()
            if (r7 == 0) goto L_0x0084
            org.apache.poi.ss.formula.ptg.OperationPtg r0 = org.apache.poi.ss.formula.ptg.FuncVarPtg.SUM
        L_0x0084:
            boolean r7 = r6.isOptimizedChoose()
            if (r7 == 0) goto L_0x00d0
            java.lang.Object r0 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r0 = (org.apache.poi.ss.formula.eval.ValueEval) r0
            int[] r7 = r6.getJumpTable()
            int r8 = r7.length
            int r9 = r17.getRowIndex()     // Catch:{ EvaluationException -> 0x00b5 }
            int r10 = r17.getColumnIndex()     // Catch:{ EvaluationException -> 0x00b5 }
            int r0 = org.apache.poi.ss.formula.functions.Choose.evaluateFirstArg(r0, r9, r10)     // Catch:{ EvaluationException -> 0x00b5 }
            if (r0 < r5) goto L_0x00ab
            if (r0 <= r8) goto L_0x00a6
            goto L_0x00ab
        L_0x00a6:
            int r0 = r0 + -1
            r0 = r7[r0]     // Catch:{ EvaluationException -> 0x00b5 }
            goto L_0x00c3
        L_0x00ab:
            org.apache.poi.ss.formula.eval.ErrorEval r0 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID     // Catch:{ EvaluationException -> 0x00b5 }
            r14.push(r0)     // Catch:{ EvaluationException -> 0x00b5 }
            int r0 = r6.getChooseFuncOffset()     // Catch:{ EvaluationException -> 0x00b5 }
            goto L_0x00c1
        L_0x00b5:
            r0 = move-exception
            org.apache.poi.ss.formula.eval.ErrorEval r0 = r0.getErrorEval()
            r14.push(r0)
            int r0 = r6.getChooseFuncOffset()
        L_0x00c1:
            int r0 = r0 + 4
        L_0x00c3:
            int r8 = r8 * 2
            int r8 = r8 + 2
            int r0 = r0 - r8
            int r0 = countTokensToBeSkipped(r3, r4, r0)
            int r11 = r4 + r0
            goto L_0x0163
        L_0x00d0:
            boolean r7 = r6.isOptimizedIf()
            if (r7 == 0) goto L_0x013c
            boolean r0 = r13.isPartOfArrayFormulaGroup()
            if (r0 != 0) goto L_0x0200
            java.lang.Object r0 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r0 = (org.apache.poi.ss.formula.eval.ValueEval) r0
            int r7 = r17.getRowIndex()     // Catch:{ EvaluationException -> 0x011b }
            int r8 = r17.getColumnIndex()     // Catch:{ EvaluationException -> 0x011b }
            boolean r7 = org.apache.poi.ss.formula.functions.IfFunc.evaluateFirstArg(r0, r7, r8)     // Catch:{ EvaluationException -> 0x011b }
            if (r7 == 0) goto L_0x00f2
            r11 = r4
            goto L_0x0163
        L_0x00f2:
            short r6 = r6.getData()
            int r6 = countTokensToBeSkipped(r3, r4, r6)
            int r11 = r4 + r6
            int r4 = r11 + 1
            r4 = r3[r4]
            r6 = r3[r11]
            boolean r6 = r6 instanceof org.apache.poi.ss.formula.ptg.AttrPtg
            if (r6 == 0) goto L_0x0163
            boolean r6 = r4 instanceof org.apache.poi.ss.formula.ptg.FuncVarPtg
            if (r6 == 0) goto L_0x0163
            org.apache.poi.ss.formula.ptg.FuncVarPtg r4 = (org.apache.poi.ss.formula.ptg.FuncVarPtg) r4
            short r4 = r4.getFunctionIndex()
            if (r4 != r5) goto L_0x0163
            r14.push(r0)
            org.apache.poi.ss.formula.eval.BoolEval r0 = org.apache.poi.ss.formula.eval.BoolEval.FALSE
            r14.push(r0)
            goto L_0x0163
        L_0x011b:
            r0 = move-exception
            org.apache.poi.ss.formula.eval.ErrorEval r0 = r0.getErrorEval()
            r14.push(r0)
            short r0 = r6.getData()
            int r0 = countTokensToBeSkipped(r3, r4, r0)
            int r11 = r4 + r0
            r0 = r3[r11]
            org.apache.poi.ss.formula.ptg.AttrPtg r0 = (org.apache.poi.ss.formula.ptg.AttrPtg) r0
            short r0 = r0.getData()
            int r0 = r0 + r5
            int r0 = countTokensToBeSkipped(r3, r11, r0)
            int r11 = r11 + r0
            goto L_0x0163
        L_0x013c:
            boolean r7 = r6.isSkip()
            if (r7 == 0) goto L_0x0166
            boolean r7 = r13.isPartOfArrayFormulaGroup()
            if (r7 != 0) goto L_0x0166
            short r0 = r6.getData()
            int r0 = r0 + r5
            int r0 = countTokensToBeSkipped(r3, r4, r0)
            int r11 = r4 + r0
            java.lang.Object r0 = r14.peek()
            org.apache.poi.ss.formula.eval.MissingArgEval r4 = org.apache.poi.ss.formula.eval.MissingArgEval.instance
            if (r0 != r4) goto L_0x0163
            r14.pop()
            org.apache.poi.ss.formula.eval.BlankEval r0 = org.apache.poi.ss.formula.eval.BlankEval.instance
            r14.push(r0)
        L_0x0163:
            r6 = 0
            goto L_0x0202
        L_0x0166:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.ControlPtg
            if (r6 == 0) goto L_0x016c
            goto L_0x0200
        L_0x016c:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.MemFuncPtg
            if (r6 != 0) goto L_0x0200
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.MemAreaPtg
            if (r6 == 0) goto L_0x0176
            goto L_0x0200
        L_0x0176:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.MemErrPtg
            if (r6 == 0) goto L_0x017c
            goto L_0x0200
        L_0x017c:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.UnionPtg
            if (r6 == 0) goto L_0x0196
            java.lang.Object r0 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r0 = (org.apache.poi.ss.formula.eval.ValueEval) r0
            java.lang.Object r6 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r6 = (org.apache.poi.ss.formula.eval.ValueEval) r6
            org.apache.poi.ss.formula.eval.RefListEval r7 = new org.apache.poi.ss.formula.eval.RefListEval
            r7.<init>(r6, r0)
            r14.push(r7)
            goto L_0x0200
        L_0x0196:
            boolean r6 = r0 instanceof org.apache.poi.ss.formula.ptg.OperationPtg
            if (r6 == 0) goto L_0x01de
            org.apache.poi.ss.formula.ptg.OperationPtg r0 = (org.apache.poi.ss.formula.ptg.OperationPtg) r0
            int r6 = r0.getNumberOfOperands()
            org.apache.poi.ss.formula.eval.ValueEval[] r7 = new org.apache.poi.ss.formula.eval.ValueEval[r6]
            int r6 = r6 - r5
            r8 = 0
        L_0x01a4:
            if (r6 < 0) goto L_0x01b6
            java.lang.Object r9 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r9 = (org.apache.poi.ss.formula.eval.ValueEval) r9
            r7[r6] = r9
            boolean r9 = r9 instanceof org.apache.poi.ss.formula.eval.AreaEval
            if (r9 == 0) goto L_0x01b3
            r8 = r5
        L_0x01b3:
            int r6 = r6 + -1
            goto L_0x01a4
        L_0x01b6:
            if (r8 == 0) goto L_0x01d1
            r11 = r4
        L_0x01b9:
            if (r11 >= r15) goto L_0x01d1
            r6 = r3[r11]
            boolean r8 = r6 instanceof org.apache.poi.ss.formula.ptg.FuncVarPtg
            if (r8 == 0) goto L_0x01ce
            org.apache.poi.ss.formula.ptg.FuncVarPtg r6 = (org.apache.poi.ss.formula.ptg.FuncVarPtg) r6
            short r6 = r6.getFunctionIndex()     // Catch:{ NotImplementedException -> 0x01d1 }
            org.apache.poi.ss.formula.functions.Function r6 = org.apache.poi.ss.formula.eval.FunctionEval.getBasicFunction(r6)     // Catch:{ NotImplementedException -> 0x01d1 }
            boolean r6 = r6 instanceof org.apache.poi.ss.formula.functions.ArrayMode     // Catch:{ NotImplementedException -> 0x01d1 }
            goto L_0x01d2
        L_0x01ce:
            int r11 = r11 + 1
            goto L_0x01b9
        L_0x01d1:
            r6 = 0
        L_0x01d2:
            r2.setArrayMode(r6)
            org.apache.poi.ss.formula.eval.ValueEval r0 = org.apache.poi.ss.formula.OperationEvaluatorFactory.evaluate(r0, r7, r2)
            r6 = 0
            r2.setArrayMode(r6)
            goto L_0x01e3
        L_0x01de:
            r6 = 0
            org.apache.poi.ss.formula.eval.ValueEval r0 = r1.getEvalForPtg(r0, r2)
        L_0x01e3:
            if (r0 == 0) goto L_0x01f8
            r14.push(r0)
            int r7 = r1.dbgEvaluationOutputIndent
            if (r7 <= 0) goto L_0x0201
            org.apache.logging.log4j.Logger r7 = r1.EVAL_LOG
            org.apache.logging.log4j.LogBuilder r7 = r7.atInfo()
            java.lang.String r8 = "{}    = {}"
            r7.log(r8, r12, r0)
            goto L_0x0201
        L_0x01f8:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Evaluation result must not be null"
            r0.<init>(r1)
            throw r0
        L_0x0200:
            r6 = 0
        L_0x0201:
            r11 = r4
        L_0x0202:
            int r11 = r11 + r5
            r4 = r6
            goto L_0x0058
        L_0x0206:
            java.lang.Object r0 = r14.pop()
            org.apache.poi.ss.formula.eval.ValueEval r0 = (org.apache.poi.ss.formula.eval.ValueEval) r0
            boolean r3 = r14.isEmpty()
            if (r3 == 0) goto L_0x0247
            boolean r3 = r17.isSingleValue()
            if (r3 == 0) goto L_0x021c
            org.apache.poi.ss.formula.eval.ValueEval r0 = dereferenceResult(r0, r2)
        L_0x021c:
            int r3 = r1.dbgEvaluationOutputIndent
            if (r3 <= 0) goto L_0x0246
            org.apache.logging.log4j.Logger r3 = r1.EVAL_LOG
            org.apache.logging.log4j.LogBuilder r3 = r3.atInfo()
            org.apache.poi.ss.util.CellReference r4 = new org.apache.poi.ss.util.CellReference
            int r6 = r17.getRowIndex()
            int r2 = r17.getColumnIndex()
            r4.<init>((int) r6, (int) r2)
            java.lang.String r2 = r4.formatAsString()
            java.lang.String r4 = "{}finished eval of {}: {}"
            r3.log(r4, r12, r2, r0)
            int r2 = r1.dbgEvaluationOutputIndent
            int r2 = r2 - r5
            r1.dbgEvaluationOutputIndent = r2
            if (r2 != r5) goto L_0x0246
            r2 = -1
            r1.dbgEvaluationOutputIndent = r2
        L_0x0246:
            return r0
        L_0x0247:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "evaluation stack not empty"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.WorkbookEvaluator.evaluateFormula(org.apache.poi.ss.formula.OperationEvaluationContext, org.apache.poi.ss.formula.ptg.Ptg[]):org.apache.poi.ss.formula.eval.ValueEval");
    }

    static /* synthetic */ Message lambda$evaluateFormula$1(String str, OperationEvaluationContext operationEvaluationContext, Ptg[] ptgArr) {
        return new SimpleMessage(str + "- evaluateFormula('" + operationEvaluationContext.getRefEvaluatorForCurrentSheet().getSheetNameRange() + "'/" + new CellReference(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex()).formatAsString() + "): " + Arrays.toString(ptgArr).replace("\\Qorg.apache.poi.ss.formula.ptg.\\E", ""));
    }

    private static int countTokensToBeSkipped(Ptg[] ptgArr, int i, int i2) {
        int i3 = i;
        while (i2 != 0) {
            i3++;
            if (i3 < ptgArr.length) {
                i2 -= ptgArr[i3].getSize();
                if (i2 < 0) {
                    throw new RuntimeException("Bad skip distance (wrong token size calculation).");
                }
            } else {
                throw new RuntimeException("Skip distance too far (ran out of formula tokens).");
            }
        }
        return i3 - i;
    }

    private static ValueEval dereferenceResult(ValueEval valueEval, OperationEvaluationContext operationEvaluationContext) {
        ValueEval valueEval2;
        if (operationEvaluationContext == null) {
            throw new IllegalArgumentException("OperationEvaluationContext ec is null");
        } else if (operationEvaluationContext.getWorkbook() != null) {
            EvaluationCell cell = operationEvaluationContext.getWorkbook().getSheet(operationEvaluationContext.getSheetIndex()).getCell(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            if (cell == null || !cell.isPartOfArrayFormulaGroup() || !(valueEval instanceof AreaEval)) {
                valueEval2 = dereferenceResult(valueEval, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            } else {
                valueEval2 = OperandResolver.getElementFromArray((AreaEval) valueEval, cell);
            }
            if (valueEval2 == BlankEval.instance) {
                return NumberEval.ZERO;
            }
            return valueEval2;
        } else {
            throw new IllegalArgumentException("OperationEvaluationContext ec.getWorkbook() is null");
        }
    }

    public static ValueEval dereferenceResult(ValueEval valueEval, int i, int i2) {
        try {
            ValueEval singleValue = OperandResolver.getSingleValue(valueEval, i, i2);
            return singleValue == BlankEval.instance ? NumberEval.ZERO : singleValue;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private ValueEval getEvalForPtg(Ptg ptg, OperationEvaluationContext operationEvaluationContext) {
        if (ptg instanceof NamePtg) {
            return getEvalForNameRecord(this._workbook.getName((NamePtg) ptg), operationEvaluationContext);
        } else if (ptg instanceof NameXPtg) {
            return processNameEval(operationEvaluationContext.getNameXEval((NameXPtg) ptg), operationEvaluationContext);
        } else {
            if (ptg instanceof NameXPxg) {
                return processNameEval(operationEvaluationContext.getNameXEval((NameXPxg) ptg), operationEvaluationContext);
            }
            if (ptg instanceof IntPtg) {
                return new NumberEval((double) ((IntPtg) ptg).getValue());
            }
            if (ptg instanceof NumberPtg) {
                return new NumberEval(((NumberPtg) ptg).getValue());
            }
            if (ptg instanceof StringPtg) {
                return new StringEval(((StringPtg) ptg).getValue());
            }
            if (ptg instanceof BoolPtg) {
                return BoolEval.valueOf(((BoolPtg) ptg).getValue());
            }
            if (ptg instanceof ErrPtg) {
                return ErrorEval.valueOf(((ErrPtg) ptg).getErrorCode());
            }
            if (ptg instanceof MissingArgPtg) {
                return MissingArgEval.instance;
            }
            if ((ptg instanceof AreaErrPtg) || (ptg instanceof RefErrorPtg) || (ptg instanceof DeletedArea3DPtg) || (ptg instanceof DeletedRef3DPtg)) {
                return ErrorEval.REF_INVALID;
            }
            if (ptg instanceof Ref3DPtg) {
                return operationEvaluationContext.getRef3DEval((Ref3DPtg) ptg);
            }
            if (ptg instanceof Ref3DPxg) {
                return operationEvaluationContext.getRef3DEval((Ref3DPxg) ptg);
            }
            if (ptg instanceof Area3DPtg) {
                return operationEvaluationContext.getArea3DEval((Area3DPtg) ptg);
            }
            if (ptg instanceof Area3DPxg) {
                return operationEvaluationContext.getArea3DEval((Area3DPxg) ptg);
            }
            if (ptg instanceof RefPtg) {
                RefPtg refPtg = (RefPtg) ptg;
                return operationEvaluationContext.getRefEval(refPtg.getRow(), refPtg.getColumn());
            } else if (ptg instanceof AreaPtg) {
                AreaPtg areaPtg = (AreaPtg) ptg;
                return operationEvaluationContext.getAreaEval(areaPtg.getFirstRow(), areaPtg.getFirstColumn(), areaPtg.getLastRow(), areaPtg.getLastColumn());
            } else if (ptg instanceof ArrayPtg) {
                ArrayPtg arrayPtg = (ArrayPtg) ptg;
                return operationEvaluationContext.getAreaValueEval(0, 0, arrayPtg.getRowCount() - 1, arrayPtg.getColumnCount() - 1, arrayPtg.getTokenArrayValues());
            } else if (ptg instanceof UnknownPtg) {
                throw new RuntimeException("UnknownPtg not allowed");
            } else if (ptg instanceof ExpPtg) {
                throw new RuntimeException("ExpPtg currently not supported");
            } else {
                throw new RuntimeException("Unexpected ptg class (" + ptg.getClass().getName() + ")");
            }
        }
    }

    private ValueEval processNameEval(ValueEval valueEval, OperationEvaluationContext operationEvaluationContext) {
        return valueEval instanceof ExternalNameEval ? getEvalForNameRecord(((ExternalNameEval) valueEval).getName(), operationEvaluationContext) : valueEval;
    }

    private ValueEval getEvalForNameRecord(EvaluationName evaluationName, OperationEvaluationContext operationEvaluationContext) {
        if (evaluationName.isFunctionName()) {
            return new FunctionNameEval(evaluationName.getNameText());
        }
        if (evaluationName.hasFormula()) {
            return evaluateNameFormula(evaluationName.getNameDefinition(), operationEvaluationContext);
        }
        throw new RuntimeException("Don't know how to evaluate name '" + evaluationName.getNameText() + "'");
    }

    /* access modifiers changed from: package-private */
    public ValueEval evaluateNameFormula(Ptg[] ptgArr, OperationEvaluationContext operationEvaluationContext) {
        if (ptgArr.length == 1) {
            Ptg ptg = ptgArr[0];
            if (!(ptg instanceof FuncVarPtg)) {
                return getEvalForPtg(ptg, operationEvaluationContext);
            }
        }
        return evaluateFormula(new OperationEvaluationContext(this, operationEvaluationContext.getWorkbook(), operationEvaluationContext.getSheetIndex(), operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex(), new EvaluationTracker(this._cache), false), ptgArr);
    }

    /* access modifiers changed from: package-private */
    public ValueEval evaluateReference(EvaluationSheet evaluationSheet, int i, int i2, int i3, EvaluationTracker evaluationTracker) {
        return evaluateAny(evaluationSheet.getCell(i2, i3), i, i2, i3, evaluationTracker);
    }

    public FreeRefFunction findUserDefinedFunction(String str) {
        return this._udfFinder.findFunction(str);
    }

    public ValueEval evaluate(String str, CellReference cellReference) {
        int i;
        int i2;
        String sheetName = cellReference == null ? null : cellReference.getSheetName();
        short s = -1;
        if (sheetName == null) {
            i = -1;
        } else {
            i = getWorkbook().getSheetIndex(sheetName);
        }
        if (cellReference == null) {
            i2 = -1;
        } else {
            i2 = cellReference.getRow();
        }
        if (cellReference != null) {
            s = cellReference.getCol();
        }
        return evaluateNameFormula(FormulaParser.parse(str, (FormulaParsingWorkbook) getWorkbook(), FormulaType.CELL, i, i2), new OperationEvaluationContext(this, getWorkbook(), i, i2, s, new EvaluationTracker(this._cache)));
    }

    public ValueEval evaluate(String str, CellReference cellReference, CellRangeAddressBase cellRangeAddressBase) {
        return evaluate(str, cellReference, cellRangeAddressBase, FormulaType.CELL);
    }

    public ValueEval evaluateList(String str, CellReference cellReference, CellRangeAddressBase cellRangeAddressBase) {
        return evaluate(str, cellReference, cellRangeAddressBase, FormulaType.DATAVALIDATION_LIST);
    }

    private ValueEval evaluate(String str, CellReference cellReference, CellRangeAddressBase cellRangeAddressBase, FormulaType formulaType) {
        String sheetName = cellReference == null ? null : cellReference.getSheetName();
        if (sheetName != null) {
            int sheetIndex = getWorkbook().getSheetIndex(sheetName);
            Ptg[] parse = FormulaParser.parse(str, (FormulaParsingWorkbook) getWorkbook(), formulaType, sheetIndex, cellReference.getRow());
            adjustRegionRelativeReference(parse, cellReference, cellRangeAddressBase);
            return evaluateNameFormula(parse, new OperationEvaluationContext(this, getWorkbook(), sheetIndex, cellReference.getRow(), cellReference.getCol(), new EvaluationTracker(this._cache), formulaType.isSingleValue()));
        }
        throw new IllegalArgumentException("Sheet name is required");
    }

    private boolean adjustRegionRelativeReference(Ptg[] ptgArr, CellReference cellReference, CellRangeAddressBase cellRangeAddressBase) {
        int row = cellReference.getRow() - cellRangeAddressBase.getFirstRow();
        int col = cellReference.getCol() - cellRangeAddressBase.getFirstColumn();
        boolean z = false;
        for (RefPtgBase refPtgBase : ptgArr) {
            if (refPtgBase instanceof RefPtgBase) {
                RefPtgBase refPtgBase2 = refPtgBase;
                SpreadsheetVersion spreadsheetVersion = this._workbook.getSpreadsheetVersion();
                if (refPtgBase2.isRowRelative() && row > 0) {
                    int row2 = refPtgBase2.getRow() + row;
                    if (row2 <= spreadsheetVersion.getMaxRows()) {
                        refPtgBase2.setRow(row2);
                        z = true;
                    } else {
                        throw new IndexOutOfBoundsException(spreadsheetVersion.name() + " files can only have " + spreadsheetVersion.getMaxRows() + " rows, but row " + row2 + " was requested.");
                    }
                }
                if (refPtgBase2.isColRelative() && col > 0) {
                    int column = refPtgBase2.getColumn() + col;
                    if (column <= spreadsheetVersion.getMaxColumns()) {
                        refPtgBase2.setColumn(column);
                        z = true;
                    } else {
                        throw new IndexOutOfBoundsException(spreadsheetVersion.name() + " files can only have " + spreadsheetVersion.getMaxColumns() + " columns, but column " + column + " was requested.");
                    }
                }
            }
        }
        return z;
    }

    public void setIgnoreMissingWorkbooks(boolean z) {
        this._ignoreMissingWorkbooks = z;
    }

    public boolean isIgnoreMissingWorkbooks() {
        return this._ignoreMissingWorkbooks;
    }

    public static Collection<String> getSupportedFunctionNames() {
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(FunctionEval.getSupportedFunctionNames());
        treeSet.addAll(AnalysisToolPak.getSupportedFunctionNames());
        return Collections.unmodifiableCollection(treeSet);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(FunctionEval.getNotSupportedFunctionNames());
        treeSet.addAll(AnalysisToolPak.getNotSupportedFunctionNames());
        return Collections.unmodifiableCollection(treeSet);
    }

    public static void registerFunction(String str, FreeRefFunction freeRefFunction) {
        AnalysisToolPak.registerFunction(str, freeRefFunction);
    }

    public static void registerFunction(String str, Function function) {
        FunctionEval.registerFunction(str, function);
    }

    public void setDebugEvaluationOutputForNextEval(boolean z) {
        this.dbgEvaluationOutputForNextEval = z;
    }

    public boolean isDebugEvaluationOutputForNextEval() {
        return this.dbgEvaluationOutputForNextEval;
    }
}
