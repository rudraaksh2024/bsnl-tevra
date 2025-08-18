package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAttr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

public class CTCustomXmlPrImpl extends XmlComplexContentImpl implements CTCustomXmlPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "placeholder"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "attr")};
    private static final long serialVersionUID = 1;

    public CTCustomXmlPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTString getPlaceholder() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetPlaceholder() {
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

    public void setPlaceholder(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], 0, 1);
    }

    public CTString addNewPlaceholder() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void unsetPlaceholder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTAttr> getAttrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustomXmlPrImpl$$ExternalSyntheticLambda0(this), new CTCustomXmlPrImpl$$ExternalSyntheticLambda1(this), new CTCustomXmlPrImpl$$ExternalSyntheticLambda2(this), new CTCustomXmlPrImpl$$ExternalSyntheticLambda3(this), new CTCustomXmlPrImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTAttr[] getAttrArray() {
        return (CTAttr[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTAttr[0]);
    }

    public CTAttr getAttrArray(int i) {
        CTAttr cTAttr;
        synchronized (monitor()) {
            check_orphaned();
            cTAttr = (CTAttr) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTAttr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAttr;
    }

    public int sizeOfAttrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setAttrArray(CTAttr[] cTAttrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAttrArr, PROPERTY_QNAME[1]);
    }

    public void setAttrArray(int i, CTAttr cTAttr) {
        generatedSetterHelperImpl(cTAttr, PROPERTY_QNAME[1], i, 2);
    }

    public CTAttr insertNewAttr(int i) {
        CTAttr cTAttr;
        synchronized (monitor()) {
            check_orphaned();
            cTAttr = (CTAttr) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTAttr;
    }

    public CTAttr addNewAttr() {
        CTAttr cTAttr;
        synchronized (monitor()) {
            check_orphaned();
            cTAttr = (CTAttr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTAttr;
    }

    public void removeAttr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
