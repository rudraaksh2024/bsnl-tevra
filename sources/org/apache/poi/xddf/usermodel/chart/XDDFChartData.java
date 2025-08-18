package org.apache.poi.xddf.usermodel.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public abstract class XDDFChartData {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = LogManager.getLogger((Class<?>) XDDFChartData.class);
    private XDDFCategoryAxis categoryAxis;
    protected XDDFChart parent;
    protected List<Series> series = new ArrayList();
    private List<XDDFValueAxis> valueAxes;

    public abstract Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource);

    /* access modifiers changed from: protected */
    @Internal
    public abstract void removeCTSeries(int i);

    public abstract void setVaryColors(Boolean bool);

    protected XDDFChartData(XDDFChart xDDFChart) {
        this.parent = xDDFChart;
    }

    /* access modifiers changed from: protected */
    public void defineAxes(CTUnsignedInt[] cTUnsignedIntArr, Map<Long, XDDFChartAxis> map, Map<Long, XDDFValueAxis> map2) {
        ArrayList arrayList = new ArrayList(cTUnsignedIntArr.length);
        for (CTUnsignedInt val : cTUnsignedIntArr) {
            Long valueOf = Long.valueOf(val.getVal());
            XDDFChartAxis xDDFChartAxis = map.get(valueOf);
            if (xDDFChartAxis == null) {
                XDDFValueAxis xDDFValueAxis = map2.get(valueOf);
                if (xDDFValueAxis != null) {
                    arrayList.add(xDDFValueAxis);
                }
            } else if (xDDFChartAxis instanceof XDDFCategoryAxis) {
                this.categoryAxis = (XDDFCategoryAxis) xDDFChartAxis;
            }
        }
        this.valueAxes = Collections.unmodifiableList(arrayList);
    }

    public XDDFCategoryAxis getCategoryAxis() {
        return this.categoryAxis;
    }

    public List<XDDFValueAxis> getValueAxes() {
        return this.valueAxes;
    }

    @Deprecated
    @Removal(version = "5.3")
    public List<Series> getSeries() {
        return Collections.unmodifiableList(this.series);
    }

    public final int getSeriesCount() {
        return this.series.size();
    }

    public final Series getSeries(int i) {
        return this.series.get(i);
    }

    public final void removeSeries(int i) {
        if (i < 0 || this.series.size() <= i) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "%s(%d): illegal index", new Object[]{"removeSeries", Integer.valueOf(i)}));
        }
        this.series.remove(i);
        removeCTSeries(i);
    }

    public abstract class Series {
        protected XDDFDataSource<?> categoryData;
        protected XDDFNumericalDataSource<? extends Number> valuesData;

        /* access modifiers changed from: protected */
        public abstract CTAxDataSource getAxDS();

        /* access modifiers changed from: protected */
        public abstract List<CTDPt> getDPtList();

        /* access modifiers changed from: protected */
        public abstract CTNumDataSource getNumDS();

        /* access modifiers changed from: protected */
        public abstract CTSerTx getSeriesText();

        public abstract XDDFShapeProperties getShapeProperties();

        /* access modifiers changed from: protected */
        public abstract void setIndex(long j);

        /* access modifiers changed from: protected */
        public abstract void setOrder(long j);

        public abstract void setShapeProperties(XDDFShapeProperties xDDFShapeProperties);

        public abstract void setShowLeaderLines(boolean z);

        protected Series(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            replaceData(xDDFDataSource, xDDFNumericalDataSource);
        }

        public void replaceData(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource) {
            int pointCount;
            if (!(this.categoryData == null || xDDFNumericalDataSource == null || (pointCount = xDDFDataSource.getPointCount()) == xDDFNumericalDataSource.getPointCount())) {
                XDDFChartData.LOGGER.warn("Category and values must have the same point count, but had " + pointCount + " categories and " + xDDFNumericalDataSource.getPointCount() + " values.");
            }
            this.categoryData = xDDFDataSource;
            this.valuesData = xDDFNumericalDataSource;
        }

        public void setTitle(String str, CellReference cellReference) {
            CTStrRef cTStrRef;
            CTStrData cTStrData;
            if (cellReference == null) {
                getSeriesText().setV(str);
                return;
            }
            if (getSeriesText().isSetStrRef()) {
                cTStrRef = getSeriesText().getStrRef();
            } else {
                cTStrRef = getSeriesText().addNewStrRef();
            }
            cTStrRef.setF(cellReference.formatAsString());
            if (str != null) {
                if (cTStrRef.isSetStrCache()) {
                    cTStrData = cTStrRef.getStrCache();
                } else {
                    cTStrData = cTStrRef.addNewStrCache();
                }
                if (cTStrData.sizeOfPtArray() < 1) {
                    cTStrData.addNewPtCount().setVal(1);
                    cTStrData.addNewPt().setIdx(0);
                }
                cTStrData.getPtArray(0).setV(str);
            }
        }

        public XDDFDataSource<?> getCategoryData() {
            return this.categoryData;
        }

        public XDDFNumericalDataSource<? extends Number> getValuesData() {
            return this.valuesData;
        }

        public void plot() {
            XDDFDataSource<?> xDDFDataSource = this.categoryData;
            if (xDDFDataSource != null) {
                if (xDDFDataSource.isNumeric()) {
                    this.categoryData.fillNumericalCache(retrieveNumCache(getAxDS(), this.categoryData));
                } else {
                    this.categoryData.fillStringCache(retrieveStrCache(getAxDS(), this.categoryData));
                }
            }
            if (this.valuesData != null) {
                this.valuesData.fillNumericalCache(retrieveNumCache(getNumDS(), (XDDFDataSource<?>) this.valuesData));
            }
        }

        public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
            XDDFShapeProperties shapeProperties = getShapeProperties();
            if (shapeProperties == null) {
                shapeProperties = new XDDFShapeProperties();
            }
            shapeProperties.setFillProperties(xDDFFillProperties);
            setShapeProperties(shapeProperties);
        }

        public void setLineProperties(XDDFLineProperties xDDFLineProperties) {
            XDDFShapeProperties shapeProperties = getShapeProperties();
            if (shapeProperties == null) {
                shapeProperties = new XDDFShapeProperties();
            }
            shapeProperties.setLineProperties(xDDFLineProperties);
            setShapeProperties(shapeProperties);
        }

        public void clearDataPoint(long j) {
            List<CTDPt> dPtList = getDPtList();
            for (int i = 0; i < dPtList.size(); i++) {
                if (dPtList.get(i).getIdx().getVal() == j) {
                    dPtList.remove(i);
                    return;
                }
            }
        }

        public XDDFDataPoint getDataPoint(long j) {
            List<CTDPt> dPtList = getDPtList();
            for (int i = 0; i < dPtList.size(); i++) {
                if (dPtList.get(i).getIdx().getVal() == j) {
                    return new XDDFDataPoint(dPtList.get(i));
                }
                if (dPtList.get(i).getIdx().getVal() > j) {
                    dPtList.add(i, CTDPt.Factory.newInstance());
                    CTDPt cTDPt = dPtList.get(i);
                    cTDPt.addNewIdx().setVal(j);
                    return new XDDFDataPoint(cTDPt);
                }
            }
            dPtList.add(CTDPt.Factory.newInstance());
            CTDPt cTDPt2 = dPtList.get(dPtList.size() - 1);
            cTDPt2.addNewIdx().setVal(j);
            return new XDDFDataPoint(cTDPt2);
        }

        private CTNumData retrieveNumCache(CTAxDataSource cTAxDataSource, XDDFDataSource<?> xDDFDataSource) {
            CTNumData cTNumData;
            CTNumData cTNumData2;
            CTNumRef cTNumRef;
            if (xDDFDataSource.isReference()) {
                if (cTAxDataSource.isSetNumRef()) {
                    cTNumRef = cTAxDataSource.getNumRef();
                } else {
                    cTNumRef = cTAxDataSource.addNewNumRef();
                }
                if (cTNumRef.isSetNumCache()) {
                    cTNumData = cTNumRef.getNumCache();
                } else {
                    cTNumData = cTNumRef.addNewNumCache();
                }
                cTNumRef.setF(xDDFDataSource.getDataRangeReference());
                if (cTAxDataSource.isSetNumLit()) {
                    cTAxDataSource.unsetNumLit();
                }
            } else {
                if (cTAxDataSource.isSetNumLit()) {
                    cTNumData2 = cTAxDataSource.getNumLit();
                } else {
                    cTNumData2 = cTAxDataSource.addNewNumLit();
                }
                cTNumData = cTNumData2;
                if (cTAxDataSource.isSetNumRef()) {
                    cTAxDataSource.unsetNumRef();
                }
            }
            return cTNumData;
        }

        private CTStrData retrieveStrCache(CTAxDataSource cTAxDataSource, XDDFDataSource<?> xDDFDataSource) {
            CTStrData cTStrData;
            CTStrData cTStrData2;
            CTStrRef cTStrRef;
            if (xDDFDataSource.isReference()) {
                if (cTAxDataSource.isSetStrRef()) {
                    cTStrRef = cTAxDataSource.getStrRef();
                } else {
                    cTStrRef = cTAxDataSource.addNewStrRef();
                }
                if (cTStrRef.isSetStrCache()) {
                    cTStrData = cTStrRef.getStrCache();
                } else {
                    cTStrData = cTStrRef.addNewStrCache();
                }
                cTStrRef.setF(xDDFDataSource.getDataRangeReference());
                if (cTAxDataSource.isSetStrLit()) {
                    cTAxDataSource.unsetStrLit();
                }
            } else {
                if (cTAxDataSource.isSetStrLit()) {
                    cTStrData2 = cTAxDataSource.getStrLit();
                } else {
                    cTStrData2 = cTAxDataSource.addNewStrLit();
                }
                cTStrData = cTStrData2;
                if (cTAxDataSource.isSetStrRef()) {
                    cTAxDataSource.unsetStrRef();
                }
            }
            return cTStrData;
        }

        private CTNumData retrieveNumCache(CTNumDataSource cTNumDataSource, XDDFDataSource<?> xDDFDataSource) {
            CTNumData cTNumData;
            CTNumData cTNumData2;
            CTNumRef cTNumRef;
            if (xDDFDataSource.isReference()) {
                if (cTNumDataSource.isSetNumRef()) {
                    cTNumRef = cTNumDataSource.getNumRef();
                } else {
                    cTNumRef = cTNumDataSource.addNewNumRef();
                }
                if (cTNumRef.isSetNumCache()) {
                    cTNumData = cTNumRef.getNumCache();
                } else {
                    cTNumData = cTNumRef.addNewNumCache();
                }
                cTNumRef.setF(xDDFDataSource.getDataRangeReference());
                if (cTNumDataSource.isSetNumLit()) {
                    cTNumDataSource.unsetNumLit();
                }
            } else {
                if (cTNumDataSource.isSetNumLit()) {
                    cTNumData2 = cTNumDataSource.getNumLit();
                } else {
                    cTNumData2 = cTNumDataSource.addNewNumLit();
                }
                cTNumData = cTNumData2;
                if (cTNumDataSource.isSetNumRef()) {
                    cTNumDataSource.unsetNumRef();
                }
            }
            return cTNumData;
        }
    }
}
