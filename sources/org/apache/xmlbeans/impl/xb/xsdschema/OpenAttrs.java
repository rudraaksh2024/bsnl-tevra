package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface OpenAttrs extends XmlObject {
    public static final DocumentFactory<OpenAttrs> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<OpenAttrs> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "openattrs2d4dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
