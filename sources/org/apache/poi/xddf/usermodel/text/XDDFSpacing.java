package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

public abstract class XDDFSpacing {
    protected CTTextSpacing spacing;

    public enum Kind {
        PERCENT,
        POINTS
    }

    public abstract Kind getType();

    @Internal
    protected XDDFSpacing(CTTextSpacing cTTextSpacing) {
        this.spacing = cTTextSpacing;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextSpacing getXmlObject() {
        return this.spacing;
    }
}
