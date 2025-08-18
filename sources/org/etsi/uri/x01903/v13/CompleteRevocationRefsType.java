package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CompleteRevocationRefsType extends XmlObject {
    public static final DocumentFactory<CompleteRevocationRefsType> Factory;
    public static final SchemaType type;

    CRLRefsType addNewCRLRefs();

    OCSPRefsType addNewOCSPRefs();

    OtherCertStatusRefsType addNewOtherRefs();

    CRLRefsType getCRLRefs();

    String getId();

    OCSPRefsType getOCSPRefs();

    OtherCertStatusRefsType getOtherRefs();

    boolean isSetCRLRefs();

    boolean isSetId();

    boolean isSetOCSPRefs();

    boolean isSetOtherRefs();

    void setCRLRefs(CRLRefsType cRLRefsType);

    void setId(String str);

    void setOCSPRefs(OCSPRefsType oCSPRefsType);

    void setOtherRefs(OtherCertStatusRefsType otherCertStatusRefsType);

    void unsetCRLRefs();

    void unsetId();

    void unsetOCSPRefs();

    void unsetOtherRefs();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<CompleteRevocationRefsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "completerevocationrefstyped8a5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
