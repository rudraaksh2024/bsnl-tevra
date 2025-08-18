package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFFirstFooter extends XSSFHeaderFooter implements Footer {
    protected XSSFFirstFooter(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
        cTHeaderFooter.setDifferentFirst(true);
    }

    public String getText() {
        return getHeaderFooter().getFirstFooter();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetFirstFooter();
            if (!getHeaderFooter().isSetFirstHeader()) {
                getHeaderFooter().unsetDifferentFirst();
                return;
            }
            return;
        }
        getHeaderFooter().setFirstFooter(str);
    }
}
