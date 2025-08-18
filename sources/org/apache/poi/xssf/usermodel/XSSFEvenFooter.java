package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFEvenFooter extends XSSFHeaderFooter implements Footer {
    protected XSSFEvenFooter(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
        cTHeaderFooter.setDifferentOddEven(true);
    }

    public String getText() {
        return getHeaderFooter().getEvenFooter();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetEvenFooter();
            if (!getHeaderFooter().isSetEvenHeader()) {
                getHeaderFooter().unsetDifferentOddEven();
                return;
            }
            return;
        }
        getHeaderFooter().setEvenFooter(str);
    }
}
