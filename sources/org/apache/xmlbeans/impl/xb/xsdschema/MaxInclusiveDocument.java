package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface MaxInclusiveDocument extends XmlObject {
    public static final DocumentFactory<MaxInclusiveDocument> Factory;
    public static final SchemaType type;

    Facet addNewMaxInclusive();

    Facet getMaxInclusive();

    void setMaxInclusive(Facet facet);

    static {
        DocumentFactory<MaxInclusiveDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "maxinclusive93dbdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
