package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrGeneral;

public class XWPFDefaultParagraphStyle {
    private final CTPPrGeneral ppr;

    public XWPFDefaultParagraphStyle(CTPPrGeneral cTPPrGeneral) {
        this.ppr = cTPPrGeneral;
    }

    /* access modifiers changed from: protected */
    public CTPPrGeneral getPPr() {
        return this.ppr;
    }

    public int getSpacingAfter() {
        if (this.ppr.isSetSpacing()) {
            return (int) Units.toDXA(POIXMLUnits.parseLength(this.ppr.getSpacing().xgetAfter()));
        }
        return -1;
    }
}
