package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.STBWMode;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STBWModeImpl extends JavaStringEnumerationHolderEx implements STBWMode {
    private static final long serialVersionUID = 1;

    public STBWModeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STBWModeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
