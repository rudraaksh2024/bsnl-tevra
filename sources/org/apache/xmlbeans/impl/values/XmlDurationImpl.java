package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDuration;

public class XmlDurationImpl extends JavaGDurationHolderEx implements XmlDuration {
    public XmlDurationImpl() {
        super(XmlDuration.type, false);
    }

    public XmlDurationImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
