package org.apache.xmlbeans.xml.stream;

public interface AttributeIterator {
    boolean hasNext();

    Attribute next();

    Attribute peek();

    void skip();
}
