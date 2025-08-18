package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlString extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlString> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlString> xmlObjectFactory = new XmlObjectFactory<>("_BI_string");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
