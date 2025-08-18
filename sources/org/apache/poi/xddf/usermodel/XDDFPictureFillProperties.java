package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;

public class XDDFPictureFillProperties implements XDDFFillProperties {
    private CTBlipFillProperties props;

    public XDDFPictureFillProperties() {
        this(CTBlipFillProperties.Factory.newInstance());
    }

    protected XDDFPictureFillProperties(CTBlipFillProperties cTBlipFillProperties) {
        this.props = cTBlipFillProperties;
    }

    @Internal
    public CTBlipFillProperties getXmlObject() {
        return this.props;
    }

    public XDDFPicture getPicture() {
        if (this.props.isSetBlip()) {
            return new XDDFPicture(this.props.getBlip());
        }
        return null;
    }

    public void setPicture(XDDFPicture xDDFPicture) {
        if (xDDFPicture == null) {
            this.props.unsetBlip();
        } else {
            this.props.setBlip(xDDFPicture.getXmlObject());
        }
    }

    public Boolean isRotatingWithShape() {
        if (this.props.isSetRotWithShape()) {
            return Boolean.valueOf(this.props.getRotWithShape());
        }
        return false;
    }

    public void setRotatingWithShape(Boolean bool) {
        if (bool != null) {
            this.props.setRotWithShape(bool.booleanValue());
        } else if (this.props.isSetRotWithShape()) {
            this.props.unsetRotWithShape();
        }
    }

    public Long getDpi() {
        if (this.props.isSetDpi()) {
            return Long.valueOf(this.props.getDpi());
        }
        return null;
    }

    public void setDpi(Long l) {
        if (l != null) {
            this.props.setDpi(l.longValue());
        } else if (this.props.isSetDpi()) {
            this.props.unsetDpi();
        }
    }

    public XDDFRelativeRectangle getSourceRectangle() {
        if (this.props.isSetSrcRect()) {
            return new XDDFRelativeRectangle(this.props.getSrcRect());
        }
        return null;
    }

    public void setSourceRectangle(XDDFRelativeRectangle xDDFRelativeRectangle) {
        if (xDDFRelativeRectangle != null) {
            this.props.setSrcRect(xDDFRelativeRectangle.getXmlObject());
        } else if (this.props.isSetSrcRect()) {
            this.props.unsetSrcRect();
        }
    }

    public XDDFStretchInfoProperties getStetchInfoProperties() {
        if (this.props.isSetStretch()) {
            return new XDDFStretchInfoProperties(this.props.getStretch());
        }
        return null;
    }

    public void setStretchInfoProperties(XDDFStretchInfoProperties xDDFStretchInfoProperties) {
        if (xDDFStretchInfoProperties != null) {
            this.props.setStretch(xDDFStretchInfoProperties.getXmlObject());
        } else if (this.props.isSetStretch()) {
            this.props.unsetStretch();
        }
    }

    public XDDFTileInfoProperties getTileInfoProperties() {
        if (this.props.isSetTile()) {
            return new XDDFTileInfoProperties(this.props.getTile());
        }
        return null;
    }

    public void setTileInfoProperties(XDDFTileInfoProperties xDDFTileInfoProperties) {
        if (xDDFTileInfoProperties != null) {
            this.props.setTile(xDDFTileInfoProperties.getXmlObject());
        } else if (this.props.isSetTile()) {
            this.props.unsetTile();
        }
    }
}
