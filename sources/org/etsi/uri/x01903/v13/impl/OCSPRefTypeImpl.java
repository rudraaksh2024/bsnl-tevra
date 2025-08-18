package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;

public class OCSPRefTypeImpl extends XmlComplexContentImpl implements OCSPRefType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "OCSPIdentifier"), new QName(SignatureFacet.XADES_132_NS, "DigestAlgAndValue")};
    private static final long serialVersionUID = 1;

    public OCSPRefTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public OCSPIdentifierType getOCSPIdentifier() {
        OCSPIdentifierType oCSPIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPIdentifierType = (OCSPIdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (oCSPIdentifierType == null) {
                oCSPIdentifierType = null;
            }
        }
        return oCSPIdentifierType;
    }

    public void setOCSPIdentifier(OCSPIdentifierType oCSPIdentifierType) {
        generatedSetterHelperImpl(oCSPIdentifierType, PROPERTY_QNAME[0], 0, 1);
    }

    public OCSPIdentifierType addNewOCSPIdentifier() {
        OCSPIdentifierType oCSPIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPIdentifierType = (OCSPIdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return oCSPIdentifierType;
    }

    public DigestAlgAndValueType getDigestAlgAndValue() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (digestAlgAndValueType == null) {
                digestAlgAndValueType = null;
            }
        }
        return digestAlgAndValueType;
    }

    public boolean isSetDigestAlgAndValue() {
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

    public void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType) {
        generatedSetterHelperImpl(digestAlgAndValueType, PROPERTY_QNAME[1], 0, 1);
    }

    public DigestAlgAndValueType addNewDigestAlgAndValue() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestAlgAndValueType = (DigestAlgAndValueType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return digestAlgAndValueType;
    }

    public void unsetDigestAlgAndValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
