package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;

public class XDDFNormalAutoFit implements XDDFAutoFit {
    private CTTextNormalAutofit autofit;

    public XDDFNormalAutoFit() {
        this(CTTextNormalAutofit.Factory.newInstance());
    }

    @Internal
    protected XDDFNormalAutoFit(CTTextNormalAutofit cTTextNormalAutofit) {
        this.autofit = cTTextNormalAutofit;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextNormalAutofit getXmlObject() {
        return this.autofit;
    }

    public int getFontScale() {
        return this.autofit.isSetFontScale() ? POIXMLUnits.parsePercent(this.autofit.xgetFontScale()) : BZip2Constants.BASEBLOCKSIZE;
    }

    public void setFontScale(Integer num) {
        if (num != null) {
            this.autofit.setFontScale(num);
        } else if (this.autofit.isSetFontScale()) {
            this.autofit.unsetFontScale();
        }
    }

    public int getLineSpaceReduction() {
        if (this.autofit.isSetLnSpcReduction()) {
            return POIXMLUnits.parsePercent(this.autofit.xgetLnSpcReduction());
        }
        return 0;
    }

    public void setLineSpaceReduction(Integer num) {
        if (num != null) {
            this.autofit.setLnSpcReduction(num);
        } else if (this.autofit.isSetLnSpcReduction()) {
            this.autofit.unsetLnSpcReduction();
        }
    }
}
