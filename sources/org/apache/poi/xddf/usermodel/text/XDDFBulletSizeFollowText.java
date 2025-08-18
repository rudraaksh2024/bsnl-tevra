package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;

public class XDDFBulletSizeFollowText implements XDDFBulletSize {
    private CTTextBulletSizeFollowText follow;

    public XDDFBulletSizeFollowText() {
        this(CTTextBulletSizeFollowText.Factory.newInstance());
    }

    @Internal
    protected XDDFBulletSizeFollowText(CTTextBulletSizeFollowText cTTextBulletSizeFollowText) {
        this.follow = cTTextBulletSizeFollowText;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizeFollowText getXmlObject() {
        return this.follow;
    }
}
