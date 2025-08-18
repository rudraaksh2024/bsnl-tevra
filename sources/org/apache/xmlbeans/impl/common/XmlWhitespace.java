package org.apache.xmlbeans.impl.common;

import org.apache.logging.log4j.util.Chars;

public class XmlWhitespace {
    public static final int WS_COLLAPSE = 3;
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_UNSPECIFIED = 0;

    public static boolean isSpace(char c) {
        return c == 9 || c == 10 || c == 13 || c == ' ';
    }

    public static boolean isAllSpace(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isSpace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllSpace(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!isSpace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String collapse(String str) {
        return collapse(str, 3);
    }

    public static String collapse(String str, int i) {
        int length;
        int i2;
        int i3;
        int i4;
        if (i == 1 || i == 0) {
            return str;
        }
        if (str.indexOf(10) >= 0) {
            str = str.replace(10, Chars.SPACE);
        }
        if (str.indexOf(9) >= 0) {
            str = str.replace(9, Chars.SPACE);
        }
        if (str.indexOf(13) >= 0) {
            str = str.replace(Chars.CR, Chars.SPACE);
        }
        String str2 = str;
        if (i == 2 || (length = str2.length()) == 0) {
            return str2;
        }
        if (str2.charAt(0) != ' ') {
            i3 = 2;
            while (true) {
                if (i3 < length) {
                    if (str2.charAt(i3) == ' ') {
                        if (str2.charAt(i3 - 1) == ' ' || i3 == length - 1) {
                            break;
                        }
                        i3++;
                        if (str2.charAt(i3) == ' ') {
                            break;
                        }
                    }
                    i3 += 2;
                } else if (i3 != length || str2.charAt(i3 - 1) != ' ') {
                    return str2;
                }
            }
            i2 = i3;
        } else {
            int i5 = 0;
            while (true) {
                int i6 = i3 + 1;
                if (i6 >= str2.length() || str2.charAt(i6) != ' ') {
                    i2 = 0;
                } else {
                    i5 = i6;
                }
            }
            i2 = 0;
        }
        char[] charArray = str2.toCharArray();
        loop2:
        while (true) {
            int i7 = i3 + 1;
            if (i7 >= length) {
                break;
            } else if (str2.charAt(i7) != ' ') {
                while (true) {
                    int i8 = i2 + 1;
                    int i9 = i7 + 1;
                    charArray[i2] = charArray[i7];
                    if (i9 >= length) {
                        i2 = i8;
                        break loop2;
                    }
                    char c = charArray[i9];
                    if (c == ' ') {
                        int i10 = i8 + 1;
                        i9++;
                        charArray[i8] = c;
                        if (i9 >= length) {
                            i2 = i10;
                            break loop2;
                        } else if (charArray[i9] == ' ') {
                            i2 = i10;
                            i7 = i9;
                            break;
                        } else {
                            i4 = i10;
                        }
                    } else {
                        i4 = i8;
                    }
                    i7 = i9;
                }
            }
        }
        if (i2 != 0) {
            int i11 = i2 - 1;
            if (charArray[i11] == ' ') {
                i2 = i11;
            }
        }
        return new String(charArray, 0, i2);
    }
}
