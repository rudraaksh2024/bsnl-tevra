package org.apache.xmlbeans.xml.stream;

public interface ChangePrefixMapping extends XMLEvent {
    String getNewNamespaceUri();

    String getOldNamespaceUri();

    String getPrefix();
}
