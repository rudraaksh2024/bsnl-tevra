package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlFloat extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlFloat> Factory;
    public static final SchemaType type;

    float getFloatValue();

    void setFloatValue(float f);

    static {
        XmlObjectFactory<XmlFloat> xmlObjectFactory = new XmlObjectFactory<>("_BI_float");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
