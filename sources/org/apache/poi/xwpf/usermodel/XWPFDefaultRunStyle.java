package org.apache.poi.xwpf.usermodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Removal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

public class XWPFDefaultRunStyle {
    private CTRPr rpr;

    public XWPFDefaultRunStyle(CTRPr cTRPr) {
        this.rpr = cTRPr;
    }

    /* access modifiers changed from: protected */
    public CTRPr getRPr() {
        return this.rpr;
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getFontSize() {
        BigDecimal fontSizeAsBigDecimal = getFontSizeAsBigDecimal(0);
        if (fontSizeAsBigDecimal == null) {
            return -1;
        }
        return fontSizeAsBigDecimal.intValue();
    }

    public Double getFontSizeAsDouble() {
        BigDecimal fontSizeAsBigDecimal = getFontSizeAsBigDecimal(1);
        if (fontSizeAsBigDecimal == null) {
            return null;
        }
        return Double.valueOf(fontSizeAsBigDecimal.doubleValue());
    }

    private BigDecimal getFontSizeAsBigDecimal(int i) {
        CTRPr cTRPr = this.rpr;
        if (cTRPr == null || cTRPr.sizeOfSzArray() <= 0) {
            return null;
        }
        return BigDecimal.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.rpr.getSzArray(0).xgetVal()))).divide(BigDecimal.valueOf(4), i, RoundingMode.HALF_UP);
    }
}
