package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTM;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMR;

public class CTMImpl extends XmlComplexContentImpl implements CTM {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mr")};
    private static final long serialVersionUID = 1;

    public CTMImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMPr getMPr() {
        CTMPr cTMPr;
        synchronized (monitor()) {
            check_orphaned();
            cTMPr = (CTMPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTMPr == null) {
                cTMPr = null;
            }
        }
        return cTMPr;
    }

    public boolean isSetMPr() {
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

    public void setMPr(CTMPr cTMPr) {
        generatedSetterHelperImpl(cTMPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMPr addNewMPr() {
        CTMPr cTMPr;
        synchronized (monitor()) {
            check_orphaned();
            cTMPr = (CTMPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTMPr;
    }

    public void unsetMPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTMR> getMrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTMImpl$$ExternalSyntheticLambda0(this), new CTMImpl$$ExternalSyntheticLambda1(this), new CTMImpl$$ExternalSyntheticLambda2(this), new CTMImpl$$ExternalSyntheticLambda3(this), new CTMImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTMR[] getMrArray() {
        return (CTMR[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTMR[0]);
    }

    public CTMR getMrArray(int i) {
        CTMR ctmr;
        synchronized (monitor()) {
            check_orphaned();
            ctmr = (CTMR) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (ctmr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return ctmr;
    }

    public int sizeOfMrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setMrArray(CTMR[] ctmrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ctmrArr, PROPERTY_QNAME[1]);
    }

    public void setMrArray(int i, CTMR ctmr) {
        generatedSetterHelperImpl(ctmr, PROPERTY_QNAME[1], i, 2);
    }

    public CTMR insertNewMr(int i) {
        CTMR ctmr;
        synchronized (monitor()) {
            check_orphaned();
            ctmr = (CTMR) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return ctmr;
    }

    public CTMR addNewMr() {
        CTMR ctmr;
        synchronized (monitor()) {
            check_orphaned();
            ctmr = (CTMR) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return ctmr;
    }

    public void removeMr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
