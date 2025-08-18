package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList;

public class CTTLTimeConditionListImpl extends XmlComplexContentImpl implements CTTLTimeConditionList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cond")};
    private static final long serialVersionUID = 1;

    public CTTLTimeConditionListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTLTimeCondition> getCondList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTLTimeConditionListImpl$$ExternalSyntheticLambda0(this), new CTTLTimeConditionListImpl$$ExternalSyntheticLambda1(this), new CTTLTimeConditionListImpl$$ExternalSyntheticLambda2(this), new CTTLTimeConditionListImpl$$ExternalSyntheticLambda3(this), new CTTLTimeConditionListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTLTimeCondition[] getCondArray() {
        return (CTTLTimeCondition[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTLTimeCondition[0]);
    }

    public CTTLTimeCondition getCondArray(int i) {
        CTTLTimeCondition cTTLTimeCondition;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeCondition = (CTTLTimeCondition) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTLTimeCondition == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTLTimeCondition;
    }

    public int sizeOfCondArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCondArray(CTTLTimeCondition[] cTTLTimeConditionArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLTimeConditionArr, PROPERTY_QNAME[0]);
    }

    public void setCondArray(int i, CTTLTimeCondition cTTLTimeCondition) {
        generatedSetterHelperImpl(cTTLTimeCondition, PROPERTY_QNAME[0], i, 2);
    }

    public CTTLTimeCondition insertNewCond(int i) {
        CTTLTimeCondition cTTLTimeCondition;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeCondition = (CTTLTimeCondition) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTLTimeCondition;
    }

    public CTTLTimeCondition addNewCond() {
        CTTLTimeCondition cTTLTimeCondition;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeCondition = (CTTLTimeCondition) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTLTimeCondition;
    }

    public void removeCond(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
