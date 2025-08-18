package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;

public class SignaturePolicyIdentifierTypeImpl extends XmlComplexContentImpl implements SignaturePolicyIdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SignaturePolicyId"), new QName(SignatureFacet.XADES_132_NS, "SignaturePolicyImplied")};
    private static final long serialVersionUID = 1;

    public SignaturePolicyIdentifierTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public SignaturePolicyIdType getSignaturePolicyId() {
        SignaturePolicyIdType signaturePolicyIdType;
        synchronized (monitor()) {
            check_orphaned();
            signaturePolicyIdType = (SignaturePolicyIdType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (signaturePolicyIdType == null) {
                signaturePolicyIdType = null;
            }
        }
        return signaturePolicyIdType;
    }

    public boolean isSetSignaturePolicyId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setSignaturePolicyId(SignaturePolicyIdType signaturePolicyIdType) {
        generatedSetterHelperImpl(signaturePolicyIdType, PROPERTY_QNAME[0], 0, 1);
    }

    public SignaturePolicyIdType addNewSignaturePolicyId() {
        SignaturePolicyIdType signaturePolicyIdType;
        synchronized (monitor()) {
            check_orphaned();
            signaturePolicyIdType = (SignaturePolicyIdType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return signaturePolicyIdType;
    }

    public void unsetSignaturePolicyId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public XmlObject getSignaturePolicyImplied() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (xmlObject == null) {
                xmlObject = null;
            }
        }
        return xmlObject;
    }

    public boolean isSetSignaturePolicyImplied() {
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

    public void setSignaturePolicyImplied(XmlObject xmlObject) {
        generatedSetterHelperImpl(xmlObject, PROPERTY_QNAME[1], 0, 1);
    }

    public XmlObject addNewSignaturePolicyImplied() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return xmlObject;
    }

    public void unsetSignaturePolicyImplied() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
