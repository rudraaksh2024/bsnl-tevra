package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CertificateValuesType extends XmlObject {
    public static final DocumentFactory<CertificateValuesType> Factory;
    public static final SchemaType type;

    EncapsulatedPKIDataType addNewEncapsulatedX509Certificate();

    AnyType addNewOtherCertificate();

    EncapsulatedPKIDataType getEncapsulatedX509CertificateArray(int i);

    EncapsulatedPKIDataType[] getEncapsulatedX509CertificateArray();

    List<EncapsulatedPKIDataType> getEncapsulatedX509CertificateList();

    String getId();

    AnyType getOtherCertificateArray(int i);

    AnyType[] getOtherCertificateArray();

    List<AnyType> getOtherCertificateList();

    EncapsulatedPKIDataType insertNewEncapsulatedX509Certificate(int i);

    AnyType insertNewOtherCertificate(int i);

    boolean isSetId();

    void removeEncapsulatedX509Certificate(int i);

    void removeOtherCertificate(int i);

    void setEncapsulatedX509CertificateArray(int i, EncapsulatedPKIDataType encapsulatedPKIDataType);

    void setEncapsulatedX509CertificateArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArr);

    void setId(String str);

    void setOtherCertificateArray(int i, AnyType anyType);

    void setOtherCertificateArray(AnyType[] anyTypeArr);

    int sizeOfEncapsulatedX509CertificateArray();

    int sizeOfOtherCertificateArray();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<CertificateValuesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "certificatevaluestype5c75type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
