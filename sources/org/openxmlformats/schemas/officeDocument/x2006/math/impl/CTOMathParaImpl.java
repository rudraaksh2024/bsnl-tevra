package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathParaPr;

public class CTOMathParaImpl extends XmlComplexContentImpl implements CTOMathPara {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathParaPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath")};
    private static final long serialVersionUID = 1;

    public CTOMathParaImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTOMathParaPr getOMathParaPr() {
        CTOMathParaPr find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetOMathParaPr() {
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

    public void setOMathParaPr(CTOMathParaPr cTOMathParaPr) {
        generatedSetterHelperImpl(cTOMathParaPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTOMathParaPr addNewOMathParaPr() {
        CTOMathParaPr add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetOMathParaPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTOMathParaImpl$$ExternalSyntheticLambda0(this), new CTOMathParaImpl$$ExternalSyntheticLambda1(this), new CTOMathParaImpl$$ExternalSyntheticLambda2(this), new CTOMathParaImpl$$ExternalSyntheticLambda3(this), new CTOMathParaImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTOMath[0]);
    }

    public CTOMath getOMathArray(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTOMath == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOMath;
    }

    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setOMathArray(CTOMath[] cTOMathArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathArr, PROPERTY_QNAME[1]);
    }

    public void setOMathArray(int i, CTOMath cTOMath) {
        generatedSetterHelperImpl(cTOMath, PROPERTY_QNAME[1], i, 2);
    }

    public CTOMath insertNewOMath(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTOMath;
    }

    public CTOMath addNewOMath() {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOMath;
    }

    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
