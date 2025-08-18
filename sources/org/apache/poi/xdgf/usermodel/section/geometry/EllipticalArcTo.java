package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class EllipticalArcTo implements GeometryRow {
    EllipticalArcTo _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    Double x;
    Double y;

    protected static double computeSweep(double d2, double d3, double d4) {
        double d5 = (d2 + 360.0d) % 360.0d;
        double d6 = (d3 + 360.0d) % 360.0d;
        double d7 = (d4 + 360.0d) % 360.0d;
        if (d5 < d6) {
            if (d5 >= d7 || d7 >= d6) {
                return (d5 - d6) + 360.0d;
            }
        } else if (d6 >= d7 || d7 >= d5) {
            return -(360.0d - (d5 - d6));
        }
        return d5 - d6;
    }

    public EllipticalArcTo(RowType rowType) {
        if (rowType.isSetDel()) {
            this.deleted = Boolean.valueOf(rowType.getDel());
        }
        for (CellType cellType : rowType.getCellArray()) {
            String n = cellType.getN();
            if (n.equals("X")) {
                this.x = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals("Y")) {
                this.y = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                this.a = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals("B")) {
                this.b = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals("C")) {
                this.c = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals("D")) {
                this.d = XDGFCell.parseDoubleValue(cellType);
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in EllipticalArcTo row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        EllipticalArcTo ellipticalArcTo = this._master;
        return ellipticalArcTo != null && ellipticalArcTo.getDel();
    }

    public Double getX() {
        Double d2 = this.x;
        return d2 == null ? this._master.x : d2;
    }

    public Double getY() {
        Double d2 = this.y;
        return d2 == null ? this._master.y : d2;
    }

    public Double getA() {
        Double d2 = this.a;
        return d2 == null ? this._master.a : d2;
    }

    public Double getB() {
        Double d2 = this.b;
        return d2 == null ? this._master.b : d2;
    }

    public Double getC() {
        Double d2 = this.c;
        return d2 == null ? this._master.c : d2;
    }

    public Double getD() {
        Double d2 = this.d;
        return d2 == null ? this._master.d : d2;
    }

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (EllipticalArcTo) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        if (!getDel()) {
            createEllipticalArc(getX().doubleValue(), getY().doubleValue(), getA().doubleValue(), getB().doubleValue(), getC().doubleValue(), getD().doubleValue(), doubleR);
        }
    }

    public static void createEllipticalArc(double d2, double d3, double d4, double d5, double d6, double d7, Path2D.Double doubleR) {
        double d8 = d6;
        Point2D currentPoint = doubleR.getCurrentPoint();
        double x2 = currentPoint.getX();
        double y2 = currentPoint.getY();
        AffineTransform rotateInstance = AffineTransform.getRotateInstance(-d8);
        double[] dArr = {x2, y2, d2, d3, d4, d5};
        rotateInstance.transform(dArr, 0, dArr, 0, 3);
        double d9 = dArr[0];
        double d10 = dArr[1];
        double d11 = dArr[2];
        double d12 = dArr[3];
        double d13 = dArr[4];
        double d14 = dArr[5];
        double d15 = d7 * d7;
        double d16 = d9 - d11;
        double d17 = d12 - d14;
        double d18 = d11 - d13;
        double d19 = d10 - d12;
        double d20 = d17 * d16;
        double d21 = d19 * d18;
        double d22 = (((((d9 + d11) * d16) * d17) - (((d11 + d13) * d18) * d19)) + (((d15 * d19) * d17) * (d10 - d14))) / ((d20 - d21) * 2.0d);
        double d23 = (((((d16 * d18) * (d9 - d13)) / d15) + ((d10 + d12) * d21)) - ((d12 + d14) * d20)) / ((d21 - d20) * 2.0d);
        double d24 = d9 - d22;
        double d25 = d10 - d23;
        double sqrt = Math.sqrt(Math.pow(d24, 2.0d) + (Math.pow(d25, 2.0d) * d15));
        double d26 = sqrt / d7;
        double degrees = Math.toDegrees(Math.atan2((d14 - d23) / d26, (d13 - d22) / sqrt));
        double degrees2 = Math.toDegrees(Math.atan2(d25 / d26, d24 / sqrt));
        double d27 = d22 - sqrt;
        double d28 = d23 - d26;
        double d29 = sqrt * 2.0d;
        double d30 = d26 * 2.0d;
        double d31 = -degrees2;
        Arc2D.Double doubleR2 = new Arc2D.Double(d27, d28, d29, d30, d31, computeSweep(degrees2, Math.toDegrees(Math.atan2((d12 - d23) / d26, (d11 - d22) / sqrt)), degrees), 0);
        rotateInstance.setToRotation(d8);
        doubleR.append(rotateInstance.createTransformedShape(doubleR2), false);
    }
}
