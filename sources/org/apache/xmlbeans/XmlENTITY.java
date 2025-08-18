package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlENTITY extends XmlNCName {
    public static final XmlObjectFactory<XmlENTITY> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlENTITY> xmlObjectFactory = new XmlObjectFactory<>("_BI_ENTITY");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
