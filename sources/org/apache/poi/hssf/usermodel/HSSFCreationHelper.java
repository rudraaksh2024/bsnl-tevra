package org.apache.poi.hssf.usermodel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

public class HSSFCreationHelper implements CreationHelper {
    private final HSSFWorkbook workbook;

    @Internal(since = "3.15 beta 3")
    HSSFCreationHelper(HSSFWorkbook hSSFWorkbook) {
        this.workbook = hSSFWorkbook;
    }

    public HSSFRichTextString createRichTextString(String str) {
        return new HSSFRichTextString(str);
    }

    public HSSFDataFormat createDataFormat() {
        return this.workbook.createDataFormat();
    }

    public HSSFHyperlink createHyperlink(HyperlinkType hyperlinkType) {
        return new HSSFHyperlink(hyperlinkType);
    }

    public HSSFExtendedColor createExtendedColor() {
        return new HSSFExtendedColor(new ExtendedColor());
    }

    public HSSFFormulaEvaluator createFormulaEvaluator() {
        return new HSSFFormulaEvaluator(this.workbook);
    }

    public HSSFClientAnchor createClientAnchor() {
        return new HSSFClientAnchor();
    }

    public AreaReference createAreaReference(String str) {
        return new AreaReference(str, this.workbook.getSpreadsheetVersion());
    }

    public AreaReference createAreaReference(CellReference cellReference, CellReference cellReference2) {
        return new AreaReference(cellReference, cellReference2, this.workbook.getSpreadsheetVersion());
    }
}
