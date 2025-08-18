package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType;

public class CommitmentTypeQualifiersListTypeImpl extends XmlComplexContentImpl implements CommitmentTypeQualifiersListType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeQualifier")};
    private static final long serialVersionUID = 1;

    public CommitmentTypeQualifiersListTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<AnyType> getCommitmentTypeQualifierList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CommitmentTypeQualifiersListTypeImpl$$ExternalSyntheticLambda0(this), new CommitmentTypeQualifiersListTypeImpl$$ExternalSyntheticLambda1(this), new CommitmentTypeQualifiersListTypeImpl$$ExternalSyntheticLambda2(this), new CommitmentTypeQualifiersListTypeImpl$$ExternalSyntheticLambda3(this), new CommitmentTypeQualifiersListTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public AnyType[] getCommitmentTypeQualifierArray() {
        return (AnyType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new AnyType[0]);
    }

    public AnyType getCommitmentTypeQualifierArray(int i) {
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

    public int sizeOfCommitmentTypeQualifierArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCommitmentTypeQualifierArray(AnyType[] anyTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) anyTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCommitmentTypeQualifierArray(int i, AnyType anyType) {
        generatedSetterHelperImpl(anyType, PROPERTY_QNAME[0], i, 2);
    }

    public AnyType insertNewCommitmentTypeQualifier(int i) {
        AnyType anyType;
        synchronized (monitor()) {
            check_orphaned();
            anyType = (AnyType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return anyType;
    }

    public AnyType addNewCommitmentTypeQualifier() {
        AnyType anyType;
        synchronized (monitor()) {
            check_orphaned();
            anyType = (AnyType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return anyType;
    }

    public void removeCommitmentTypeQualifier(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
