package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFCategoryAxis extends XDDFChartAxis {
    private CTCatAx ctCatAx;

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

    public XDDFCategoryAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        initializeAxis(cTPlotArea, axisPosition);
    }

    public XDDFCategoryAxis(CTCatAx cTCatAx) {
        this.ctCatAx = cTCatAx;
    }

    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctCatAx.isSetMajorGridlines()) {
            cTChartLines = this.ctCatAx.getMajorGridlines();
        } else {
            cTChartLines = this.ctCatAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctCatAx.isSetMinorGridlines()) {
            cTChartLines = this.ctCatAx.getMinorGridlines();
        } else {
            cTChartLines = this.ctCatAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties cTShapeProperties;
        if (this.ctCatAx.isSetSpPr()) {
            cTShapeProperties = this.ctCatAx.getSpPr();
        } else {
            cTShapeProperties = this.ctCatAx.addNewSpPr();
        }
        return new XDDFShapeProperties(cTShapeProperties);
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody cTTextBody;
        if (this.ctCatAx.isSetTxPr()) {
            cTTextBody = this.ctCatAx.getTxPr();
        } else {
            cTTextBody = this.ctCatAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(cTTextBody));
    }

    public void setTitle(String str) {
        if (!this.ctCatAx.isSetTitle()) {
            this.ctCatAx.addNewTitle();
        }
        XDDFTitle xDDFTitle = new XDDFTitle((TextContainer) null, this.ctCatAx.getTitle());
        xDDFTitle.setOverlay(false);
        xDDFTitle.setText(str);
    }

    public void crossAxis(XDDFChartAxis xDDFChartAxis) {
        this.ctCatAx.getCrossAx().setVal(xDDFChartAxis.getId());
    }

    /* access modifiers changed from: protected */
    public CTUnsignedInt getCTAxId() {
        return this.ctCatAx.getAxId();
    }

    /* access modifiers changed from: protected */
    public CTAxPos getCTAxPos() {
        return this.ctCatAx.getAxPos();
    }

    public boolean hasNumberFormat() {
        return this.ctCatAx.isSetNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTNumFmt getCTNumFmt() {
        if (this.ctCatAx.isSetNumFmt()) {
            return this.ctCatAx.getNumFmt();
        }
        return this.ctCatAx.addNewNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTScaling getCTScaling() {
        return this.ctCatAx.getScaling();
    }

    /* access modifiers changed from: protected */
    public CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctCatAx.getCrosses();
        return crosses == null ? this.ctCatAx.addNewCrosses() : crosses;
    }

    /* access modifiers changed from: protected */
    public CTBoolean getDelete() {
        return this.ctCatAx.getDelete();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMajorCTTickMark() {
        return this.ctCatAx.getMajorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMinorCTTickMark() {
        return this.ctCatAx.getMinorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickLblPos getCTTickLblPos() {
        return this.ctCatAx.getTickLblPos();
    }

    public AxisLabelAlignment getLabelAlignment() {
        return AxisLabelAlignment.valueOf(this.ctCatAx.getLblAlgn().getVal());
    }

    public void setLabelAlignment(AxisLabelAlignment axisLabelAlignment) {
        this.ctCatAx.getLblAlgn().setVal(axisLabelAlignment.underlying);
    }

    private void initializeAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        long nextAxId = getNextAxId(cTPlotArea);
        CTCatAx addNewCatAx = cTPlotArea.addNewCatAx();
        this.ctCatAx = addNewCatAx;
        addNewCatAx.addNewAxId().setVal(nextAxId);
        this.ctCatAx.addNewAuto().setVal(false);
        this.ctCatAx.addNewAxPos();
        this.ctCatAx.addNewScaling();
        this.ctCatAx.addNewCrosses();
        this.ctCatAx.addNewCrossAx();
        this.ctCatAx.addNewTickLblPos();
        this.ctCatAx.addNewDelete();
        this.ctCatAx.addNewMajorTickMark();
        this.ctCatAx.addNewMinorTickMark();
        this.ctCatAx.addNewNumFmt().setSourceLinked(true);
        this.ctCatAx.getNumFmt().setFormatCode("");
        setPosition(axisPosition);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
