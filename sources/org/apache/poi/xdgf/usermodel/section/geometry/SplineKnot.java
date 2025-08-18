package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class SplineKnot implements GeometryRow {
    SplineKnot _master;
    Double a;
    Boolean deleted;
    Double x;
    Double y;

    public SplineKnot(RowType rowType) {
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
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in SplineKnot row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        SplineKnot splineKnot = this._master;
        return splineKnot != null && splineKnot.getDel();
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

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (SplineKnot) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        throw new POIXMLException("Error: Use SplineRenderer!");
    }

    public String toString() {
        return "{SplineKnot x=" + getX() + " y=" + getY() + " a=" + getA() + VectorFormat.DEFAULT_SUFFIX;
    }
}
