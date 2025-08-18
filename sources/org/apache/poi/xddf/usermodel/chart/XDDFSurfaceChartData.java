package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceSer;

public class XDDFSurfaceChartData extends XDDFChartData {
    private CTSurfaceChart chart;

    public void setVaryColors(Boolean bool) {
    }

    @Internal
    protected XDDFSurfaceChartData(XDDFChart xDDFChart, CTSurfaceChart cTSurfaceChart, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        super(xDDFChart);
        this.chart = cTSurfaceChart;
        for (CTSurfaceSer next : cTSurfaceChart.getSerList()) {
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

    public void defineSeriesAxis(XDDFSeriesAxis xDDFSeriesAxis) {
        this.chart.addNewAxId().setVal(xDDFSeriesAxis.getId());
    }

    public Boolean isWireframe() {
        if (this.chart.isSetWireframe()) {
            return Boolean.valueOf(this.chart.getWireframe().getVal());
        }
        return false;
    }

    public void setWireframe(Boolean bool) {
        if (bool == null) {
            if (this.chart.isSetWireframe()) {
                this.chart.unsetWireframe();
            }
        } else if (this.chart.isSetWireframe()) {
            this.chart.getWireframe().setVal(bool.booleanValue());
        } else {
            this.chart.addNewWireframe().setVal(bool.booleanValue());
        }
    }

    public XDDFChartData.Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
        long incrementSeriesCount = this.parent.incrementSeriesCount();
        CTSurfaceSer addNewSer = this.chart.addNewSer();
        addNewSer.addNewCat();
        addNewSer.addNewVal();
        addNewSer.addNewIdx().setVal(incrementSeriesCount);
        addNewSer.addNewOrder().setVal(incrementSeriesCount);
        Series series = new Series(addNewSer, xDDFDataSource, xDDFNumericalDataSource);
        this.series.add(series);
        return series;
    }

    public class Series extends XDDFChartData.Series {
        private CTSurfaceSer series;

        public void setShowLeaderLines(boolean z) {
        }

        protected Series(CTSurfaceSer cTSurfaceSer, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            super(xDDFDataSource, xDDFNumericalDataSource);
            this.series = cTSurfaceSer;
        }

        protected Series(CTSurfaceSer cTSurfaceSer, CTAxDataSource cTAxDataSource, CTNumDataSource cTNumDataSource) {
            super(XDDFDataSourcesFactory.fromDataSource(cTAxDataSource), XDDFDataSourcesFactory.fromDataSource(cTNumDataSource));
            this.series = cTSurfaceSer;
        }

        /* access modifiers changed from: protected */
        public CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
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
            throw new IllegalStateException("Surface data series don't support data point settings.");
        }
    }
}
