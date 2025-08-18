package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class Ellipse implements GeometryRow {
    Ellipse _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    Double x;
    Double y;

    public Ellipse(RowType rowType) {
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
                throw new POIXMLException("Invalid cell '" + n + "' in Ellipse row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        Ellipse ellipse = this._master;
        return ellipse != null && ellipse.getDel();
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
        this._master = (Ellipse) geometryRow;
    }

    public Path2D.Double getPath() {
        if (getDel()) {
            return null;
        }
        double doubleValue = getX().doubleValue();
        double doubleValue2 = getY().doubleValue();
        double doubleValue3 = getA().doubleValue();
        double doubleValue4 = getB().doubleValue();
        double doubleValue5 = getC().doubleValue();
        double doubleValue6 = getD().doubleValue();
        double hypot = Math.hypot(doubleValue3 - doubleValue, doubleValue4 - doubleValue2);
        double hypot2 = Math.hypot(doubleValue5 - doubleValue, doubleValue6 - doubleValue2);
        double d2 = doubleValue2 > doubleValue4 ? 1.0d : -1.0d;
        Path2D.Double doubleR = new Path2D.Double(new Ellipse2D.Double(doubleValue - hypot, doubleValue2 - hypot2, hypot * 2.0d, hypot2 * 2.0d));
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(((d2 * Math.acos((doubleValue - doubleValue3) / hypot)) + 6.283185307179586d) % 6.283185307179586d, doubleValue, doubleValue2);
        doubleR.transform(affineTransform);
        return doubleR;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        throw new POIXMLException("Ellipse elements cannot be part of a path");
    }
}
