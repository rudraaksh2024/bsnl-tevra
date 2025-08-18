package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

public class XDDFFont {
    private CTTextFont font;
    private FontGroup group;

    public static XDDFFont unsetFontForGroup(FontGroup fontGroup) {
        return new XDDFFont(fontGroup, (CTTextFont) null);
    }

    public XDDFFont(FontGroup fontGroup, String str, Byte b, Byte b2, byte[] bArr) {
        this(fontGroup, CTTextFont.Factory.newInstance());
        if (str != null) {
            this.font.setTypeface(str);
        } else if (this.font.getTypeface() != null && !this.font.getTypeface().equals("")) {
            this.font.setTypeface("");
        }
        if (b != null) {
            this.font.setCharset(b.byteValue());
        } else if (this.font.isSetCharset()) {
            this.font.unsetCharset();
        }
        if (b2 != null) {
            this.font.setPitchFamily(b2.byteValue());
        } else if (this.font.isSetPitchFamily()) {
            this.font.unsetPitchFamily();
        }
        if (bArr != null && bArr.length != 0) {
            this.font.setPanose(bArr);
        } else if (this.font.isSetPanose()) {
            this.font.unsetPanose();
        }
    }

    @Internal
    protected XDDFFont(FontGroup fontGroup, CTTextFont cTTextFont) {
        this.group = fontGroup;
        this.font = cTTextFont;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextFont getXmlObject() {
        return this.font;
    }

    public FontGroup getGroup() {
        return this.group;
    }

    public String getTypeface() {
        return this.font.getTypeface();
    }

    public Byte getCharset() {
        if (this.font.isSetCharset()) {
            return Byte.valueOf(this.font.getCharset());
        }
        return null;
    }

    public Byte getPitchFamily() {
        if (this.font.isSetPitchFamily()) {
            return Byte.valueOf(this.font.getPitchFamily());
        }
        return null;
    }

    public byte[] getPanose() {
        if (this.font.isSetPanose()) {
            return this.font.getPanose();
        }
        return null;
    }
}
