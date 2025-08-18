package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class Vector3D implements Serializable, Vector<Euclidean3D> {
    public static final Vector3D MINUS_I = new Vector3D(-1.0d, 0.0d, 0.0d);
    public static final Vector3D MINUS_J = new Vector3D(0.0d, -1.0d, 0.0d);
    public static final Vector3D MINUS_K = new Vector3D(0.0d, 0.0d, -1.0d);
    public static final Vector3D NEGATIVE_INFINITY = new Vector3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    public static final Vector3D NaN = new Vector3D(Double.NaN, Double.NaN, Double.NaN);
    public static final Vector3D PLUS_I = new Vector3D(1.0d, 0.0d, 0.0d);
    public static final Vector3D PLUS_J = new Vector3D(0.0d, 1.0d, 0.0d);
    public static final Vector3D PLUS_K = new Vector3D(0.0d, 0.0d, 1.0d);
    public static final Vector3D POSITIVE_INFINITY = new Vector3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector3D ZERO = new Vector3D(0.0d, 0.0d, 0.0d);
    private static final long serialVersionUID = 1313493323784566947L;
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
    }

    public Vector3D(double[] dArr) throws DimensionMismatchException {
        if (dArr.length == 3) {
            this.x = dArr[0];
            this.y = dArr[1];
            this.z = dArr[2];
            return;
        }
        throw new DimensionMismatchException(dArr.length, 3);
    }

    public Vector3D(double d, double d2) {
        double cos = FastMath.cos(d2);
        this.x = FastMath.cos(d) * cos;
        this.y = FastMath.sin(d) * cos;
        this.z = FastMath.sin(d2);
    }

    public Vector3D(double d, Vector3D vector3D) {
        this.x = vector3D.x * d;
        this.y = vector3D.y * d;
        this.z = d * vector3D.z;
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2) {
        Vector3D vector3D3 = vector3D;
        Vector3D vector3D4 = vector3D2;
        this.x = MathArrays.linearCombination(d, vector3D3.x, d2, vector3D4.x);
        double d3 = d;
        double d4 = d2;
        this.y = MathArrays.linearCombination(d3, vector3D3.y, d4, vector3D4.y);
        this.z = MathArrays.linearCombination(d3, vector3D3.z, d4, vector3D4.z);
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2, double d3, Vector3D vector3D3) {
        Vector3D vector3D4 = vector3D;
        Vector3D vector3D5 = vector3D2;
        Vector3D vector3D6 = vector3D3;
        this.x = MathArrays.linearCombination(d, vector3D4.x, d2, vector3D5.x, d3, vector3D6.x);
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        this.y = MathArrays.linearCombination(d4, vector3D4.y, d5, vector3D5.y, d6, vector3D6.y);
        this.z = MathArrays.linearCombination(d4, vector3D4.z, d5, vector3D5.z, d6, vector3D6.z);
    }

    public Vector3D(double d, Vector3D vector3D, double d2, Vector3D vector3D2, double d3, Vector3D vector3D3, double d4, Vector3D vector3D4) {
        Vector3D vector3D5 = vector3D;
        Vector3D vector3D6 = vector3D2;
        Vector3D vector3D7 = vector3D3;
        Vector3D vector3D8 = vector3D4;
        double d5 = vector3D5.x;
        double d6 = vector3D6.x;
        double d7 = vector3D7.x;
        double d8 = d7;
        this.x = MathArrays.linearCombination(d, d5, d2, d6, d3, d8, d4, vector3D8.x);
        double d9 = d;
        double d10 = d2;
        double d11 = d3;
        double d12 = d4;
        this.y = MathArrays.linearCombination(d9, vector3D5.y, d10, vector3D6.y, d11, vector3D7.y, d12, vector3D8.y);
        this.z = MathArrays.linearCombination(d9, vector3D5.z, d10, vector3D6.z, d11, vector3D7.z, d12, vector3D8.z);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y, this.z};
    }

    public Space getSpace() {
        return Euclidean3D.getInstance();
    }

    public Vector3D getZero() {
        return ZERO;
    }

    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y) + FastMath.abs(this.z);
    }

    public double getNorm() {
        double d = this.x;
        double d2 = this.y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.z;
        return FastMath.sqrt(d3 + (d4 * d4));
    }

    public double getNormSq() {
        double d = this.x;
        double d2 = this.y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.z;
        return d3 + (d4 * d4);
    }

    public double getNormInf() {
        return FastMath.max(FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y)), FastMath.abs(this.z));
    }

    public double getAlpha() {
        return FastMath.atan2(this.y, this.x);
    }

    public double getDelta() {
        return FastMath.asin(this.z / getNorm());
    }

    public Vector3D add(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(this.x + vector3D.x, this.y + vector3D.y, this.z + vector3D.z);
    }

    public Vector3D add(double d, Vector<Euclidean3D> vector) {
        return new Vector3D(1.0d, this, d, (Vector3D) vector);
    }

    public Vector3D subtract(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(this.x - vector3D.x, this.y - vector3D.y, this.z - vector3D.z);
    }

    public Vector3D subtract(double d, Vector<Euclidean3D> vector) {
        return new Vector3D(1.0d, this, -d, (Vector3D) vector);
    }

    public Vector3D normalize() throws MathArithmeticException {
        double norm = getNorm();
        if (norm != 0.0d) {
            return scalarMultiply(1.0d / norm);
        }
        throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
    }

    public Vector3D orthogonal() throws MathArithmeticException {
        double norm = getNorm() * 0.6d;
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        } else if (FastMath.abs(this.x) <= norm) {
            double d = this.y;
            double d2 = this.z;
            double sqrt = 1.0d / FastMath.sqrt((d * d) + (d2 * d2));
            return new Vector3D(0.0d, sqrt * this.z, (-sqrt) * this.y);
        } else if (FastMath.abs(this.y) <= norm) {
            double d3 = this.x;
            double d4 = this.z;
            double sqrt2 = 1.0d / FastMath.sqrt((d3 * d3) + (d4 * d4));
            return new Vector3D((-sqrt2) * this.z, 0.0d, sqrt2 * this.x);
        } else {
            double d5 = this.x;
            double d6 = this.y;
            double sqrt3 = 1.0d / FastMath.sqrt((d5 * d5) + (d6 * d6));
            return new Vector3D(sqrt3 * this.y, (-sqrt3) * this.x, 0.0d);
        }
    }

    public static double angle(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        double norm = vector3D.getNorm() * vector3D2.getNorm();
        if (norm != 0.0d) {
            double dotProduct = vector3D.dotProduct(vector3D2);
            double d = 0.9999d * norm;
            if (dotProduct >= (-d) && dotProduct <= d) {
                return FastMath.acos(dotProduct / norm);
            }
            Vector3D crossProduct = crossProduct(vector3D, vector3D2);
            if (dotProduct >= 0.0d) {
                return FastMath.asin(crossProduct.getNorm() / norm);
            }
            return 3.141592653589793d - FastMath.asin(crossProduct.getNorm() / norm);
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public Vector3D negate() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }

    public Vector3D scalarMultiply(double d) {
        return new Vector3D(d * this.x, this.y * d, this.z * d);
    }

    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z);
    }

    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y) || Double.isInfinite(this.z));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vector3D)) {
            return false;
        }
        Vector3D vector3D = (Vector3D) obj;
        if (vector3D.isNaN()) {
            return isNaN();
        }
        if (this.x == vector3D.x && this.y == vector3D.y && this.z == vector3D.z) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isNaN()) {
            return 642;
        }
        return ((MathUtils.hash(this.x) * 164) + (MathUtils.hash(this.y) * 3) + MathUtils.hash(this.z)) * 643;
    }

    public double dotProduct(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return MathArrays.linearCombination(this.x, vector3D.x, this.y, vector3D.y, this.z, vector3D.z);
    }

    public Vector3D crossProduct(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return new Vector3D(MathArrays.linearCombination(this.y, vector3D.z, -this.z, vector3D.y), MathArrays.linearCombination(this.z, vector3D.x, -this.x, vector3D.z), MathArrays.linearCombination(this.x, vector3D.y, -this.y, vector3D.x));
    }

    public double distance1(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        return FastMath.abs(vector3D.x - this.x) + FastMath.abs(vector3D.y - this.y) + FastMath.abs(vector3D.z - this.z);
    }

    public double distance(Vector<Euclidean3D> vector) {
        return distance((Point<Euclidean3D>) vector);
    }

    public double distance(Point<Euclidean3D> point) {
        Vector3D vector3D = (Vector3D) point;
        double d = vector3D.x - this.x;
        double d2 = vector3D.y - this.y;
        double d3 = vector3D.z - this.z;
        return FastMath.sqrt((d * d) + (d2 * d2) + (d3 * d3));
    }

    public double distanceInf(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        double abs = FastMath.abs(vector3D.x - this.x);
        double abs2 = FastMath.abs(vector3D.y - this.y);
        return FastMath.max(FastMath.max(abs, abs2), FastMath.abs(vector3D.z - this.z));
    }

    public double distanceSq(Vector<Euclidean3D> vector) {
        Vector3D vector3D = (Vector3D) vector;
        double d = vector3D.x - this.x;
        double d2 = vector3D.y - this.y;
        double d3 = vector3D.z - this.z;
        return (d * d) + (d2 * d2) + (d3 * d3);
    }

    public static double dotProduct(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.dotProduct(vector3D2);
    }

    public static Vector3D crossProduct(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.crossProduct(vector3D2);
    }

    public static double distance1(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distance1(vector3D2);
    }

    public static double distance(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distance((Vector<Euclidean3D>) vector3D2);
    }

    public static double distanceInf(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distanceInf(vector3D2);
    }

    public static double distanceSq(Vector3D vector3D, Vector3D vector3D2) {
        return vector3D.distanceSq(vector3D2);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(this);
    }

    public String toString(NumberFormat numberFormat) {
        return new Vector3DFormat(numberFormat).format(this);
    }
}
