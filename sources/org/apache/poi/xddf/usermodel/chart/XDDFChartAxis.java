package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.HasShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class XDDFChartAxis implements HasShapeProperties {
    private static final double MAX_LOG_BASE = 1000.0d;
    private static final double MIN_LOG_BASE = 2.0d;

    public abstract void crossAxis(XDDFChartAxis xDDFChartAxis);

    /* access modifiers changed from: protected */
    public abstract CTUnsignedInt getCTAxId();

    /* access modifiers changed from: protected */
    public abstract CTAxPos getCTAxPos();

    /* access modifiers changed from: protected */
    public abstract CTCrosses getCTCrosses();

    /* access modifiers changed from: protected */
    public abstract CTNumFmt getCTNumFmt();

    /* access modifiers changed from: protected */
    public abstract CTScaling getCTScaling();

    /* access modifiers changed from: protected */
    public abstract CTTickLblPos getCTTickLblPos();

    /* access modifiers changed from: protected */
    public abstract CTBoolean getDelete();

    /* access modifiers changed from: protected */
    public abstract CTTickMark getMajorCTTickMark();

    public abstract double getMajorUnit();

    /* access modifiers changed from: protected */
    public abstract CTTickMark getMinorCTTickMark();

    public abstract double getMinorUnit();

    public abstract XDDFShapeProperties getOrAddMajorGridProperties();

    public abstract XDDFShapeProperties getOrAddMinorGridProperties();

    public abstract XDDFRunProperties getOrAddTextProperties();

    public abstract boolean hasNumberFormat();

    public abstract boolean isSetMajorUnit();

    public abstract boolean isSetMinorUnit();

    public abstract void setMajorUnit(double d);

    public abstract void setMinorUnit(double d);

    public abstract void setTitle(String str);

    public long getId() {
        return getCTAxId().getVal();
    }

    public AxisPosition getPosition() {
        return AxisPosition.valueOf(getCTAxPos().getVal());
    }

    public void setPosition(AxisPosition axisPosition) {
        getCTAxPos().setVal(axisPosition.underlying);
    }

    public void setNumberFormat(String str) {
        getCTNumFmt().setFormatCode(str);
        getCTNumFmt().setSourceLinked(true);
    }

    public String getNumberFormat() {
        return getCTNumFmt().getFormatCode();
    }

    public boolean isSetLogBase() {
        return getCTScaling().isSetLogBase();
    }

    public void setLogBase(double d) {
        if (d < MIN_LOG_BASE || MAX_LOG_BASE < d) {
            throw new IllegalArgumentException("Axis log base must be between 2 and 1000 (inclusive), got: " + d);
        }
        CTScaling cTScaling = getCTScaling();
        if (cTScaling.isSetLogBase()) {
            cTScaling.getLogBase().setVal(d);
        } else {
            cTScaling.addNewLogBase().setVal(d);
        }
    }

    public double getLogBase() {
        CTScaling cTScaling = getCTScaling();
        if (cTScaling.isSetLogBase()) {
            return cTScaling.getLogBase().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMinimum() {
        return getCTScaling().isSetMin();
    }

    public void setMinimum(double d) {
        CTScaling cTScaling = getCTScaling();
        if (Double.isNaN(d)) {
            if (cTScaling.isSetMin()) {
                cTScaling.unsetMin();
            }
        } else if (cTScaling.isSetMin()) {
            cTScaling.getMin().setVal(d);
        } else {
            cTScaling.addNewMin().setVal(d);
        }
    }

    public double getMinimum() {
        CTScaling cTScaling = getCTScaling();
        if (cTScaling.isSetMin()) {
            return cTScaling.getMin().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMaximum() {
        return getCTScaling().isSetMax();
    }

    public void setMaximum(double d) {
        CTScaling cTScaling = getCTScaling();
        if (Double.isNaN(d)) {
            if (cTScaling.isSetMax()) {
                cTScaling.unsetMax();
            }
        } else if (cTScaling.isSetMax()) {
            cTScaling.getMax().setVal(d);
        } else {
            cTScaling.addNewMax().setVal(d);
        }
    }

    public double getMaximum() {
        CTScaling cTScaling = getCTScaling();
        if (cTScaling.isSetMax()) {
            return cTScaling.getMax().getVal();
        }
        return Double.NaN;
    }

    public AxisOrientation getOrientation() {
        return AxisOrientation.valueOf(getCTScaling().getOrientation().getVal());
    }

    public void setOrientation(AxisOrientation axisOrientation) {
        CTScaling cTScaling = getCTScaling();
        if (cTScaling.isSetOrientation()) {
            cTScaling.getOrientation().setVal(axisOrientation.underlying);
        } else {
            cTScaling.addNewOrientation().setVal(axisOrientation.underlying);
        }
    }

    public AxisCrosses getCrosses() {
        return AxisCrosses.valueOf(getCTCrosses().getVal());
    }

    public void setCrosses(AxisCrosses axisCrosses) {
        getCTCrosses().setVal(axisCrosses.underlying);
    }

    public boolean isVisible() {
        return !getDelete().getVal();
    }

    public void setVisible(boolean z) {
        getDelete().setVal(!z);
    }

    public AxisTickMark getMajorTickMark() {
        return AxisTickMark.valueOf(getMajorCTTickMark().getVal());
    }

    public void setMajorTickMark(AxisTickMark axisTickMark) {
        getMajorCTTickMark().setVal(axisTickMark.underlying);
    }

    public AxisTickMark getMinorTickMark() {
        return AxisTickMark.valueOf(getMinorCTTickMark().getVal());
    }

    public void setMinorTickMark(AxisTickMark axisTickMark) {
        getMinorCTTickMark().setVal(axisTickMark.underlying);
    }

    public AxisTickLabelPosition getTickLabelPosition() {
        return AxisTickLabelPosition.valueOf(getCTTickLblPos().getVal());
    }

    public void setTickLabelPosition(AxisTickLabelPosition axisTickLabelPosition) {
        getCTTickLblPos().setVal(axisTickLabelPosition.underlying);
    }

    /* access modifiers changed from: protected */
    public CTTextCharacterProperties getOrAddTextProperties(CTTextBody cTTextBody) {
        CTTextParagraph cTTextParagraph;
        CTTextParagraphProperties cTTextParagraphProperties;
        if (cTTextBody.getBodyPr() == null) {
            cTTextBody.addNewBodyPr();
        }
        if (cTTextBody.sizeOfPArray() > 0) {
            cTTextParagraph = cTTextBody.getPArray(0);
        } else {
            cTTextParagraph = cTTextBody.addNewP();
        }
        if (cTTextParagraph.isSetPPr()) {
            cTTextParagraphProperties = cTTextParagraph.getPPr();
        } else {
            cTTextParagraphProperties = cTTextParagraph.addNewPPr();
        }
        if (cTTextParagraphProperties.isSetDefRPr()) {
            return cTTextParagraphProperties.getDefRPr();
        }
        return cTTextParagraphProperties.addNewDefRPr();
    }

    /* access modifiers changed from: protected */
    public CTShapeProperties getOrAddLinesProperties(CTChartLines cTChartLines) {
        if (cTChartLines.isSetSpPr()) {
            return cTChartLines.getSpPr();
        }
        return cTChartLines.addNewSpPr();
    }

    /* access modifiers changed from: protected */
    public long getNextAxId(CTPlotArea cTPlotArea) {
        return ((long) cTPlotArea.sizeOfValAxArray()) + 0 + ((long) cTPlotArea.sizeOfCatAxArray()) + ((long) cTPlotArea.sizeOfDateAxArray()) + ((long) cTPlotArea.sizeOfSerAxArray());
    }
}
