package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface MaxExclusiveDocument extends XmlObject {
    public static final DocumentFactory<MaxExclusiveDocument> Factory;
    public static final SchemaType type;

    Facet addNewMaxExclusive();

    Facet getMaxExclusive();

    void setMaxExclusive(Facet facet);

    static {
        DocumentFactory<MaxExclusiveDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "maxexclusive6d69doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
