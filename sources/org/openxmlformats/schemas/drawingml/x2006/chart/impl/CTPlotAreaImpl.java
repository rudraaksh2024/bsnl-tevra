package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBubbleChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDTable;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStockChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTPlotAreaImpl extends XmlComplexContentImpl implements CTPlotArea {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "layout"), new QName(XSSFRelation.NS_CHART, "areaChart"), new QName(XSSFRelation.NS_CHART, "area3DChart"), new QName(XSSFRelation.NS_CHART, "lineChart"), new QName(XSSFRelation.NS_CHART, "line3DChart"), new QName(XSSFRelation.NS_CHART, "stockChart"), new QName(XSSFRelation.NS_CHART, "radarChart"), new QName(XSSFRelation.NS_CHART, "scatterChart"), new QName(XSSFRelation.NS_CHART, "pieChart"), new QName(XSSFRelation.NS_CHART, "pie3DChart"), new QName(XSSFRelation.NS_CHART, "doughnutChart"), new QName(XSSFRelation.NS_CHART, "barChart"), new QName(XSSFRelation.NS_CHART, "bar3DChart"), new QName(XSSFRelation.NS_CHART, "ofPieChart"), new QName(XSSFRelation.NS_CHART, "surfaceChart"), new QName(XSSFRelation.NS_CHART, "surface3DChart"), new QName(XSSFRelation.NS_CHART, "bubbleChart"), new QName(XSSFRelation.NS_CHART, "valAx"), new QName(XSSFRelation.NS_CHART, "catAx"), new QName(XSSFRelation.NS_CHART, "dateAx"), new QName(XSSFRelation.NS_CHART, "serAx"), new QName(XSSFRelation.NS_CHART, "dTable"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTPlotAreaImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTLayout getLayout() {
        CTLayout cTLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTLayout = (CTLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTLayout == null) {
                cTLayout = null;
            }
        }
        return cTLayout;
    }

    public boolean isSetLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setLayout(CTLayout cTLayout) {
        generatedSetterHelperImpl(cTLayout, PROPERTY_QNAME[0], 0, 1);
    }

    public CTLayout addNewLayout() {
        CTLayout cTLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTLayout = (CTLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTLayout;
    }

    public void unsetLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTAreaChart> getAreaChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda67(this), new CTPlotAreaImpl$$ExternalSyntheticLambda68(this), new CTPlotAreaImpl$$ExternalSyntheticLambda69(this), new CTPlotAreaImpl$$ExternalSyntheticLambda70(this), new CTPlotAreaImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTAreaChart[] getAreaChartArray() {
        return (CTAreaChart[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTAreaChart[0]);
    }

    public CTAreaChart getAreaChartArray(int i) {
        CTAreaChart cTAreaChart;
        synchronized (monitor()) {
            check_orphaned();
            cTAreaChart = (CTAreaChart) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTAreaChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAreaChart;
    }

    public int sizeOfAreaChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setAreaChartArray(CTAreaChart[] cTAreaChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAreaChartArr, PROPERTY_QNAME[1]);
    }

    public void setAreaChartArray(int i, CTAreaChart cTAreaChart) {
        generatedSetterHelperImpl(cTAreaChart, PROPERTY_QNAME[1], i, 2);
    }

    public CTAreaChart insertNewAreaChart(int i) {
        CTAreaChart cTAreaChart;
        synchronized (monitor()) {
            check_orphaned();
            cTAreaChart = (CTAreaChart) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTAreaChart;
    }

    public CTAreaChart addNewAreaChart() {
        CTAreaChart cTAreaChart;
        synchronized (monitor()) {
            check_orphaned();
            cTAreaChart = (CTAreaChart) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTAreaChart;
    }

    public void removeAreaChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTArea3DChart> getArea3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda55(this), new CTPlotAreaImpl$$ExternalSyntheticLambda66(this), new CTPlotAreaImpl$$ExternalSyntheticLambda77(this), new CTPlotAreaImpl$$ExternalSyntheticLambda88(this), new CTPlotAreaImpl$$ExternalSyntheticLambda99(this));
        }
        return javaListXmlObject;
    }

    public CTArea3DChart[] getArea3DChartArray() {
        return (CTArea3DChart[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTArea3DChart[0]);
    }

    public CTArea3DChart getArea3DChartArray(int i) {
        CTArea3DChart cTArea3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTArea3DChart = (CTArea3DChart) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTArea3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTArea3DChart;
    }

    public int sizeOfArea3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setArea3DChartArray(CTArea3DChart[] cTArea3DChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTArea3DChartArr, PROPERTY_QNAME[2]);
    }

    public void setArea3DChartArray(int i, CTArea3DChart cTArea3DChart) {
        generatedSetterHelperImpl(cTArea3DChart, PROPERTY_QNAME[2], i, 2);
    }

    public CTArea3DChart insertNewArea3DChart(int i) {
        CTArea3DChart cTArea3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTArea3DChart = (CTArea3DChart) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTArea3DChart;
    }

    public CTArea3DChart addNewArea3DChart() {
        CTArea3DChart cTArea3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTArea3DChart = (CTArea3DChart) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTArea3DChart;
    }

    public void removeArea3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTLineChart> getLineChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda0(this), new CTPlotAreaImpl$$ExternalSyntheticLambda11(this), new CTPlotAreaImpl$$ExternalSyntheticLambda22(this), new CTPlotAreaImpl$$ExternalSyntheticLambda33(this), new CTPlotAreaImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTLineChart[] getLineChartArray() {
        return (CTLineChart[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTLineChart[0]);
    }

    public CTLineChart getLineChartArray(int i) {
        CTLineChart cTLineChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLineChart = (CTLineChart) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTLineChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLineChart;
    }

    public int sizeOfLineChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setLineChartArray(CTLineChart[] cTLineChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLineChartArr, PROPERTY_QNAME[3]);
    }

    public void setLineChartArray(int i, CTLineChart cTLineChart) {
        generatedSetterHelperImpl(cTLineChart, PROPERTY_QNAME[3], i, 2);
    }

    public CTLineChart insertNewLineChart(int i) {
        CTLineChart cTLineChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLineChart = (CTLineChart) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTLineChart;
    }

    public CTLineChart addNewLineChart() {
        CTLineChart cTLineChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLineChart = (CTLineChart) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTLineChart;
    }

    public void removeLineChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTLine3DChart> getLine3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda12(this), new CTPlotAreaImpl$$ExternalSyntheticLambda13(this), new CTPlotAreaImpl$$ExternalSyntheticLambda14(this), new CTPlotAreaImpl$$ExternalSyntheticLambda15(this), new CTPlotAreaImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTLine3DChart[] getLine3DChartArray() {
        return (CTLine3DChart[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTLine3DChart[0]);
    }

    public CTLine3DChart getLine3DChartArray(int i) {
        CTLine3DChart cTLine3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLine3DChart = (CTLine3DChart) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTLine3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLine3DChart;
    }

    public int sizeOfLine3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setLine3DChartArray(CTLine3DChart[] cTLine3DChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLine3DChartArr, PROPERTY_QNAME[4]);
    }

    public void setLine3DChartArray(int i, CTLine3DChart cTLine3DChart) {
        generatedSetterHelperImpl(cTLine3DChart, PROPERTY_QNAME[4], i, 2);
    }

    public CTLine3DChart insertNewLine3DChart(int i) {
        CTLine3DChart cTLine3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLine3DChart = (CTLine3DChart) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTLine3DChart;
    }

    public CTLine3DChart addNewLine3DChart() {
        CTLine3DChart cTLine3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTLine3DChart = (CTLine3DChart) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTLine3DChart;
    }

    public void removeLine3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTStockChart> getStockChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda50(this), new CTPlotAreaImpl$$ExternalSyntheticLambda51(this), new CTPlotAreaImpl$$ExternalSyntheticLambda52(this), new CTPlotAreaImpl$$ExternalSyntheticLambda53(this), new CTPlotAreaImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTStockChart[] getStockChartArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTStockChart[0]);
    }

    public CTStockChart getStockChartArray(int i) {
        CTStockChart find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfStockChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setStockChartArray(CTStockChart[] cTStockChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTStockChartArr, PROPERTY_QNAME[5]);
    }

    public void setStockChartArray(int i, CTStockChart cTStockChart) {
        generatedSetterHelperImpl(cTStockChart, PROPERTY_QNAME[5], i, 2);
    }

    public CTStockChart insertNewStockChart(int i) {
        CTStockChart insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return insert_element_user;
    }

    public CTStockChart addNewStockChart() {
        CTStockChart add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void removeStockChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTRadarChart> getRadarChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda34(this), new CTPlotAreaImpl$$ExternalSyntheticLambda35(this), new CTPlotAreaImpl$$ExternalSyntheticLambda36(this), new CTPlotAreaImpl$$ExternalSyntheticLambda37(this), new CTPlotAreaImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTRadarChart[] getRadarChartArray() {
        return (CTRadarChart[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTRadarChart[0]);
    }

    public CTRadarChart getRadarChartArray(int i) {
        CTRadarChart cTRadarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarChart = (CTRadarChart) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTRadarChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRadarChart;
    }

    public int sizeOfRadarChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setRadarChartArray(CTRadarChart[] cTRadarChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRadarChartArr, PROPERTY_QNAME[6]);
    }

    public void setRadarChartArray(int i, CTRadarChart cTRadarChart) {
        generatedSetterHelperImpl(cTRadarChart, PROPERTY_QNAME[6], i, 2);
    }

    public CTRadarChart insertNewRadarChart(int i) {
        CTRadarChart cTRadarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarChart = (CTRadarChart) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTRadarChart;
    }

    public CTRadarChart addNewRadarChart() {
        CTRadarChart cTRadarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarChart = (CTRadarChart) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTRadarChart;
    }

    public void removeRadarChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTScatterChart> getScatterChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda78(this), new CTPlotAreaImpl$$ExternalSyntheticLambda79(this), new CTPlotAreaImpl$$ExternalSyntheticLambda80(this), new CTPlotAreaImpl$$ExternalSyntheticLambda81(this), new CTPlotAreaImpl$$ExternalSyntheticLambda82(this));
        }
        return javaListXmlObject;
    }

    public CTScatterChart[] getScatterChartArray() {
        return (CTScatterChart[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTScatterChart[0]);
    }

    public CTScatterChart getScatterChartArray(int i) {
        CTScatterChart cTScatterChart;
        synchronized (monitor()) {
            check_orphaned();
            cTScatterChart = (CTScatterChart) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTScatterChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTScatterChart;
    }

    public int sizeOfScatterChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setScatterChartArray(CTScatterChart[] cTScatterChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTScatterChartArr, PROPERTY_QNAME[7]);
    }

    public void setScatterChartArray(int i, CTScatterChart cTScatterChart) {
        generatedSetterHelperImpl(cTScatterChart, PROPERTY_QNAME[7], i, 2);
    }

    public CTScatterChart insertNewScatterChart(int i) {
        CTScatterChart cTScatterChart;
        synchronized (monitor()) {
            check_orphaned();
            cTScatterChart = (CTScatterChart) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTScatterChart;
    }

    public CTScatterChart addNewScatterChart() {
        CTScatterChart cTScatterChart;
        synchronized (monitor()) {
            check_orphaned();
            cTScatterChart = (CTScatterChart) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTScatterChart;
    }

    public void removeScatterChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTPieChart> getPieChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda28(this), new CTPlotAreaImpl$$ExternalSyntheticLambda29(this), new CTPlotAreaImpl$$ExternalSyntheticLambda30(this), new CTPlotAreaImpl$$ExternalSyntheticLambda31(this), new CTPlotAreaImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTPieChart[] getPieChartArray() {
        return (CTPieChart[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTPieChart[0]);
    }

    public CTPieChart getPieChartArray(int i) {
        CTPieChart cTPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPieChart = (CTPieChart) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTPieChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPieChart;
    }

    public int sizeOfPieChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setPieChartArray(CTPieChart[] cTPieChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPieChartArr, PROPERTY_QNAME[8]);
    }

    public void setPieChartArray(int i, CTPieChart cTPieChart) {
        generatedSetterHelperImpl(cTPieChart, PROPERTY_QNAME[8], i, 2);
    }

    public CTPieChart insertNewPieChart(int i) {
        CTPieChart cTPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPieChart = (CTPieChart) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTPieChart;
    }

    public CTPieChart addNewPieChart() {
        CTPieChart cTPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPieChart = (CTPieChart) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTPieChart;
    }

    public void removePieChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTPie3DChart> getPie3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda83(this), new CTPlotAreaImpl$$ExternalSyntheticLambda84(this), new CTPlotAreaImpl$$ExternalSyntheticLambda85(this), new CTPlotAreaImpl$$ExternalSyntheticLambda86(this), new CTPlotAreaImpl$$ExternalSyntheticLambda87(this));
        }
        return javaListXmlObject;
    }

    public CTPie3DChart[] getPie3DChartArray() {
        return (CTPie3DChart[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTPie3DChart[0]);
    }

    public CTPie3DChart getPie3DChartArray(int i) {
        CTPie3DChart cTPie3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPie3DChart = (CTPie3DChart) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTPie3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPie3DChart;
    }

    public int sizeOfPie3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setPie3DChartArray(CTPie3DChart[] cTPie3DChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPie3DChartArr, PROPERTY_QNAME[9]);
    }

    public void setPie3DChartArray(int i, CTPie3DChart cTPie3DChart) {
        generatedSetterHelperImpl(cTPie3DChart, PROPERTY_QNAME[9], i, 2);
    }

    public CTPie3DChart insertNewPie3DChart(int i) {
        CTPie3DChart cTPie3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPie3DChart = (CTPie3DChart) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTPie3DChart;
    }

    public CTPie3DChart addNewPie3DChart() {
        CTPie3DChart cTPie3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTPie3DChart = (CTPie3DChart) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTPie3DChart;
    }

    public void removePie3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTDoughnutChart> getDoughnutChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda39(this), new CTPlotAreaImpl$$ExternalSyntheticLambda40(this), new CTPlotAreaImpl$$ExternalSyntheticLambda41(this), new CTPlotAreaImpl$$ExternalSyntheticLambda42(this), new CTPlotAreaImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTDoughnutChart[] getDoughnutChartArray() {
        return (CTDoughnutChart[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTDoughnutChart[0]);
    }

    public CTDoughnutChart getDoughnutChartArray(int i) {
        CTDoughnutChart cTDoughnutChart;
        synchronized (monitor()) {
            check_orphaned();
            cTDoughnutChart = (CTDoughnutChart) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTDoughnutChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDoughnutChart;
    }

    public int sizeOfDoughnutChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setDoughnutChartArray(CTDoughnutChart[] cTDoughnutChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDoughnutChartArr, PROPERTY_QNAME[10]);
    }

    public void setDoughnutChartArray(int i, CTDoughnutChart cTDoughnutChart) {
        generatedSetterHelperImpl(cTDoughnutChart, PROPERTY_QNAME[10], i, 2);
    }

    public CTDoughnutChart insertNewDoughnutChart(int i) {
        CTDoughnutChart cTDoughnutChart;
        synchronized (monitor()) {
            check_orphaned();
            cTDoughnutChart = (CTDoughnutChart) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTDoughnutChart;
    }

    public CTDoughnutChart addNewDoughnutChart() {
        CTDoughnutChart cTDoughnutChart;
        synchronized (monitor()) {
            check_orphaned();
            cTDoughnutChart = (CTDoughnutChart) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTDoughnutChart;
    }

    public void removeDoughnutChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTBarChart> getBarChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda94(this), new CTPlotAreaImpl$$ExternalSyntheticLambda95(this), new CTPlotAreaImpl$$ExternalSyntheticLambda96(this), new CTPlotAreaImpl$$ExternalSyntheticLambda97(this), new CTPlotAreaImpl$$ExternalSyntheticLambda98(this));
        }
        return javaListXmlObject;
    }

    public CTBarChart[] getBarChartArray() {
        return (CTBarChart[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTBarChart[0]);
    }

    public CTBarChart getBarChartArray(int i) {
        CTBarChart cTBarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBarChart = (CTBarChart) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTBarChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBarChart;
    }

    public int sizeOfBarChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setBarChartArray(CTBarChart[] cTBarChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBarChartArr, PROPERTY_QNAME[11]);
    }

    public void setBarChartArray(int i, CTBarChart cTBarChart) {
        generatedSetterHelperImpl(cTBarChart, PROPERTY_QNAME[11], i, 2);
    }

    public CTBarChart insertNewBarChart(int i) {
        CTBarChart cTBarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBarChart = (CTBarChart) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTBarChart;
    }

    public CTBarChart addNewBarChart() {
        CTBarChart cTBarChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBarChart = (CTBarChart) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTBarChart;
    }

    public void removeBarChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTBar3DChart> getBar3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda17(this), new CTPlotAreaImpl$$ExternalSyntheticLambda18(this), new CTPlotAreaImpl$$ExternalSyntheticLambda19(this), new CTPlotAreaImpl$$ExternalSyntheticLambda20(this), new CTPlotAreaImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTBar3DChart[] getBar3DChartArray() {
        return (CTBar3DChart[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTBar3DChart[0]);
    }

    public CTBar3DChart getBar3DChartArray(int i) {
        CTBar3DChart cTBar3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBar3DChart = (CTBar3DChart) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTBar3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBar3DChart;
    }

    public int sizeOfBar3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setBar3DChartArray(CTBar3DChart[] cTBar3DChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBar3DChartArr, PROPERTY_QNAME[12]);
    }

    public void setBar3DChartArray(int i, CTBar3DChart cTBar3DChart) {
        generatedSetterHelperImpl(cTBar3DChart, PROPERTY_QNAME[12], i, 2);
    }

    public CTBar3DChart insertNewBar3DChart(int i) {
        CTBar3DChart cTBar3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBar3DChart = (CTBar3DChart) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTBar3DChart;
    }

    public CTBar3DChart addNewBar3DChart() {
        CTBar3DChart cTBar3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTBar3DChart = (CTBar3DChart) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTBar3DChart;
    }

    public void removeBar3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTOfPieChart> getOfPieChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda23(this), new CTPlotAreaImpl$$ExternalSyntheticLambda24(this), new CTPlotAreaImpl$$ExternalSyntheticLambda25(this), new CTPlotAreaImpl$$ExternalSyntheticLambda26(this), new CTPlotAreaImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTOfPieChart[] getOfPieChartArray() {
        return (CTOfPieChart[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTOfPieChart[0]);
    }

    public CTOfPieChart getOfPieChartArray(int i) {
        CTOfPieChart cTOfPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTOfPieChart = (CTOfPieChart) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTOfPieChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOfPieChart;
    }

    public int sizeOfOfPieChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setOfPieChartArray(CTOfPieChart[] cTOfPieChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOfPieChartArr, PROPERTY_QNAME[13]);
    }

    public void setOfPieChartArray(int i, CTOfPieChart cTOfPieChart) {
        generatedSetterHelperImpl(cTOfPieChart, PROPERTY_QNAME[13], i, 2);
    }

    public CTOfPieChart insertNewOfPieChart(int i) {
        CTOfPieChart cTOfPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTOfPieChart = (CTOfPieChart) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTOfPieChart;
    }

    public CTOfPieChart addNewOfPieChart() {
        CTOfPieChart cTOfPieChart;
        synchronized (monitor()) {
            check_orphaned();
            cTOfPieChart = (CTOfPieChart) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTOfPieChart;
    }

    public void removeOfPieChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTSurfaceChart> getSurfaceChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda1(this), new CTPlotAreaImpl$$ExternalSyntheticLambda2(this), new CTPlotAreaImpl$$ExternalSyntheticLambda3(this), new CTPlotAreaImpl$$ExternalSyntheticLambda4(this), new CTPlotAreaImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTSurfaceChart[] getSurfaceChartArray() {
        return (CTSurfaceChart[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTSurfaceChart[0]);
    }

    public CTSurfaceChart getSurfaceChartArray(int i) {
        CTSurfaceChart cTSurfaceChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceChart = (CTSurfaceChart) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTSurfaceChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSurfaceChart;
    }

    public int sizeOfSurfaceChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setSurfaceChartArray(CTSurfaceChart[] cTSurfaceChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSurfaceChartArr, PROPERTY_QNAME[14]);
    }

    public void setSurfaceChartArray(int i, CTSurfaceChart cTSurfaceChart) {
        generatedSetterHelperImpl(cTSurfaceChart, PROPERTY_QNAME[14], i, 2);
    }

    public CTSurfaceChart insertNewSurfaceChart(int i) {
        CTSurfaceChart cTSurfaceChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceChart = (CTSurfaceChart) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTSurfaceChart;
    }

    public CTSurfaceChart addNewSurfaceChart() {
        CTSurfaceChart cTSurfaceChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceChart = (CTSurfaceChart) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTSurfaceChart;
    }

    public void removeSurfaceChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTSurface3DChart> getSurface3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda56(this), new CTPlotAreaImpl$$ExternalSyntheticLambda57(this), new CTPlotAreaImpl$$ExternalSyntheticLambda58(this), new CTPlotAreaImpl$$ExternalSyntheticLambda59(this), new CTPlotAreaImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTSurface3DChart[] getSurface3DChartArray() {
        return (CTSurface3DChart[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTSurface3DChart[0]);
    }

    public CTSurface3DChart getSurface3DChartArray(int i) {
        CTSurface3DChart cTSurface3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface3DChart = (CTSurface3DChart) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (cTSurface3DChart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSurface3DChart;
    }

    public int sizeOfSurface3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setSurface3DChartArray(CTSurface3DChart[] cTSurface3DChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSurface3DChartArr, PROPERTY_QNAME[15]);
    }

    public void setSurface3DChartArray(int i, CTSurface3DChart cTSurface3DChart) {
        generatedSetterHelperImpl(cTSurface3DChart, PROPERTY_QNAME[15], i, 2);
    }

    public CTSurface3DChart insertNewSurface3DChart(int i) {
        CTSurface3DChart cTSurface3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface3DChart = (CTSurface3DChart) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTSurface3DChart;
    }

    public CTSurface3DChart addNewSurface3DChart() {
        CTSurface3DChart cTSurface3DChart;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface3DChart = (CTSurface3DChart) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTSurface3DChart;
    }

    public void removeSurface3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTBubbleChart> getBubbleChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda45(this), new CTPlotAreaImpl$$ExternalSyntheticLambda46(this), new CTPlotAreaImpl$$ExternalSyntheticLambda47(this), new CTPlotAreaImpl$$ExternalSyntheticLambda48(this), new CTPlotAreaImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTBubbleChart[] getBubbleChartArray() {
        return getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTBubbleChart[0]);
    }

    public CTBubbleChart getBubbleChartArray(int i) {
        CTBubbleChart find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfBubbleChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setBubbleChartArray(CTBubbleChart[] cTBubbleChartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBubbleChartArr, PROPERTY_QNAME[16]);
    }

    public void setBubbleChartArray(int i, CTBubbleChart cTBubbleChart) {
        generatedSetterHelperImpl(cTBubbleChart, PROPERTY_QNAME[16], i, 2);
    }

    public CTBubbleChart insertNewBubbleChart(int i) {
        CTBubbleChart insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return insert_element_user;
    }

    public CTBubbleChart addNewBubbleChart() {
        CTBubbleChart add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return add_element_user;
    }

    public void removeBubbleChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTValAx> getValAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda89(this), new CTPlotAreaImpl$$ExternalSyntheticLambda90(this), new CTPlotAreaImpl$$ExternalSyntheticLambda91(this), new CTPlotAreaImpl$$ExternalSyntheticLambda92(this), new CTPlotAreaImpl$$ExternalSyntheticLambda93(this));
        }
        return javaListXmlObject;
    }

    public CTValAx[] getValAxArray() {
        return (CTValAx[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTValAx[0]);
    }

    public CTValAx getValAxArray(int i) {
        CTValAx cTValAx;
        synchronized (monitor()) {
            check_orphaned();
            cTValAx = (CTValAx) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (cTValAx == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTValAx;
    }

    public int sizeOfValAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setValAxArray(CTValAx[] cTValAxArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTValAxArr, PROPERTY_QNAME[17]);
    }

    public void setValAxArray(int i, CTValAx cTValAx) {
        generatedSetterHelperImpl(cTValAx, PROPERTY_QNAME[17], i, 2);
    }

    public CTValAx insertNewValAx(int i) {
        CTValAx cTValAx;
        synchronized (monitor()) {
            check_orphaned();
            cTValAx = (CTValAx) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTValAx;
    }

    public CTValAx addNewValAx() {
        CTValAx cTValAx;
        synchronized (monitor()) {
            check_orphaned();
            cTValAx = (CTValAx) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTValAx;
    }

    public void removeValAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTCatAx> getCatAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda6(this), new CTPlotAreaImpl$$ExternalSyntheticLambda7(this), new CTPlotAreaImpl$$ExternalSyntheticLambda8(this), new CTPlotAreaImpl$$ExternalSyntheticLambda9(this), new CTPlotAreaImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTCatAx[] getCatAxArray() {
        return (CTCatAx[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTCatAx[0]);
    }

    public CTCatAx getCatAxArray(int i) {
        CTCatAx cTCatAx;
        synchronized (monitor()) {
            check_orphaned();
            cTCatAx = (CTCatAx) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (cTCatAx == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCatAx;
    }

    public int sizeOfCatAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setCatAxArray(CTCatAx[] cTCatAxArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCatAxArr, PROPERTY_QNAME[18]);
    }

    public void setCatAxArray(int i, CTCatAx cTCatAx) {
        generatedSetterHelperImpl(cTCatAx, PROPERTY_QNAME[18], i, 2);
    }

    public CTCatAx insertNewCatAx(int i) {
        CTCatAx cTCatAx;
        synchronized (monitor()) {
            check_orphaned();
            cTCatAx = (CTCatAx) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTCatAx;
    }

    public CTCatAx addNewCatAx() {
        CTCatAx cTCatAx;
        synchronized (monitor()) {
            check_orphaned();
            cTCatAx = (CTCatAx) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTCatAx;
    }

    public void removeCatAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTDateAx> getDateAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda72(this), new CTPlotAreaImpl$$ExternalSyntheticLambda73(this), new CTPlotAreaImpl$$ExternalSyntheticLambda74(this), new CTPlotAreaImpl$$ExternalSyntheticLambda75(this), new CTPlotAreaImpl$$ExternalSyntheticLambda76(this));
        }
        return javaListXmlObject;
    }

    public CTDateAx[] getDateAxArray() {
        return (CTDateAx[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTDateAx[0]);
    }

    public CTDateAx getDateAxArray(int i) {
        CTDateAx cTDateAx;
        synchronized (monitor()) {
            check_orphaned();
            cTDateAx = (CTDateAx) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (cTDateAx == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDateAx;
    }

    public int sizeOfDateAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setDateAxArray(CTDateAx[] cTDateAxArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDateAxArr, PROPERTY_QNAME[19]);
    }

    public void setDateAxArray(int i, CTDateAx cTDateAx) {
        generatedSetterHelperImpl(cTDateAx, PROPERTY_QNAME[19], i, 2);
    }

    public CTDateAx insertNewDateAx(int i) {
        CTDateAx cTDateAx;
        synchronized (monitor()) {
            check_orphaned();
            cTDateAx = (CTDateAx) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTDateAx;
    }

    public CTDateAx addNewDateAx() {
        CTDateAx cTDateAx;
        synchronized (monitor()) {
            check_orphaned();
            cTDateAx = (CTDateAx) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTDateAx;
    }

    public void removeDateAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTSerAx> getSerAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPlotAreaImpl$$ExternalSyntheticLambda61(this), new CTPlotAreaImpl$$ExternalSyntheticLambda62(this), new CTPlotAreaImpl$$ExternalSyntheticLambda63(this), new CTPlotAreaImpl$$ExternalSyntheticLambda64(this), new CTPlotAreaImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTSerAx[] getSerAxArray() {
        return (CTSerAx[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTSerAx[0]);
    }

    public CTSerAx getSerAxArray(int i) {
        CTSerAx cTSerAx;
        synchronized (monitor()) {
            check_orphaned();
            cTSerAx = (CTSerAx) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (cTSerAx == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSerAx;
    }

    public int sizeOfSerAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setSerAxArray(CTSerAx[] cTSerAxArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSerAxArr, PROPERTY_QNAME[20]);
    }

    public void setSerAxArray(int i, CTSerAx cTSerAx) {
        generatedSetterHelperImpl(cTSerAx, PROPERTY_QNAME[20], i, 2);
    }

    public CTSerAx insertNewSerAx(int i) {
        CTSerAx cTSerAx;
        synchronized (monitor()) {
            check_orphaned();
            cTSerAx = (CTSerAx) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTSerAx;
    }

    public CTSerAx addNewSerAx() {
        CTSerAx cTSerAx;
        synchronized (monitor()) {
            check_orphaned();
            cTSerAx = (CTSerAx) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTSerAx;
    }

    public void removeSerAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public CTDTable getDTable() {
        CTDTable find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetDTable() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setDTable(CTDTable cTDTable) {
        generatedSetterHelperImpl(cTDTable, PROPERTY_QNAME[21], 0, 1);
    }

    public CTDTable addNewDTable() {
        CTDTable add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return add_element_user;
    }

    public void unsetDTable() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (cTShapeProperties == null) {
                cTShapeProperties = null;
            }
        }
        return cTShapeProperties;
    }

    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[22], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[23], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }
}
