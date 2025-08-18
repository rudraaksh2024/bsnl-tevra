package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFDateAxis extends XDDFChartAxis {
    private CTDateAx ctDateAx;

    public XDDFDateAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        initializeAxis(cTPlotArea, axisPosition);
    }

    public XDDFDateAxis(CTDateAx cTDateAx) {
        this.ctDateAx = cTDateAx;
    }

    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctDateAx.isSetMajorGridlines()) {
            cTChartLines = this.ctDateAx.getMajorGridlines();
        } else {
            cTChartLines = this.ctDateAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines cTChartLines;
        if (this.ctDateAx.isSetMinorGridlines()) {
            cTChartLines = this.ctDateAx.getMinorGridlines();
        } else {
            cTChartLines = this.ctDateAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(cTChartLines));
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties cTShapeProperties;
        if (this.ctDateAx.isSetSpPr()) {
            cTShapeProperties = this.ctDateAx.getSpPr();
        } else {
            cTShapeProperties = this.ctDateAx.addNewSpPr();
        }
        return new XDDFShapeProperties(cTShapeProperties);
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody cTTextBody;
        if (this.ctDateAx.isSetTxPr()) {
            cTTextBody = this.ctDateAx.getTxPr();
        } else {
            cTTextBody = this.ctDateAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(cTTextBody));
    }

    public void setTitle(String str) {
        if (!this.ctDateAx.isSetTitle()) {
            this.ctDateAx.addNewTitle();
        }
        XDDFTitle xDDFTitle = new XDDFTitle((TextContainer) null, this.ctDateAx.getTitle());
        xDDFTitle.setOverlay(false);
        xDDFTitle.setText(str);
    }

    public boolean isSetMinorUnit() {
        return this.ctDateAx.isSetMinorUnit();
    }

    public void setMinorUnit(double d) {
        if (Double.isNaN(d)) {
            if (this.ctDateAx.isSetMinorUnit()) {
                this.ctDateAx.unsetMinorUnit();
            }
        } else if (this.ctDateAx.isSetMinorUnit()) {
            this.ctDateAx.getMinorUnit().setVal(d);
        } else {
            this.ctDateAx.addNewMinorUnit().setVal(d);
        }
    }

    public double getMinorUnit() {
        if (this.ctDateAx.isSetMinorUnit()) {
            return this.ctDateAx.getMinorUnit().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMajorUnit() {
        return this.ctDateAx.isSetMajorUnit();
    }

    public void setMajorUnit(double d) {
        if (Double.isNaN(d)) {
            if (this.ctDateAx.isSetMajorUnit()) {
                this.ctDateAx.unsetMajorUnit();
            }
        } else if (this.ctDateAx.isSetMajorUnit()) {
            this.ctDateAx.getMajorUnit().setVal(d);
        } else {
            this.ctDateAx.addNewMajorUnit().setVal(d);
        }
    }

    public double getMajorUnit() {
        if (this.ctDateAx.isSetMajorUnit()) {
            return this.ctDateAx.getMajorUnit().getVal();
        }
        return Double.NaN;
    }

    public void crossAxis(XDDFChartAxis xDDFChartAxis) {
        this.ctDateAx.getCrossAx().setVal(xDDFChartAxis.getId());
    }

    /* access modifiers changed from: protected */
    public CTUnsignedInt getCTAxId() {
        return this.ctDateAx.getAxId();
    }

    /* access modifiers changed from: protected */
    public CTAxPos getCTAxPos() {
        return this.ctDateAx.getAxPos();
    }

    public boolean hasNumberFormat() {
        return this.ctDateAx.isSetNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTNumFmt getCTNumFmt() {
        if (this.ctDateAx.isSetNumFmt()) {
            return this.ctDateAx.getNumFmt();
        }
        return this.ctDateAx.addNewNumFmt();
    }

    /* access modifiers changed from: protected */
    public CTScaling getCTScaling() {
        return this.ctDateAx.getScaling();
    }

    /* access modifiers changed from: protected */
    public CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctDateAx.getCrosses();
        return crosses == null ? this.ctDateAx.addNewCrosses() : crosses;
    }

    /* access modifiers changed from: protected */
    public CTBoolean getDelete() {
        return this.ctDateAx.getDelete();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMajorCTTickMark() {
        return this.ctDateAx.getMajorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickMark getMinorCTTickMark() {
        return this.ctDateAx.getMinorTickMark();
    }

    /* access modifiers changed from: protected */
    public CTTickLblPos getCTTickLblPos() {
        return this.ctDateAx.getTickLblPos();
    }

    private void initializeAxis(CTPlotArea cTPlotArea, AxisPosition axisPosition) {
        long nextAxId = getNextAxId(cTPlotArea);
        CTDateAx addNewDateAx = cTPlotArea.addNewDateAx();
        this.ctDateAx = addNewDateAx;
        addNewDateAx.addNewAxId().setVal(nextAxId);
        this.ctDateAx.addNewAuto().setVal(false);
        this.ctDateAx.addNewAxPos();
        this.ctDateAx.addNewScaling();
        this.ctDateAx.addNewCrosses();
        this.ctDateAx.addNewCrossAx();
        this.ctDateAx.addNewTickLblPos();
        this.ctDateAx.addNewDelete();
        this.ctDateAx.addNewMajorTickMark();
        this.ctDateAx.addNewMinorTickMark();
        this.ctDateAx.addNewNumFmt().setSourceLinked(true);
        this.ctDateAx.getNumFmt().setFormatCode("");
        setPosition(axisPosition);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
