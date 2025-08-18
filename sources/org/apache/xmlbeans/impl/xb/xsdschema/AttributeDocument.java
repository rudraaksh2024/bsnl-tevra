package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AttributeDocument extends XmlObject {
    public static final DocumentFactory<AttributeDocument> Factory;
    public static final SchemaType type;

    TopLevelAttribute addNewAttribute();

    TopLevelAttribute getAttribute();

    void setAttribute(TopLevelAttribute topLevelAttribute);

    static {
        DocumentFactory<AttributeDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "attributeedb9doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
