package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface RevocationValuesType extends XmlObject {
    public static final DocumentFactory<RevocationValuesType> Factory;
    public static final SchemaType type;

    CRLValuesType addNewCRLValues();

    OCSPValuesType addNewOCSPValues();

    OtherCertStatusValuesType addNewOtherValues();

    CRLValuesType getCRLValues();

    String getId();

    OCSPValuesType getOCSPValues();

    OtherCertStatusValuesType getOtherValues();

    boolean isSetCRLValues();

    boolean isSetId();

    boolean isSetOCSPValues();

    boolean isSetOtherValues();

    void setCRLValues(CRLValuesType cRLValuesType);

    void setId(String str);

    void setOCSPValues(OCSPValuesType oCSPValuesType);

    void setOtherValues(OtherCertStatusValuesType otherCertStatusValuesType);

    void unsetCRLValues();

    void unsetId();

    void unsetOCSPValues();

    void unsetOtherValues();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<RevocationValuesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "revocationvaluestype9a6etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
