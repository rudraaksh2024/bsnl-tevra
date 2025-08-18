package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;

public class XDDFBulletStyleAutoNumbered implements XDDFBulletStyle {
    private CTTextAutonumberBullet style;

    @Internal
    protected XDDFBulletStyleAutoNumbered(CTTextAutonumberBullet cTTextAutonumberBullet) {
        this.style = cTTextAutonumberBullet;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextAutonumberBullet getXmlObject() {
        return this.style;
    }

    public AutonumberScheme getType() {
        return AutonumberScheme.valueOf(this.style.getType());
    }

    public void setType(AutonumberScheme autonumberScheme) {
        this.style.setType(autonumberScheme.underlying);
    }

    public int getStartAt() {
        if (this.style.isSetStartAt()) {
            return this.style.getStartAt();
        }
        return 1;
    }

    public void setStartAt(Integer num) {
        if (num != null) {
            this.style.setStartAt(num.intValue());
        } else if (this.style.isSetStartAt()) {
            this.style.unsetStartAt();
        }
    }
}
