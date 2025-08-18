package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;

public class CTGradientStopListImpl extends XmlComplexContentImpl implements CTGradientStopList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gs")};
    private static final long serialVersionUID = 1;

    public CTGradientStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTGradientStop> getGsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGradientStopListImpl$$ExternalSyntheticLambda0(this), new CTGradientStopListImpl$$ExternalSyntheticLambda1(this), new CTGradientStopListImpl$$ExternalSyntheticLambda2(this), new CTGradientStopListImpl$$ExternalSyntheticLambda3(this), new CTGradientStopListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTGradientStop[] getGsArray() {
        return (CTGradientStop[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTGradientStop[0]);
    }

    public CTGradientStop getGsArray(int i) {
        CTGradientStop cTGradientStop;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientStop = (CTGradientStop) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTGradientStop == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGradientStop;
    }

    public int sizeOfGsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setGsArray(CTGradientStop[] cTGradientStopArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGradientStopArr, PROPERTY_QNAME[0]);
    }

    public void setGsArray(int i, CTGradientStop cTGradientStop) {
        generatedSetterHelperImpl(cTGradientStop, PROPERTY_QNAME[0], i, 2);
    }

    public CTGradientStop insertNewGs(int i) {
        CTGradientStop cTGradientStop;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientStop = (CTGradientStop) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTGradientStop;
    }

    public CTGradientStop addNewGs() {
        CTGradientStop cTGradientStop;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientStop = (CTGradientStop) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGradientStop;
    }

    public void removeGs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
