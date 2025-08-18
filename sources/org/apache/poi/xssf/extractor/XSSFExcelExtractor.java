package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;

public class XSSFExcelExtractor implements POIXMLTextExtractor, ExcelExtractor {
    public static final List<XSSFRelation> SUPPORTED_TYPES = Collections.unmodifiableList(Arrays.asList(new XSSFRelation[]{XSSFRelation.WORKBOOK, XSSFRelation.MACRO_TEMPLATE_WORKBOOK, XSSFRelation.MACRO_ADDIN_WORKBOOK, XSSFRelation.TEMPLATE_WORKBOOK, XSSFRelation.MACROS_WORKBOOK}));
    private boolean doCloseFilesystem;
    private boolean formulasNotResults;
    private boolean includeCellComments;
    private boolean includeHeadersFooters;
    private boolean includeSheetNames;
    private boolean includeTextBoxes;
    private Locale locale;
    private final XSSFWorkbook workbook;

    public XSSFExcelExtractor(OPCPackage oPCPackage) throws XmlException, OpenXML4JException, IOException {
        this(new XSSFWorkbook(oPCPackage));
    }

    public XSSFExcelExtractor(XSSFWorkbook xSSFWorkbook) {
        this.includeSheetNames = true;
        this.includeHeadersFooters = true;
        this.includeTextBoxes = true;
        this.doCloseFilesystem = true;
        this.workbook = xSSFWorkbook;
    }

    public void setIncludeSheetNames(boolean z) {
        this.includeSheetNames = z;
    }

    public void setFormulasNotResults(boolean z) {
        this.formulasNotResults = z;
    }

    public void setIncludeCellComments(boolean z) {
        this.includeCellComments = z;
    }

    public void setIncludeHeadersFooters(boolean z) {
        this.includeHeadersFooters = z;
    }

    public void setIncludeTextBoxes(boolean z) {
        this.includeTextBoxes = z;
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public String getText() {
        DataFormatter dataFormatter;
        XSSFDrawing drawingPatriarch;
        if (this.locale == null) {
            dataFormatter = new DataFormatter();
        } else {
            dataFormatter = new DataFormatter(this.locale);
        }
        StringBuilder sb = new StringBuilder(64);
        Iterator<Sheet> it = this.workbook.iterator();
        while (it.hasNext()) {
            XSSFSheet xSSFSheet = (XSSFSheet) it.next();
            if (this.includeSheetNames) {
                sb.append(xSSFSheet.getSheetName()).append("\n");
            }
            if (this.includeHeadersFooters) {
                sb.append(extractHeaderFooter(xSSFSheet.getFirstHeader()));
                sb.append(extractHeaderFooter(xSSFSheet.getOddHeader()));
                sb.append(extractHeaderFooter(xSSFSheet.getEvenHeader()));
            }
            Iterator<Row> it2 = xSSFSheet.iterator();
            while (it2.hasNext()) {
                Iterator<Cell> cellIterator = it2.next().cellIterator();
                while (cellIterator.hasNext()) {
                    Cell next = cellIterator.next();
                    if (next.getCellType() == CellType.FORMULA) {
                        if (this.formulasNotResults) {
                            String cellFormula = next.getCellFormula();
                            checkMaxTextSize(sb, cellFormula);
                            sb.append(cellFormula);
                        } else if (next.getCachedFormulaResultType() == CellType.STRING) {
                            handleStringCell(sb, next);
                        } else {
                            handleNonStringCell(sb, next, dataFormatter);
                        }
                    } else if (next.getCellType() == CellType.STRING) {
                        handleStringCell(sb, next);
                    } else {
                        handleNonStringCell(sb, next, dataFormatter);
                    }
                    Comment cellComment = next.getCellComment();
                    if (this.includeCellComments && cellComment != null) {
                        String replace = cellComment.getString().getString().replace(10, Chars.SPACE);
                        checkMaxTextSize(sb, replace);
                        sb.append(" Comment by ").append(cellComment.getAuthor()).append(": ").append(replace);
                    }
                    if (cellIterator.hasNext()) {
                        sb.append("\t");
                    }
                }
                sb.append("\n");
            }
            if (this.includeTextBoxes && (drawingPatriarch = xSSFSheet.getDrawingPatriarch()) != null) {
                for (XSSFShape next2 : drawingPatriarch.getShapes()) {
                    if (next2 instanceof XSSFSimpleShape) {
                        String text = ((XSSFSimpleShape) next2).getText();
                        if (text.length() > 0) {
                            sb.append(text);
                            sb.append(10);
                        }
                    }
                }
            }
            if (this.includeHeadersFooters) {
                sb.append(extractHeaderFooter(xSSFSheet.getFirstFooter()));
                sb.append(extractHeaderFooter(xSSFSheet.getOddFooter()));
                sb.append(extractHeaderFooter(xSSFSheet.getEvenFooter()));
            }
        }
        return sb.toString();
    }

    private void handleStringCell(StringBuilder sb, Cell cell) {
        String string = cell.getRichStringCellValue().getString();
        checkMaxTextSize(sb, string);
        sb.append(string);
    }

    private void handleNonStringCell(StringBuilder sb, Cell cell, DataFormatter dataFormatter) {
        CellStyle cellStyle;
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = cell.getCachedFormulaResultType();
        }
        if (cellType != CellType.NUMERIC || (cellStyle = cell.getCellStyle()) == null || cellStyle.getDataFormatString() == null) {
            String rawValue = ((XSSFCell) cell).getRawValue();
            if (rawValue != null) {
                checkMaxTextSize(sb, rawValue);
                sb.append(rawValue);
                return;
            }
            return;
        }
        String formatRawCellContents = dataFormatter.formatRawCellContents(cell.getNumericCellValue(), cellStyle.getDataFormat(), cellStyle.getDataFormatString());
        checkMaxTextSize(sb, formatRawCellContents);
        sb.append(formatRawCellContents);
    }

    private String extractHeaderFooter(HeaderFooter headerFooter) {
        return org.apache.poi.hssf.extractor.ExcelExtractor._extractHeaderFooter(headerFooter);
    }

    public XSSFWorkbook getDocument() {
        return this.workbook;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public XSSFWorkbook getFilesystem() {
        return this.workbook;
    }
}
