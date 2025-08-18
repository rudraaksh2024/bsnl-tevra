package org.apache.xmlbeans.xml.stream;

public interface StartDocument extends XMLEvent {
    String getCharacterEncodingScheme();

    String getSystemId();

    String getVersion();

    boolean isStandalone();
}
