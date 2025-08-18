package org.apache.poi.ss.formula;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;

public final class OperationEvaluationContext {
    public static final FreeRefFunction UDF = UserDefinedFunction.instance;
    private final WorkbookEvaluator _bookEvaluator;
    private final int _columnIndex;
    private boolean _isInArrayContext;
    private final boolean _isSingleValue;
    private final int _rowIndex;
    private final int _sheetIndex;
    private final EvaluationTracker _tracker;
    private final EvaluationWorkbook _workbook;

    public OperationEvaluationContext(WorkbookEvaluator workbookEvaluator, EvaluationWorkbook evaluationWorkbook, int i, int i2, int i3, EvaluationTracker evaluationTracker) {
        this(workbookEvaluator, evaluationWorkbook, i, i2, i3, evaluationTracker, true);
    }

    public OperationEvaluationContext(WorkbookEvaluator workbookEvaluator, EvaluationWorkbook evaluationWorkbook, int i, int i2, int i3, EvaluationTracker evaluationTracker, boolean z) {
        this._bookEvaluator = workbookEvaluator;
        this._workbook = evaluationWorkbook;
        this._sheetIndex = i;
        this._rowIndex = i2;
        this._columnIndex = i3;
        this._tracker = evaluationTracker;
        this._isSingleValue = z;
    }

    public boolean isArraymode() {
        return this._isInArrayContext;
    }

    public void setArrayMode(boolean z) {
        this._isInArrayContext = z;
    }

    public EvaluationWorkbook getWorkbook() {
        return this._workbook;
    }

    public int getRowIndex() {
        return this._rowIndex;
    }

    public int getColumnIndex() {
        return this._columnIndex;
    }

    /* access modifiers changed from: package-private */
    public SheetRangeEvaluator createExternSheetRefEvaluator(ExternSheetReferenceToken externSheetReferenceToken) {
        return createExternSheetRefEvaluator(externSheetReferenceToken.getExternSheetIndex());
    }

    /* access modifiers changed from: package-private */
    public SheetRangeEvaluator createExternSheetRefEvaluator(String str, String str2, int i) {
        return createExternSheetRefEvaluator(this._workbook.getExternalSheet(str, str2, i));
    }

    /* access modifiers changed from: package-private */
    public SheetRangeEvaluator createExternSheetRefEvaluator(int i) {
        return createExternSheetRefEvaluator(this._workbook.getExternalSheet(i));
    }

    /* access modifiers changed from: package-private */
    public SheetRangeEvaluator createExternSheetRefEvaluator(EvaluationWorkbook.ExternalSheet externalSheet) {
        int i;
        int i2;
        WorkbookEvaluator workbookEvaluator;
        if (externalSheet == null || externalSheet.getWorkbookName() == null) {
            workbookEvaluator = this._bookEvaluator;
            if (externalSheet == null) {
                i2 = 0;
            } else {
                i2 = this._workbook.getSheetIndex(externalSheet.getSheetName());
            }
            i = externalSheet instanceof EvaluationWorkbook.ExternalSheetRange ? this._workbook.getSheetIndex(((EvaluationWorkbook.ExternalSheetRange) externalSheet).getLastSheetName()) : -1;
        } else {
            String workbookName = externalSheet.getWorkbookName();
            try {
                workbookEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(workbookName);
                i2 = workbookEvaluator.getSheetIndex(externalSheet.getSheetName());
                i = externalSheet instanceof EvaluationWorkbook.ExternalSheetRange ? workbookEvaluator.getSheetIndex(((EvaluationWorkbook.ExternalSheetRange) externalSheet).getLastSheetName()) : -1;
                if (i2 < 0) {
                    throw new RuntimeException("Invalid sheet name '" + externalSheet.getSheetName() + "' in bool '" + workbookName + "'.");
                }
            } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        if (i == -1) {
            i = i2;
        }
        int i3 = (i - i2) + 1;
        SheetRefEvaluator[] sheetRefEvaluatorArr = new SheetRefEvaluator[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            sheetRefEvaluatorArr[i4] = new SheetRefEvaluator(workbookEvaluator, this._tracker, i4 + i2);
        }
        return new SheetRangeEvaluator(i2, i, sheetRefEvaluatorArr);
    }

    private SheetRefEvaluator createExternSheetRefEvaluator(String str, String str2) {
        WorkbookEvaluator workbookEvaluator;
        if (str == null) {
            workbookEvaluator = this._bookEvaluator;
        } else if (str2 != null) {
            try {
                workbookEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(str);
            } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException unused) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("sheetName must not be null if workbookName is provided");
        }
        int sheetIndex = str2 == null ? this._sheetIndex : workbookEvaluator.getSheetIndex(str2);
        if (sheetIndex < 0) {
            return null;
        }
        return new SheetRefEvaluator(workbookEvaluator, this._tracker, sheetIndex);
    }

    public SheetRangeEvaluator getRefEvaluatorForCurrentSheet() {
        return new SheetRangeEvaluator(this._sheetIndex, new SheetRefEvaluator(this._bookEvaluator, this._tracker, this._sheetIndex));
    }

    public ValueEval getDynamicReference(String str, String str2, String str3, String str4, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        CellReference cellReference;
        CellReference cellReference2;
        int i5;
        int i6;
        CellReference cellReference3;
        String str5 = str3;
        String str6 = str4;
        SheetRefEvaluator createExternSheetRefEvaluator = createExternSheetRefEvaluator(str, str2);
        if (createExternSheetRefEvaluator == null) {
            return ErrorEval.REF_INVALID;
        }
        SheetRangeEvaluator sheetRangeEvaluator = new SheetRangeEvaluator(this._sheetIndex, createExternSheetRefEvaluator);
        SpreadsheetVersion spreadsheetVersion = this._workbook.getSpreadsheetVersion();
        CellReference.NameType classifyCellReference = z ? classifyCellReference(str5, spreadsheetVersion) : getR1C1CellType(str3);
        int i7 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$util$CellReference$NameType[classifyCellReference.ordinal()];
        if (i7 == 1) {
            return ErrorEval.REF_INVALID;
        }
        if (i7 == 2) {
            EvaluationName name = this._workbook.getName(str5, this._sheetIndex);
            if (name == null) {
                throw new RuntimeException("Specified name '" + str5 + "' is not found in the workbook (sheetIndex=" + this._sheetIndex + ").");
            } else if (name.isRange()) {
                return this._bookEvaluator.evaluateNameFormula(name.getNameDefinition(), this);
            } else {
                throw new RuntimeException("Specified name '" + str5 + "' is not a range as expected.");
            }
        } else if (str6 == null) {
            int i8 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$util$CellReference$NameType[classifyCellReference.ordinal()];
            if (i8 != 3) {
                if (i8 != 4) {
                    if (i8 == 5) {
                        if (z) {
                            cellReference3 = new CellReference(str5);
                        } else {
                            cellReference3 = applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), str5);
                        }
                        return new LazyRefEval(cellReference3.getRow(), cellReference3.getCol(), sheetRangeEvaluator);
                    }
                    throw new IllegalStateException("Unexpected reference classification of '" + str5 + "'.");
                } else if (z) {
                    return ErrorEval.REF_INVALID;
                } else {
                    try {
                        String trim = str5.substring(str5.toUpperCase(LocaleUtil.getUserLocale()).indexOf(82) + 1).trim();
                        if (trim.startsWith("[") && trim.endsWith("]")) {
                            i6 = getRowIndex() + Integer.parseInt(trim.substring(1, trim.length() - 1).trim());
                        } else if (trim.isEmpty()) {
                            return ErrorEval.REF_INVALID;
                        } else {
                            i6 = Integer.parseInt(trim) - 1;
                        }
                        return new LazyAreaEval(i6, 0, i6, spreadsheetVersion.getLastColumnIndex(), sheetRangeEvaluator);
                    } catch (Exception unused) {
                        return ErrorEval.REF_INVALID;
                    }
                }
            } else if (z) {
                return ErrorEval.REF_INVALID;
            } else {
                try {
                    String trim2 = str5.substring(str5.toUpperCase(LocaleUtil.getUserLocale()).indexOf(67) + 1).trim();
                    if (trim2.startsWith("[") && trim2.endsWith("]")) {
                        i5 = getColumnIndex() + Integer.parseInt(trim2.substring(1, trim2.length() - 1).trim());
                    } else if (trim2.isEmpty()) {
                        return ErrorEval.REF_INVALID;
                    } else {
                        i5 = Integer.parseInt(trim2) - 1;
                    }
                    return new LazyAreaEval(0, i5, spreadsheetVersion.getLastRowIndex(), i5, sheetRangeEvaluator);
                } catch (Exception unused2) {
                    return ErrorEval.REF_INVALID;
                }
            }
        } else {
            CellReference.NameType classifyCellReference2 = z ? classifyCellReference(str6, spreadsheetVersion) : getR1C1CellType(str4);
            int i9 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$util$CellReference$NameType[classifyCellReference2.ordinal()];
            if (i9 == 1) {
                return ErrorEval.REF_INVALID;
            }
            if (i9 == 2) {
                throw new RuntimeException("Cannot evaluate '" + str5 + "'. Indirect evaluation of defined names not supported yet");
            } else if (classifyCellReference2 != classifyCellReference) {
                return ErrorEval.REF_INVALID;
            } else {
                int i10 = AnonymousClass1.$SwitchMap$org$apache$poi$ss$util$CellReference$NameType[classifyCellReference.ordinal()];
                if (i10 == 3) {
                    int lastRowIndex = spreadsheetVersion.getLastRowIndex();
                    i = parseRowRef(str3);
                    i3 = 0;
                    i2 = lastRowIndex;
                    i4 = parseRowRef(str4);
                } else if (i10 == 4) {
                    i4 = spreadsheetVersion.getLastColumnIndex();
                    i3 = parseColRef(str3);
                    i2 = parseColRef(str4);
                    i = 0;
                } else if (i10 == 5) {
                    if (z) {
                        cellReference = new CellReference(str5);
                    } else {
                        cellReference = applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), str5);
                    }
                    i3 = cellReference.getRow();
                    i = cellReference.getCol();
                    if (z) {
                        cellReference2 = new CellReference(str6);
                    } else {
                        cellReference2 = applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), str6);
                    }
                    i2 = cellReference2.getRow();
                    i4 = cellReference2.getCol();
                } else {
                    throw new IllegalStateException("Unexpected reference classification of '" + str5 + "'.");
                }
                return new LazyAreaEval(i3, i, i2, i4, sheetRangeEvaluator);
            }
        }
    }

    /* renamed from: org.apache.poi.ss.formula.OperationEvaluationContext$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$util$CellReference$NameType;

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
                org.apache.poi.ss.util.CellReference$NameType[] r0 = org.apache.poi.ss.util.CellReference.NameType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$util$CellReference$NameType = r0
                org.apache.poi.ss.util.CellReference$NameType r1 = org.apache.poi.ss.util.CellReference.NameType.BAD_CELL_OR_NAMED_RANGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$util$CellReference$NameType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.util.CellReference$NameType r1 = org.apache.poi.ss.util.CellReference.NameType.NAMED_RANGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$util$CellReference$NameType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.util.CellReference$NameType r1 = org.apache.poi.ss.util.CellReference.NameType.COLUMN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$util$CellReference$NameType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.util.CellReference$NameType r1 = org.apache.poi.ss.util.CellReference.NameType.ROW     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$util$CellReference$NameType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.util.CellReference$NameType r1 = org.apache.poi.ss.util.CellReference.NameType.CELL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.OperationEvaluationContext.AnonymousClass1.<clinit>():void");
        }
    }

    private static int parseRowRef(String str) {
        return CellReference.convertColStringToIndex(str);
    }

    private static int parseColRef(String str) {
        return Integer.parseInt(str) - 1;
    }

    private static CellReference.NameType classifyCellReference(String str, SpreadsheetVersion spreadsheetVersion) {
        if (str.length() < 1) {
            return CellReference.NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return CellReference.classifyCellReference(str, spreadsheetVersion);
    }

    public FreeRefFunction findUserDefinedFunction(String str) {
        return this._bookEvaluator.findUserDefinedFunction(str);
    }

    public ValueEval getRefEval(int i, int i2) {
        return new LazyRefEval(i, i2, getRefEvaluatorForCurrentSheet());
    }

    public ValueEval getRef3DEval(Ref3DPtg ref3DPtg) {
        return new LazyRefEval(ref3DPtg.getRow(), ref3DPtg.getColumn(), createExternSheetRefEvaluator(ref3DPtg.getExternSheetIndex()));
    }

    public ValueEval getRef3DEval(Ref3DPxg ref3DPxg) {
        return new LazyRefEval(ref3DPxg.getRow(), ref3DPxg.getColumn(), createExternSheetRefEvaluator(ref3DPxg.getSheetName(), ref3DPxg.getLastSheetName(), ref3DPxg.getExternalWorkbookNumber()));
    }

    public ValueEval getAreaEval(int i, int i2, int i3, int i4) {
        return new LazyAreaEval(i, i2, i3, i4, getRefEvaluatorForCurrentSheet());
    }

    public ValueEval getArea3DEval(Area3DPtg area3DPtg) {
        return new LazyAreaEval(area3DPtg.getFirstRow(), area3DPtg.getFirstColumn(), area3DPtg.getLastRow(), area3DPtg.getLastColumn(), createExternSheetRefEvaluator(area3DPtg.getExternSheetIndex()));
    }

    public ValueEval getArea3DEval(Area3DPxg area3DPxg) {
        return new LazyAreaEval(area3DPxg.getFirstRow(), area3DPxg.getFirstColumn(), area3DPxg.getLastRow(), area3DPxg.getLastColumn(), createExternSheetRefEvaluator(area3DPxg.getSheetName(), area3DPxg.getLastSheetName(), area3DPxg.getExternalWorkbookNumber()));
    }

    public ValueEval getAreaValueEval(int i, int i2, int i3, int i4, Object[][] objArr) {
        ValueEval[] valueEvalArr = new ValueEval[(objArr.length * objArr[0].length)];
        int i5 = 0;
        for (Object[] objArr2 : objArr) {
            int i6 = 0;
            while (i6 < objArr[0].length) {
                valueEvalArr[i5] = convertObjectEval(objArr2[i6]);
                i6++;
                i5++;
            }
        }
        return new CacheAreaEval(i, i2, i3, i4, valueEvalArr);
    }

    private ValueEval convertObjectEval(Object obj) {
        if (obj == null) {
            throw new RuntimeException("Array item cannot be null");
        } else if (obj instanceof String) {
            return new StringEval((String) obj);
        } else {
            if (obj instanceof Double) {
                return new NumberEval(((Double) obj).doubleValue());
            }
            if (obj instanceof Boolean) {
                return BoolEval.valueOf(((Boolean) obj).booleanValue());
            }
            if (obj instanceof ErrorConstant) {
                return ErrorEval.valueOf(((ErrorConstant) obj).getErrorCode());
            }
            throw new IllegalArgumentException("Unexpected constant class (" + obj.getClass().getName() + ")");
        }
    }

    public ValueEval getNameXEval(NameXPtg nameXPtg) {
        EvaluationWorkbook.ExternalSheet externalSheet = this._workbook.getExternalSheet(nameXPtg.getSheetRefIndex());
        if (externalSheet == null || externalSheet.getWorkbookName() == null) {
            return getLocalNameXEval(nameXPtg);
        }
        return getExternalNameXEval(this._workbook.getExternalName(nameXPtg.getSheetRefIndex(), nameXPtg.getNameIndex()), externalSheet.getWorkbookName());
    }

    public ValueEval getNameXEval(NameXPxg nameXPxg) {
        EvaluationWorkbook.ExternalSheet externalSheet = this._workbook.getExternalSheet(nameXPxg.getSheetName(), (String) null, nameXPxg.getExternalWorkbookNumber());
        if (externalSheet == null || externalSheet.getWorkbookName() == null) {
            return getLocalNameXEval(nameXPxg);
        }
        return getExternalNameXEval(this._workbook.getExternalName(nameXPxg.getNameName(), nameXPxg.getSheetName(), nameXPxg.getExternalWorkbookNumber()), externalSheet.getWorkbookName());
    }

    private ValueEval getLocalNameXEval(NameXPxg nameXPxg) {
        int sheetIndex = nameXPxg.getSheetName() != null ? this._workbook.getSheetIndex(nameXPxg.getSheetName()) : -1;
        String nameName = nameXPxg.getNameName();
        EvaluationName name = this._workbook.getName(nameName, sheetIndex);
        if (name != null) {
            return new ExternalNameEval(name);
        }
        return new FunctionNameEval(nameName);
    }

    private ValueEval getLocalNameXEval(NameXPtg nameXPtg) {
        EvaluationName evaluationName;
        String resolveNameXText = this._workbook.resolveNameXText(nameXPtg);
        int indexOf = resolveNameXText.indexOf(33);
        if (indexOf > -1) {
            String substring = resolveNameXText.substring(0, indexOf);
            String substring2 = resolveNameXText.substring(indexOf + 1);
            EvaluationWorkbook evaluationWorkbook = this._workbook;
            evaluationName = evaluationWorkbook.getName(substring2, evaluationWorkbook.getSheetIndex(substring));
        } else {
            evaluationName = this._workbook.getName(resolveNameXText, -1);
        }
        if (evaluationName != null) {
            return new ExternalNameEval(evaluationName);
        }
        return new FunctionNameEval(resolveNameXText);
    }

    public int getSheetIndex() {
        return this._sheetIndex;
    }

    public boolean isSingleValue() {
        return this._isSingleValue;
    }

    private ValueEval getExternalNameXEval(EvaluationWorkbook.ExternalName externalName, String str) {
        try {
            WorkbookEvaluator otherWorkbookEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(str);
            EvaluationName name = otherWorkbookEvaluator.getName(externalName.getName(), externalName.getIx() - 1);
            if (name != null && name.hasFormula()) {
                if (name.getNameDefinition().length <= 1) {
                    OperationEvaluationContext operationEvaluationContext = new OperationEvaluationContext(otherWorkbookEvaluator, otherWorkbookEvaluator.getWorkbook(), -1, -1, -1, this._tracker);
                    Ptg ptg = name.getNameDefinition()[0];
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
                } else {
                    throw new RuntimeException("Complex name formulas not supported yet");
                }
            }
            return ErrorEval.REF_INVALID;
        } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException unused) {
            return ErrorEval.REF_INVALID;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.ss.util.CellReference applyR1C1Reference(org.apache.poi.ss.util.CellReference r7, java.lang.String r8) {
        /*
            java.util.Locale r0 = org.apache.poi.util.LocaleUtil.getUserLocale()
            java.lang.String r0 = r8.toUpperCase(r0)
            r1 = 82
            int r1 = r0.indexOf(r1)
            r2 = 67
            int r0 = r0.indexOf(r2)
            if (r1 < 0) goto L_0x00a0
            if (r0 <= r1) goto L_0x00a0
            r2 = 1
            int r1 = r1 + r2
            java.lang.String r1 = r8.substring(r1, r0)
            java.lang.String r1 = r1.trim()
            int r0 = r0 + r2
            java.lang.String r8 = r8.substring(r0)
            java.lang.String r8 = r8.trim()
            java.lang.String r0 = "["
            boolean r3 = r1.startsWith(r0)
            java.lang.String r4 = "]"
            r5 = 0
            r6 = -1
            if (r3 == 0) goto L_0x0050
            boolean r3 = r1.endsWith(r4)
            if (r3 == 0) goto L_0x0050
            int r3 = r1.length()
            int r3 = r3 - r2
            java.lang.String r1 = r1.substring(r2, r3)
            java.lang.String r1 = r1.trim()
            int r1 = java.lang.Integer.parseInt(r1)
            r3 = r1
            goto L_0x005d
        L_0x0050:
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x005c
            int r1 = java.lang.Integer.parseInt(r1)
            r3 = r5
            goto L_0x005e
        L_0x005c:
            r3 = r5
        L_0x005d:
            r1 = r6
        L_0x005e:
            boolean r0 = r8.startsWith(r0)
            if (r0 == 0) goto L_0x007c
            boolean r0 = r8.endsWith(r4)
            if (r0 == 0) goto L_0x007c
            int r0 = r8.length()
            int r0 = r0 - r2
            java.lang.String r8 = r8.substring(r2, r0)
            java.lang.String r8 = r8.trim()
            int r5 = java.lang.Integer.parseInt(r8)
            goto L_0x0086
        L_0x007c:
            boolean r0 = r8.isEmpty()
            if (r0 != 0) goto L_0x0086
            int r6 = java.lang.Integer.parseInt(r8)
        L_0x0086:
            if (r1 < 0) goto L_0x008a
            int r1 = r1 - r2
            goto L_0x0090
        L_0x008a:
            int r8 = r7.getRow()
            int r1 = r8 + r3
        L_0x0090:
            if (r6 < 0) goto L_0x0094
            int r6 = r6 - r2
            goto L_0x009a
        L_0x0094:
            short r7 = r7.getCol()
            int r6 = r7 + r5
        L_0x009a:
            org.apache.poi.ss.util.CellReference r7 = new org.apache.poi.ss.util.CellReference
            r7.<init>((int) r1, (int) r6)
            return r7
        L_0x00a0:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r8 = r0.append(r8)
            java.lang.String r0 = " is not a valid R1C1 reference"
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.OperationEvaluationContext.applyR1C1Reference(org.apache.poi.ss.util.CellReference, java.lang.String):org.apache.poi.ss.util.CellReference");
    }

    private static CellReference.NameType getR1C1CellType(String str) {
        String upperCase = str.toUpperCase(LocaleUtil.getUserLocale());
        int indexOf = upperCase.indexOf(82);
        int indexOf2 = upperCase.indexOf(67);
        if (indexOf != -1) {
            if (indexOf2 == -1) {
                return CellReference.NameType.ROW;
            }
            return CellReference.NameType.CELL;
        } else if (indexOf2 == -1) {
            return CellReference.NameType.BAD_CELL_OR_NAMED_RANGE;
        } else {
            return CellReference.NameType.COLUMN;
        }
    }
}
