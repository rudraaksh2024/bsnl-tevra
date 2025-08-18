package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;

public class XDDFPresetLineDash {
    private CTPresetLineDashProperties props;

    public XDDFPresetLineDash(PresetLineDash presetLineDash) {
        this(CTPresetLineDashProperties.Factory.newInstance());
        setValue(presetLineDash);
    }

    protected XDDFPresetLineDash(CTPresetLineDashProperties cTPresetLineDashProperties) {
        this.props = cTPresetLineDashProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTPresetLineDashProperties getXmlObject() {
        return this.props;
    }

    public PresetLineDash getValue() {
        if (this.props.isSetVal()) {
            return PresetLineDash.valueOf(this.props.getVal());
        }
        return null;
    }

    public void setValue(PresetLineDash presetLineDash) {
        if (presetLineDash != null) {
            this.props.setVal(presetLineDash.underlying);
        } else if (this.props.isSetVal()) {
            this.props.unsetVal();
        }
    }
}
