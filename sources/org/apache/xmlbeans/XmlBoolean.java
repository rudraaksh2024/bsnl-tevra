package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlBoolean extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlBoolean> Factory = new XmlObjectFactory<>("_BI_boolean");
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_boolean");

    boolean getBooleanValue();

    void setBooleanValue(boolean z);
}
