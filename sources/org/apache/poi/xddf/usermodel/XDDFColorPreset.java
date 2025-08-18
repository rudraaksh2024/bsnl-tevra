package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;

public class XDDFColorPreset extends XDDFColor {
    private CTPresetColor color;

    public XDDFColorPreset(PresetColor presetColor) {
        this(CTPresetColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(presetColor);
    }

    @Internal
    protected XDDFColorPreset(CTPresetColor cTPresetColor) {
        this(cTPresetColor, (CTColor) null);
    }

    @Internal
    protected XDDFColorPreset(CTPresetColor cTPresetColor, CTColor cTColor) {
        super(cTColor);
        this.color = cTPresetColor;
    }

    /* access modifiers changed from: protected */
    @Internal
    public XmlObject getXmlObject() {
        return this.color;
    }

    public PresetColor getValue() {
        if (this.color.xgetVal() != null) {
            return PresetColor.valueOf(this.color.getVal());
        }
        return null;
    }

    public void setValue(PresetColor presetColor) {
        if (presetColor != null) {
            this.color.setVal(presetColor.underlying);
        } else if (this.color.xgetVal() != null) {
            this.color.setVal(PresetColor.WHITE.underlying);
        }
    }
}
