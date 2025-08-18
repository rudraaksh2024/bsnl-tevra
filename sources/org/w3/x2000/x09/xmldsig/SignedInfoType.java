package org.w3.x2000.x09.xmldsig;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignedInfoType extends XmlObject {
    public static final DocumentFactory<SignedInfoType> Factory;
    public static final SchemaType type;

    CanonicalizationMethodType addNewCanonicalizationMethod();

    ReferenceType addNewReference();

    SignatureMethodType addNewSignatureMethod();

    CanonicalizationMethodType getCanonicalizationMethod();

    String getId();

    ReferenceType getReferenceArray(int i);

    ReferenceType[] getReferenceArray();

    List<ReferenceType> getReferenceList();

    SignatureMethodType getSignatureMethod();

    ReferenceType insertNewReference(int i);

    boolean isSetId();

    void removeReference(int i);

    void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethodType);

    void setId(String str);

    void setReferenceArray(int i, ReferenceType referenceType);

    void setReferenceArray(ReferenceType[] referenceTypeArr);

    void setSignatureMethod(SignatureMethodType signatureMethodType);

    int sizeOfReferenceArray();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<SignedInfoType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signedinfotype54dbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
