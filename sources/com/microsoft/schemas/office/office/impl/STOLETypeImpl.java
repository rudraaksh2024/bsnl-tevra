package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.STOLEType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;

public class STOLETypeImpl extends JavaStringEnumerationHolderEx implements STOLEType {
    private static final long serialVersionUID = 1;

    public STOLETypeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STOLETypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
