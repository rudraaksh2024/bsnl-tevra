package org.apache.commons.math3.geometry.euclidean.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.util.FastMath;

public class Segment {
    private final Vector2D end;
    private final Line line;
    private final Vector2D start;

    public Segment(Vector2D vector2D, Vector2D vector2D2, Line line2) {
        this.start = vector2D;
        this.end = vector2D2;
        this.line = line2;
    }

    public Vector2D getStart() {
        return this.start;
    }

    public Vector2D getEnd() {
        return this.end;
    }

    public Line getLine() {
        return this.line;
    }

    public double distance(Vector2D vector2D) {
        double x = this.end.getX() - this.start.getX();
        double y = this.end.getY() - this.start.getY();
        double x2 = (((vector2D.getX() - this.start.getX()) * x) + ((vector2D.getY() - this.start.getY()) * y)) / ((x * x) + (y * y));
        if (x2 < 0.0d || x2 > 1.0d) {
            return FastMath.min(getStart().distance((Point<Euclidean2D>) vector2D), getEnd().distance((Point<Euclidean2D>) vector2D));
        }
        return new Vector2D(this.start.getX() + (x * x2), this.start.getY() + (x2 * y)).distance((Point<Euclidean2D>) vector2D);
    }
}
