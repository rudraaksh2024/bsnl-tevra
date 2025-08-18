package org.apache.logging.log4j.util;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

final class PrivateSecurityManagerStackTraceUtil {
    private static final PrivateSecurityManager SECURITY_MANAGER;

    static {
        PrivateSecurityManager privateSecurityManager = null;
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkPermission(new RuntimePermission("createSecurityManager"));
            }
            privateSecurityManager = new PrivateSecurityManager();
        } catch (SecurityException unused) {
        }
        SECURITY_MANAGER = privateSecurityManager;
    }

    private PrivateSecurityManagerStackTraceUtil() {
    }

    static boolean isEnabled() {
        return SECURITY_MANAGER != null;
    }

    static Deque<Class<?>> getCurrentStackTrace() {
        Class[] classContext = SECURITY_MANAGER.getClassContext();
        ArrayDeque arrayDeque = new ArrayDeque(classContext.length);
        Collections.addAll(arrayDeque, classContext);
        return arrayDeque;
    }

    private static final class PrivateSecurityManager extends SecurityManager {
        private PrivateSecurityManager() {
        }

        /* access modifiers changed from: protected */
        public Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }
}
