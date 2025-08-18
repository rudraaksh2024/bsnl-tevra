package org.apache.logging.log4j.util;

public final class Constants {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final boolean ENABLE_THREADLOCALS;
    public static final boolean IS_WEB_APP;
    public static final int JAVA_MAJOR_VERSION = getMajorVersion();
    public static final String LOG4J2_DEBUG = "log4j2.debug";
    public static final int MAX_REUSABLE_MESSAGE_SIZE = size("log4j.maxReusableMsgSize", 518);

    static {
        boolean z = true;
        boolean booleanProperty = PropertiesUtil.getProperties().getBooleanProperty("log4j2.is.webapp", isClassAvailable("javax.servlet.Servlet") || isClassAvailable("jakarta.servlet.Servlet"));
        IS_WEB_APP = booleanProperty;
        if (booleanProperty || !PropertiesUtil.getProperties().getBooleanProperty("log4j2.enable.threadlocals", true)) {
            z = false;
        }
        ENABLE_THREADLOCALS = z;
    }

    private static int size(String str, int i) {
        return PropertiesUtil.getProperties().getIntegerProperty(str, i);
    }

    private static boolean isClassAvailable(String str) {
        try {
            return LoaderUtil.loadClass(str) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Constants() {
    }

    private static int getMajorVersion() {
        return getMajorVersion(System.getProperty("java.version"));
    }

    static int getMajorVersion(String str) {
        String[] split = str.split("-|\\.");
        try {
            int parseInt = Integer.parseInt(split[0]);
            if (parseInt != 1) {
                return parseInt;
            }
            return Integer.parseInt(split[1]);
        } catch (Exception unused) {
            return 0;
        }
    }
}
