package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlByte extends XmlShort {
    public static final XmlObjectFactory<XmlByte> Factory;
    public static final SchemaType type;

    byte getByteValue();

    void setByteValue(byte b);

    static {
        XmlObjectFactory<XmlByte> xmlObjectFactory = new XmlObjectFactory<>("_BI_byte");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
