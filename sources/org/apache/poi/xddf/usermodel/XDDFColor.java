package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;

public abstract class XDDFColor {
    protected CTColor container;

    /* access modifiers changed from: protected */
    @Internal
    public abstract XmlObject getXmlObject();

    @Internal
    protected XDDFColor(CTColor cTColor) {
        this.container = cTColor;
    }

    public static XDDFColor from(byte[] bArr) {
        return new XDDFColorRgbBinary(bArr);
    }

    public static XDDFColor from(int i, int i2, int i3) {
        return new XDDFColorRgbPercent(i, i2, i3);
    }

    public static XDDFColor from(PresetColor presetColor) {
        return new XDDFColorPreset(presetColor);
    }

    public static XDDFColor from(SchemeColor schemeColor) {
        return new XDDFColorSchemeBased(schemeColor);
    }

    public static XDDFColor from(SystemColor systemColor) {
        return new XDDFColorSystemDefined(systemColor);
    }

    @Internal
    public static XDDFColor forColorContainer(CTColor cTColor) {
        if (cTColor.isSetHslClr()) {
            return new XDDFColorHsl(cTColor.getHslClr(), cTColor);
        }
        if (cTColor.isSetPrstClr()) {
            return new XDDFColorPreset(cTColor.getPrstClr(), cTColor);
        }
        if (cTColor.isSetSchemeClr()) {
            return new XDDFColorSchemeBased(cTColor.getSchemeClr(), cTColor);
        }
        if (cTColor.isSetScrgbClr()) {
            return new XDDFColorRgbPercent(cTColor.getScrgbClr(), cTColor);
        }
        if (cTColor.isSetSrgbClr()) {
            return new XDDFColorRgbBinary(cTColor.getSrgbClr(), cTColor);
        }
        if (cTColor.isSetSysClr()) {
            return new XDDFColorSystemDefined(cTColor.getSysClr(), cTColor);
        }
        return null;
    }

    @Internal
    public CTColor getColorContainer() {
        return this.container;
    }
}
