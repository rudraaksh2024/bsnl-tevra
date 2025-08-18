package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextShapeAutofit;

public class XDDFShapeAutoFit implements XDDFAutoFit {
    private CTTextShapeAutofit autofit;

    public int getFontScale() {
        return BZip2Constants.BASEBLOCKSIZE;
    }

    public int getLineSpaceReduction() {
        return 0;
    }

    public XDDFShapeAutoFit() {
        this(CTTextShapeAutofit.Factory.newInstance());
    }

    @Internal
    protected XDDFShapeAutoFit(CTTextShapeAutofit cTTextShapeAutofit) {
        this.autofit = cTTextShapeAutofit;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextShapeAutofit getXmlObject() {
        return this.autofit;
    }
}
