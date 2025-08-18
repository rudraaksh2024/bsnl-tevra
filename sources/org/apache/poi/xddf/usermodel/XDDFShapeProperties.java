package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class XDDFShapeProperties {
    private CTShapeProperties props;

    public XDDFShapeProperties() {
        this(CTShapeProperties.Factory.newInstance());
    }

    @Internal
    public XDDFShapeProperties(CTShapeProperties cTShapeProperties) {
        this.props = cTShapeProperties;
    }

    @Internal
    public CTShapeProperties getXmlObject() {
        return this.props;
    }

    public BlackWhiteMode getBlackWhiteMode() {
        if (this.props.isSetBwMode()) {
            return BlackWhiteMode.valueOf(this.props.getBwMode());
        }
        return null;
    }

    public void setBlackWhiteMode(BlackWhiteMode blackWhiteMode) {
        if (blackWhiteMode != null) {
            this.props.setBwMode(blackWhiteMode.underlying);
        } else if (this.props.isSetBwMode()) {
            this.props.unsetBwMode();
        }
    }

    public XDDFFillProperties getFillProperties() {
        if (this.props.isSetGradFill()) {
            return new XDDFGradientFillProperties(this.props.getGradFill());
        }
        if (this.props.isSetGrpFill()) {
            return new XDDFGroupFillProperties(this.props.getGrpFill());
        }
        if (this.props.isSetNoFill()) {
            return new XDDFNoFillProperties(this.props.getNoFill());
        }
        if (this.props.isSetPattFill()) {
            return new XDDFPatternFillProperties(this.props.getPattFill());
        }
        if (this.props.isSetBlipFill()) {
            return new XDDFPictureFillProperties(this.props.getBlipFill());
        }
        if (this.props.isSetSolidFill()) {
            return new XDDFSolidFillProperties(this.props.getSolidFill());
        }
        return null;
    }

    public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
        if (this.props.isSetBlipFill()) {
            this.props.unsetBlipFill();
        }
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetGrpFill()) {
            this.props.unsetGrpFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (xDDFFillProperties != null) {
            if (xDDFFillProperties instanceof XDDFGradientFillProperties) {
                this.props.setGradFill(((XDDFGradientFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFGroupFillProperties) {
                this.props.setGrpFill(((XDDFGroupFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFNoFillProperties) {
                this.props.setNoFill(((XDDFNoFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFPatternFillProperties) {
                this.props.setPattFill(((XDDFPatternFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFPictureFillProperties) {
                this.props.setBlipFill(((XDDFPictureFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFSolidFillProperties) {
                this.props.setSolidFill(((XDDFSolidFillProperties) xDDFFillProperties).getXmlObject());
            }
        }
    }

    public XDDFLineProperties getLineProperties() {
        if (this.props.isSetLn()) {
            return new XDDFLineProperties(this.props.getLn());
        }
        return null;
    }

    public void setLineProperties(XDDFLineProperties xDDFLineProperties) {
        if (xDDFLineProperties != null) {
            this.props.setLn(xDDFLineProperties.getXmlObject());
        } else if (this.props.isSetLn()) {
            this.props.unsetLn();
        }
    }

    public XDDFCustomGeometry2D getCustomGeometry2D() {
        if (this.props.isSetCustGeom()) {
            return new XDDFCustomGeometry2D(this.props.getCustGeom());
        }
        return null;
    }

    public void setCustomGeometry2D(XDDFCustomGeometry2D xDDFCustomGeometry2D) {
        if (xDDFCustomGeometry2D != null) {
            this.props.setCustGeom(xDDFCustomGeometry2D.getXmlObject());
        } else if (this.props.isSetCustGeom()) {
            this.props.unsetCustGeom();
        }
    }

    public XDDFPresetGeometry2D getPresetGeometry2D() {
        if (this.props.isSetPrstGeom()) {
            return new XDDFPresetGeometry2D(this.props.getPrstGeom());
        }
        return null;
    }

    public void setPresetGeometry2D(XDDFPresetGeometry2D xDDFPresetGeometry2D) {
        if (xDDFPresetGeometry2D != null) {
            this.props.setPrstGeom(xDDFPresetGeometry2D.getXmlObject());
        } else if (this.props.isSetPrstGeom()) {
            this.props.unsetPrstGeom();
        }
    }

    public XDDFEffectContainer getEffectContainer() {
        if (this.props.isSetEffectDag()) {
            return new XDDFEffectContainer(this.props.getEffectDag());
        }
        return null;
    }

    public void setEffectContainer(XDDFEffectContainer xDDFEffectContainer) {
        if (xDDFEffectContainer != null) {
            this.props.setEffectDag(xDDFEffectContainer.getXmlObject());
        } else if (this.props.isSetEffectDag()) {
            this.props.unsetEffectDag();
        }
    }

    public XDDFEffectList getEffectList() {
        if (this.props.isSetEffectLst()) {
            return new XDDFEffectList(this.props.getEffectLst());
        }
        return null;
    }

    public void setEffectList(XDDFEffectList xDDFEffectList) {
        if (xDDFEffectList != null) {
            this.props.setEffectLst(xDDFEffectList.getXmlObject());
        } else if (this.props.isSetEffectLst()) {
            this.props.unsetEffectLst();
        }
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList xDDFExtensionList) {
        if (xDDFExtensionList != null) {
            this.props.setExtLst(xDDFExtensionList.getXmlObject());
        } else if (this.props.isSetExtLst()) {
            this.props.unsetExtLst();
        }
    }

    public XDDFScene3D getScene3D() {
        if (this.props.isSetScene3D()) {
            return new XDDFScene3D(this.props.getScene3D());
        }
        return null;
    }

    public void setScene3D(XDDFScene3D xDDFScene3D) {
        if (xDDFScene3D != null) {
            this.props.setScene3D(xDDFScene3D.getXmlObject());
        } else if (this.props.isSetScene3D()) {
            this.props.unsetScene3D();
        }
    }

    public XDDFShape3D getShape3D() {
        if (this.props.isSetSp3D()) {
            return new XDDFShape3D(this.props.getSp3D());
        }
        return null;
    }

    public void setShape3D(XDDFShape3D xDDFShape3D) {
        if (xDDFShape3D != null) {
            this.props.setSp3D(xDDFShape3D.getXmlObject());
        } else if (this.props.isSetSp3D()) {
            this.props.unsetSp3D();
        }
    }

    public XDDFTransform2D getTransform2D() {
        if (this.props.isSetXfrm()) {
            return new XDDFTransform2D(this.props.getXfrm());
        }
        return null;
    }

    public void setTransform2D(XDDFTransform2D xDDFTransform2D) {
        if (xDDFTransform2D != null) {
            this.props.setXfrm(xDDFTransform2D.getXmlObject());
        } else if (this.props.isSetXfrm()) {
            this.props.unsetXfrm();
        }
    }
}
