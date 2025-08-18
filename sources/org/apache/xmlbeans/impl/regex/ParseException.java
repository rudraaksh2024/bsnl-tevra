package org.apache.xmlbeans.impl.regex;

public class ParseException extends RuntimeException {
    int location;

    public ParseException(String str, int i) {
        super(str);
        this.location = i;
    }

    public int getLocation() {
        return this.location;
    }
}
