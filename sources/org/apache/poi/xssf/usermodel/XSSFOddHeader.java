package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFOddHeader extends XSSFHeaderFooter implements Header {
    protected XSSFOddHeader(CTHeaderFooter cTHeaderFooter) {
        super(cTHeaderFooter);
    }

    public String getText() {
        return getHeaderFooter().getOddHeader();
    }

    public void setText(String str) {
        if (str == null) {
            getHeaderFooter().unsetOddHeader();
        } else {
            getHeaderFooter().setOddHeader(str);
        }
    }
}
