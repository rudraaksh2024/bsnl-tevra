package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.xb.xmlconfig.JavaName;

public class JavaNameImpl extends JavaStringHolderEx implements JavaName {
    private static final long serialVersionUID = 1;

    public JavaNameImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected JavaNameImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
