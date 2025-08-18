package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface LengthDocument extends XmlObject {
    public static final DocumentFactory<LengthDocument> Factory;
    public static final SchemaType type;

    NumFacet addNewLength();

    NumFacet getLength();

    void setLength(NumFacet numFacet);

    static {
        DocumentFactory<LengthDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "length7edddoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
