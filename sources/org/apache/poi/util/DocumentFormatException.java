package org.apache.poi.util;

public class DocumentFormatException extends RuntimeException {
    public DocumentFormatException(String str) {
        super(str);
    }

    public DocumentFormatException(String str, Throwable th) {
        super(str, th);
    }

    public DocumentFormatException(Throwable th) {
        super(th);
    }

    public static void check(boolean z, String str) {
        if (!z) {
            throw new DocumentFormatException(str);
        }
    }
}
