package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperty;

public class CTCustomPropertiesImpl extends XmlComplexContentImpl implements CTCustomProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "customPr")};
    private static final long serialVersionUID = 1;

    public CTCustomPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCustomProperty> getCustomPrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustomPropertiesImpl$$ExternalSyntheticLambda0(this), new CTCustomPropertiesImpl$$ExternalSyntheticLambda1(this), new CTCustomPropertiesImpl$$ExternalSyntheticLambda2(this), new CTCustomPropertiesImpl$$ExternalSyntheticLambda3(this), new CTCustomPropertiesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCustomProperty[] getCustomPrArray() {
        return (CTCustomProperty[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCustomProperty[0]);
    }

    public CTCustomProperty getCustomPrArray(int i) {
        CTCustomProperty cTCustomProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomProperty = (CTCustomProperty) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCustomProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCustomProperty;
    }

    public int sizeOfCustomPrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCustomPrArray(CTCustomProperty[] cTCustomPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomPropertyArr, PROPERTY_QNAME[0]);
    }

    public void setCustomPrArray(int i, CTCustomProperty cTCustomProperty) {
        generatedSetterHelperImpl(cTCustomProperty, PROPERTY_QNAME[0], i, 2);
    }

    public CTCustomProperty insertNewCustomPr(int i) {
        CTCustomProperty cTCustomProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomProperty = (CTCustomProperty) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCustomProperty;
    }

    public CTCustomProperty addNewCustomPr() {
        CTCustomProperty cTCustomProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomProperty = (CTCustomProperty) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCustomProperty;
    }

    public void removeCustomPr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
