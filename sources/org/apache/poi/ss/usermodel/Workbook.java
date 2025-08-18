package org.apache.poi.ss.usermodel;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Removal;

public interface Workbook extends Closeable, Iterable<Sheet> {
    public static final int MAX_SENSITIVE_SHEET_NAME_LEN = 31;
    public static final int PICTURE_TYPE_DIB = 7;
    public static final int PICTURE_TYPE_EMF = 2;
    public static final int PICTURE_TYPE_JPEG = 5;
    public static final int PICTURE_TYPE_PICT = 4;
    public static final int PICTURE_TYPE_PNG = 6;
    public static final int PICTURE_TYPE_WMF = 3;

    int addOlePackage(byte[] bArr, String str, String str2, String str3) throws IOException;

    int addPicture(byte[] bArr, int i);

    void addToolPack(UDFFinder uDFFinder);

    Sheet cloneSheet(int i);

    void close() throws IOException;

    CellStyle createCellStyle();

    DataFormat createDataFormat();

    EvaluationWorkbook createEvaluationWorkbook();

    Font createFont();

    Name createName();

    Sheet createSheet();

    Sheet createSheet(String str);

    Font findFont(boolean z, short s, short s2, String str, boolean z2, boolean z3, short s3, byte b);

    int getActiveSheetIndex();

    List<? extends Name> getAllNames();

    List<? extends PictureData> getAllPictures();

    CellReferenceType getCellReferenceType();

    CellStyle getCellStyleAt(int i);

    CreationHelper getCreationHelper();

    int getFirstVisibleTab();

    Font getFontAt(int i);

    boolean getForceFormulaRecalculation();

    Row.MissingCellPolicy getMissingCellPolicy();

    Name getName(String str);

    List<? extends Name> getNames(String str);

    int getNumCellStyles();

    int getNumberOfFonts();

    @Deprecated
    @Removal(version = "6.0.0")
    int getNumberOfFontsAsInt();

    int getNumberOfNames();

    int getNumberOfSheets();

    String getPrintArea(int i);

    Sheet getSheet(String str);

    Sheet getSheetAt(int i);

    int getSheetIndex(String str);

    int getSheetIndex(Sheet sheet);

    String getSheetName(int i);

    SheetVisibility getSheetVisibility(int i);

    SpreadsheetVersion getSpreadsheetVersion();

    boolean isHidden();

    boolean isSheetHidden(int i);

    boolean isSheetVeryHidden(int i);

    int linkExternalWorkbook(String str, Workbook workbook);

    void removeName(Name name);

    void removePrintArea(int i);

    void removeSheetAt(int i);

    void setActiveSheet(int i);

    void setCellReferenceType(CellReferenceType cellReferenceType);

    void setFirstVisibleTab(int i);

    void setForceFormulaRecalculation(boolean z);

    void setHidden(boolean z);

    void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy);

    void setPrintArea(int i, int i2, int i3, int i4, int i5);

    void setPrintArea(int i, String str);

    void setSelectedTab(int i);

    void setSheetHidden(int i, boolean z);

    void setSheetName(int i, String str);

    void setSheetOrder(String str, int i);

    void setSheetVisibility(int i, SheetVisibility sheetVisibility);

    Iterator<Sheet> sheetIterator();

    void write(OutputStream outputStream) throws IOException;

    Iterator<Sheet> iterator() {
        return sheetIterator();
    }

    Spliterator<Sheet> spliterator() {
        return Spliterators.spliterator(sheetIterator(), (long) getNumberOfSheets(), 0);
    }
}
