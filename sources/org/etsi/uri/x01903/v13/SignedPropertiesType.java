package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface SignedPropertiesType extends XmlObject {
    public static final DocumentFactory<SignedPropertiesType> Factory;
    public static final SchemaType type;

    SignedDataObjectPropertiesType addNewSignedDataObjectProperties();

    SignedSignaturePropertiesType addNewSignedSignatureProperties();

    String getId();

    SignedDataObjectPropertiesType getSignedDataObjectProperties();

    SignedSignaturePropertiesType getSignedSignatureProperties();

    boolean isSetId();

    boolean isSetSignedDataObjectProperties();

    boolean isSetSignedSignatureProperties();

    void setId(String str);

    void setSignedDataObjectProperties(SignedDataObjectPropertiesType signedDataObjectPropertiesType);

    void setSignedSignatureProperties(SignedSignaturePropertiesType signedSignaturePropertiesType);

    void unsetId();

    void unsetSignedDataObjectProperties();

    void unsetSignedSignatureProperties();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<SignedPropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signedpropertiestype163dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
