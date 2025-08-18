package org.apache.logging.log4j.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

public final class ProviderUtil {
    private static final String API_VERSION = "Log4jAPIVersion";
    private static final String[] COMPATIBLE_API_VERSIONS = {"2.6.0"};
    private static final Logger LOGGER = StatusLogger.getLogger();
    protected static final Collection<Provider> PROVIDERS = new HashSet();
    protected static final String PROVIDER_RESOURCE = "META-INF/log4j-provider.properties";
    protected static final Lock STARTUP_LOCK = new ReentrantLock();
    private static volatile ProviderUtil instance;

    private ProviderUtil() {
        for (ClassLoader classLoader : LoaderUtil.getClassLoaders()) {
            try {
                loadProviders(classLoader);
            } catch (Throwable th) {
                LOGGER.debug("Unable to retrieve provider from ClassLoader {}", (Object) classLoader, th);
            }
        }
        for (LoaderUtil.UrlResource next : LoaderUtil.findUrlResources(PROVIDER_RESOURCE)) {
            loadProvider(next.getUrl(), next.getClassLoader());
        }
    }

    protected static void addProvider(Provider provider) {
        PROVIDERS.add(provider);
        LOGGER.debug("Loaded Provider {}", (Object) provider);
    }

    protected static void loadProvider(URL url, ClassLoader classLoader) {
        try {
            Properties loadClose = PropertiesUtil.loadClose(url.openStream(), url);
            if (validVersion(loadClose.getProperty(API_VERSION))) {
                Provider provider = new Provider(loadClose, url, classLoader);
                PROVIDERS.add(provider);
                LOGGER.debug("Loaded Provider {}", (Object) provider);
            }
        } catch (IOException e) {
            LOGGER.error("Unable to open {}", (Object) url, (Object) e);
        }
    }

    protected static void loadProviders(ClassLoader classLoader) {
        Iterator<S> it = ServiceLoader.load(Provider.class, classLoader).iterator();
        while (it.hasNext()) {
            Provider provider = (Provider) it.next();
            if (validVersion(provider.getVersions())) {
                Collection<Provider> collection = PROVIDERS;
                if (!collection.contains(provider)) {
                    collection.add(provider);
                }
            }
        }
    }

    @Deprecated
    protected static void loadProviders(Enumeration<URL> enumeration, ClassLoader classLoader) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                loadProvider(enumeration.nextElement(), classLoader);
            }
        }
    }

    public static Iterable<Provider> getProviders() {
        lazyInit();
        return PROVIDERS;
    }

    public static boolean hasProviders() {
        lazyInit();
        return !PROVIDERS.isEmpty();
    }

    protected static void lazyInit() {
        if (instance == null) {
            try {
                Lock lock = STARTUP_LOCK;
                lock.lockInterruptibly();
                if (instance == null) {
                    instance = new ProviderUtil();
                }
                lock.unlock();
            } catch (InterruptedException e) {
                LOGGER.fatal("Interrupted before Log4j Providers could be loaded.", (Throwable) e);
                Thread.currentThread().interrupt();
            } catch (Throwable th) {
                STARTUP_LOCK.unlock();
                throw th;
            }
        }
    }

    public static ClassLoader findClassLoader() {
        return LoaderUtil.getThreadContextClassLoader();
    }

    private static boolean validVersion(String str) {
        for (String startsWith : COMPATIBLE_API_VERSIONS) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}
