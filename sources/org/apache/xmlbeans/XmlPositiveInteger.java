package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlPositiveInteger extends XmlNonNegativeInteger {
    public static final XmlObjectFactory<XmlPositiveInteger> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlPositiveInteger> xmlObjectFactory = new XmlObjectFactory<>("_BI_positiveInteger");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
