package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;

public interface CTTextBodyProperties extends XmlObject {
    public static final DocumentFactory<CTTextBodyProperties> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTFlatText addNewFlatTx();

    CTTextNoAutofit addNewNoAutofit();

    CTTextNormalAutofit addNewNormAutofit();

    CTPresetTextShape addNewPrstTxWarp();

    CTScene3D addNewScene3D();

    CTShape3D addNewSp3D();

    CTTextShapeAutofit addNewSpAutoFit();

    STTextAnchoringType.Enum getAnchor();

    boolean getAnchorCtr();

    Object getBIns();

    boolean getCompatLnSpc();

    CTOfficeArtExtensionList getExtLst();

    CTFlatText getFlatTx();

    boolean getForceAA();

    boolean getFromWordArt();

    STTextHorzOverflowType.Enum getHorzOverflow();

    Object getLIns();

    CTTextNoAutofit getNoAutofit();

    CTTextNormalAutofit getNormAutofit();

    int getNumCol();

    CTPresetTextShape getPrstTxWarp();

    Object getRIns();

    int getRot();

    boolean getRtlCol();

    CTScene3D getScene3D();

    CTShape3D getSp3D();

    CTTextShapeAutofit getSpAutoFit();

    int getSpcCol();

    boolean getSpcFirstLastPara();

    Object getTIns();

    boolean getUpright();

    STTextVerticalType.Enum getVert();

    STTextVertOverflowType.Enum getVertOverflow();

    STTextWrappingType.Enum getWrap();

    boolean isSetAnchor();

    boolean isSetAnchorCtr();

    boolean isSetBIns();

    boolean isSetCompatLnSpc();

    boolean isSetExtLst();

    boolean isSetFlatTx();

    boolean isSetForceAA();

    boolean isSetFromWordArt();

    boolean isSetHorzOverflow();

    boolean isSetLIns();

    boolean isSetNoAutofit();

    boolean isSetNormAutofit();

    boolean isSetNumCol();

    boolean isSetPrstTxWarp();

    boolean isSetRIns();

    boolean isSetRot();

    boolean isSetRtlCol();

    boolean isSetScene3D();

    boolean isSetSp3D();

    boolean isSetSpAutoFit();

    boolean isSetSpcCol();

    boolean isSetSpcFirstLastPara();

    boolean isSetTIns();

    boolean isSetUpright();

    boolean isSetVert();

    boolean isSetVertOverflow();

    boolean isSetWrap();

    void setAnchor(STTextAnchoringType.Enum enumR);

    void setAnchorCtr(boolean z);

    void setBIns(Object obj);

    void setCompatLnSpc(boolean z);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFlatTx(CTFlatText cTFlatText);

    void setForceAA(boolean z);

    void setFromWordArt(boolean z);

    void setHorzOverflow(STTextHorzOverflowType.Enum enumR);

    void setLIns(Object obj);

    void setNoAutofit(CTTextNoAutofit cTTextNoAutofit);

    void setNormAutofit(CTTextNormalAutofit cTTextNormalAutofit);

    void setNumCol(int i);

    void setPrstTxWarp(CTPresetTextShape cTPresetTextShape);

    void setRIns(Object obj);

    void setRot(int i);

    void setRtlCol(boolean z);

    void setScene3D(CTScene3D cTScene3D);

    void setSp3D(CTShape3D cTShape3D);

    void setSpAutoFit(CTTextShapeAutofit cTTextShapeAutofit);

    void setSpcCol(int i);

    void setSpcFirstLastPara(boolean z);

    void setTIns(Object obj);

    void setUpright(boolean z);

    void setVert(STTextVerticalType.Enum enumR);

    void setVertOverflow(STTextVertOverflowType.Enum enumR);

    void setWrap(STTextWrappingType.Enum enumR);

    void unsetAnchor();

    void unsetAnchorCtr();

    void unsetBIns();

    void unsetCompatLnSpc();

    void unsetExtLst();

    void unsetFlatTx();

    void unsetForceAA();

    void unsetFromWordArt();

    void unsetHorzOverflow();

    void unsetLIns();

    void unsetNoAutofit();

    void unsetNormAutofit();

    void unsetNumCol();

    void unsetPrstTxWarp();

    void unsetRIns();

    void unsetRot();

    void unsetRtlCol();

    void unsetScene3D();

    void unsetSp3D();

    void unsetSpAutoFit();

    void unsetSpcCol();

    void unsetSpcFirstLastPara();

    void unsetTIns();

    void unsetUpright();

    void unsetVert();

    void unsetVertOverflow();

    void unsetWrap();

    STTextAnchoringType xgetAnchor();

    XmlBoolean xgetAnchorCtr();

    STCoordinate32 xgetBIns();

    XmlBoolean xgetCompatLnSpc();

    XmlBoolean xgetForceAA();

    XmlBoolean xgetFromWordArt();

    STTextHorzOverflowType xgetHorzOverflow();

    STCoordinate32 xgetLIns();

    STTextColumnCount xgetNumCol();

    STCoordinate32 xgetRIns();

    STAngle xgetRot();

    XmlBoolean xgetRtlCol();

    STPositiveCoordinate32 xgetSpcCol();

    XmlBoolean xgetSpcFirstLastPara();

    STCoordinate32 xgetTIns();

    XmlBoolean xgetUpright();

    STTextVerticalType xgetVert();

    STTextVertOverflowType xgetVertOverflow();

    STTextWrappingType xgetWrap();

    void xsetAnchor(STTextAnchoringType sTTextAnchoringType);

    void xsetAnchorCtr(XmlBoolean xmlBoolean);

    void xsetBIns(STCoordinate32 sTCoordinate32);

    void xsetCompatLnSpc(XmlBoolean xmlBoolean);

    void xsetForceAA(XmlBoolean xmlBoolean);

    void xsetFromWordArt(XmlBoolean xmlBoolean);

    void xsetHorzOverflow(STTextHorzOverflowType sTTextHorzOverflowType);

    void xsetLIns(STCoordinate32 sTCoordinate32);

    void xsetNumCol(STTextColumnCount sTTextColumnCount);

    void xsetRIns(STCoordinate32 sTCoordinate32);

    void xsetRot(STAngle sTAngle);

    void xsetRtlCol(XmlBoolean xmlBoolean);

    void xsetSpcCol(STPositiveCoordinate32 sTPositiveCoordinate32);

    void xsetSpcFirstLastPara(XmlBoolean xmlBoolean);

    void xsetTIns(STCoordinate32 sTCoordinate32);

    void xsetUpright(XmlBoolean xmlBoolean);

    void xsetVert(STTextVerticalType sTTextVerticalType);

    void xsetVertOverflow(STTextVertOverflowType sTTextVertOverflowType);

    void xsetWrap(STTextWrappingType sTTextWrappingType);

    static {
        DocumentFactory<CTTextBodyProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbodyproperties87ddtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
