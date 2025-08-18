package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFScatterChartData extends XDDFChartData {
    private CTScatterChart chart;

    @Internal
    protected XDDFScatterChartData(XDDFChart xDDFChart, CTScatterChart cTScatterChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTScatterChart;
        for (CTScatterSer next : cTScatterChart.getSerList()) {
            this.series.add(new Series(next, next.getXVal(), next.getYVal()));
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

    public ScatterStyle getStyle() {
        CTScatterStyle scatterStyle = this.chart.getScatterStyle();
        if (scatterStyle == null) {
            scatterStyle = this.chart.addNewScatterStyle();
            scatterStyle.setVal(ScatterStyle.LINE_MARKER.underlying);
        }
        return ScatterStyle.valueOf(scatterStyle.getVal());
    }

    public void setStyle(ScatterStyle scatterStyle) {
        CTScatterStyle scatterStyle2 = this.chart.getScatterStyle();
        if (scatterStyle2 == null) {
            scatterStyle2 = this.chart.addNewScatterStyle();
        }
        scatterStyle2.setVal(scatterStyle.underlying);
    }

    public XDDFChartData.Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
        long incrementSeriesCount = this.parent.incrementSeriesCount();
        CTScatterSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewXVal();
        addNewSer.addNewYVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, (XDDFNumericalDataSource<?>) xDDFNumericalDataSource);
        series.setMarkerStyle(MarkerStyle.NONE);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTScatterSer series;

        protected Series(CTScatterSer cTScatterSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<?> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTScatterSer;
        }

        protected Series(CTScatterSer cTScatterSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTScatterSer;
        }

        /* access modifiers changed from: protected */
        public CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
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

        public int getErrorBarsCount() {
            return this.series.sizeOfErrBarsArray();
        }

        public XDDFErrorBars getErrorBars(int i) {
            return new XDDFErrorBars(this.series.getErrBarsArray(i));
        }

        public XDDFErrorBars addNewErrorBars() {
            return new XDDFErrorBars(this.series.addNewErrBars());
        }

        public XDDFErrorBars insertNewErrorBars(int i) {
            return new XDDFErrorBars(this.series.insertNewErrBars(i));
        }

        public void removeErrorBars(int i) {
            this.series.removeErrBars(i);
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
            return this.series.getXVal();
        }

        /* access modifiers changed from: protected */
        public CTNumDataSource getNumDS() {
            return this.series.getYVal();
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
