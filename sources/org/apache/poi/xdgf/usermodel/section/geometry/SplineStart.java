package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class SplineStart implements GeometryRow {
    SplineStart _master;
    Double a;
    Double b;
    Double c;
    Integer d;
    Boolean deleted;
    Double x;
    Double y;

    public SplineStart(RowType rowType) {
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
                this.d = XDGFCell.parseIntegerValue(cellType);
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in SplineStart row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        SplineStart splineStart = this._master;
        return splineStart != null && splineStart.getDel();
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

    public Integer getD() {
        Integer num = this.d;
        return num == null ? this._master.d : num;
    }

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (SplineStart) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        throw new POIXMLException("Error: Use SplineRenderer!");
    }

    public String toString() {
        return "{SplineStart x=" + getX() + " y=" + getY() + " a=" + getA() + " b=" + getB() + " c=" + getC() + " d=" + getD() + VectorFormat.DEFAULT_SUFFIX;
    }
}
