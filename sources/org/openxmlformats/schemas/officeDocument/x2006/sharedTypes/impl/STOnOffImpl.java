package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;

public class STOnOffImpl extends XmlUnionImpl implements STOnOff, XmlBoolean, STOnOff1 {
    private static final long serialVersionUID = 1;

    public STOnOffImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STOnOffImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
