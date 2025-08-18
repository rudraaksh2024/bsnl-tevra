package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignerRoleType extends XmlObject {
    public static final DocumentFactory<SignerRoleType> Factory;
    public static final SchemaType type;

    CertifiedRolesListType addNewCertifiedRoles();

    ClaimedRolesListType addNewClaimedRoles();

    CertifiedRolesListType getCertifiedRoles();

    ClaimedRolesListType getClaimedRoles();

    boolean isSetCertifiedRoles();

    boolean isSetClaimedRoles();

    void setCertifiedRoles(CertifiedRolesListType certifiedRolesListType);

    void setClaimedRoles(ClaimedRolesListType claimedRolesListType);

    void unsetCertifiedRoles();

    void unsetClaimedRoles();

    static {
        DocumentFactory<SignerRoleType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signerroletypef58etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
