package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;

public class XmlAnySimpleTypeRestriction extends XmlAnySimpleTypeImpl {
    private SchemaType _schemaType;

    public XmlAnySimpleTypeRestriction(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }
}
