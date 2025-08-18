package org.apache.poi.xssf.model;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;

public interface Styles {
    XSSFCellBorder getBorderAt(int i);

    XSSFCellFill getFillAt(int i);

    XSSFFont getFontAt(int i);

    int getNumCellStyles();

    int getNumDataFormats();

    String getNumberFormatAt(short s);

    XSSFCellStyle getStyleAt(int i);

    int putBorder(XSSFCellBorder xSSFCellBorder);

    int putFill(XSSFCellFill xSSFCellFill);

    int putFont(XSSFFont xSSFFont);

    int putFont(XSSFFont xSSFFont, boolean z);

    int putNumberFormat(String str);

    void putNumberFormat(short s, String str);

    int putStyle(XSSFCellStyle xSSFCellStyle);

    boolean removeNumberFormat(String str);

    boolean removeNumberFormat(short s);
}
