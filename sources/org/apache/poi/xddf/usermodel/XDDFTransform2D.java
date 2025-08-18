package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

public class XDDFTransform2D {
    private CTTransform2D transform;

    protected XDDFTransform2D(CTTransform2D cTTransform2D) {
        this.transform = cTTransform2D;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTransform2D getXmlObject() {
        return this.transform;
    }

    public Boolean getFlipHorizontal() {
        if (this.transform.isSetFlipH()) {
            return Boolean.valueOf(this.transform.getFlipH());
        }
        return null;
    }

    public void setFlipHorizontal(Boolean bool) {
        if (bool != null) {
            this.transform.setFlipH(bool.booleanValue());
        } else if (this.transform.isSetFlipH()) {
            this.transform.unsetFlipH();
        }
    }

    public Boolean getFlipVertical() {
        if (this.transform.isSetFlipV()) {
            return Boolean.valueOf(this.transform.getFlipV());
        }
        return null;
    }

    public void setFlipVertical(Boolean bool) {
        if (bool != null) {
            this.transform.setFlipV(bool.booleanValue());
        } else if (this.transform.isSetFlipV()) {
            this.transform.unsetFlipV();
        }
    }

    public XDDFPositiveSize2D getExtension() {
        if (this.transform.isSetExt()) {
            return new XDDFPositiveSize2D(this.transform.getExt());
        }
        return null;
    }

    public void setExtension(XDDFPositiveSize2D xDDFPositiveSize2D) {
        CTPositiveSize2D cTPositiveSize2D;
        if (xDDFPositiveSize2D != null) {
            if (this.transform.isSetExt()) {
                cTPositiveSize2D = this.transform.getExt();
            } else {
                cTPositiveSize2D = this.transform.addNewExt();
            }
            cTPositiveSize2D.setCx(xDDFPositiveSize2D.getX());
            cTPositiveSize2D.setCy(xDDFPositiveSize2D.getY());
        } else if (this.transform.isSetExt()) {
            this.transform.unsetExt();
        }
    }

    public XDDFPoint2D getOffset() {
        if (this.transform.isSetOff()) {
            return new XDDFPoint2D(this.transform.getOff());
        }
        return null;
    }

    public void setOffset(XDDFPoint2D xDDFPoint2D) {
        CTPoint2D cTPoint2D;
        if (xDDFPoint2D != null) {
            if (this.transform.isSetOff()) {
                cTPoint2D = this.transform.getOff();
            } else {
                cTPoint2D = this.transform.addNewOff();
            }
            cTPoint2D.setX(Long.valueOf(xDDFPoint2D.getX()));
            cTPoint2D.setY(Long.valueOf(xDDFPoint2D.getY()));
        } else if (this.transform.isSetOff()) {
            this.transform.unsetOff();
        }
    }

    public Double getRotation() {
        if (this.transform.isSetRot()) {
            return Double.valueOf(Angles.attributeToDegrees(this.transform.getRot()));
        }
        return null;
    }

    public void setRotation(Double d) {
        if (d != null) {
            this.transform.setRot(Angles.degreesToAttribute(d.doubleValue()));
        } else if (this.transform.isSetRot()) {
            this.transform.unsetRot();
        }
    }
}
