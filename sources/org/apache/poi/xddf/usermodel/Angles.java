package org.apache.poi.xddf.usermodel;

public class Angles {
    public static final int OOXML_DEGREE = 60000;

    public static final double attributeToDegrees(int i) {
        return ((double) i) / 60000.0d;
    }

    public static final int degreesToAttribute(double d) {
        return Math.toIntExact(Math.round(d * 60000.0d));
    }
}
