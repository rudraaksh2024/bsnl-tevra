package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface KeyDocument extends XmlObject {
    public static final DocumentFactory<KeyDocument> Factory;
    public static final SchemaType type;

    Keybase addNewKey();

    Keybase getKey();

    void setKey(Keybase keybase);

    static {
        DocumentFactory<KeyDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "key5d16doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
