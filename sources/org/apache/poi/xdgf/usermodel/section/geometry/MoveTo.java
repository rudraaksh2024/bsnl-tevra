package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public class MoveTo implements GeometryRow {
    MoveTo _master;
    Boolean deleted;
    Double x;
    Double y;

    public MoveTo(RowType rowType) {
        if (rowType.isSetDel()) {
            this.deleted = Boolean.valueOf(rowType.getDel());
        }
        for (CellType cellType : rowType.getCellArray()) {
            String n = cellType.getN();
            if (n.equals("X")) {
                this.x = XDGFCell.parseDoubleValue(cellType);
            } else if (n.equals("Y")) {
                this.y = XDGFCell.parseDoubleValue(cellType);
            } else {
                throw new POIXMLException("Invalid cell '" + n + "' in MoveTo row");
            }
        }
    }

    public String toString() {
        return "MoveTo: x=" + getX() + "; y=" + getY();
    }

    public boolean getDel() {
        Boolean bool = this.deleted;
        if (bool != null) {
            return bool.booleanValue();
        }
        MoveTo moveTo = this._master;
        return moveTo != null && moveTo.getDel();
    }

    public Double getX() {
        Double d = this.x;
        return d == null ? this._master.x : d;
    }

    public Double getY() {
        Double d = this.y;
        return d == null ? this._master.y : d;
    }

    public void setupMaster(GeometryRow geometryRow) {
        this._master = (MoveTo) geometryRow;
    }

    public void addToPath(Path2D.Double doubleR, XDGFShape xDGFShape) {
        if (!getDel()) {
            doubleR.moveTo(getX().doubleValue(), getY().doubleValue());
        }
    }
}
