package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedShort;

public class XmlUnsignedShortImpl extends JavaIntHolderEx implements XmlUnsignedShort {
    public XmlUnsignedShortImpl() {
        super(XmlUnsignedShort.type, false);
    }

    public XmlUnsignedShortImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
