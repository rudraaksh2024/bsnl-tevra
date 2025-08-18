package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignaturePolicyIdentifierType extends XmlObject {
    public static final DocumentFactory<SignaturePolicyIdentifierType> Factory;
    public static final SchemaType type;

    SignaturePolicyIdType addNewSignaturePolicyId();

    XmlObject addNewSignaturePolicyImplied();

    SignaturePolicyIdType getSignaturePolicyId();

    XmlObject getSignaturePolicyImplied();

    boolean isSetSignaturePolicyId();

    boolean isSetSignaturePolicyImplied();

    void setSignaturePolicyId(SignaturePolicyIdType signaturePolicyIdType);

    void setSignaturePolicyImplied(XmlObject xmlObject);

    void unsetSignaturePolicyId();

    void unsetSignaturePolicyImplied();

    static {
        DocumentFactory<SignaturePolicyIdentifierType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signaturepolicyidentifiertype80aftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
