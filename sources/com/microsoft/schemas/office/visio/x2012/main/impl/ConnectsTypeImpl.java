package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ConnectsTypeImpl extends XmlComplexContentImpl implements ConnectsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connect")};
    private static final long serialVersionUID = 1;

    public ConnectsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<ConnectType> getConnectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new ConnectsTypeImpl$$ExternalSyntheticLambda0(this), new ConnectsTypeImpl$$ExternalSyntheticLambda1(this), new ConnectsTypeImpl$$ExternalSyntheticLambda2(this), new ConnectsTypeImpl$$ExternalSyntheticLambda3(this), new ConnectsTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public ConnectType[] getConnectArray() {
        return (ConnectType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new ConnectType[0]);
    }

    public ConnectType getConnectArray(int i) {
        ConnectType connectType;
        synchronized (monitor()) {
            check_orphaned();
            connectType = (ConnectType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (connectType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return connectType;
    }

    public int sizeOfConnectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setConnectArray(ConnectType[] connectTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) connectTypeArr, PROPERTY_QNAME[0]);
    }

    public void setConnectArray(int i, ConnectType connectType) {
        generatedSetterHelperImpl(connectType, PROPERTY_QNAME[0], i, 2);
    }

    public ConnectType insertNewConnect(int i) {
        ConnectType connectType;
        synchronized (monitor()) {
            check_orphaned();
            connectType = (ConnectType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return connectType;
    }

    public ConnectType addNewConnect() {
        ConnectType connectType;
        synchronized (monitor()) {
            check_orphaned();
            connectType = (ConnectType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return connectType;
    }

    public void removeConnect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
