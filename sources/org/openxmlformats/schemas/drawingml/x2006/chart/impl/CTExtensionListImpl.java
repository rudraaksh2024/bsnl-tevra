package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtension;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;

public class CTExtensionListImpl extends XmlComplexContentImpl implements CTExtensionList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "ext")};
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
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExtension[0]);
    }

    public CTExtension getExtArray(int i) {
        CTExtension find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
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
        CTExtension insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CTExtension addNewExt() {
        CTExtension add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeExt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
