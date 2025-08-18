package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlNMTOKEN extends XmlToken {
    public static final XmlObjectFactory<XmlNMTOKEN> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlNMTOKEN> xmlObjectFactory = new XmlObjectFactory<>("_BI_NMTOKEN");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
