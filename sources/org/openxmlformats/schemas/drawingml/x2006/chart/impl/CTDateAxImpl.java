package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLblOffset;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTimeUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTDateAxImpl extends XmlComplexContentImpl implements CTDateAx {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "scaling"), new QName(XSSFRelation.NS_CHART, "delete"), new QName(XSSFRelation.NS_CHART, "axPos"), new QName(XSSFRelation.NS_CHART, "majorGridlines"), new QName(XSSFRelation.NS_CHART, "minorGridlines"), new QName(XSSFRelation.NS_CHART, "title"), new QName(XSSFRelation.NS_CHART, "numFmt"), new QName(XSSFRelation.NS_CHART, "majorTickMark"), new QName(XSSFRelation.NS_CHART, "minorTickMark"), new QName(XSSFRelation.NS_CHART, "tickLblPos"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "txPr"), new QName(XSSFRelation.NS_CHART, "crossAx"), new QName(XSSFRelation.NS_CHART, "crosses"), new QName(XSSFRelation.NS_CHART, "crossesAt"), new QName(XSSFRelation.NS_CHART, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), new QName(XSSFRelation.NS_CHART, "lblOffset"), new QName(XSSFRelation.NS_CHART, "baseTimeUnit"), new QName(XSSFRelation.NS_CHART, "majorUnit"), new QName(XSSFRelation.NS_CHART, "majorTimeUnit"), new QName(XSSFRelation.NS_CHART, "minorUnit"), new QName(XSSFRelation.NS_CHART, "minorTimeUnit"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDateAxImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTUnsignedInt getAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTUnsignedInt == null) {
                cTUnsignedInt = null;
            }
        }
        return cTUnsignedInt;
    }

    public void setAxId(CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[0], 0, 1);
    }

    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTUnsignedInt;
    }

    public CTScaling getScaling() {
        CTScaling cTScaling;
        synchronized (monitor()) {
            check_orphaned();
            cTScaling = (CTScaling) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTScaling == null) {
                cTScaling = null;
            }
        }
        return cTScaling;
    }

    public void setScaling(CTScaling cTScaling) {
        generatedSetterHelperImpl(cTScaling, PROPERTY_QNAME[1], 0, 1);
    }

    public CTScaling addNewScaling() {
        CTScaling cTScaling;
        synchronized (monitor()) {
            check_orphaned();
            cTScaling = (CTScaling) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTScaling;
    }

    public CTBoolean getDelete() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetDelete() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setDelete(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[2], 0, 1);
    }

    public CTBoolean addNewDelete() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTBoolean;
    }

    public void unsetDelete() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTAxPos getAxPos() {
        CTAxPos cTAxPos;
        synchronized (monitor()) {
            check_orphaned();
            cTAxPos = (CTAxPos) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTAxPos == null) {
                cTAxPos = null;
            }
        }
        return cTAxPos;
    }

    public void setAxPos(CTAxPos cTAxPos) {
        generatedSetterHelperImpl(cTAxPos, PROPERTY_QNAME[3], 0, 1);
    }

    public CTAxPos addNewAxPos() {
        CTAxPos cTAxPos;
        synchronized (monitor()) {
            check_orphaned();
            cTAxPos = (CTAxPos) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTAxPos;
    }

    public CTChartLines getMajorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTChartLines == null) {
                cTChartLines = null;
            }
        }
        return cTChartLines;
    }

    public boolean isSetMajorGridlines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setMajorGridlines(CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[4], 0, 1);
    }

    public CTChartLines addNewMajorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTChartLines;
    }

    public void unsetMajorGridlines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTChartLines getMinorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTChartLines == null) {
                cTChartLines = null;
            }
        }
        return cTChartLines;
    }

    public boolean isSetMinorGridlines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setMinorGridlines(CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[5], 0, 1);
    }

    public CTChartLines addNewMinorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTChartLines;
    }

    public void unsetMinorGridlines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTTitle getTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            cTTitle = (CTTitle) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setTitle(CTTitle cTTitle) {
        generatedSetterHelperImpl(cTTitle, PROPERTY_QNAME[6], 0, 1);
    }

    public CTTitle addNewTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            cTTitle = (CTTitle) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTTitle;
    }

    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTNumFmt == null) {
                cTNumFmt = null;
            }
        }
        return cTNumFmt;
    }

    public boolean isSetNumFmt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setNumFmt(CTNumFmt cTNumFmt) {
        generatedSetterHelperImpl(cTNumFmt, PROPERTY_QNAME[7], 0, 1);
    }

    public CTNumFmt addNewNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTNumFmt;
    }

    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTickMark getMajorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            cTTickMark = (CTTickMark) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTickMark == null) {
                cTTickMark = null;
            }
        }
        return cTTickMark;
    }

    public boolean isSetMajorTickMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setMajorTickMark(CTTickMark cTTickMark) {
        generatedSetterHelperImpl(cTTickMark, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTickMark addNewMajorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            cTTickMark = (CTTickMark) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTickMark;
    }

    public void unsetMajorTickMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTTickMark getMinorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            cTTickMark = (CTTickMark) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTTickMark == null) {
                cTTickMark = null;
            }
        }
        return cTTickMark;
    }

    public boolean isSetMinorTickMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setMinorTickMark(CTTickMark cTTickMark) {
        generatedSetterHelperImpl(cTTickMark, PROPERTY_QNAME[9], 0, 1);
    }

    public CTTickMark addNewMinorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            cTTickMark = (CTTickMark) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTickMark;
    }

    public void unsetMinorTickMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTTickLblPos getTickLblPos() {
        CTTickLblPos cTTickLblPos;
        synchronized (monitor()) {
            check_orphaned();
            cTTickLblPos = (CTTickLblPos) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTTickLblPos == null) {
                cTTickLblPos = null;
            }
        }
        return cTTickLblPos;
    }

    public boolean isSetTickLblPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTickLblPos(CTTickLblPos cTTickLblPos) {
        generatedSetterHelperImpl(cTTickLblPos, PROPERTY_QNAME[10], 0, 1);
    }

    public CTTickLblPos addNewTickLblPos() {
        CTTickLblPos cTTickLblPos;
        synchronized (monitor()) {
            check_orphaned();
            cTTickLblPos = (CTTickLblPos) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTTickLblPos;
    }

    public void unsetTickLblPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[11], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[11], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTTextBody getTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTTextBody == null) {
                cTTextBody = null;
            }
        }
        return cTTextBody;
    }

    public boolean isSetTxPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setTxPr(CTTextBody cTTextBody) {
        generatedSetterHelperImpl(cTTextBody, PROPERTY_QNAME[12], 0, 1);
    }

    public CTTextBody addNewTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTTextBody;
    }

    public void unsetTxPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTUnsignedInt getCrossAx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTUnsignedInt == null) {
                cTUnsignedInt = null;
            }
        }
        return cTUnsignedInt;
    }

    public void setCrossAx(CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[13], 0, 1);
    }

    public CTUnsignedInt addNewCrossAx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTUnsignedInt;
    }

    public CTCrosses getCrosses() {
        CTCrosses cTCrosses;
        synchronized (monitor()) {
            check_orphaned();
            cTCrosses = (CTCrosses) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTCrosses == null) {
                cTCrosses = null;
            }
        }
        return cTCrosses;
    }

    public boolean isSetCrosses() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setCrosses(CTCrosses cTCrosses) {
        generatedSetterHelperImpl(cTCrosses, PROPERTY_QNAME[14], 0, 1);
    }

    public CTCrosses addNewCrosses() {
        CTCrosses cTCrosses;
        synchronized (monitor()) {
            check_orphaned();
            cTCrosses = (CTCrosses) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTCrosses;
    }

    public void unsetCrosses() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTDouble getCrossesAt() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetCrossesAt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setCrossesAt(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[15], 0, 1);
    }

    public CTDouble addNewCrossesAt() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTDouble;
    }

    public void unsetCrossesAt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public CTBoolean getAuto() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetAuto() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    public void setAuto(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[16], 0, 1);
    }

    public CTBoolean addNewAuto() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTBoolean;
    }

    public void unsetAuto() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    public CTLblOffset getLblOffset() {
        CTLblOffset find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetLblOffset() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setLblOffset(CTLblOffset cTLblOffset) {
        generatedSetterHelperImpl(cTLblOffset, PROPERTY_QNAME[17], 0, 1);
    }

    public CTLblOffset addNewLblOffset() {
        CTLblOffset add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return add_element_user;
    }

    public void unsetLblOffset() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTTimeUnit getBaseTimeUnit() {
        CTTimeUnit find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetBaseTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setBaseTimeUnit(CTTimeUnit cTTimeUnit) {
        generatedSetterHelperImpl(cTTimeUnit, PROPERTY_QNAME[18], 0, 1);
    }

    public CTTimeUnit addNewBaseTimeUnit() {
        CTTimeUnit add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return add_element_user;
    }

    public void unsetBaseTimeUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public CTAxisUnit getMajorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            cTAxisUnit = (CTAxisUnit) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (cTAxisUnit == null) {
                cTAxisUnit = null;
            }
        }
        return cTAxisUnit;
    }

    public boolean isSetMajorUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setMajorUnit(CTAxisUnit cTAxisUnit) {
        generatedSetterHelperImpl(cTAxisUnit, PROPERTY_QNAME[19], 0, 1);
    }

    public CTAxisUnit addNewMajorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            cTAxisUnit = (CTAxisUnit) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTAxisUnit;
    }

    public void unsetMajorUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public CTTimeUnit getMajorTimeUnit() {
        CTTimeUnit find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetMajorTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    public void setMajorTimeUnit(CTTimeUnit cTTimeUnit) {
        generatedSetterHelperImpl(cTTimeUnit, PROPERTY_QNAME[20], 0, 1);
    }

    public CTTimeUnit addNewMajorTimeUnit() {
        CTTimeUnit add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return add_element_user;
    }

    public void unsetMajorTimeUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    public CTAxisUnit getMinorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            cTAxisUnit = (CTAxisUnit) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (cTAxisUnit == null) {
                cTAxisUnit = null;
            }
        }
        return cTAxisUnit;
    }

    public boolean isSetMinorUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setMinorUnit(CTAxisUnit cTAxisUnit) {
        generatedSetterHelperImpl(cTAxisUnit, PROPERTY_QNAME[21], 0, 1);
    }

    public CTAxisUnit addNewMinorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            cTAxisUnit = (CTAxisUnit) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTAxisUnit;
    }

    public void unsetMinorUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public CTTimeUnit getMinorTimeUnit() {
        CTTimeUnit find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetMinorTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    public void setMinorTimeUnit(CTTimeUnit cTTimeUnit) {
        generatedSetterHelperImpl(cTTimeUnit, PROPERTY_QNAME[22], 0, 1);
    }

    public CTTimeUnit addNewMinorTimeUnit() {
        CTTimeUnit add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return add_element_user;
    }

    public void unsetMinorTimeUnit() {
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
