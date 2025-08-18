package org.apache.poi.hssf.usermodel;

import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.HCenterRecord;
import org.apache.poi.hssf.record.HeaderRecord;
import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.hssf.record.ProtectRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.VCenterRecord;
import org.apache.poi.hssf.record.chart.AreaFormatRecord;
import org.apache.poi.hssf.record.chart.AreaRecord;
import org.apache.poi.hssf.record.chart.AxisLineFormatRecord;
import org.apache.poi.hssf.record.chart.AxisOptionsRecord;
import org.apache.poi.hssf.record.chart.AxisParentRecord;
import org.apache.poi.hssf.record.chart.AxisRecord;
import org.apache.poi.hssf.record.chart.AxisUsedRecord;
import org.apache.poi.hssf.record.chart.BarRecord;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.apache.poi.hssf.record.chart.ChartFormatRecord;
import org.apache.poi.hssf.record.chart.ChartRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;
import org.apache.poi.hssf.record.chart.EndRecord;
import org.apache.poi.hssf.record.chart.FontBasisRecord;
import org.apache.poi.hssf.record.chart.FontIndexRecord;
import org.apache.poi.hssf.record.chart.FrameRecord;
import org.apache.poi.hssf.record.chart.LegendRecord;
import org.apache.poi.hssf.record.chart.LineFormatRecord;
import org.apache.poi.hssf.record.chart.LinkedDataRecord;
import org.apache.poi.hssf.record.chart.PlotAreaRecord;
import org.apache.poi.hssf.record.chart.PlotGrowthRecord;
import org.apache.poi.hssf.record.chart.SeriesChartGroupIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.apache.poi.hssf.record.chart.SeriesTextRecord;
import org.apache.poi.hssf.record.chart.SheetPropertiesRecord;
import org.apache.poi.hssf.record.chart.TextRecord;
import org.apache.poi.hssf.record.chart.TickRecord;
import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.apache.poi.hssf.record.chart.ValueRangeRecord;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressBase;

public final class HSSFChart {
    private ChartRecord chartRecord;
    private ChartTitleFormatRecord chartTitleFormat;
    private SeriesTextRecord chartTitleText;
    private LegendRecord legendRecord;
    private List<HSSFSeries> series = new ArrayList();
    private HSSFSheet sheet;
    private HSSFChartType type = HSSFChartType.Unknown;
    private List<ValueRangeRecord> valueRanges = new ArrayList();

    public enum HSSFChartType {
        Area {
            public short getSid() {
                return AreaRecord.sid;
            }
        },
        Bar {
            public short getSid() {
                return BarRecord.sid;
            }
        },
        Line {
            public short getSid() {
                return 4120;
            }
        },
        Pie {
            public short getSid() {
                return 4121;
            }
        },
        Scatter {
            public short getSid() {
                return 4123;
            }
        },
        Unknown {
            public short getSid() {
                return 0;
            }
        };

        public abstract short getSid();
    }

    private HSSFChart(HSSFSheet hSSFSheet, ChartRecord chartRecord2) {
        this.chartRecord = chartRecord2;
        this.sheet = hSSFSheet;
    }

    public void createBarChart(HSSFWorkbook hSSFWorkbook, HSSFSheet hSSFSheet) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(createMSDrawingObjectRecord());
        arrayList.add(createOBJRecord());
        arrayList.add(createBOFRecord());
        arrayList.add(new HeaderRecord(""));
        arrayList.add(new FooterRecord(""));
        arrayList.add(createHCenterRecord());
        arrayList.add(createVCenterRecord());
        arrayList.add(createPrintSetupRecord());
        arrayList.add(createFontBasisRecord1());
        arrayList.add(createFontBasisRecord2());
        arrayList.add(new ProtectRecord(false));
        arrayList.add(createUnitsRecord());
        arrayList.add(createChartRecord(0, 0, 30434904, 19031616));
        arrayList.add(createBeginRecord());
        arrayList.add(createSCLRecord(1, 1));
        arrayList.add(createPlotGrowthRecord(65536, 65536));
        arrayList.add(createFrameRecord1());
        arrayList.add(createBeginRecord());
        arrayList.add(createLineFormatRecord(true));
        arrayList.add(createAreaFormatRecord1());
        arrayList.add(createEndRecord());
        arrayList.add(createSeriesRecord());
        arrayList.add(createBeginRecord());
        arrayList.add(createTitleLinkedDataRecord());
        arrayList.add(createValuesLinkedDataRecord());
        arrayList.add(createCategoriesLinkedDataRecord());
        arrayList.add(createDataFormatRecord());
        arrayList.add(createSeriesToChartGroupRecord());
        arrayList.add(createEndRecord());
        arrayList.add(createSheetPropsRecord());
        arrayList.add(createDefaultTextRecord(2));
        arrayList.add(createAllTextRecord());
        arrayList.add(createBeginRecord());
        arrayList.add(createFontIndexRecord(5));
        arrayList.add(createDirectLinkRecord());
        arrayList.add(createEndRecord());
        arrayList.add(createDefaultTextRecord(3));
        arrayList.add(createUnknownTextRecord());
        arrayList.add(createBeginRecord());
        arrayList.add(createFontIndexRecord(6));
        arrayList.add(createDirectLinkRecord());
        arrayList.add(createEndRecord());
        arrayList.add(createAxisUsedRecord(1));
        createAxisRecords(arrayList);
        arrayList.add(createEndRecord());
        arrayList.add(createDimensionsRecord());
        arrayList.add(createSeriesIndexRecord(2));
        arrayList.add(createSeriesIndexRecord(1));
        arrayList.add(createSeriesIndexRecord(3));
        arrayList.add(EOFRecord.instance);
        hSSFSheet.insertChartRecords(arrayList);
        hSSFWorkbook.insertChartRecord();
    }

    public static HSSFChart[] getSheetCharts(HSSFSheet hSSFSheet) {
        ArrayList arrayList = new ArrayList();
        Iterator<RecordBase> it = hSSFSheet.getSheet().getRecords().iterator();
        HSSFSeries hSSFSeries = null;
        HSSFChart hSSFChart = null;
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                return (HSSFChart[]) arrayList.toArray(new HSSFChart[0]);
            }
            RecordBase next = it.next();
            if (next instanceof ChartRecord) {
                hSSFChart = new HSSFChart(hSSFSheet, (ChartRecord) next);
                arrayList.add(hSSFChart);
                hSSFSeries = null;
            } else if (next instanceof LinkedDataRecord) {
                LinkedDataRecord linkedDataRecord = (LinkedDataRecord) next;
                if (hSSFSeries != null) {
                    hSSFSeries.insertData(linkedDataRecord);
                }
            }
            if (hSSFChart != null) {
                if (next instanceof LegendRecord) {
                    hSSFChart.legendRecord = (LegendRecord) next;
                } else if (next instanceof SeriesRecord) {
                    hSSFSeries = new HSSFSeries((SeriesRecord) next);
                    hSSFChart.series.add(hSSFSeries);
                } else if (next instanceof ChartTitleFormatRecord) {
                    hSSFChart.chartTitleFormat = (ChartTitleFormatRecord) next;
                } else if (next instanceof SeriesTextRecord) {
                    SeriesTextRecord seriesTextRecord = (SeriesTextRecord) next;
                    if (hSSFChart.legendRecord != null || hSSFChart.series.isEmpty()) {
                        hSSFChart.chartTitleText = seriesTextRecord;
                    } else {
                        List<HSSFSeries> list = hSSFChart.series;
                        SeriesTextRecord unused = list.get(list.size() - 1).seriesTitleText = seriesTextRecord;
                    }
                } else if (next instanceof ValueRangeRecord) {
                    hSSFChart.valueRanges.add((ValueRangeRecord) next);
                } else if (next instanceof Record) {
                    Record record = (Record) next;
                    HSSFChartType[] values = HSSFChartType.values();
                    int length = values.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        HSSFChartType hSSFChartType = values[i];
                        if (hSSFChartType != HSSFChartType.Unknown && record.getSid() == hSSFChartType.getSid()) {
                            hSSFChart.type = hSSFChartType;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
    }

    public int getChartX() {
        return this.chartRecord.getX();
    }

    public int getChartY() {
        return this.chartRecord.getY();
    }

    public int getChartWidth() {
        return this.chartRecord.getWidth();
    }

    public int getChartHeight() {
        return this.chartRecord.getHeight();
    }

    public void setChartX(int i) {
        this.chartRecord.setX(i);
    }

    public void setChartY(int i) {
        this.chartRecord.setY(i);
    }

    public void setChartWidth(int i) {
        this.chartRecord.setWidth(i);
    }

    public void setChartHeight(int i) {
        this.chartRecord.setHeight(i);
    }

    public HSSFSeries[] getSeries() {
        return (HSSFSeries[]) this.series.toArray(new HSSFSeries[0]);
    }

    public String getChartTitle() {
        SeriesTextRecord seriesTextRecord = this.chartTitleText;
        if (seriesTextRecord != null) {
            return seriesTextRecord.getText();
        }
        return null;
    }

    public void setChartTitle(String str) {
        SeriesTextRecord seriesTextRecord = this.chartTitleText;
        if (seriesTextRecord != null) {
            seriesTextRecord.setText(str);
            return;
        }
        throw new IllegalStateException("No chart title found to change");
    }

    public void setValueRange(int i, Double d, Double d2, Double d3, Double d4) {
        ValueRangeRecord valueRangeRecord = this.valueRanges.get(i);
        if (valueRangeRecord != null) {
            if (d != null) {
                valueRangeRecord.setAutomaticMinimum(d.isNaN());
                valueRangeRecord.setMinimumAxisValue(d.doubleValue());
            }
            if (d2 != null) {
                valueRangeRecord.setAutomaticMaximum(d2.isNaN());
                valueRangeRecord.setMaximumAxisValue(d2.doubleValue());
            }
            if (d3 != null) {
                valueRangeRecord.setAutomaticMajor(d3.isNaN());
                valueRangeRecord.setMajorIncrement(d3.doubleValue());
            }
            if (d4 != null) {
                valueRangeRecord.setAutomaticMinor(d4.isNaN());
                valueRangeRecord.setMinorIncrement(d4.doubleValue());
            }
        }
    }

    private SeriesIndexRecord createSeriesIndexRecord(int i) {
        SeriesIndexRecord seriesIndexRecord = new SeriesIndexRecord();
        seriesIndexRecord.setIndex((short) i);
        return seriesIndexRecord;
    }

    private DimensionsRecord createDimensionsRecord() {
        DimensionsRecord dimensionsRecord = new DimensionsRecord();
        dimensionsRecord.setFirstRow(0);
        dimensionsRecord.setLastRow(31);
        dimensionsRecord.setFirstCol(0);
        dimensionsRecord.setLastCol(1);
        return dimensionsRecord;
    }

    private HCenterRecord createHCenterRecord() {
        HCenterRecord hCenterRecord = new HCenterRecord();
        hCenterRecord.setHCenter(false);
        return hCenterRecord;
    }

    private VCenterRecord createVCenterRecord() {
        VCenterRecord vCenterRecord = new VCenterRecord();
        vCenterRecord.setVCenter(false);
        return vCenterRecord;
    }

    private PrintSetupRecord createPrintSetupRecord() {
        PrintSetupRecord printSetupRecord = new PrintSetupRecord();
        printSetupRecord.setPaperSize(0);
        printSetupRecord.setScale(18);
        printSetupRecord.setPageStart(1);
        printSetupRecord.setFitWidth(1);
        printSetupRecord.setFitHeight(1);
        printSetupRecord.setLeftToRight(false);
        printSetupRecord.setLandscape(false);
        printSetupRecord.setValidSettings(true);
        printSetupRecord.setNoColor(false);
        printSetupRecord.setDraft(false);
        printSetupRecord.setNotes(false);
        printSetupRecord.setNoOrientation(false);
        printSetupRecord.setUsePage(false);
        printSetupRecord.setHResolution(0);
        printSetupRecord.setVResolution(0);
        printSetupRecord.setHeaderMargin(0.5d);
        printSetupRecord.setFooterMargin(0.5d);
        printSetupRecord.setCopies(15);
        return printSetupRecord;
    }

    private FontBasisRecord createFontBasisRecord1() {
        FontBasisRecord fontBasisRecord = new FontBasisRecord();
        fontBasisRecord.setXBasis(9120);
        fontBasisRecord.setYBasis(5640);
        fontBasisRecord.setHeightBasis(EscherAggregate.ST_ACTIONBUTTONMOVIE);
        fontBasisRecord.setScale(0);
        fontBasisRecord.setIndexToFontTable(5);
        return fontBasisRecord;
    }

    private FontBasisRecord createFontBasisRecord2() {
        FontBasisRecord createFontBasisRecord1 = createFontBasisRecord1();
        createFontBasisRecord1.setIndexToFontTable(6);
        return createFontBasisRecord1;
    }

    private BOFRecord createBOFRecord() {
        BOFRecord bOFRecord = new BOFRecord();
        bOFRecord.setVersion(600);
        bOFRecord.setType(20);
        bOFRecord.setBuild(7422);
        bOFRecord.setBuildYear(1997);
        bOFRecord.setHistoryBitMask(16585);
        bOFRecord.setRequiredVersion(106);
        return bOFRecord;
    }

    private UnknownRecord createOBJRecord() {
        return new UnknownRecord(93, new byte[]{ParenthesisPtg.sid, 0, UnaryPlusPtg.sid, 0, 5, 0, 2, 0, RangePtg.sid, 96, 0, 0, 0, 0, -72, 3, -121, 3, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    private UnknownRecord createMSDrawingObjectRecord() {
        return new UnknownRecord(236, new byte[]{IntersectionPtg.sid, 0, 2, -16, -64, 0, 0, 0, UnionPtg.sid, 0, 8, -16, 8, 0, 0, 0, 2, 0, 0, 0, 2, 4, 0, 0, IntersectionPtg.sid, 0, 3, -16, -88, 0, 0, 0, IntersectionPtg.sid, 0, 4, -16, 40, 0, 0, 0, 1, 0, 9, -16, UnionPtg.sid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 10, -16, 8, 0, 0, 0, 0, 4, 0, 0, 5, 0, 0, 0, IntersectionPtg.sid, 0, 4, -16, 112, 0, 0, 0, -110, 12, 10, -16, 8, 0, 0, 0, 2, 4, 0, 0, 0, 10, 0, 0, -109, 0, 11, -16, TarConstants.LF_FIFO, 0, 0, 0, ByteCompanionObject.MAX_VALUE, 0, 4, 1, 4, 1, -65, 0, 8, 0, 8, 0, -127, 1, 78, 0, 0, 8, -125, 1, 77, 0, 0, 8, -65, 1, UnionPtg.sid, 0, RangePtg.sid, 0, -64, 1, 77, 0, 0, 8, -1, 1, 8, 0, 8, 0, 63, 2, 0, 0, 2, 0, -65, 3, 0, 0, 8, 0, 0, 0, UnionPtg.sid, -16, UnaryPlusPtg.sid, 0, 0, 0, 0, 0, 4, 0, -64, 2, 10, 0, -12, 0, NotEqualPtg.sid, 0, 102, 1, 32, 0, -23, 0, 0, 0, RangePtg.sid, -16, 0, 0, 0, 0});
    }

    private void createAxisRecords(List<Record> list) {
        list.add(createAxisParentRecord());
        list.add(createBeginRecord());
        list.add(createAxisRecord(0));
        list.add(createBeginRecord());
        list.add(createCategorySeriesAxisRecord());
        list.add(createAxisOptionsRecord());
        list.add(createTickRecord1());
        list.add(createEndRecord());
        list.add(createAxisRecord(1));
        list.add(createBeginRecord());
        list.add(createValueRangeRecord());
        list.add(createTickRecord2());
        list.add(createAxisLineFormatRecord(1));
        list.add(createLineFormatRecord(false));
        list.add(createEndRecord());
        list.add(createPlotAreaRecord());
        list.add(createFrameRecord2());
        list.add(createBeginRecord());
        list.add(createLineFormatRecord2());
        list.add(createAreaFormatRecord2());
        list.add(createEndRecord());
        list.add(createChartFormatRecord());
        list.add(createBeginRecord());
        list.add(createBarRecord());
        list.add(createLegendRecord());
        list.add(createBeginRecord());
        list.add(createTextRecord());
        list.add(createBeginRecord());
        list.add(createLinkedDataRecord());
        list.add(createEndRecord());
        list.add(createEndRecord());
        list.add(createEndRecord());
        list.add(createEndRecord());
    }

    private LinkedDataRecord createLinkedDataRecord() {
        LinkedDataRecord linkedDataRecord = new LinkedDataRecord();
        linkedDataRecord.setLinkType((byte) 0);
        linkedDataRecord.setReferenceType((byte) 1);
        linkedDataRecord.setCustomNumberFormat(false);
        linkedDataRecord.setIndexNumberFmtRecord(0);
        linkedDataRecord.setFormulaOfLink((Ptg[]) null);
        return linkedDataRecord;
    }

    private TextRecord createTextRecord() {
        TextRecord textRecord = new TextRecord();
        textRecord.setHorizontalAlignment((byte) 2);
        textRecord.setVerticalAlignment((byte) 2);
        textRecord.setDisplayMode(1);
        textRecord.setRgbColor(0);
        textRecord.setX(-37);
        textRecord.setY(-60);
        textRecord.setWidth(0);
        textRecord.setHeight(0);
        textRecord.setAutoColor(true);
        textRecord.setShowKey(false);
        textRecord.setShowValue(false);
        textRecord.setVertical(false);
        textRecord.setAutoGeneratedText(true);
        textRecord.setGenerated(true);
        textRecord.setAutoLabelDeleted(false);
        textRecord.setAutoBackground(true);
        textRecord.setRotation(0);
        textRecord.setShowCategoryLabelAsPercentage(false);
        textRecord.setShowValueAsPercentage(false);
        textRecord.setShowBubbleSizes(false);
        textRecord.setShowLabel(false);
        textRecord.setIndexOfColorValue(77);
        textRecord.setDataLabelPlacement(0);
        textRecord.setTextRotation(0);
        return textRecord;
    }

    private LegendRecord createLegendRecord() {
        LegendRecord legendRecord2 = new LegendRecord();
        legendRecord2.setXAxisUpperLeft(3542);
        legendRecord2.setYAxisUpperLeft(1566);
        legendRecord2.setXSize(437);
        legendRecord2.setYSize(213);
        legendRecord2.setType((byte) 3);
        legendRecord2.setSpacing((byte) 1);
        legendRecord2.setAutoPosition(true);
        legendRecord2.setAutoSeries(true);
        legendRecord2.setAutoXPositioning(true);
        legendRecord2.setAutoYPositioning(true);
        legendRecord2.setVertical(true);
        legendRecord2.setDataTable(false);
        return legendRecord2;
    }

    private BarRecord createBarRecord() {
        BarRecord barRecord = new BarRecord();
        barRecord.setBarSpace(0);
        barRecord.setCategorySpace(EscherAggregate.ST_TEXTCIRCLEPOUR);
        barRecord.setHorizontal(false);
        barRecord.setStacked(false);
        barRecord.setDisplayAsPercentage(false);
        barRecord.setShadow(false);
        return barRecord;
    }

    private ChartFormatRecord createChartFormatRecord() {
        ChartFormatRecord chartFormatRecord = new ChartFormatRecord();
        chartFormatRecord.setXPosition(0);
        chartFormatRecord.setYPosition(0);
        chartFormatRecord.setWidth(0);
        chartFormatRecord.setHeight(0);
        chartFormatRecord.setVaryDisplayPattern(false);
        return chartFormatRecord;
    }

    private PlotAreaRecord createPlotAreaRecord() {
        return new PlotAreaRecord();
    }

    private AxisLineFormatRecord createAxisLineFormatRecord(short s) {
        AxisLineFormatRecord axisLineFormatRecord = new AxisLineFormatRecord();
        axisLineFormatRecord.setAxisType(s);
        return axisLineFormatRecord;
    }

    private ValueRangeRecord createValueRangeRecord() {
        ValueRangeRecord valueRangeRecord = new ValueRangeRecord();
        valueRangeRecord.setMinimumAxisValue(0.0d);
        valueRangeRecord.setMaximumAxisValue(0.0d);
        valueRangeRecord.setMajorIncrement(0.0d);
        valueRangeRecord.setMinorIncrement(0.0d);
        valueRangeRecord.setCategoryAxisCross(0.0d);
        valueRangeRecord.setAutomaticMinimum(true);
        valueRangeRecord.setAutomaticMaximum(true);
        valueRangeRecord.setAutomaticMajor(true);
        valueRangeRecord.setAutomaticMinor(true);
        valueRangeRecord.setAutomaticCategoryCrossing(true);
        valueRangeRecord.setLogarithmicScale(false);
        valueRangeRecord.setValuesInReverse(false);
        valueRangeRecord.setCrossCategoryAxisAtMaximum(false);
        valueRangeRecord.setReserved(true);
        return valueRangeRecord;
    }

    private TickRecord createTickRecord1() {
        TickRecord tickRecord = new TickRecord();
        tickRecord.setMajorTickType((byte) 2);
        tickRecord.setMinorTickType((byte) 0);
        tickRecord.setLabelPosition((byte) 3);
        tickRecord.setBackground((byte) 1);
        tickRecord.setLabelColorRgb(0);
        tickRecord.setZero1(0);
        tickRecord.setZero2(0);
        tickRecord.setZero3(45);
        tickRecord.setAutorotate(true);
        tickRecord.setAutoTextBackground(true);
        tickRecord.setRotation(0);
        tickRecord.setAutorotate(true);
        tickRecord.setTickColor(77);
        return tickRecord;
    }

    private TickRecord createTickRecord2() {
        TickRecord createTickRecord1 = createTickRecord1();
        createTickRecord1.setZero3(0);
        return createTickRecord1;
    }

    private AxisOptionsRecord createAxisOptionsRecord() {
        AxisOptionsRecord axisOptionsRecord = new AxisOptionsRecord();
        axisOptionsRecord.setMinimumCategory(-28644);
        axisOptionsRecord.setMaximumCategory(-28715);
        axisOptionsRecord.setMajorUnitValue(2);
        axisOptionsRecord.setMajorUnit(0);
        axisOptionsRecord.setMinorUnitValue(1);
        axisOptionsRecord.setMinorUnit(0);
        axisOptionsRecord.setBaseUnit(0);
        axisOptionsRecord.setCrossingPoint(-28644);
        axisOptionsRecord.setDefaultMinimum(true);
        axisOptionsRecord.setDefaultMaximum(true);
        axisOptionsRecord.setDefaultMajor(true);
        axisOptionsRecord.setDefaultMinorUnit(true);
        axisOptionsRecord.setIsDate(true);
        axisOptionsRecord.setDefaultBase(true);
        axisOptionsRecord.setDefaultCross(true);
        axisOptionsRecord.setDefaultDateSettings(true);
        return axisOptionsRecord;
    }

    private CategorySeriesAxisRecord createCategorySeriesAxisRecord() {
        CategorySeriesAxisRecord categorySeriesAxisRecord = new CategorySeriesAxisRecord();
        categorySeriesAxisRecord.setCrossingPoint(1);
        categorySeriesAxisRecord.setLabelFrequency(1);
        categorySeriesAxisRecord.setTickMarkFrequency(1);
        categorySeriesAxisRecord.setValueAxisCrossing(true);
        categorySeriesAxisRecord.setCrossesFarRight(false);
        categorySeriesAxisRecord.setReversed(false);
        return categorySeriesAxisRecord;
    }

    private AxisRecord createAxisRecord(short s) {
        AxisRecord axisRecord = new AxisRecord();
        axisRecord.setAxisType(s);
        return axisRecord;
    }

    private AxisParentRecord createAxisParentRecord() {
        AxisParentRecord axisParentRecord = new AxisParentRecord();
        axisParentRecord.setAxisType(0);
        axisParentRecord.setX(479);
        axisParentRecord.setY(221);
        axisParentRecord.setWidth(2995);
        axisParentRecord.setHeight(2902);
        return axisParentRecord;
    }

    private AxisUsedRecord createAxisUsedRecord(short s) {
        AxisUsedRecord axisUsedRecord = new AxisUsedRecord();
        axisUsedRecord.setNumAxis(s);
        return axisUsedRecord;
    }

    private LinkedDataRecord createDirectLinkRecord() {
        LinkedDataRecord linkedDataRecord = new LinkedDataRecord();
        linkedDataRecord.setLinkType((byte) 0);
        linkedDataRecord.setReferenceType((byte) 1);
        linkedDataRecord.setCustomNumberFormat(false);
        linkedDataRecord.setIndexNumberFmtRecord(0);
        linkedDataRecord.setFormulaOfLink((Ptg[]) null);
        return linkedDataRecord;
    }

    private FontIndexRecord createFontIndexRecord(int i) {
        FontIndexRecord fontIndexRecord = new FontIndexRecord();
        fontIndexRecord.setFontIndex((short) i);
        return fontIndexRecord;
    }

    private TextRecord createAllTextRecord() {
        TextRecord textRecord = new TextRecord();
        textRecord.setHorizontalAlignment((byte) 2);
        textRecord.setVerticalAlignment((byte) 2);
        textRecord.setDisplayMode(1);
        textRecord.setRgbColor(0);
        textRecord.setX(-37);
        textRecord.setY(-60);
        textRecord.setWidth(0);
        textRecord.setHeight(0);
        textRecord.setAutoColor(true);
        textRecord.setShowKey(false);
        textRecord.setShowValue(true);
        textRecord.setVertical(false);
        textRecord.setAutoGeneratedText(true);
        textRecord.setGenerated(true);
        textRecord.setAutoLabelDeleted(false);
        textRecord.setAutoBackground(true);
        textRecord.setRotation(0);
        textRecord.setShowCategoryLabelAsPercentage(false);
        textRecord.setShowValueAsPercentage(false);
        textRecord.setShowBubbleSizes(false);
        textRecord.setShowLabel(false);
        textRecord.setIndexOfColorValue(77);
        textRecord.setDataLabelPlacement(0);
        textRecord.setTextRotation(0);
        return textRecord;
    }

    private TextRecord createUnknownTextRecord() {
        TextRecord textRecord = new TextRecord();
        textRecord.setHorizontalAlignment((byte) 2);
        textRecord.setVerticalAlignment((byte) 2);
        textRecord.setDisplayMode(1);
        textRecord.setRgbColor(0);
        textRecord.setX(-37);
        textRecord.setY(-60);
        textRecord.setWidth(0);
        textRecord.setHeight(0);
        textRecord.setAutoColor(true);
        textRecord.setShowKey(false);
        textRecord.setShowValue(false);
        textRecord.setVertical(false);
        textRecord.setAutoGeneratedText(true);
        textRecord.setGenerated(true);
        textRecord.setAutoLabelDeleted(false);
        textRecord.setAutoBackground(true);
        textRecord.setRotation(0);
        textRecord.setShowCategoryLabelAsPercentage(false);
        textRecord.setShowValueAsPercentage(false);
        textRecord.setShowBubbleSizes(false);
        textRecord.setShowLabel(false);
        textRecord.setIndexOfColorValue(77);
        textRecord.setDataLabelPlacement(11088);
        textRecord.setTextRotation(0);
        return textRecord;
    }

    private DefaultDataLabelTextPropertiesRecord createDefaultTextRecord(short s) {
        DefaultDataLabelTextPropertiesRecord defaultDataLabelTextPropertiesRecord = new DefaultDataLabelTextPropertiesRecord();
        defaultDataLabelTextPropertiesRecord.setCategoryDataType(s);
        return defaultDataLabelTextPropertiesRecord;
    }

    private SheetPropertiesRecord createSheetPropsRecord() {
        SheetPropertiesRecord sheetPropertiesRecord = new SheetPropertiesRecord();
        sheetPropertiesRecord.setChartTypeManuallyFormatted(false);
        sheetPropertiesRecord.setPlotVisibleOnly(true);
        sheetPropertiesRecord.setDoNotSizeWithWindow(false);
        sheetPropertiesRecord.setDefaultPlotDimensions(true);
        sheetPropertiesRecord.setAutoPlotArea(false);
        return sheetPropertiesRecord;
    }

    private SeriesChartGroupIndexRecord createSeriesToChartGroupRecord() {
        return new SeriesChartGroupIndexRecord();
    }

    private DataFormatRecord createDataFormatRecord() {
        DataFormatRecord dataFormatRecord = new DataFormatRecord();
        dataFormatRecord.setPointNumber(-1);
        dataFormatRecord.setSeriesIndex(0);
        dataFormatRecord.setSeriesNumber(0);
        dataFormatRecord.setUseExcel4Colors(false);
        return dataFormatRecord;
    }

    private LinkedDataRecord createCategoriesLinkedDataRecord() {
        LinkedDataRecord linkedDataRecord = new LinkedDataRecord();
        linkedDataRecord.setLinkType((byte) 2);
        linkedDataRecord.setReferenceType((byte) 2);
        linkedDataRecord.setCustomNumberFormat(false);
        linkedDataRecord.setIndexNumberFmtRecord(0);
        linkedDataRecord.setFormulaOfLink(new Ptg[]{new Area3DPtg(0, 31, 1, 1, false, false, false, false, 0)});
        return linkedDataRecord;
    }

    private LinkedDataRecord createValuesLinkedDataRecord() {
        LinkedDataRecord linkedDataRecord = new LinkedDataRecord();
        linkedDataRecord.setLinkType((byte) 1);
        linkedDataRecord.setReferenceType((byte) 2);
        linkedDataRecord.setCustomNumberFormat(false);
        linkedDataRecord.setIndexNumberFmtRecord(0);
        linkedDataRecord.setFormulaOfLink(new Ptg[]{new Area3DPtg(0, 31, 0, 0, false, false, false, false, 0)});
        return linkedDataRecord;
    }

    private LinkedDataRecord createTitleLinkedDataRecord() {
        LinkedDataRecord linkedDataRecord = new LinkedDataRecord();
        linkedDataRecord.setLinkType((byte) 0);
        linkedDataRecord.setReferenceType((byte) 1);
        linkedDataRecord.setCustomNumberFormat(false);
        linkedDataRecord.setIndexNumberFmtRecord(0);
        linkedDataRecord.setFormulaOfLink((Ptg[]) null);
        return linkedDataRecord;
    }

    private SeriesRecord createSeriesRecord() {
        SeriesRecord seriesRecord = new SeriesRecord();
        seriesRecord.setCategoryDataType(1);
        seriesRecord.setValuesDataType(1);
        seriesRecord.setNumCategories(32);
        seriesRecord.setNumValues(31);
        seriesRecord.setBubbleSeriesType(1);
        seriesRecord.setNumBubbleValues(0);
        return seriesRecord;
    }

    private EndRecord createEndRecord() {
        return new EndRecord();
    }

    private AreaFormatRecord createAreaFormatRecord1() {
        AreaFormatRecord areaFormatRecord = new AreaFormatRecord();
        areaFormatRecord.setForegroundColor(ViewCompat.MEASURED_SIZE_MASK);
        areaFormatRecord.setBackgroundColor(0);
        areaFormatRecord.setPattern(1);
        areaFormatRecord.setAutomatic(true);
        areaFormatRecord.setInvert(false);
        areaFormatRecord.setForecolorIndex(78);
        areaFormatRecord.setBackcolorIndex(77);
        return areaFormatRecord;
    }

    private AreaFormatRecord createAreaFormatRecord2() {
        AreaFormatRecord areaFormatRecord = new AreaFormatRecord();
        areaFormatRecord.setForegroundColor(12632256);
        areaFormatRecord.setBackgroundColor(0);
        areaFormatRecord.setPattern(1);
        areaFormatRecord.setAutomatic(false);
        areaFormatRecord.setInvert(false);
        areaFormatRecord.setForecolorIndex(22);
        areaFormatRecord.setBackcolorIndex(79);
        return areaFormatRecord;
    }

    private LineFormatRecord createLineFormatRecord(boolean z) {
        LineFormatRecord lineFormatRecord = new LineFormatRecord();
        lineFormatRecord.setLineColor(0);
        lineFormatRecord.setLinePattern(0);
        lineFormatRecord.setWeight(-1);
        lineFormatRecord.setAuto(true);
        lineFormatRecord.setDrawTicks(z);
        lineFormatRecord.setColourPaletteIndex(77);
        return lineFormatRecord;
    }

    private LineFormatRecord createLineFormatRecord2() {
        LineFormatRecord lineFormatRecord = new LineFormatRecord();
        lineFormatRecord.setLineColor(8421504);
        lineFormatRecord.setLinePattern(0);
        lineFormatRecord.setWeight(0);
        lineFormatRecord.setAuto(false);
        lineFormatRecord.setDrawTicks(false);
        lineFormatRecord.setUnknown(false);
        lineFormatRecord.setColourPaletteIndex(23);
        return lineFormatRecord;
    }

    private FrameRecord createFrameRecord1() {
        FrameRecord frameRecord = new FrameRecord();
        frameRecord.setBorderType(0);
        frameRecord.setAutoSize(false);
        frameRecord.setAutoPosition(true);
        return frameRecord;
    }

    private FrameRecord createFrameRecord2() {
        FrameRecord frameRecord = new FrameRecord();
        frameRecord.setBorderType(0);
        frameRecord.setAutoSize(true);
        frameRecord.setAutoPosition(true);
        return frameRecord;
    }

    private PlotGrowthRecord createPlotGrowthRecord(int i, int i2) {
        PlotGrowthRecord plotGrowthRecord = new PlotGrowthRecord();
        plotGrowthRecord.setHorizontalScale(i);
        plotGrowthRecord.setVerticalScale(i2);
        return plotGrowthRecord;
    }

    private SCLRecord createSCLRecord(short s, short s2) {
        SCLRecord sCLRecord = new SCLRecord();
        sCLRecord.setDenominator(s2);
        sCLRecord.setNumerator(s);
        return sCLRecord;
    }

    private BeginRecord createBeginRecord() {
        return new BeginRecord();
    }

    private ChartRecord createChartRecord(int i, int i2, int i3, int i4) {
        ChartRecord chartRecord2 = new ChartRecord();
        chartRecord2.setX(i);
        chartRecord2.setY(i2);
        chartRecord2.setWidth(i3);
        chartRecord2.setHeight(i4);
        return chartRecord2;
    }

    private UnitsRecord createUnitsRecord() {
        UnitsRecord unitsRecord = new UnitsRecord();
        unitsRecord.setUnits(0);
        return unitsRecord;
    }

    public static class HSSFSeries {
        private LinkedDataRecord dataCategoryLabels;
        private LinkedDataRecord dataName;
        private LinkedDataRecord dataSecondaryCategoryLabels;
        private LinkedDataRecord dataValues;
        /* access modifiers changed from: private */
        public SeriesRecord series;
        /* access modifiers changed from: private */
        public SeriesTextRecord seriesTitleText;

        HSSFSeries(SeriesRecord seriesRecord) {
            this.series = seriesRecord;
        }

        /* access modifiers changed from: package-private */
        public void insertData(LinkedDataRecord linkedDataRecord) {
            byte linkType = linkedDataRecord.getLinkType();
            if (linkType == 0) {
                this.dataName = linkedDataRecord;
            } else if (linkType == 1) {
                this.dataValues = linkedDataRecord;
            } else if (linkType == 2) {
                this.dataCategoryLabels = linkedDataRecord;
            } else if (linkType == 3) {
                this.dataSecondaryCategoryLabels = linkedDataRecord;
            } else {
                throw new IllegalStateException("Invalid link type: " + linkedDataRecord.getLinkType());
            }
        }

        /* access modifiers changed from: package-private */
        public void setSeriesTitleText(SeriesTextRecord seriesTextRecord) {
            this.seriesTitleText = seriesTextRecord;
        }

        public short getNumValues() {
            return this.series.getNumValues();
        }

        public short getValueType() {
            return this.series.getValuesDataType();
        }

        public String getSeriesTitle() {
            SeriesTextRecord seriesTextRecord = this.seriesTitleText;
            if (seriesTextRecord != null) {
                return seriesTextRecord.getText();
            }
            return null;
        }

        public void setSeriesTitle(String str) {
            SeriesTextRecord seriesTextRecord = this.seriesTitleText;
            if (seriesTextRecord != null) {
                seriesTextRecord.setText(str);
                return;
            }
            throw new IllegalStateException("No series title found to change");
        }

        public LinkedDataRecord getDataName() {
            return this.dataName;
        }

        public LinkedDataRecord getDataValues() {
            return this.dataValues;
        }

        public LinkedDataRecord getDataCategoryLabels() {
            return this.dataCategoryLabels;
        }

        public LinkedDataRecord getDataSecondaryCategoryLabels() {
            return this.dataSecondaryCategoryLabels;
        }

        public SeriesRecord getSeries() {
            return this.series;
        }

        private CellRangeAddressBase getCellRange(LinkedDataRecord linkedDataRecord) {
            if (linkedDataRecord == null) {
                return null;
            }
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (Ptg ptg : linkedDataRecord.getFormulaOfLink()) {
                if (ptg instanceof AreaPtgBase) {
                    AreaPtgBase areaPtgBase = (AreaPtgBase) ptg;
                    i = areaPtgBase.getFirstRow();
                    i2 = areaPtgBase.getLastRow();
                    i3 = areaPtgBase.getFirstColumn();
                    i4 = areaPtgBase.getLastColumn();
                }
            }
            return new CellRangeAddress(i, i2, i3, i4);
        }

        public CellRangeAddressBase getValuesCellRange() {
            return getCellRange(this.dataValues);
        }

        public CellRangeAddressBase getCategoryLabelsCellRange() {
            return getCellRange(this.dataCategoryLabels);
        }

        private Integer setVerticalCellRange(LinkedDataRecord linkedDataRecord, CellRangeAddressBase cellRangeAddressBase) {
            if (linkedDataRecord == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int lastRow = (cellRangeAddressBase.getLastRow() - cellRangeAddressBase.getFirstRow()) + 1;
            int lastColumn = (cellRangeAddressBase.getLastColumn() - cellRangeAddressBase.getFirstColumn()) + 1;
            for (Ptg ptg : linkedDataRecord.getFormulaOfLink()) {
                if (ptg instanceof AreaPtgBase) {
                    AreaPtgBase areaPtgBase = (AreaPtgBase) ptg;
                    areaPtgBase.setFirstRow(cellRangeAddressBase.getFirstRow());
                    areaPtgBase.setLastRow(cellRangeAddressBase.getLastRow());
                    areaPtgBase.setFirstColumn(cellRangeAddressBase.getFirstColumn());
                    areaPtgBase.setLastColumn(cellRangeAddressBase.getLastColumn());
                    arrayList.add(areaPtgBase);
                }
            }
            linkedDataRecord.setFormulaOfLink((Ptg[]) arrayList.toArray(new Ptg[0]));
            return Integer.valueOf(lastRow * lastColumn);
        }

        public void setValuesCellRange(CellRangeAddressBase cellRangeAddressBase) {
            Integer verticalCellRange = setVerticalCellRange(this.dataValues, cellRangeAddressBase);
            if (verticalCellRange != null) {
                this.series.setNumValues((short) verticalCellRange.intValue());
            }
        }

        public void setCategoryLabelsCellRange(CellRangeAddressBase cellRangeAddressBase) {
            Integer verticalCellRange = setVerticalCellRange(this.dataCategoryLabels, cellRangeAddressBase);
            if (verticalCellRange != null) {
                this.series.setNumCategories((short) verticalCellRange.intValue());
            }
        }
    }

    public HSSFSeries createSeries() throws Exception {
        BeginRecord beginRecord;
        ArrayList arrayList = new ArrayList();
        List<RecordBase> records = this.sheet.getSheet().getRecords();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        for (RecordBase next : records) {
            i++;
            if (!(next instanceof BeginRecord)) {
                if (next instanceof EndRecord) {
                    i2--;
                    if (i4 == i2) {
                        if (!z) {
                            arrayList.add(next);
                            i6 = i;
                            i4 = -1;
                            z = true;
                        } else {
                            i6 = i;
                            i4 = -1;
                        }
                    }
                    if (i7 == i2) {
                        break;
                    }
                }
            } else {
                i2++;
            }
            if (next instanceof ChartRecord) {
                if (next == this.chartRecord) {
                    i5 = i;
                    i7 = i2;
                }
            } else if ((next instanceof SeriesRecord) && i5 != -1) {
                i3++;
                i4 = i2;
            }
            if (i4 != -1 && !z) {
                arrayList.add(next);
            }
        }
        if (i6 == -1) {
            return null;
        }
        int i8 = i6 + 1;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        HSSFSeries hSSFSeries = null;
        while (it.hasNext()) {
            RecordBase recordBase = (RecordBase) it.next();
            if (recordBase instanceof BeginRecord) {
                beginRecord = new BeginRecord();
            } else if (recordBase instanceof EndRecord) {
                beginRecord = new EndRecord();
            } else if (recordBase instanceof SeriesRecord) {
                SeriesRecord copy = ((SeriesRecord) recordBase).copy();
                hSSFSeries = new HSSFSeries(copy);
                beginRecord = copy;
            } else if (recordBase instanceof LinkedDataRecord) {
                LinkedDataRecord copy2 = ((LinkedDataRecord) recordBase).copy();
                beginRecord = copy2;
                if (hSSFSeries != null) {
                    hSSFSeries.insertData(copy2);
                    beginRecord = copy2;
                }
            } else if (recordBase instanceof DataFormatRecord) {
                DataFormatRecord copy3 = ((DataFormatRecord) recordBase).copy();
                short s = (short) i3;
                copy3.setSeriesIndex(s);
                copy3.setSeriesNumber(s);
                beginRecord = copy3;
            } else if (recordBase instanceof SeriesTextRecord) {
                SeriesTextRecord copy4 = ((SeriesTextRecord) recordBase).copy();
                beginRecord = copy4;
                if (hSSFSeries != null) {
                    hSSFSeries.setSeriesTitleText(copy4);
                    beginRecord = copy4;
                }
            } else {
                beginRecord = recordBase instanceof Record ? ((Record) recordBase).copy() : null;
            }
            if (beginRecord != null) {
                arrayList2.add(beginRecord);
            }
        }
        if (hSSFSeries == null) {
            return null;
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            records.add(i8, (RecordBase) it2.next());
            i8++;
        }
        return hSSFSeries;
    }

    public boolean removeSeries(HSSFSeries hSSFSeries) {
        Iterator<RecordBase> it = this.sheet.getSheet().getRecords().iterator();
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        while (it.hasNext()) {
            RecordBase next = it.next();
            if (!(next instanceof BeginRecord)) {
                if (next instanceof EndRecord) {
                    i--;
                    if (i2 == i) {
                        if (z) {
                            it.remove();
                            z = false;
                            i2 = -1;
                            z3 = true;
                        } else {
                            i2 = -1;
                        }
                    }
                    if (i3 == i) {
                        break;
                    }
                }
            } else {
                i++;
            }
            if (next instanceof ChartRecord) {
                if (next == this.chartRecord) {
                    i3 = i;
                    z2 = true;
                }
            } else if (next instanceof SeriesRecord) {
                if (z2) {
                    if (hSSFSeries.series == next) {
                        i2 = i;
                        z = true;
                    } else {
                        i4++;
                    }
                }
            } else if ((next instanceof DataFormatRecord) && z2 && !z) {
                DataFormatRecord dataFormatRecord = (DataFormatRecord) next;
                short s = (short) i4;
                dataFormatRecord.setSeriesIndex(s);
                dataFormatRecord.setSeriesNumber(s);
            }
            if (z) {
                it.remove();
            }
        }
        return z3;
    }

    public HSSFChartType getType() {
        return this.type;
    }
}
