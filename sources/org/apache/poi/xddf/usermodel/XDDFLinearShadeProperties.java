package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;

public class XDDFLinearShadeProperties {
    private CTLinearShadeProperties props;

    protected XDDFLinearShadeProperties(CTLinearShadeProperties cTLinearShadeProperties) {
        this.props = cTLinearShadeProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLinearShadeProperties getXmlObject() {
        return this.props;
    }

    public Double getAngle() {
        if (this.props.isSetAng()) {
            return Double.valueOf(Angles.attributeToDegrees(this.props.getAng()));
        }
        return null;
    }

    public void setAngle(Double d) {
        if (d == null) {
            if (this.props.isSetAng()) {
                this.props.unsetAng();
            }
        } else if (d.doubleValue() < 0.0d || 360.0d <= d.doubleValue()) {
            throw new IllegalArgumentException("angle must be in the range [0, 360).");
        } else {
            this.props.setAng(Angles.degreesToAttribute(d.doubleValue()));
        }
    }

    public Boolean isScaled() {
        if (this.props.isSetScaled()) {
            return Boolean.valueOf(this.props.getScaled());
        }
        return false;
    }

    public void setScaled(Boolean bool) {
        if (bool != null) {
            this.props.setScaled(bool.booleanValue());
        } else if (this.props.isSetScaled()) {
            this.props.unsetScaled();
        }
    }
}
