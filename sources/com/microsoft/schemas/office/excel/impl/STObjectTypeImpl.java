package com.microsoft.schemas.office.excel.impl;

import com.microsoft.schemas.office.excel.STObjectType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STObjectTypeImpl extends JavaStringEnumerationHolderEx implements STObjectType {
    private static final long serialVersionUID = 1;

    public STObjectTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STObjectTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
