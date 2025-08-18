package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ProviderUtil;

public final class ThreadContextMapFactory {
    private static final String GC_FREE_THREAD_CONTEXT_KEY = "log4j2.garbagefree.threadContextMap";
    private static boolean GcFreeThreadContextKey = false;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final String THREAD_CONTEXT_KEY = "log4j2.threadContextMap";
    private static String ThreadContextMapName;

    static {
        initPrivate();
    }

    public static void init() {
        CopyOnWriteSortedArrayThreadContextMap.init();
        GarbageFreeSortedArrayThreadContextMap.init();
        DefaultThreadContextMap.init();
        initPrivate();
    }

    private static void initPrivate() {
        PropertiesUtil properties = PropertiesUtil.getProperties();
        ThreadContextMapName = properties.getStringProperty(THREAD_CONTEXT_KEY);
        GcFreeThreadContextKey = properties.getBooleanProperty(GC_FREE_THREAD_CONTEXT_KEY);
    }

    private ThreadContextMapFactory() {
    }

    public static ThreadContextMap createThreadContextMap() {
        Class<? extends ThreadContextMap> loadThreadContextMap;
        ClassLoader findClassLoader = ProviderUtil.findClassLoader();
        String str = ThreadContextMapName;
        ThreadContextMap threadContextMap = null;
        if (str != null) {
            try {
                Class<?> loadClass = findClassLoader.loadClass(str);
                if (ThreadContextMap.class.isAssignableFrom(loadClass)) {
                    threadContextMap = (ThreadContextMap) loadClass.newInstance();
                }
            } catch (ClassNotFoundException unused) {
                LOGGER.error("Unable to locate configured ThreadContextMap {}", (Object) ThreadContextMapName);
            } catch (Exception e) {
                LOGGER.error("Unable to create configured ThreadContextMap {}", (Object) ThreadContextMapName, (Object) e);
            }
        }
        if (threadContextMap == null && ProviderUtil.hasProviders() && LogManager.getFactory() != null) {
            String name = LogManager.getFactory().getClass().getName();
            for (Provider next : ProviderUtil.getProviders()) {
                if (name.equals(next.getClassName()) && (loadThreadContextMap = next.loadThreadContextMap()) != null) {
                    try {
                        threadContextMap = (ThreadContextMap) loadThreadContextMap.newInstance();
                        break;
                    } catch (Exception e2) {
                        LOGGER.error("Unable to locate or load configured ThreadContextMap {}", (Object) next.getThreadContextMap(), (Object) e2);
                        threadContextMap = createDefaultThreadContextMap();
                    }
                }
            }
        }
        return threadContextMap == null ? createDefaultThreadContextMap() : threadContextMap;
    }

    private static ThreadContextMap createDefaultThreadContextMap() {
        if (!Constants.ENABLE_THREADLOCALS) {
            return new DefaultThreadContextMap(true);
        }
        if (GcFreeThreadContextKey) {
            return new GarbageFreeSortedArrayThreadContextMap();
        }
        return new CopyOnWriteSortedArrayThreadContextMap();
    }
}
