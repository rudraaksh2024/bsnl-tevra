package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

public interface CTTableCellProperties extends XmlObject {
    public static final DocumentFactory<CTTableCellProperties> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTCell3D addNewCell3D();

    CTOfficeArtExtensionList addNewExtLst();

    CTGradientFillProperties addNewGradFill();

    CTGroupFillProperties addNewGrpFill();

    CTHeaders addNewHeaders();

    CTLineProperties addNewLnB();

    CTLineProperties addNewLnBlToTr();

    CTLineProperties addNewLnL();

    CTLineProperties addNewLnR();

    CTLineProperties addNewLnT();

    CTLineProperties addNewLnTlToBr();

    CTNoFillProperties addNewNoFill();

    CTPatternFillProperties addNewPattFill();

    CTSolidColorFillProperties addNewSolidFill();

    STTextAnchoringType.Enum getAnchor();

    boolean getAnchorCtr();

    CTBlipFillProperties getBlipFill();

    CTCell3D getCell3D();

    CTOfficeArtExtensionList getExtLst();

    CTGradientFillProperties getGradFill();

    CTGroupFillProperties getGrpFill();

    CTHeaders getHeaders();

    STTextHorzOverflowType.Enum getHorzOverflow();

    CTLineProperties getLnB();

    CTLineProperties getLnBlToTr();

    CTLineProperties getLnL();

    CTLineProperties getLnR();

    CTLineProperties getLnT();

    CTLineProperties getLnTlToBr();

    Object getMarB();

    Object getMarL();

    Object getMarR();

    Object getMarT();

    CTNoFillProperties getNoFill();

    CTPatternFillProperties getPattFill();

    CTSolidColorFillProperties getSolidFill();

    STTextVerticalType.Enum getVert();

    boolean isSetAnchor();

    boolean isSetAnchorCtr();

    boolean isSetBlipFill();

    boolean isSetCell3D();

    boolean isSetExtLst();

    boolean isSetGradFill();

    boolean isSetGrpFill();

    boolean isSetHeaders();

    boolean isSetHorzOverflow();

    boolean isSetLnB();

    boolean isSetLnBlToTr();

    boolean isSetLnL();

    boolean isSetLnR();

    boolean isSetLnT();

    boolean isSetLnTlToBr();

    boolean isSetMarB();

    boolean isSetMarL();

    boolean isSetMarR();

    boolean isSetMarT();

    boolean isSetNoFill();

    boolean isSetPattFill();

    boolean isSetSolidFill();

    boolean isSetVert();

    void setAnchor(STTextAnchoringType.Enum enumR);

    void setAnchorCtr(boolean z);

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setCell3D(CTCell3D cTCell3D);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGradFill(CTGradientFillProperties cTGradientFillProperties);

    void setGrpFill(CTGroupFillProperties cTGroupFillProperties);

    void setHeaders(CTHeaders cTHeaders);

    void setHorzOverflow(STTextHorzOverflowType.Enum enumR);

    void setLnB(CTLineProperties cTLineProperties);

    void setLnBlToTr(CTLineProperties cTLineProperties);

    void setLnL(CTLineProperties cTLineProperties);

    void setLnR(CTLineProperties cTLineProperties);

    void setLnT(CTLineProperties cTLineProperties);

    void setLnTlToBr(CTLineProperties cTLineProperties);

    void setMarB(Object obj);

    void setMarL(Object obj);

    void setMarR(Object obj);

    void setMarT(Object obj);

    void setNoFill(CTNoFillProperties cTNoFillProperties);

    void setPattFill(CTPatternFillProperties cTPatternFillProperties);

    void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties);

    void setVert(STTextVerticalType.Enum enumR);

    void unsetAnchor();

    void unsetAnchorCtr();

    void unsetBlipFill();

    void unsetCell3D();

    void unsetExtLst();

    void unsetGradFill();

    void unsetGrpFill();

    void unsetHeaders();

    void unsetHorzOverflow();

    void unsetLnB();

    void unsetLnBlToTr();

    void unsetLnL();

    void unsetLnR();

    void unsetLnT();

    void unsetLnTlToBr();

    void unsetMarB();

    void unsetMarL();

    void unsetMarR();

    void unsetMarT();

    void unsetNoFill();

    void unsetPattFill();

    void unsetSolidFill();

    void unsetVert();

    STTextAnchoringType xgetAnchor();

    XmlBoolean xgetAnchorCtr();

    STTextHorzOverflowType xgetHorzOverflow();

    STCoordinate32 xgetMarB();

    STCoordinate32 xgetMarL();

    STCoordinate32 xgetMarR();

    STCoordinate32 xgetMarT();

    STTextVerticalType xgetVert();

    void xsetAnchor(STTextAnchoringType sTTextAnchoringType);

    void xsetAnchorCtr(XmlBoolean xmlBoolean);

    void xsetHorzOverflow(STTextHorzOverflowType sTTextHorzOverflowType);

    void xsetMarB(STCoordinate32 sTCoordinate32);

    void xsetMarL(STCoordinate32 sTCoordinate32);

    void xsetMarR(STCoordinate32 sTCoordinate32);

    void xsetMarT(STCoordinate32 sTCoordinate32);

    void xsetVert(STTextVerticalType sTTextVerticalType);

    static {
        DocumentFactory<CTTableCellProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecellproperties1614type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
