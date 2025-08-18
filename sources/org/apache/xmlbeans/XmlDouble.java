package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlDouble extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDouble> Factory;
    public static final SchemaType type;

    double getDoubleValue();

    void setDoubleValue(double d);

    static {
        XmlObjectFactory<XmlDouble> xmlObjectFactory = new XmlObjectFactory<>("_BI_double");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
