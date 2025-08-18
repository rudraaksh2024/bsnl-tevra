package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.CounterSignatureType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

public class UnsignedSignaturePropertiesTypeImpl extends XmlComplexContentImpl implements UnsignedSignaturePropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CounterSignature"), new QName(SignatureFacet.XADES_132_NS, "SignatureTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "CompleteCertificateRefs"), new QName(SignatureFacet.XADES_132_NS, "CompleteRevocationRefs"), new QName(SignatureFacet.XADES_132_NS, "AttributeCertificateRefs"), new QName(SignatureFacet.XADES_132_NS, "AttributeRevocationRefs"), new QName(SignatureFacet.XADES_132_NS, "SigAndRefsTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "RefsOnlyTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "CertificateValues"), new QName(SignatureFacet.XADES_132_NS, "RevocationValues"), new QName(SignatureFacet.XADES_132_NS, "AttrAuthoritiesCertValues"), new QName(SignatureFacet.XADES_132_NS, "AttributeRevocationValues"), new QName(SignatureFacet.XADES_132_NS, "ArchiveTimeStamp"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public UnsignedSignaturePropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CounterSignatureType> getCounterSignatureList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda56(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda57(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda58(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda59(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CounterSignatureType[] getCounterSignatureArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CounterSignatureType[0]);
    }

    public CounterSignatureType getCounterSignatureArray(int i) {
        CounterSignatureType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCounterSignatureArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCounterSignatureArray(CounterSignatureType[] counterSignatureTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) counterSignatureTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCounterSignatureArray(int i, CounterSignatureType counterSignatureType) {
        generatedSetterHelperImpl(counterSignatureType, PROPERTY_QNAME[0], i, 2);
    }

    public CounterSignatureType insertNewCounterSignature(int i) {
        CounterSignatureType insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CounterSignatureType addNewCounterSignature() {
        CounterSignatureType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeCounterSignature(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<XAdESTimeStampType> getSignatureTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda50(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda51(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda52(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda53(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getSignatureTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getSignatureTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfSignatureTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSignatureTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[1]);
    }

    public void setSignatureTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[1], i, 2);
    }

    public XAdESTimeStampType insertNewSignatureTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewSignatureTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return xAdESTimeStampType;
    }

    public void removeSignatureTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CompleteCertificateRefsType> getCompleteCertificateRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda12(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda13(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda14(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda15(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CompleteCertificateRefsType[] getCompleteCertificateRefsArray() {
        return (CompleteCertificateRefsType[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CompleteCertificateRefsType[0]);
    }

    public CompleteCertificateRefsType getCompleteCertificateRefsArray(int i) {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (completeCertificateRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return completeCertificateRefsType;
    }

    public int sizeOfCompleteCertificateRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setCompleteCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) completeCertificateRefsTypeArr, PROPERTY_QNAME[2]);
    }

    public void setCompleteCertificateRefsArray(int i, CompleteCertificateRefsType completeCertificateRefsType) {
        generatedSetterHelperImpl(completeCertificateRefsType, PROPERTY_QNAME[2], i, 2);
    }

    public CompleteCertificateRefsType insertNewCompleteCertificateRefs(int i) {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return completeCertificateRefsType;
    }

    public CompleteCertificateRefsType addNewCompleteCertificateRefs() {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return completeCertificateRefsType;
    }

    public void removeCompleteCertificateRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CompleteRevocationRefsType> getCompleteRevocationRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda0(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda11(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda22(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda33(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CompleteRevocationRefsType[] getCompleteRevocationRefsArray() {
        return (CompleteRevocationRefsType[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CompleteRevocationRefsType[0]);
    }

    public CompleteRevocationRefsType getCompleteRevocationRefsArray(int i) {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (completeRevocationRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return completeRevocationRefsType;
    }

    public int sizeOfCompleteRevocationRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setCompleteRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) completeRevocationRefsTypeArr, PROPERTY_QNAME[3]);
    }

    public void setCompleteRevocationRefsArray(int i, CompleteRevocationRefsType completeRevocationRefsType) {
        generatedSetterHelperImpl(completeRevocationRefsType, PROPERTY_QNAME[3], i, 2);
    }

    public CompleteRevocationRefsType insertNewCompleteRevocationRefs(int i) {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return completeRevocationRefsType;
    }

    public CompleteRevocationRefsType addNewCompleteRevocationRefs() {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return completeRevocationRefsType;
    }

    public void removeCompleteRevocationRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CompleteCertificateRefsType> getAttributeCertificateRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda17(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda18(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda19(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda20(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CompleteCertificateRefsType[] getAttributeCertificateRefsArray() {
        return (CompleteCertificateRefsType[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CompleteCertificateRefsType[0]);
    }

    public CompleteCertificateRefsType getAttributeCertificateRefsArray(int i) {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (completeCertificateRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return completeCertificateRefsType;
    }

    public int sizeOfAttributeCertificateRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setAttributeCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) completeCertificateRefsTypeArr, PROPERTY_QNAME[4]);
    }

    public void setAttributeCertificateRefsArray(int i, CompleteCertificateRefsType completeCertificateRefsType) {
        generatedSetterHelperImpl(completeCertificateRefsType, PROPERTY_QNAME[4], i, 2);
    }

    public CompleteCertificateRefsType insertNewAttributeCertificateRefs(int i) {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return completeCertificateRefsType;
    }

    public CompleteCertificateRefsType addNewAttributeCertificateRefs() {
        CompleteCertificateRefsType completeCertificateRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeCertificateRefsType = (CompleteCertificateRefsType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return completeCertificateRefsType;
    }

    public void removeAttributeCertificateRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CompleteRevocationRefsType> getAttributeRevocationRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda6(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda7(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda8(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda9(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CompleteRevocationRefsType[] getAttributeRevocationRefsArray() {
        return (CompleteRevocationRefsType[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CompleteRevocationRefsType[0]);
    }

    public CompleteRevocationRefsType getAttributeRevocationRefsArray(int i) {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (completeRevocationRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return completeRevocationRefsType;
    }

    public int sizeOfAttributeRevocationRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAttributeRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) completeRevocationRefsTypeArr, PROPERTY_QNAME[5]);
    }

    public void setAttributeRevocationRefsArray(int i, CompleteRevocationRefsType completeRevocationRefsType) {
        generatedSetterHelperImpl(completeRevocationRefsType, PROPERTY_QNAME[5], i, 2);
    }

    public CompleteRevocationRefsType insertNewAttributeRevocationRefs(int i) {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return completeRevocationRefsType;
    }

    public CompleteRevocationRefsType addNewAttributeRevocationRefs() {
        CompleteRevocationRefsType completeRevocationRefsType;
        synchronized (monitor()) {
            check_orphaned();
            completeRevocationRefsType = (CompleteRevocationRefsType) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return completeRevocationRefsType;
    }

    public void removeAttributeRevocationRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<XAdESTimeStampType> getSigAndRefsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda55(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda61(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda62(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda63(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda64(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getSigAndRefsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getSigAndRefsTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfSigAndRefsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setSigAndRefsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[6]);
    }

    public void setSigAndRefsTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[6], i, 2);
    }

    public XAdESTimeStampType insertNewSigAndRefsTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewSigAndRefsTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return xAdESTimeStampType;
    }

    public void removeSigAndRefsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<XAdESTimeStampType> getRefsOnlyTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda28(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda29(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda30(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda31(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getRefsOnlyTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getRefsOnlyTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfRefsOnlyTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setRefsOnlyTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[7]);
    }

    public void setRefsOnlyTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[7], i, 2);
    }

    public XAdESTimeStampType insertNewRefsOnlyTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewRefsOnlyTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return xAdESTimeStampType;
    }

    public void removeRefsOnlyTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CertificateValuesType> getCertificateValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda45(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda46(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda47(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda48(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CertificateValuesType[] getCertificateValuesArray() {
        return (CertificateValuesType[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CertificateValuesType[0]);
    }

    public CertificateValuesType getCertificateValuesArray(int i) {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (certificateValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return certificateValuesType;
    }

    public int sizeOfCertificateValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setCertificateValuesArray(CertificateValuesType[] certificateValuesTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) certificateValuesTypeArr, PROPERTY_QNAME[8]);
    }

    public void setCertificateValuesArray(int i, CertificateValuesType certificateValuesType) {
        generatedSetterHelperImpl(certificateValuesType, PROPERTY_QNAME[8], i, 2);
    }

    public CertificateValuesType insertNewCertificateValues(int i) {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return certificateValuesType;
    }

    public CertificateValuesType addNewCertificateValues() {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return certificateValuesType;
    }

    public void removeCertificateValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<RevocationValuesType> getRevocationValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda34(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda35(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda36(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda37(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public RevocationValuesType[] getRevocationValuesArray() {
        return (RevocationValuesType[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new RevocationValuesType[0]);
    }

    public RevocationValuesType getRevocationValuesArray(int i) {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (revocationValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return revocationValuesType;
    }

    public int sizeOfRevocationValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) revocationValuesTypeArr, PROPERTY_QNAME[9]);
    }

    public void setRevocationValuesArray(int i, RevocationValuesType revocationValuesType) {
        generatedSetterHelperImpl(revocationValuesType, PROPERTY_QNAME[9], i, 2);
    }

    public RevocationValuesType insertNewRevocationValues(int i) {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return revocationValuesType;
    }

    public RevocationValuesType addNewRevocationValues() {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return revocationValuesType;
    }

    public void removeRevocationValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CertificateValuesType> getAttrAuthoritiesCertValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda23(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda24(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda25(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda26(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CertificateValuesType[] getAttrAuthoritiesCertValuesArray() {
        return (CertificateValuesType[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CertificateValuesType[0]);
    }

    public CertificateValuesType getAttrAuthoritiesCertValuesArray(int i) {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (certificateValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return certificateValuesType;
    }

    public int sizeOfAttrAuthoritiesCertValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setAttrAuthoritiesCertValuesArray(CertificateValuesType[] certificateValuesTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) certificateValuesTypeArr, PROPERTY_QNAME[10]);
    }

    public void setAttrAuthoritiesCertValuesArray(int i, CertificateValuesType certificateValuesType) {
        generatedSetterHelperImpl(certificateValuesType, PROPERTY_QNAME[10], i, 2);
    }

    public CertificateValuesType insertNewAttrAuthoritiesCertValues(int i) {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return certificateValuesType;
    }

    public CertificateValuesType addNewAttrAuthoritiesCertValues() {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            certificateValuesType = (CertificateValuesType) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return certificateValuesType;
    }

    public void removeAttrAuthoritiesCertValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<RevocationValuesType> getAttributeRevocationValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda1(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda2(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda3(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda4(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public RevocationValuesType[] getAttributeRevocationValuesArray() {
        return (RevocationValuesType[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new RevocationValuesType[0]);
    }

    public RevocationValuesType getAttributeRevocationValuesArray(int i) {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (revocationValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return revocationValuesType;
    }

    public int sizeOfAttributeRevocationValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setAttributeRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) revocationValuesTypeArr, PROPERTY_QNAME[11]);
    }

    public void setAttributeRevocationValuesArray(int i, RevocationValuesType revocationValuesType) {
        generatedSetterHelperImpl(revocationValuesType, PROPERTY_QNAME[11], i, 2);
    }

    public RevocationValuesType insertNewAttributeRevocationValues(int i) {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return revocationValuesType;
    }

    public RevocationValuesType addNewAttributeRevocationValues() {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            revocationValuesType = (RevocationValuesType) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return revocationValuesType;
    }

    public void removeAttributeRevocationValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<XAdESTimeStampType> getArchiveTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda39(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda40(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda41(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda42(this), new UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public XAdESTimeStampType[] getArchiveTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new XAdESTimeStampType[0]);
    }

    public XAdESTimeStampType getArchiveTimeStampArray(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return xAdESTimeStampType;
    }

    public int sizeOfArchiveTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setArchiveTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) xAdESTimeStampTypeArr, PROPERTY_QNAME[12]);
    }

    public void setArchiveTimeStampArray(int i, XAdESTimeStampType xAdESTimeStampType) {
        generatedSetterHelperImpl(xAdESTimeStampType, PROPERTY_QNAME[12], i, 2);
    }

    public XAdESTimeStampType insertNewArchiveTimeStamp(int i) {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return xAdESTimeStampType;
    }

    public XAdESTimeStampType addNewArchiveTimeStamp() {
        XAdESTimeStampType xAdESTimeStampType;
        synchronized (monitor()) {
            check_orphaned();
            xAdESTimeStampType = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return xAdESTimeStampType;
    }

    public void removeArchiveTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public String getId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlID xgetId() {
        XmlID xmlID;
        synchronized (monitor()) {
            check_orphaned();
            xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return xmlID;
    }

    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setId(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl.setId(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetId(org.apache.xmlbeans.XmlID r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl.xsetId(org.apache.xmlbeans.XmlID):void");
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }
}
