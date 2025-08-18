package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

public interface CTGraphicalObjectFrame extends XmlObject {
    public static final DocumentFactory<CTGraphicalObjectFrame> Factory;
    public static final SchemaType type;

    CTGraphicalObject addNewGraphic();

    CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr();

    CTTransform2D addNewXfrm();

    boolean getFPublished();

    CTGraphicalObject getGraphic();

    String getMacro();

    CTGraphicalObjectFrameNonVisual getNvGraphicFramePr();

    CTTransform2D getXfrm();

    boolean isSetFPublished();

    boolean isSetMacro();

    void setFPublished(boolean z);

    void setGraphic(CTGraphicalObject cTGraphicalObject);

    void setMacro(String str);

    void setNvGraphicFramePr(CTGraphicalObjectFrameNonVisual cTGraphicalObjectFrameNonVisual);

    void setXfrm(CTTransform2D cTTransform2D);

    void unsetFPublished();

    void unsetMacro();

    XmlBoolean xgetFPublished();

    XmlString xgetMacro();

    void xsetFPublished(XmlBoolean xmlBoolean);

    void xsetMacro(XmlString xmlString);

    static {
        DocumentFactory<CTGraphicalObjectFrame> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobjectframe536ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
