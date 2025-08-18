package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTime;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeIndefinite;

public class STTLTimeImpl extends XmlUnionImpl implements STTLTime, XmlUnsignedInt, STTLTimeIndefinite {
    private static final long serialVersionUID = 1;

    public STTLTimeImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STTLTimeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
