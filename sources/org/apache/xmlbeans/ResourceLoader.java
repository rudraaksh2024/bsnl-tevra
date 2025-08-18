package org.apache.xmlbeans;

import java.io.InputStream;

public interface ResourceLoader {
    void close();

    InputStream getResourceAsStream(String str);
}
