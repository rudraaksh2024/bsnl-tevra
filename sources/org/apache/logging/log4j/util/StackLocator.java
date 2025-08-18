package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;

public final class StackLocator {
    private static final Class<?> DEFAULT_CALLER_CLASS = null;
    private static final Method GET_CALLER_CLASS_METHOD;
    private static final StackLocator INSTANCE = new StackLocator();
    static final int JDK_7U25_OFFSET;

    static {
        int i = -1;
        Method method = null;
        try {
            Class<?> loadClass = LoaderUtil.loadClass("sun.reflect.Reflection");
            Method declaredMethod = loadClass.getDeclaredMethod("getCallerClass", new Class[]{Integer.TYPE});
            Object invoke = declaredMethod.invoke((Object) null, new Object[]{0});
            declaredMethod.invoke((Object) null, new Object[]{0});
            if (invoke != null) {
                if (invoke == loadClass) {
                    if (declaredMethod.invoke((Object) null, new Object[]{1}) == loadClass) {
                        System.out.println("WARNING: Unexpected result from sun.reflect.Reflection.getCallerClass(int), adjusting offset for future calls.");
                        method = declaredMethod;
                        i = 1;
                    } else {
                        method = declaredMethod;
                        i = 0;
                    }
                }
            }
        } catch (Exception | LinkageError unused) {
            System.out.println("WARNING: sun.reflect.Reflection.getCallerClass is not supported. This will impact performance.");
        }
        GET_CALLER_CLASS_METHOD = method;
        JDK_7U25_OFFSET = i;
    }

    public static StackLocator getInstance() {
        return INSTANCE;
    }

    private StackLocator() {
    }

    public Class<?> getCallerClass(Class<?> cls, Predicate<Class<?>> predicate) {
        if (cls == null) {
            throw new IllegalArgumentException("sentinelClass cannot be null");
        } else if (predicate != null) {
            boolean z = false;
            int i = 2;
            while (true) {
                Class<?> callerClass = getCallerClass(i);
                if (callerClass == null) {
                    return DEFAULT_CALLER_CLASS;
                }
                if (cls.equals(callerClass)) {
                    z = true;
                } else if (z && predicate.test(callerClass)) {
                    return callerClass;
                }
                i++;
            }
        } else {
            throw new IllegalArgumentException("callerPredicate cannot be null");
        }
    }

    public Class<?> getCallerClass(int i) {
        if (i >= 0) {
            Method method = GET_CALLER_CLASS_METHOD;
            if (method == null) {
                return DEFAULT_CALLER_CLASS;
            }
            try {
                return (Class) method.invoke((Object) null, new Object[]{Integer.valueOf(i + 1 + JDK_7U25_OFFSET)});
            } catch (Exception unused) {
                return DEFAULT_CALLER_CLASS;
            }
        } else {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
    }

    public Class<?> getCallerClass(String str, String str2) {
        boolean z = false;
        int i = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass == null) {
                return DEFAULT_CALLER_CLASS;
            }
            if (str.equals(callerClass.getName())) {
                z = true;
            } else if (z && callerClass.getName().startsWith(str2)) {
                return callerClass;
            }
            i++;
        }
    }

    public Class<?> getCallerClass(Class<?> cls) {
        boolean z = false;
        int i = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass == null) {
                return Object.class;
            }
            if (cls.equals(callerClass)) {
                z = true;
            } else if (z) {
                return callerClass;
            }
            i++;
        }
    }

    public Deque<Class<?>> getCurrentStackTrace() {
        if (PrivateSecurityManagerStackTraceUtil.isEnabled()) {
            return PrivateSecurityManagerStackTraceUtil.getCurrentStackTrace();
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i = 1;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass == null) {
                return arrayDeque;
            }
            arrayDeque.push(callerClass);
            i++;
        }
    }

    public StackTraceElement calcLocation(String str) {
        if (str == null) {
            return null;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        boolean z = false;
        for (int i = 0; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (str.equals(className)) {
                z = true;
            } else if (z && !str.equals(className)) {
                return stackTrace[i];
            }
        }
        return null;
    }

    public StackTraceElement getStackTraceElement(int i) {
        int i2 = 0;
        for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
            if (isValid(stackTraceElement)) {
                if (i2 == i) {
                    return stackTraceElement;
                }
                i2++;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(i));
    }

    private boolean isValid(StackTraceElement stackTraceElement) {
        if (stackTraceElement.isNativeMethod()) {
            return false;
        }
        String className = stackTraceElement.getClassName();
        if (className.startsWith("sun.reflect.")) {
            return false;
        }
        String methodName = stackTraceElement.getMethodName();
        if ((className.startsWith("java.lang.reflect.") && (methodName.equals("invoke") || methodName.equals("newInstance"))) || className.startsWith("jdk.internal.reflect.")) {
            return false;
        }
        if (className.equals("java.lang.Class") && methodName.equals("newInstance")) {
            return false;
        }
        if (!className.equals("java.lang.invoke.MethodHandle") || !methodName.startsWith("invoke")) {
            return true;
        }
        return false;
    }
}
