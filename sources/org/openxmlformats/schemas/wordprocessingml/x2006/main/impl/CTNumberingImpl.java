package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;

public class CTNumberingImpl extends XmlComplexContentImpl implements CTNumbering {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numPicBullet"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "abstractNum"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "num"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numIdMacAtCleanup")};
    private static final long serialVersionUID = 1;

    public CTNumberingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTNumPicBullet> getNumPicBulletList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTNumberingImpl$$ExternalSyntheticLambda1(this), new CTNumberingImpl$$ExternalSyntheticLambda2(this), new CTNumberingImpl$$ExternalSyntheticLambda3(this), new CTNumberingImpl$$ExternalSyntheticLambda4(this), new CTNumberingImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTNumPicBullet[] getNumPicBulletArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTNumPicBullet[0]);
    }

    public CTNumPicBullet getNumPicBulletArray(int i) {
        CTNumPicBullet find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfNumPicBulletArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setNumPicBulletArray(CTNumPicBullet[] cTNumPicBulletArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTNumPicBulletArr, PROPERTY_QNAME[0]);
    }

    public void setNumPicBulletArray(int i, CTNumPicBullet cTNumPicBullet) {
        generatedSetterHelperImpl(cTNumPicBullet, PROPERTY_QNAME[0], i, 2);
    }

    public CTNumPicBullet insertNewNumPicBullet(int i) {
        CTNumPicBullet insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CTNumPicBullet addNewNumPicBullet() {
        CTNumPicBullet add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeNumPicBullet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTAbstractNum> getAbstractNumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTNumberingImpl$$ExternalSyntheticLambda0(this), new CTNumberingImpl$$ExternalSyntheticLambda6(this), new CTNumberingImpl$$ExternalSyntheticLambda7(this), new CTNumberingImpl$$ExternalSyntheticLambda8(this), new CTNumberingImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTAbstractNum[] getAbstractNumArray() {
        return (CTAbstractNum[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTAbstractNum[0]);
    }

    public CTAbstractNum getAbstractNumArray(int i) {
        CTAbstractNum cTAbstractNum;
        synchronized (monitor()) {
            check_orphaned();
            cTAbstractNum = (CTAbstractNum) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTAbstractNum == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAbstractNum;
    }

    public int sizeOfAbstractNumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setAbstractNumArray(CTAbstractNum[] cTAbstractNumArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAbstractNumArr, PROPERTY_QNAME[1]);
    }

    public void setAbstractNumArray(int i, CTAbstractNum cTAbstractNum) {
        generatedSetterHelperImpl(cTAbstractNum, PROPERTY_QNAME[1], i, 2);
    }

    public CTAbstractNum insertNewAbstractNum(int i) {
        CTAbstractNum cTAbstractNum;
        synchronized (monitor()) {
            check_orphaned();
            cTAbstractNum = (CTAbstractNum) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTAbstractNum;
    }

    public CTAbstractNum addNewAbstractNum() {
        CTAbstractNum cTAbstractNum;
        synchronized (monitor()) {
            check_orphaned();
            cTAbstractNum = (CTAbstractNum) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTAbstractNum;
    }

    public void removeAbstractNum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTNum> getNumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTNumberingImpl$$ExternalSyntheticLambda10(this), new CTNumberingImpl$$ExternalSyntheticLambda11(this), new CTNumberingImpl$$ExternalSyntheticLambda12(this), new CTNumberingImpl$$ExternalSyntheticLambda13(this), new CTNumberingImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public CTNum[] getNumArray() {
        return (CTNum[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTNum[0]);
    }

    public CTNum getNumArray(int i) {
        CTNum cTNum;
        synchronized (monitor()) {
            check_orphaned();
            cTNum = (CTNum) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTNum == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTNum;
    }

    public int sizeOfNumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setNumArray(CTNum[] cTNumArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTNumArr, PROPERTY_QNAME[2]);
    }

    public void setNumArray(int i, CTNum cTNum) {
        generatedSetterHelperImpl(cTNum, PROPERTY_QNAME[2], i, 2);
    }

    public CTNum insertNewNum(int i) {
        CTNum cTNum;
        synchronized (monitor()) {
            check_orphaned();
            cTNum = (CTNum) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTNum;
    }

    public CTNum addNewNum() {
        CTNum cTNum;
        synchronized (monitor()) {
            check_orphaned();
            cTNum = (CTNum) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTNum;
    }

    public void removeNum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public CTDecimalNumber getNumIdMacAtCleanup() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetNumIdMacAtCleanup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setNumIdMacAtCleanup(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[3], 0, 1);
    }

    public CTDecimalNumber addNewNumIdMacAtCleanup() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDecimalNumber;
    }

    public void unsetNumIdMacAtCleanup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
