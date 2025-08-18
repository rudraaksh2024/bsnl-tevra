package org.apache.xmlbeans;

import java.math.BigDecimal;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlDecimal extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDecimal> Factory;
    public static final SchemaType type;

    BigDecimal getBigDecimalValue();

    void setBigDecimalValue(BigDecimal bigDecimal);

    static {
        XmlObjectFactory<XmlDecimal> xmlObjectFactory = new XmlObjectFactory<>("_BI_decimal");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
