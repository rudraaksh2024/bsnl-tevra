package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMR;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg;

public class CTMRImpl extends XmlComplexContentImpl implements CTMR {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "e")};
    private static final long serialVersionUID = 1;

    public CTMRImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTOMathArg> getEList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTMRImpl$$ExternalSyntheticLambda0(this), new CTMRImpl$$ExternalSyntheticLambda1(this), new CTMRImpl$$ExternalSyntheticLambda2(this), new CTMRImpl$$ExternalSyntheticLambda3(this), new CTMRImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTOMathArg[] getEArray() {
        return (CTOMathArg[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTOMathArg[0]);
    }

    public CTOMathArg getEArray(int i) {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTOMathArg == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOMathArg;
    }

    public int sizeOfEArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setEArray(CTOMathArg[] cTOMathArgArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathArgArr, PROPERTY_QNAME[0]);
    }

    public void setEArray(int i, CTOMathArg cTOMathArg) {
        generatedSetterHelperImpl(cTOMathArg, PROPERTY_QNAME[0], i, 2);
    }

    public CTOMathArg insertNewE(int i) {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTOMathArg;
    }

    public CTOMathArg addNewE() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTOMathArg;
    }

    public void removeE(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
