package org.apache.poi.xddf.usermodel.chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class XDDFChart extends POIXMLDocumentPart implements TextContainer {
    public static final int DEFAULT_HEIGHT = 500000;
    public static final int DEFAULT_WIDTH = 500000;
    public static final int DEFAULT_X = 10;
    public static final int DEFAULT_Y = 10;
    protected List<XDDFChartAxis> axes = new ArrayList();
    private int chartIndex = 0;
    protected final CTChartSpace chartSpace;
    private long seriesCount = 0;
    private XSSFWorkbook workbook;

    /* access modifiers changed from: protected */
    public abstract POIXMLFactory getChartFactory();

    /* access modifiers changed from: protected */
    public abstract POIXMLRelation getChartRelation();

    /* access modifiers changed from: protected */
    public abstract POIXMLRelation getChartWorkbookRelation();

    protected XDDFChart() {
        CTChartSpace newInstance = CTChartSpace.Factory.newInstance();
        this.chartSpace = newInstance;
        newInstance.addNewChart().addNewPlotArea();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r3 != null) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected XDDFChart(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>((org.apache.poi.openxml4j.opc.PackagePart) r3)
            r0 = 0
            r2.chartIndex = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2.axes = r0
            r0 = 0
            r2.seriesCount = r0
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument> r0 = org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument.Factory     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x002b }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument r0 = (org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument) r0     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace r0 = r0.getChartSpace()     // Catch:{ all -> 0x002b }
            r2.chartSpace = r0     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x002a
            r3.close()
        L_0x002a:
            return
        L_0x002b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r0 = move-exception
            if (r3 == 0) goto L_0x0038
            r3.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x0038:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.chart.XDDFChart.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    @Internal
    public CTChartSpace getCTChartSpace() {
        return this.chartSpace;
    }

    @Internal
    public CTChart getCTChart() {
        return this.chartSpace.getChart();
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTPlotArea getCTPlotArea() {
        return getCTChart().getPlotArea();
    }

    public void clear() {
        this.axes.clear();
        this.seriesCount = 0;
        XSSFWorkbook xSSFWorkbook = this.workbook;
        if (xSSFWorkbook != null) {
            xSSFWorkbook.removeSheetAt(0);
            this.workbook.createSheet();
        }
        getCTChart().set(CTChart.Factory.newInstance());
        getCTChart().addNewPlotArea();
    }

    public boolean isPlotOnlyVisibleCells() {
        if (getCTChart().isSetPlotVisOnly()) {
            return getCTChart().getPlotVisOnly().getVal();
        }
        return false;
    }

    public void setPlotOnlyVisibleCells(boolean z) {
        if (!getCTChart().isSetPlotVisOnly()) {
            getCTChart().setPlotVisOnly(CTBoolean.Factory.newInstance());
        }
        getCTChart().getPlotVisOnly().setVal(z);
    }

    public void setFloor(int i) {
        if (!getCTChart().isSetFloor()) {
            getCTChart().setFloor(CTSurface.Factory.newInstance());
        }
        getCTChart().getFloor().getThickness().setVal(Integer.valueOf(i));
    }

    public void setBackWall(int i) {
        if (!getCTChart().isSetBackWall()) {
            getCTChart().setBackWall(CTSurface.Factory.newInstance());
        }
        getCTChart().getBackWall().getThickness().setVal(Integer.valueOf(i));
    }

    public void setSideWall(int i) {
        if (!getCTChart().isSetSideWall()) {
            getCTChart().setSideWall(CTSurface.Factory.newInstance());
        }
        getCTChart().getSideWall().getThickness().setVal(Integer.valueOf(i));
    }

    public void setAutoTitleDeleted(boolean z) {
        if (!getCTChart().isSetAutoTitleDeleted()) {
            getCTChart().setAutoTitleDeleted(CTBoolean.Factory.newInstance());
        }
        getCTChart().getAutoTitleDeleted().setVal(z);
        if (z && getCTChart().isSetTitle()) {
            getCTChart().unsetTitle();
        }
    }

    public void displayBlanksAs(DisplayBlanks displayBlanks) {
        if (displayBlanks == null) {
            if (getCTChart().isSetDispBlanksAs()) {
                getCTChart().unsetDispBlanksAs();
            }
        } else if (getCTChart().isSetDispBlanksAs()) {
            getCTChart().getDispBlanksAs().setVal(displayBlanks.underlying);
        } else {
            getCTChart().addNewDispBlanksAs().setVal(displayBlanks.underlying);
        }
    }

    public Boolean getTitleOverlay() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        CTTitle title = getCTChart().getTitle();
        if (title.isSetOverlay()) {
            return Boolean.valueOf(title.getOverlay().getVal());
        }
        return null;
    }

    public void setTitleOverlay(boolean z) {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        new XDDFTitle(this, getCTChart().getTitle()).setOverlay(Boolean.valueOf(z));
    }

    public void setTitleText(String str) {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        new XDDFTitle(this, getCTChart().getTitle()).setText(str);
    }

    public XDDFTitle getTitle() {
        if (getCTChart().isSetTitle()) {
            return new XDDFTitle(this, getCTChart().getTitle());
        }
        return null;
    }

    public void removeTitle() {
        setAutoTitleDeleted(true);
    }

    public XDDFView3D getOrAddView3D() {
        CTView3D cTView3D;
        if (getCTChart().isSetView3D()) {
            cTView3D = getCTChart().getView3D();
        } else {
            cTView3D = getCTChart().addNewView3D();
        }
        return new XDDFView3D(cTView3D);
    }

    public XDDFTextBody getFormattedTitle() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        return new XDDFTitle(this, getCTChart().getTitle()).getBody();
    }

    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        return Optional.empty();
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        return Optional.empty();
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties cTShapeProperties;
        CTPlotArea cTPlotArea = getCTPlotArea();
        if (cTPlotArea.isSetSpPr()) {
            cTShapeProperties = cTPlotArea.getSpPr();
        } else {
            cTShapeProperties = cTPlotArea.addNewSpPr();
        }
        return new XDDFShapeProperties(cTShapeProperties);
    }

    public void deleteShapeProperties() {
        if (getCTPlotArea().isSetSpPr()) {
            getCTPlotArea().unsetSpPr();
        }
    }

    public XDDFChartLegend getOrAddLegend() {
        return new XDDFChartLegend(getCTChart());
    }

    public void deleteLegend() {
        if (getCTChart().isSetLegend()) {
            getCTChart().unsetLegend();
        }
    }

    public XDDFManualLayout getOrAddManualLayout() {
        return new XDDFManualLayout(getCTPlotArea());
    }

    /* access modifiers changed from: protected */
    public long incrementSeriesCount() {
        long j = this.seriesCount;
        this.seriesCount = 1 + j;
        return j;
    }

    public void plot(XDDFChartData xDDFChartData) {
        XSSFSheet sheet = getSheet();
        for (int i = 0; i < xDDFChartData.getSeriesCount(); i++) {
            XDDFChartData.Series series = xDDFChartData.getSeries(i);
            series.plot();
            XDDFDataSource<?> categoryData = series.getCategoryData();
            XDDFNumericalDataSource<? extends Number> valuesData = series.getValuesData();
            if (categoryData != null && !categoryData.isCellRange() && !categoryData.isLiteral() && valuesData != null && !valuesData.isCellRange() && !valuesData.isLiteral()) {
                fillSheet(sheet, categoryData, valuesData);
            }
        }
    }

    public List<XDDFChartData> getChartSeries() {
        LinkedList linkedList = new LinkedList();
        CTPlotArea cTPlotArea = getCTPlotArea();
        Map<Long, XDDFChartAxis> categoryAxes = getCategoryAxes();
        Map<Long, XDDFValueAxis> valueAxes = getValueAxes();
        for (int i = 0; i < cTPlotArea.sizeOfAreaChartArray(); i++) {
            linkedList.add(new XDDFAreaChartData(this, cTPlotArea.getAreaChartArray(i), categoryAxes, valueAxes));
        }
        for (int i2 = 0; i2 < cTPlotArea.sizeOfArea3DChartArray(); i2++) {
            linkedList.add(new XDDFArea3DChartData(this, cTPlotArea.getArea3DChartArray(i2), categoryAxes, valueAxes));
        }
        for (int i3 = 0; i3 < cTPlotArea.sizeOfBarChartArray(); i3++) {
            linkedList.add(new XDDFBarChartData(this, cTPlotArea.getBarChartArray(i3), categoryAxes, valueAxes));
        }
        for (int i4 = 0; i4 < cTPlotArea.sizeOfBar3DChartArray(); i4++) {
            linkedList.add(new XDDFBar3DChartData(this, cTPlotArea.getBar3DChartArray(i4), categoryAxes, valueAxes));
        }
        for (int i5 = 0; i5 < cTPlotArea.sizeOfDoughnutChartArray(); i5++) {
            linkedList.add(new XDDFDoughnutChartData(this, cTPlotArea.getDoughnutChartArray(i5)));
        }
        for (int i6 = 0; i6 < cTPlotArea.sizeOfLineChartArray(); i6++) {
            linkedList.add(new XDDFLineChartData(this, cTPlotArea.getLineChartArray(i6), categoryAxes, valueAxes));
        }
        for (int i7 = 0; i7 < cTPlotArea.sizeOfLine3DChartArray(); i7++) {
            linkedList.add(new XDDFLine3DChartData(this, cTPlotArea.getLine3DChartArray(i7), categoryAxes, valueAxes));
        }
        for (int i8 = 0; i8 < cTPlotArea.sizeOfPieChartArray(); i8++) {
            linkedList.add(new XDDFPieChartData(this, cTPlotArea.getPieChartArray(i8)));
        }
        for (int i9 = 0; i9 < cTPlotArea.sizeOfPie3DChartArray(); i9++) {
            linkedList.add(new XDDFPie3DChartData(this, cTPlotArea.getPie3DChartArray(i9)));
        }
        for (int i10 = 0; i10 < cTPlotArea.sizeOfRadarChartArray(); i10++) {
            linkedList.add(new XDDFRadarChartData(this, cTPlotArea.getRadarChartArray(i10), categoryAxes, valueAxes));
        }
        for (int i11 = 0; i11 < cTPlotArea.sizeOfScatterChartArray(); i11++) {
            linkedList.add(new XDDFScatterChartData(this, cTPlotArea.getScatterChartArray(i11), categoryAxes, valueAxes));
        }
        for (int i12 = 0; i12 < cTPlotArea.sizeOfSurfaceChartArray(); i12++) {
            linkedList.add(new XDDFSurfaceChartData(this, cTPlotArea.getSurfaceChartArray(i12), categoryAxes, valueAxes));
        }
        for (int i13 = 0; i13 < cTPlotArea.sizeOfSurface3DChartArray(); i13++) {
            linkedList.add(new XDDFSurface3DChartData(this, cTPlotArea.getSurface3DChartArray(i13), categoryAxes, valueAxes));
        }
        this.seriesCount = (long) linkedList.size();
        return linkedList;
    }

    public void clearChartSeries() {
        CTPlotArea cTPlotArea = getCTPlotArea();
        for (int sizeOfAreaChartArray = cTPlotArea.sizeOfAreaChartArray(); sizeOfAreaChartArray > 0; sizeOfAreaChartArray--) {
            cTPlotArea.removeAreaChart(sizeOfAreaChartArray - 1);
        }
        for (int sizeOfArea3DChartArray = cTPlotArea.sizeOfArea3DChartArray(); sizeOfArea3DChartArray > 0; sizeOfArea3DChartArray--) {
            cTPlotArea.removeArea3DChart(sizeOfArea3DChartArray - 1);
        }
        for (int sizeOfBarChartArray = cTPlotArea.sizeOfBarChartArray(); sizeOfBarChartArray > 0; sizeOfBarChartArray--) {
            cTPlotArea.removeBarChart(sizeOfBarChartArray - 1);
        }
        for (int sizeOfBar3DChartArray = cTPlotArea.sizeOfBar3DChartArray(); sizeOfBar3DChartArray > 0; sizeOfBar3DChartArray--) {
            cTPlotArea.removeBar3DChart(sizeOfBar3DChartArray - 1);
        }
        for (int sizeOfBubbleChartArray = cTPlotArea.sizeOfBubbleChartArray(); sizeOfBubbleChartArray > 0; sizeOfBubbleChartArray--) {
            cTPlotArea.removeBubbleChart(sizeOfBubbleChartArray - 1);
        }
        for (int sizeOfDoughnutChartArray = cTPlotArea.sizeOfDoughnutChartArray(); sizeOfDoughnutChartArray > 0; sizeOfDoughnutChartArray--) {
            cTPlotArea.removeDoughnutChart(sizeOfDoughnutChartArray - 1);
        }
        for (int sizeOfLineChartArray = cTPlotArea.sizeOfLineChartArray(); sizeOfLineChartArray > 0; sizeOfLineChartArray--) {
            cTPlotArea.removeLineChart(sizeOfLineChartArray - 1);
        }
        for (int sizeOfLine3DChartArray = cTPlotArea.sizeOfLine3DChartArray(); sizeOfLine3DChartArray > 0; sizeOfLine3DChartArray--) {
            cTPlotArea.removeLine3DChart(sizeOfLine3DChartArray - 1);
        }
        for (int sizeOfOfPieChartArray = cTPlotArea.sizeOfOfPieChartArray(); sizeOfOfPieChartArray > 0; sizeOfOfPieChartArray--) {
            cTPlotArea.removeOfPieChart(sizeOfOfPieChartArray - 1);
        }
        for (int sizeOfPieChartArray = cTPlotArea.sizeOfPieChartArray(); sizeOfPieChartArray > 0; sizeOfPieChartArray--) {
            cTPlotArea.removePieChart(sizeOfPieChartArray - 1);
        }
        for (int sizeOfPie3DChartArray = cTPlotArea.sizeOfPie3DChartArray(); sizeOfPie3DChartArray > 0; sizeOfPie3DChartArray--) {
            cTPlotArea.removePie3DChart(sizeOfPie3DChartArray - 1);
        }
        for (int sizeOfRadarChartArray = cTPlotArea.sizeOfRadarChartArray(); sizeOfRadarChartArray > 0; sizeOfRadarChartArray--) {
            cTPlotArea.removeRadarChart(sizeOfRadarChartArray - 1);
        }
        for (int sizeOfScatterChartArray = cTPlotArea.sizeOfScatterChartArray(); sizeOfScatterChartArray > 0; sizeOfScatterChartArray--) {
            cTPlotArea.removeScatterChart(sizeOfScatterChartArray - 1);
        }
        for (int sizeOfStockChartArray = cTPlotArea.sizeOfStockChartArray(); sizeOfStockChartArray > 0; sizeOfStockChartArray--) {
            cTPlotArea.removeStockChart(sizeOfStockChartArray - 1);
        }
        for (int sizeOfSurfaceChartArray = cTPlotArea.sizeOfSurfaceChartArray(); sizeOfSurfaceChartArray > 0; sizeOfSurfaceChartArray--) {
            cTPlotArea.removeSurfaceChart(sizeOfSurfaceChartArray - 1);
        }
        for (int sizeOfSurface3DChartArray = cTPlotArea.sizeOfSurface3DChartArray(); sizeOfSurface3DChartArray > 0; sizeOfSurface3DChartArray--) {
            cTPlotArea.removeSurface3DChart(sizeOfSurface3DChartArray - 1);
        }
    }

    private Map<Long, XDDFChartAxis> getCategoryAxes() {
        CTPlotArea cTPlotArea = getCTPlotArea();
        int sizeOfCatAxArray = cTPlotArea.sizeOfCatAxArray();
        HashMap hashMap = new HashMap(sizeOfCatAxArray);
        for (int i = 0; i < sizeOfCatAxArray; i++) {
            CTCatAx catAxArray = cTPlotArea.getCatAxArray(i);
            hashMap.put(Long.valueOf(catAxArray.getAxId().getVal()), new XDDFCategoryAxis(catAxArray));
        }
        return hashMap;
    }

    private Map<Long, XDDFValueAxis> getValueAxes() {
        CTPlotArea cTPlotArea = getCTPlotArea();
        int sizeOfValAxArray = cTPlotArea.sizeOfValAxArray();
        HashMap hashMap = new HashMap(sizeOfValAxArray);
        for (int i = 0; i < sizeOfValAxArray; i++) {
            CTValAx valAxArray = cTPlotArea.getValAxArray(i);
            hashMap.put(Long.valueOf(valAxArray.getAxId().getVal()), new XDDFValueAxis(valAxArray));
        }
        return hashMap;
    }

    public XDDFValueAxis createValueAxis(AxisPosition axisPosition) {
        XDDFValueAxis xDDFValueAxis = new XDDFValueAxis(getCTPlotArea(), axisPosition);
        addAxis(xDDFValueAxis);
        return xDDFValueAxis;
    }

    public XDDFSeriesAxis createSeriesAxis(AxisPosition axisPosition) {
        XDDFSeriesAxis xDDFSeriesAxis = new XDDFSeriesAxis(getCTPlotArea(), axisPosition);
        addAxis(xDDFSeriesAxis);
        return xDDFSeriesAxis;
    }

    public XDDFCategoryAxis createCategoryAxis(AxisPosition axisPosition) {
        XDDFCategoryAxis xDDFCategoryAxis = new XDDFCategoryAxis(getCTPlotArea(), axisPosition);
        addAxis(xDDFCategoryAxis);
        return xDDFCategoryAxis;
    }

    public XDDFDateAxis createDateAxis(AxisPosition axisPosition) {
        XDDFDateAxis xDDFDateAxis = new XDDFDateAxis(getCTPlotArea(), axisPosition);
        addAxis(xDDFDateAxis);
        return xDDFDateAxis;
    }

    private void addAxis(XDDFChartAxis xDDFChartAxis) {
        if (this.axes.size() == 1) {
            XDDFChartAxis xDDFChartAxis2 = this.axes.get(0);
            xDDFChartAxis2.crossAxis(xDDFChartAxis);
            xDDFChartAxis.crossAxis(xDDFChartAxis2);
            xDDFChartAxis2.setCrosses(AxisCrosses.AUTO_ZERO);
            xDDFChartAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        }
        this.axes.add(xDDFChartAxis);
    }

    public XDDFChartData createData(ChartTypes chartTypes, XDDFChartAxis xDDFChartAxis, XDDFValueAxis xDDFValueAxis) {
        Map map;
        Map map2;
        if (ChartTypes.PIE == chartTypes || ChartTypes.PIE3D == chartTypes || ChartTypes.DOUGHNUT == chartTypes) {
            map2 = null;
            map = null;
        } else {
            map2 = Collections.singletonMap(Long.valueOf(xDDFChartAxis.getId()), xDDFChartAxis);
            map = Collections.singletonMap(Long.valueOf(xDDFValueAxis.getId()), xDDFValueAxis);
        }
        CTPlotArea cTPlotArea = getCTPlotArea();
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes[chartTypes.ordinal()]) {
            case 1:
                return new XDDFAreaChartData(this, cTPlotArea.addNewAreaChart(), map2, map);
            case 2:
                return new XDDFArea3DChartData(this, cTPlotArea.addNewArea3DChart(), map2, map);
            case 3:
                return new XDDFBarChartData(this, cTPlotArea.addNewBarChart(), map2, map);
            case 4:
                return new XDDFBar3DChartData(this, cTPlotArea.addNewBar3DChart(), map2, map);
            case 5:
                return new XDDFDoughnutChartData(this, cTPlotArea.addNewDoughnutChart());
            case 6:
                return new XDDFLineChartData(this, cTPlotArea.addNewLineChart(), map2, map);
            case 7:
                return new XDDFLine3DChartData(this, cTPlotArea.addNewLine3DChart(), map2, map);
            case 8:
                return new XDDFPieChartData(this, cTPlotArea.addNewPieChart());
            case 9:
                return new XDDFPie3DChartData(this, cTPlotArea.addNewPie3DChart());
            case 10:
                return new XDDFRadarChartData(this, cTPlotArea.addNewRadarChart(), map2, map);
            case 11:
                return new XDDFScatterChartData(this, cTPlotArea.addNewScatterChart(), map2, map);
            case 12:
                return new XDDFSurfaceChartData(this, cTPlotArea.addNewSurfaceChart(), map2, map);
            case 13:
                return new XDDFSurface3DChartData(this, cTPlotArea.addNewSurface3DChart(), map2, map);
            default:
                return null;
        }
    }

    /* renamed from: org.apache.poi.xddf.usermodel.chart.XDDFChart$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xddf.usermodel.chart.ChartTypes[] r0 = org.apache.poi.xddf.usermodel.chart.ChartTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes = r0
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.AREA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.AREA3D     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.BAR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.BAR3D     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.DOUGHNUT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.LINE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.LINE3D     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.PIE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.PIE3D     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.RADAR     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.SCATTER     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.SURFACE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$xddf$usermodel$chart$ChartTypes     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.xddf.usermodel.chart.ChartTypes r1 = org.apache.poi.xddf.usermodel.chart.ChartTypes.SURFACE3D     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.chart.XDDFChart.AnonymousClass1.<clinit>():void");
        }
    }

    public List<? extends XDDFChartAxis> getAxes() {
        if (this.axes.isEmpty() && hasAxes()) {
            parseAxes();
        }
        return this.axes;
    }

    private boolean hasAxes() {
        CTPlotArea cTPlotArea = getCTPlotArea();
        return ((cTPlotArea.sizeOfValAxArray() + cTPlotArea.sizeOfCatAxArray()) + cTPlotArea.sizeOfDateAxArray()) + cTPlotArea.sizeOfSerAxArray() > 0;
    }

    private void parseAxes() {
        for (CTCatAx xDDFCategoryAxis : getCTPlotArea().getCatAxArray()) {
            this.axes.add(new XDDFCategoryAxis(xDDFCategoryAxis));
        }
        for (CTDateAx xDDFDateAxis : getCTPlotArea().getDateAxArray()) {
            this.axes.add(new XDDFDateAxis(xDDFDateAxis));
        }
        for (CTSerAx xDDFSeriesAxis : getCTPlotArea().getSerAxArray()) {
            this.axes.add(new XDDFSeriesAxis(xDDFSeriesAxis));
        }
        for (CTValAx xDDFValueAxis : getCTPlotArea().getValAxArray()) {
            this.axes.add(new XDDFValueAxis(xDDFValueAxis));
        }
    }

    public void setValueRange(int i, Double d, Double d2, Double d3, Double d4) {
        XDDFChartAxis xDDFChartAxis = (XDDFChartAxis) getAxes().get(i);
        if (xDDFChartAxis != null) {
            if (d != null) {
                xDDFChartAxis.setMinimum(d.doubleValue());
            }
            if (d2 != null) {
                xDDFChartAxis.setMaximum(d2.doubleValue());
            }
            if (d3 != null) {
                xDDFChartAxis.setMajorUnit(d3.doubleValue());
            }
            if (d4 != null) {
                xDDFChartAxis.setMinorUnit(d4.doubleValue());
            }
        }
    }

    public PackageRelationship createRelationshipInChart(POIXMLRelation pOIXMLRelation, POIXMLFactory pOIXMLFactory, int i) {
        return addRelation((String) null, pOIXMLRelation, createRelationship(pOIXMLRelation, pOIXMLFactory, i, true).getDocumentPart()).getRelationship();
    }

    private PackagePart createWorksheetPart(POIXMLRelation pOIXMLRelation, POIXMLFactory pOIXMLFactory) throws InvalidFormatException {
        PackageRelationship createRelationshipInChart = createRelationshipInChart(pOIXMLRelation, pOIXMLFactory, this.chartIndex);
        setExternalId(createRelationshipInChart.getId());
        return getTargetPart(createRelationshipInChart);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveWorkbook(org.apache.poi.xssf.usermodel.XSSFWorkbook r3) throws java.io.IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getWorksheetPart()
            if (r0 != 0) goto L_0x001f
            org.apache.poi.ooxml.POIXMLRelation r0 = r2.getChartWorkbookRelation()
            org.apache.poi.ooxml.POIXMLFactory r1 = r2.getChartFactory()
            if (r0 == 0) goto L_0x0017
            if (r1 == 0) goto L_0x0017
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.createWorksheetPart(r0, r1)
            goto L_0x001f
        L_0x0017:
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r2 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException
            java.lang.String r3 = "unable to determine chart relations"
            r2.<init>(r3)
            throw r2
        L_0x001f:
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.setWorksheetPartCommitted()     // Catch:{ all -> 0x002f }
            r3.write(r0)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002e
            r0.close()
        L_0x002e:
            return
        L_0x002f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ all -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.chart.XDDFChart.saveWorkbook(org.apache.poi.xssf.usermodel.XSSFWorkbook):void");
    }

    /* access modifiers changed from: protected */
    public void fillSheet(XSSFSheet xSSFSheet, XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<?> xDDFNumericalDataSource) {
        int pointCount = xDDFDataSource.getPointCount();
        int i = 0;
        while (i < pointCount) {
            int i2 = i + 1;
            XSSFRow row = getRow(xSSFSheet, i2);
            Object pointAt = xDDFDataSource.getPointAt(i);
            if (pointAt != null) {
                getCell(row, xDDFDataSource.getColIndex()).setCellValue(pointAt.toString());
            }
            Number number = (Number) xDDFNumericalDataSource.getPointAt(i);
            if (number != null) {
                getCell(row, xDDFNumericalDataSource.getColIndex()).setCellValue(number.doubleValue());
            }
            i = i2;
        }
    }

    private XSSFRow getRow(XSSFSheet xSSFSheet, int i) {
        XSSFRow row = xSSFSheet.getRow(i);
        return row == null ? xSSFSheet.createRow(i) : row;
    }

    private XSSFCell getCell(XSSFRow xSSFRow, int i) {
        XSSFCell cell = xSSFRow.getCell(i);
        return cell == null ? xSSFRow.createCell(i) : cell;
    }

    public void importContent(XDDFChart xDDFChart) {
        getCTChartSpace().set(xDDFChart.getCTChartSpace());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "chartSpace"
            java.lang.String r4 = "c"
            r1.<init>(r2, r3, r4)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.xssf.usermodel.XSSFWorkbook r1 = r5.workbook
            if (r1 == 0) goto L_0x002c
            r5.saveWorkbook(r1)     // Catch:{ InvalidFormatException -> 0x0025 }
            goto L_0x002c
        L_0x0025:
            r5 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r5)
            throw r0
        L_0x002c:
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace r5 = r5.chartSpace     // Catch:{ all -> 0x003f }
            r5.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003e
            r1.close()
        L_0x003e:
            return
        L_0x003f:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ all -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r1 = move-exception
            r5.addSuppressed(r1)
        L_0x004c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.chart.XDDFChart.commit():void");
    }

    public CellReference setSheetTitle(String str, int i) {
        XSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        getCell(getRow(sheet, 0), i).setCellValue(str);
        return new CellReference(sheet.getSheetName(), 0, i, true, true);
    }

    public String formatRange(CellRangeAddress cellRangeAddress) {
        XSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        return cellRangeAddress.formatAsString(sheet.getSheetName(), true);
    }

    private XSSFSheet getSheet() {
        try {
            return getWorkbook().getSheetAt(0);
        } catch (IOException | InvalidFormatException unused) {
            return null;
        }
    }

    private PackagePart getWorksheetPart() throws InvalidFormatException {
        for (POIXMLDocumentPart.RelationPart next : getRelationParts()) {
            if (POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(next.getRelationship().getRelationshipType())) {
                return getTargetPart(next.getRelationship());
            }
        }
        return null;
    }

    private void setWorksheetPartCommitted() {
        for (POIXMLDocumentPart.RelationPart next : getRelationParts()) {
            if (POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(next.getRelationship().getRelationshipType())) {
                next.getDocumentPart().setCommitted(true);
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (r0 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0033, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xssf.usermodel.XSSFWorkbook getWorkbook() throws java.io.IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r3 = this;
            org.apache.poi.xssf.usermodel.XSSFWorkbook r0 = r3.workbook
            if (r0 != 0) goto L_0x003e
            org.apache.poi.openxml4j.opc.PackagePart r0 = r3.getWorksheetPart()     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            if (r0 != 0) goto L_0x0015
            org.apache.poi.xssf.usermodel.XSSFWorkbook r0 = new org.apache.poi.xssf.usermodel.XSSFWorkbook     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            r0.<init>()     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            r3.workbook = r0     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            r0.createSheet()     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            goto L_0x003e
        L_0x0015:
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            org.apache.poi.xssf.usermodel.XSSFWorkbook r1 = new org.apache.poi.xssf.usermodel.XSSFWorkbook     // Catch:{ all -> 0x0026 }
            r1.<init>((java.io.InputStream) r0)     // Catch:{ all -> 0x0026 }
            r3.workbook = r1     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
            goto L_0x003e
        L_0x0026:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r2 = move-exception
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ all -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
        L_0x0033:
            throw r2     // Catch:{ NotOfficeXmlFileException -> 0x0034 }
        L_0x0034:
            org.apache.poi.xssf.usermodel.XSSFWorkbook r0 = new org.apache.poi.xssf.usermodel.XSSFWorkbook
            r0.<init>()
            r3.workbook = r0
            r0.createSheet()
        L_0x003e:
            org.apache.poi.xssf.usermodel.XSSFWorkbook r3 = r3.workbook
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.chart.XDDFChart.getWorkbook():org.apache.poi.xssf.usermodel.XSSFWorkbook");
    }

    public void setWorkbook(XSSFWorkbook xSSFWorkbook) {
        this.workbook = xSSFWorkbook;
    }

    public void setExternalId(String str) {
        CTExternalData cTExternalData;
        CTChartSpace cTChartSpace = getCTChartSpace();
        if (cTChartSpace.isSetExternalData()) {
            cTExternalData = cTChartSpace.getExternalData();
        } else {
            cTExternalData = cTChartSpace.addNewExternalData();
        }
        cTExternalData.setId(str);
    }

    /* access modifiers changed from: protected */
    public int getChartIndex() {
        return this.chartIndex;
    }

    public void setChartIndex(int i) {
        this.chartIndex = i;
    }

    public void replaceReferences(XSSFSheet xSSFSheet) {
        for (XDDFChartData xDDFChartData : getChartSeries()) {
            for (XDDFChartData.Series next : xDDFChartData.series) {
                XDDFDataSource<?> xDDFDataSource = next.categoryData;
                XDDFNumericalDataSource xDDFNumericalDataSource = next.valuesData;
                try {
                    if (next.categoryData != null && next.categoryData.isReference()) {
                        String dataRangeReference = next.categoryData.getDataRangeReference();
                        CellRangeAddress valueOf = CellRangeAddress.valueOf(dataRangeReference.substring(dataRangeReference.indexOf(33) + 1));
                        if (next.categoryData.isNumeric()) {
                            xDDFDataSource = XDDFDataSourcesFactory.fromNumericCellRange(xSSFSheet, valueOf);
                        } else {
                            xDDFDataSource = XDDFDataSourcesFactory.fromStringCellRange(xSSFSheet, valueOf);
                        }
                        if (xDDFDataSource.isNumeric()) {
                            ((XDDFNumericalDataSource) xDDFDataSource).setFormatCode(next.categoryData.getFormatCode());
                        }
                    }
                    if (next.valuesData != null && next.valuesData.isReference()) {
                        String dataRangeReference2 = next.valuesData.getDataRangeReference();
                        xDDFNumericalDataSource = XDDFDataSourcesFactory.fromNumericCellRange(xSSFSheet, CellRangeAddress.valueOf(dataRangeReference2.substring(dataRangeReference2.indexOf(33) + 1)));
                        xDDFNumericalDataSource.setFormatCode(next.valuesData.getFormatCode());
                    }
                } catch (IllegalArgumentException unused) {
                }
                next.replaceData(xDDFDataSource, xDDFNumericalDataSource);
                next.plot();
            }
        }
    }
}
