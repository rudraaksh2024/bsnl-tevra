package org.apache.poi.xssf.model;

import org.apache.poi.xssf.usermodel.XSSFColor;

public interface Themes {
    XSSFColor getThemeColor(int i);

    void inheritFromThemeAsRequired(XSSFColor xSSFColor);
}
