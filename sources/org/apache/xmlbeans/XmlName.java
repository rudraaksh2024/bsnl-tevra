package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlName extends XmlToken {
    public static final XmlObjectFactory<XmlName> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlName> xmlObjectFactory = new XmlObjectFactory<>("_BI_Name");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
