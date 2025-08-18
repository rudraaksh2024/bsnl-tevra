package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtension;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;

public class CTExtensionListImpl extends XmlComplexContentImpl implements CTExtensionList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "ext")};
    private static final long serialVersionUID = 1;

    public CTExtensionListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTExtension> getExtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTExtensionListImpl$$ExternalSyntheticLambda0(this), new CTExtensionListImpl$$ExternalSyntheticLambda1(this), new CTExtensionListImpl$$ExternalSyntheticLambda2(this), new CTExtensionListImpl$$ExternalSyntheticLambda3(this), new CTExtensionListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTExtension[] getExtArray() {
        return (CTExtension[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExtension[0]);
    }

    public CTExtension getExtArray(int i) {
        CTExtension cTExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTExtension = (CTExtension) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTExtension == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTExtension;
    }

    public int sizeOfExtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setExtArray(CTExtension[] cTExtensionArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTExtensionArr, PROPERTY_QNAME[0]);
    }

    public void setExtArray(int i, CTExtension cTExtension) {
        generatedSetterHelperImpl(cTExtension, PROPERTY_QNAME[0], i, 2);
    }

    public CTExtension insertNewExt(int i) {
        CTExtension cTExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTExtension = (CTExtension) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTExtension;
    }

    public CTExtension addNewExt() {
        CTExtension cTExtension;
        synchronized (monitor()) {
            check_orphaned();
            cTExtension = (CTExtension) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExtension;
    }

    public void removeExt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
