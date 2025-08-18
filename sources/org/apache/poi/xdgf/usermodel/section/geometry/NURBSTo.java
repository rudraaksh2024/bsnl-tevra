package org.apache.poi.xdgf.usermodel.section.geometry;

import androidx.exifinterface.media.ExifInterface;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.ValueVector;
import com.graphbuilder.geom.PointFactory;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.geom.SplineRenderer;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class NURBSTo implements GeometryRow {
    NURBSTo _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    String e;
    Double x;
    Double y;

    public NURBSTo(RowType rowType) {
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
            } else if (n.equals(ExifInterface.LONGITUDE_EAST)) {
                this.e = cellType.getV();
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in NURBS row");
            }
        }
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        NURBSTo nURBSTo = this._master;
        return nURBSTo != null && nURBSTo.getDel();
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

    public String getE() {
        String str = this.e;
        return str == null ? this._master.e : str;
    }

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (NURBSTo) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        if (!getDel()) {
            Point2D currentPoint = doubleR.getCurrentPoint();
            String trim = getE().trim();
            if (!trim.startsWith("NURBS(") || !trim.endsWith(")")) {
                throw new POIXMLException("Invalid NURBS formula: " + trim);
            }
            String[] split = trim.substring(6, trim.length() - 1).split(",");
            if (split.length < 8) {
                throw new POIXMLException("Invalid NURBS formula (not enough arguments)");
            } else if ((split.length - 4) % 4 == 0) {
                double doubleValue = getX().doubleValue();
                double doubleValue2 = getY().doubleValue();
                double doubleValue3 = getA().doubleValue();
                double doubleValue4 = getB().doubleValue();
                double doubleValue5 = getC().doubleValue();
                double doubleValue6 = getD().doubleValue();
                double d2 = doubleValue;
                double parseDouble = Double.parseDouble(split[0].trim());
                int parseInt = Integer.parseInt(split[1].trim());
                int parseInt2 = Integer.parseInt(split[2].trim());
                int parseInt3 = Integer.parseInt(split[3].trim());
                double d3 = 1.0d;
                double doubleValue7 = parseInt2 == 0 ? xDGFShape.getWidth().doubleValue() : 1.0d;
                if (parseInt3 == 0) {
                    d3 = xDGFShape.getHeight().doubleValue();
                }
                ControlPath controlPath = new ControlPath();
                int i = parseInt;
                ValueVector valueVector = new ValueVector();
                double d4 = doubleValue2;
                ValueVector valueVector2 = new ValueVector();
                valueVector.add(doubleValue5);
                valueVector2.add(doubleValue6);
                controlPath.addPoint(PointFactory.create(currentPoint.getX(), currentPoint.getY()));
                int length = (split.length - 4) / 4;
                int i2 = 0;
                while (i2 < length) {
                    int i3 = (i2 * 4) + 4;
                    double parseDouble2 = Double.parseDouble(split[i3 + 0].trim());
                    double parseDouble3 = Double.parseDouble(split[i3 + 1].trim());
                    double d5 = doubleValue4;
                    double parseDouble4 = Double.parseDouble(split[i3 + 2].trim());
                    int i4 = length;
                    double parseDouble5 = Double.parseDouble(split[i3 + 3].trim());
                    controlPath.addPoint(PointFactory.create(parseDouble2 * doubleValue7, parseDouble3 * d3));
                    valueVector.add(parseDouble4);
                    valueVector2.add(parseDouble5);
                    i2++;
                    length = i4;
                    split = split;
                    doubleValue4 = d5;
                    parseDouble = parseDouble;
                }
                valueVector.add(doubleValue3);
                valueVector.add(parseDouble);
                valueVector2.add(doubleValue4);
                controlPath.addPoint(PointFactory.create(d2, d4));
                doubleR.append(SplineRenderer.createNurbsSpline(controlPath, valueVector, valueVector2, i), true);
            } else {
                throw new POIXMLException("Invalid NURBS formula -- need 4 + n*4 arguments, got " + split.length);
            }
        }
    }
}
