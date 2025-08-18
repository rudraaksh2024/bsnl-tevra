package org.apache.poi.schemas.vmldrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface XmlDocument extends XmlObject {
    public static final DocumentFactory<XmlDocument> Factory;
    public static final SchemaType type;

    CTXML addNewXml();

    CTXML getXml();

    void setXml(CTXML ctxml);

    static {
        DocumentFactory<XmlDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "xml2eb5doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
