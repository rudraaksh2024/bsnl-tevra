package com.graphbuilder.curve;

import com.graphbuilder.struc.Bag;

public class ControlPath {
    private Bag curveBag = new Bag();
    private Bag pointBag = new Bag();

    public void addCurve(Curve curve) {
        if (curve != null) {
            this.curveBag.add(curve);
            return;
        }
        throw new IllegalArgumentException("Curve cannot be null.");
    }

    public void addPoint(Point point) {
        if (point != null) {
            this.pointBag.add(point);
            return;
        }
        throw new IllegalArgumentException("Point cannot be null.");
    }

    public void insertCurve(Curve curve, int i) {
        if (curve != null) {
            this.curveBag.insert(curve, i);
            return;
        }
        throw new IllegalArgumentException("Curve cannot be null.");
    }

    public void insertPoint(Point point, int i) {
        if (point != null) {
            this.pointBag.insert(point, i);
            return;
        }
        throw new IllegalArgumentException("Point cannot be null.");
    }

    public Curve setCurve(Curve curve, int i) {
        if (curve != null) {
            return (Curve) this.curveBag.set(curve, i);
        }
        throw new IllegalArgumentException("Curve cannot be null.");
    }

    public Point setPoint(Point point, int i) {
        if (point != null) {
            return (Point) this.pointBag.set(point, i);
        }
        throw new IllegalArgumentException("Point cannot be null.");
    }

    public Curve getCurve(int i) {
        return (Curve) this.curveBag.get(i);
    }

    public Point getPoint(int i) {
        return (Point) this.pointBag.get(i);
    }

    public int numCurves() {
        return this.curveBag.size();
    }

    public int numPoints() {
        return this.pointBag.size();
    }

    public void removeCurve(Curve curve) {
        this.curveBag.remove((Object) curve);
    }

    public void removePoint(Point point) {
        this.pointBag.remove((Object) point);
    }

    public void removeCurve(int i) {
        this.curveBag.remove(i);
    }

    public void removePoint(int i) {
        this.pointBag.remove(i);
    }

    public void ensureCurveCapacity(int i) {
        this.curveBag.ensureCapacity(i);
    }

    public void ensurePointCapacity(int i) {
        this.pointBag.ensureCapacity(i);
    }

    public void trimCurveArray() {
        this.curveBag.trimArray();
    }

    public void trimPointArray() {
        this.pointBag.trimArray();
    }
}
