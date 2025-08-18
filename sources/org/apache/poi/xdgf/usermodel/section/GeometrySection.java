package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.awt.geom.Path2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.geom.SplineCollector;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.geometry.Ellipse;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;
import org.apache.poi.xdgf.usermodel.section.geometry.InfiniteLine;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

public class GeometrySection extends XDGFSection {
    GeometrySection _master;
    SortedMap<Long, GeometryRow> _rows = new TreeMap();

    public GeometrySection(SectionType sectionType, XDGFSheet xDGFSheet) {
        super(sectionType, xDGFSheet);
        RowType[] rowArray = sectionType.getRowArray();
        int length = rowArray.length;
        int i = 0;
        while (i < length) {
            RowType rowType = rowArray[i];
            if (!this._rows.containsKey(Long.valueOf(rowType.getIX()))) {
                this._rows.put(Long.valueOf(rowType.getIX()), GeometryRowTypes.load(rowType));
                i++;
            } else {
                throw new POIXMLException("Index element '" + rowType.getIX() + "' already exists");
            }
        }
    }

    public void setupMaster(XDGFSection xDGFSection) {
        this._master = (GeometrySection) xDGFSection;
        for (Map.Entry next : this._rows.entrySet()) {
            GeometryRow geometryRow = (GeometryRow) this._master._rows.get(next.getKey());
            if (geometryRow != null) {
                try {
                    ((GeometryRow) next.getValue()).setupMaster(geometryRow);
                } catch (ClassCastException unused) {
                }
            }
        }
    }

    public Boolean getNoShow() {
        Boolean maybeGetBoolean = XDGFCell.maybeGetBoolean(this._cells, "NoShow");
        if (maybeGetBoolean != null) {
            return maybeGetBoolean;
        }
        GeometrySection geometrySection = this._master;
        if (geometrySection != null) {
            return geometrySection.getNoShow();
        }
        return false;
    }

    @Internal
    public static <T, S extends SortedMap<Long, T>> Collection<T> combineGeometries(S s, S s2) {
        if (s2 != null) {
            S treeMap = new TreeMap(s2);
            treeMap.putAll(s);
            s = treeMap;
        }
        return s.values();
    }

    public Iterable<GeometryRow> getCombinedRows() {
        SortedMap<Long, GeometryRow> sortedMap = this._rows;
        GeometrySection geometrySection = this._master;
        return combineGeometries(sortedMap, geometrySection == null ? null : geometrySection._rows);
    }

    public Path2D.Double getPath(XDGFShape xDGFShape) {
        GeometryRow geometryRow;
        Iterator<GeometryRow> it = getCombinedRows().iterator();
        GeometryRow next = it.hasNext() ? it.next() : null;
        if (next instanceof Ellipse) {
            return ((Ellipse) next).getPath();
        }
        if (next instanceof InfiniteLine) {
            return ((InfiniteLine) next).getPath();
        }
        if (!(next instanceof SplineStart)) {
            Path2D.Double doubleR = new Path2D.Double();
            SplineCollector splineCollector = null;
            while (true) {
                if (next != null) {
                    geometryRow = null;
                } else if (!it.hasNext()) {
                    if (splineCollector != null) {
                        splineCollector.addToPath(doubleR, xDGFShape);
                    }
                    return doubleR;
                } else {
                    geometryRow = next;
                    next = it.next();
                }
                if (next instanceof SplineStart) {
                    if (splineCollector == null) {
                        splineCollector = new SplineCollector((SplineStart) next);
                    } else {
                        throw new POIXMLException("SplineStart found multiple times!");
                    }
                } else if (!(next instanceof SplineKnot)) {
                    if (splineCollector != null) {
                        splineCollector.addToPath(doubleR, xDGFShape);
                        splineCollector = null;
                    }
                    next.addToPath(doubleR, xDGFShape);
                } else if (splineCollector != null) {
                    splineCollector.addKnot((SplineKnot) next);
                } else {
                    throw new POIXMLException("SplineKnot found without SplineStart!");
                }
                next = geometryRow;
            }
        } else {
            throw new POIXMLException("SplineStart must be preceded by another type");
        }
    }
}
