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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTLine3DChartImpl extends XmlComplexContentImpl implements CTLine3DChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "grouping"), new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "dropLines"), new QName(XSSFRelation.NS_CHART, "gapDepth"), new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTLine3DChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGrouping getGrouping() {
        CTGrouping cTGrouping;
        synchronized (monitor()) {
            check_orphaned();
            cTGrouping = (CTGrouping) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGrouping == null) {
                cTGrouping = null;
            }
        }
        return cTGrouping;
    }

    public void setGrouping(CTGrouping cTGrouping) {
        generatedSetterHelperImpl(cTGrouping, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGrouping addNewGrouping() {
        CTGrouping cTGrouping;
        synchronized (monitor()) {
            check_orphaned();
            cTGrouping = (CTGrouping) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGrouping;
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

    public List<CTLineSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTLine3DChartImpl$$ExternalSyntheticLambda5(this), new CTLine3DChartImpl$$ExternalSyntheticLambda6(this), new CTLine3DChartImpl$$ExternalSyntheticLambda7(this), new CTLine3DChartImpl$$ExternalSyntheticLambda8(this), new CTLine3DChartImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTLineSer[] getSerArray() {
        return (CTLineSer[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTLineSer[0]);
    }

    public CTLineSer getSerArray(int i) {
        CTLineSer cTLineSer;
        synchronized (monitor()) {
            check_orphaned();
            cTLineSer = (CTLineSer) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTLineSer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLineSer;
    }

    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSerArray(CTLineSer[] cTLineSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLineSerArr, PROPERTY_QNAME[2]);
    }

    public void setSerArray(int i, CTLineSer cTLineSer) {
        generatedSetterHelperImpl(cTLineSer, PROPERTY_QNAME[2], i, 2);
    }

    public CTLineSer insertNewSer(int i) {
        CTLineSer cTLineSer;
        synchronized (monitor()) {
            check_orphaned();
            cTLineSer = (CTLineSer) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTLineSer;
    }

    public CTLineSer addNewSer() {
        CTLineSer cTLineSer;
        synchronized (monitor()) {
            check_orphaned();
            cTLineSer = (CTLineSer) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTLineSer;
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

    public CTChartLines getDropLines() {
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

    public boolean isSetDropLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setDropLines(CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[4], 0, 1);
    }

    public CTChartLines addNewDropLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTChartLines;
    }

    public void unsetDropLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTGapAmount getGapDepth() {
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

    public boolean isSetGapDepth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setGapDepth(CTGapAmount cTGapAmount) {
        generatedSetterHelperImpl(cTGapAmount, PROPERTY_QNAME[5], 0, 1);
    }

    public CTGapAmount addNewGapDepth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            cTGapAmount = (CTGapAmount) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTGapAmount;
    }

    public void unsetGapDepth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public List<CTUnsignedInt> getAxIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTLine3DChartImpl$$ExternalSyntheticLambda0(this), new CTLine3DChartImpl$$ExternalSyntheticLambda1(this), new CTLine3DChartImpl$$ExternalSyntheticLambda2(this), new CTLine3DChartImpl$$ExternalSyntheticLambda3(this), new CTLine3DChartImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedInt[] getAxIdArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTUnsignedInt[0]);
    }

    public CTUnsignedInt getAxIdArray(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[6], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedIntArr, PROPERTY_QNAME[6]);
    }

    public void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[6], i, 2);
    }

    public CTUnsignedInt insertNewAxId(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTUnsignedInt;
    }

    public void removeAxId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[7], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[7], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
