package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.STConnectorType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STConnectorTypeImpl extends JavaStringEnumerationHolderEx implements STConnectorType {
    private static final long serialVersionUID = 1;

    public STConnectorTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STConnectorTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
