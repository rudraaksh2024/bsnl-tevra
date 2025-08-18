package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class InfiniteLine implements GeometryRow {
    InfiniteLine _master;
    Double a;
    Double b;
    Boolean deleted;
    Double x;
    Double y;

    public InfiniteLine(RowType rowType) {
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
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in InfiniteLine row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        InfiniteLine infiniteLine = this._master;
        return infiniteLine != null && infiniteLine.getDel();
    }

    public Double getX() {
        Double d = this.x;
        return d == null ? this._master.x : d;
    }

    public Double getY() {
        Double d = this.y;
        return d == null ? this._master.y : d;
    }

    public Double getA() {
        Double d = this.a;
        return d == null ? this._master.a : d;
    }

    public Double getB() {
        Double d = this.b;
        return d == null ? this._master.b : d;
    }

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (InfiniteLine) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        if (!getDel()) {
            throw new POIXMLException("InfiniteLine elements cannot be part of a path");
        }
    }

    public Path2D.Double getPath() {
        Path2D.Double doubleR = new Path2D.Double();
        double doubleValue = getX().doubleValue();
        double doubleValue2 = getY().doubleValue();
        double doubleValue3 = getA().doubleValue();
        double doubleValue4 = getB().doubleValue();
        if (doubleValue == doubleValue3) {
            doubleR.moveTo(doubleValue, -100000.0d);
            doubleR.lineTo(doubleValue, 100000.0d);
        } else if (doubleValue2 == doubleValue4) {
            doubleR.moveTo(-100000.0d, doubleValue2);
            doubleR.lineTo(100000.0d, doubleValue2);
        } else {
            double d = (doubleValue4 - doubleValue2) / (doubleValue3 - doubleValue);
            double d2 = doubleValue2 - (doubleValue * d);
            doubleR.moveTo(100000.0d, (d * 100000.0d) + d2);
            doubleR.lineTo(100000.0d, (100000.0d - d2) / d);
        }
        return doubleR;
    }
}
