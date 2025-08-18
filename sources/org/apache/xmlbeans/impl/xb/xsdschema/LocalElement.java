package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface LocalElement extends Element {
    public static final DocumentFactory<LocalElement> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<LocalElement> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "localelement2ce2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
