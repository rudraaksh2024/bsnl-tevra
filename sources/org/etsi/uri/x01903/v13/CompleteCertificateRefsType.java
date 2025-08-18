package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CompleteCertificateRefsType extends XmlObject {
    public static final DocumentFactory<CompleteCertificateRefsType> Factory;
    public static final SchemaType type;

    CertIDListType addNewCertRefs();

    CertIDListType getCertRefs();

    String getId();

    boolean isSetId();

    void setCertRefs(CertIDListType certIDListType);

    void setId(String str);

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<CompleteCertificateRefsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "completecertificaterefstype07datype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
