package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlUnsignedInt extends XmlUnsignedLong {
    public static final XmlObjectFactory<XmlUnsignedInt> Factory;
    public static final SchemaType type;

    long getLongValue();

    void setLongValue(long j);

    static {
        XmlObjectFactory<XmlUnsignedInt> xmlObjectFactory = new XmlObjectFactory<>("_BI_unsignedInt");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
