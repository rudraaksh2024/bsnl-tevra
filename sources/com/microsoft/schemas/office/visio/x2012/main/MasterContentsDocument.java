package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface MasterContentsDocument extends XmlObject {
    public static final DocumentFactory<MasterContentsDocument> Factory;
    public static final SchemaType type;

    PageContentsType addNewMasterContents();

    PageContentsType getMasterContents();

    void setMasterContents(PageContentsType pageContentsType);

    static {
        DocumentFactory<MasterContentsDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "mastercontentscb9edoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
