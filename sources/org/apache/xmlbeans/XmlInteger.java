package org.apache.xmlbeans;

import java.math.BigInteger;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlInteger extends XmlDecimal {
    public static final XmlObjectFactory<XmlInteger> Factory;
    public static final SchemaType type;

    BigInteger getBigIntegerValue();

    void setBigIntegerValue(BigInteger bigInteger);

    static {
        XmlObjectFactory<XmlInteger> xmlObjectFactory = new XmlObjectFactory<>("_BI_integer");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
