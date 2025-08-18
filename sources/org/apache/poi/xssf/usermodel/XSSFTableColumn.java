package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;

public class XSSFTableColumn {
    private final CTTableColumn ctTableColumn;
    private final XSSFTable table;
    private XSSFXmlColumnPr xmlColumnPr;

    @Internal
    protected XSSFTableColumn(XSSFTable xSSFTable, CTTableColumn cTTableColumn) {
        this.table = xSSFTable;
        this.ctTableColumn = cTTableColumn;
    }

    public XSSFTable getTable() {
        return this.table;
    }

    public long getId() {
        return this.ctTableColumn.getId();
    }

    public void setId(long j) {
        this.ctTableColumn.setId(j);
    }

    public String getName() {
        return this.ctTableColumn.getName();
    }

    public void setName(String str) {
        this.ctTableColumn.setName(str);
    }

    public XSSFXmlColumnPr getXmlColumnPr() {
        CTXmlColumnPr xmlColumnPr2;
        if (this.xmlColumnPr == null && (xmlColumnPr2 = this.ctTableColumn.getXmlColumnPr()) != null) {
            this.xmlColumnPr = new XSSFXmlColumnPr(this, xmlColumnPr2);
        }
        return this.xmlColumnPr;
    }

    public int getColumnIndex() {
        return this.table.findColumnIndex(getName());
    }
}
