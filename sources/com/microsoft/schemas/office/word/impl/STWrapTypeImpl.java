package com.microsoft.schemas.office.word.impl;

import com.microsoft.schemas.office.word.STWrapType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STWrapTypeImpl extends JavaStringEnumerationHolderEx implements STWrapType {
    private static final long serialVersionUID = 1;

    public STWrapTypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STWrapTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
