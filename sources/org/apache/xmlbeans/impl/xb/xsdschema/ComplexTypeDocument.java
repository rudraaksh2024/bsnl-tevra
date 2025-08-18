package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ComplexTypeDocument extends XmlObject {
    public static final DocumentFactory<ComplexTypeDocument> Factory;
    public static final SchemaType type;

    TopLevelComplexType addNewComplexType();

    TopLevelComplexType getComplexType();

    void setComplexType(TopLevelComplexType topLevelComplexType);

    static {
        DocumentFactory<ComplexTypeDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "complextype83cbdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
