package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFValueAxis extends XDDFChartAxis {
    private CTValAx ctValAx;

    public XDDFValueAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        initializeAxis(cTPlotArea, axisPosition);
    }

    public XDDFValueAxis(CTValAx cTValAx) {
        this.ctValAx = cTValAx;
    }

    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctValAx.isSetMajorGridlines()) {
            cTChartLines = this.ctValAx.getMajorGridlines();
        } else {
            cTChartLines = this.ctValAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctValAx.isSetMinorGridlines()) {
            cTChartLines = this.ctValAx.getMinorGridlines();
        } else {
            cTChartLines = this.ctValAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties cTShapeProperties;
        if (this.ctValAx.isSetSpPr()) {
            cTShapeProperties = this.ctValAx.getSpPr();
        } else {
            cTShapeProperties = this.ctValAx.addNewSpPr();
        }
        return new XDDFShapeProperties(cTShapeProperties);
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody cTTextBody;
        if (this.ctValAx.isSetTxPr()) {
            cTTextBody = this.ctValAx.getTxPr();
        } else {
            cTTextBody = this.ctValAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(cTTextBody));
    }

    public void setTitle(String str) {
        if (!this.ctValAx.isSetTitle()) {
            this.ctValAx.addNewTitle();
        }
        XDDFTitle xDDFTitle = new XDDFTitle((TextContainer) null, this.ctValAx.getTitle());
        xDDFTitle.setOverlay(false);
        xDDFTitle.setText(str);
    }

    public boolean isSetMinorUnit() {
        return this.ctValAx.isSetMinorUnit();
    }

    public void setMinorUnit(double d) {
        if (Double.isNaN(d)) {
            if (this.ctValAx.isSetMinorUnit()) {
                this.ctValAx.unsetMinorUnit();
            }
        } else if (this.ctValAx.isSetMinorUnit()) {
            this.ctValAx.getMinorUnit().setVal(d);
        } else {
            this.ctValAx.addNewMinorUnit().setVal(d);
        }
    }

    public double getMinorUnit() {
        if (this.ctValAx.isSetMinorUnit()) {
            return this.ctValAx.getMinorUnit().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMajorUnit() {
        return this.ctValAx.isSetMajorUnit();
    }

    public void setMajorUnit(double d) {
        if (Double.isNaN(d)) {
            if (this.ctValAx.isSetMajorUnit()) {
                this.ctValAx.unsetMajorUnit();
            }
        } else if (this.ctValAx.isSetMajorUnit()) {
            this.ctValAx.getMajorUnit().setVal(d);
        } else {
            this.ctValAx.addNewMajorUnit().setVal(d);
        }
    }

    public double getMajorUnit() {
        if (this.ctValAx.isSetMajorUnit()) {
            return this.ctValAx.getMajorUnit().getVal();
        }
        return Double.NaN;
    }

    public void crossAxis(XDDFChartAxis xDDFChartAxis) {
        this.ctValAx.getCrossAx().setVal(xDDFChartAxis.getId());
    }

    /* access modifiers changed from: protected */
    public CTUnsignedInt getCTAxId() {
        return this.ctValAx.getAxId();
    }

    /* access modifiers changed from: protected */
    public CTAxPos getCTAxPos() {
        return this.ctValAx.getAxPos();
    }

    public boolean hasNumberFormat() {
        return this.ctValAx.isSetNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTNumFmt getCTNumFmt() {
        if (this.ctValAx.isSetNumFmt()) {
            return this.ctValAx.getNumFmt();
        }
        return this.ctValAx.addNewNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTScaling getCTScaling() {
        return this.ctValAx.getScaling();
    }

    /* access modifiers changed from: protected */
    public CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctValAx.getCrosses();
        return crosses == null ? this.ctValAx.addNewCrosses() : crosses;
    }

    /* access modifiers changed from: protected */
    public CTBoolean getDelete() {
        return this.ctValAx.getDelete();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMajorCTTickMark() {
        return this.ctValAx.getMajorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMinorCTTickMark() {
        return this.ctValAx.getMinorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickLblPos getCTTickLblPos() {
        return this.ctValAx.getTickLblPos();
    }

    public AxisCrossBetween getCrossBetween() {
        return AxisCrossBetween.valueOf(this.ctValAx.getCrossBetween().getVal());
    }

    public void setCrossBetween(AxisCrossBetween axisCrossBetween) {
        this.ctValAx.getCrossBetween().setVal(axisCrossBetween.underlying);
    }

    private void initializeAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        long nextAxId = getNextAxId(cTPlotArea);
        CTValAx addNewValAx = cTPlotArea.addNewValAx();
        this.ctValAx = addNewValAx;
        addNewValAx.addNewAxId().setVal(nextAxId);
        this.ctValAx.addNewAxPos();
        this.ctValAx.addNewScaling();
        this.ctValAx.addNewCrossBetween();
        this.ctValAx.addNewCrosses();
        this.ctValAx.addNewCrossAx();
        this.ctValAx.addNewTickLblPos();
        this.ctValAx.addNewDelete();
        this.ctValAx.addNewMajorTickMark();
        this.ctValAx.addNewMinorTickMark();
        setPosition(axisPosition);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrossBetween(AxisCrossBetween.MIDPOINT_CATEGORY);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
