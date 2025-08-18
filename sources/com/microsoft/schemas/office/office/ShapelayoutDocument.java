package com.microsoft.schemas.office.office;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ShapelayoutDocument extends XmlObject {
    public static final DocumentFactory<ShapelayoutDocument> Factory;
    public static final SchemaType type;

    CTShapeLayout addNewShapelayout();

    CTShapeLayout getShapelayout();

    void setShapelayout(CTShapeLayout cTShapeLayout);

    static {
        DocumentFactory<ShapelayoutDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "shapelayoutebb0doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
