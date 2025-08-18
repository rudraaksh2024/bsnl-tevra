package org.apache.logging.log4j.util;

public final class Chars {
    public static final char CR = '\r';
    public static final char DQUOTE = '\"';
    public static final char EQ = '=';
    public static final char LF = '\n';
    public static final char NUL = '\u0000';
    public static final char QUOTE = '\'';
    public static final char SPACE = ' ';
    public static final char TAB = '\t';

    private static char getLowerCaseAlphaDigit(int i) {
        return (char) ((i + 97) - 10);
    }

    private static char getNumericalDigit(int i) {
        return (char) (i + 48);
    }

    private static char getUpperCaseAlphaDigit(int i) {
        return (char) ((i + 65) - 10);
    }

    public static char getUpperCaseHex(int i) {
        if (i < 0 || i >= 16) {
            return 0;
        }
        return i < 10 ? getNumericalDigit(i) : getUpperCaseAlphaDigit(i);
    }

    public static char getLowerCaseHex(int i) {
        if (i < 0 || i >= 16) {
            return 0;
        }
        return i < 10 ? getNumericalDigit(i) : getLowerCaseAlphaDigit(i);
    }

    private Chars() {
    }
}
