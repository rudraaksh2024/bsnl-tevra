package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOverlap;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTBarChartImpl extends XmlComplexContentImpl implements CTBarChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "barDir"), new QName(XSSFRelation.NS_CHART, "grouping"), new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "gapWidth"), new QName(XSSFRelation.NS_CHART, "overlap"), new QName(XSSFRelation.NS_CHART, "serLines"), new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTBarChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBarDir getBarDir() {
        CTBarDir cTBarDir;
        synchronized (monitor()) {
            check_orphaned();
            cTBarDir = (CTBarDir) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTBarDir == null) {
                cTBarDir = null;
            }
        }
        return cTBarDir;
    }

    public void setBarDir(CTBarDir cTBarDir) {
        generatedSetterHelperImpl(cTBarDir, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBarDir addNewBarDir() {
        CTBarDir cTBarDir;
        synchronized (monitor()) {
            check_orphaned();
            cTBarDir = (CTBarDir) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBarDir;
    }

    public CTBarGrouping getGrouping() {
        CTBarGrouping cTBarGrouping;
        synchronized (monitor()) {
            check_orphaned();
            cTBarGrouping = (CTBarGrouping) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTBarGrouping == null) {
                cTBarGrouping = null;
            }
        }
        return cTBarGrouping;
    }

    public boolean isSetGrouping() {
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

    public void setGrouping(CTBarGrouping cTBarGrouping) {
        generatedSetterHelperImpl(cTBarGrouping, PROPERTY_QNAME[1], 0, 1);
    }

    public CTBarGrouping addNewGrouping() {
        CTBarGrouping cTBarGrouping;
        synchronized (monitor()) {
            check_orphaned();
            cTBarGrouping = (CTBarGrouping) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTBarGrouping;
    }

    public void unsetGrouping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTBoolean getVaryColors() {
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

    public boolean isSetVaryColors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setVaryColors(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[2], 0, 1);
    }

    public CTBoolean addNewVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTBoolean;
    }

    public void unsetVaryColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public List<CTBarSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBarChartImpl$$ExternalSyntheticLambda0(this), new CTBarChartImpl$$ExternalSyntheticLambda6(this), new CTBarChartImpl$$ExternalSyntheticLambda7(this), new CTBarChartImpl$$ExternalSyntheticLambda8(this), new CTBarChartImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTBarSer[] getSerArray() {
        return (CTBarSer[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTBarSer[0]);
    }

    public CTBarSer getSerArray(int i) {
        CTBarSer cTBarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTBarSer = (CTBarSer) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTBarSer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBarSer;
    }

    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setSerArray(CTBarSer[] cTBarSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBarSerArr, PROPERTY_QNAME[3]);
    }

    public void setSerArray(int i, CTBarSer cTBarSer) {
        generatedSetterHelperImpl(cTBarSer, PROPERTY_QNAME[3], i, 2);
    }

    public CTBarSer insertNewSer(int i) {
        CTBarSer cTBarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTBarSer = (CTBarSer) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTBarSer;
    }

    public CTBarSer addNewSer() {
        CTBarSer cTBarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTBarSer = (CTBarSer) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBarSer;
    }

    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTDLbls == null) {
                cTDLbls = null;
            }
        }
        return cTDLbls;
    }

    public boolean isSetDLbls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setDLbls(CTDLbls cTDLbls) {
        generatedSetterHelperImpl(cTDLbls, PROPERTY_QNAME[4], 0, 1);
    }

    public CTDLbls addNewDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTDLbls;
    }

    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTGapAmount getGapWidth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            cTGapAmount = (CTGapAmount) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTGapAmount == null) {
                cTGapAmount = null;
            }
        }
        return cTGapAmount;
    }

    public boolean isSetGapWidth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setGapWidth(CTGapAmount cTGapAmount) {
        generatedSetterHelperImpl(cTGapAmount, PROPERTY_QNAME[5], 0, 1);
    }

    public CTGapAmount addNewGapWidth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            cTGapAmount = (CTGapAmount) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTGapAmount;
    }

    public void unsetGapWidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTOverlap getOverlap() {
        CTOverlap cTOverlap;
        synchronized (monitor()) {
            check_orphaned();
            cTOverlap = (CTOverlap) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTOverlap == null) {
                cTOverlap = null;
            }
        }
        return cTOverlap;
    }

    public boolean isSetOverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setOverlap(CTOverlap cTOverlap) {
        generatedSetterHelperImpl(cTOverlap, PROPERTY_QNAME[6], 0, 1);
    }

    public CTOverlap addNewOverlap() {
        CTOverlap cTOverlap;
        synchronized (monitor()) {
            check_orphaned();
            cTOverlap = (CTOverlap) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTOverlap;
    }

    public void unsetOverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public List<CTChartLines> getSerLinesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBarChartImpl$$ExternalSyntheticLambda10(this), new CTBarChartImpl$$ExternalSyntheticLambda11(this), new CTBarChartImpl$$ExternalSyntheticLambda12(this), new CTBarChartImpl$$ExternalSyntheticLambda13(this), new CTBarChartImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public CTChartLines[] getSerLinesArray() {
        return (CTChartLines[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTChartLines[0]);
    }

    public CTChartLines getSerLinesArray(int i) {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTChartLines == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTChartLines;
    }

    public int sizeOfSerLinesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setSerLinesArray(CTChartLines[] cTChartLinesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTChartLinesArr, PROPERTY_QNAME[7]);
    }

    public void setSerLinesArray(int i, CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[7], i, 2);
    }

    public CTChartLines insertNewSerLines(int i) {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTChartLines;
    }

    public CTChartLines addNewSerLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTChartLines;
    }

    public void removeSerLines(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTUnsignedInt> getAxIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBarChartImpl$$ExternalSyntheticLambda1(this), new CTBarChartImpl$$ExternalSyntheticLambda2(this), new CTBarChartImpl$$ExternalSyntheticLambda3(this), new CTBarChartImpl$$ExternalSyntheticLambda4(this), new CTBarChartImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedInt[] getAxIdArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTUnsignedInt[0]);
    }

    public CTUnsignedInt getAxIdArray(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTUnsignedInt == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTUnsignedInt;
    }

    public int sizeOfAxIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedIntArr, PROPERTY_QNAME[8]);
    }

    public void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[8], i, 2);
    }

    public CTUnsignedInt insertNewAxId(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTUnsignedInt;
    }

    public void removeAxId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[9], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[9], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }
}
