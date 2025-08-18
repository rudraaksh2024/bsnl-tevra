package com.github.mikephil.charting.data.filter;

import java.util.Arrays;

public class Approximator {
    public float[] reduceWithDouglasPeucker(float[] fArr, float f) {
        Line line = new Line(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f2 = 0.0f;
        int i = 0;
        for (int i2 = 2; i2 < fArr.length - 2; i2 += 2) {
            float distance = line.distance(fArr[i2], fArr[i2 + 1]);
            if (distance > f2) {
                i = i2;
                f2 = distance;
            }
        }
        if (f2 <= f) {
            return line.getPoints();
        }
        float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i + 2), f);
        float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i, fArr.length), f);
        return concat(reduceWithDouglasPeucker, Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length));
    }

    /* access modifiers changed from: package-private */
    public float[] concat(float[]... fArr) {
        int i = 0;
        for (float[] length : fArr) {
            i += length.length;
        }
        float[] fArr2 = new float[i];
        int i2 = 0;
        for (float[] fArr3 : fArr) {
            for (float f : fArr[r2]) {
                fArr2[i2] = f;
                i2++;
            }
        }
        return fArr2;
    }

    private class Line {
        private float dx;
        private float dy;
        private float exsy;
        private float length;
        private float[] points;
        private float sxey;

        public Line(float f, float f2, float f3, float f4) {
            float f5 = f - f3;
            this.dx = f5;
            float f6 = f2 - f4;
            this.dy = f6;
            this.sxey = f * f4;
            this.exsy = f3 * f2;
            this.length = (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
            this.points = new float[]{f, f2, f3, f4};
        }

        public float distance(float f, float f2) {
            return Math.abs((((this.dy * f) - (this.dx * f2)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }
}
