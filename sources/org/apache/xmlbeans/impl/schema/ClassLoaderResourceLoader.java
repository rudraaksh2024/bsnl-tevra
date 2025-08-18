package org.apache.xmlbeans.impl.schema;

import java.io.InputStream;
import org.apache.xmlbeans.ResourceLoader;

public class ClassLoaderResourceLoader implements ResourceLoader {
    ClassLoader _classLoader;

    public void close() {
    }

    public ClassLoaderResourceLoader(ClassLoader classLoader) {
        this._classLoader = classLoader;
    }

    public InputStream getResourceAsStream(String str) {
        return this._classLoader.getResourceAsStream(str);
    }
}
