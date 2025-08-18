package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import javax.xml.crypto.MarshalException;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;
import org.etsi.uri.x01903.v14.TimeStampValidationDataDocument;
import org.etsi.uri.x01903.v14.ValidationDataType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XAdESXLSignatureFacet implements SignatureFacet {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XAdESXLSignatureFacet.class);
    private final CertificateFactory certificateFactory;

    public XAdESXLSignatureFacet() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new RuntimeException("X509 JCA error: " + e.getMessage(), e);
        }
    }

    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        Logger logger = LOG;
        logger.atDebug().log("XAdES-X-L post sign phase");
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        NodeList elementsByTagNameNS = document.getElementsByTagNameNS(SignatureFacet.XADES_132_NS, "QualifyingProperties");
        QualifyingPropertiesType qualProps = getQualProps(elementsByTagNameNS);
        Optional ofNullable = Optional.ofNullable(qualProps.getUnsignedProperties());
        qualProps.getClass();
        UnsignedPropertiesType unsignedPropertiesType = (UnsignedPropertiesType) ofNullable.orElseGet(new XAdESXLSignatureFacet$$ExternalSyntheticLambda1(qualProps));
        Optional ofNullable2 = Optional.ofNullable(unsignedPropertiesType.getUnsignedSignatureProperties());
        unsignedPropertiesType.getClass();
        UnsignedSignaturePropertiesType unsignedSignaturePropertiesType = (UnsignedSignaturePropertiesType) ofNullable2.orElseGet(new XAdESXLSignatureFacet$$ExternalSyntheticLambda2(unsignedPropertiesType));
        NodeList elementsByTagNameNS2 = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "SignatureValue");
        if (elementsByTagNameNS2.getLength() == 1) {
            Element element = (Element) elementsByTagNameNS2.item(0);
            RevocationDataService revocationDataService = signatureConfig.getRevocationDataService();
            if (revocationDataService != null) {
                addCertificateValues(unsignedSignaturePropertiesType, signatureConfig);
            }
            logger.atDebug().log("creating XAdES-T time-stamp");
            try {
                RevocationData revocationData = new RevocationData();
                XAdESTimeStampType createXAdESTimeStamp = createXAdESTimeStamp(signatureInfo, revocationData, element);
                unsignedSignaturePropertiesType.addNewSignatureTimeStamp().set(createXAdESTimeStamp);
                if (revocationData.hasRevocationDataEntries()) {
                    XAdESSignatureFacet.insertXChild(unsignedSignaturePropertiesType, createValidationData(revocationData));
                }
                if (revocationDataService != null) {
                    CompleteCertificateRefsType completeCertificateRefs = completeCertificateRefs(unsignedSignaturePropertiesType, signatureConfig);
                    RevocationData revocationData2 = revocationDataService.getRevocationData(signatureConfig.getSigningCertificateChain());
                    CompleteRevocationRefsType addNewCompleteRevocationRefs = unsignedSignaturePropertiesType.addNewCompleteRevocationRefs();
                    addRevocationCRL(addNewCompleteRevocationRefs, signatureConfig, revocationData2);
                    addRevocationOCSP(addNewCompleteRevocationRefs, signatureConfig, revocationData2);
                    createRevocationValues(unsignedSignaturePropertiesType.addNewRevocationValues(), revocationData2);
                    logger.atDebug().log("creating XAdES-X time-stamp");
                    unsignedSignaturePropertiesType.addNewSigAndRefsTimeStamp().set(createXAdESTimeStamp(signatureInfo, new RevocationData(), element, createXAdESTimeStamp.getDomNode(), completeCertificateRefs.getDomNode(), addNewCompleteRevocationRefs.getDomNode()));
                }
                Element element2 = (Element) document.importNode(qualProps.getDomNode(), true);
                NodeList elementsByTagName = element2.getElementsByTagName("TimeStampValidationData");
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    ((Element) elementsByTagName.item(i)).setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX, SignatureFacet.XADES_141_NS);
                }
                Node item = elementsByTagNameNS.item(0);
                item.getParentNode().replaceChild(element2, item);
            } catch (CertificateEncodingException e) {
                throw new MarshalException("unable to create XAdES signatrue", e);
            }
        } else {
            throw new IllegalArgumentException("SignatureValue is not set.");
        }
    }

    private QualifyingPropertiesType getQualProps(NodeList nodeList) throws MarshalException {
        if (nodeList.getLength() == 1) {
            try {
                return QualifyingPropertiesDocument.Factory.parse(nodeList.item(0), POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getQualifyingProperties();
            } catch (XmlException e) {
                throw new MarshalException(e);
            }
        } else {
            throw new MarshalException("no XAdES-BES extension present");
        }
    }

    private CompleteCertificateRefsType completeCertificateRefs(UnsignedSignaturePropertiesType unsignedSignaturePropertiesType, SignatureConfig signatureConfig) {
        CompleteCertificateRefsType addNewCompleteCertificateRefs = unsignedSignaturePropertiesType.addNewCompleteCertificateRefs();
        signatureConfig.getSigningCertificateChain().stream().skip(1).forEachOrdered(new XAdESXLSignatureFacet$$ExternalSyntheticLambda0(addNewCompleteCertificateRefs.addNewCertRefs(), signatureConfig));
        return addNewCompleteCertificateRefs;
    }

    private void addRevocationCRL(CompleteRevocationRefsType completeRevocationRefsType, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLRefsType addNewCRLRefs = completeRevocationRefsType.addNewCRLRefs();
            completeRevocationRefsType.setCRLRefs(addNewCRLRefs);
            for (byte[] next : revocationData.getCRLs()) {
                CRLRefType addNewCRLRef = addNewCRLRefs.addNewCRLRef();
                try {
                    X509CRL x509crl = (X509CRL) this.certificateFactory.generateCRL(new UnsynchronizedByteArrayInputStream(next));
                    CRLIdentifierType addNewCRLIdentifier = addNewCRLRef.addNewCRLIdentifier();
                    addNewCRLIdentifier.setIssuer(x509crl.getIssuerX500Principal().getName().replace(",", ", "));
                    Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    instance.setTime(x509crl.getThisUpdate());
                    addNewCRLIdentifier.setIssueTime(instance);
                    addNewCRLIdentifier.setNumber(getCrlNumber(x509crl));
                    XAdESSignatureFacet.setDigestAlgAndValue(addNewCRLRef.addNewDigestAlgAndValue(), next, signatureConfig.getDigestAlgo());
                } catch (CRLException e) {
                    throw new RuntimeException("CRL parse error: " + e.getMessage(), e);
                }
            }
        }
    }

    private void addRevocationOCSP(CompleteRevocationRefsType completeRevocationRefsType, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasOCSPs()) {
            OCSPRefsType addNewOCSPRefs = completeRevocationRefsType.addNewOCSPRefs();
            for (byte[] next : revocationData.getOCSPs()) {
                try {
                    OCSPRefType addNewOCSPRef = addNewOCSPRefs.addNewOCSPRef();
                    XAdESSignatureFacet.setDigestAlgAndValue(addNewOCSPRef.addNewDigestAlgAndValue(), next, signatureConfig.getDigestAlgo());
                    OCSPIdentifierType addNewOCSPIdentifier = addNewOCSPRef.addNewOCSPIdentifier();
                    BasicOCSPResp basicOCSPResp = (BasicOCSPResp) new OCSPResp(next).getResponseObject();
                    Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    instance.setTime(basicOCSPResp.getProducedAt());
                    addNewOCSPIdentifier.setProducedAt(instance);
                    ResponderIDType addNewResponderID = addNewOCSPIdentifier.addNewResponderID();
                    DERTaggedObject aSN1Primitive = basicOCSPResp.getResponderId().toASN1Primitive().toASN1Primitive();
                    if (2 == aSN1Primitive.getTagNo()) {
                        addNewResponderID.setByKey(aSN1Primitive.getBaseObject().getOctets());
                    } else {
                        addNewResponderID.setByName(X500Name.getInstance(aSN1Primitive.getBaseObject()).toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("OCSP decoding error: " + e.getMessage(), e);
                }
            }
        }
    }

    private void addCertificateValues(UnsignedSignaturePropertiesType unsignedSignaturePropertiesType, SignatureConfig signatureConfig) {
        List<X509Certificate> signingCertificateChain = signatureConfig.getSigningCertificateChain();
        if (signingCertificateChain.size() >= 2) {
            CertificateValuesType addNewCertificateValues = unsignedSignaturePropertiesType.addNewCertificateValues();
            try {
                for (X509Certificate encoded : signingCertificateChain.subList(1, signingCertificateChain.size())) {
                    addNewCertificateValues.addNewEncapsulatedX509Certificate().setByteArrayValue(encoded.getEncoded());
                }
            } catch (CertificateEncodingException e) {
                throw new RuntimeException("certificate encoding error: " + e.getMessage(), e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] getC14nValue(java.util.List<org.w3c.dom.Node> r3, java.lang.String r4) {
        /*
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ RuntimeException -> 0x004b, Exception -> 0x0031 }
            r0.<init>()     // Catch:{ RuntimeException -> 0x004b, Exception -> 0x0031 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0025 }
        L_0x0009:
            boolean r1 = r3.hasNext()     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x001d
            java.lang.Object r1 = r3.next()     // Catch:{ all -> 0x0025 }
            org.w3c.dom.Node r1 = (org.w3c.dom.Node) r1     // Catch:{ all -> 0x0025 }
            org.apache.xml.security.c14n.Canonicalizer r2 = org.apache.xml.security.c14n.Canonicalizer.getInstance(r4)     // Catch:{ all -> 0x0025 }
            r2.canonicalizeSubtree(r1, r0)     // Catch:{ all -> 0x0025 }
            goto L_0x0009
        L_0x001d:
            byte[] r3 = r0.toByteArray()     // Catch:{ all -> 0x0025 }
            r0.close()     // Catch:{ RuntimeException -> 0x004b, Exception -> 0x0031 }
            return r3
        L_0x0025:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ RuntimeException -> 0x004b, Exception -> 0x0031 }
        L_0x0030:
            throw r4     // Catch:{ RuntimeException -> 0x004b, Exception -> 0x0031 }
        L_0x0031:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "c14n error: "
            r0.<init>(r1)
            java.lang.String r1 = r3.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0, r3)
            throw r4
        L_0x004b:
            r3 = move-exception
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.facets.XAdESXLSignatureFacet.getC14nValue(java.util.List, java.lang.String):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004a, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.math.BigInteger getCrlNumber(java.security.cert.X509CRL r3) {
        /*
            r2 = this;
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x509.Extension.cRLNumber
            java.lang.String r2 = r2.getId()
            byte[] r2 = r3.getExtensionValue(r2)
            if (r2 != 0) goto L_0x000e
            r2 = 0
            return r2
        L_0x000e:
            org.bouncycastle.asn1.ASN1InputStream r3 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x004b }
            r3.<init>(r2)     // Catch:{ IOException -> 0x004b }
            org.bouncycastle.asn1.ASN1Primitive r2 = r3.readObject()     // Catch:{ all -> 0x003f }
            org.bouncycastle.asn1.ASN1OctetString r2 = (org.bouncycastle.asn1.ASN1OctetString) r2     // Catch:{ all -> 0x003f }
            byte[] r2 = r2.getOctets()     // Catch:{ all -> 0x003f }
            org.bouncycastle.asn1.ASN1InputStream r0 = new org.bouncycastle.asn1.ASN1InputStream     // Catch:{ all -> 0x003f }
            r0.<init>(r2)     // Catch:{ all -> 0x003f }
            org.bouncycastle.asn1.ASN1Primitive r2 = r0.readObject()     // Catch:{ all -> 0x0033 }
            org.bouncycastle.asn1.ASN1Integer r2 = (org.bouncycastle.asn1.ASN1Integer) r2     // Catch:{ all -> 0x0033 }
            java.math.BigInteger r2 = r2.getPositiveValue()     // Catch:{ all -> 0x0033 }
            r0.close()     // Catch:{ all -> 0x003f }
            r3.close()     // Catch:{ IOException -> 0x004b }
            return r2
        L_0x0033:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ all -> 0x003f }
        L_0x003e:
            throw r1     // Catch:{ all -> 0x003f }
        L_0x003f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ IOException -> 0x004b }
        L_0x004a:
            throw r0     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            r2 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "I/O error: "
            r0.<init>(r1)
            java.lang.String r1 = r2.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.facets.XAdESXLSignatureFacet.getCrlNumber(java.security.cert.X509CRL):java.math.BigInteger");
    }

    private XAdESTimeStampType createXAdESTimeStamp(SignatureInfo signatureInfo, RevocationData revocationData, Node... nodeArr) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        try {
            byte[] timeStamp = signatureConfig.getTspService().timeStamp(signatureInfo, getC14nValue(Arrays.asList(nodeArr), signatureConfig.getXadesCanonicalizationMethod()), revocationData);
            XAdESTimeStampType newInstance = XAdESTimeStampType.Factory.newInstance();
            newInstance.addNewCanonicalizationMethod().setAlgorithm(signatureConfig.getXadesCanonicalizationMethod());
            newInstance.addNewEncapsulatedTimeStamp().setByteArrayValue(timeStamp);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException("error while creating a time-stamp: " + e.getMessage(), e);
        }
    }

    private TimeStampValidationDataDocument createValidationData(RevocationData revocationData) throws CertificateEncodingException {
        TimeStampValidationDataDocument newInstance = TimeStampValidationDataDocument.Factory.newInstance();
        ValidationDataType addNewTimeStampValidationData = newInstance.addNewTimeStampValidationData();
        List<X509Certificate> x509chain = revocationData.getX509chain();
        if (x509chain.size() > 1) {
            CertificateValuesType addNewCertificateValues = addNewTimeStampValidationData.addNewCertificateValues();
            for (X509Certificate encoded : x509chain.subList(1, x509chain.size())) {
                addNewCertificateValues.addNewEncapsulatedX509Certificate().setByteArrayValue(encoded.getEncoded());
            }
        }
        createRevocationValues(addNewTimeStampValidationData.addNewRevocationValues(), revocationData);
        return newInstance;
    }

    private void createRevocationValues(RevocationValuesType revocationValuesType, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLValuesType addNewCRLValues = revocationValuesType.addNewCRLValues();
            for (byte[] byteArrayValue : revocationData.getCRLs()) {
                addNewCRLValues.addNewEncapsulatedCRLValue().setByteArrayValue(byteArrayValue);
            }
        }
        if (revocationData.hasOCSPs()) {
            OCSPValuesType addNewOCSPValues = revocationValuesType.addNewOCSPValues();
            for (byte[] byteArrayValue2 : revocationData.getOCSPs()) {
                addNewOCSPValues.addNewEncapsulatedOCSPValue().setByteArrayValue(byteArrayValue2);
            }
        }
    }
}
