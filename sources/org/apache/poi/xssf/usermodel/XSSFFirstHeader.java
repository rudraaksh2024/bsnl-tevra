package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFFirstHeader extends XSSFHeaderFooter implements Header {
    protected XSSFFirstHeader(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
        cTHeaderFooter.setDifferentFirst(true);
    }

    public String getText() {
        return getHeaderFooter().getFirstHeader();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetFirstHeader();
            if (!getHeaderFooter().isSetFirstFooter()) {
                getHeaderFooter().unsetDifferentFirst();
                return;
            }
            return;
        }
        getHeaderFooter().setFirstHeader(str);
    }
}
