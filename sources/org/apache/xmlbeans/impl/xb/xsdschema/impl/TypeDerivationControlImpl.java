package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.TypeDerivationControl;

public class TypeDerivationControlImpl extends JavaStringEnumerationHolderEx implements TypeDerivationControl {
    private static final long serialVersionUID = 1;

    public TypeDerivationControlImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected TypeDerivationControlImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
