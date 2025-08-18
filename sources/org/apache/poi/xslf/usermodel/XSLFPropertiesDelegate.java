package org.apache.poi.xslf.usermodel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;

@Internal
class XSLFPropertiesDelegate {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFPropertiesDelegate.class);

    public interface XSLFEffectProperties {
        CTEffectContainer addNewEffectDag();

        CTEffectList addNewEffectLst();

        CTEffectContainer getEffectDag();

        CTEffectList getEffectLst();

        boolean isSetEffectDag();

        boolean isSetEffectLst();

        void setEffectDag(CTEffectContainer cTEffectContainer);

        void setEffectLst(CTEffectList cTEffectList);

        void unsetEffectDag();

        void unsetEffectLst();
    }

    public interface XSLFFillProperties {
        CTBlipFillProperties addNewBlipFill();

        CTGradientFillProperties addNewGradFill();

        CTGroupFillProperties addNewGrpFill();

        CTNoFillProperties addNewNoFill();

        CTPatternFillProperties addNewPattFill();

        CTSolidColorFillProperties addNewSolidFill();

        CTBlipFillProperties getBlipFill();

        CTGradientFillProperties getGradFill();

        CTGroupFillProperties getGrpFill();

        CTStyleMatrixReference getMatrixStyle();

        CTNoFillProperties getNoFill();

        CTPatternFillProperties getPattFill();

        CTSolidColorFillProperties getSolidFill();

        boolean isLineStyle();

        boolean isSetBlipFill();

        boolean isSetGradFill();

        boolean isSetGrpFill();

        boolean isSetMatrixStyle();

        boolean isSetNoFill();

        boolean isSetPattFill();

        boolean isSetSolidFill();

        void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

        void setGradFill(CTGradientFillProperties cTGradientFillProperties);

        void setGrpFill(CTGroupFillProperties cTGroupFillProperties);

        void setNoFill(CTNoFillProperties cTNoFillProperties);

        void setPattFill(CTPatternFillProperties cTPatternFillProperties);

        void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties);

        void unsetBlipFill();

        void unsetGradFill();

        void unsetGrpFill();

        void unsetNoFill();

        void unsetPattFill();

        void unsetSolidFill();
    }

    public interface XSLFGeometryProperties {
        CTCustomGeometry2D addNewCustGeom();

        CTPresetGeometry2D addNewPrstGeom();

        CTCustomGeometry2D getCustGeom();

        CTPresetGeometry2D getPrstGeom();

        boolean isSetCustGeom();

        boolean isSetPrstGeom();

        void setCustGeom(CTCustomGeometry2D cTCustomGeometry2D);

        void setPrstGeom(CTPresetGeometry2D cTPresetGeometry2D);

        void unsetCustGeom();

        void unsetPrstGeom();
    }

    XSLFPropertiesDelegate() {
    }

    public static XSLFFillProperties getFillDelegate(XmlObject xmlObject) {
        return (XSLFFillProperties) getDelegate(XSLFFillProperties.class, xmlObject);
    }

    public static XSLFGeometryProperties getGeometryDelegate(XmlObject xmlObject) {
        return (XSLFGeometryProperties) getDelegate(XSLFGeometryProperties.class, xmlObject);
    }

    public static XSLFEffectProperties getEffectDelegate(XmlObject xmlObject) {
        return (XSLFEffectProperties) getDelegate(XSLFEffectProperties.class, xmlObject);
    }

    private static class ShapeDelegate implements XSLFFillProperties, XSLFGeometryProperties, XSLFEffectProperties {
        final CTShapeProperties props;

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        ShapeDelegate(CTShapeProperties cTShapeProperties) {
            this.props = cTShapeProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
            this.props.setBlipFill(cTBlipFillProperties);
        }

        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
            this.props.setGrpFill(cTGroupFillProperties);
        }

        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        public CTCustomGeometry2D getCustGeom() {
            return this.props.getCustGeom();
        }

        public boolean isSetCustGeom() {
            return this.props.isSetCustGeom();
        }

        public void setCustGeom(CTCustomGeometry2D cTCustomGeometry2D) {
            this.props.setCustGeom(cTCustomGeometry2D);
        }

        public CTCustomGeometry2D addNewCustGeom() {
            return this.props.addNewCustGeom();
        }

        public void unsetCustGeom() {
            this.props.unsetCustGeom();
        }

        public CTPresetGeometry2D getPrstGeom() {
            return this.props.getPrstGeom();
        }

        public boolean isSetPrstGeom() {
            return this.props.isSetPrstGeom();
        }

        public void setPrstGeom(CTPresetGeometry2D cTPresetGeometry2D) {
            this.props.setPrstGeom(cTPresetGeometry2D);
        }

        public CTPresetGeometry2D addNewPrstGeom() {
            return this.props.addNewPrstGeom();
        }

        public void unsetPrstGeom() {
            this.props.unsetPrstGeom();
        }

        public CTEffectList getEffectLst() {
            return this.props.getEffectLst();
        }

        public boolean isSetEffectLst() {
            return this.props.isSetEffectLst();
        }

        public void setEffectLst(CTEffectList cTEffectList) {
            this.props.setEffectLst(cTEffectList);
        }

        public CTEffectList addNewEffectLst() {
            return this.props.addNewEffectLst();
        }

        public void unsetEffectLst() {
            this.props.unsetEffectLst();
        }

        public CTEffectContainer getEffectDag() {
            return this.props.getEffectDag();
        }

        public boolean isSetEffectDag() {
            return this.props.isSetEffectDag();
        }

        public void setEffectDag(CTEffectContainer cTEffectContainer) {
            this.props.setEffectDag(cTEffectContainer);
        }

        public CTEffectContainer addNewEffectDag() {
            return this.props.addNewEffectDag();
        }

        public void unsetEffectDag() {
            this.props.unsetEffectDag();
        }
    }

    private static class BackgroundDelegate implements XSLFFillProperties, XSLFEffectProperties {
        final CTBackgroundProperties props;

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        BackgroundDelegate(CTBackgroundProperties cTBackgroundProperties) {
            this.props = cTBackgroundProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
            this.props.setBlipFill(cTBlipFillProperties);
        }

        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
            this.props.setGrpFill(cTGroupFillProperties);
        }

        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        public CTEffectList getEffectLst() {
            return this.props.getEffectLst();
        }

        public boolean isSetEffectLst() {
            return this.props.isSetEffectLst();
        }

        public void setEffectLst(CTEffectList cTEffectList) {
            this.props.setEffectLst(cTEffectList);
        }

        public CTEffectList addNewEffectLst() {
            return this.props.addNewEffectLst();
        }

        public void unsetEffectLst() {
            this.props.unsetEffectLst();
        }

        public CTEffectContainer getEffectDag() {
            return this.props.getEffectDag();
        }

        public boolean isSetEffectDag() {
            return this.props.isSetEffectDag();
        }

        public void setEffectDag(CTEffectContainer cTEffectContainer) {
            this.props.setEffectDag(cTEffectContainer);
        }

        public CTEffectContainer addNewEffectDag() {
            return this.props.addNewEffectDag();
        }

        public void unsetEffectDag() {
            this.props.unsetEffectDag();
        }
    }

    private static class TableCellDelegate implements XSLFFillProperties {
        final CTTableCellProperties props;

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        TableCellDelegate(CTTableCellProperties cTTableCellProperties) {
            this.props = cTTableCellProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
            this.props.setBlipFill(cTBlipFillProperties);
        }

        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
            this.props.setGrpFill(cTGroupFillProperties);
        }

        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }
    }

    private static class StyleMatrixDelegate implements XSLFFillProperties {
        final CTStyleMatrixReference props;

        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        public CTGradientFillProperties addNewGradFill() {
            return null;
        }

        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        public CTNoFillProperties addNewNoFill() {
            return null;
        }

        public CTPatternFillProperties addNewPattFill() {
            return null;
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return null;
        }

        public CTBlipFillProperties getBlipFill() {
            return null;
        }

        public CTGradientFillProperties getGradFill() {
            return null;
        }

        public CTGroupFillProperties getGrpFill() {
            return null;
        }

        public CTNoFillProperties getNoFill() {
            return null;
        }

        public CTPatternFillProperties getPattFill() {
            return null;
        }

        public CTSolidColorFillProperties getSolidFill() {
            return null;
        }

        public boolean isSetBlipFill() {
            return false;
        }

        public boolean isSetGradFill() {
            return false;
        }

        public boolean isSetGrpFill() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return true;
        }

        public boolean isSetNoFill() {
            return false;
        }

        public boolean isSetPattFill() {
            return false;
        }

        public boolean isSetSolidFill() {
            return false;
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        }

        public void unsetBlipFill() {
        }

        public void unsetGradFill() {
        }

        public void unsetGrpFill() {
        }

        public void unsetNoFill() {
        }

        public void unsetPattFill() {
        }

        public void unsetSolidFill() {
        }

        StyleMatrixDelegate(CTStyleMatrixReference cTStyleMatrixReference) {
            this.props = cTStyleMatrixReference;
        }

        public CTStyleMatrixReference getMatrixStyle() {
            return this.props;
        }

        public boolean isLineStyle() {
            XmlCursor newCursor = this.props.newCursor();
            try {
                return "lnRef".equals(newCursor.getName().getLocalPart());
            } finally {
                newCursor.dispose();
            }
        }
    }

    private static class FillDelegate implements XSLFFillProperties {
        final CTFillProperties props;

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        FillDelegate(CTFillProperties cTFillProperties) {
            this.props = cTFillProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
            this.props.setBlipFill(cTBlipFillProperties);
        }

        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
            this.props.setGrpFill(cTGroupFillProperties);
        }

        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }
    }

    private static class FillPartDelegate implements XSLFFillProperties {
        final XmlObject props;

        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        public CTGradientFillProperties addNewGradFill() {
            return null;
        }

        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        public CTNoFillProperties addNewNoFill() {
            return null;
        }

        public CTPatternFillProperties addNewPattFill() {
            return null;
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return null;
        }

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        }

        public void unsetBlipFill() {
        }

        public void unsetGradFill() {
        }

        public void unsetGrpFill() {
        }

        public void unsetNoFill() {
        }

        public void unsetPattFill() {
        }

        public void unsetSolidFill() {
        }

        FillPartDelegate(XmlObject xmlObject) {
            this.props = xmlObject;
        }

        public CTNoFillProperties getNoFill() {
            if (isSetNoFill()) {
                return (CTNoFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetNoFill() {
            return this.props instanceof CTNoFillProperties;
        }

        public CTSolidColorFillProperties getSolidFill() {
            if (isSetSolidFill()) {
                return (CTSolidColorFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetSolidFill() {
            return this.props instanceof CTSolidColorFillProperties;
        }

        public CTGradientFillProperties getGradFill() {
            if (isSetGradFill()) {
                return (CTGradientFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetGradFill() {
            return this.props instanceof CTGradientFillProperties;
        }

        public CTBlipFillProperties getBlipFill() {
            if (isSetBlipFill()) {
                return (CTBlipFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetBlipFill() {
            return this.props instanceof CTBlipFillProperties;
        }

        public CTPatternFillProperties getPattFill() {
            if (isSetPattFill()) {
                return (CTPatternFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetPattFill() {
            return this.props instanceof CTPatternFillProperties;
        }

        public CTGroupFillProperties getGrpFill() {
            if (isSetGrpFill()) {
                return (CTGroupFillProperties) this.props;
            }
            return null;
        }

        public boolean isSetGrpFill() {
            return this.props instanceof CTGroupFillProperties;
        }
    }

    private static class LineStyleDelegate implements XSLFFillProperties {
        final CTLineProperties props;

        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        public CTBlipFillProperties getBlipFill() {
            return null;
        }

        public CTGroupFillProperties getGrpFill() {
            return null;
        }

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return true;
        }

        public boolean isSetBlipFill() {
            return false;
        }

        public boolean isSetGrpFill() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        }

        public void unsetBlipFill() {
        }

        public void unsetGrpFill() {
        }

        LineStyleDelegate(CTLineProperties cTLineProperties) {
            this.props = cTLineProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }
    }

    private static class TextCharDelegate implements XSLFFillProperties {
        final CTTextCharacterProperties props;

        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        public boolean isLineStyle() {
            return false;
        }

        public boolean isSetMatrixStyle() {
            return false;
        }

        TextCharDelegate(CTTextCharacterProperties cTTextCharacterProperties) {
            this.props = cTTextCharacterProperties;
        }

        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        public void setNoFill(CTNoFillProperties cTNoFillProperties) {
            this.props.setNoFill(cTNoFillProperties);
        }

        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
            this.props.setSolidFill(cTSolidColorFillProperties);
        }

        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
            this.props.setGradFill(cTGradientFillProperties);
        }

        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
            this.props.setBlipFill(cTBlipFillProperties);
        }

        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
            this.props.setPattFill(cTPatternFillProperties);
        }

        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
            this.props.setGrpFill(cTGroupFillProperties);
        }

        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }
    }

    private static <T> T getDelegate(Class<T> cls, XmlObject xmlObject) {
        T t;
        if (xmlObject == null) {
            return null;
        }
        if (xmlObject instanceof CTShapeProperties) {
            t = new ShapeDelegate((CTShapeProperties) xmlObject);
        } else if (xmlObject instanceof CTBackgroundProperties) {
            t = new BackgroundDelegate((CTBackgroundProperties) xmlObject);
        } else if (xmlObject instanceof CTStyleMatrixReference) {
            t = new StyleMatrixDelegate((CTStyleMatrixReference) xmlObject);
        } else if (xmlObject instanceof CTTableCellProperties) {
            t = new TableCellDelegate((CTTableCellProperties) xmlObject);
        } else if ((xmlObject instanceof CTNoFillProperties) || (xmlObject instanceof CTSolidColorFillProperties) || (xmlObject instanceof CTGradientFillProperties) || (xmlObject instanceof CTBlipFillProperties) || (xmlObject instanceof CTPatternFillProperties) || (xmlObject instanceof CTGroupFillProperties)) {
            t = new FillPartDelegate(xmlObject);
        } else if (xmlObject instanceof CTFillProperties) {
            t = new FillDelegate((CTFillProperties) xmlObject);
        } else if (xmlObject instanceof CTLineProperties) {
            t = new LineStyleDelegate((CTLineProperties) xmlObject);
        } else if (xmlObject instanceof CTTextCharacterProperties) {
            t = new TextCharDelegate((CTTextCharacterProperties) xmlObject);
        } else {
            LOG.atError().log("{} is an unknown properties type", (Object) xmlObject.getClass());
            return null;
        }
        if (cls.isInstance(t)) {
            return t;
        }
        LOG.atWarn().log("{} doesn't implement {}", t.getClass(), cls);
        return null;
    }
}
