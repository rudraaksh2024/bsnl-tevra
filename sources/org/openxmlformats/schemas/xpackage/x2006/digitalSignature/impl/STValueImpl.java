package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STValue;

public class STValueImpl extends JavaStringHolderEx implements STValue {
    private static final long serialVersionUID = 1;

    public STValueImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STValueImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
