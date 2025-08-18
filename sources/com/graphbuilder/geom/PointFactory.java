package com.graphbuilder.geom;

public class PointFactory {

    static class Point2D implements Point2d {
        double[] pts;

        public Point2D(double d, double d2) {
            this.pts = new double[]{d, d2};
        }

        public double getX() {
            return this.pts[0];
        }

        public double getY() {
            return this.pts[1];
        }

        public void setLocation(double[] dArr) {
            double[] dArr2 = this.pts;
            dArr2[0] = dArr[0];
            dArr2[1] = dArr[1];
        }

        public void setLocation(double d, double d2) {
            double[] dArr = this.pts;
            dArr[0] = d;
            dArr[1] = d2;
        }

        public double[] getLocation() {
            return this.pts;
        }
    }

    public static Point2d create(double d, double d2) {
        return new Point2D(d, d2);
    }
}
