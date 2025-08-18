package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFArea3DChartData extends XDDFChartData {
    private CTArea3DChart chart;

    @Internal
    protected XDDFArea3DChartData(XDDFChart xDDFChart, CTArea3DChart cTArea3DChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTArea3DChart;
        for (CTAreaSer next : cTArea3DChart.getSerList()) {
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

    public Grouping getGrouping() {
        if (this.chart.isSetGrouping()) {
            return Grouping.valueOf(this.chart.getGrouping().getVal());
        }
        return null;
    }

    public void setGrouping(Grouping grouping) {
        if (grouping == null) {
            if (this.chart.isSetGrouping()) {
                this.chart.unsetGrouping();
            }
        } else if (this.chart.isSetGrouping()) {
            this.chart.getGrouping().setVal(grouping.underlying);
        } else {
            this.chart.addNewGrouping().setVal(grouping.underlying);
        }
    }

    public Integer getGapDepth() {
        if (this.chart.isSetGapDepth()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.chart.getGapDepth().xgetVal()) / 1000);
        }
        return null;
    }

    public void setGapDepth(Integer num) {
        if (num == null) {
            if (this.chart.isSetGapDepth()) {
                this.chart.unsetGapDepth();
            }
        } else if (this.chart.isSetGapDepth()) {
            this.chart.getGapDepth().setVal(num);
        } else {
            this.chart.addNewGapDepth().setVal(num);
        }
    }

    public XDDFChartData.Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
        long incrementSeriesCount = this.parent.incrementSeriesCount();
        CTAreaSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewCat();
        addNewSer.addNewVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, xDDFNumericalDataSource);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTAreaSer series;

        protected Series(CTAreaSer cTAreaSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTAreaSer;
        }

        protected Series(CTAreaSer cTAreaSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTAreaSer;
        }

        /* access modifiers changed from: protected */
        public CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
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
