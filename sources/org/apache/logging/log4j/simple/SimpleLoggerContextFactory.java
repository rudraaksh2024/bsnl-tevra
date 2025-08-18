package org.apache.logging.log4j.simple;

import java.net.URI;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

public class SimpleLoggerContextFactory implements LoggerContextFactory {
    public static final SimpleLoggerContextFactory INSTANCE = new SimpleLoggerContextFactory();

    public boolean isClassLoaderDependent() {
        return false;
    }

    public void removeContext(LoggerContext loggerContext) {
    }

    public LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z) {
        return SimpleLoggerContext.INSTANCE;
    }

    public LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI uri, String str2) {
        return SimpleLoggerContext.INSTANCE;
    }
}
