package org.apache.xmlbeans;

import java.math.BigInteger;
import javax.xml.namespace.QName;

public interface SchemaField {
    String getDefaultText();

    XmlAnySimpleType getDefaultValue();

    BigInteger getMaxOccurs();

    BigInteger getMinOccurs();

    QName getName();

    SchemaType getType();

    Object getUserData();

    boolean isAttribute();

    boolean isDefault();

    boolean isFixed();

    boolean isNillable();
}
