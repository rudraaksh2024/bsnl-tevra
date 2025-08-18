package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;

public class XDDFPathShadeProperties {
    private CTPathShadeProperties props;

    public XDDFPathShadeProperties() {
        this(CTPathShadeProperties.Factory.newInstance());
    }

    protected XDDFPathShadeProperties(CTPathShadeProperties cTPathShadeProperties) {
        this.props = cTPathShadeProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTPathShadeProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillToRectangle() {
        if (this.props.isSetFillToRect()) {
            return new XDDFRelativeRectangle(this.props.getFillToRect());
        }
        return null;
    }

    public void setFillToRectangle(XDDFRelativeRectangle xDDFRelativeRectangle) {
        if (xDDFRelativeRectangle != null) {
            this.props.setFillToRect(xDDFRelativeRectangle.getXmlObject());
        } else if (this.props.isSetFillToRect()) {
            this.props.unsetFillToRect();
        }
    }

    public PathShadeType getPathShadeType() {
        if (this.props.isSetPath()) {
            return PathShadeType.valueOf(this.props.getPath());
        }
        return null;
    }

    public void setPathShadeType(PathShadeType pathShadeType) {
        if (pathShadeType != null) {
            this.props.setPath(pathShadeType.underlying);
        } else if (this.props.isSetPath()) {
            this.props.unsetPath();
        }
    }
}
