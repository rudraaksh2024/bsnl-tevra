package org.apache.poi.openxml4j.exceptions;

public class OpenXML4JException extends Exception {
    public OpenXML4JException(String str) {
        super(str);
    }

    public OpenXML4JException(String str, Throwable th) {
        super(str, th);
    }
}
