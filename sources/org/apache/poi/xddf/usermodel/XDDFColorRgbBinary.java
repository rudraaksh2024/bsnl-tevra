package org.apache.poi.xddf.usermodel;

import java.util.Locale;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;

public class XDDFColorRgbBinary extends XDDFColor {
    private CTSRgbColor color;

    public XDDFColorRgbBinary(byte[] bArr) {
        this(CTSRgbColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(bArr);
    }

    @Internal
    protected XDDFColorRgbBinary(CTSRgbColor cTSRgbColor) {
        this(cTSRgbColor, (CTColor) null);
    }

    @Internal
    protected XDDFColorRgbBinary(CTSRgbColor cTSRgbColor, CTColor cTColor) {
        super(cTColor);
        this.color = cTSRgbColor;
    }

    /* access modifiers changed from: protected */
    @Internal
    public XmlObject getXmlObject() {
        return this.color;
    }

    public byte[] getValue() {
        return this.color.getVal();
    }

    public void setValue(byte[] bArr) {
        this.color.setVal(bArr);
    }

    public String toRGBHex() {
        StringBuilder sb = new StringBuilder(6);
        for (byte valueOf : this.color.getVal()) {
            sb.append(String.format(Locale.ROOT, "%02X", new Object[]{Byte.valueOf(valueOf)}));
        }
        return sb.toString().toUpperCase(Locale.ROOT);
    }
}
