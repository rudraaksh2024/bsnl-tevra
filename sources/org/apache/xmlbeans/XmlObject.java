package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlObject extends XmlTokenSource {
    public static final int EQUAL = 0;
    public static final XmlObjectFactory<XmlObject> Factory;
    public static final int GREATER_THAN = 1;
    public static final int LESS_THAN = -1;
    public static final int NOT_EQUAL = 2;
    public static final SchemaType type;

    XmlObject changeType(SchemaType schemaType);

    int compareTo(Object obj);

    int compareValue(XmlObject xmlObject);

    XmlObject copy();

    XmlObject copy(XmlOptions xmlOptions);

    XmlObject[] execQuery(String str);

    XmlObject[] execQuery(String str, XmlOptions xmlOptions);

    boolean isImmutable();

    boolean isNil();

    SchemaType schemaType();

    XmlObject selectAttribute(String str, String str2);

    XmlObject selectAttribute(QName qName);

    XmlObject[] selectAttributes(QNameSet qNameSet);

    XmlObject[] selectChildren(String str, String str2);

    XmlObject[] selectChildren(QName qName);

    XmlObject[] selectChildren(QNameSet qNameSet);

    XmlObject[] selectPath(String str);

    XmlObject[] selectPath(String str, XmlOptions xmlOptions);

    XmlObject set(XmlObject xmlObject);

    void setNil();

    XmlObject substitute(QName qName, SchemaType schemaType);

    String toString();

    boolean validate();

    boolean validate(XmlOptions xmlOptions);

    boolean valueEquals(XmlObject xmlObject);

    int valueHashCode();

    static {
        XmlObjectFactory<XmlObject> xmlObjectFactory = new XmlObjectFactory<>("_BI_anyType");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
