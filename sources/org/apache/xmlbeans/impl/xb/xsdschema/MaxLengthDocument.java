package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface MaxLengthDocument extends XmlObject {
    public static final DocumentFactory<MaxLengthDocument> Factory;
    public static final SchemaType type;

    NumFacet addNewMaxLength();

    NumFacet getMaxLength();

    void setMaxLength(NumFacet numFacet);

    static {
        DocumentFactory<MaxLengthDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "maxlengthf8abdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
