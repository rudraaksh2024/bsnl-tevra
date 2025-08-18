package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;

public class XDDFDataPoint {
    private final CTDPt point;

    @Internal
    protected XDDFDataPoint(CTDPt cTDPt) {
        this.point = cTDPt;
    }

    public long getIndex() {
        return this.point.getIdx().getVal();
    }

    public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
        XDDFShapeProperties shapeProperties = getShapeProperties();
        if (shapeProperties == null) {
            shapeProperties = new XDDFShapeProperties();
        }
        shapeProperties.setFillProperties(xDDFFillProperties);
        setShapeProperties(shapeProperties);
    }

    public void setLineProperties(XDDFLineProperties xDDFLineProperties) {
        XDDFShapeProperties shapeProperties = getShapeProperties();
        if (shapeProperties == null) {
            shapeProperties = new XDDFShapeProperties();
        }
        shapeProperties.setLineProperties(xDDFLineProperties);
        setShapeProperties(shapeProperties);
    }

    public XDDFShapeProperties getShapeProperties() {
        if (this.point.isSetSpPr()) {
            return new XDDFShapeProperties(this.point.getSpPr());
        }
        return null;
    }

    public void setShapeProperties(XDDFShapeProperties xDDFShapeProperties) {
        if (xDDFShapeProperties == null) {
            if (this.point.isSetSpPr()) {
                this.point.unsetSpPr();
            }
        } else if (this.point.isSetSpPr()) {
            this.point.setSpPr(xDDFShapeProperties.getXmlObject());
        } else {
            this.point.addNewSpPr().set(xDDFShapeProperties.getXmlObject());
        }
    }

    public Long getExplosion() {
        if (this.point.isSetExplosion()) {
            return Long.valueOf(this.point.getExplosion().getVal());
        }
        return null;
    }

    public void setExplosion(Long l) {
        if (l == null) {
            if (this.point.isSetExplosion()) {
                this.point.unsetExplosion();
            }
        } else if (this.point.isSetExplosion()) {
            this.point.getExplosion().setVal(l.longValue());
        } else {
            this.point.addNewExplosion().setVal(l.longValue());
        }
    }

    public boolean getInvertIfNegative() {
        if (this.point.isSetInvertIfNegative()) {
            return this.point.getInvertIfNegative().getVal();
        }
        return false;
    }

    public void setInvertIfNegative(boolean z) {
        if (this.point.isSetInvertIfNegative()) {
            this.point.getInvertIfNegative().setVal(z);
        } else {
            this.point.addNewInvertIfNegative().setVal(z);
        }
    }

    public void setMarkerSize(short s) {
        if (s < 2 || 72 < s) {
            throw new IllegalArgumentException("Minimum inclusive: 2; Maximum inclusive: 72");
        }
        CTMarker marker = getMarker();
        if (marker.isSetSize()) {
            marker.getSize().setVal(s);
        } else {
            marker.addNewSize().setVal(s);
        }
    }

    public void setMarkerStyle(MarkerStyle markerStyle) {
        CTMarker marker = getMarker();
        if (marker.isSetSymbol()) {
            marker.getSymbol().setVal(markerStyle.underlying);
        } else {
            marker.addNewSymbol().setVal(markerStyle.underlying);
        }
    }

    private CTMarker getMarker() {
        if (this.point.isSetMarker()) {
            return this.point.getMarker();
        }
        return this.point.addNewMarker();
    }
}
