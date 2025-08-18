package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.STExt;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STExtImpl extends JavaStringEnumerationHolderEx implements STExt {
    private static final long serialVersionUID = 1;

    public STExtImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STExtImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
