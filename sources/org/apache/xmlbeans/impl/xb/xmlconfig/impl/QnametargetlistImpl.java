package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetlist;

public class QnametargetlistImpl extends XmlListImpl implements Qnametargetlist {
    private static final long serialVersionUID = 1;

    public QnametargetlistImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected QnametargetlistImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
