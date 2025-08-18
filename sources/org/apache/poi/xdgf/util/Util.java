package org.apache.poi.xdgf.util;

public class Util {
    public static int countLines(String str) {
        int i = 0;
        int i2 = 1;
        while (true) {
            i = str.indexOf(10, i) + 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public static String sanitizeFilename(String str) {
        return str.replaceAll("[:\\\\/*\"?|<>]", "_");
    }
}
