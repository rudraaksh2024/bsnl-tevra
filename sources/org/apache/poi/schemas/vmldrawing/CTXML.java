package org.apache.poi.schemas.vmldrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTXML extends XmlObject {
    public static final DocumentFactory<CTXML> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTXML> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxml2989type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
