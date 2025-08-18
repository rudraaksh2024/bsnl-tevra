package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MasterShortcutType;
import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MastersTypeImpl extends XmlComplexContentImpl implements MastersType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Master"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "MasterShortcut")};
    private static final long serialVersionUID = 1;

    public MastersTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<MasterType> getMasterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new MastersTypeImpl$$ExternalSyntheticLambda0(this), new MastersTypeImpl$$ExternalSyntheticLambda1(this), new MastersTypeImpl$$ExternalSyntheticLambda2(this), new MastersTypeImpl$$ExternalSyntheticLambda3(this), new MastersTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public MasterType[] getMasterArray() {
        return (MasterType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new MasterType[0]);
    }

    public MasterType getMasterArray(int i) {
        MasterType masterType;
        synchronized (monitor()) {
            check_orphaned();
            masterType = (MasterType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (masterType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return masterType;
    }

    public int sizeOfMasterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setMasterArray(MasterType[] masterTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) masterTypeArr, PROPERTY_QNAME[0]);
    }

    public void setMasterArray(int i, MasterType masterType) {
        generatedSetterHelperImpl(masterType, PROPERTY_QNAME[0], i, 2);
    }

    public MasterType insertNewMaster(int i) {
        MasterType masterType;
        synchronized (monitor()) {
            check_orphaned();
            masterType = (MasterType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return masterType;
    }

    public MasterType addNewMaster() {
        MasterType masterType;
        synchronized (monitor()) {
            check_orphaned();
            masterType = (MasterType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return masterType;
    }

    public void removeMaster(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<MasterShortcutType> getMasterShortcutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new MastersTypeImpl$$ExternalSyntheticLambda5(this), new MastersTypeImpl$$ExternalSyntheticLambda6(this), new MastersTypeImpl$$ExternalSyntheticLambda7(this), new MastersTypeImpl$$ExternalSyntheticLambda8(this), new MastersTypeImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public MasterShortcutType[] getMasterShortcutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new MasterShortcutType[0]);
    }

    public MasterShortcutType getMasterShortcutArray(int i) {
        MasterShortcutType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfMasterShortcutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setMasterShortcutArray(MasterShortcutType[] masterShortcutTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) masterShortcutTypeArr, PROPERTY_QNAME[1]);
    }

    public void setMasterShortcutArray(int i, MasterShortcutType masterShortcutType) {
        generatedSetterHelperImpl(masterShortcutType, PROPERTY_QNAME[1], i, 2);
    }

    public MasterShortcutType insertNewMasterShortcut(int i) {
        MasterShortcutType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return insert_element_user;
    }

    public MasterShortcutType addNewMasterShortcut() {
        MasterShortcutType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void removeMasterShortcut(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
