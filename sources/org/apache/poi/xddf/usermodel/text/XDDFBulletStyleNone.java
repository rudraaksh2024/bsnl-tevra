package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoBullet;

public class XDDFBulletStyleNone implements XDDFBulletStyle {
    private CTTextNoBullet style;

    @Internal
    protected XDDFBulletStyleNone(CTTextNoBullet cTTextNoBullet) {
        this.style = cTTextNoBullet;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextNoBullet getXmlObject() {
        return this.style;
    }
}
