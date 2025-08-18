package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface LocalComplexType extends ComplexType {
    public static final DocumentFactory<LocalComplexType> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<LocalComplexType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "localcomplextype6494type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
