package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFactory;

public interface LoggerContext {
    public static final LoggerContext[] EMPTY_ARRAY = new LoggerContext[0];

    Object getExternalContext();

    ExtendedLogger getLogger(String str);

    ExtendedLogger getLogger(String str, MessageFactory messageFactory);

    LoggerRegistry<? extends Logger> getLoggerRegistry() {
        return null;
    }

    Object getObject(String str) {
        return null;
    }

    boolean hasLogger(String str);

    boolean hasLogger(String str, Class<? extends MessageFactory> cls);

    boolean hasLogger(String str, MessageFactory messageFactory);

    Object putObject(String str, Object obj) {
        return null;
    }

    Object putObjectIfAbsent(String str, Object obj) {
        return null;
    }

    Object removeObject(String str) {
        return null;
    }

    boolean removeObject(String str, Object obj) {
        return false;
    }

    ExtendedLogger getLogger(Class<?> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = cls.getName();
        }
        return getLogger(canonicalName);
    }

    ExtendedLogger getLogger(Class<?> cls, MessageFactory messageFactory) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = cls.getName();
        }
        return getLogger(canonicalName, messageFactory);
    }
}
