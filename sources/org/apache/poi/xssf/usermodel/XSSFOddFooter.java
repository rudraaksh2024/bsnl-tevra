package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFOddFooter extends XSSFHeaderFooter implements Footer {
    protected XSSFOddFooter(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
    }

    public String getText() {
        return getHeaderFooter().getOddFooter();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetOddFooter();
        } else {
            getHeaderFooter().setOddFooter(str);
        }
    }
}
