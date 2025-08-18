package org.apache.poi.openxml4j.exceptions;

public class InvalidOperationException extends OpenXML4JRuntimeException {
    public InvalidOperationException(String str) {
        super(str);
    }

    public InvalidOperationException(String str, Throwable th) {
        super(str, th);
    }
}
