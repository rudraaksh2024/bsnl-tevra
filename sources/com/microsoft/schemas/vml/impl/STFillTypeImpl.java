package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.STFillType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STFillTypeImpl extends JavaStringEnumerationHolderEx implements STFillType {
    private static final long serialVersionUID = 1;

    public STFillTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STFillTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
