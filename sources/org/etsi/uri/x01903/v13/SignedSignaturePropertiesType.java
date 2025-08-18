package org.etsi.uri.x01903.v13;

import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignedSignaturePropertiesType extends XmlObject {
    public static final DocumentFactory<SignedSignaturePropertiesType> Factory;
    public static final SchemaType type;

    SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier();

    SignatureProductionPlaceType addNewSignatureProductionPlace();

    SignerRoleType addNewSignerRole();

    CertIDListType addNewSigningCertificate();

    String getId();

    SignaturePolicyIdentifierType getSignaturePolicyIdentifier();

    SignatureProductionPlaceType getSignatureProductionPlace();

    SignerRoleType getSignerRole();

    CertIDListType getSigningCertificate();

    Calendar getSigningTime();

    boolean isSetId();

    boolean isSetSignaturePolicyIdentifier();

    boolean isSetSignatureProductionPlace();

    boolean isSetSignerRole();

    boolean isSetSigningCertificate();

    boolean isSetSigningTime();

    void setId(String str);

    void setSignaturePolicyIdentifier(SignaturePolicyIdentifierType signaturePolicyIdentifierType);

    void setSignatureProductionPlace(SignatureProductionPlaceType signatureProductionPlaceType);

    void setSignerRole(SignerRoleType signerRoleType);

    void setSigningCertificate(CertIDListType certIDListType);

    void setSigningTime(Calendar calendar);

    void unsetId();

    void unsetSignaturePolicyIdentifier();

    void unsetSignatureProductionPlace();

    void unsetSignerRole();

    void unsetSigningCertificate();

    void unsetSigningTime();

    XmlID xgetId();

    XmlDateTime xgetSigningTime();

    void xsetId(XmlID xmlID);

    void xsetSigningTime(XmlDateTime xmlDateTime);

    static {
        DocumentFactory<SignedSignaturePropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signedsignaturepropertiestype06abtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
