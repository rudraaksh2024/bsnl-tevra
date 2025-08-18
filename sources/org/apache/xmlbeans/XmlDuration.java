package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlDuration extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDuration> Factory;
    public static final SchemaType type;

    GDuration getGDurationValue();

    void setGDurationValue(GDuration gDuration);

    static {
        XmlObjectFactory<XmlDuration> xmlObjectFactory = new XmlObjectFactory<>("_BI_duration");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
