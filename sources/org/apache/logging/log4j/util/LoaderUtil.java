package org.apache.logging.log4j.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Objects;

public final class LoaderUtil {
    private static final ClassLoader[] EMPTY_CLASS_LOADER_ARRAY = new ClassLoader[0];
    /* access modifiers changed from: private */
    public static final boolean GET_CLASS_LOADER_DISABLED;
    public static final String IGNORE_TCCL_PROPERTY = "log4j.ignoreTCL";
    private static final SecurityManager SECURITY_MANAGER;
    private static final PrivilegedAction<ClassLoader> TCCL_GETTER = new ThreadContextClassLoaderGetter();
    private static Boolean ignoreTCCL;

    static {
        boolean z = false;
        SecurityManager securityManager = System.getSecurityManager();
        SECURITY_MANAGER = securityManager;
        if (securityManager != null) {
            try {
                securityManager.checkPermission(new RuntimePermission("getClassLoader"));
            } catch (SecurityException unused) {
                z = true;
            }
            GET_CLASS_LOADER_DISABLED = z;
            return;
        }
        GET_CLASS_LOADER_DISABLED = false;
    }

    private LoaderUtil() {
    }

    public static ClassLoader getThreadContextClassLoader() {
        if (GET_CLASS_LOADER_DISABLED) {
            return LoaderUtil.class.getClassLoader();
        }
        return (ClassLoader) (SECURITY_MANAGER == null ? TCCL_GETTER.run() : AccessController.doPrivileged(TCCL_GETTER));
    }

    private static class ThreadContextClassLoaderGetter implements PrivilegedAction<ClassLoader> {
        private ThreadContextClassLoaderGetter() {
        }

        public ClassLoader run() {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                return contextClassLoader;
            }
            ClassLoader classLoader = LoaderUtil.class.getClassLoader();
            return (classLoader != null || LoaderUtil.GET_CLASS_LOADER_DISABLED) ? classLoader : ClassLoader.getSystemClassLoader();
        }
    }

    public static ClassLoader[] getClassLoaders() {
        ClassLoader classLoader;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ClassLoader threadContextClassLoader = getThreadContextClassLoader();
        if (threadContextClassLoader != null) {
            linkedHashSet.add(threadContextClassLoader);
        }
        accumulateClassLoaders(LoaderUtil.class.getClassLoader(), linkedHashSet);
        if (threadContextClassLoader == null) {
            classLoader = null;
        } else {
            classLoader = threadContextClassLoader.getParent();
        }
        accumulateClassLoaders(classLoader, linkedHashSet);
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        if (systemClassLoader != null) {
            linkedHashSet.add(systemClassLoader);
        }
        return (ClassLoader[]) linkedHashSet.toArray(EMPTY_CLASS_LOADER_ARRAY);
    }

    private static void accumulateClassLoaders(ClassLoader classLoader, Collection<ClassLoader> collection) {
        if (classLoader != null && collection.add(classLoader)) {
            accumulateClassLoaders(classLoader.getParent(), collection);
        }
    }

    public static boolean isClassAvailable(String str) {
        try {
            return loadClass(str) != null;
        } catch (ClassNotFoundException | LinkageError unused) {
            return false;
        } catch (Throwable th) {
            LowLevelLogUtil.logException("Unknown error checking for existence of class: " + str, th);
            return false;
        }
    }

    public static Class<?> loadClass(String str) throws ClassNotFoundException {
        if (isIgnoreTccl()) {
            return Class.forName(str);
        }
        try {
            ClassLoader threadContextClassLoader = getThreadContextClassLoader();
            if (threadContextClassLoader != null) {
                return threadContextClassLoader.loadClass(str);
            }
        } catch (Throwable unused) {
        }
        return Class.forName(str);
    }

    public static <T> T newInstanceOf(Class<T> cls) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            return cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (NoSuchMethodException unused) {
            return cls.newInstance();
        }
    }

    public static <T> T newInstanceOf(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return newInstanceOf(loadClass(str));
    }

    public static <T> T newCheckedInstanceOf(String str, Class<T> cls) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return cls.cast(newInstanceOf(str));
    }

    public static <T> T newCheckedInstanceOfProperty(String str, Class<T> cls) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(str);
        if (stringProperty == null) {
            return null;
        }
        return newCheckedInstanceOf(stringProperty, cls);
    }

    private static boolean isIgnoreTccl() {
        if (ignoreTCCL == null) {
            String stringProperty = PropertiesUtil.getProperties().getStringProperty(IGNORE_TCCL_PROPERTY, (String) null);
            ignoreTCCL = Boolean.valueOf(stringProperty != null && !"false".equalsIgnoreCase(stringProperty.trim()));
        }
        return ignoreTCCL.booleanValue();
    }

    public static Collection<URL> findResources(String str) {
        Collection<UrlResource> findUrlResources = findUrlResources(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet(findUrlResources.size());
        for (UrlResource url : findUrlResources) {
            linkedHashSet.add(url.getUrl());
        }
        return linkedHashSet;
    }

    static Collection<UrlResource> findUrlResources(String str) {
        ClassLoader classLoader;
        ClassLoader[] classLoaderArr = new ClassLoader[3];
        classLoaderArr[0] = getThreadContextClassLoader();
        classLoaderArr[1] = LoaderUtil.class.getClassLoader();
        if (GET_CLASS_LOADER_DISABLED) {
            classLoader = null;
        } else {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        classLoaderArr[2] = classLoader;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < 3; i++) {
            ClassLoader classLoader2 = classLoaderArr[i];
            if (classLoader2 != null) {
                try {
                    Enumeration<URL> resources = classLoader2.getResources(str);
                    while (resources.hasMoreElements()) {
                        linkedHashSet.add(new UrlResource(classLoader2, resources.nextElement()));
                    }
                } catch (IOException e) {
                    LowLevelLogUtil.logException(e);
                }
            }
        }
        return linkedHashSet;
    }

    static class UrlResource {
        private final ClassLoader classLoader;
        private final URL url;

        UrlResource(ClassLoader classLoader2, URL url2) {
            this.classLoader = classLoader2;
            this.url = url2;
        }

        public ClassLoader getClassLoader() {
            return this.classLoader;
        }

        public URL getUrl() {
            return this.url;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UrlResource urlResource = (UrlResource) obj;
            ClassLoader classLoader2 = this.classLoader;
            if (classLoader2 == null ? urlResource.classLoader != null : !classLoader2.equals(urlResource.classLoader)) {
                return false;
            }
            URL url2 = this.url;
            return url2 == null ? urlResource.url == null : url2.equals(urlResource.url);
        }

        public int hashCode() {
            return Objects.hashCode(this.classLoader) + Objects.hashCode(this.url);
        }
    }
}
