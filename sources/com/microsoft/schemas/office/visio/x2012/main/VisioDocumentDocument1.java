package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface VisioDocumentDocument1 extends XmlObject {
    public static final DocumentFactory<VisioDocumentDocument1> Factory;
    public static final SchemaType type;

    VisioDocumentType addNewVisioDocument();

    VisioDocumentType getVisioDocument();

    void setVisioDocument(VisioDocumentType visioDocumentType);

    static {
        DocumentFactory<VisioDocumentDocument1> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "visiodocumentd431doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
