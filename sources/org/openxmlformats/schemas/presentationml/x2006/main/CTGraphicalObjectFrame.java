package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public interface CTGraphicalObjectFrame extends XmlObject {
    public static final DocumentFactory<CTGraphicalObjectFrame> Factory;
    public static final SchemaType type;

    CTExtensionListModify addNewExtLst();

    CTGraphicalObject addNewGraphic();

    CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr();

    CTTransform2D addNewXfrm();

    STBlackWhiteMode.Enum getBwMode();

    CTExtensionListModify getExtLst();

    CTGraphicalObject getGraphic();

    CTGraphicalObjectFrameNonVisual getNvGraphicFramePr();

    CTTransform2D getXfrm();

    boolean isSetBwMode();

    boolean isSetExtLst();

    void setBwMode(STBlackWhiteMode.Enum enumR);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setGraphic(CTGraphicalObject cTGraphicalObject);

    void setNvGraphicFramePr(CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual);

    void setXfrm(CTTransform2D cTTransform2D);

    void unsetBwMode();

    void unsetExtLst();

    STBlackWhiteMode xgetBwMode();

    void xsetBwMode(STBlackWhiteMode sTBlackWhiteMode);

    static {
        DocumentFactory<CTGraphicalObjectFrame> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobjectframebfeatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
