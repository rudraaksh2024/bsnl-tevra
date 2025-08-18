package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;

public class ClaimedRolesListTypeImpl extends XmlComplexContentImpl implements ClaimedRolesListType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "ClaimedRole")};
    private static final long serialVersionUID = 1;

    public ClaimedRolesListTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<AnyType> getClaimedRoleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new ClaimedRolesListTypeImpl$$ExternalSyntheticLambda0(this), new ClaimedRolesListTypeImpl$$ExternalSyntheticLambda1(this), new ClaimedRolesListTypeImpl$$ExternalSyntheticLambda2(this), new ClaimedRolesListTypeImpl$$ExternalSyntheticLambda3(this), new ClaimedRolesListTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public AnyType[] getClaimedRoleArray() {
        return (AnyType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new AnyType[0]);
    }

    public AnyType getClaimedRoleArray(int i) {
        AnyType anyType;
        synchronized (monitor()) {
            check_orphaned();
            anyType = (AnyType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (anyType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return anyType;
    }

    public int sizeOfClaimedRoleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setClaimedRoleArray(AnyType[] anyTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) anyTypeArr, PROPERTY_QNAME[0]);
    }

    public void setClaimedRoleArray(int i, AnyType anyType) {
        generatedSetterHelperImpl(anyType, PROPERTY_QNAME[0], i, 2);
    }

    public AnyType insertNewClaimedRole(int i) {
        AnyType anyType;
        synchronized (monitor()) {
            check_orphaned();
            anyType = (AnyType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return anyType;
    }

    public AnyType addNewClaimedRole() {
        AnyType anyType;
        synchronized (monitor()) {
            check_orphaned();
            anyType = (AnyType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return anyType;
    }

    public void removeClaimedRole(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
