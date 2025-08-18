package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlShort extends XmlInt {
    public static final XmlObjectFactory<XmlShort> Factory;
    public static final SchemaType type;

    short getShortValue();

    void setShortValue(short s);

    static {
        XmlObjectFactory<XmlShort> xmlObjectFactory = new XmlObjectFactory<>("_BI_short");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
