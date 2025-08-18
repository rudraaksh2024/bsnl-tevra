package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlInt extends XmlLong {
    public static final XmlObjectFactory<XmlInt> Factory;
    public static final SchemaType type;

    int getIntValue();

    void setIntValue(int i);

    static {
        XmlObjectFactory<XmlInt> xmlObjectFactory = new XmlObjectFactory<>("_BI_int");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
