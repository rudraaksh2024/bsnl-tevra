package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.model.StylesTable;

public class XSSFDataFormat implements DataFormat {
    private final StylesTable stylesSource;

    protected XSSFDataFormat(StylesTable stylesTable) {
        this.stylesSource = stylesTable;
    }

    public short getFormat(String str) {
        int builtinFormat = BuiltinFormats.getBuiltinFormat(str);
        if (builtinFormat == -1) {
            builtinFormat = this.stylesSource.putNumberFormat(str);
        }
        return (short) builtinFormat;
    }

    public String getFormat(short s) {
        String numberFormatAt = this.stylesSource.getNumberFormatAt(s);
        return numberFormatAt == null ? BuiltinFormats.getBuiltinFormat((int) s) : numberFormatAt;
    }

    public void putFormat(short s, String str) {
        this.stylesSource.putNumberFormat(s, str);
    }
}
