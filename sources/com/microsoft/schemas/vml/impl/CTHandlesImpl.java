package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTH;
import com.microsoft.schemas.vml.CTHandles;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTHandlesImpl extends XmlComplexContentImpl implements CTHandles {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "h")};
    private static final long serialVersionUID = 1;

    public CTHandlesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTH> getHList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTHandlesImpl$$ExternalSyntheticLambda0(this), new CTHandlesImpl$$ExternalSyntheticLambda1(this), new CTHandlesImpl$$ExternalSyntheticLambda2(this), new CTHandlesImpl$$ExternalSyntheticLambda3(this), new CTHandlesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTH[] getHArray() {
        return (CTH[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTH[0]);
    }

    public CTH getHArray(int i) {
        CTH cth;
        synchronized (monitor()) {
            check_orphaned();
            cth = (CTH) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cth == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cth;
    }

    public int sizeOfHArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setHArray(CTH[] cthArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cthArr, PROPERTY_QNAME[0]);
    }

    public void setHArray(int i, CTH cth) {
        generatedSetterHelperImpl(cth, PROPERTY_QNAME[0], i, 2);
    }

    public CTH insertNewH(int i) {
        CTH cth;
        synchronized (monitor()) {
            check_orphaned();
            cth = (CTH) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cth;
    }

    public CTH addNewH() {
        CTH cth;
        synchronized (monitor()) {
            check_orphaned();
            cth = (CTH) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cth;
    }

    public void removeH(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
