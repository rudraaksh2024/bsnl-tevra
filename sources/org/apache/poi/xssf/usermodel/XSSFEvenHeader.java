package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFEvenHeader extends XSSFHeaderFooter implements Header {
    protected XSSFEvenHeader(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
        cTHeaderFooter.setDifferentOddEven(true);
    }

    public String getText() {
        return getHeaderFooter().getEvenHeader();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetEvenHeader();
            if (!getHeaderFooter().isSetEvenFooter()) {
                getHeaderFooter().unsetDifferentOddEven();
                return;
            }
            return;
        }
        getHeaderFooter().setEvenHeader(str);
    }
}
