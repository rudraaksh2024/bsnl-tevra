package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface MinInclusiveDocument extends XmlObject {
    public static final DocumentFactory<MinInclusiveDocument> Factory;
    public static final SchemaType type;

    Facet addNewMinInclusive();

    Facet getMinInclusive();

    void setMinInclusive(Facet facet);

    static {
        DocumentFactory<MinInclusiveDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "mininclusive8b49doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
