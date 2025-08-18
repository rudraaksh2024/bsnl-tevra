package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCustSplit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSecondPieSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSplitType;

public class CTOfPieChartImpl extends XmlComplexContentImpl implements CTOfPieChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "ofPieType"), new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "gapWidth"), new QName(XSSFRelation.NS_CHART, "splitType"), new QName(XSSFRelation.NS_CHART, "splitPos"), new QName(XSSFRelation.NS_CHART, "custSplit"), new QName(XSSFRelation.NS_CHART, "secondPieSize"), new QName(XSSFRelation.NS_CHART, "serLines"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTOfPieChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTOfPieType getOfPieType() {
        CTOfPieType cTOfPieType;
        synchronized (monitor()) {
            check_orphaned();
            cTOfPieType = (CTOfPieType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTOfPieType == null) {
                cTOfPieType = null;
            }
        }
        return cTOfPieType;
    }

    public void setOfPieType(CTOfPieType cTOfPieType) {
        generatedSetterHelperImpl(cTOfPieType, PROPERTY_QNAME[0], 0, 1);
    }

    public CTOfPieType addNewOfPieType() {
        CTOfPieType cTOfPieType;
        synchronized (monitor()) {
            check_orphaned();
            cTOfPieType = (CTOfPieType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTOfPieType;
    }

    public CTBoolean getVaryColors() {
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

    public boolean isSetVaryColors() {
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

    public void setVaryColors(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[1], 0, 1);
    }

    public CTBoolean addNewVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTBoolean;
    }

    public void unsetVaryColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public List<CTPieSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTOfPieChartImpl$$ExternalSyntheticLambda0(this), new CTOfPieChartImpl$$ExternalSyntheticLambda1(this), new CTOfPieChartImpl$$ExternalSyntheticLambda2(this), new CTOfPieChartImpl$$ExternalSyntheticLambda3(this), new CTOfPieChartImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTPieSer[] getSerArray() {
        return (CTPieSer[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTPieSer[0]);
    }

    public CTPieSer getSerArray(int i) {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTPieSer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPieSer;
    }

    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSerArray(CTPieSer[] cTPieSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPieSerArr, PROPERTY_QNAME[2]);
    }

    public void setSerArray(int i, CTPieSer cTPieSer) {
        generatedSetterHelperImpl(cTPieSer, PROPERTY_QNAME[2], i, 2);
    }

    public CTPieSer insertNewSer(int i) {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTPieSer;
    }

    public CTPieSer addNewSer() {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPieSer;
    }

    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setDLbls(CTDLbls cTDLbls) {
        generatedSetterHelperImpl(cTDLbls, PROPERTY_QNAME[3], 0, 1);
    }

    public CTDLbls addNewDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDLbls;
    }

    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTGapAmount getGapWidth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            cTGapAmount = (CTGapAmount) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setGapWidth(CTGapAmount cTGapAmount) {
        generatedSetterHelperImpl(cTGapAmount, PROPERTY_QNAME[4], 0, 1);
    }

    public CTGapAmount addNewGapWidth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            cTGapAmount = (CTGapAmount) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTGapAmount;
    }

    public void unsetGapWidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTSplitType getSplitType() {
        CTSplitType cTSplitType;
        synchronized (monitor()) {
            check_orphaned();
            cTSplitType = (CTSplitType) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTSplitType == null) {
                cTSplitType = null;
            }
        }
        return cTSplitType;
    }

    public boolean isSetSplitType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setSplitType(CTSplitType cTSplitType) {
        generatedSetterHelperImpl(cTSplitType, PROPERTY_QNAME[5], 0, 1);
    }

    public CTSplitType addNewSplitType() {
        CTSplitType cTSplitType;
        synchronized (monitor()) {
            check_orphaned();
            cTSplitType = (CTSplitType) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTSplitType;
    }

    public void unsetSplitType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTDouble getSplitPos() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetSplitPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setSplitPos(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[6], 0, 1);
    }

    public CTDouble addNewSplitPos() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTDouble;
    }

    public void unsetSplitPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTCustSplit getCustSplit() {
        CTCustSplit cTCustSplit;
        synchronized (monitor()) {
            check_orphaned();
            cTCustSplit = (CTCustSplit) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTCustSplit == null) {
                cTCustSplit = null;
            }
        }
        return cTCustSplit;
    }

    public boolean isSetCustSplit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setCustSplit(CTCustSplit cTCustSplit) {
        generatedSetterHelperImpl(cTCustSplit, PROPERTY_QNAME[7], 0, 1);
    }

    public CTCustSplit addNewCustSplit() {
        CTCustSplit cTCustSplit;
        synchronized (monitor()) {
            check_orphaned();
            cTCustSplit = (CTCustSplit) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTCustSplit;
    }

    public void unsetCustSplit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTSecondPieSize getSecondPieSize() {
        CTSecondPieSize cTSecondPieSize;
        synchronized (monitor()) {
            check_orphaned();
            cTSecondPieSize = (CTSecondPieSize) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTSecondPieSize == null) {
                cTSecondPieSize = null;
            }
        }
        return cTSecondPieSize;
    }

    public boolean isSetSecondPieSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setSecondPieSize(CTSecondPieSize cTSecondPieSize) {
        generatedSetterHelperImpl(cTSecondPieSize, PROPERTY_QNAME[8], 0, 1);
    }

    public CTSecondPieSize addNewSecondPieSize() {
        CTSecondPieSize cTSecondPieSize;
        synchronized (monitor()) {
            check_orphaned();
            cTSecondPieSize = (CTSecondPieSize) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTSecondPieSize;
    }

    public void unsetSecondPieSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public List<CTChartLines> getSerLinesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTOfPieChartImpl$$ExternalSyntheticLambda5(this), new CTOfPieChartImpl$$ExternalSyntheticLambda6(this), new CTOfPieChartImpl$$ExternalSyntheticLambda7(this), new CTOfPieChartImpl$$ExternalSyntheticLambda8(this), new CTOfPieChartImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTChartLines[] getSerLinesArray() {
        return (CTChartLines[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTChartLines[0]);
    }

    public CTChartLines getSerLinesArray(int i) {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[9], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setSerLinesArray(CTChartLines[] cTChartLinesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTChartLinesArr, PROPERTY_QNAME[9]);
    }

    public void setSerLinesArray(int i, CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[9], i, 2);
    }

    public CTChartLines insertNewSerLines(int i) {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTChartLines;
    }

    public CTChartLines addNewSerLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTChartLines;
    }

    public void removeSerLines(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[10], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }
}
