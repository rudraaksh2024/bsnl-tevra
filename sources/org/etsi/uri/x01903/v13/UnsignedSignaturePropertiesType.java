package org.etsi.uri.x01903.v13;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface UnsignedSignaturePropertiesType extends XmlObject {
    public static final DocumentFactory<UnsignedSignaturePropertiesType> Factory;
    public static final SchemaType type;

    XAdESTimeStampType addNewArchiveTimeStamp();

    CertificateValuesType addNewAttrAuthoritiesCertValues();

    CompleteCertificateRefsType addNewAttributeCertificateRefs();

    CompleteRevocationRefsType addNewAttributeRevocationRefs();

    RevocationValuesType addNewAttributeRevocationValues();

    CertificateValuesType addNewCertificateValues();

    CompleteCertificateRefsType addNewCompleteCertificateRefs();

    CompleteRevocationRefsType addNewCompleteRevocationRefs();

    CounterSignatureType addNewCounterSignature();

    XAdESTimeStampType addNewRefsOnlyTimeStamp();

    RevocationValuesType addNewRevocationValues();

    XAdESTimeStampType addNewSigAndRefsTimeStamp();

    XAdESTimeStampType addNewSignatureTimeStamp();

    XAdESTimeStampType getArchiveTimeStampArray(int i);

    XAdESTimeStampType[] getArchiveTimeStampArray();

    List<XAdESTimeStampType> getArchiveTimeStampList();

    CertificateValuesType getAttrAuthoritiesCertValuesArray(int i);

    CertificateValuesType[] getAttrAuthoritiesCertValuesArray();

    List<CertificateValuesType> getAttrAuthoritiesCertValuesList();

    CompleteCertificateRefsType getAttributeCertificateRefsArray(int i);

    CompleteCertificateRefsType[] getAttributeCertificateRefsArray();

    List<CompleteCertificateRefsType> getAttributeCertificateRefsList();

    CompleteRevocationRefsType getAttributeRevocationRefsArray(int i);

    CompleteRevocationRefsType[] getAttributeRevocationRefsArray();

    List<CompleteRevocationRefsType> getAttributeRevocationRefsList();

    RevocationValuesType getAttributeRevocationValuesArray(int i);

    RevocationValuesType[] getAttributeRevocationValuesArray();

    List<RevocationValuesType> getAttributeRevocationValuesList();

    CertificateValuesType getCertificateValuesArray(int i);

    CertificateValuesType[] getCertificateValuesArray();

    List<CertificateValuesType> getCertificateValuesList();

    CompleteCertificateRefsType getCompleteCertificateRefsArray(int i);

    CompleteCertificateRefsType[] getCompleteCertificateRefsArray();

    List<CompleteCertificateRefsType> getCompleteCertificateRefsList();

    CompleteRevocationRefsType getCompleteRevocationRefsArray(int i);

    CompleteRevocationRefsType[] getCompleteRevocationRefsArray();

    List<CompleteRevocationRefsType> getCompleteRevocationRefsList();

    CounterSignatureType getCounterSignatureArray(int i);

    CounterSignatureType[] getCounterSignatureArray();

    List<CounterSignatureType> getCounterSignatureList();

    String getId();

    XAdESTimeStampType getRefsOnlyTimeStampArray(int i);

    XAdESTimeStampType[] getRefsOnlyTimeStampArray();

    List<XAdESTimeStampType> getRefsOnlyTimeStampList();

    RevocationValuesType getRevocationValuesArray(int i);

    RevocationValuesType[] getRevocationValuesArray();

    List<RevocationValuesType> getRevocationValuesList();

    XAdESTimeStampType getSigAndRefsTimeStampArray(int i);

    XAdESTimeStampType[] getSigAndRefsTimeStampArray();

    List<XAdESTimeStampType> getSigAndRefsTimeStampList();

    XAdESTimeStampType getSignatureTimeStampArray(int i);

    XAdESTimeStampType[] getSignatureTimeStampArray();

    List<XAdESTimeStampType> getSignatureTimeStampList();

    XAdESTimeStampType insertNewArchiveTimeStamp(int i);

    CertificateValuesType insertNewAttrAuthoritiesCertValues(int i);

    CompleteCertificateRefsType insertNewAttributeCertificateRefs(int i);

    CompleteRevocationRefsType insertNewAttributeRevocationRefs(int i);

    RevocationValuesType insertNewAttributeRevocationValues(int i);

    CertificateValuesType insertNewCertificateValues(int i);

    CompleteCertificateRefsType insertNewCompleteCertificateRefs(int i);

    CompleteRevocationRefsType insertNewCompleteRevocationRefs(int i);

    CounterSignatureType insertNewCounterSignature(int i);

    XAdESTimeStampType insertNewRefsOnlyTimeStamp(int i);

    RevocationValuesType insertNewRevocationValues(int i);

    XAdESTimeStampType insertNewSigAndRefsTimeStamp(int i);

    XAdESTimeStampType insertNewSignatureTimeStamp(int i);

    boolean isSetId();

    void removeArchiveTimeStamp(int i);

    void removeAttrAuthoritiesCertValues(int i);

    void removeAttributeCertificateRefs(int i);

    void removeAttributeRevocationRefs(int i);

    void removeAttributeRevocationValues(int i);

    void removeCertificateValues(int i);

    void removeCompleteCertificateRefs(int i);

    void removeCompleteRevocationRefs(int i);

    void removeCounterSignature(int i);

    void removeRefsOnlyTimeStamp(int i);

    void removeRevocationValues(int i);

    void removeSigAndRefsTimeStamp(int i);

    void removeSignatureTimeStamp(int i);

    void setArchiveTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setArchiveTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    void setAttrAuthoritiesCertValuesArray(int i, CertificateValuesType certificateValuesType);

    void setAttrAuthoritiesCertValuesArray(CertificateValuesType[] certificateValuesTypeArr);

    void setAttributeCertificateRefsArray(int i, CompleteCertificateRefsType completeCertificateRefsType);

    void setAttributeCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArr);

    void setAttributeRevocationRefsArray(int i, CompleteRevocationRefsType completeRevocationRefsType);

    void setAttributeRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArr);

    void setAttributeRevocationValuesArray(int i, RevocationValuesType revocationValuesType);

    void setAttributeRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArr);

    void setCertificateValuesArray(int i, CertificateValuesType certificateValuesType);

    void setCertificateValuesArray(CertificateValuesType[] certificateValuesTypeArr);

    void setCompleteCertificateRefsArray(int i, CompleteCertificateRefsType completeCertificateRefsType);

    void setCompleteCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArr);

    void setCompleteRevocationRefsArray(int i, CompleteRevocationRefsType completeRevocationRefsType);

    void setCompleteRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArr);

    void setCounterSignatureArray(int i, CounterSignatureType counterSignatureType);

    void setCounterSignatureArray(CounterSignatureType[] counterSignatureTypeArr);

    void setId(String str);

    void setRefsOnlyTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setRefsOnlyTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    void setRevocationValuesArray(int i, RevocationValuesType revocationValuesType);

    void setRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArr);

    void setSigAndRefsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setSigAndRefsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    void setSignatureTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType);

    void setSignatureTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr);

    int sizeOfArchiveTimeStampArray();

    int sizeOfAttrAuthoritiesCertValuesArray();

    int sizeOfAttributeCertificateRefsArray();

    int sizeOfAttributeRevocationRefsArray();

    int sizeOfAttributeRevocationValuesArray();

    int sizeOfCertificateValuesArray();

    int sizeOfCompleteCertificateRefsArray();

    int sizeOfCompleteRevocationRefsArray();

    int sizeOfCounterSignatureArray();

    int sizeOfRefsOnlyTimeStampArray();

    int sizeOfRevocationValuesArray();

    int sizeOfSigAndRefsTimeStampArray();

    int sizeOfSignatureTimeStampArray();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<UnsignedSignaturePropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "unsignedsignaturepropertiestypecf32type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
