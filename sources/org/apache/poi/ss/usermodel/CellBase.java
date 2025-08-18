package org.apache.poi.ss.usermodel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;

public abstract class CellBase implements Cell {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* access modifiers changed from: protected */
    public abstract SpreadsheetVersion getSpreadsheetVersion();

    /* access modifiers changed from: protected */
    public abstract void removeFormulaImpl();

    /* access modifiers changed from: protected */
    public abstract void setCellFormulaImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void setCellTypeImpl(CellType cellType);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(double d);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(LocalDateTime localDateTime);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(Calendar calendar);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(Date date);

    /* access modifiers changed from: protected */
    public abstract void setCellValueImpl(RichTextString richTextString);

    public final void setCellType(CellType cellType) {
        if (cellType == null || cellType == CellType._NONE) {
            throw new IllegalArgumentException("cellType shall not be null nor _NONE");
        } else if (cellType != CellType.FORMULA) {
            tryToDeleteArrayFormulaIfSet();
            setCellTypeImpl(cellType);
        } else if (getCellType() != CellType.FORMULA) {
            throw new IllegalArgumentException("Calling Cell.setCellType(CellType.FORMULA) is illegal. Use setCellFormula(String) directly.");
        }
    }

    public void setBlank() {
        setCellType(CellType.BLANK);
    }

    public CellAddress getAddress() {
        return new CellAddress((Cell) this);
    }

    public final void tryToDeleteArrayFormula(String str) {
        if (getArrayFormulaRange().getNumberOfCells() > 1) {
            if (str == null) {
                str = "Cell " + new CellReference((Cell) this).formatAsString() + " is part of a multi-cell array formula. You cannot change part of an array.";
            }
            throw new IllegalStateException(str);
        }
        getRow().getSheet().removeArrayFormula(this);
    }

    public final void setCellFormula(String str) throws FormulaParseException, IllegalStateException {
        tryToDeleteArrayFormulaIfSet();
        if (str == null) {
            removeFormula();
        } else {
            setCellFormulaImpl(str);
        }
    }

    /* access modifiers changed from: protected */
    public final CellType getValueType() {
        CellType cellType = getCellType();
        if (cellType != CellType.FORMULA) {
            return cellType;
        }
        return getCachedFormulaResultType();
    }

    public final void removeFormula() {
        if (getCellType() != CellType.BLANK) {
            if (isPartOfArrayFormulaGroup()) {
                tryToDeleteArrayFormula((String) null);
            } else {
                removeFormulaImpl();
            }
        }
    }

    private void tryToDeleteArrayFormulaIfSet() {
        if (isPartOfArrayFormulaGroup()) {
            tryToDeleteArrayFormula((String) null);
        }
    }

    public void setCellValue(double d) {
        if (Double.isInfinite(d)) {
            setCellErrorValue(FormulaError.DIV0.getCode());
        } else if (Double.isNaN(d)) {
            setCellErrorValue(FormulaError.NUM.getCode());
        } else {
            setCellValueImpl(d);
        }
    }

    public void setCellValue(Date date) {
        if (date == null) {
            setBlank();
        } else {
            setCellValueImpl(date);
        }
    }

    public void setCellValue(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            setBlank();
        } else {
            setCellValueImpl(localDateTime);
        }
    }

    public void setCellValue(Calendar calendar) {
        if (calendar == null) {
            setBlank();
        } else {
            setCellValueImpl(calendar);
        }
    }

    public void setCellValue(String str) {
        if (str == null) {
            setBlank();
            return;
        }
        checkLength(str);
        setCellValueImpl(str);
    }

    private void checkLength(String str) {
        if (str.length() > getSpreadsheetVersion().getMaxTextLength()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "The maximum length of cell contents (text) is %d characters", new Object[]{Integer.valueOf(getSpreadsheetVersion().getMaxTextLength())}));
        }
    }

    public void setCellValue(RichTextString richTextString) {
        if (richTextString == null || richTextString.getString() == null) {
            setBlank();
            return;
        }
        checkLength(richTextString.getString());
        setCellValueImpl(richTextString);
    }
}
