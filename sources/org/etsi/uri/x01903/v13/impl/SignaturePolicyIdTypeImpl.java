package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;
import org.etsi.uri.x01903.v13.SigPolicyQualifiersListType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.w3.x2000.x09.xmldsig.TransformsType;

public class SignaturePolicyIdTypeImpl extends XmlComplexContentImpl implements SignaturePolicyIdType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SigPolicyId"), new QName(SignatureFacet.XML_DIGSIG_NS, "Transforms"), new QName(SignatureFacet.XADES_132_NS, "SigPolicyHash"), new QName(SignatureFacet.XADES_132_NS, "SigPolicyQualifiers")};
    private static final long serialVersionUID = 1;

    public SignaturePolicyIdTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ObjectIdentifierType getSigPolicyId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            objectIdentifierType = (ObjectIdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (objectIdentifierType == null) {
                objectIdentifierType = null;
            }
        }
        return objectIdentifierType;
    }

    public void setSigPolicyId(ObjectIdentifierType objectIdentifierType) {
        generatedSetterHelperImpl(objectIdentifierType, PROPERTY_QNAME[0], 0, 1);
    }

    public ObjectIdentifierType addNewSigPolicyId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            objectIdentifierType = (ObjectIdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return objectIdentifierType;
    }

    public TransformsType getTransforms() {
        TransformsType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTransforms() {
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

    public void setTransforms(TransformsType transformsType) {
        generatedSetterHelperImpl(transformsType, PROPERTY_QNAME[1], 0, 1);
    }

    public TransformsType addNewTransforms() {
        TransformsType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetTransforms() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public DigestAlgAndValueType getSigPolicyHash() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (digestAlgAndValueType == null) {
                digestAlgAndValueType = null;
            }
        }
        return digestAlgAndValueType;
    }

    public void setSigPolicyHash(DigestAlgAndValueType digestAlgAndValueType) {
        generatedSetterHelperImpl(digestAlgAndValueType, PROPERTY_QNAME[2], 0, 1);
    }

    public DigestAlgAndValueType addNewSigPolicyHash() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return digestAlgAndValueType;
    }

    public SigPolicyQualifiersListType getSigPolicyQualifiers() {
        SigPolicyQualifiersListType sigPolicyQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            sigPolicyQualifiersListType = (SigPolicyQualifiersListType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (sigPolicyQualifiersListType == null) {
                sigPolicyQualifiersListType = null;
            }
        }
        return sigPolicyQualifiersListType;
    }

    public boolean isSetSigPolicyQualifiers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSigPolicyQualifiers(SigPolicyQualifiersListType sigPolicyQualifiersListType) {
        generatedSetterHelperImpl(sigPolicyQualifiersListType, PROPERTY_QNAME[3], 0, 1);
    }

    public SigPolicyQualifiersListType addNewSigPolicyQualifiers() {
        SigPolicyQualifiersListType sigPolicyQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            sigPolicyQualifiersListType = (SigPolicyQualifiersListType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return sigPolicyQualifiersListType;
    }

    public void unsetSigPolicyQualifiers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
