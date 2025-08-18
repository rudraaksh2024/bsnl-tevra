package org.apache.poi.xssf.streaming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class SXSSFCreationHelper implements CreationHelper {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SXSSFCreationHelper.class);
    private final XSSFCreationHelper helper;
    private final SXSSFWorkbook wb;

    @Internal
    public SXSSFCreationHelper(SXSSFWorkbook sXSSFWorkbook) {
        this.helper = new XSSFCreationHelper(sXSSFWorkbook.getXSSFWorkbook());
        this.wb = sXSSFWorkbook;
    }

    public XSSFRichTextString createRichTextString(String str) {
        return new XSSFRichTextString(str);
    }

    public SXSSFFormulaEvaluator createFormulaEvaluator() {
        return new SXSSFFormulaEvaluator(this.wb);
    }

    public DataFormat createDataFormat() {
        return this.helper.createDataFormat();
    }

    public Hyperlink createHyperlink(HyperlinkType hyperlinkType) {
        return this.helper.createHyperlink(hyperlinkType);
    }

    public ExtendedColor createExtendedColor() {
        return this.helper.createExtendedColor();
    }

    public ClientAnchor createClientAnchor() {
        return this.helper.createClientAnchor();
    }

    public AreaReference createAreaReference(String str) {
        return new AreaReference(str, this.wb.getSpreadsheetVersion());
    }

    public AreaReference createAreaReference(CellReference cellReference, CellReference cellReference2) {
        return new AreaReference(cellReference, cellReference2, this.wb.getSpreadsheetVersion());
    }
}
