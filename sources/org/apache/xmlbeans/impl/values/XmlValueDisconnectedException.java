package org.apache.xmlbeans.impl.values;

public class XmlValueDisconnectedException extends RuntimeException {
    XmlValueDisconnectedException() {
    }

    XmlValueDisconnectedException(String str) {
        super(str);
    }

    XmlValueDisconnectedException(String str, Throwable th) {
        super(str, th);
    }
}
