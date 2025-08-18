package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertifiedRolesListType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.etsi.uri.x01903.v13.SignerRoleType;

public class SignerRoleTypeImpl extends XmlComplexContentImpl implements SignerRoleType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "ClaimedRoles"), new QName(SignatureFacet.XADES_132_NS, "CertifiedRoles")};
    private static final long serialVersionUID = 1;

    public SignerRoleTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ClaimedRolesListType getClaimedRoles() {
        ClaimedRolesListType claimedRolesListType;
        synchronized (monitor()) {
            check_orphaned();
            claimedRolesListType = (ClaimedRolesListType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (claimedRolesListType == null) {
                claimedRolesListType = null;
            }
        }
        return claimedRolesListType;
    }

    public boolean isSetClaimedRoles() {
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

    public void setClaimedRoles(ClaimedRolesListType claimedRolesListType) {
        generatedSetterHelperImpl(claimedRolesListType, PROPERTY_QNAME[0], 0, 1);
    }

    public ClaimedRolesListType addNewClaimedRoles() {
        ClaimedRolesListType claimedRolesListType;
        synchronized (monitor()) {
            check_orphaned();
            claimedRolesListType = (ClaimedRolesListType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return claimedRolesListType;
    }

    public void unsetClaimedRoles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CertifiedRolesListType getCertifiedRoles() {
        CertifiedRolesListType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCertifiedRoles() {
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

    public void setCertifiedRoles(CertifiedRolesListType certifiedRolesListType) {
        generatedSetterHelperImpl(certifiedRolesListType, PROPERTY_QNAME[1], 0, 1);
    }

    public CertifiedRolesListType addNewCertifiedRoles() {
        CertifiedRolesListType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetCertifiedRoles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
