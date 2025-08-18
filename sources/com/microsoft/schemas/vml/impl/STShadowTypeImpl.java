package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.STShadowType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STShadowTypeImpl extends JavaStringEnumerationHolderEx implements STShadowType {
    private static final long serialVersionUID = 1;

    public STShadowTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STShadowTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
