package org.etsi.uri.x01903.v14;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.RevocationValuesType;

public interface ValidationDataType extends XmlObject {
    public static final DocumentFactory<ValidationDataType> Factory;
    public static final SchemaType type;

    CertificateValuesType addNewCertificateValues();

    RevocationValuesType addNewRevocationValues();

    CertificateValuesType getCertificateValues();

    String getId();

    RevocationValuesType getRevocationValues();

    String getURI();

    boolean isSetCertificateValues();

    boolean isSetId();

    boolean isSetRevocationValues();

    boolean isSetURI();

    void setCertificateValues(CertificateValuesType certificateValuesType);

    void setId(String str);

    void setRevocationValues(RevocationValuesType revocationValuesType);

    void setURI(String str);

    void unsetCertificateValues();

    void unsetId();

    void unsetRevocationValues();

    void unsetURI();

    XmlID xgetId();

    XmlAnyURI xgetURI();

    void xsetId(XmlID xmlID);

    void xsetURI(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<ValidationDataType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "validationdatatype2c11type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
