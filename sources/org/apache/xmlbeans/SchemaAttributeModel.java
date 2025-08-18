package org.apache.xmlbeans;

import javax.xml.namespace.QName;

public interface SchemaAttributeModel {
    public static final int LAX = 2;
    public static final int NONE = 0;
    public static final int SKIP = 3;
    public static final int STRICT = 1;

    SchemaLocalAttribute getAttribute(QName qName);

    SchemaLocalAttribute[] getAttributes();

    int getWildcardProcess();

    QNameSet getWildcardSet();
}
