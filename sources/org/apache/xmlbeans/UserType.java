package org.apache.xmlbeans;

import javax.xml.namespace.QName;

public interface UserType {
    String getJavaName();

    QName getName();

    String getStaticHandler();
}
