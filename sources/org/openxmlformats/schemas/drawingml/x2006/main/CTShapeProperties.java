package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public interface CTShapeProperties extends XmlObject {
    public static final DocumentFactory<CTShapeProperties> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTCustomGeometry2D addNewCustGeom();

    CTEffectContainer addNewEffectDag();

    CTEffectList addNewEffectLst();

    CTOfficeArtExtensionList addNewExtLst();

    CTGradientFillProperties addNewGradFill();

    CTGroupFillProperties addNewGrpFill();

    CTLineProperties addNewLn();

    CTNoFillProperties addNewNoFill();

    CTPatternFillProperties addNewPattFill();

    CTPresetGeometry2D addNewPrstGeom();

    CTScene3D addNewScene3D();

    CTSolidColorFillProperties addNewSolidFill();

    CTShape3D addNewSp3D();

    CTTransform2D addNewXfrm();

    CTBlipFillProperties getBlipFill();

    STBlackWhiteMode.Enum getBwMode();

    CTCustomGeometry2D getCustGeom();

    CTEffectContainer getEffectDag();

    CTEffectList getEffectLst();

    CTOfficeArtExtensionList getExtLst();

    CTGradientFillProperties getGradFill();

    CTGroupFillProperties getGrpFill();

    CTLineProperties getLn();

    CTNoFillProperties getNoFill();

    CTPatternFillProperties getPattFill();

    CTPresetGeometry2D getPrstGeom();

    CTScene3D getScene3D();

    CTSolidColorFillProperties getSolidFill();

    CTShape3D getSp3D();

    CTTransform2D getXfrm();

    boolean isSetBlipFill();

    boolean isSetBwMode();

    boolean isSetCustGeom();

    boolean isSetEffectDag();

    boolean isSetEffectLst();

    boolean isSetExtLst();

    boolean isSetGradFill();

    boolean isSetGrpFill();

    boolean isSetLn();

    boolean isSetNoFill();

    boolean isSetPattFill();

    boolean isSetPrstGeom();

    boolean isSetScene3D();

    boolean isSetSolidFill();

    boolean isSetSp3D();

    boolean isSetXfrm();

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setBwMode(STBlackWhiteMode.Enum enumR);

    void setCustGeom(CTCustomGeometry2D cTCustomGeometry2D);

    void setEffectDag(CTEffectContainer cTEffectContainer);

    void setEffectLst(CTEffectList cTEffectList);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGradFill(CTGradientFillProperties cTGradientFillProperties);

    void setGrpFill(CTGroupFillProperties cTGroupFillProperties);

    void setLn(CTLineProperties cTLineProperties);

    void setNoFill(CTNoFillProperties cTNoFillProperties);

    void setPattFill(CTPatternFillProperties cTPatternFillProperties);

    void setPrstGeom(CTPresetGeometry2D cTPresetGeometry2D);

    void setScene3D(CTScene3D cTScene3D);

    void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties);

    void setSp3D(CTShape3D cTShape3D);

    void setXfrm(CTTransform2D cTTransform2D);

    void unsetBlipFill();

    void unsetBwMode();

    void unsetCustGeom();

    void unsetEffectDag();

    void unsetEffectLst();

    void unsetExtLst();

    void unsetGradFill();

    void unsetGrpFill();

    void unsetLn();

    void unsetNoFill();

    void unsetPattFill();

    void unsetPrstGeom();

    void unsetScene3D();

    void unsetSolidFill();

    void unsetSp3D();

    void unsetXfrm();

    STBlackWhiteMode xgetBwMode();

    void xsetBwMode(STBlackWhiteMode sTBlackWhiteMode);

    static {
        DocumentFactory<CTShapeProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapeproperties30e5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
