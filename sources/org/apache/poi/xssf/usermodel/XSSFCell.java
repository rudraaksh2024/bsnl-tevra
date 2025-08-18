package org.apache.poi.xssf.usermodel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SharedFormula;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public final class XSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String FALSE = "FALSE";
    private static final String FALSE_AS_STRING = "0";
    private static final String TRUE = "TRUE";
    private static final String TRUE_AS_STRING = "1";
    private CTCell _cell;
    private int _cellNum;
    private final XSSFRow _row;
    private final SharedStringsTable _sharedStringSource;
    private final StylesTable _stylesSource;

    protected XSSFCell(XSSFRow xSSFRow, CTCell cTCell) {
        this._cell = cTCell;
        this._row = xSSFRow;
        String r = cTCell.getR();
        if (r != null) {
            this._cellNum = new CellReference(r).getCol();
        } else {
            short lastCellNum = xSSFRow.getLastCellNum();
            if (lastCellNum != -1) {
                this._cellNum = xSSFRow.getCell(lastCellNum - 1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).getColumnIndex() + 1;
            }
        }
        this._sharedStringSource = xSSFRow.getSheet().getWorkbook().getSharedStringSource();
        this._stylesSource = xSSFRow.getSheet().getWorkbook().getStylesSource();
    }

    /* access modifiers changed from: protected */
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public void copyCellFrom(Cell cell, CellCopyPolicy cellCopyPolicy) {
        CellUtil.copyCell(cell, this, cellCopyPolicy, (CellCopyContext) null);
    }

    /* access modifiers changed from: protected */
    public SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    /* access modifiers changed from: protected */
    public StylesTable getStylesSource() {
        return this._stylesSource;
    }

    public XSSFSheet getSheet() {
        return getRow().getSheet();
    }

    public XSSFRow getRow() {
        return this._row;
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFCell$1  reason: invalid class name */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFCell.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean getBooleanCellValue() {
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i != 2) {
            if (i != 3) {
                throw typeMismatch(CellType.BOOLEAN, cellType, false);
            } else if (!this._cell.isSetV() || !TRUE_AS_STRING.equals(this._cell.getV())) {
                return false;
            } else {
                return true;
            }
        } else if (!this._cell.isSetV() || !TRUE_AS_STRING.equals(this._cell.getV())) {
            return false;
        } else {
            return true;
        }
    }

    public void setCellValue(boolean z) {
        this._cell.setT(STCellType.B);
        this._cell.setV(z ? TRUE_AS_STRING : FALSE_AS_STRING);
    }

    public double getNumericCellValue() {
        CellType cachedFormulaResultType = isFormulaCell() ? getCachedFormulaResultType() : getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cachedFormulaResultType.ordinal()];
        if (i == 1) {
            return 0.0d;
        }
        if (i == 3) {
            throw new AssertionError();
        } else if (i != 4) {
            throw typeMismatch(CellType.NUMERIC, cachedFormulaResultType, false);
        } else if (!this._cell.isSetV()) {
            return 0.0d;
        } else {
            String v = this._cell.getV();
            if (v.isEmpty()) {
                return 0.0d;
            }
            try {
                return Double.parseDouble(v);
            } catch (NumberFormatException unused) {
                throw typeMismatch(CellType.NUMERIC, CellType.STRING, false);
            }
        }
    }

    public void setCellValueImpl(double d) {
        this._cell.setT(STCellType.N);
        this._cell.setV(String.valueOf(d));
    }

    public String getStringCellValue() {
        return getRichStringCellValue().getString();
    }

    public XSSFRichTextString getRichStringCellValue() {
        XSSFRichTextString xSSFRichTextString;
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        String str = "";
        if (i == 1) {
            xSSFRichTextString = new XSSFRichTextString(str);
        } else if (i == 3) {
            CellType baseCellType = getBaseCellType(false);
            if (baseCellType == CellType.STRING) {
                if (this._cell.isSetV()) {
                    str = this._cell.getV();
                }
                xSSFRichTextString = new XSSFRichTextString(str);
            } else {
                throw typeMismatch(CellType.STRING, baseCellType, true);
            }
        } else if (i == 5) {
            STCellType.Enum t = this._cell.getT();
            if (t == STCellType.INLINE_STR) {
                if (this._cell.isSetIs()) {
                    xSSFRichTextString = new XSSFRichTextString(this._cell.getIs());
                } else if (this._cell.isSetV()) {
                    xSSFRichTextString = new XSSFRichTextString(this._cell.getV());
                } else {
                    xSSFRichTextString = new XSSFRichTextString(str);
                }
            } else if (t == STCellType.STR) {
                if (this._cell.isSetV()) {
                    str = this._cell.getV();
                }
                xSSFRichTextString = new XSSFRichTextString(str);
            } else if (this._cell.isSetV()) {
                try {
                    xSSFRichTextString = (XSSFRichTextString) this._sharedStringSource.getItemAt(Integer.parseInt(this._cell.getV()));
                } catch (Throwable unused) {
                    xSSFRichTextString = new XSSFRichTextString(str);
                }
            } else {
                xSSFRichTextString = new XSSFRichTextString(str);
            }
        } else {
            throw typeMismatch(CellType.STRING, cellType, false);
        }
        xSSFRichTextString.setStylesTableReference(this._stylesSource);
        return xSSFRichTextString;
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(String str) {
        setCellValueImpl((RichTextString) new XSSFRichTextString(str));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(RichTextString richTextString) {
        if (getCellType() == CellType.FORMULA) {
            this._cell.setV(richTextString.getString());
            this._cell.setT(STCellType.STR);
        } else if (this._cell.getT() == STCellType.INLINE_STR) {
            this._cell.setV(richTextString.getString());
        } else {
            this._cell.setT(STCellType.S);
            XSSFRichTextString xSSFRichTextString = (XSSFRichTextString) richTextString;
            xSSFRichTextString.setStylesTableReference(this._stylesSource);
            this._cell.setV(Integer.toString(this._sharedStringSource.addSharedStringItem(xSSFRichTextString)));
        }
    }

    public String getCellFormula() {
        return getCellFormula((BaseXSSFEvaluationWorkbook) null);
    }

    /* access modifiers changed from: protected */
    public String getCellFormula(BaseXSSFEvaluationWorkbook baseXSSFEvaluationWorkbook) {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            CTCellFormula f = this._cell.getF();
            if (isPartOfArrayFormulaGroup() && (f == null || f.getStringValue().isEmpty())) {
                return getSheet().getFirstCellInArrayFormula(this).getCellFormula(baseXSSFEvaluationWorkbook);
            }
            if (f == null) {
                return null;
            }
            if (f.getT() != STCellFormulaType.SHARED) {
                return f.getStringValue();
            }
            int intExact = Math.toIntExact(f.getSi());
            if (baseXSSFEvaluationWorkbook == null) {
                baseXSSFEvaluationWorkbook = XSSFEvaluationWorkbook.create(getSheet().getWorkbook());
            }
            return convertSharedFormula(intExact, baseXSSFEvaluationWorkbook);
        }
        throw typeMismatch(CellType.FORMULA, cellType, false);
    }

    private String convertSharedFormula(int i, BaseXSSFEvaluationWorkbook baseXSSFEvaluationWorkbook) {
        XSSFSheet sheet = getSheet();
        CTCellFormula sharedFormula = sheet.getSharedFormula(i);
        if (sharedFormula != null) {
            String stringValue = sharedFormula.getStringValue();
            CellRangeAddress valueOf = CellRangeAddress.valueOf(sharedFormula.getRef());
            return FormulaRenderer.toFormulaString(baseXSSFEvaluationWorkbook, new SharedFormula(SpreadsheetVersion.EXCEL2007).convertSharedFormulas(FormulaParser.parse(stringValue, baseXSSFEvaluationWorkbook, FormulaType.CELL, sheet.getWorkbook().getSheetIndex((Sheet) sheet), getRowIndex()), getRowIndex() - valueOf.getFirstRow(), getColumnIndex() - valueOf.getFirstColumn()));
        }
        throw new IllegalStateException("Master cell of a shared formula with sid=" + i + " was not found");
    }

    /* access modifiers changed from: protected */
    public void setCellFormulaImpl(String str) {
        setFormula(str, FormulaType.CELL);
    }

    /* access modifiers changed from: package-private */
    public void setCellArrayFormula(String str, CellRangeAddress cellRangeAddress) {
        setFormula(str, FormulaType.ARRAY);
        CTCellFormula f = this._cell.getF();
        f.setT(STCellFormulaType.ARRAY);
        f.setRef(cellRangeAddress.formatAsString());
    }

    private void setFormula(String str, FormulaType formulaType) {
        XSSFWorkbook workbook = this._row.getSheet().getWorkbook();
        if (formulaType == FormulaType.ARRAY && str == null) {
            removeFormulaImpl();
            return;
        }
        if (workbook.getCellFormulaValidation()) {
            XSSFEvaluationWorkbook create = XSSFEvaluationWorkbook.create(workbook);
            Ptg[] parse = FormulaParser.parse(str, create, formulaType, workbook.getSheetIndex((Sheet) getSheet()), getRowIndex());
            int length = parse.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (parse[i] instanceof ErrPtg) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                str = FormulaRenderer.toFormulaString(create, parse);
            }
        }
        if (this._cell.isSetF()) {
            CTCellFormula f = this._cell.getF();
            f.setStringValue(str);
            if (f.getT() == STCellFormulaType.SHARED) {
                getRow().getSheet().onReadCell(this);
                return;
            }
            return;
        }
        CTCellFormula newInstance = CTCellFormula.Factory.newInstance();
        newInstance.setStringValue(str);
        this._cell.setF(newInstance);
    }

    /* access modifiers changed from: protected */
    public void removeFormulaImpl() {
        this._row.getSheet().getWorkbook().onDeleteFormula(this);
        if (this._cell.isSetF()) {
            this._row.getSheet().onDeleteFormula(this, (BaseXSSFEvaluationWorkbook) null);
            this._cell.unsetF();
        }
    }

    public int getColumnIndex() {
        return this._cellNum;
    }

    public int getRowIndex() {
        return this._row.getRowNum();
    }

    public String getReference() {
        String r = this._cell.getR();
        return r == null ? getAddress().formatAsString() : r;
    }

    public XSSFCellStyle getCellStyle() {
        if (this._stylesSource.getNumCellStyles() <= 0) {
            return null;
        }
        return this._stylesSource.getStyleAt(Math.toIntExact(this._cell.isSetS() ? this._cell.getS() : 0));
    }

    public void setCellStyle(CellStyle cellStyle) {
        if (cellStyle != null) {
            XSSFCellStyle xSSFCellStyle = (XSSFCellStyle) cellStyle;
            xSSFCellStyle.verifyBelongsToStylesSource(this._stylesSource);
            this._cell.setS((long) this._stylesSource.putStyle(xSSFCellStyle));
        } else if (this._cell.isSetS()) {
            this._cell.unsetS();
        }
    }

    private boolean isFormulaCell() {
        return (this._cell.isSetF() && this._cell.getF().getT() != STCellFormulaType.DATA_TABLE) || getSheet().isCellInArrayFormulaContext(this);
    }

    public CellType getCellType() {
        if (isFormulaCell()) {
            return CellType.FORMULA;
        }
        return getBaseCellType(true);
    }

    public CellType getCachedFormulaResultType() {
        if (isFormulaCell()) {
            return getBaseCellType(false);
        }
        throw new IllegalStateException("Only formula cells have cached results");
    }

    private CellType getBaseCellType(boolean z) {
        switch (this._cell.getT().intValue()) {
            case 1:
                return CellType.BOOLEAN;
            case 2:
                if (this._cell.isSetV() || !z) {
                    return CellType.NUMERIC;
                }
                return CellType.BLANK;
            case 3:
                return CellType.ERROR;
            case 4:
            case 5:
            case 6:
                return CellType.STRING;
            default:
                throw new IllegalStateException("Illegal cell type: " + this._cell.getT());
        }
    }

    public Date getDateCellValue() {
        if (getCellType() == CellType.BLANK) {
            return null;
        }
        return DateUtil.getJavaDate(getNumericCellValue(), getSheet().getWorkbook().isDate1904());
    }

    public LocalDateTime getLocalDateTimeCellValue() {
        if (getCellType() == CellType.BLANK) {
            return null;
        }
        return DateUtil.getLocalDateTime(getNumericCellValue(), getSheet().getWorkbook().isDate1904());
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(Date date) {
        setCellValue(DateUtil.getExcelDate(date, getSheet().getWorkbook().isDate1904()));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(LocalDateTime localDateTime) {
        setCellValue(DateUtil.getExcelDate(localDateTime, getSheet().getWorkbook().isDate1904()));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(Calendar calendar) {
        setCellValue(DateUtil.getExcelDate(calendar, getSheet().getWorkbook().isDate1904()));
    }

    public String getErrorCellString() throws IllegalStateException {
        CellType baseCellType = getBaseCellType(true);
        if (baseCellType == CellType.ERROR) {
            return this._cell.getV();
        }
        throw typeMismatch(CellType.ERROR, baseCellType, false);
    }

    public byte getErrorCellValue() throws IllegalStateException {
        String errorCellString = getErrorCellString();
        if (errorCellString == null) {
            return 0;
        }
        try {
            return FormulaError.forString(errorCellString).getCode();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unexpected error code", e);
        }
    }

    public void setCellErrorValue(byte b) {
        setCellErrorValue(FormulaError.forInt(b));
    }

    public void setCellErrorValue(FormulaError formulaError) {
        this._cell.setT(STCellType.E);
        this._cell.setV(formulaError.getString());
    }

    public void setAsActiveCell() {
        getSheet().setActiveCell(getAddress());
    }

    private void setBlankPrivate() {
        CTCell newInstance = CTCell.Factory.newInstance();
        newInstance.setR(this._cell.getR());
        if (this._cell.isSetS()) {
            newInstance.setS(this._cell.getS());
        }
        this._cell.set(newInstance);
    }

    /* access modifiers changed from: protected */
    public void setCellNum(int i) {
        checkBounds(i);
        this._cellNum = i;
        this._cell.setR(new CellReference(getRowIndex(), getColumnIndex()).formatAsString());
    }

    /* access modifiers changed from: protected */
    public void setCellTypeImpl(CellType cellType) {
        setCellType(cellType, (BaseXSSFEvaluationWorkbook) null);
    }

    /* access modifiers changed from: protected */
    public void setCellType(CellType cellType, BaseXSSFEvaluationWorkbook baseXSSFEvaluationWorkbook) {
        CellType cellType2 = getCellType();
        if (cellType2 == CellType.FORMULA && cellType != CellType.FORMULA) {
            if (this._cell.isSetF()) {
                this._row.getSheet().onDeleteFormula(this, baseXSSFEvaluationWorkbook);
            }
            getSheet().getWorkbook().onDeleteFormula(this);
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        String str = FALSE_AS_STRING;
        switch (i) {
            case 1:
                setBlankPrivate();
                break;
            case 2:
                if (convertCellValueToBoolean()) {
                    str = TRUE_AS_STRING;
                }
                this._cell.setT(STCellType.B);
                this._cell.setV(str);
                break;
            case 3:
                if (!this._cell.isSetF()) {
                    CTCellFormula newInstance = CTCellFormula.Factory.newInstance();
                    newInstance.setStringValue(str);
                    this._cell.setF(newInstance);
                    if (this._cell.isSetT()) {
                        this._cell.unsetT();
                        break;
                    }
                }
                break;
            case 4:
                this._cell.setT(STCellType.N);
                break;
            case 5:
                if (cellType2 != CellType.STRING) {
                    XSSFRichTextString xSSFRichTextString = new XSSFRichTextString(convertCellValueToString());
                    xSSFRichTextString.setStylesTableReference(this._stylesSource);
                    this._cell.setV(Integer.toString(this._sharedStringSource.addSharedStringItem(xSSFRichTextString)));
                }
                this._cell.setT(STCellType.S);
                break;
            case 6:
                this._cell.setT(STCellType.E);
                break;
            default:
                throw new IllegalArgumentException("Illegal cell type: " + cellType);
        }
        if (cellType != CellType.FORMULA && this._cell.isSetF()) {
            this._cell.unsetF();
        }
    }

    public String toString() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCellType().ordinal()]) {
            case 1:
                return "";
            case 2:
                return getBooleanCellValue() ? TRUE : FALSE;
            case 3:
                return getCellFormula();
            case 4:
                if (!DateUtil.isCellDateFormatted(this)) {
                    return Double.toString(getNumericCellValue());
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
                return simpleDateFormat.format(getDateCellValue());
            case 5:
                return getRichStringCellValue().toString();
            case 6:
                return ErrorEval.getText(getErrorCellValue());
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    public String getRawValue() {
        return this._cell.getV();
    }

    private static RuntimeException typeMismatch(CellType cellType, CellType cellType2, boolean z) {
        return new IllegalStateException("Cannot get a " + cellType + " value from a " + cellType2 + " " + (z ? "formula " : "") + "cell");
    }

    private static void checkBounds(int i) {
        SpreadsheetVersion spreadsheetVersion = SpreadsheetVersion.EXCEL2007;
        int lastColumnIndex = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        if (i < 0 || i > lastColumnIndex) {
            throw new IllegalArgumentException("Invalid column index (" + i + ").  Allowable column range for " + spreadsheetVersion.name() + " is (0.." + lastColumnIndex + ") or ('A'..'" + spreadsheetVersion.getLastColumnName() + "')");
        }
    }

    public XSSFComment getCellComment() {
        return getSheet().getCellComment(new CellAddress((Cell) this));
    }

    public void setCellComment(Comment comment) {
        if (comment == null) {
            removeCellComment();
        } else {
            comment.setAddress(getRowIndex(), getColumnIndex());
        }
    }

    public void removeCellComment() {
        if (getCellComment() != null) {
            CellAddress cellAddress = new CellAddress(getReference());
            XSSFSheet sheet = getSheet();
            sheet.getCommentsTable(false).removeComment(cellAddress);
            sheet.getVMLDrawing(false).removeCommentShape(getRowIndex(), getColumnIndex());
        }
    }

    public XSSFHyperlink getHyperlink() {
        return getSheet().getHyperlink(this._row.getRowNum(), this._cellNum);
    }

    public void setHyperlink(Hyperlink hyperlink) {
        XSSFHyperlink xSSFHyperlink;
        if (hyperlink == null) {
            removeHyperlink();
            return;
        }
        if (hyperlink instanceof XSSFHyperlink) {
            xSSFHyperlink = (XSSFHyperlink) hyperlink;
        } else {
            xSSFHyperlink = new XSSFHyperlink(hyperlink);
        }
        xSSFHyperlink.setCellReference(new CellReference(this._row.getRowNum(), this._cellNum).formatAsString());
        getSheet().addHyperlink(xSSFHyperlink);
    }

    public void removeHyperlink() {
        getSheet().removeHyperlink(this._row.getRowNum(), this._cellNum);
    }

    @Internal
    public CTCell getCTCell() {
        return this._cell;
    }

    @Internal
    public void setCTCell(CTCell cTCell) {
        this._cell = cTCell;
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = getBaseCellType(false);
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return TRUE_AS_STRING.equals(this._cell.getV());
            }
            if (i != 4) {
                if (i == 5) {
                    return Boolean.parseBoolean(this._sharedStringSource.getItemAt(Integer.parseInt(this._cell.getV())).getString());
                } else if (i != 6) {
                    throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
                }
            } else if (Double.parseDouble(this._cell.getV()) != 0.0d) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private String convertCellValueToString() {
        CellType cellType = getCellType();
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
            case 1:
                return "";
            case 2:
                return TRUE_AS_STRING.equals(this._cell.getV()) ? TRUE : FALSE;
            case 3:
                CellType baseCellType = getBaseCellType(false);
                String v = this._cell.getV();
                int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[baseCellType.ordinal()];
                if (i != 2) {
                    if (i == 4 || i == 5 || i == 6) {
                        return v;
                    }
                    throw new IllegalStateException("Unexpected formula result type (" + baseCellType + ")");
                } else if (TRUE_AS_STRING.equals(v)) {
                    return TRUE;
                } else {
                    if (FALSE_AS_STRING.equals(v)) {
                        return FALSE;
                    }
                    throw new IllegalStateException("Unexpected boolean cached formula value '" + v + "'.");
                }
            case 4:
            case 6:
                return this._cell.getV();
            case 5:
                try {
                    return this._sharedStringSource.getItemAt(Integer.parseInt(this._cell.getV())).getString();
                } catch (Throwable unused) {
                    return "";
                }
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    public CellRangeAddress getArrayFormulaRange() {
        XSSFCell firstCellInArrayFormula = getSheet().getFirstCellInArrayFormula(this);
        if (firstCellInArrayFormula != null) {
            return CellRangeAddress.valueOf(firstCellInArrayFormula._cell.getF().getRef());
        }
        throw new IllegalStateException("Cell " + new CellReference((Cell) this).formatAsString() + " is not part of an array formula.");
    }

    public boolean isPartOfArrayFormulaGroup() {
        return getSheet().isCellInArrayFormulaContext(this);
    }

    public void updateCellReferencesForShifting(String str) {
        if (isPartOfArrayFormulaGroup()) {
            tryToDeleteArrayFormula(str);
        }
        CalculationChain calculationChain = getSheet().getWorkbook().getCalculationChain();
        int intExact = Math.toIntExact(getSheet().sheet.getSheetId());
        if (calculationChain != null) {
            calculationChain.removeItem(intExact, getReference());
        }
        getCTCell().setR(new CellReference(getRowIndex(), getColumnIndex()).formatAsString());
    }
}
