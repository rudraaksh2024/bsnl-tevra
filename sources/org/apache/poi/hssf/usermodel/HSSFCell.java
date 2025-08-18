package org.apache.poi.hssf.usermodel;

import com.google.android.gms.common.internal.ImagesContract;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;

public class HSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final short ENCODING_COMPRESSED_UNICODE = 0;
    public static final short ENCODING_UNCHANGED = -1;
    public static final short ENCODING_UTF_16 = 1;
    private static final String FILE_FORMAT_NAME = "BIFF8";
    private static final String LAST_COLUMN_NAME = SpreadsheetVersion.EXCEL97.getLastColumnName();
    public static final int LAST_COLUMN_NUMBER = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
    private final HSSFWorkbook _book;
    private CellType _cellType;
    private HSSFComment _comment;
    private CellValueRecordInterface _record;
    private final HSSFSheet _sheet;
    private HSSFRichTextString _stringValue;

    protected HSSFCell(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet, int i, short s) {
        checkBounds(s);
        this._stringValue = null;
        this._book = hSSFWorkbook;
        this._sheet = hSSFSheet;
        setCellType(CellType.BLANK, false, i, s, hSSFSheet.getSheet().getXFIndexForColAt(s));
    }

    /* access modifiers changed from: protected */
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    public HSSFSheet getSheet() {
        return this._sheet;
    }

    public HSSFRow getRow() {
        return this._sheet.getRow(getRowIndex());
    }

    protected HSSFCell(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet, int i, short s, CellType cellType) {
        checkBounds(s);
        this._cellType = CellType._NONE;
        this._stringValue = null;
        this._book = hSSFWorkbook;
        this._sheet = hSSFSheet;
        setCellType(cellType, false, i, s, hSSFSheet.getSheet().getXFIndexForColAt(s));
    }

    protected HSSFCell(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet, CellValueRecordInterface cellValueRecordInterface) {
        this._record = cellValueRecordInterface;
        this._cellType = determineType(cellValueRecordInterface);
        this._stringValue = null;
        this._book = hSSFWorkbook;
        this._sheet = hSSFSheet;
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 1) {
            this._stringValue = new HSSFRichTextString(hSSFWorkbook.getWorkbook(), (LabelSSTRecord) cellValueRecordInterface);
        } else if (i == 2) {
            this._stringValue = new HSSFRichTextString(((FormulaRecordAggregate) cellValueRecordInterface).getStringValue());
        }
    }

    private static CellType determineType(CellValueRecordInterface cellValueRecordInterface) {
        if (cellValueRecordInterface instanceof FormulaRecordAggregate) {
            return CellType.FORMULA;
        }
        Record record = (Record) cellValueRecordInterface;
        short sid = record.getSid();
        if (sid == 253) {
            return CellType.STRING;
        }
        if (sid == 513) {
            return CellType.BLANK;
        }
        if (sid == 515) {
            return CellType.NUMERIC;
        }
        if (sid == 517) {
            return ((BoolErrRecord) record).isBoolean() ? CellType.BOOLEAN : CellType.ERROR;
        }
        throw new RuntimeException("Bad cell value rec (" + cellValueRecordInterface.getClass().getName() + ")");
    }

    /* access modifiers changed from: protected */
    public InternalWorkbook getBoundWorkbook() {
        return this._book.getWorkbook();
    }

    public int getRowIndex() {
        return this._record.getRow();
    }

    /* access modifiers changed from: protected */
    public void updateCellNum(short s) {
        this._record.setColumn(s);
    }

    public int getColumnIndex() {
        return this._record.getColumn() & 65535;
    }

    /* access modifiers changed from: protected */
    public void setCellTypeImpl(CellType cellType) {
        notifyFormulaChanging();
        setCellType(cellType, true, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
    }

    private void setCellType(CellType cellType, boolean z, int i, short s, short s2) {
        LabelSSTRecord labelSSTRecord;
        FormulaRecordAggregate formulaRecordAggregate;
        BlankRecord blankRecord;
        NumberRecord numberRecord;
        BoolErrRecord boolErrRecord;
        BoolErrRecord boolErrRecord2;
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()]) {
            case 1:
                if (cellType == this._cellType) {
                    labelSSTRecord = (LabelSSTRecord) this._record;
                } else {
                    labelSSTRecord = new LabelSSTRecord();
                    labelSSTRecord.setColumn(s);
                    labelSSTRecord.setRow(i);
                    labelSSTRecord.setXFIndex(s2);
                }
                if (z) {
                    String convertCellValueToString = convertCellValueToString();
                    if (convertCellValueToString == null) {
                        setCellType(CellType.BLANK, false, i, s, s2);
                        return;
                    }
                    int addSSTString = this._book.getWorkbook().addSSTString(new UnicodeString(convertCellValueToString));
                    labelSSTRecord.setSSTIndex(addSSTString);
                    UnicodeString sSTString = this._book.getWorkbook().getSSTString(addSSTString);
                    HSSFRichTextString hSSFRichTextString = new HSSFRichTextString();
                    this._stringValue = hSSFRichTextString;
                    hSSFRichTextString.setUnicodeString(sSTString);
                }
                this._record = labelSSTRecord;
                break;
            case 2:
                if (cellType != this._cellType) {
                    formulaRecordAggregate = this._sheet.getSheet().getRowsAggregate().createFormula(i, s);
                } else {
                    formulaRecordAggregate = (FormulaRecordAggregate) this._record;
                    formulaRecordAggregate.setRow(i);
                    formulaRecordAggregate.setColumn(s);
                }
                if (getCellType() == CellType.BLANK) {
                    formulaRecordAggregate.getFormulaRecord().setValue(0.0d);
                }
                formulaRecordAggregate.setXFIndex(s2);
                this._record = formulaRecordAggregate;
                break;
            case 3:
                if (cellType != this._cellType) {
                    blankRecord = new BlankRecord();
                } else {
                    blankRecord = (BlankRecord) this._record;
                }
                blankRecord.setColumn(s);
                blankRecord.setXFIndex(s2);
                blankRecord.setRow(i);
                this._record = blankRecord;
                break;
            case 4:
                if (cellType != this._cellType) {
                    numberRecord = new NumberRecord();
                } else {
                    numberRecord = (NumberRecord) this._record;
                }
                numberRecord.setColumn(s);
                if (z) {
                    numberRecord.setValue(getNumericCellValue());
                }
                numberRecord.setXFIndex(s2);
                numberRecord.setRow(i);
                this._record = numberRecord;
                break;
            case 5:
                if (cellType != this._cellType) {
                    boolErrRecord = new BoolErrRecord();
                } else {
                    boolErrRecord = (BoolErrRecord) this._record;
                }
                boolErrRecord.setColumn(s);
                if (z) {
                    boolErrRecord.setValue(convertCellValueToBoolean());
                }
                boolErrRecord.setXFIndex(s2);
                boolErrRecord.setRow(i);
                this._record = boolErrRecord;
                break;
            case 6:
                if (cellType != this._cellType) {
                    boolErrRecord2 = new BoolErrRecord();
                } else {
                    boolErrRecord2 = (BoolErrRecord) this._record;
                }
                boolErrRecord2.setColumn(s);
                if (z) {
                    boolErrRecord2.setValue(FormulaError.VALUE.getCode());
                }
                boolErrRecord2.setXFIndex(s2);
                boolErrRecord2.setRow(i);
                this._record = boolErrRecord2;
                break;
            default:
                throw new IllegalStateException("Invalid cell type: " + cellType);
        }
        CellType cellType2 = this._cellType;
        if (!(cellType == cellType2 || cellType2 == CellType._NONE)) {
            this._sheet.getSheet().replaceValueRecord(this._record);
        }
        this._cellType = cellType;
    }

    public CellType getCellType() {
        return this._cellType;
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(double d) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i != 2) {
            if (i != 4) {
                setCellType(CellType.NUMERIC, false, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
            }
            ((NumberRecord) this._record).setValue(d);
            return;
        }
        ((FormulaRecordAggregate) this._record).setCachedDoubleResult(d);
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(Date date) {
        setCellValue(DateUtil.getExcelDate(date, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(LocalDateTime localDateTime) {
        setCellValue(DateUtil.getExcelDate(localDateTime, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(Calendar calendar) {
        setCellValue(DateUtil.getExcelDate(calendar, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(String str) {
        setCellValueImpl((RichTextString) new HSSFRichTextString(str));
    }

    /* access modifiers changed from: protected */
    public void setCellValueImpl(RichTextString richTextString) {
        if (this._cellType == CellType.FORMULA) {
            ((FormulaRecordAggregate) this._record).setCachedStringResult(richTextString.getString());
            this._stringValue = new HSSFRichTextString(richTextString.getString());
            return;
        }
        if (this._cellType != CellType.STRING) {
            setCellType(CellType.STRING, false, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
        }
        HSSFRichTextString hSSFRichTextString = (HSSFRichTextString) richTextString;
        int addSSTString = this._book.getWorkbook().addSSTString(hSSFRichTextString.getUnicodeString());
        ((LabelSSTRecord) this._record).setSSTIndex(addSSTString);
        this._stringValue = hSSFRichTextString;
        hSSFRichTextString.setWorkbookReferences(this._book.getWorkbook(), (LabelSSTRecord) this._record);
        this._stringValue.setUnicodeString(this._book.getWorkbook().getSSTString(addSSTString));
    }

    /* access modifiers changed from: protected */
    public void setCellFormulaImpl(String str) {
        if (getValueType() == CellType.BLANK) {
            setCellValue(0.0d);
        }
        int row = this._record.getRow();
        short column = this._record.getColumn();
        short xFIndex = this._record.getXFIndex();
        CellValue readValue = readValue();
        Ptg[] parse = HSSFFormulaParser.parse(str, this._book, FormulaType.CELL, this._book.getSheetIndex((Sheet) this._sheet));
        setCellType(CellType.FORMULA, false, row, column, xFIndex);
        FormulaRecordAggregate formulaRecordAggregate = (FormulaRecordAggregate) this._record;
        formulaRecordAggregate.getFormulaRecord().setOptions(2);
        if (formulaRecordAggregate.getXFIndex() == 0) {
            formulaRecordAggregate.setXFIndex(15);
        }
        formulaRecordAggregate.setParsedExpression(parse);
        restoreValue(readValue);
    }

    private CellValue readValue() {
        CellType cachedFormulaResultType = getCellType() == CellType.FORMULA ? getCachedFormulaResultType() : getCellType();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cachedFormulaResultType.ordinal()];
        if (i == 1) {
            return new CellValue(getStringCellValue());
        }
        if (i == 4) {
            return new CellValue(getNumericCellValue());
        }
        if (i == 5) {
            return CellValue.valueOf(getBooleanCellValue());
        }
        if (i == 6) {
            return CellValue.getError(getErrorCellValue());
        }
        throw new IllegalStateException("Unexpected cell-type " + cachedFormulaResultType);
    }

    private void restoreValue(CellValue cellValue) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellValue.getCellType().ordinal()];
        if (i == 1) {
            setCellValue(cellValue.getStringValue());
        } else if (i == 4) {
            setCellValue(cellValue.getNumberValue());
        } else if (i == 5) {
            setCellValue(cellValue.getBooleanValue());
        } else if (i == 6) {
            setCellErrorValue(FormulaError.forInt(cellValue.getErrorValue()));
        } else {
            throw new IllegalStateException("Unexpected cell-type " + cellValue.getCellType() + " for cell-value: " + cellValue);
        }
    }

    /* access modifiers changed from: protected */
    public void removeFormulaImpl() {
        notifyFormulaChanging();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCachedFormulaResultType().ordinal()];
        if (i == 1) {
            NumberRecord numberRecord = new NumberRecord();
            this._record = numberRecord;
            NumberRecord numberRecord2 = numberRecord;
            numberRecord.setValue(0.0d);
            this._cellType = CellType.STRING;
        } else if (i == 4) {
            double value = ((FormulaRecordAggregate) this._record).getFormulaRecord().getValue();
            NumberRecord numberRecord3 = new NumberRecord();
            this._record = numberRecord3;
            NumberRecord numberRecord4 = numberRecord3;
            numberRecord3.setValue(value);
            this._cellType = CellType.NUMERIC;
        } else if (i == 5) {
            boolean cachedBooleanValue = ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedBooleanValue();
            BoolErrRecord boolErrRecord = new BoolErrRecord();
            this._record = boolErrRecord;
            BoolErrRecord boolErrRecord2 = boolErrRecord;
            boolErrRecord.setValue(cachedBooleanValue);
            this._cellType = CellType.BOOLEAN;
        } else if (i == 6) {
            byte cachedErrorValue = (byte) ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedErrorValue();
            BoolErrRecord boolErrRecord3 = new BoolErrRecord();
            this._record = boolErrRecord3;
            try {
                BoolErrRecord boolErrRecord4 = boolErrRecord3;
                boolErrRecord3.setValue(cachedErrorValue);
            } catch (IllegalArgumentException unused) {
                ((BoolErrRecord) this._record).setValue((byte) ErrorEval.REF_INVALID.getErrorCode());
            }
            this._cellType = CellType.ERROR;
        } else {
            throw new AssertionError();
        }
    }

    private void notifyFormulaChanging() {
        CellValueRecordInterface cellValueRecordInterface = this._record;
        if (cellValueRecordInterface instanceof FormulaRecordAggregate) {
            ((FormulaRecordAggregate) cellValueRecordInterface).notifyFormulaChanging();
        }
    }

    public String getCellFormula() {
        CellValueRecordInterface cellValueRecordInterface = this._record;
        if (cellValueRecordInterface instanceof FormulaRecordAggregate) {
            return HSSFFormulaParser.toFormulaString(this._book, ((FormulaRecordAggregate) cellValueRecordInterface).getFormulaTokens());
        }
        throw typeMismatch(CellType.FORMULA, this._cellType, true);
    }

    private static RuntimeException typeMismatch(CellType cellType, CellType cellType2, boolean z) {
        return new IllegalStateException("Cannot get a " + cellType + " value from a " + cellType2 + " " + (z ? "formula " : "") + "cell");
    }

    private static void checkFormulaCachedValueType(CellType cellType, FormulaRecord formulaRecord) {
        CellType cachedResultTypeEnum = formulaRecord.getCachedResultTypeEnum();
        if (cachedResultTypeEnum != cellType) {
            throw typeMismatch(cellType, cachedResultTypeEnum, true);
        }
    }

    public double getNumericCellValue() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 2) {
            FormulaRecord formulaRecord = ((FormulaRecordAggregate) this._record).getFormulaRecord();
            checkFormulaCachedValueType(CellType.NUMERIC, formulaRecord);
            return formulaRecord.getValue();
        } else if (i == 3) {
            return 0.0d;
        } else {
            if (i == 4) {
                return ((NumberRecord) this._record).getValue();
            }
            throw typeMismatch(CellType.NUMERIC, this._cellType, false);
        }
    }

    public Date getDateCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double numericCellValue = getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getJavaDate(numericCellValue, true);
        }
        return DateUtil.getJavaDate(numericCellValue, false);
    }

    public LocalDateTime getLocalDateTimeCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double numericCellValue = getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getLocalDateTime(numericCellValue, true);
        }
        return DateUtil.getLocalDateTime(numericCellValue, false);
    }

    public String getStringCellValue() {
        return getRichStringCellValue().getString();
    }

    public HSSFRichTextString getRichStringCellValue() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 1) {
            return this._stringValue;
        }
        String str = "";
        if (i == 2) {
            FormulaRecordAggregate formulaRecordAggregate = (FormulaRecordAggregate) this._record;
            checkFormulaCachedValueType(CellType.STRING, formulaRecordAggregate.getFormulaRecord());
            String stringValue = formulaRecordAggregate.getStringValue();
            if (stringValue != null) {
                str = stringValue;
            }
            return new HSSFRichTextString(str);
        } else if (i == 3) {
            return new HSSFRichTextString(str);
        } else {
            throw typeMismatch(CellType.STRING, this._cellType, false);
        }
    }

    public void setCellValue(boolean z) {
        int row = this._record.getRow();
        short column = this._record.getColumn();
        short xFIndex = this._record.getXFIndex();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i != 2) {
            if (i != 5) {
                setCellType(CellType.BOOLEAN, false, row, column, xFIndex);
            }
            ((BoolErrRecord) this._record).setValue(z);
            return;
        }
        ((FormulaRecordAggregate) this._record).setCachedBooleanResult(z);
    }

    @Deprecated
    public void setCellErrorValue(byte b) {
        setCellErrorValue(FormulaError.forInt(b));
    }

    public void setCellErrorValue(FormulaError formulaError) {
        int row = this._record.getRow();
        short column = this._record.getColumn();
        short xFIndex = this._record.getXFIndex();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i != 2) {
            if (i != 6) {
                setCellType(CellType.ERROR, false, row, column, xFIndex);
            }
            ((BoolErrRecord) this._record).setValue(formulaError);
            return;
        }
        ((FormulaRecordAggregate) this._record).setCachedErrorResult((int) formulaError.getCode());
    }

    private boolean convertCellValueToBoolean() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()]) {
            case 1:
                return Boolean.parseBoolean(this._book.getWorkbook().getSSTString(((LabelSSTRecord) this._record).getSSTIndex()).getString());
            case 2:
                FormulaRecord formulaRecord = ((FormulaRecordAggregate) this._record).getFormulaRecord();
                checkFormulaCachedValueType(CellType.BOOLEAN, formulaRecord);
                return formulaRecord.getCachedBooleanValue();
            case 3:
            case 6:
                return false;
            case 4:
                if (((NumberRecord) this._record).getValue() != 0.0d) {
                    return true;
                }
                return false;
            case 5:
                return ((BoolErrRecord) this._record).getBooleanValue();
            default:
                throw new IllegalStateException("Unexpected cell type (" + this._cellType + ")");
        }
    }

    private String convertCellValueToString() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()]) {
            case 1:
                return this._book.getWorkbook().getSSTString(((LabelSSTRecord) this._record).getSSTIndex()).getString();
            case 2:
                FormulaRecordAggregate formulaRecordAggregate = (FormulaRecordAggregate) this._record;
                FormulaRecord formulaRecord = formulaRecordAggregate.getFormulaRecord();
                int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[formulaRecord.getCachedResultTypeEnum().ordinal()];
                if (i == 1) {
                    return formulaRecordAggregate.getStringValue();
                }
                if (i == 4) {
                    return NumberToTextConverter.toText(formulaRecord.getValue());
                }
                if (i == 5) {
                    return formulaRecord.getCachedBooleanValue() ? "TRUE" : "FALSE";
                }
                if (i == 6) {
                    return FormulaError.forInt(formulaRecord.getCachedErrorValue()).getString();
                }
                throw new IllegalStateException("Unexpected formula result type (" + this._cellType + ")");
            case 3:
                return "";
            case 4:
                return NumberToTextConverter.toText(((NumberRecord) this._record).getValue());
            case 5:
                return ((BoolErrRecord) this._record).getBooleanValue() ? "TRUE" : "FALSE";
            case 6:
                return FormulaError.forInt(((BoolErrRecord) this._record).getErrorValue()).getString();
            default:
                throw new IllegalStateException("Unexpected cell type (" + this._cellType + ")");
        }
    }

    public boolean getBooleanCellValue() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 2) {
            FormulaRecord formulaRecord = ((FormulaRecordAggregate) this._record).getFormulaRecord();
            checkFormulaCachedValueType(CellType.BOOLEAN, formulaRecord);
            return formulaRecord.getCachedBooleanValue();
        } else if (i == 3) {
            return false;
        } else {
            if (i == 5) {
                return ((BoolErrRecord) this._record).getBooleanValue();
            }
            throw typeMismatch(CellType.BOOLEAN, this._cellType, false);
        }
    }

    public byte getErrorCellValue() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 2) {
            FormulaRecord formulaRecord = ((FormulaRecordAggregate) this._record).getFormulaRecord();
            checkFormulaCachedValueType(CellType.ERROR, formulaRecord);
            return (byte) formulaRecord.getCachedErrorValue();
        } else if (i == 6) {
            return ((BoolErrRecord) this._record).getErrorValue();
        } else {
            throw typeMismatch(CellType.ERROR, this._cellType, false);
        }
    }

    public void setCellStyle(CellStyle cellStyle) {
        setCellStyle((HSSFCellStyle) cellStyle);
    }

    public void setCellStyle(HSSFCellStyle hSSFCellStyle) {
        short s;
        if (hSSFCellStyle == null) {
            this._record.setXFIndex(15);
            return;
        }
        hSSFCellStyle.verifyBelongsToWorkbook(this._book);
        if (hSSFCellStyle.getUserStyleName() != null) {
            s = applyUserCellStyle(hSSFCellStyle);
        } else {
            s = hSSFCellStyle.getIndex();
        }
        this._record.setXFIndex(s);
    }

    public HSSFCellStyle getCellStyle() {
        short xFIndex = this._record.getXFIndex();
        return new HSSFCellStyle(xFIndex, this._book.getWorkbook().getExFormatAt(xFIndex), this._book);
    }

    /* access modifiers changed from: protected */
    public CellValueRecordInterface getCellValueRecord() {
        return this._record;
    }

    private static void checkBounds(int i) {
        if (i < 0 || i > LAST_COLUMN_NUMBER) {
            throw new IllegalArgumentException("Invalid column index (" + i + ").  Allowable column range for BIFF8 is (0.." + LAST_COLUMN_NUMBER + ") or ('A'..'" + LAST_COLUMN_NAME + "')");
        }
    }

    public void setAsActiveCell() {
        int row = this._record.getRow();
        short column = this._record.getColumn();
        this._sheet.getSheet().setActiveCellRow(row);
        this._sheet.getSheet().setActiveCellCol(column);
    }

    public String toString() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[getCellType().ordinal()]) {
            case 1:
                return getStringCellValue();
            case 2:
                return getCellFormula();
            case 3:
                return "";
            case 4:
                if (!DateUtil.isCellDateFormatted(this)) {
                    return String.valueOf(getNumericCellValue());
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
                return simpleDateFormat.format(getDateCellValue());
            case 5:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case 6:
                return ErrorEval.getText(((BoolErrRecord) this._record).getErrorValue());
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    public void setCellComment(Comment comment) {
        if (comment == null) {
            removeCellComment();
            return;
        }
        comment.setRow(this._record.getRow());
        comment.setColumn(this._record.getColumn());
        this._comment = (HSSFComment) comment;
    }

    public HSSFComment getCellComment() {
        if (this._comment == null) {
            this._comment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        }
        return this._comment;
    }

    public void removeCellComment() {
        HSSFComment findCellComment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        this._comment = null;
        if (findCellComment != null) {
            this._sheet.getDrawingPatriarch().removeShape(findCellComment);
        }
    }

    public HSSFHyperlink getHyperlink() {
        return this._sheet.getHyperlink(this._record.getRow(), (int) this._record.getColumn());
    }

    public void setHyperlink(Hyperlink hyperlink) {
        HSSFHyperlink hSSFHyperlink;
        if (hyperlink == null) {
            removeHyperlink();
            return;
        }
        if (hyperlink instanceof HSSFHyperlink) {
            hSSFHyperlink = (HSSFHyperlink) hyperlink;
        } else {
            hSSFHyperlink = new HSSFHyperlink(hyperlink);
        }
        hSSFHyperlink.setFirstRow(this._record.getRow());
        hSSFHyperlink.setLastRow(this._record.getRow());
        hSSFHyperlink.setFirstColumn(this._record.getColumn());
        hSSFHyperlink.setLastColumn(this._record.getColumn());
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$HyperlinkType[hSSFHyperlink.getType().ordinal()];
        if (i == 1 || i == 2) {
            hSSFHyperlink.setLabel(ImagesContract.URL);
        } else if (i == 3) {
            hSSFHyperlink.setLabel("file");
        } else if (i == 4) {
            hSSFHyperlink.setLabel("place");
        }
        List<RecordBase> records = this._sheet.getSheet().getRecords();
        records.add(records.size() - 1, hSSFHyperlink.record);
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFCell$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        static {
            /*
                org.apache.poi.common.usermodel.HyperlinkType[] r0 = org.apache.poi.common.usermodel.HyperlinkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType = r0
                r1 = 1
                org.apache.poi.common.usermodel.HyperlinkType r2 = org.apache.poi.common.usermodel.HyperlinkType.EMAIL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.HyperlinkType r3 = org.apache.poi.common.usermodel.HyperlinkType.URL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.HyperlinkType r4 = org.apache.poi.common.usermodel.HyperlinkType.FILE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.HyperlinkType r5 = org.apache.poi.common.usermodel.HyperlinkType.DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                org.apache.poi.ss.usermodel.CellType[] r4 = org.apache.poi.ss.usermodel.CellType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r4
                org.apache.poi.ss.usermodel.CellType r5 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x004e }
                org.apache.poi.ss.usermodel.CellType r4 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BLANK     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0062 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x006d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFCell.AnonymousClass1.<clinit>():void");
        }
    }

    public void removeHyperlink() {
        Iterator<RecordBase> it = this._sheet.getSheet().getRecords().iterator();
        while (it.hasNext()) {
            RecordBase next = it.next();
            if (next instanceof HyperlinkRecord) {
                HyperlinkRecord hyperlinkRecord = (HyperlinkRecord) next;
                if (hyperlinkRecord.getFirstColumn() == this._record.getColumn() && hyperlinkRecord.getFirstRow() == this._record.getRow()) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public CellType getCachedFormulaResultType() {
        if (this._cellType == CellType.FORMULA) {
            return ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedResultTypeEnum();
        }
        throw new IllegalStateException("Only formula cells have cached results");
    }

    /* access modifiers changed from: package-private */
    public void setCellArrayFormula(CellRangeAddress cellRangeAddress) {
        setCellType(CellType.FORMULA, false, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
        ((FormulaRecordAggregate) this._record).setParsedExpression(new Ptg[]{new ExpPtg(cellRangeAddress.getFirstRow(), cellRangeAddress.getFirstColumn())});
    }

    public CellRangeAddress getArrayFormulaRange() {
        if (this._cellType == CellType.FORMULA) {
            return ((FormulaRecordAggregate) this._record).getArrayFormulaRange();
        }
        throw new IllegalStateException("Cell " + new CellReference((Cell) this).formatAsString() + " is not part of an array formula.");
    }

    public boolean isPartOfArrayFormulaGroup() {
        return this._cellType == CellType.FORMULA && ((FormulaRecordAggregate) this._record).isPartOfArrayFormula();
    }

    private short applyUserCellStyle(HSSFCellStyle hSSFCellStyle) {
        if (hSSFCellStyle.getUserStyleName() != null) {
            InternalWorkbook workbook = this._book.getWorkbook();
            int numExFormats = workbook.getNumExFormats();
            short s = 0;
            while (true) {
                if (s >= numExFormats) {
                    s = -1;
                    break;
                }
                ExtendedFormatRecord exFormatAt = workbook.getExFormatAt(s);
                if (exFormatAt.getXFType() == 0 && exFormatAt.getParentIndex() == hSSFCellStyle.getIndex()) {
                    break;
                }
                s = (short) (s + 1);
            }
            if (s != -1) {
                return s;
            }
            ExtendedFormatRecord createCellXF = workbook.createCellXF();
            createCellXF.cloneStyleFrom(workbook.getExFormatAt(hSSFCellStyle.getIndex()));
            createCellXF.setIndentionOptions(0);
            createCellXF.setXFType(0);
            createCellXF.setParentIndex(hSSFCellStyle.getIndex());
            return (short) numExFormats;
        }
        throw new IllegalArgumentException("Expected user-defined style");
    }
}
