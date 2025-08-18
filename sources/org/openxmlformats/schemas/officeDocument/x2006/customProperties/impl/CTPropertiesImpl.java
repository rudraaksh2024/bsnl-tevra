package org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

public class CTPropertiesImpl extends XmlComplexContentImpl implements CTProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/custom-properties", "property")};
    private static final long serialVersionUID = 1;

    public CTPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTProperty> getPropertyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPropertiesImpl$$ExternalSyntheticLambda0(this), new CTPropertiesImpl$$ExternalSyntheticLambda1(this), new CTPropertiesImpl$$ExternalSyntheticLambda2(this), new CTPropertiesImpl$$ExternalSyntheticLambda3(this), new CTPropertiesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTProperty[] getPropertyArray() {
        return (CTProperty[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTProperty[0]);
    }

    public CTProperty getPropertyArray(int i) {
        CTProperty cTProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTProperty = (CTProperty) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTProperty;
    }

    public int sizeOfPropertyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setPropertyArray(CTProperty[] cTPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPropertyArr, PROPERTY_QNAME[0]);
    }

    public void setPropertyArray(int i, CTProperty cTProperty) {
        generatedSetterHelperImpl(cTProperty, PROPERTY_QNAME[0], i, 2);
    }

    public CTProperty insertNewProperty(int i) {
        CTProperty cTProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTProperty = (CTProperty) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTProperty;
    }

    public CTProperty addNewProperty() {
        CTProperty cTProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTProperty = (CTProperty) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTProperty;
    }

    public void removeProperty(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
