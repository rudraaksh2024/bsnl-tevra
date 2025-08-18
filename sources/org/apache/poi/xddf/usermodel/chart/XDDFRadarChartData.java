package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFRadarChartData extends XDDFChartData {
    private CTRadarChart chart;

    @Internal
    protected XDDFRadarChartData(XDDFChart xDDFChart, CTRadarChart cTRadarChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTRadarChart;
        for (CTRadarSer next : cTRadarChart.getSerList()) {
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

    public RadarStyle getStyle() {
        return RadarStyle.valueOf(this.chart.getRadarStyle().getVal());
    }

    public void setStyle(RadarStyle radarStyle) {
        CTRadarStyle radarStyle2 = this.chart.getRadarStyle();
        if (radarStyle2 == null) {
            radarStyle2 = this.chart.addNewRadarStyle();
        }
        radarStyle2.setVal(radarStyle.underlying);
    }

    public XDDFChartData.Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
        long incrementSeriesCount = this.parent.incrementSeriesCount();
        CTRadarSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewCat();
        addNewSer.addNewVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, xDDFNumericalDataSource);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTRadarSer series;

        protected Series(CTRadarSer cTRadarSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTRadarSer;
        }

        protected Series(CTRadarSer cTRadarSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTRadarSer;
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
