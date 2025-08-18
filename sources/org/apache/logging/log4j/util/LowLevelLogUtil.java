package org.apache.logging.log4j.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Objects;

final class LowLevelLogUtil {
    private static PrintWriter writer = new PrintWriter(System.err, true);

    public static void log(String str) {
        if (str != null) {
            writer.println(str);
        }
    }

    public static void logException(Throwable th) {
        if (th != null) {
            th.printStackTrace(writer);
        }
    }

    public static void logException(String str, Throwable th) {
        log(str);
        logException(th);
    }

    public static void setOutputStream(OutputStream outputStream) {
        writer = new PrintWriter((OutputStream) Objects.requireNonNull(outputStream), true);
    }

    public static void setWriter(Writer writer2) {
        writer = new PrintWriter((Writer) Objects.requireNonNull(writer2), true);
    }

    private LowLevelLogUtil() {
    }
}
