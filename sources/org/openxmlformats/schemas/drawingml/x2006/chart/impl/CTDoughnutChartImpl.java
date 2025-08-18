package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTFirstSliceAng;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHoleSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;

public class CTDoughnutChartImpl extends XmlComplexContentImpl implements CTDoughnutChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "firstSliceAng"), new QName(XSSFRelation.NS_CHART, "holeSize"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDoughnutChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBoolean getVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[0], 0);
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
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setVaryColors(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBoolean addNewVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBoolean;
    }

    public void unsetVaryColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTPieSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDoughnutChartImpl$$ExternalSyntheticLambda0(this), new CTDoughnutChartImpl$$ExternalSyntheticLambda1(this), new CTDoughnutChartImpl$$ExternalSyntheticLambda2(this), new CTDoughnutChartImpl$$ExternalSyntheticLambda3(this), new CTDoughnutChartImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTPieSer[] getSerArray() {
        return (CTPieSer[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTPieSer[0]);
    }

    public CTPieSer getSerArray(int i) {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().find_element_user(PROPERTY_QNAME[1], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSerArray(CTPieSer[] cTPieSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPieSerArr, PROPERTY_QNAME[1]);
    }

    public void setSerArray(int i, CTPieSer cTPieSer) {
        generatedSetterHelperImpl(cTPieSer, PROPERTY_QNAME[1], i, 2);
    }

    public CTPieSer insertNewSer(int i) {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTPieSer;
    }

    public CTPieSer addNewSer() {
        CTPieSer cTPieSer;
        synchronized (monitor()) {
            check_orphaned();
            cTPieSer = (CTPieSer) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPieSer;
    }

    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[2], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setDLbls(CTDLbls cTDLbls) {
        generatedSetterHelperImpl(cTDLbls, PROPERTY_QNAME[2], 0, 1);
    }

    public CTDLbls addNewDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTDLbls;
    }

    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTFirstSliceAng getFirstSliceAng() {
        CTFirstSliceAng cTFirstSliceAng;
        synchronized (monitor()) {
            check_orphaned();
            cTFirstSliceAng = (CTFirstSliceAng) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTFirstSliceAng == null) {
                cTFirstSliceAng = null;
            }
        }
        return cTFirstSliceAng;
    }

    public boolean isSetFirstSliceAng() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setFirstSliceAng(CTFirstSliceAng cTFirstSliceAng) {
        generatedSetterHelperImpl(cTFirstSliceAng, PROPERTY_QNAME[3], 0, 1);
    }

    public CTFirstSliceAng addNewFirstSliceAng() {
        CTFirstSliceAng cTFirstSliceAng;
        synchronized (monitor()) {
            check_orphaned();
            cTFirstSliceAng = (CTFirstSliceAng) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTFirstSliceAng;
    }

    public void unsetFirstSliceAng() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTHoleSize getHoleSize() {
        CTHoleSize cTHoleSize;
        synchronized (monitor()) {
            check_orphaned();
            cTHoleSize = (CTHoleSize) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTHoleSize == null) {
                cTHoleSize = null;
            }
        }
        return cTHoleSize;
    }

    public boolean isSetHoleSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setHoleSize(CTHoleSize cTHoleSize) {
        generatedSetterHelperImpl(cTHoleSize, PROPERTY_QNAME[4], 0, 1);
    }

    public CTHoleSize addNewHoleSize() {
        CTHoleSize cTHoleSize;
        synchronized (monitor()) {
            check_orphaned();
            cTHoleSize = (CTHoleSize) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTHoleSize;
    }

    public void unsetHoleSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[5], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
