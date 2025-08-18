package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RealGroup;

public class RealGroupImpl extends GroupImpl implements RealGroup {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence")};
    private static final long serialVersionUID = 1;

    public RealGroupImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<All> getAllList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RealGroupImpl$$ExternalSyntheticLambda0(this), new RealGroupImpl$$ExternalSyntheticLambda6(this), new RealGroupImpl$$ExternalSyntheticLambda7(this), new RealGroupImpl$$ExternalSyntheticLambda8(this), new RealGroupImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public All[] getAllArray() {
        return (All[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new All[0]);
    }

    public All getAllArray(int i) {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (all == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return all;
    }

    public int sizeOfAllArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setAllArray(All[] allArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) allArr, PROPERTY_QNAME[0]);
    }

    public void setAllArray(int i, All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[0], i, 2);
    }

    public All insertNewAll(int i) {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return all;
    }

    public All addNewAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            all = (All) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return all;
    }

    public void removeAll(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<ExplicitGroup> getChoiceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RealGroupImpl$$ExternalSyntheticLambda1(this), new RealGroupImpl$$ExternalSyntheticLambda2(this), new RealGroupImpl$$ExternalSyntheticLambda3(this), new RealGroupImpl$$ExternalSyntheticLambda4(this), new RealGroupImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public ExplicitGroup[] getChoiceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new ExplicitGroup[0]);
    }

    public ExplicitGroup getChoiceArray(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (explicitGroup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return explicitGroup;
    }

    public int sizeOfChoiceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setChoiceArray(ExplicitGroup[] explicitGroupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) explicitGroupArr, PROPERTY_QNAME[1]);
    }

    public void setChoiceArray(int i, ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[1], i, 2);
    }

    public ExplicitGroup insertNewChoice(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return explicitGroup;
    }

    public ExplicitGroup addNewChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return explicitGroup;
    }

    public void removeChoice(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<ExplicitGroup> getSequenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new RealGroupImpl$$ExternalSyntheticLambda10(this), new RealGroupImpl$$ExternalSyntheticLambda11(this), new RealGroupImpl$$ExternalSyntheticLambda12(this), new RealGroupImpl$$ExternalSyntheticLambda13(this), new RealGroupImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public ExplicitGroup[] getSequenceArray() {
        return (ExplicitGroup[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new ExplicitGroup[0]);
    }

    public ExplicitGroup getSequenceArray(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (explicitGroup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return explicitGroup;
    }

    public int sizeOfSequenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSequenceArray(ExplicitGroup[] explicitGroupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) explicitGroupArr, PROPERTY_QNAME[2]);
    }

    public void setSequenceArray(int i, ExplicitGroup explicitGroup) {
        generatedSetterHelperImpl(explicitGroup, PROPERTY_QNAME[2], i, 2);
    }

    public ExplicitGroup insertNewSequence(int i) {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return explicitGroup;
    }

    public ExplicitGroup addNewSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            explicitGroup = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return explicitGroup;
    }

    public void removeSequence(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
