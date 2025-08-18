package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFPicture;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;

public class XDDFBulletStylePicture implements XDDFBulletStyle {
    private CTTextBlipBullet style;

    @Internal
    protected XDDFBulletStylePicture(CTTextBlipBullet cTTextBlipBullet) {
        this.style = cTTextBlipBullet;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextBlipBullet getXmlObject() {
        return this.style;
    }

    public XDDFPicture getPicture() {
        return new XDDFPicture(this.style.getBlip());
    }

    public void setPicture(XDDFPicture xDDFPicture) {
        if (xDDFPicture != null) {
            this.style.setBlip(xDDFPicture.getXmlObject());
        }
    }
}
