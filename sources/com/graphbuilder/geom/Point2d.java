package com.graphbuilder.geom;

import com.graphbuilder.curve.Point;

public interface Point2d extends Point {
    double getX();

    double getY();

    void setLocation(double d, double d2);
}
