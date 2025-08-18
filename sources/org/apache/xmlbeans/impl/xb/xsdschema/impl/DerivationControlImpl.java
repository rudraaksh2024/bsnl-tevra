package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;

public class DerivationControlImpl extends JavaStringEnumerationHolderEx implements DerivationControl {
    private static final long serialVersionUID = 1;

    public DerivationControlImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected DerivationControlImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
