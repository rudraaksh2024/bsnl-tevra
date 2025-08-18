package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTStrDataImpl extends XmlComplexContentImpl implements CTStrData {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "ptCount"), new QName(XSSFRelation.NS_CHART, "pt"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTStrDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTUnsignedInt getPtCount() {
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

    public boolean isSetPtCount() {
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

    public void setPtCount(CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[0], 0, 1);
    }

    public CTUnsignedInt addNewPtCount() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTUnsignedInt;
    }

    public void unsetPtCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTStrVal> getPtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTStrDataImpl$$ExternalSyntheticLambda0(this), new CTStrDataImpl$$ExternalSyntheticLambda1(this), new CTStrDataImpl$$ExternalSyntheticLambda2(this), new CTStrDataImpl$$ExternalSyntheticLambda3(this), new CTStrDataImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTStrVal[] getPtArray() {
        return (CTStrVal[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTStrVal[0]);
    }

    public CTStrVal getPtArray(int i) {
        CTStrVal cTStrVal;
        synchronized (monitor()) {
            check_orphaned();
            cTStrVal = (CTStrVal) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTStrVal == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTStrVal;
    }

    public int sizeOfPtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setPtArray(CTStrVal[] cTStrValArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTStrValArr, PROPERTY_QNAME[1]);
    }

    public void setPtArray(int i, CTStrVal cTStrVal) {
        generatedSetterHelperImpl(cTStrVal, PROPERTY_QNAME[1], i, 2);
    }

    public CTStrVal insertNewPt(int i) {
        CTStrVal cTStrVal;
        synchronized (monitor()) {
            check_orphaned();
            cTStrVal = (CTStrVal) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTStrVal;
    }

    public CTStrVal addNewPt() {
        CTStrVal cTStrVal;
        synchronized (monitor()) {
            check_orphaned();
            cTStrVal = (CTStrVal) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTStrVal;
    }

    public void removePt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[2], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
