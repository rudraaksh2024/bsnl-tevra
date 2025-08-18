package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlIDREF extends XmlNCName {
    public static final XmlObjectFactory<XmlIDREF> Factory;
    public static final SchemaType type;

    static {
        XmlObjectFactory<XmlIDREF> xmlObjectFactory = new XmlObjectFactory<>("_BI_IDREF");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
