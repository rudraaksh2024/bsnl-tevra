package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlID extends XmlNCName {
    public static final XmlObjectFactory<XmlID> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlID> xmlObjectFactory = new XmlObjectFactory<>("_BI_ID");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
