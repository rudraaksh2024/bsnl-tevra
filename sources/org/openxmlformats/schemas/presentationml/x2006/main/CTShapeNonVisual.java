package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingShapeProps;

public interface CTShapeNonVisual extends XmlObject {
    public static final DocumentFactory<CTShapeNonVisual> Factory;
    public static final SchemaType type;

    CTNonVisualDrawingProps addNewCNvPr();

    CTNonVisualDrawingShapeProps addNewCNvSpPr();

    CTApplicationNonVisualDrawingProps addNewNvPr();

    CTNonVisualDrawingProps getCNvPr();

    CTNonVisualDrawingShapeProps getCNvSpPr();

    CTApplicationNonVisualDrawingProps getNvPr();

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setCNvSpPr(CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps);

    void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps);

    static {
        DocumentFactory<CTShapeNonVisual> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapenonvisualb619type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
