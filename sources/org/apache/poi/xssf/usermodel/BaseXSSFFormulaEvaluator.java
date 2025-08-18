package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.ExternalLinksTable;

public abstract class BaseXSSFFormulaEvaluator extends BaseFormulaEvaluator {
    /* access modifiers changed from: protected */
    public abstract EvaluationCell toEvaluationCell(Cell cell);

    protected BaseXSSFFormulaEvaluator(WorkbookEvaluator workbookEvaluator) {
        super(workbookEvaluator);
    }

    /* access modifiers changed from: protected */
    public RichTextString createRichTextString(String str) {
        return new XSSFRichTextString(str);
    }

    /* access modifiers changed from: protected */
    public CellValue evaluateFormulaCellValue(Cell cell) {
        EvaluationCell evaluationCell = toEvaluationCell(cell);
        ValueEval evaluate = this._bookEvaluator.evaluate(evaluationCell);
        cacheExternalWorkbookCells(evaluationCell);
        if (evaluate instanceof NumberEval) {
            return new CellValue(((NumberEval) evaluate).getNumberValue());
        }
        if (evaluate instanceof BoolEval) {
            return CellValue.valueOf(((BoolEval) evaluate).getBooleanValue());
        }
        if (evaluate instanceof StringEval) {
            return new CellValue(((StringEval) evaluate).getStringValue());
        }
        if (evaluate instanceof ErrorEval) {
            return CellValue.getError(((ErrorEval) evaluate).getErrorCode());
        }
        throw new RuntimeException("Unexpected eval class (" + evaluate.getClass().getName() + ")");
    }

    private void cacheExternalWorkbookCells(EvaluationCell evaluationCell) {
        EvaluationCell evaluationCell2 = evaluationCell;
        Ptg[] formulaTokens = getEvaluationWorkbook().getFormulaTokens(evaluationCell2);
        int length = formulaTokens.length;
        int i = 0;
        while (i < length) {
            Ptg ptg = formulaTokens[i];
            if (ptg instanceof Area3DPxg) {
                Area3DPxg area3DPxg = (Area3DPxg) ptg;
                if (area3DPxg.getExternalWorkbookNumber() > 0) {
                    EvaluationWorkbook.ExternalSheet externalSheet = getEvaluationWorkbook().getExternalSheet(area3DPxg.getSheetName(), area3DPxg.getLastSheetName(), area3DPxg.getExternalWorkbookNumber());
                    XSSFCell xSSFCell = ((XSSFEvaluationCell) evaluationCell2).getXSSFCell();
                    XSSFWorkbook xSSFWorkbook = (XSSFWorkbook) xSSFCell.getSheet().getWorkbook().getCreationHelper().getReferencedWorkbooks().get(externalSheet.getWorkbookName());
                    ExternalLinksTable externalLinksTable = xSSFCell.getSheet().getWorkbook().getExternalLinksTable().get(area3DPxg.getExternalWorkbookNumber() - 1);
                    int sheetIndex = xSSFWorkbook.getSheetIndex(area3DPxg.getSheetName());
                    int sheetIndex2 = area3DPxg.getLastSheetName() != null ? xSSFWorkbook.getSheetIndex(area3DPxg.getLastSheetName()) : sheetIndex;
                    int i2 = sheetIndex;
                    while (i2 <= sheetIndex2) {
                        XSSFSheet sheetAt = xSSFWorkbook.getSheetAt(i2);
                        int firstRow = area3DPxg.getFirstRow();
                        int lastRow = area3DPxg.getLastRow();
                        int i3 = firstRow;
                        while (i3 <= lastRow) {
                            XSSFRow row = sheetAt.getRow(i3);
                            int firstColumn = area3DPxg.getFirstColumn();
                            int lastColumn = area3DPxg.getLastColumn();
                            while (firstColumn <= lastColumn) {
                                XSSFCell cell = row.getCell(firstColumn);
                                String rawValue = cell.getRawValue();
                                String formatAsString = new CellReference((Cell) cell).formatAsString(false);
                                int i4 = lastRow;
                                externalLinksTable.cacheData(sheetAt.getSheetName(), ((long) i3) + 1, formatAsString, rawValue);
                                firstColumn++;
                                sheetAt = sheetAt;
                                lastRow = i4;
                                i3 = i3;
                                i = i;
                                lastColumn = lastColumn;
                                row = row;
                                EvaluationCell evaluationCell3 = evaluationCell;
                            }
                            int i5 = i;
                            int i6 = lastRow;
                            XSSFSheet xSSFSheet = sheetAt;
                            i3++;
                            EvaluationCell evaluationCell4 = evaluationCell;
                        }
                        int i7 = i;
                        i2++;
                        EvaluationCell evaluationCell5 = evaluationCell;
                    }
                }
            }
            i++;
            evaluationCell2 = evaluationCell;
        }
    }

    /* access modifiers changed from: protected */
    public void setCellType(Cell cell, CellType cellType) {
        if (cell instanceof XSSFCell) {
            EvaluationWorkbook evaluationWorkbook = getEvaluationWorkbook();
            ((XSSFCell) cell).setCellType(cellType, BaseXSSFEvaluationWorkbook.class.isAssignableFrom(evaluationWorkbook.getClass()) ? (BaseXSSFEvaluationWorkbook) evaluationWorkbook : null);
            return;
        }
        cell.setCellType(cellType);
    }
}
