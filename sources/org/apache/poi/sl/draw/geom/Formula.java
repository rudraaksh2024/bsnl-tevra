package org.apache.poi.sl.draw.geom;

public interface Formula {
    public static final double OOXML_DEGREE = 60000.0d;

    double evaluate(Context context);
}
