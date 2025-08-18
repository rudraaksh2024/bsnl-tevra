package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;

public class XDDFTileInfoProperties {
    private CTTileInfoProperties props;

    protected XDDFTileInfoProperties(CTTileInfoProperties cTTileInfoProperties) {
        this.props = cTTileInfoProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTileInfoProperties getXmlObject() {
        return this.props;
    }

    public void setAlignment(RectangleAlignment rectangleAlignment) {
        if (rectangleAlignment != null) {
            this.props.setAlgn(rectangleAlignment.underlying);
        } else if (this.props.isSetAlgn()) {
            this.props.unsetAlgn();
        }
    }

    public TileFlipMode getFlipMode() {
        if (this.props.isSetFlip()) {
            return TileFlipMode.valueOf(this.props.getFlip());
        }
        return null;
    }

    public void setFlipMode(TileFlipMode tileFlipMode) {
        if (tileFlipMode != null) {
            this.props.setFlip(tileFlipMode.underlying);
        } else if (this.props.isSetFlip()) {
            this.props.unsetFlip();
        }
    }

    public Integer getSx() {
        if (this.props.isSetSx()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.props.xgetSx()));
        }
        return null;
    }

    public void setSx(Integer num) {
        if (num != null) {
            this.props.setSx(num);
        } else if (this.props.isSetSx()) {
            this.props.unsetSx();
        }
    }

    public Integer getSy() {
        if (this.props.isSetSy()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.props.xgetSy()));
        }
        return null;
    }

    public void setSy(Integer num) {
        if (num != null) {
            this.props.setSy(num);
        } else if (this.props.isSetSy()) {
            this.props.unsetSy();
        }
    }

    public Long getTx() {
        if (this.props.isSetTx()) {
            return Long.valueOf(POIXMLUnits.parseLength(this.props.xgetTx()));
        }
        return null;
    }

    public void setTx(Long l) {
        if (l != null) {
            this.props.setTx(l);
        } else if (this.props.isSetTx()) {
            this.props.unsetTx();
        }
    }

    public Long getTy() {
        if (this.props.isSetTy()) {
            return Long.valueOf(POIXMLUnits.parseLength(this.props.xgetTy()));
        }
        return null;
    }

    public void setTy(Long l) {
        if (l != null) {
            this.props.setTy(l);
        } else if (this.props.isSetTy()) {
            this.props.unsetTy();
        }
    }
}
