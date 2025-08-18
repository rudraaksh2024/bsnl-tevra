package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CertIDListType extends XmlObject {
    public static final DocumentFactory<CertIDListType> Factory;
    public static final SchemaType type;

    CertIDType addNewCert();

    CertIDType getCertArray(int i);

    CertIDType[] getCertArray();

    List<CertIDType> getCertList();

    CertIDType insertNewCert(int i);

    void removeCert(int i);

    void setCertArray(int i, CertIDType certIDType);

    void setCertArray(CertIDType[] certIDTypeArr);

    int sizeOfCertArray();

    static {
        DocumentFactory<CertIDListType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "certidlisttype488btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
