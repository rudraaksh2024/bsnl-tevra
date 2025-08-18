package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlHexBinary extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlHexBinary> Factory;
    public static final SchemaType type;

    byte[] getByteArrayValue();

    void setByteArrayValue(byte[] bArr);

    static {
        XmlObjectFactory<XmlHexBinary> xmlObjectFactory = new XmlObjectFactory<>("_BI_hexBinary");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
