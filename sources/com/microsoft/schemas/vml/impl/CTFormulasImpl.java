package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTF;
import com.microsoft.schemas.vml.CTFormulas;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTFormulasImpl extends XmlComplexContentImpl implements CTFormulas {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "f")};
    private static final long serialVersionUID = 1;

    public CTFormulasImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTF> getFList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFormulasImpl$$ExternalSyntheticLambda0(this), new CTFormulasImpl$$ExternalSyntheticLambda1(this), new CTFormulasImpl$$ExternalSyntheticLambda2(this), new CTFormulasImpl$$ExternalSyntheticLambda3(this), new CTFormulasImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTF[] getFArray() {
        return (CTF[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTF[0]);
    }

    public CTF getFArray(int i) {
        CTF ctf;
        synchronized (monitor()) {
            check_orphaned();
            ctf = (CTF) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (ctf == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return ctf;
    }

    public int sizeOfFArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setFArray(CTF[] ctfArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ctfArr, PROPERTY_QNAME[0]);
    }

    public void setFArray(int i, CTF ctf) {
        generatedSetterHelperImpl(ctf, PROPERTY_QNAME[0], i, 2);
    }

    public CTF insertNewF(int i) {
        CTF ctf;
        synchronized (monitor()) {
            check_orphaned();
            ctf = (CTF) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return ctf;
    }

    public CTF addNewF() {
        CTF ctf;
        synchronized (monitor()) {
            check_orphaned();
            ctf = (CTF) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return ctf;
    }

    public void removeF(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
