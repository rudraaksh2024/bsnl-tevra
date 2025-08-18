package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

public interface CTGradientFillProperties extends XmlObject {
    public static final DocumentFactory<CTGradientFillProperties> Factory;
    public static final SchemaType type;

    CTGradientStopList addNewGsLst();

    CTLinearShadeProperties addNewLin();

    CTPathShadeProperties addNewPath();

    CTRelativeRect addNewTileRect();

    STTileFlipMode.Enum getFlip();

    CTGradientStopList getGsLst();

    CTLinearShadeProperties getLin();

    CTPathShadeProperties getPath();

    boolean getRotWithShape();

    CTRelativeRect getTileRect();

    boolean isSetFlip();

    boolean isSetGsLst();

    boolean isSetLin();

    boolean isSetPath();

    boolean isSetRotWithShape();

    boolean isSetTileRect();

    void setFlip(STTileFlipMode.Enum enumR);

    void setGsLst(CTGradientStopList cTGradientStopList);

    void setLin(CTLinearShadeProperties cTLinearShadeProperties);

    void setPath(CTPathShadeProperties cTPathShadeProperties);

    void setRotWithShape(boolean z);

    void setTileRect(CTRelativeRect cTRelativeRect);

    void unsetFlip();

    void unsetGsLst();

    void unsetLin();

    void unsetPath();

    void unsetRotWithShape();

    void unsetTileRect();

    STTileFlipMode xgetFlip();

    XmlBoolean xgetRotWithShape();

    void xsetFlip(STTileFlipMode sTTileFlipMode);

    void xsetRotWithShape(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTGradientFillProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgradientfillproperties81c1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
