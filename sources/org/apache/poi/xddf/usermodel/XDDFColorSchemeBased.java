package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;

public class XDDFColorSchemeBased extends XDDFColor {
    private CTSchemeColor color;

    public XDDFColorSchemeBased(SchemeColor schemeColor) {
        this(CTSchemeColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(schemeColor);
    }

    @Internal
    protected XDDFColorSchemeBased(CTSchemeColor cTSchemeColor) {
        this(cTSchemeColor, (CTColor) null);
    }

    @Internal
    protected XDDFColorSchemeBased(CTSchemeColor cTSchemeColor, CTColor cTColor) {
        super(cTColor);
        this.color = cTSchemeColor;
    }

    /* access modifiers changed from: protected */
    @Internal
    public XmlObject getXmlObject() {
        return this.color;
    }

    public SchemeColor getValue() {
        return SchemeColor.valueOf(this.color.getVal());
    }

    public void setValue(SchemeColor schemeColor) {
        this.color.setVal(schemeColor.underlying);
    }
}
