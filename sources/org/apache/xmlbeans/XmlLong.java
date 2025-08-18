package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlLong extends XmlInteger {
    public static final XmlObjectFactory<XmlLong> Factory;
    public static final SchemaType type;

    long getLongValue();

    void setLongValue(long j);

    static {
        XmlObjectFactory<XmlLong> xmlObjectFactory = new XmlObjectFactory<>("_BI_long");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
