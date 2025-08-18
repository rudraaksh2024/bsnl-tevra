package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlToken extends XmlNormalizedString {
    public static final XmlObjectFactory<XmlToken> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlToken> xmlObjectFactory = new XmlObjectFactory<>("_BI_token");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
