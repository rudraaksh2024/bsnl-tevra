package org.apache.logging.log4j.internal;

public class LogManagerStatus {
    private static boolean initialized = false;

    public static void setInitialized(boolean z) {
        initialized = z;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
