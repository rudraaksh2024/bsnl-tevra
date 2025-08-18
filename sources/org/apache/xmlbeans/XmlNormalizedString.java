package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlNormalizedString extends XmlString {
    public static final XmlObjectFactory<XmlNormalizedString> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlNormalizedString> xmlObjectFactory = new XmlObjectFactory<>("_BI_normalizedString");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
