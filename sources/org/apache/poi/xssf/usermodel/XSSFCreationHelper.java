package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

public class XSSFCreationHelper implements CreationHelper {
    private final Map<String, Workbook> referencedWorkbooks = new HashMap();
    private final XSSFWorkbook workbook;

    @Internal
    public XSSFCreationHelper(XSSFWorkbook xSSFWorkbook) {
        this.workbook = xSSFWorkbook;
    }

    public XSSFRichTextString createRichTextString(String str) {
        XSSFRichTextString xSSFRichTextString = new XSSFRichTextString(str);
        xSSFRichTextString.setStylesTableReference(this.workbook.getStylesSource());
        return xSSFRichTextString;
    }

    public XSSFDataFormat createDataFormat() {
        return this.workbook.createDataFormat();
    }

    public XSSFColor createExtendedColor() {
        return XSSFColor.from(CTColor.Factory.newInstance(), this.workbook.getStylesSource().getIndexedColors());
    }

    public XSSFHyperlink createHyperlink(HyperlinkType hyperlinkType) {
        return new XSSFHyperlink(hyperlinkType);
    }

    public XSSFFormulaEvaluator createFormulaEvaluator() {
        XSSFFormulaEvaluator xSSFFormulaEvaluator = new XSSFFormulaEvaluator(this.workbook);
        HashMap hashMap = new HashMap();
        hashMap.put("", xSSFFormulaEvaluator);
        this.referencedWorkbooks.forEach(new XSSFCreationHelper$$ExternalSyntheticLambda0(hashMap));
        xSSFFormulaEvaluator.setupReferencedWorkbooks(hashMap);
        return xSSFFormulaEvaluator;
    }

    static /* synthetic */ void lambda$createFormulaEvaluator$0(Map map, String str, Workbook workbook2) {
        FormulaEvaluator formulaEvaluator = (FormulaEvaluator) map.put(str, workbook2.getCreationHelper().createFormulaEvaluator());
    }

    public XSSFClientAnchor createClientAnchor() {
        return new XSSFClientAnchor();
    }

    public AreaReference createAreaReference(String str) {
        return new AreaReference(str, this.workbook.getSpreadsheetVersion());
    }

    public AreaReference createAreaReference(CellReference cellReference, CellReference cellReference2) {
        return new AreaReference(cellReference, cellReference2, this.workbook.getSpreadsheetVersion());
    }

    /* access modifiers changed from: protected */
    public Map<String, Workbook> getReferencedWorkbooks() {
        return this.referencedWorkbooks;
    }

    /* access modifiers changed from: protected */
    public void addExternalWorkbook(String str, Workbook workbook2) {
        this.referencedWorkbooks.put(str, workbook2);
    }
}
