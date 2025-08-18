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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTRadarChartImpl extends XmlComplexContentImpl implements CTRadarChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "radarStyle"), new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTRadarChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRadarStyle getRadarStyle() {
        CTRadarStyle cTRadarStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarStyle = (CTRadarStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRadarStyle == null) {
                cTRadarStyle = null;
            }
        }
        return cTRadarStyle;
    }

    public void setRadarStyle(CTRadarStyle cTRadarStyle) {
        generatedSetterHelperImpl(cTRadarStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRadarStyle addNewRadarStyle() {
        CTRadarStyle cTRadarStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarStyle = (CTRadarStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRadarStyle;
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

    public List<CTRadarSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRadarChartImpl$$ExternalSyntheticLambda5(this), new CTRadarChartImpl$$ExternalSyntheticLambda6(this), new CTRadarChartImpl$$ExternalSyntheticLambda7(this), new CTRadarChartImpl$$ExternalSyntheticLambda8(this), new CTRadarChartImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTRadarSer[] getSerArray() {
        return (CTRadarSer[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTRadarSer[0]);
    }

    public CTRadarSer getSerArray(int i) {
        CTRadarSer cTRadarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarSer = (CTRadarSer) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTRadarSer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRadarSer;
    }

    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSerArray(CTRadarSer[] cTRadarSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRadarSerArr, PROPERTY_QNAME[2]);
    }

    public void setSerArray(int i, CTRadarSer cTRadarSer) {
        generatedSetterHelperImpl(cTRadarSer, PROPERTY_QNAME[2], i, 2);
    }

    public CTRadarSer insertNewSer(int i) {
        CTRadarSer cTRadarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarSer = (CTRadarSer) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTRadarSer;
    }

    public CTRadarSer addNewSer() {
        CTRadarSer cTRadarSer;
        synchronized (monitor()) {
            check_orphaned();
            cTRadarSer = (CTRadarSer) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTRadarSer;
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

    public List<CTUnsignedInt> getAxIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRadarChartImpl$$ExternalSyntheticLambda0(this), new CTRadarChartImpl$$ExternalSyntheticLambda1(this), new CTRadarChartImpl$$ExternalSyntheticLambda2(this), new CTRadarChartImpl$$ExternalSyntheticLambda3(this), new CTRadarChartImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedInt[] getAxIdArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTUnsignedInt[0]);
    }

    public CTUnsignedInt getAxIdArray(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[4], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedIntArr, PROPERTY_QNAME[4]);
    }

    public void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[4], i, 2);
    }

    public CTUnsignedInt insertNewAxId(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTUnsignedInt;
    }

    public void removeAxId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
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
