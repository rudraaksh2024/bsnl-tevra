package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;

public class XDDFLineJoinBevelProperties implements XDDFLineJoinProperties {
    private CTLineJoinBevel join;

    public XDDFLineJoinBevelProperties() {
        this(CTLineJoinBevel.Factory.newInstance());
    }

    protected XDDFLineJoinBevelProperties(CTLineJoinBevel cTLineJoinBevel) {
        this.join = cTLineJoinBevel;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLineJoinBevel getXmlObject() {
        return this.join;
    }
}
