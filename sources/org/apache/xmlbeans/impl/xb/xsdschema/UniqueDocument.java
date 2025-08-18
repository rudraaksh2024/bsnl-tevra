package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface UniqueDocument extends XmlObject {
    public static final DocumentFactory<UniqueDocument> Factory;
    public static final SchemaType type;

    Keybase addNewUnique();

    Keybase getUnique();

    void setUnique(Keybase keybase);

    static {
        DocumentFactory<UniqueDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "unique3752doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
