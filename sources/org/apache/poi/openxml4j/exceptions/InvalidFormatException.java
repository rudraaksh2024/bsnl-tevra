package org.apache.poi.openxml4j.exceptions;

public final class InvalidFormatException extends OpenXML4JException {
    public InvalidFormatException(String str) {
        super(str);
    }

    public InvalidFormatException(String str, Throwable th) {
        super(str, th);
    }
}
