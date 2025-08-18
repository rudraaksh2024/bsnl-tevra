package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SimpleRestrictionType extends RestrictionType {
    public static final DocumentFactory<SimpleRestrictionType> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<SimpleRestrictionType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "simplerestrictiontypeeab1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
