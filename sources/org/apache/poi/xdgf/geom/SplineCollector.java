package org.apache.poi.xdgf.geom;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.ValueVector;
import com.graphbuilder.geom.PointFactory;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

public class SplineCollector {
    ArrayList<SplineKnot> _knots = new ArrayList<>();
    SplineStart _start;

    public SplineCollector(SplineStart splineStart) {
        this._start = splineStart;
    }

    public void addKnot(SplineKnot splineKnot) {
        if (!splineKnot.getDel()) {
            this._knots.add(splineKnot);
        }
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        Point2D currentPoint = doubleR.getCurrentPoint();
        ControlPath controlPath = new ControlPath();
        ValueVector valueVector = new ValueVector(this._knots.size() + 3);
        double doubleValue = this._start.getB().doubleValue();
        double doubleValue2 = this._start.getC().doubleValue();
        int intValue = this._start.getD().intValue();
        valueVector.add(doubleValue);
        valueVector.add(this._start.getA().doubleValue());
        controlPath.addPoint(PointFactory.create(currentPoint.getX(), currentPoint.getY()));
        controlPath.addPoint(PointFactory.create(this._start.getX().doubleValue(), this._start.getY().doubleValue()));
        Iterator<SplineKnot> it = this._knots.iterator();
        while (it.hasNext()) {
            SplineKnot next = it.next();
            valueVector.add(next.getA().doubleValue());
            controlPath.addPoint(PointFactory.create(next.getX().doubleValue(), next.getY().doubleValue()));
        }
        valueVector.add(doubleValue2);
        doubleR.append(SplineRenderer.createNurbsSpline(controlPath, valueVector, (ValueVector) null, intValue), true);
    }
}
