package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class XDDFSolidFillProperties implements XDDFFillProperties {
    private CTSolidColorFillProperties props;

    public XDDFSolidFillProperties() {
        this(CTSolidColorFillProperties.Factory.newInstance());
    }

    public XDDFSolidFillProperties(XDDFColor xDDFColor) {
        this(CTSolidColorFillProperties.Factory.newInstance());
        setColor(xDDFColor);
    }

    @Internal
    public XDDFSolidFillProperties(CTSolidColorFillProperties cTSolidColorFillProperties) {
        this.props = cTSolidColorFillProperties;
    }

    @Internal
    public CTSolidColorFillProperties getXmlObject() {
        return this.props;
    }

    public XDDFColor getColor() {
        if (this.props.isSetHslClr()) {
            return new XDDFColorHsl(this.props.getHslClr());
        }
        if (this.props.isSetPrstClr()) {
            return new XDDFColorPreset(this.props.getPrstClr());
        }
        if (this.props.isSetSchemeClr()) {
            return new XDDFColorSchemeBased(this.props.getSchemeClr());
        }
        if (this.props.isSetScrgbClr()) {
            return new XDDFColorRgbPercent(this.props.getScrgbClr());
        }
        if (this.props.isSetSrgbClr()) {
            return new XDDFColorRgbBinary(this.props.getSrgbClr());
        }
        if (this.props.isSetSysClr()) {
            return new XDDFColorSystemDefined(this.props.getSysClr());
        }
        return null;
    }

    public void setColor(XDDFColor xDDFColor) {
        if (this.props.isSetHslClr()) {
            this.props.unsetHslClr();
        }
        if (this.props.isSetPrstClr()) {
            this.props.unsetPrstClr();
        }
        if (this.props.isSetSchemeClr()) {
            this.props.unsetSchemeClr();
        }
        if (this.props.isSetScrgbClr()) {
            this.props.unsetScrgbClr();
        }
        if (this.props.isSetSrgbClr()) {
            this.props.unsetSrgbClr();
        }
        if (this.props.isSetSysClr()) {
            this.props.unsetSysClr();
        }
        if (xDDFColor != null) {
            if (xDDFColor instanceof XDDFColorHsl) {
                this.props.setHslClr((CTHslColor) xDDFColor.getXmlObject());
            } else if (xDDFColor instanceof XDDFColorPreset) {
                this.props.setPrstClr((CTPresetColor) xDDFColor.getXmlObject());
            } else if (xDDFColor instanceof XDDFColorSchemeBased) {
                this.props.setSchemeClr((CTSchemeColor) xDDFColor.getXmlObject());
            } else if (xDDFColor instanceof XDDFColorRgbPercent) {
                this.props.setScrgbClr((CTScRgbColor) xDDFColor.getXmlObject());
            } else if (xDDFColor instanceof XDDFColorRgbBinary) {
                this.props.setSrgbClr((CTSRgbColor) xDDFColor.getXmlObject());
            } else if (xDDFColor instanceof XDDFColorSystemDefined) {
                this.props.setSysClr((CTSystemColor) xDDFColor.getXmlObject());
            }
        }
    }
}
