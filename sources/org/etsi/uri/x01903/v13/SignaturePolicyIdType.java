package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.w3.x2000.x09.xmldsig.TransformsType;

public interface SignaturePolicyIdType extends XmlObject {
    public static final DocumentFactory<SignaturePolicyIdType> Factory;
    public static final SchemaType type;

    DigestAlgAndValueType addNewSigPolicyHash();

    ObjectIdentifierType addNewSigPolicyId();

    SigPolicyQualifiersListType addNewSigPolicyQualifiers();

    TransformsType addNewTransforms();

    DigestAlgAndValueType getSigPolicyHash();

    ObjectIdentifierType getSigPolicyId();

    SigPolicyQualifiersListType getSigPolicyQualifiers();

    TransformsType getTransforms();

    boolean isSetSigPolicyQualifiers();

    boolean isSetTransforms();

    void setSigPolicyHash(DigestAlgAndValueType digestAlgAndValueType);

    void setSigPolicyId(ObjectIdentifierType objectIdentifierType);

    void setSigPolicyQualifiers(SigPolicyQualifiersListType sigPolicyQualifiersListType);

    void setTransforms(TransformsType transformsType);

    void unsetSigPolicyQualifiers();

    void unsetTransforms();

    static {
        DocumentFactory<SignaturePolicyIdType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signaturepolicyidtype0ca1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
