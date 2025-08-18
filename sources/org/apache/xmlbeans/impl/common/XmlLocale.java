package org.apache.xmlbeans.impl.common;

public interface XmlLocale {
    void enter();

    void exit();

    boolean noSync();

    boolean sync();
}
