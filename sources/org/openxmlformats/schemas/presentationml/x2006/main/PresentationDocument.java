package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface PresentationDocument extends XmlObject {
    public static final DocumentFactory<PresentationDocument> Factory;
    public static final SchemaType type;

    CTPresentation addNewPresentation();

    CTPresentation getPresentation();

    void setPresentation(CTPresentation cTPresentation);

    static {
        DocumentFactory<PresentationDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "presentation02f7doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
