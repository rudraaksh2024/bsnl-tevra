package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlUnsignedByte extends XmlUnsignedShort {
    public static final XmlObjectFactory<XmlUnsignedByte> Factory;
    public static final SchemaType type;

    short getShortValue();

    void setShortValue(short s);

    static {
        XmlObjectFactory<XmlUnsignedByte> xmlObjectFactory = new XmlObjectFactory<>("_BI_unsignedByte");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
