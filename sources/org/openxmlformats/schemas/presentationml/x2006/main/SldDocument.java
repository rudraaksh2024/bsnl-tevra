package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SldDocument extends XmlObject {
    public static final DocumentFactory<SldDocument> Factory;
    public static final SchemaType type;

    CTSlide addNewSld();

    CTSlide getSld();

    void setSld(CTSlide cTSlide);

    static {
        DocumentFactory<SldDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sld1b98doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
