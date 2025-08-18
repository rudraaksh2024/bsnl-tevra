package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

public class CTChartImpl extends XmlComplexContentImpl implements CTChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "title"), new QName(XSSFRelation.NS_CHART, "autoTitleDeleted"), new QName(XSSFRelation.NS_CHART, "pivotFmts"), new QName(XSSFRelation.NS_CHART, "view3D"), new QName(XSSFRelation.NS_CHART, "floor"), new QName(XSSFRelation.NS_CHART, "sideWall"), new QName(XSSFRelation.NS_CHART, "backWall"), new QName(XSSFRelation.NS_CHART, "plotArea"), new QName(XSSFRelation.NS_CHART, "legend"), new QName(XSSFRelation.NS_CHART, "plotVisOnly"), new QName(XSSFRelation.NS_CHART, "dispBlanksAs"), new QName(XSSFRelation.NS_CHART, "showDLblsOverMax"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTitle getTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            cTTitle = (CTTitle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTitle == null) {
                cTTitle = null;
            }
        }
        return cTTitle;
    }

    public boolean isSetTitle() {
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

    public void setTitle(CTTitle cTTitle) {
        generatedSetterHelperImpl(cTTitle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTitle addNewTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            cTTitle = (CTTitle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTitle;
    }

    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTBoolean getAutoTitleDeleted() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetAutoTitleDeleted() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setAutoTitleDeleted(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[1], 0, 1);
    }

    public CTBoolean addNewAutoTitleDeleted() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTBoolean;
    }

    public void unsetAutoTitleDeleted() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTPivotFmts getPivotFmts() {
        CTPivotFmts find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPivotFmts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setPivotFmts(CTPivotFmts cTPivotFmts) {
        generatedSetterHelperImpl(cTPivotFmts, PROPERTY_QNAME[2], 0, 1);
    }

    public CTPivotFmts addNewPivotFmts() {
        CTPivotFmts add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetPivotFmts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTView3D getView3D() {
        CTView3D cTView3D;
        synchronized (monitor()) {
            check_orphaned();
            cTView3D = (CTView3D) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTView3D == null) {
                cTView3D = null;
            }
        }
        return cTView3D;
    }

    public boolean isSetView3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setView3D(CTView3D cTView3D) {
        generatedSetterHelperImpl(cTView3D, PROPERTY_QNAME[3], 0, 1);
    }

    public CTView3D addNewView3D() {
        CTView3D cTView3D;
        synchronized (monitor()) {
            check_orphaned();
            cTView3D = (CTView3D) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTView3D;
    }

    public void unsetView3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTSurface getFloor() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTSurface == null) {
                cTSurface = null;
            }
        }
        return cTSurface;
    }

    public boolean isSetFloor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setFloor(CTSurface cTSurface) {
        generatedSetterHelperImpl(cTSurface, PROPERTY_QNAME[4], 0, 1);
    }

    public CTSurface addNewFloor() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTSurface;
    }

    public void unsetFloor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTSurface getSideWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTSurface == null) {
                cTSurface = null;
            }
        }
        return cTSurface;
    }

    public boolean isSetSideWall() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setSideWall(CTSurface cTSurface) {
        generatedSetterHelperImpl(cTSurface, PROPERTY_QNAME[5], 0, 1);
    }

    public CTSurface addNewSideWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTSurface;
    }

    public void unsetSideWall() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTSurface getBackWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTSurface == null) {
                cTSurface = null;
            }
        }
        return cTSurface;
    }

    public boolean isSetBackWall() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setBackWall(CTSurface cTSurface) {
        generatedSetterHelperImpl(cTSurface, PROPERTY_QNAME[6], 0, 1);
    }

    public CTSurface addNewBackWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            cTSurface = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTSurface;
    }

    public void unsetBackWall() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTPlotArea getPlotArea() {
        CTPlotArea cTPlotArea;
        synchronized (monitor()) {
            check_orphaned();
            cTPlotArea = (CTPlotArea) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTPlotArea == null) {
                cTPlotArea = null;
            }
        }
        return cTPlotArea;
    }

    public void setPlotArea(CTPlotArea cTPlotArea) {
        generatedSetterHelperImpl(cTPlotArea, PROPERTY_QNAME[7], 0, 1);
    }

    public CTPlotArea addNewPlotArea() {
        CTPlotArea cTPlotArea;
        synchronized (monitor()) {
            check_orphaned();
            cTPlotArea = (CTPlotArea) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPlotArea;
    }

    public CTLegend getLegend() {
        CTLegend cTLegend;
        synchronized (monitor()) {
            check_orphaned();
            cTLegend = (CTLegend) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTLegend == null) {
                cTLegend = null;
            }
        }
        return cTLegend;
    }

    public boolean isSetLegend() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setLegend(CTLegend cTLegend) {
        generatedSetterHelperImpl(cTLegend, PROPERTY_QNAME[8], 0, 1);
    }

    public CTLegend addNewLegend() {
        CTLegend cTLegend;
        synchronized (monitor()) {
            check_orphaned();
            cTLegend = (CTLegend) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTLegend;
    }

    public void unsetLegend() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTBoolean getPlotVisOnly() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetPlotVisOnly() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setPlotVisOnly(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[9], 0, 1);
    }

    public CTBoolean addNewPlotVisOnly() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTBoolean;
    }

    public void unsetPlotVisOnly() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTDispBlanksAs getDispBlanksAs() {
        CTDispBlanksAs cTDispBlanksAs;
        synchronized (monitor()) {
            check_orphaned();
            cTDispBlanksAs = (CTDispBlanksAs) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTDispBlanksAs == null) {
                cTDispBlanksAs = null;
            }
        }
        return cTDispBlanksAs;
    }

    public boolean isSetDispBlanksAs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setDispBlanksAs(CTDispBlanksAs cTDispBlanksAs) {
        generatedSetterHelperImpl(cTDispBlanksAs, PROPERTY_QNAME[10], 0, 1);
    }

    public CTDispBlanksAs addNewDispBlanksAs() {
        CTDispBlanksAs cTDispBlanksAs;
        synchronized (monitor()) {
            check_orphaned();
            cTDispBlanksAs = (CTDispBlanksAs) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTDispBlanksAs;
    }

    public void unsetDispBlanksAs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTBoolean getShowDLblsOverMax() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowDLblsOverMax() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setShowDLblsOverMax(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[11], 0, 1);
    }

    public CTBoolean addNewShowDLblsOverMax() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTBoolean;
    }

    public void unsetShowDLblsOverMax() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[12], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[12], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }
}
