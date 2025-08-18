package org.w3.x2000.x09.xmldsig;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignatureType extends XmlObject {
    public static final DocumentFactory<SignatureType> Factory;
    public static final SchemaType type;

    KeyInfoType addNewKeyInfo();

    ObjectType addNewObject();

    SignatureValueType addNewSignatureValue();

    SignedInfoType addNewSignedInfo();

    String getId();

    KeyInfoType getKeyInfo();

    ObjectType getObjectArray(int i);

    ObjectType[] getObjectArray();

    List<ObjectType> getObjectList();

    SignatureValueType getSignatureValue();

    SignedInfoType getSignedInfo();

    ObjectType insertNewObject(int i);

    boolean isSetId();

    boolean isSetKeyInfo();

    void removeObject(int i);

    void setId(String str);

    void setKeyInfo(KeyInfoType keyInfoType);

    void setObjectArray(int i, ObjectType objectType);

    void setObjectArray(ObjectType[] objectTypeArr);

    void setSignatureValue(SignatureValueType signatureValueType);

    void setSignedInfo(SignedInfoType signedInfoType);

    int sizeOfObjectArray();

    void unsetId();

    void unsetKeyInfo();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<SignatureType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signaturetype0a3ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
