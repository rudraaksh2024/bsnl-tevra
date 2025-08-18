package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;

public class CRLRefTypeImpl extends XmlComplexContentImpl implements CRLRefType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "DigestAlgAndValue"), new QName(SignatureFacet.XADES_132_NS, "CRLIdentifier")};
    private static final long serialVersionUID = 1;

    public CRLRefTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DigestAlgAndValueType getDigestAlgAndValue() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (digestAlgAndValueType == null) {
                digestAlgAndValueType = null;
            }
        }
        return digestAlgAndValueType;
    }

    public void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType) {
        generatedSetterHelperImpl(digestAlgAndValueType, PROPERTY_QNAME[0], 0, 1);
    }

    public DigestAlgAndValueType addNewDigestAlgAndValue() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return digestAlgAndValueType;
    }

    public CRLIdentifierType getCRLIdentifier() {
        CRLIdentifierType cRLIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            cRLIdentifierType = (CRLIdentifierType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cRLIdentifierType == null) {
                cRLIdentifierType = null;
            }
        }
        return cRLIdentifierType;
    }

    public boolean isSetCRLIdentifier() {
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

    public void setCRLIdentifier(CRLIdentifierType cRLIdentifierType) {
        generatedSetterHelperImpl(cRLIdentifierType, PROPERTY_QNAME[1], 0, 1);
    }

    public CRLIdentifierType addNewCRLIdentifier() {
        CRLIdentifierType cRLIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            cRLIdentifierType = (CRLIdentifierType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cRLIdentifierType;
    }

    public void unsetCRLIdentifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
