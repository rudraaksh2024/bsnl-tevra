package org.apache.xmlbeans.impl.common;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;

public class SystemCache {
    private static SystemCache INSTANCE;
    private ThreadLocal<SoftReference> tl_saxLoaders = new ThreadLocal<>();

    public void addToTypeLoaderCache(SchemaTypeLoader schemaTypeLoader, ClassLoader classLoader) {
    }

    public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader classLoader) {
        return null;
    }

    static {
        Object obj;
        INSTANCE = new SystemCache();
        String property = SystemProperties.getProperty("xmlbean.systemcacheimpl");
        if (property != null) {
            try {
                obj = Class.forName(property).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (!(obj instanceof SystemCache)) {
                    throw new ClassCastException("Value for system property \"xmlbean.systemcacheimpl\" points to a class (" + property + ") which does not derive from SystemCache");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cache class " + property + " specified by \"xmlbean.systemcacheimpl\" was not found.", e);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
                throw new RuntimeException("Could not instantiate class " + property + " as specified by \"xmlbean.systemcacheimpl\". An empty constructor may be missing.", e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Could not instantiate class " + property + " as specified by \"xmlbean.systemcacheimpl\". A public empty constructor may be missing.", e3);
            }
        } else {
            obj = null;
        }
        if (obj != null) {
            INSTANCE = (SystemCache) obj;
        }
    }

    public static synchronized void set(SystemCache systemCache) {
        synchronized (SystemCache.class) {
            INSTANCE = systemCache;
        }
    }

    public static SystemCache get() {
        return INSTANCE;
    }

    public void clearThreadLocals() {
        this.tl_saxLoaders.remove();
    }

    public Object getSaxLoader() {
        SoftReference softReference = this.tl_saxLoaders.get();
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public void setSaxLoader(Object obj) {
        this.tl_saxLoaders.set(new SoftReference(obj));
    }
}
