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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFSeriesAxis extends XDDFChartAxis {
    private CTSerAx ctSerAx;

    public double getMajorUnit() {
        return Double.NaN;
    }

    public double getMinorUnit() {
        return Double.NaN;
    }

    public boolean isSetMajorUnit() {
        return false;
    }

    public boolean isSetMinorUnit() {
        return false;
    }

    public void setMajorUnit(double d) {
    }

    public void setMinorUnit(double d) {
    }

    public XDDFSeriesAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        initializeAxis(cTPlotArea, axisPosition);
    }

    public XDDFSeriesAxis(CTSerAx cTSerAx) {
        this.ctSerAx = cTSerAx;
    }

    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctSerAx.isSetMajorGridlines()) {
            cTChartLines = this.ctSerAx.getMajorGridlines();
        } else {
            cTChartLines = this.ctSerAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctSerAx.isSetMinorGridlines()) {
            cTChartLines = this.ctSerAx.getMinorGridlines();
        } else {
            cTChartLines = this.ctSerAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties cTShapeProperties;
        if (this.ctSerAx.isSetSpPr()) {
            cTShapeProperties = this.ctSerAx.getSpPr();
        } else {
            cTShapeProperties = this.ctSerAx.addNewSpPr();
        }
        return new XDDFShapeProperties(cTShapeProperties);
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody cTTextBody;
        if (this.ctSerAx.isSetTxPr()) {
            cTTextBody = this.ctSerAx.getTxPr();
        } else {
            cTTextBody = this.ctSerAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(cTTextBody));
    }

    public void setTitle(String str) {
        if (!this.ctSerAx.isSetTitle()) {
            this.ctSerAx.addNewTitle();
        }
        XDDFTitle xDDFTitle = new XDDFTitle((TextContainer) null, this.ctSerAx.getTitle());
        xDDFTitle.setOverlay(false);
        xDDFTitle.setText(str);
    }

    public void crossAxis(XDDFChartAxis xDDFChartAxis) {
        this.ctSerAx.getCrossAx().setVal(xDDFChartAxis.getId());
    }

    /* access modifiers changed from: protected */
    public CTUnsignedInt getCTAxId() {
        return this.ctSerAx.getAxId();
    }

    /* access modifiers changed from: protected */
    public CTAxPos getCTAxPos() {
        return this.ctSerAx.getAxPos();
    }

    public boolean hasNumberFormat() {
        return this.ctSerAx.isSetNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTNumFmt getCTNumFmt() {
        if (this.ctSerAx.isSetNumFmt()) {
            return this.ctSerAx.getNumFmt();
        }
        return this.ctSerAx.addNewNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTScaling getCTScaling() {
        return this.ctSerAx.getScaling();
    }

    /* access modifiers changed from: protected */
    public CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctSerAx.getCrosses();
        return crosses == null ? this.ctSerAx.addNewCrosses() : crosses;
    }

    /* access modifiers changed from: protected */
    public CTBoolean getDelete() {
        return this.ctSerAx.getDelete();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMajorCTTickMark() {
        return this.ctSerAx.getMajorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMinorCTTickMark() {
        return this.ctSerAx.getMinorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickLblPos getCTTickLblPos() {
        return this.ctSerAx.getTickLblPos();
    }

    private void initializeAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        long nextAxId = getNextAxId(cTPlotArea);
        CTSerAx addNewSerAx = cTPlotArea.addNewSerAx();
        this.ctSerAx = addNewSerAx;
        addNewSerAx.addNewAxId().setVal(nextAxId);
        this.ctSerAx.addNewAxPos();
        this.ctSerAx.addNewScaling();
        this.ctSerAx.addNewCrosses();
        this.ctSerAx.addNewCrossAx();
        this.ctSerAx.addNewTickLblPos();
        this.ctSerAx.addNewDelete();
        this.ctSerAx.addNewMajorTickMark();
        this.ctSerAx.addNewMinorTickMark();
        setPosition(axisPosition);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
