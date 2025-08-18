package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoAutofit;

public class XDDFNoAutoFit implements XDDFAutoFit {
    private CTTextNoAutofit autofit;

    public int getFontScale() {
        return BZip2Constants.BASEBLOCKSIZE;
    }

    public int getLineSpaceReduction() {
        return 0;
    }

    public XDDFNoAutoFit() {
        this(CTTextNoAutofit.Factory.newInstance());
    }

    @Internal
    protected XDDFNoAutoFit(CTTextNoAutofit cTTextNoAutofit) {
        this.autofit = cTTextNoAutofit;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextNoAutofit getXmlObject() {
        return this.autofit;
    }
}
