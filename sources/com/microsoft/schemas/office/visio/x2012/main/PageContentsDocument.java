package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface PageContentsDocument extends XmlObject {
    public static final DocumentFactory<PageContentsDocument> Factory;
    public static final SchemaType type;

    PageContentsType addNewPageContents();

    PageContentsType getPageContents();

    void setPageContents(PageContentsType pageContentsType);

    static {
        DocumentFactory<PageContentsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pagecontentsfc8bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
