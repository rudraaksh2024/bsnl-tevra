package com.microsoft.schemas.office.excel.impl;

import com.microsoft.schemas.office.excel.STCF;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;

public class STCFImpl extends JavaStringHolderEx implements STCF {
    private static final long serialVersionUID = 1;

    public STCFImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STCFImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
