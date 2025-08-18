package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class CTAdjustHandleListImpl extends XmlComplexContentImpl implements CTAdjustHandleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ahXY"), new QName(XSSFRelation.NS_DRAWINGML, "ahPolar")};
    private static final long serialVersionUID = 1;

    public CTAdjustHandleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTXYAdjustHandle> getAhXYList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTAdjustHandleListImpl$$ExternalSyntheticLambda0(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda1(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda2(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda3(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTXYAdjustHandle[] getAhXYArray() {
        return (CTXYAdjustHandle[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTXYAdjustHandle[0]);
    }

    public CTXYAdjustHandle getAhXYArray(int i) {
        CTXYAdjustHandle cTXYAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTXYAdjustHandle = (CTXYAdjustHandle) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTXYAdjustHandle == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTXYAdjustHandle;
    }

    public int sizeOfAhXYArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setAhXYArray(CTXYAdjustHandle[] cTXYAdjustHandleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTXYAdjustHandleArr, PROPERTY_QNAME[0]);
    }

    public void setAhXYArray(int i, CTXYAdjustHandle cTXYAdjustHandle) {
        generatedSetterHelperImpl(cTXYAdjustHandle, PROPERTY_QNAME[0], i, 2);
    }

    public CTXYAdjustHandle insertNewAhXY(int i) {
        CTXYAdjustHandle cTXYAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTXYAdjustHandle = (CTXYAdjustHandle) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTXYAdjustHandle;
    }

    public CTXYAdjustHandle addNewAhXY() {
        CTXYAdjustHandle cTXYAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTXYAdjustHandle = (CTXYAdjustHandle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTXYAdjustHandle;
    }

    public void removeAhXY(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTPolarAdjustHandle> getAhPolarList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTAdjustHandleListImpl$$ExternalSyntheticLambda5(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda6(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda7(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda8(this), new CTAdjustHandleListImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTPolarAdjustHandle[] getAhPolarArray() {
        return (CTPolarAdjustHandle[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTPolarAdjustHandle[0]);
    }

    public CTPolarAdjustHandle getAhPolarArray(int i) {
        CTPolarAdjustHandle cTPolarAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTPolarAdjustHandle = (CTPolarAdjustHandle) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTPolarAdjustHandle == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPolarAdjustHandle;
    }

    public int sizeOfAhPolarArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setAhPolarArray(CTPolarAdjustHandle[] cTPolarAdjustHandleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPolarAdjustHandleArr, PROPERTY_QNAME[1]);
    }

    public void setAhPolarArray(int i, CTPolarAdjustHandle cTPolarAdjustHandle) {
        generatedSetterHelperImpl(cTPolarAdjustHandle, PROPERTY_QNAME[1], i, 2);
    }

    public CTPolarAdjustHandle insertNewAhPolar(int i) {
        CTPolarAdjustHandle cTPolarAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTPolarAdjustHandle = (CTPolarAdjustHandle) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTPolarAdjustHandle;
    }

    public CTPolarAdjustHandle addNewAhPolar() {
        CTPolarAdjustHandle cTPolarAdjustHandle;
        synchronized (monitor()) {
            check_orphaned();
            cTPolarAdjustHandle = (CTPolarAdjustHandle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPolarAdjustHandle;
    }

    public void removeAhPolar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
