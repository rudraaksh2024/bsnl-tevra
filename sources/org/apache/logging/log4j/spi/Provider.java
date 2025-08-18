package org.apache.logging.log4j.spi;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

public class Provider {
    private static final Integer DEFAULT_PRIORITY = -1;
    public static final String FACTORY_PRIORITY = "FactoryPriority";
    private static final Logger LOGGER = StatusLogger.getLogger();
    public static final String LOGGER_CONTEXT_FACTORY = "LoggerContextFactory";
    public static final String THREAD_CONTEXT_MAP = "ThreadContextMap";
    private final WeakReference<ClassLoader> classLoader;
    private final String className;
    private final Class<? extends LoggerContextFactory> loggerContextFactoryClass;
    private final Integer priority;
    private final String threadContextMap;
    private final Class<? extends ThreadContextMap> threadContextMapClass;
    private final URL url;
    private final String versions;

    public Provider(Properties properties, URL url2, ClassLoader classLoader2) {
        this.url = url2;
        this.classLoader = new WeakReference<>(classLoader2);
        String property = properties.getProperty(FACTORY_PRIORITY);
        this.priority = property == null ? DEFAULT_PRIORITY : Integer.valueOf(property);
        this.className = properties.getProperty(LOGGER_CONTEXT_FACTORY);
        this.threadContextMap = properties.getProperty(THREAD_CONTEXT_MAP);
        this.loggerContextFactoryClass = null;
        this.threadContextMapClass = null;
        this.versions = null;
    }

    public Provider(Integer num, String str, Class<? extends LoggerContextFactory> cls) {
        this(num, str, cls, (Class<? extends ThreadContextMap>) null);
    }

    public Provider(Integer num, String str, Class<? extends LoggerContextFactory> cls, Class<? extends ThreadContextMap> cls2) {
        this.url = null;
        this.classLoader = null;
        this.priority = num;
        this.loggerContextFactoryClass = cls;
        this.threadContextMapClass = cls2;
        this.className = null;
        this.threadContextMap = null;
        this.versions = str;
    }

    public String getVersions() {
        return this.versions;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public String getClassName() {
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls != null) {
            return cls.getName();
        }
        return this.className;
    }

    public Class<? extends LoggerContextFactory> loadLoggerContextFactory() {
        ClassLoader classLoader2;
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls != null) {
            return cls;
        }
        if (this.className == null || (classLoader2 = (ClassLoader) this.classLoader.get()) == null) {
            return null;
        }
        try {
            Class<?> loadClass = classLoader2.loadClass(this.className);
            if (LoggerContextFactory.class.isAssignableFrom(loadClass)) {
                return loadClass.asSubclass(LoggerContextFactory.class);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", (Object) this.className, (Object) this.url.toString(), (Object) e);
        }
        return null;
    }

    public String getThreadContextMap() {
        Class<? extends ThreadContextMap> cls = this.threadContextMapClass;
        if (cls != null) {
            return cls.getName();
        }
        return this.threadContextMap;
    }

    public Class<? extends ThreadContextMap> loadThreadContextMap() {
        ClassLoader classLoader2;
        Class<? extends ThreadContextMap> cls = this.threadContextMapClass;
        if (cls != null) {
            return cls;
        }
        if (this.threadContextMap == null || (classLoader2 = (ClassLoader) this.classLoader.get()) == null) {
            return null;
        }
        try {
            Class<?> loadClass = classLoader2.loadClass(this.threadContextMap);
            if (ThreadContextMap.class.isAssignableFrom(loadClass)) {
                return loadClass.asSubclass(ThreadContextMap.class);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", (Object) this.threadContextMap, (Object) this.url.toString(), (Object) e);
        }
        return null;
    }

    public URL getUrl() {
        return this.url;
    }

    public String toString() {
        ClassLoader classLoader2;
        StringBuilder sb = new StringBuilder("Provider[");
        if (!DEFAULT_PRIORITY.equals(this.priority)) {
            sb.append("priority=").append(this.priority).append(", ");
        }
        if (this.threadContextMap != null) {
            sb.append("threadContextMap=").append(this.threadContextMap).append(", ");
        } else if (this.threadContextMapClass != null) {
            sb.append("threadContextMapClass=").append(this.threadContextMapClass.getName());
        }
        if (this.className != null) {
            sb.append("className=").append(this.className).append(", ");
        } else if (this.loggerContextFactoryClass != null) {
            sb.append("class=").append(this.loggerContextFactoryClass.getName());
        }
        if (this.url != null) {
            sb.append("url=").append(this.url);
        }
        WeakReference<ClassLoader> weakReference = this.classLoader;
        if (weakReference == null || (classLoader2 = (ClassLoader) weakReference.get()) == null) {
            sb.append(", classLoader=null(not reachable)");
        } else {
            sb.append(", classLoader=").append(classLoader2);
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Provider provider = (Provider) obj;
        Integer num = this.priority;
        if (num == null ? provider.priority != null : !num.equals(provider.priority)) {
            return false;
        }
        String str = this.className;
        if (str == null ? provider.className != null : !str.equals(provider.className)) {
            return false;
        }
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        if (cls == null ? provider.loggerContextFactoryClass != null : !cls.equals(provider.loggerContextFactoryClass)) {
            return false;
        }
        String str2 = this.versions;
        if (str2 != null) {
            return str2.equals(provider.versions);
        }
        if (provider.versions == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.priority;
        int i = 0;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.className;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Class<? extends LoggerContextFactory> cls = this.loggerContextFactoryClass;
        int hashCode3 = (hashCode2 + (cls != null ? cls.hashCode() : 0)) * 31;
        String str2 = this.versions;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 + i;
    }
}
