package org.apache.poi.util;

public class RecordFormatException extends RuntimeException {
    public RecordFormatException(String str) {
        super(str);
    }

    public RecordFormatException(String str, Throwable th) {
        super(str, th);
    }

    public RecordFormatException(Throwable th) {
        super(th);
    }

    public static void check(boolean z, String str) {
        if (!z) {
            throw new RecordFormatException(str);
        }
    }
}
