package org.apache.poi.poifs.filesystem;

public class Ole10NativeException extends Exception {
    public Ole10NativeException(String str) {
        super(str);
    }

    public Ole10NativeException(Throwable th) {
        super(th);
    }

    public Ole10NativeException(String str, Throwable th) {
        super(str, th);
    }
}
