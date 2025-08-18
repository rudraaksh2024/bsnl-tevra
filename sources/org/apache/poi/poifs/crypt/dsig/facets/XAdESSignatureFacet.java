package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.security.auth.x500.X500Principal;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.SignerRoleType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XAdESSignatureFacet implements SignatureFacet {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XAdESSignatureFacet.class);
    private static final String XADES_TYPE = "http://uri.etsi.org/01903#SignedProperties";
    private final Map<String, String> dataObjectFormatMimeTypes = new HashMap();

    public void preSign(SignatureInfo signatureInfo, Document document, List<Reference> list, List<XMLObject> list2) throws XMLSignatureException {
        LOG.atDebug().log("preSign");
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        QualifyingPropertiesType addNewQualifyingProperties = QualifyingPropertiesDocument.Factory.newInstance().addNewQualifyingProperties();
        addNewQualifyingProperties.setTarget("#" + signatureConfig.getPackageSignatureId());
        createSignedProperties(signatureInfo, addNewQualifyingProperties);
        list2.add(addXadesObject(signatureInfo, document, addNewQualifyingProperties));
        list.add(addXadesReference(signatureInfo));
    }

    /* access modifiers changed from: protected */
    public SignedPropertiesType createSignedProperties(SignatureInfo signatureInfo, QualifyingPropertiesType qualifyingPropertiesType) {
        SignedPropertiesType addNewSignedProperties = qualifyingPropertiesType.addNewSignedProperties();
        addNewSignedProperties.setId(signatureInfo.getSignatureConfig().getXadesSignatureId());
        SignedSignaturePropertiesType addNewSignedSignatureProperties = addNewSignedProperties.addNewSignedSignatureProperties();
        addSigningTime(signatureInfo, addNewSignedSignatureProperties);
        addCertificate(signatureInfo, addNewSignedSignatureProperties);
        addXadesRole(signatureInfo, addNewSignedSignatureProperties);
        addPolicy(signatureInfo, addNewSignedSignatureProperties);
        addMimeTypes(signatureInfo, addNewSignedProperties);
        addCommitmentType(signatureInfo, addNewSignedProperties);
        return addNewSignedProperties;
    }

    /* access modifiers changed from: protected */
    public void addSigningTime(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignaturePropertiesType) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
        instance.setTime(signatureConfig.getExecutionTime());
        instance.clear(14);
        signedSignaturePropertiesType.setSigningTime(instance);
    }

    /* access modifiers changed from: protected */
    public void addCertificate(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignaturePropertiesType) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        List<X509Certificate> signingCertificateChain = signatureConfig.getSigningCertificateChain();
        if (signingCertificateChain == null || signingCertificateChain.isEmpty()) {
            throw new RuntimeException("no signing certificate chain available");
        }
        setCertID(signedSignaturePropertiesType.addNewSigningCertificate().addNewCert(), signatureConfig, signatureConfig.isXadesIssuerNameNoReverseOrder(), signingCertificateChain.get(0));
    }

    /* access modifiers changed from: protected */
    public void addXadesRole(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignaturePropertiesType) {
        String xadesRole = signatureInfo.getSignatureConfig().getXadesRole();
        if (xadesRole != null && !xadesRole.isEmpty()) {
            SignerRoleType addNewSignerRole = signedSignaturePropertiesType.addNewSignerRole();
            signedSignaturePropertiesType.setSignerRole(addNewSignerRole);
            AnyType addNewClaimedRole = addNewSignerRole.addNewClaimedRoles().addNewClaimedRole();
            XmlString newInstance = XmlString.Factory.newInstance();
            newInstance.setStringValue(xadesRole);
            insertXChild(addNewClaimedRole, newInstance);
        }
    }

    /* access modifiers changed from: protected */
    public void addPolicy(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignaturePropertiesType) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        SignaturePolicyService signaturePolicyService = signatureConfig.getSignaturePolicyService();
        if (signaturePolicyService != null) {
            SignaturePolicyIdType addNewSignaturePolicyId = signedSignaturePropertiesType.addNewSignaturePolicyIdentifier().addNewSignaturePolicyId();
            ObjectIdentifierType addNewSigPolicyId = addNewSignaturePolicyId.addNewSigPolicyId();
            addNewSigPolicyId.setDescription(signaturePolicyService.getSignaturePolicyDescription());
            addNewSigPolicyId.addNewIdentifier().setStringValue(signaturePolicyService.getSignaturePolicyIdentifier());
            setDigestAlgAndValue(addNewSignaturePolicyId.addNewSigPolicyHash(), signaturePolicyService.getSignaturePolicyDocument(), signatureConfig.getDigestAlgo());
            String signaturePolicyDownloadUrl = signaturePolicyService.getSignaturePolicyDownloadUrl();
            if (signaturePolicyDownloadUrl != null) {
                AnyType addNewSigPolicyQualifier = addNewSignaturePolicyId.addNewSigPolicyQualifiers().addNewSigPolicyQualifier();
                XmlString newInstance = XmlString.Factory.newInstance();
                newInstance.setStringValue(signaturePolicyDownloadUrl);
                insertXChild(addNewSigPolicyQualifier, newInstance);
            }
        } else if (signatureConfig.isXadesSignaturePolicyImplied()) {
            signedSignaturePropertiesType.addNewSignaturePolicyIdentifier().addNewSignaturePolicyImplied();
        }
    }

    /* access modifiers changed from: protected */
    public void addMimeTypes(SignatureInfo signatureInfo, SignedPropertiesType signedPropertiesType) {
        if (!this.dataObjectFormatMimeTypes.isEmpty()) {
            this.dataObjectFormatMimeTypes.forEach(new XAdESSignatureFacet$$ExternalSyntheticLambda0(signedPropertiesType.addNewSignedDataObjectProperties().getDataObjectFormatList()));
        }
    }

    static /* synthetic */ void lambda$addMimeTypes$0(List list, String str, String str2) {
        DataObjectFormatType newInstance = DataObjectFormatType.Factory.newInstance();
        newInstance.setObjectReference("#" + str);
        newInstance.setMimeType(str2);
        list.add(newInstance);
    }

    /* access modifiers changed from: protected */
    public XMLObject addXadesObject(SignatureInfo signatureInfo, Document document, QualifyingPropertiesType qualifyingPropertiesType) {
        return signatureInfo.getSignatureFactory().newXMLObject(Collections.singletonList(new DOMStructure(importNode(document, qualifyingPropertiesType))), (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public void addCommitmentType(SignatureInfo signatureInfo, SignedPropertiesType signedPropertiesType) {
        SignedDataObjectPropertiesType signedDataObjectPropertiesType;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        String signatureDescription = signatureConfig.getSignatureDescription();
        String commitmentType = signatureConfig.getCommitmentType();
        if (signatureDescription != null || commitmentType != null) {
            if (signedPropertiesType.isSetSignedDataObjectProperties()) {
                signedDataObjectPropertiesType = signedPropertiesType.getSignedDataObjectProperties();
            } else {
                signedDataObjectPropertiesType = signedPropertiesType.addNewSignedDataObjectProperties();
            }
            CommitmentTypeIndicationType addNewCommitmentTypeIndication = signedDataObjectPropertiesType.addNewCommitmentTypeIndication();
            if (commitmentType != null) {
                ObjectIdentifierType addNewCommitmentTypeId = addNewCommitmentTypeIndication.addNewCommitmentTypeId();
                addNewCommitmentTypeId.addNewIdentifier().setStringValue("http://uri.etsi.org/01903/v1.2.2#ProofOfOrigin");
                addNewCommitmentTypeId.setDescription(signatureConfig.getCommitmentType());
            }
            if (signatureDescription != null) {
                addNewCommitmentTypeIndication.addNewAllSignedDataObjects();
                addNewCommitmentTypeIndication.addNewCommitmentTypeQualifiers().addNewCommitmentTypeQualifier().set(XmlString.Factory.newValue(signatureDescription));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Reference addXadesReference(SignatureInfo signatureInfo) throws XMLSignatureException {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        return SignatureFacetHelper.newReference(signatureInfo, "#" + signatureConfig.getXadesSignatureId(), Collections.singletonList(SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315")), XADES_TYPE);
    }

    protected static void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType, byte[] bArr, HashAlgorithm hashAlgorithm) {
        digestAlgAndValueType.addNewDigestMethod().setAlgorithm(SignatureConfig.getDigestMethodUri(hashAlgorithm));
        digestAlgAndValueType.setDigestValue(CryptoFunctions.getMessageDigest(hashAlgorithm).digest(bArr));
    }

    protected static void setCertID(CertIDType certIDType, SignatureConfig signatureConfig, boolean z, X509Certificate x509Certificate) {
        String str;
        X509IssuerSerialType addNewIssuerSerial = certIDType.addNewIssuerSerial();
        X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
        if (z) {
            str = issuerX500Principal.getName().replace(",", ", ");
        } else {
            str = issuerX500Principal.toString();
        }
        addNewIssuerSerial.setX509IssuerName(str);
        addNewIssuerSerial.setX509SerialNumber(x509Certificate.getSerialNumber());
        try {
            setDigestAlgAndValue(certIDType.addNewCertDigest(), x509Certificate.getEncoded(), signatureConfig.getXadesDigestAlgo());
        } catch (CertificateEncodingException e) {
            throw new RuntimeException("certificate encoding error: " + e.getMessage(), e);
        }
    }

    public void addMimeType(String str, String str2) {
        this.dataObjectFormatMimeTypes.put(str, str2);
    }

    protected static void insertXChild(XmlObject xmlObject, XmlObject xmlObject2) {
        XmlCursor newCursor = xmlObject.newCursor();
        newCursor.toEndToken();
        XmlCursor newCursor2 = xmlObject2.newCursor();
        newCursor2.toNextToken();
        newCursor2.moveXml(newCursor);
        newCursor2.dispose();
        newCursor.dispose();
    }

    private static Element importNode(Document document, XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            QName name = newCursor.getName();
            Element createElementNS = document.createElementNS(name.getNamespaceURI(), name.getLocalPart());
            while (newCursor.hasNextToken()) {
                switch (newCursor.toNextToken().intValue()) {
                    case 3:
                        QName name2 = newCursor.getName();
                        createElementNS = (Element) createElementNS.appendChild(document.createElementNS(name2.getNamespaceURI(), name2.getLocalPart()));
                        break;
                    case 4:
                        Element element = (Element) createElementNS.getParentNode();
                        if (element == null) {
                            break;
                        } else {
                            createElementNS = element;
                            break;
                        }
                    case 5:
                        createElementNS.appendChild(document.createTextNode(newCursor.getTextValue()));
                        break;
                    case 6:
                        QName name3 = newCursor.getName();
                        createElementNS.setAttributeNS(name3.getNamespaceURI(), name3.getLocalPart(), newCursor.getTextValue());
                        if (!PackageRelationship.ID_ATTRIBUTE_NAME.equals(name3.getLocalPart())) {
                            break;
                        } else {
                            createElementNS.setIdAttribute(PackageRelationship.ID_ATTRIBUTE_NAME, true);
                            break;
                        }
                    case 7:
                        QName name4 = newCursor.getName();
                        createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_STRING + name4.getPrefix(), name4.getNamespaceURI());
                        break;
                    case 8:
                        createElementNS.appendChild(document.createComment(newCursor.getTextValue()));
                        break;
                }
            }
            return createElementNS;
        } finally {
            newCursor.dispose();
        }
    }
}
