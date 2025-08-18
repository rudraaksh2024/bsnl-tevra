package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlUnsignedShort extends XmlUnsignedInt {
    public static final XmlObjectFactory<XmlUnsignedShort> Factory;
    public static final SchemaType type;

    int getIntValue();

    void setIntValue(int i);

    static {
        XmlObjectFactory<XmlUnsignedShort> xmlObjectFactory = new XmlObjectFactory<>("_BI_unsignedShort");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
