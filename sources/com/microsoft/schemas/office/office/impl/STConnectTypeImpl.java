package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.STConnectType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STConnectTypeImpl extends JavaStringEnumerationHolderEx implements STConnectType {
    private static final long serialVersionUID = 1;

    public STConnectTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STConnectTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
