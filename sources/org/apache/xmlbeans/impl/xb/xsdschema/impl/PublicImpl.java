package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class PublicImpl extends JavaStringHolderEx implements Public {
    private static final long serialVersionUID = 1;

    public PublicImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected PublicImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
