package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;

public class CTSdtEndPrImpl extends XmlComplexContentImpl implements CTSdtEndPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr")};
    private static final long serialVersionUID = 1;

    public CTSdtEndPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTRPr> getRPrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSdtEndPrImpl$$ExternalSyntheticLambda0(this), new CTSdtEndPrImpl$$ExternalSyntheticLambda1(this), new CTSdtEndPrImpl$$ExternalSyntheticLambda2(this), new CTSdtEndPrImpl$$ExternalSyntheticLambda3(this), new CTSdtEndPrImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTRPr[] getRPrArray() {
        return (CTRPr[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTRPr[0]);
    }

    public CTRPr getRPrArray(int i) {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTRPr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRPr;
    }

    public int sizeOfRPrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRPrArray(CTRPr[] cTRPrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRPrArr, PROPERTY_QNAME[0]);
    }

    public void setRPrArray(int i, CTRPr cTRPr) {
        generatedSetterHelperImpl(cTRPr, PROPERTY_QNAME[0], i, 2);
    }

    public CTRPr insertNewRPr(int i) {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTRPr;
    }

    public CTRPr addNewRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRPr;
    }

    public void removeRPr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
