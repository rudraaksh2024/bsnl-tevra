package org.apache.poi.xssf.streaming;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class SXSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Property _firstProperty;
    private final SXSSFRow _row;
    private CellStyle _style;
    private Value _value = new BlankValue();

    interface Value {
        CellType getType();
    }

    @NotImplemented
    public CellRangeAddress getArrayFormulaRange() {
        return null;
    }

    @NotImplemented
    public boolean isPartOfArrayFormulaGroup() {
        return false;
    }

    public SXSSFCell(SXSSFRow sXSSFRow, CellType cellType) {
        this._row = sXSSFRow;
        setType(cellType);
    }

    /* access modifiers changed from: protected */
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public int getColumnIndex() {
        return this._row.getCellIndex(this);
    }

    public int getRowIndex() {
        return this._row.getRowNum();
    }

    public SXSSFSheet getSheet() {
        return this._row.getSheet();
    }

    public Row getRow() {
        return this._row;
    }

    /* access modifiers changed from: protected */
    public void setCellTypeImpl(CellType cellType) {
        ensureType(cellType);
    }

    private boolean isFormulaCell() {
        return this._value instanceof FormulaValue;
    }

    public CellType getCellType() {
        if (isFormulaCell()) {
            return CellType.FORMULA;
        }
        return this._value.getType();
    }

    public CellType getCachedFormulaResultType() {
        if (isFormulaCell()) {
            return ((FormulaValue) this._value).getFormulaType();
        }
        throw new IllegalStateException("Only formula cells have cached results");
    }

    public void setCellValueImpl(double d) {
        ensureTypeOrFormulaType(CellType.NUMERIC);
        if (this._value.getType() == CellType.FORMULA) {
            ((NumericFormulaValue) this._value).setPreEvaluatedValue(d);
        } else {
            ((NumericValue) this._value).setValue(d);
        }
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

    /* access modifiers changed from: protected */
    public void setCellValueImpl(RichTextString richTextString) {
        ensureRichTextStringType();
        Value value = this._value;
        if (value instanceof RichTextStringFormulaValue) {
            ((RichTextStringFormulaValue) value).setPreEvaluatedValue(richTextString);
        } else {
            ((RichTextValue) value).setValue(richTextString);
        }
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(String str) {
        ensureTypeOrFormulaType(CellType.STRING);
        if (this._value.getType() == CellType.FORMULA) {
            ((StringFormulaValue) this._value).setPreEvaluatedValue(str);
        } else {
            ((PlainStringValue) this._value).setValue(str);
        }
    }

    public void setCellFormulaImpl(String str) throws FormulaParseException {
        if (getCellType() == CellType.FORMULA) {
            ((FormulaValue) this._value).setValue(str);
            return;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCellType().ordinal()];
        if (i == 1 || i == 2) {
            this._value = new NumericFormulaValue(str, getNumericCellValue());
        } else if (i != 3) {
            if (i == 4) {
                this._value = new BooleanFormulaValue(str, getBooleanCellValue());
            } else if (i == 5) {
                this._value = new ErrorFormulaValue(str, getErrorCellValue());
            } else {
                throw new IllegalStateException("Cannot set a formula for a cell of type " + getCellType());
            }
        } else if (this._value instanceof PlainStringValue) {
            this._value = new StringFormulaValue(str, getStringCellValue());
        } else {
            this._value = new RichTextStringFormulaValue(str, ((RichTextValue) this._value).getValue());
        }
    }

    /* renamed from: org.apache.poi.xssf.streaming.SXSSFCell$1  reason: invalid class name */
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
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFCell.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void removeFormulaImpl() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCachedFormulaResultType().ordinal()];
        if (i == 2) {
            double preEvaluatedValue = ((NumericFormulaValue) this._value).getPreEvaluatedValue();
            NumericValue numericValue = new NumericValue();
            this._value = numericValue;
            NumericValue numericValue2 = numericValue;
            numericValue.setValue(preEvaluatedValue);
        } else if (i == 3) {
            String preEvaluatedValue2 = ((StringFormulaValue) this._value).getPreEvaluatedValue();
            PlainStringValue plainStringValue = new PlainStringValue();
            this._value = plainStringValue;
            PlainStringValue plainStringValue2 = plainStringValue;
            plainStringValue.setValue(preEvaluatedValue2);
        } else if (i == 4) {
            boolean preEvaluatedValue3 = ((BooleanFormulaValue) this._value).getPreEvaluatedValue();
            BooleanValue booleanValue = new BooleanValue();
            this._value = booleanValue;
            BooleanValue booleanValue2 = booleanValue;
            booleanValue.setValue(preEvaluatedValue3);
        } else if (i == 5) {
            byte preEvaluatedValue4 = ((ErrorFormulaValue) this._value).getPreEvaluatedValue();
            ErrorValue errorValue = new ErrorValue();
            this._value = errorValue;
            ErrorValue errorValue2 = errorValue;
            errorValue.setValue(preEvaluatedValue4);
        } else {
            throw new AssertionError();
        }
    }

    public String getCellFormula() {
        if (this._value.getType() == CellType.FORMULA) {
            return ((FormulaValue) this._value).getValue();
        }
        throw typeMismatch(CellType.FORMULA, this._value.getType(), false);
    }

    public double getNumericCellValue() {
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return 0.0d;
        }
        if (i == 2) {
            return ((NumericValue) this._value).getValue();
        }
        if (i != 6) {
            throw typeMismatch(CellType.NUMERIC, cellType, false);
        } else if (((FormulaValue) this._value).getFormulaType() == CellType.NUMERIC) {
            return ((NumericFormulaValue) this._value).getPreEvaluatedValue();
        } else {
            throw typeMismatch(CellType.NUMERIC, CellType.FORMULA, false);
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

    public RichTextString getRichStringCellValue() {
        CellType cellType = getCellType();
        if (getCellType() != CellType.STRING) {
            throw typeMismatch(CellType.STRING, cellType, false);
        } else if (((StringValue) this._value).isRichText()) {
            return ((RichTextValue) this._value).getValue();
        } else {
            return new XSSFRichTextString(getStringCellValue());
        }
    }

    public String getStringCellValue() {
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return "";
        }
        if (i != 3) {
            if (i != 6) {
                throw typeMismatch(CellType.STRING, cellType, false);
            } else if (((FormulaValue) this._value).getFormulaType() == CellType.STRING) {
                Value value = this._value;
                if (value instanceof RichTextStringFormulaValue) {
                    return ((RichTextStringFormulaValue) value).getPreEvaluatedValue().getString();
                }
                return ((StringFormulaValue) value).getPreEvaluatedValue();
            } else {
                throw typeMismatch(CellType.STRING, CellType.FORMULA, false);
            }
        } else if (((StringValue) this._value).isRichText()) {
            return ((RichTextValue) this._value).getValue().getString();
        } else {
            return ((PlainStringValue) this._value).getValue();
        }
    }

    public void setCellValue(boolean z) {
        ensureTypeOrFormulaType(CellType.BOOLEAN);
        if (this._value.getType() == CellType.FORMULA) {
            ((BooleanFormulaValue) this._value).setPreEvaluatedValue(z);
        } else {
            ((BooleanValue) this._value).setValue(z);
        }
    }

    public void setCellErrorValue(byte b) {
        if (this._value.getType() == CellType.FORMULA) {
            this._value = new ErrorFormulaValue(getCellFormula(), b);
        } else {
            this._value = new ErrorValue(b);
        }
    }

    public boolean getBooleanCellValue() {
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 4) {
            return ((BooleanValue) this._value).getValue();
        }
        if (i != 6) {
            throw typeMismatch(CellType.BOOLEAN, cellType, false);
        } else if (((FormulaValue) this._value).getFormulaType() == CellType.BOOLEAN) {
            return ((BooleanFormulaValue) this._value).getPreEvaluatedValue();
        } else {
            throw typeMismatch(CellType.BOOLEAN, CellType.FORMULA, false);
        }
    }

    public byte getErrorCellValue() {
        CellType cellType = getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 5) {
            return ((ErrorValue) this._value).getValue();
        }
        if (i != 6) {
            throw typeMismatch(CellType.ERROR, cellType, false);
        } else if (((FormulaValue) this._value).getFormulaType() == CellType.ERROR) {
            return ((ErrorFormulaValue) this._value).getPreEvaluatedValue();
        } else {
            throw typeMismatch(CellType.ERROR, CellType.FORMULA, false);
        }
    }

    public void setCellStyle(CellStyle cellStyle) {
        this._style = cellStyle;
    }

    public CellStyle getCellStyle() {
        CellStyle cellStyle = this._style;
        return cellStyle == null ? ((SXSSFWorkbook) getRow().getSheet().getWorkbook()).getCellStyleAt(0) : cellStyle;
    }

    public void setAsActiveCell() {
        getSheet().setActiveCell(getAddress());
    }

    public void setCellComment(Comment comment) {
        setProperty(1, comment);
    }

    public Comment getCellComment() {
        return (Comment) getPropertyValue(1);
    }

    public void removeCellComment() {
        removeProperty(1);
    }

    public Hyperlink getHyperlink() {
        return (Hyperlink) getPropertyValue(2);
    }

    public void setHyperlink(Hyperlink hyperlink) {
        if (hyperlink == null) {
            removeHyperlink();
            return;
        }
        setProperty(2, hyperlink);
        XSSFHyperlink xSSFHyperlink = (XSSFHyperlink) hyperlink;
        xSSFHyperlink.setCellReference(new CellReference(getRowIndex(), getColumnIndex()));
        getSheet()._sh.addHyperlink(xSSFHyperlink);
    }

    public void removeHyperlink() {
        removeProperty(2);
        getSheet()._sh.removeHyperlink(getRowIndex(), getColumnIndex());
    }

    public String toString() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCellType().ordinal()]) {
            case 1:
                return "";
            case 2:
                if (!DateUtil.isCellDateFormatted(this)) {
                    return getNumericCellValue() + "";
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
                return simpleDateFormat.format(getDateCellValue());
            case 3:
                return getRichStringCellValue().toString();
            case 4:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case 5:
                return ErrorEval.getText(getErrorCellValue());
            case 6:
                return getCellFormula();
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeProperty(int i) {
        Property property = this._firstProperty;
        Property property2 = null;
        while (property != null && property.getType() != i) {
            property2 = property;
            property = property._next;
        }
        if (property == null) {
            return;
        }
        if (property2 != null) {
            property2._next = property._next;
        } else {
            this._firstProperty = property._next;
        }
    }

    /* access modifiers changed from: package-private */
    public void setProperty(int i, Object obj) {
        Property property;
        Property property2 = this._firstProperty;
        Property property3 = null;
        while (property2 != null && property2.getType() != i) {
            property3 = property2;
            property2 = property2._next;
        }
        if (property2 != null) {
            property2.setValue(obj);
            return;
        }
        if (i == 1) {
            property = new CommentProperty(obj);
        } else if (i == 2) {
            property = new HyperlinkProperty(obj);
        } else {
            throw new IllegalArgumentException("Invalid type: " + i);
        }
        if (property3 != null) {
            property3._next = property;
        } else {
            this._firstProperty = property;
        }
    }

    /* access modifiers changed from: package-private */
    public Object getPropertyValue(int i) {
        return getPropertyValue(i, (String) null);
    }

    /* access modifiers changed from: package-private */
    public Object getPropertyValue(int i, String str) {
        Property property = this._firstProperty;
        while (property != null && property.getType() != i) {
            property = property._next;
        }
        return property == null ? str : property.getValue();
    }

    /* access modifiers changed from: package-private */
    public void ensurePlainStringType() {
        if (this._value.getType() != CellType.STRING || ((StringValue) this._value).isRichText()) {
            this._value = new PlainStringValue();
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureRichTextStringType() {
        if (this._value.getType() == CellType.FORMULA) {
            this._value = new RichTextStringFormulaValue(((FormulaValue) this._value).getValue(), new XSSFRichTextString(""));
        } else if (this._value.getType() != CellType.STRING || !((StringValue) this._value).isRichText()) {
            this._value = new RichTextValue();
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureType(CellType cellType) {
        if (this._value.getType() != cellType) {
            setType(cellType);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureTypeOrFormulaType(CellType cellType) {
        if (this._value.getType() == cellType) {
            if (cellType == CellType.STRING && ((StringValue) this._value).isRichText()) {
                setType(CellType.STRING);
            }
        } else if (this._value.getType() != CellType.FORMULA) {
            setType(cellType);
        } else if (((FormulaValue) this._value).getFormulaType() != cellType) {
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
            if (i == 2) {
                this._value = new NumericFormulaValue(getCellFormula(), 0.0d);
            } else if (i == 3) {
                this._value = new StringFormulaValue(getCellFormula(), "");
            } else if (i == 4) {
                this._value = new BooleanFormulaValue(getCellFormula(), false);
            } else if (i == 5) {
                this._value = new ErrorFormulaValue(getCellFormula(), FormulaError._NO_ERROR.getCode());
            } else {
                throw new AssertionError();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setType(CellType cellType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
            case 1:
                this._value = new BlankValue();
                return;
            case 2:
                this._value = new NumericValue();
                return;
            case 3:
                PlainStringValue plainStringValue = new PlainStringValue();
                if (this._value != null) {
                    plainStringValue.setValue(convertCellValueToString());
                }
                this._value = plainStringValue;
                return;
            case 4:
                BooleanValue booleanValue = new BooleanValue();
                if (this._value != null) {
                    booleanValue.setValue(convertCellValueToBoolean());
                }
                this._value = booleanValue;
                return;
            case 5:
                this._value = new ErrorValue();
                return;
            case 6:
                if (getCellType() == CellType.BLANK) {
                    this._value = new NumericFormulaValue("", 0.0d);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Illegal type " + cellType);
        }
    }

    private static IllegalStateException typeMismatch(CellType cellType, CellType cellType2, boolean z) {
        return new IllegalStateException("Cannot get a " + cellType + " value from a " + cellType2 + " " + (z ? "formula " : "") + "cell");
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = getCachedFormulaResultType();
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i != 2) {
            if (i == 3) {
                return Boolean.parseBoolean(getStringCellValue());
            }
            if (i == 4) {
                return getBooleanCellValue();
            }
            if (i == 5) {
                return false;
            }
            throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        } else if (getNumericCellValue() != 0.0d) {
            return true;
        } else {
            return false;
        }
    }

    private String convertCellValueToString() {
        return convertCellValueToString(getCellType());
    }

    private String convertCellValueToString(CellType cellType) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
            case 1:
                return "";
            case 2:
                return Double.toString(getNumericCellValue());
            case 3:
                return getStringCellValue();
            case 4:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case 5:
                return FormulaError.forInt(getErrorCellValue()).getString();
            case 6:
                Value value = this._value;
                if (value != null) {
                    FormulaValue formulaValue = (FormulaValue) value;
                    if (formulaValue.getFormulaType() != CellType.FORMULA) {
                        return convertCellValueToString(formulaValue.getFormulaType());
                    }
                }
                return "";
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    static abstract class Property {
        static final int COMMENT = 1;
        static final int HYPERLINK = 2;
        Property _next;
        Object _value;

        /* access modifiers changed from: package-private */
        public abstract int getType();

        public Property(Object obj) {
            this._value = obj;
        }

        /* access modifiers changed from: package-private */
        public void setValue(Object obj) {
            this._value = obj;
        }

        /* access modifiers changed from: package-private */
        public Object getValue() {
            return this._value;
        }
    }

    static class CommentProperty extends Property {
        public int getType() {
            return 1;
        }

        public CommentProperty(Object obj) {
            super(obj);
        }
    }

    static class HyperlinkProperty extends Property {
        public int getType() {
            return 2;
        }

        public HyperlinkProperty(Object obj) {
            super(obj);
        }
    }

    static class NumericValue implements Value {
        double _value;

        public NumericValue() {
            this._value = 0.0d;
        }

        public NumericValue(double d) {
            this._value = d;
        }

        public CellType getType() {
            return CellType.NUMERIC;
        }

        /* access modifiers changed from: package-private */
        public void setValue(double d) {
            this._value = d;
        }

        /* access modifiers changed from: package-private */
        public double getValue() {
            return this._value;
        }
    }

    static abstract class StringValue implements Value {
        /* access modifiers changed from: package-private */
        public abstract boolean isRichText();

        StringValue() {
        }

        public CellType getType() {
            return CellType.STRING;
        }
    }

    static class PlainStringValue extends StringValue {
        String _value;

        /* access modifiers changed from: package-private */
        public boolean isRichText() {
            return false;
        }

        PlainStringValue() {
        }

        /* access modifiers changed from: package-private */
        public void setValue(String str) {
            this._value = str;
        }

        /* access modifiers changed from: package-private */
        public String getValue() {
            return this._value;
        }
    }

    static class RichTextValue extends StringValue {
        RichTextString _value;

        /* access modifiers changed from: package-private */
        public boolean isRichText() {
            return true;
        }

        RichTextValue() {
        }

        public CellType getType() {
            return CellType.STRING;
        }

        /* access modifiers changed from: package-private */
        public void setValue(RichTextString richTextString) {
            this._value = richTextString;
        }

        /* access modifiers changed from: package-private */
        public RichTextString getValue() {
            return this._value;
        }
    }

    static abstract class FormulaValue implements Value {
        String _value;

        /* access modifiers changed from: package-private */
        public abstract CellType getFormulaType();

        public FormulaValue(String str) {
            this._value = str;
        }

        public CellType getType() {
            return CellType.FORMULA;
        }

        /* access modifiers changed from: package-private */
        public void setValue(String str) {
            this._value = str;
        }

        /* access modifiers changed from: package-private */
        public String getValue() {
            return this._value;
        }
    }

    static class NumericFormulaValue extends FormulaValue {
        double _preEvaluatedValue;

        public NumericFormulaValue(String str, double d) {
            super(str);
            this._preEvaluatedValue = d;
        }

        /* access modifiers changed from: package-private */
        public CellType getFormulaType() {
            return CellType.NUMERIC;
        }

        /* access modifiers changed from: package-private */
        public void setPreEvaluatedValue(double d) {
            this._preEvaluatedValue = d;
        }

        /* access modifiers changed from: package-private */
        public double getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class StringFormulaValue extends FormulaValue {
        String _preEvaluatedValue;

        public StringFormulaValue(String str, String str2) {
            super(str);
            this._preEvaluatedValue = str2;
        }

        /* access modifiers changed from: package-private */
        public CellType getFormulaType() {
            return CellType.STRING;
        }

        /* access modifiers changed from: package-private */
        public void setPreEvaluatedValue(String str) {
            this._preEvaluatedValue = str;
        }

        /* access modifiers changed from: package-private */
        public String getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class RichTextStringFormulaValue extends FormulaValue {
        RichTextString _preEvaluatedValue;

        public RichTextStringFormulaValue(String str, RichTextString richTextString) {
            super(str);
            this._preEvaluatedValue = richTextString;
        }

        /* access modifiers changed from: package-private */
        public CellType getFormulaType() {
            return CellType.STRING;
        }

        /* access modifiers changed from: package-private */
        public void setPreEvaluatedValue(RichTextString richTextString) {
            this._preEvaluatedValue = richTextString;
        }

        /* access modifiers changed from: package-private */
        public RichTextString getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class BooleanFormulaValue extends FormulaValue {
        boolean _preEvaluatedValue;

        public BooleanFormulaValue(String str, boolean z) {
            super(str);
            this._preEvaluatedValue = z;
        }

        /* access modifiers changed from: package-private */
        public CellType getFormulaType() {
            return CellType.BOOLEAN;
        }

        /* access modifiers changed from: package-private */
        public void setPreEvaluatedValue(boolean z) {
            this._preEvaluatedValue = z;
        }

        /* access modifiers changed from: package-private */
        public boolean getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class ErrorFormulaValue extends FormulaValue {
        byte _preEvaluatedValue;

        public ErrorFormulaValue(String str, byte b) {
            super(str);
            this._preEvaluatedValue = b;
        }

        /* access modifiers changed from: package-private */
        public CellType getFormulaType() {
            return CellType.ERROR;
        }

        /* access modifiers changed from: package-private */
        public void setPreEvaluatedValue(byte b) {
            this._preEvaluatedValue = b;
        }

        /* access modifiers changed from: package-private */
        public byte getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class BlankValue implements Value {
        BlankValue() {
        }

        public CellType getType() {
            return CellType.BLANK;
        }
    }

    static class BooleanValue implements Value {
        boolean _value;

        public BooleanValue() {
            this._value = false;
        }

        public BooleanValue(boolean z) {
            this._value = z;
        }

        public CellType getType() {
            return CellType.BOOLEAN;
        }

        /* access modifiers changed from: package-private */
        public void setValue(boolean z) {
            this._value = z;
        }

        /* access modifiers changed from: package-private */
        public boolean getValue() {
            return this._value;
        }
    }

    static class ErrorValue implements Value {
        byte _value;

        public ErrorValue() {
            this._value = FormulaError._NO_ERROR.getCode();
        }

        public ErrorValue(byte b) {
            this._value = b;
        }

        public CellType getType() {
            return CellType.ERROR;
        }

        /* access modifiers changed from: package-private */
        public void setValue(byte b) {
            this._value = b;
        }

        /* access modifiers changed from: package-private */
        public byte getValue() {
            return this._value;
        }
    }
}
