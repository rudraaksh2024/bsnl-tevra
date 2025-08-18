package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFLine3DChartData extends XDDFChartData {
    private CTLine3DChart chart;

    @Internal
    protected XDDFLine3DChartData(XDDFChart xDDFChart, CTLine3DChart cTLine3DChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTLine3DChart;
        for (CTLineSer next : cTLine3DChart.getSerList()) {
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
        return Grouping.valueOf(this.chart.getGrouping().getVal());
    }

    public void setGrouping(Grouping grouping) {
        if (this.chart.getGrouping() != null) {
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
        CTLineSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewCat();
        addNewSer.addNewVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, xDDFNumericalDataSource);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTLineSer series;

        protected Series(CTLineSer cTLineSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTLineSer;
        }

        protected Series(CTLineSer cTLineSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTLineSer;
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

        public Boolean isSmooth() {
            if (this.series.isSetSmooth()) {
                return Boolean.valueOf(this.series.getSmooth().getVal());
            }
            return false;
        }

        public void setSmooth(Boolean bool) {
            if (bool == null) {
                if (this.series.isSetSmooth()) {
                    this.series.unsetSmooth();
                }
            } else if (this.series.isSetSmooth()) {
                this.series.getSmooth().setVal(bool.booleanValue());
            } else {
                this.series.addNewSmooth().setVal(bool.booleanValue());
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
            if (this.series.isSetMarker()) {
                return this.series.getMarker();
            }
            return this.series.addNewMarker();
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
