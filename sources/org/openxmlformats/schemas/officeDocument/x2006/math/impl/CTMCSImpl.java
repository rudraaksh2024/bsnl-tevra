package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMC;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS;

public class CTMCSImpl extends XmlComplexContentImpl implements CTMCS {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mc")};
    private static final long serialVersionUID = 1;

    public CTMCSImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTMC> getMcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTMCSImpl$$ExternalSyntheticLambda0(this), new CTMCSImpl$$ExternalSyntheticLambda1(this), new CTMCSImpl$$ExternalSyntheticLambda2(this), new CTMCSImpl$$ExternalSyntheticLambda3(this), new CTMCSImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTMC[] getMcArray() {
        return (CTMC[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTMC[0]);
    }

    public CTMC getMcArray(int i) {
        CTMC ctmc;
        synchronized (monitor()) {
            check_orphaned();
            ctmc = (CTMC) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (ctmc == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return ctmc;
    }

    public int sizeOfMcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setMcArray(CTMC[] ctmcArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ctmcArr, PROPERTY_QNAME[0]);
    }

    public void setMcArray(int i, CTMC ctmc) {
        generatedSetterHelperImpl(ctmc, PROPERTY_QNAME[0], i, 2);
    }

    public CTMC insertNewMc(int i) {
        CTMC ctmc;
        synchronized (monitor()) {
            check_orphaned();
            ctmc = (CTMC) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return ctmc;
    }

    public CTMC addNewMc() {
        CTMC ctmc;
        synchronized (monitor()) {
            check_orphaned();
            ctmc = (CTMC) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return ctmc;
    }

    public void removeMc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
