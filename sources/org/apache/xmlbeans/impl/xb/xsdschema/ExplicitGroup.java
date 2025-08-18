package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ExplicitGroup extends Group {
    public static final DocumentFactory<ExplicitGroup> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<ExplicitGroup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "explicitgroup4efatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
