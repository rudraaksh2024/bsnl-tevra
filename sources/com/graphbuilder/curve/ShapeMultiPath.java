package com.graphbuilder.curve;

import com.graphbuilder.geom.Geom;
import com.graphbuilder.org.apache.harmony.awt.gl.Crossing;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShapeMultiPath extends MultiPath implements Shape {
    private int ai0 = 0;
    private int ai1 = 1;
    private int windingRule = 0;

    public ShapeMultiPath() {
        super(2);
    }

    public ShapeMultiPath(int i) {
        super(i);
        if (i < 2) {
            throw new IllegalArgumentException("dimension >= 2 required");
        }
    }

    public void setBasisVectors(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int dimension = getDimension();
        if (i < 0 || i2 < 0 || i >= dimension || i2 >= dimension) {
            throw new IllegalArgumentException("basis vectors must be >= 0 and < dimension");
        }
        this.ai0 = i;
        this.ai1 = i2;
    }

    public int[] getBasisVectors() {
        return new int[]{this.ai0, this.ai1};
    }

    public double getDistSq(double d, double d2) {
        int numPoints = getNumPoints();
        double d3 = Double.MAX_VALUE;
        if (numPoints == 0) {
            return Double.MAX_VALUE;
        }
        double[] dArr = get(0);
        double d4 = dArr[this.ai0];
        double d5 = dArr[this.ai1];
        int i = 1;
        double d6 = d4;
        double d7 = d5;
        while (i < numPoints) {
            double[] dArr2 = get(i);
            double d8 = dArr2[this.ai0];
            double d9 = dArr2[this.ai1];
            if (getType(i) == MultiPath.LINE_TO) {
                double ptSegDistSq = Geom.ptSegDistSq(d8, d9, d6, d7, d, d2, (double[]) null);
                if (ptSegDistSq < d3) {
                    d3 = ptSegDistSq;
                }
            }
            i++;
            d6 = d8;
            d7 = d9;
        }
        return d3;
    }

    public int getWindingRule() {
        return this.windingRule;
    }

    public void setWindingRule(int i) {
        if (i == 0 || i == 1) {
            this.windingRule = i;
            return;
        }
        throw new IllegalArgumentException("winding rule must be WIND_EVEN_ODD or WIND_NON_ZERO");
    }

    public PathIterator getPathIterator(AffineTransform affineTransform) {
        return new ShapeMultiPathIterator(this, affineTransform);
    }

    public PathIterator getPathIterator(AffineTransform affineTransform, double d) {
        return new ShapeMultiPathIterator(this, affineTransform);
    }

    public Rectangle getBounds() {
        Rectangle2D bounds2D = getBounds2D();
        if (bounds2D == null) {
            return null;
        }
        return bounds2D.getBounds();
    }

    public Rectangle2D getBounds2D() {
        int numPoints = getNumPoints();
        double d = -1.7976931348623157E308d;
        double d2 = Double.MAX_VALUE;
        double d3 = Double.MAX_VALUE;
        double d4 = -1.7976931348623157E308d;
        int i = 0;
        boolean z = false;
        while (i < numPoints) {
            double[] dArr = get(i);
            if (getType(i) != MultiPath.MOVE_TO || (i < numPoints + -1 && getType(i + 1) == MultiPath.LINE_TO)) {
                double d5 = dArr[this.ai0];
                if (d5 < d2) {
                    d2 = d5;
                }
                double d6 = dArr[this.ai1];
                if (d6 < d3) {
                    d3 = d6;
                }
                if (d5 > d) {
                    d = d5;
                }
                if (d6 > d4) {
                    d4 = d6;
                }
                z = true;
            }
            i++;
        }
        if (!z) {
            return null;
        }
        return new Rectangle2D.Double(d2, d3, d - d2, d4 - d3);
    }

    public boolean contains(double d, double d2) {
        int crossPath = Crossing.crossPath(getPathIterator((AffineTransform) null), d, d2);
        return this.windingRule == 1 ? crossPath != 0 : (crossPath & 1) != 0;
    }

    public boolean contains(Point2D point2D) {
        return contains(point2D.getX(), point2D.getY());
    }

    public boolean contains(double d, double d2, double d3, double d4) {
        int numPoints;
        boolean z;
        double d5;
        double d6;
        int i;
        int i2;
        double d7 = d;
        double d8 = d2;
        double d9 = d7 + d3;
        double d10 = d8 + d4;
        boolean z2 = false;
        if (!contains(d, d2) || !contains(d7, d10) || !contains(d9, d8) || !contains(d9, d10) || (numPoints = getNumPoints()) == 0) {
            return false;
        }
        double[] dArr = get(0);
        double d11 = dArr[this.ai0];
        double d12 = dArr[this.ai1];
        int i3 = 1;
        while (i3 < numPoints) {
            double[] dArr2 = get(i3);
            double d13 = dArr2[this.ai0];
            double d14 = dArr2[this.ai1];
            if (getType(i3) == MultiPath.LINE_TO) {
                i2 = numPoints;
                i = i3;
                d6 = d10;
                d5 = d9;
                z = z2;
                if (Geom.getSegSegIntersection(d13, d14, d11, d12, d, d2, d5, d2, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d, d2, d, d6, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d, d6, d5, d6, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d5, d2, d5, d6, (double[]) null) == Geom.INTERSECT) {
                    return z;
                }
            } else {
                i2 = numPoints;
                i = i3;
                d6 = d10;
                d5 = d9;
                z = z2;
            }
            i3 = i + 1;
            double d15 = d;
            double d16 = d2;
            d11 = d13;
            d12 = d14;
            numPoints = i2;
            d10 = d6;
            d9 = d5;
            z2 = z;
        }
        return true;
    }

    public boolean contains(Rectangle2D rectangle2D) {
        return contains(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }

    public boolean intersects(double d, double d2, double d3, double d4) {
        int i;
        double d5;
        double d6;
        boolean z;
        int i2;
        double d7 = d;
        double d8 = d2;
        double d9 = d7 + d3;
        double d10 = d8 + d4;
        if (contains(d, d2) || contains(d7, d10) || contains(d9, d8) || contains(d9, d10)) {
            return true;
        }
        int numPoints = getNumPoints();
        boolean z2 = false;
        if (numPoints == 0) {
            return false;
        }
        double[] dArr = get(0);
        double d11 = dArr[this.ai0];
        double d12 = dArr[this.ai1];
        int i3 = 1;
        while (i3 < numPoints) {
            double[] dArr2 = get(i3);
            double d13 = dArr2[this.ai0];
            double d14 = dArr2[this.ai1];
            if (getType(i3) == MultiPath.LINE_TO) {
                z = z2;
                i2 = i3;
                d6 = d10;
                d5 = d9;
                i = numPoints;
                if (Geom.getSegSegIntersection(d13, d14, d11, d12, d, d2, d5, d2, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d, d2, d, d6, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d, d6, d5, d6, (double[]) null) == Geom.INTERSECT || Geom.getSegSegIntersection(d13, d14, d11, d12, d5, d2, d5, d6, (double[]) null) == Geom.INTERSECT) {
                    return true;
                }
                if (d13 >= d && d14 >= d2 && d13 <= d5 && d14 <= d6) {
                    return true;
                }
                if (d11 >= d && d12 >= d2 && d11 <= d5 && d12 <= d6) {
                    return true;
                }
            } else {
                z = z2;
                i2 = i3;
                d6 = d10;
                d5 = d9;
                i = numPoints;
            }
            i3 = i2 + 1;
            double d15 = d;
            double d16 = d2;
            d11 = d13;
            d12 = d14;
            z2 = z;
            d10 = d6;
            d9 = d5;
            numPoints = i;
        }
        return z2;
    }

    public boolean intersects(Rectangle2D rectangle2D) {
        return intersects(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }
}
