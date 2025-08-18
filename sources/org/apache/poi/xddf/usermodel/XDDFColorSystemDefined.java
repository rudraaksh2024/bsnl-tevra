package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class XDDFColorSystemDefined extends XDDFColor {
    private CTSystemColor color;

    public XDDFColorSystemDefined(SystemColor systemColor) {
        this(CTSystemColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(systemColor);
    }

    @Internal
    protected XDDFColorSystemDefined(CTSystemColor cTSystemColor) {
        this(cTSystemColor, (CTColor) null);
    }

    @Internal
    protected XDDFColorSystemDefined(CTSystemColor cTSystemColor, CTColor cTColor) {
        super(cTColor);
        this.color = cTSystemColor;
    }

    /* access modifiers changed from: protected */
    @Internal
    public XmlObject getXmlObject() {
        return this.color;
    }

    public SystemColor getValue() {
        return SystemColor.valueOf(this.color.getVal());
    }

    public void setValue(SystemColor systemColor) {
        this.color.setVal(systemColor.underlying);
    }

    public byte[] getLastColor() {
        if (this.color.isSetLastClr()) {
            return this.color.getLastClr();
        }
        return null;
    }

    public void setLastColor(byte[] bArr) {
        if (bArr != null) {
            this.color.setLastClr(bArr);
        } else if (this.color.isSetLastClr()) {
            this.color.unsetLastClr();
        }
    }
}
