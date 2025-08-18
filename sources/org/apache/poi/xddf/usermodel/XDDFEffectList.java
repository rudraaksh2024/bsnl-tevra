package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;

public class XDDFEffectList {
    private CTEffectList list;

    @Internal
    public XDDFEffectList(CTEffectList cTEffectList) {
        this.list = cTEffectList;
    }

    @Internal
    public CTEffectList getXmlObject() {
        return this.list;
    }
}
