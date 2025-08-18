package org.apache.xmlbeans.impl.common;

import java.io.InputStream;
import org.apache.xmlbeans.ResourceLoader;

public class DefaultClassLoaderResourceLoader implements ResourceLoader {
    public void close() {
    }

    public InputStream getResourceAsStream(String str) {
        InputStream inputStream;
        try {
            inputStream = getResourceAsStream(Thread.currentThread().getContextClassLoader(), str);
        } catch (SecurityException unused) {
            inputStream = null;
        }
        Class<DefaultClassLoaderResourceLoader> cls = DefaultClassLoaderResourceLoader.class;
        if (inputStream == null) {
            inputStream = getResourceAsStream(cls.getClassLoader(), str);
        }
        return inputStream == null ? cls.getResourceAsStream(str) : inputStream;
    }

    private InputStream getResourceAsStream(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            return null;
        }
        return classLoader.getResourceAsStream(str);
    }
}
