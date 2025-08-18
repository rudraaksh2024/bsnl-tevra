package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SldLayoutDocument extends XmlObject {
    public static final DocumentFactory<SldLayoutDocument> Factory;
    public static final SchemaType type;

    CTSlideLayout addNewSldLayout();

    CTSlideLayout getSldLayout();

    void setSldLayout(CTSlideLayout cTSlideLayout);

    static {
        DocumentFactory<SldLayoutDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sldlayout638edoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
