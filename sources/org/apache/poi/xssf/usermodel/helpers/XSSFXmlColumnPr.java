package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;

public class XSSFXmlColumnPr {
    private CTXmlColumnPr ctXmlColumnPr;
    private XSSFTable table;
    private XSSFTableColumn tableColumn;

    @Internal
    public XSSFXmlColumnPr(XSSFTableColumn xSSFTableColumn, CTXmlColumnPr cTXmlColumnPr) {
        this.table = xSSFTableColumn.getTable();
        this.tableColumn = xSSFTableColumn;
        this.ctXmlColumnPr = cTXmlColumnPr;
    }

    public XSSFTableColumn getTableColumn() {
        return this.tableColumn;
    }

    public long getMapId() {
        return this.ctXmlColumnPr.getMapId();
    }

    public String getXPath() {
        return this.ctXmlColumnPr.getXpath();
    }

    public String getLocalXPath() {
        StringBuilder sb = new StringBuilder();
        String[] split = this.ctXmlColumnPr.getXpath().split(PackagingURIHelper.FORWARD_SLASH_STRING);
        for (int length = this.table.getCommonXpath().split(PackagingURIHelper.FORWARD_SLASH_STRING).length - 1; length < split.length; length++) {
            sb.append(PackagingURIHelper.FORWARD_SLASH_STRING + split[length]);
        }
        return sb.toString();
    }

    public String getXmlDataType() {
        return this.ctXmlColumnPr.getXmlDataType();
    }
}
