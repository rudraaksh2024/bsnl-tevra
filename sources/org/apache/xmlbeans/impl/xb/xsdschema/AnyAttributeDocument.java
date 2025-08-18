package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AnyAttributeDocument extends XmlObject {
    public static final DocumentFactory<AnyAttributeDocument> Factory;
    public static final SchemaType type;

    Wildcard addNewAnyAttribute();

    Wildcard getAnyAttribute();

    void setAnyAttribute(Wildcard wildcard);

    static {
        DocumentFactory<AnyAttributeDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "anyattribute23b3doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
