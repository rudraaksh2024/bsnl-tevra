package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;

public class XDDFGradientFillProperties implements XDDFFillProperties {
    private CTGradientFillProperties props;

    public XDDFGradientFillProperties() {
        this(CTGradientFillProperties.Factory.newInstance());
    }

    protected XDDFGradientFillProperties(CTGradientFillProperties cTGradientFillProperties) {
        this.props = cTGradientFillProperties;
    }

    @Internal
    public CTGradientFillProperties getXmlObject() {
        return this.props;
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

    public TileFlipMode getTileFlipMode() {
        if (this.props.isSetFlip()) {
            return TileFlipMode.valueOf(this.props.getFlip());
        }
        return null;
    }

    public void setTileFlipMode(TileFlipMode tileFlipMode) {
        if (tileFlipMode != null) {
            this.props.setFlip(tileFlipMode.underlying);
        } else if (this.props.isSetFlip()) {
            this.props.unsetFlip();
        }
    }

    public XDDFGradientStop addGradientStop() {
        if (!this.props.isSetGsLst()) {
            this.props.addNewGsLst();
        }
        return new XDDFGradientStop(this.props.getGsLst().addNewGs());
    }

    public XDDFGradientStop insertGradientStop(int i) {
        if (!this.props.isSetGsLst()) {
            this.props.addNewGsLst();
        }
        return new XDDFGradientStop(this.props.getGsLst().insertNewGs(i));
    }

    public void removeGradientStop(int i) {
        if (this.props.isSetGsLst()) {
            this.props.getGsLst().removeGs(i);
        }
    }

    public XDDFGradientStop getGradientStop(int i) {
        if (this.props.isSetGsLst()) {
            return new XDDFGradientStop(this.props.getGsLst().getGsArray(i));
        }
        return null;
    }

    public List<XDDFGradientStop> getGradientStops() {
        if (this.props.isSetGsLst()) {
            return Collections.unmodifiableList((List) this.props.getGsLst().getGsList().stream().map(new XDDFGradientFillProperties$$ExternalSyntheticLambda0()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public int countGradientStops() {
        if (this.props.isSetGsLst()) {
            return this.props.getGsLst().sizeOfGsArray();
        }
        return 0;
    }

    public XDDFLinearShadeProperties getLinearShadeProperties() {
        if (this.props.isSetLin()) {
            return new XDDFLinearShadeProperties(this.props.getLin());
        }
        return null;
    }

    public void setLinearShadeProperties(XDDFLinearShadeProperties xDDFLinearShadeProperties) {
        if (xDDFLinearShadeProperties != null) {
            this.props.setLin(xDDFLinearShadeProperties.getXmlObject());
        } else if (this.props.isSetLin()) {
            this.props.unsetLin();
        }
    }

    public XDDFPathShadeProperties getPathShadeProperties() {
        if (this.props.isSetPath()) {
            return new XDDFPathShadeProperties(this.props.getPath());
        }
        return null;
    }

    public void setPathShadeProperties(XDDFPathShadeProperties xDDFPathShadeProperties) {
        if (xDDFPathShadeProperties != null) {
            this.props.setPath(xDDFPathShadeProperties.getXmlObject());
        } else if (this.props.isSetPath()) {
            this.props.unsetPath();
        }
    }

    public XDDFRelativeRectangle getTileRectangle() {
        if (this.props.isSetTileRect()) {
            return new XDDFRelativeRectangle(this.props.getTileRect());
        }
        return null;
    }

    public void setTileRectangle(XDDFRelativeRectangle xDDFRelativeRectangle) {
        if (xDDFRelativeRectangle != null) {
            this.props.setTileRect(xDDFRelativeRectangle.getXmlObject());
        } else if (this.props.isSetTileRect()) {
            this.props.unsetTileRect();
        }
    }
}
