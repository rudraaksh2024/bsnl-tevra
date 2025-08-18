package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;

public class XDDFLineJoinRoundProperties implements XDDFLineJoinProperties {
    private CTLineJoinRound join;

    public XDDFLineJoinRoundProperties() {
        this(CTLineJoinRound.Factory.newInstance());
    }

    protected XDDFLineJoinRoundProperties(CTLineJoinRound cTLineJoinRound) {
        this.join = cTLineJoinRound;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLineJoinRound getXmlObject() {
        return this.join;
    }
}
