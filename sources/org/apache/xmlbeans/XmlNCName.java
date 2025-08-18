package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlNCName extends XmlName {
    public static final XmlObjectFactory<XmlNCName> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlNCName> xmlObjectFactory = new XmlObjectFactory<>("_BI_NCName");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
