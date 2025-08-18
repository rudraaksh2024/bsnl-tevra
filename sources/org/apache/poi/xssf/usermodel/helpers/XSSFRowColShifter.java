package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;

@Internal
final class XSSFRowColShifter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFRowColShifter.class);

    private XSSFRowColShifter() {
    }

    static void updateNamedRanges(Sheet sheet, FormulaShifter formulaShifter) {
        Workbook workbook = sheet.getWorkbook();
        XSSFEvaluationWorkbook create = XSSFEvaluationWorkbook.create((XSSFWorkbook) workbook);
        for (Name name : workbook.getAllNames()) {
            String refersToFormula = name.getRefersToFormula();
            int sheetIndex = name.getSheetIndex();
            Ptg[] parse = FormulaParser.parse(refersToFormula, create, FormulaType.NAMEDRANGE, sheetIndex, -1);
            if (formulaShifter.adjustFormula(parse, sheetIndex)) {
                name.setRefersToFormula(FormulaRenderer.toFormulaString(create, parse));
            }
        }
    }

    static void updateFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        updateSheetFormulas(sheet, formulaShifter);
        for (Sheet next : sheet.getWorkbook()) {
            if (sheet != next) {
                updateSheetFormulas(next, formulaShifter);
            }
        }
    }

    static void updateSheetFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            updateRowFormulas((XSSFRow) it.next(), formulaShifter);
        }
    }

    static void updateRowFormulas(XSSFRow xSSFRow, FormulaShifter formulaShifter) {
        String shiftFormula;
        XSSFSheet sheet = xSSFRow.getSheet();
        Iterator<Cell> it = xSSFRow.iterator();
        while (it.hasNext()) {
            CTCell cTCell = ((XSSFCell) it.next()).getCTCell();
            if (cTCell.isSetF()) {
                CTCellFormula f = cTCell.getF();
                String stringValue = f.getStringValue();
                if (stringValue.length() > 0 && (shiftFormula = shiftFormula(xSSFRow, stringValue, formulaShifter)) != null) {
                    f.setStringValue(shiftFormula);
                    if (f.getT() == STCellFormulaType.SHARED) {
                        CTCellFormula sharedFormula = sheet.getSharedFormula(Math.toIntExact(f.getSi()));
                        sharedFormula.setStringValue(shiftFormula);
                        updateRefInCTCellFormula(xSSFRow, formulaShifter, sharedFormula);
                    }
                }
                updateRefInCTCellFormula(xSSFRow, formulaShifter, f);
            }
        }
    }

    static String shiftFormula(Row row, String str, FormulaShifter formulaShifter) {
        Sheet sheet = row.getSheet();
        Workbook workbook = sheet.getWorkbook();
        int sheetIndex = workbook.getSheetIndex(sheet);
        int rowNum = row.getRowNum();
        XSSFEvaluationWorkbook create = XSSFEvaluationWorkbook.create((XSSFWorkbook) workbook);
        try {
            Ptg[] parse = FormulaParser.parse(str, create, FormulaType.CELL, sheetIndex, rowNum);
            if (formulaShifter.adjustFormula(parse, sheetIndex)) {
                return FormulaRenderer.toFormulaString(create, parse);
            }
            return null;
        } catch (FormulaParseException e) {
            LOG.atWarn().withThrowable(e).log("Error shifting formula on row {}", (Object) Unbox.box(row.getRowNum()));
            return str;
        }
    }

    static void updateRefInCTCellFormula(Row row, FormulaShifter formulaShifter, CTCellFormula cTCellFormula) {
        String shiftFormula;
        if (cTCellFormula.isSetRef() && (shiftFormula = shiftFormula(row, cTCellFormula.getRef(), formulaShifter)) != null) {
            cTCellFormula.setRef(shiftFormula);
        }
    }

    static void updateConditionalFormatting(Sheet sheet, FormulaShifter formulaShifter) {
        Sheet sheet2 = sheet;
        FormulaShifter formulaShifter2 = formulaShifter;
        XSSFSheet xSSFSheet = (XSSFSheet) sheet2;
        XSSFWorkbook workbook = xSSFSheet.getWorkbook();
        int sheetIndex = workbook.getSheetIndex(sheet2);
        XSSFEvaluationWorkbook create = XSSFEvaluationWorkbook.create(workbook);
        CTWorksheet cTWorksheet = xSSFSheet.getCTWorksheet();
        CTConditionalFormatting[] conditionalFormattingArray = cTWorksheet.getConditionalFormattingArray();
        boolean z = true;
        int length = conditionalFormattingArray.length - 1;
        while (length >= 0) {
            CTConditionalFormatting cTConditionalFormatting = conditionalFormattingArray[length];
            ArrayList arrayList = new ArrayList();
            Iterator it = cTConditionalFormatting.getSqref().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                for (String valueOf : it.next().toString().split(" ")) {
                    arrayList.add(CellRangeAddress.valueOf(valueOf));
                }
            }
            ArrayList<CellRangeAddress> arrayList2 = new ArrayList<>();
            Iterator it2 = arrayList.iterator();
            boolean z2 = false;
            while (it2.hasNext()) {
                CellRangeAddress cellRangeAddress = (CellRangeAddress) it2.next();
                CellRangeAddress shiftRange = BaseRowColShifter.shiftRange(formulaShifter2, cellRangeAddress, sheetIndex);
                if (shiftRange != null) {
                    arrayList2.add(shiftRange);
                    if (shiftRange == cellRangeAddress) {
                    }
                }
                z2 = z;
            }
            if (z2) {
                if (arrayList2.size() == 0) {
                    cTWorksheet.removeConditionalFormatting(length);
                    length--;
                    z = true;
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    for (CellRangeAddress formatAsString : arrayList2) {
                        arrayList3.add(formatAsString.formatAsString());
                    }
                    cTConditionalFormatting.setSqref(arrayList3);
                }
            }
            for (CTCfRule cTCfRule : cTConditionalFormatting.getCfRuleArray()) {
                String[] formulaArray = cTCfRule.getFormulaArray();
                for (int i = 0; i < formulaArray.length; i++) {
                    Ptg[] parse = FormulaParser.parse(formulaArray[i], create, FormulaType.CELL, sheetIndex, -1);
                    if (formulaShifter2.adjustFormula(parse, sheetIndex)) {
                        cTCfRule.setFormulaArray(i, FormulaRenderer.toFormulaString(create, parse));
                    }
                }
            }
            length--;
            z = true;
        }
    }

    static void updateHyperlinks(Sheet sheet, FormulaShifter formulaShifter) {
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        for (Hyperlink hyperlink : sheet.getHyperlinkList()) {
            XSSFHyperlink xSSFHyperlink = (XSSFHyperlink) hyperlink;
            CellRangeAddress valueOf = CellRangeAddress.valueOf(xSSFHyperlink.getCellRef());
            CellRangeAddress shiftRange = BaseRowColShifter.shiftRange(formulaShifter, valueOf, sheetIndex);
            if (!(shiftRange == null || shiftRange == valueOf)) {
                xSSFHyperlink.setCellReference(shiftRange.formatAsString());
            }
        }
    }
}
