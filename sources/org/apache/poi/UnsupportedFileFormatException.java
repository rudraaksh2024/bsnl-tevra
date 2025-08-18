package org.apache.poi;

public abstract class UnsupportedFileFormatException extends IllegalArgumentException {
    private static final long serialVersionUID = -8281969197282030046L;

    protected UnsupportedFileFormatException(String str) {
        super(str);
    }

    protected UnsupportedFileFormatException(String str, Throwable th) {
        super(str, th);
    }
}
