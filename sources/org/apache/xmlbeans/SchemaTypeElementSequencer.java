package org.apache.xmlbeans;

import javax.xml.namespace.QName;

public interface SchemaTypeElementSequencer {
    boolean next(QName qName);

    boolean peek(QName qName);
}
