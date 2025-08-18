package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SimpleExplicitGroup extends ExplicitGroup {
    public static final DocumentFactory<SimpleExplicitGroup> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<SimpleExplicitGroup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "simpleexplicitgroup428ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
