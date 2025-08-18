package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface EnumerationDocument extends XmlObject {
    public static final DocumentFactory<EnumerationDocument> Factory;
    public static final SchemaType type;

    NoFixedFacet addNewEnumeration();

    NoFixedFacet getEnumeration();

    void setEnumeration(NoFixedFacet noFixedFacet);

    static {
        DocumentFactory<EnumerationDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "enumeration052edoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
