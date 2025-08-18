package org.apache.poi.hssf.usermodel;

import org.apache.poi.ss.usermodel.AutoFilter;

public final class HSSFAutoFilter implements AutoFilter {
    private HSSFSheet _sheet;

    HSSFAutoFilter(HSSFSheet hSSFSheet) {
        this._sheet = hSSFSheet;
    }
}
