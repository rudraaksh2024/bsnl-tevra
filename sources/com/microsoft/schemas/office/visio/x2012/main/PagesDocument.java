package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface PagesDocument extends XmlObject {
    public static final DocumentFactory<PagesDocument> Factory;
    public static final SchemaType type;

    PagesType addNewPages();

    PagesType getPages();

    void setPages(PagesType pagesType);

    static {
        DocumentFactory<PagesDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pages52f4doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
