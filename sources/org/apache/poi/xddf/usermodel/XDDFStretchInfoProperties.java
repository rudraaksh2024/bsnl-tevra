package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;

public class XDDFStretchInfoProperties {
    private CTStretchInfoProperties props;

    protected XDDFStretchInfoProperties(CTStretchInfoProperties cTStretchInfoProperties) {
        this.props = cTStretchInfoProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTStretchInfoProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillRectangle() {
        if (this.props.isSetFillRect()) {
            return new XDDFRelativeRectangle(this.props.getFillRect());
        }
        return null;
    }

    public void setFillRectangle(XDDFRelativeRectangle xDDFRelativeRectangle) {
        if (xDDFRelativeRectangle != null) {
            this.props.setFillRect(xDDFRelativeRectangle.getXmlObject());
        } else if (this.props.isSetFillRect()) {
            this.props.unsetFillRect();
        }
    }
}
