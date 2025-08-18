package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;

public class XDDFSpacingPercent extends XDDFSpacing {
    private CTTextSpacingPercent percent;
    private Double scale;

    public XDDFSpacingPercent(double d) {
        this(CTTextSpacing.Factory.newInstance(), CTTextSpacingPercent.Factory.newInstance(), (Double) null);
        if (this.spacing.isSetSpcPts()) {
            this.spacing.unsetSpcPts();
        }
        this.spacing.setSpcPct(this.percent);
        setPercent(d);
    }

    @Internal
    protected XDDFSpacingPercent(CTTextSpacing cTTextSpacing, CTTextSpacingPercent cTTextSpacingPercent, Double d) {
        super(cTTextSpacing);
        this.percent = cTTextSpacingPercent;
        this.scale = Double.valueOf(d != null ? 0.001d * d.doubleValue() : 0.001d);
    }

    public XDDFSpacing.Kind getType() {
        return XDDFSpacing.Kind.PERCENT;
    }

    public double getPercent() {
        return ((double) POIXMLUnits.parsePercent(this.percent.xgetVal())) * this.scale.doubleValue();
    }

    public void setPercent(double d) {
        this.percent.setVal(Integer.valueOf((int) (d * 1000.0d)));
    }
}
