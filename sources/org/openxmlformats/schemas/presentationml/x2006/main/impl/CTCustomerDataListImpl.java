package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTagsData;

public class CTCustomerDataListImpl extends XmlComplexContentImpl implements CTCustomerDataList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custData"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "tags")};
    private static final long serialVersionUID = 1;

    public CTCustomerDataListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCustomerData> getCustDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustomerDataListImpl$$ExternalSyntheticLambda0(this), new CTCustomerDataListImpl$$ExternalSyntheticLambda1(this), new CTCustomerDataListImpl$$ExternalSyntheticLambda2(this), new CTCustomerDataListImpl$$ExternalSyntheticLambda3(this), new CTCustomerDataListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCustomerData[] getCustDataArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCustomerData[0]);
    }

    public CTCustomerData getCustDataArray(int i) {
        CTCustomerData find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCustDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCustDataArray(CTCustomerData[] cTCustomerDataArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomerDataArr, PROPERTY_QNAME[0]);
    }

    public void setCustDataArray(int i, CTCustomerData cTCustomerData) {
        generatedSetterHelperImpl(cTCustomerData, PROPERTY_QNAME[0], i, 2);
    }

    public CTCustomerData insertNewCustData(int i) {
        CTCustomerData insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CTCustomerData addNewCustData() {
        CTCustomerData add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeCustData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public CTTagsData getTags() {
        CTTagsData cTTagsData;
        synchronized (monitor()) {
            check_orphaned();
            cTTagsData = (CTTagsData) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTagsData == null) {
                cTTagsData = null;
            }
        }
        return cTTagsData;
    }

    public boolean isSetTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setTags(CTTagsData cTTagsData) {
        generatedSetterHelperImpl(cTTagsData, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTagsData addNewTags() {
        CTTagsData cTTagsData;
        synchronized (monitor()) {
            check_orphaned();
            cTTagsData = (CTTagsData) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTagsData;
    }

    public void unsetTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
