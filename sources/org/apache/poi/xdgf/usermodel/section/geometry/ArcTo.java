package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class ArcTo implements GeometryRow {
    ArcTo _master;
    Double a;
    Boolean deleted;
    Double x;
    Double y;

    public ArcTo(RowType rowType) {
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
                throw new POIXMLException("Invalid cell '" + n + "' in ArcTo row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        ArcTo arcTo = this._master;
        return arcTo != null && arcTo.getDel();
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
        this._master = (ArcTo) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        if (!getDel()) {
            Point2D currentPoint = doubleR.getCurrentPoint();
            double doubleValue = getX().doubleValue();
            double doubleValue2 = getY().doubleValue();
            double doubleValue3 = getA().doubleValue();
            if (doubleValue3 == 0.0d) {
                doubleR.lineTo(doubleValue, doubleValue2);
                return;
            }
            Path2D.Double doubleR2 = doubleR;
            double x2 = currentPoint.getX();
            double y2 = currentPoint.getY();
            double d = doubleValue2 - y2;
            double d2 = x2 - doubleValue;
            double sqrt = Math.sqrt((d * d) + (d2 * d2));
            EllipticalArcTo.createEllipticalArc(doubleValue, doubleValue2, ((x2 + doubleValue) / 2.0d) + ((d * doubleValue3) / sqrt), ((y2 + doubleValue2) / 2.0d) + ((doubleValue3 * d2) / sqrt), 0.0d, 1.0d, doubleR);
        }
    }

    public String toString() {
        return String.format(LocaleUtil.getUserLocale(), "ArcTo: x=%f; y=%f; a=%f", new Object[]{this.x, this.y, this.a});
    }
}
