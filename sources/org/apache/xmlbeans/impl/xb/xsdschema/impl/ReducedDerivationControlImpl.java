package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.ReducedDerivationControl;

public class ReducedDerivationControlImpl extends JavaStringEnumerationHolderEx implements ReducedDerivationControl {
    private static final long serialVersionUID = 1;

    public ReducedDerivationControlImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected ReducedDerivationControlImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
