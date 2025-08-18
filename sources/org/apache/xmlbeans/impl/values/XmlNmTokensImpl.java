package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNMTOKENS;

public class XmlNmTokensImpl extends XmlListImpl implements XmlNMTOKENS {
    public XmlNmTokensImpl() {
        super(XmlNMTOKENS.type, false);
    }

    public XmlNmTokensImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
