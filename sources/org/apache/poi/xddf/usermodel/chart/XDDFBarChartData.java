package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFBarChartData extends XDDFChartData {
    private CTBarChart chart;

    @Internal
    protected XDDFBarChartData(XDDFChart xDDFChart, CTBarChart cTBarChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTBarChart;
        if (cTBarChart.getBarDir() == null) {
            cTBarChart.addNewBarDir().setVal(BarDirection.BAR.underlying);
        }
        for (CTBarSer next : cTBarChart.getSerList()) {
            this.series.add(new Series(next, next.getCat(), next.getVal()));
        }
        defineAxes(map, map2);
    }

    private void defineAxes(Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        if (this.chart.sizeOfAxIdArray() == 0) {
            for (Long longValue : map.keySet()) {
                this.chart.addNewAxId().setVal(longValue.longValue());
            }
            for (Long longValue2 : map2.keySet()) {
                this.chart.addNewAxId().setVal(longValue2.longValue());
            }
        }
        defineAxes(this.chart.getAxIdArray(), map, map2);
    }

    /* access modifiers changed from: protected */
    @Internal
    public void removeCTSeries(int i) {
        this.chart.removeSer(i);
    }

    public void setVaryColors(Boolean bool) {
        if (bool == null) {
            if (this.chart.isSetVaryColors()) {
                this.chart.unsetVaryColors();
            }
        } else if (this.chart.isSetVaryColors()) {
            this.chart.getVaryColors().setVal(bool.booleanValue());
        } else {
            this.chart.addNewVaryColors().setVal(bool.booleanValue());
        }
    }

    public BarDirection getBarDirection() {
        return BarDirection.valueOf(this.chart.getBarDir().getVal());
    }

    public void setBarDirection(BarDirection barDirection) {
        this.chart.getBarDir().setVal(barDirection.underlying);
    }

    public BarGrouping getBarGrouping() {
        if (this.chart.isSetGrouping()) {
            return BarGrouping.valueOf(this.chart.getGrouping().getVal());
        }
        return null;
    }

    public void setBarGrouping(BarGrouping barGrouping) {
        if (barGrouping == null) {
            if (this.chart.isSetGrouping()) {
                this.chart.unsetGrouping();
            }
        } else if (this.chart.isSetGrouping()) {
            this.chart.getGrouping().setVal(barGrouping.underlying);
        } else {
            this.chart.addNewGrouping().setVal(barGrouping.underlying);
        }
    }

    public Integer getGapWidth() {
        if (this.chart.isSetGapWidth()) {
            return Integer.valueOf((int) (((double) POIXMLUnits.parsePercent(this.chart.getGapWidth().xgetVal())) / 1000.0d));
        }
        return null;
    }

    public void setGapWidth(Integer num) {
        if (num == null) {
            if (this.chart.isSetGapWidth()) {
                this.chart.unsetGapWidth();
            }
        } else if (this.chart.isSetGapWidth()) {
            this.chart.getGapWidth().setVal(num);
        } else {
            this.chart.addNewGapWidth().setVal(num);
        }
    }

    public Byte getOverlap() {
        if (this.chart.isSetOverlap()) {
            return Byte.valueOf((byte) (POIXMLUnits.parsePercent(this.chart.getOverlap().xgetVal()) / 1000));
        }
        return null;
    }

    public void setOverlap(Byte b) {
        if (b == null) {
            if (this.chart.isSetOverlap()) {
                this.chart.unsetOverlap();
            }
        } else if (b.byteValue() >= -100 && 100 >= b.byteValue()) {
            if (this.chart.isSetOverlap()) {
                this.chart.getOverlap().setVal(b);
            } else {
                this.chart.addNewOverlap().setVal(b);
            }
        }
    }

    public XDDFChartData.Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
        long incrementSeriesCount = this.parent.incrementSeriesCount();
        CTBarSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewTx();
        addNewSer.addNewCat();
        addNewSer.addNewVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, xDDFNumericalDataSource);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTBarSer series;

        protected Series(CTBarSer cTBarSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTBarSer;
        }

        protected Series(CTBarSer cTBarSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTBarSer;
        }

        /* access modifiers changed from: protected */
        public CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
        }

        public boolean hasErrorBars() {
            return this.series.isSetErrBars();
        }

        public XDDFErrorBars getErrorBars() {
            if (this.series.isSetErrBars()) {
                return new XDDFErrorBars(this.series.getErrBars());
            }
            return null;
        }

        public void setErrorBars(XDDFErrorBars xDDFErrorBars) {
            if (xDDFErrorBars == null) {
                if (this.series.isSetErrBars()) {
                    this.series.unsetErrBars();
                }
            } else if (this.series.isSetErrBars()) {
                this.series.getErrBars().set(xDDFErrorBars.getXmlObject());
            } else {
                this.series.addNewErrBars().set(xDDFErrorBars.getXmlObject());
            }
        }

        public boolean getInvertIfNegative() {
            if (this.series.isSetInvertIfNegative()) {
                return this.series.getInvertIfNegative().getVal();
            }
            return false;
        }

        public void setInvertIfNegative(boolean z) {
            if (this.series.isSetInvertIfNegative()) {
                this.series.getInvertIfNegative().setVal(z);
            } else {
                this.series.addNewInvertIfNegative().setVal(z);
            }
        }

        public void setShowLeaderLines(boolean z) {
            if (!this.series.isSetDLbls()) {
                this.series.addNewDLbls();
            }
            if (this.series.getDLbls().isSetShowLeaderLines()) {
                this.series.getDLbls().getShowLeaderLines().setVal(z);
            } else {
                this.series.getDLbls().addNewShowLeaderLines().setVal(z);
            }
        }

        public XDDFShapeProperties getShapeProperties() {
            if (this.series.isSetSpPr()) {
                return new XDDFShapeProperties(this.series.getSpPr());
            }
            return null;
        }

        public void setShapeProperties(XDDFShapeProperties xDDFShapeProperties) {
            if (xDDFShapeProperties == null) {
                if (this.series.isSetSpPr()) {
                    this.series.unsetSpPr();
                }
            } else if (this.series.isSetSpPr()) {
                this.series.setSpPr(xDDFShapeProperties.getXmlObject());
            } else {
                this.series.addNewSpPr().set(xDDFShapeProperties.getXmlObject());
            }
        }

        /* access modifiers changed from: protected */
        public CTAxDataSource getAxDS() {
            return this.series.getCat();
        }

        /* access modifiers changed from: protected */
        public CTNumDataSource getNumDS() {
            return this.series.getVal();
        }

        /* access modifiers changed from: protected */
        public void setIndex(long j) {
            this.series.getIdx().setVal(j);
        }

        /* access modifiers changed from: protected */
        public void setOrder(long j) {
            this.series.getOrder().setVal(j);
        }

        /* access modifiers changed from: protected */
        public List<CTDPt> getDPtList() {
            return this.series.getDPtList();
        }
    }
}
