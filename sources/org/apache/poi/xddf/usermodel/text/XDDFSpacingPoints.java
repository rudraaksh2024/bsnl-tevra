package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

public class XDDFSpacingPoints extends XDDFSpacing {
    private CTTextSpacingPoint points;

    public XDDFSpacingPoints(double d) {
        this(CTTextSpacing.Factory.newInstance(), CTTextSpacingPoint.Factory.newInstance());
        if (this.spacing.isSetSpcPct()) {
            this.spacing.unsetSpcPct();
        }
        this.spacing.setSpcPts(this.points);
        setPoints(d);
    }

    @Internal
    protected XDDFSpacingPoints(CTTextSpacing cTTextSpacing, CTTextSpacingPoint cTTextSpacingPoint) {
        super(cTTextSpacing);
        this.points = cTTextSpacingPoint;
    }

    public XDDFSpacing.Kind getType() {
        return XDDFSpacing.Kind.POINTS;
    }

    public double getPoints() {
        return ((double) this.points.getVal()) * 0.01d;
    }

    public void setPoints(double d) {
        this.points.setVal((int) (d * 100.0d));
    }
}
