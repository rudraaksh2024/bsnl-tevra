package org.apache.poi.xssf.usermodel;

public enum XSSFWorkbookType {
    XLSX(XSSFRelation.WORKBOOK.getContentType(), "xlsx"),
    XLSM(XSSFRelation.MACROS_WORKBOOK.getContentType(), "xlsm");
    
    private final String _contentType;
    private final String _extension;

    private XSSFWorkbookType(String str, String str2) {
        this._contentType = str;
        this._extension = str2;
    }

    public String getContentType() {
        return this._contentType;
    }

    public String getExtension() {
        return this._extension;
    }
}
