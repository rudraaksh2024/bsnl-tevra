package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;

public interface CTGraphicalObjectFrameNonVisual extends XmlObject {
    public static final DocumentFactory<CTGraphicalObjectFrameNonVisual> Factory;
    public static final SchemaType type;

    CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr();

    CTNonVisualDrawingProps addNewCNvPr();

    CTApplicationNonVisualDrawingProps addNewNvPr();

    CTNonVisualGraphicFrameProperties getCNvGraphicFramePr();

    CTNonVisualDrawingProps getCNvPr();

    CTApplicationNonVisualDrawingProps getNvPr();

    void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties);

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps);

    static {
        DocumentFactory<CTGraphicalObjectFrameNonVisual> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgraphicalobjectframenonvisual257dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
