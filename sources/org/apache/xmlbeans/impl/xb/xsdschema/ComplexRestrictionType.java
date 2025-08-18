package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ComplexRestrictionType extends RestrictionType {
    public static final DocumentFactory<ComplexRestrictionType> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<ComplexRestrictionType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "complexrestrictiontype1b7dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
