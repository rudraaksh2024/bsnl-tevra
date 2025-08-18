package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

public class SphericalCoordinates implements Serializable {
    private static final long serialVersionUID = 20130206;
    private double[][] jacobian;
    private final double phi;
    private double[][] phiHessian;
    private final double r;
    private double[][] rHessian;
    private final double theta;
    private double[][] thetaHessian;
    private final Vector3D v;

    public SphericalCoordinates(Vector3D vector3D) {
        this.v = vector3D;
        double norm = vector3D.getNorm();
        this.r = norm;
        this.theta = vector3D.getAlpha();
        this.phi = FastMath.acos(vector3D.getZ() / norm);
    }

    public SphericalCoordinates(double d, double d2, double d3) {
        double d4 = d;
        double cos = FastMath.cos(d2);
        double sin = FastMath.sin(d2);
        double cos2 = FastMath.cos(d3);
        double sin2 = FastMath.sin(d3);
        this.r = d4;
        this.theta = d2;
        this.phi = d3;
        double d5 = d4 * cos2;
        Vector3D vector3D = r11;
        Vector3D vector3D2 = new Vector3D(cos * d4 * sin2, d4 * sin * sin2, d5);
        this.v = vector3D;
    }

    public Vector3D getCartesian() {
        return this.v;
    }

    public double getR() {
        return this.r;
    }

    public double getTheta() {
        return this.theta;
    }

    public double getPhi() {
        return this.phi;
    }

    public double[] toCartesianGradient(double[] dArr) {
        computeJacobian();
        double d = dArr[0];
        double[][] dArr2 = this.jacobian;
        double[] dArr3 = dArr2[0];
        double d2 = dArr[1];
        double[] dArr4 = dArr2[1];
        double d3 = dArr[2];
        double[] dArr5 = dArr2[2];
        double d4 = dArr[0];
        return new double[]{(d * dArr3[0]) + (dArr4[0] * d2) + (dArr5[0] * d3), (dArr3[1] * d4) + (d2 * dArr4[1]) + (dArr5[1] * d3), (d4 * dArr3[2]) + (d3 * dArr5[2])};
    }

    public double[][] toCartesianHessian(double[][] dArr, double[] dArr2) {
        computeJacobian();
        computeHessians();
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
        double[][] dArr4 = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
        double[] dArr5 = dArr3[0];
        double[] dArr6 = dArr[0];
        double d = dArr6[0];
        double[][] dArr7 = this.jacobian;
        double[] dArr8 = dArr7[0];
        double[] dArr9 = dArr[1];
        double d2 = dArr9[0];
        double[] dArr10 = dArr7[1];
        double d3 = (d * dArr8[0]) + (d2 * dArr10[0]);
        double[] dArr11 = dArr[2];
        double d4 = dArr11[0];
        double[] dArr12 = dArr7[2];
        dArr5[0] = d3 + (d4 * dArr12[0]);
        double d5 = dArr6[0];
        double d6 = dArr9[0];
        double d7 = (dArr8[1] * d5) + (dArr10[1] * d6);
        double d8 = dArr11[0];
        dArr5[1] = d7 + (dArr12[1] * d8);
        dArr5[2] = (d5 * dArr8[2]) + (d8 * dArr12[2]);
        double[] dArr13 = dArr3[1];
        double d9 = d6 * dArr8[0];
        double d10 = dArr9[1];
        double d11 = d9 + (dArr10[0] * d10);
        double d12 = dArr11[1];
        dArr13[0] = d11 + (dArr12[0] * d12);
        dArr13[1] = (dArr9[0] * dArr8[1]) + (d10 * dArr10[1]) + (d12 * dArr12[1]);
        double[] dArr14 = dArr3[2];
        double d13 = dArr11[0] * dArr8[0];
        double d14 = dArr11[1];
        double d15 = d13 + (dArr10[0] * d14);
        double d16 = dArr11[2];
        double d17 = d15 + (dArr12[0] * d16);
        dArr14[0] = d17;
        double d18 = dArr11[0];
        double d19 = (dArr8[1] * d18) + (d14 * dArr10[1]) + (dArr12[1] * d16);
        dArr14[1] = d19;
        double d20 = (d18 * dArr8[2]) + (d16 * dArr12[2]);
        dArr14[2] = d20;
        double[] dArr15 = dArr4[0];
        dArr15[0] = (dArr8[0] * dArr5[0]) + (dArr10[0] * dArr13[0]) + (dArr12[0] * d17);
        double[] dArr16 = dArr4[1];
        double d21 = dArr8[1];
        double d22 = dArr10[1];
        double d23 = (dArr5[0] * d21) + (dArr13[0] * d22);
        double d24 = dArr12[1];
        dArr16[0] = d23 + (dArr14[0] * d24);
        double[] dArr17 = dArr4[2];
        double d25 = dArr8[2];
        double d26 = dArr12[2];
        dArr17[0] = (dArr5[0] * d25) + (dArr14[0] * d26);
        dArr16[1] = (d21 * dArr5[1]) + (d22 * dArr13[1]) + (d24 * d19);
        dArr17[1] = (dArr5[1] * d25) + (dArr14[1] * d26);
        double d27 = (d25 * dArr5[2]) + (d26 * d20);
        dArr17[2] = d27;
        double d28 = dArr15[0];
        double d29 = dArr2[0];
        double[][] dArr18 = this.rHessian;
        double d30 = d29 * dArr18[0][0];
        double d31 = dArr2[1];
        double[][] dArr19 = this.thetaHessian;
        double d32 = d30 + (dArr19[0][0] * d31);
        double d33 = dArr2[2];
        double[][] dArr20 = this.phiHessian;
        dArr15[0] = d28 + d32 + (dArr20[0][0] * d33);
        double d34 = dArr16[0];
        double d35 = dArr2[0];
        double[] dArr21 = dArr18[1];
        double[] dArr22 = dArr19[1];
        double d36 = (d35 * dArr21[0]) + (dArr22[0] * d31);
        double[] dArr23 = dArr20[1];
        dArr16[0] = d34 + d36 + (dArr23[0] * d33);
        double d37 = dArr17[0];
        double d38 = dArr2[0];
        double[] dArr24 = dArr18[2];
        double[] dArr25 = dArr20[2];
        double d39 = d37 + (d38 * dArr24[0]) + (dArr25[0] * d33);
        dArr17[0] = d39;
        double d40 = dArr16[1];
        double d41 = dArr2[0];
        dArr16[1] = d40 + (dArr21[1] * d41) + (d31 * dArr22[1]) + (dArr23[1] * d33);
        dArr17[1] = dArr17[1] + (dArr24[1] * d41) + (dArr25[1] * d33);
        dArr17[2] = d27 + (d41 * dArr24[2]) + (d33 * dArr25[2]);
        dArr15[1] = dArr16[0];
        dArr15[2] = d39;
        dArr16[2] = dArr17[1];
        return dArr4;
    }

    private void computeJacobian() {
        if (this.jacobian == null) {
            double x = this.v.getX();
            double y = this.v.getY();
            double z = this.v.getZ();
            double d = (x * x) + (y * y);
            double sqrt = FastMath.sqrt(d);
            double d2 = (z * z) + d;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
            this.jacobian = dArr;
            double[] dArr2 = dArr[0];
            double[][] dArr3 = dArr;
            double d3 = this.r;
            dArr2[0] = x / d3;
            dArr2[1] = y / d3;
            dArr2[2] = z / d3;
            double[] dArr4 = dArr3[1];
            double d4 = x;
            dArr4[0] = (-y) / d;
            dArr4[1] = d4 / d;
            double[] dArr5 = dArr3[2];
            double d5 = sqrt * d2;
            dArr5[0] = (d4 * z) / d5;
            dArr5[1] = (y * z) / d5;
            dArr5[2] = (-sqrt) / d2;
        }
    }

    private void computeHessians() {
        if (this.rHessian == null) {
            double x = this.v.getX();
            double y = this.v.getY();
            double z = this.v.getZ();
            double d = x * x;
            double d2 = y * y;
            double d3 = z * z;
            double d4 = d + d2;
            double sqrt = FastMath.sqrt(d4);
            double d5 = d4 + d3;
            double d6 = d2;
            double d7 = this.r;
            double d8 = x / d4;
            double d9 = y / d4;
            double d10 = (x / d7) / d5;
            double d11 = (y / d7) / d5;
            double d12 = (z / d7) / d5;
            double d13 = d;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
            this.rHessian = dArr;
            double[] dArr2 = dArr[0];
            double d14 = y * d11;
            double d15 = z * d12;
            dArr2[0] = d14 + d15;
            double[] dArr3 = dArr[1];
            double d16 = d3;
            double d17 = -x;
            dArr3[0] = d11 * d17;
            double[] dArr4 = dArr[2];
            double d18 = d12;
            double d19 = (-z) * d10;
            dArr4[0] = d19;
            double d20 = d10 * x;
            dArr3[1] = d20 + d15;
            double d21 = d17;
            dArr4[1] = (-y) * d18;
            dArr4[2] = d20 + d14;
            dArr2[1] = dArr3[0];
            dArr2[2] = d19;
            dArr3[2] = dArr4[1];
            double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{2, 2});
            this.thetaHessian = dArr5;
            double[] dArr6 = dArr5[0];
            dArr6[0] = d8 * 2.0d * d9;
            double[] dArr7 = dArr5[1];
            double d22 = (d9 * d9) - (d8 * d8);
            dArr7[0] = d22;
            dArr7[1] = d8 * -2.0d * d9;
            dArr6[1] = d22;
            double d23 = sqrt * d5;
            double d24 = sqrt * d23;
            double d25 = d23 * d5;
            double d26 = d25 * d4;
            double d27 = (3.0d * d4) + d16;
            double[][] dArr8 = (double[][]) Array.newInstance(Double.TYPE, new int[]{3, 3});
            this.phiHessian = dArr8;
            double[] dArr9 = dArr8[0];
            dArr9[0] = ((d24 - (d13 * d27)) * z) / d26;
            double[] dArr10 = dArr8[1];
            dArr10[0] = (((d21 * y) * z) * d27) / d26;
            double[] dArr11 = dArr8[2];
            double d28 = d4 - d16;
            double d29 = (x * d28) / d25;
            dArr11[0] = d29;
            dArr10[1] = (z * (d24 - (d6 * d27))) / d26;
            dArr11[1] = (y * d28) / d25;
            dArr11[2] = ((sqrt * 2.0d) * d18) / this.r;
            dArr9[1] = dArr10[0];
            dArr9[2] = d29;
            dArr10[2] = dArr11[1];
        }
    }

    private Object writeReplace() {
        return new DataTransferObject(this.v.getX(), this.v.getY(), this.v.getZ());
    }

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20130206;
        private final double x;
        private final double y;
        private final double z;

        DataTransferObject(double d, double d2, double d3) {
            this.x = d;
            this.y = d2;
            this.z = d3;
        }

        private Object readResolve() {
            return new SphericalCoordinates(new Vector3D(this.x, this.y, this.z));
        }
    }
}
