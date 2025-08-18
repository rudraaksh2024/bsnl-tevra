package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;

public class NamespacePrefixListImpl extends XmlListImpl implements NamespacePrefixList {
    private static final long serialVersionUID = 1;

    public NamespacePrefixListImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected NamespacePrefixListImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
