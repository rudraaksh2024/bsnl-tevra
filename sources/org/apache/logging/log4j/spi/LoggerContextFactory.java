package org.apache.logging.log4j.spi;

import java.net.URI;

public interface LoggerContextFactory {
    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z);

    LoggerContext getContext(String str, ClassLoader classLoader, Object obj, boolean z, URI uri, String str2);

    boolean hasContext(String str, ClassLoader classLoader, boolean z) {
        return false;
    }

    boolean isClassLoaderDependent() {
        return true;
    }

    void removeContext(LoggerContext loggerContext);

    void shutdown(String str, ClassLoader classLoader, boolean z, boolean z2) {
        if (hasContext(str, classLoader, z)) {
            LoggerContext context = getContext(str, classLoader, (Object) null, z);
            if (context instanceof Terminable) {
                ((Terminable) context).terminate();
            }
        }
    }
}
