package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface MinLengthDocument extends XmlObject {
    public static final DocumentFactory<MinLengthDocument> Factory;
    public static final SchemaType type;

    NumFacet addNewMinLength();

    NumFacet getMinLength();

    void setMinLength(NumFacet numFacet);

    static {
        DocumentFactory<MinLengthDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "minlengthe7fddoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
