package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;

public class FormChoiceImpl extends JavaStringEnumerationHolderEx implements FormChoice {
    private static final long serialVersionUID = 1;

    public FormChoiceImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected FormChoiceImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
