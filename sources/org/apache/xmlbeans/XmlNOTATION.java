package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlNOTATION extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlNOTATION> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlNOTATION> xmlObjectFactory = new XmlObjectFactory<>("_BI_NOTATION");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
