package org.apache.poi.xssf.usermodel.helpers;

import java.util.Iterator;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Pxg;
import org.apache.poi.ss.formula.ptg.Pxg3D;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class XSSFFormulaUtils {
    private final XSSFEvaluationWorkbook _fpwb;
    private final XSSFWorkbook _wb;

    public XSSFFormulaUtils(XSSFWorkbook xSSFWorkbook) {
        this._wb = xSSFWorkbook;
        this._fpwb = XSSFEvaluationWorkbook.create(xSSFWorkbook);
    }

    public void updateSheetName(int i, String str, String str2) {
        for (XSSFName next : this._wb.getAllNames()) {
            if (next.getSheetIndex() == -1 || next.getSheetIndex() == i) {
                updateName(next, str, str2);
            }
        }
        Iterator<Sheet> it = this._wb.iterator();
        while (it.hasNext()) {
            for (Row it2 : it.next()) {
                for (Cell next2 : it2) {
                    if (next2.getCellType() == CellType.FORMULA) {
                        updateFormula((XSSFCell) next2, str, str2);
                    }
                }
            }
        }
        for (POIXMLDocumentPart next3 : this._wb.getSheetAt(i).getRelations()) {
            if (next3 instanceof XSSFDrawing) {
                for (XSSFChart cTChartSpace : ((XSSFDrawing) next3).getCharts()) {
                    updateDomSheetReference(cTChartSpace.getCTChartSpace().getDomNode(), str, str2);
                }
            }
        }
    }

    private void updateFormula(XSSFCell xSSFCell, String str, String str2) {
        String stringValue;
        CTCellFormula f = xSSFCell.getCTCell().getF();
        if (f != null && (stringValue = f.getStringValue()) != null && stringValue.length() > 0) {
            Ptg[] parse = FormulaParser.parse(stringValue, this._fpwb, FormulaType.CELL, this._wb.getSheetIndex((Sheet) xSSFCell.getSheet()), xSSFCell.getRowIndex());
            for (Ptg updatePtg : parse) {
                updatePtg(updatePtg, str, str2);
            }
            String formulaString = FormulaRenderer.toFormulaString(this._fpwb, parse);
            if (!stringValue.equals(formulaString)) {
                f.setStringValue(formulaString);
            }
        }
    }

    private void updateName(XSSFName xSSFName, String str, String str2) {
        String refersToFormula = xSSFName.getRefersToFormula();
        if (refersToFormula != null) {
            Ptg[] parse = FormulaParser.parse(refersToFormula, this._fpwb, FormulaType.NAMEDRANGE, xSSFName.getSheetIndex(), -1);
            for (Ptg updatePtg : parse) {
                updatePtg(updatePtg, str, str2);
            }
            String formulaString = FormulaRenderer.toFormulaString(this._fpwb, parse);
            if (!refersToFormula.equals(formulaString)) {
                xSSFName.setRefersToFormula(formulaString);
            }
        }
    }

    private void updatePtg(Ptg ptg, String str, String str2) {
        if (ptg instanceof Pxg) {
            Pxg pxg = (Pxg) ptg;
            if (pxg.getExternalWorkbookNumber() < 1) {
                if (pxg.getSheetName() != null && pxg.getSheetName().equals(str)) {
                    pxg.setSheetName(str2);
                }
                if (pxg instanceof Pxg3D) {
                    Pxg3D pxg3D = (Pxg3D) pxg;
                    if (pxg3D.getLastSheetName() != null && pxg3D.getLastSheetName().equals(str)) {
                        pxg3D.setLastSheetName(str2);
                    }
                }
            }
        }
    }

    private void updateDomSheetReference(Node node, String str, String str2) {
        String nodeValue = node.getNodeValue();
        if (nodeValue != null && (nodeValue.contains(str + "!") || nodeValue.contains(str + "'!"))) {
            XSSFName createName = this._wb.createName();
            createName.setRefersToFormula(nodeValue);
            updateName(createName, str, str2);
            node.setNodeValue(createName.getRefersToFormula());
            this._wb.removeName(createName);
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            updateDomSheetReference(childNodes.item(i), str, str2);
        }
    }
}
