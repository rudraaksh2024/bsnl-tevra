package org.apache.poi.ss.usermodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

public interface Cell {
    CellAddress getAddress();

    CellRangeAddress getArrayFormulaRange();

    boolean getBooleanCellValue();

    CellType getCachedFormulaResultType();

    Comment getCellComment();

    String getCellFormula();

    CellStyle getCellStyle();

    CellType getCellType();

    int getColumnIndex();

    Date getDateCellValue();

    byte getErrorCellValue();

    Hyperlink getHyperlink();

    LocalDateTime getLocalDateTimeCellValue();

    double getNumericCellValue();

    RichTextString getRichStringCellValue();

    Row getRow();

    int getRowIndex();

    Sheet getSheet();

    String getStringCellValue();

    boolean isPartOfArrayFormulaGroup();

    void removeCellComment();

    void removeFormula() throws IllegalStateException;

    void removeHyperlink();

    void setAsActiveCell();

    void setBlank();

    void setCellComment(Comment comment);

    void setCellErrorValue(byte b);

    void setCellFormula(String str) throws FormulaParseException, IllegalStateException;

    void setCellStyle(CellStyle cellStyle);

    @Deprecated
    @Removal(version = "5.0")
    void setCellType(CellType cellType);

    void setCellValue(double d);

    void setCellValue(String str);

    void setCellValue(LocalDateTime localDateTime);

    void setCellValue(Calendar calendar);

    void setCellValue(Date date);

    void setCellValue(RichTextString richTextString);

    void setCellValue(boolean z);

    void setHyperlink(Hyperlink hyperlink);

    void setCellValue(LocalDate localDate) {
        setCellValue(localDate == null ? null : localDate.atStartOfDay());
    }
}
