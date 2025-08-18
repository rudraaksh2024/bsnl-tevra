package org.apache.poi.openxml4j.exceptions;

public class OpenXML4JRuntimeException extends RuntimeException {
    public OpenXML4JRuntimeException(String str) {
        super(str);
    }

    public OpenXML4JRuntimeException(String str, Throwable th) {
        super(str, th);
    }
}
