package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;

public class CTDashStopListImpl extends XmlComplexContentImpl implements CTDashStopList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ds")};
    private static final long serialVersionUID = 1;

    public CTDashStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTDashStop> getDsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDashStopListImpl$$ExternalSyntheticLambda0(this), new CTDashStopListImpl$$ExternalSyntheticLambda1(this), new CTDashStopListImpl$$ExternalSyntheticLambda2(this), new CTDashStopListImpl$$ExternalSyntheticLambda3(this), new CTDashStopListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTDashStop[] getDsArray() {
        return (CTDashStop[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTDashStop[0]);
    }

    public CTDashStop getDsArray(int i) {
        CTDashStop cTDashStop;
        synchronized (monitor()) {
            check_orphaned();
            cTDashStop = (CTDashStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTDashStop == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDashStop;
    }

    public int sizeOfDsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setDsArray(CTDashStop[] cTDashStopArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDashStopArr, PROPERTY_QNAME[0]);
    }

    public void setDsArray(int i, CTDashStop cTDashStop) {
        generatedSetterHelperImpl(cTDashStop, PROPERTY_QNAME[0], i, 2);
    }

    public CTDashStop insertNewDs(int i) {
        CTDashStop cTDashStop;
        synchronized (monitor()) {
            check_orphaned();
            cTDashStop = (CTDashStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTDashStop;
    }

    public CTDashStop addNewDs() {
        CTDashStop cTDashStop;
        synchronized (monitor()) {
            check_orphaned();
            cTDashStop = (CTDashStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTDashStop;
    }

    public void removeDs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
