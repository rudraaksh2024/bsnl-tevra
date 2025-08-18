package org.apache.poi.poifs.crypt.dsig;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.w3c.dom.Document;

public class SignaturePart {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SignaturePart.class);
    private static final String XMLSEC_VALIDATE_MANIFEST = "org.jcp.xml.dsig.validateManifests";
    private static final String XMLSEC_VALIDATE_SECURE = "org.apache.jcp.xml.dsig.secureValidation";
    private List<X509Certificate> certChain;
    /* access modifiers changed from: private */
    public final SignatureInfo signatureInfo;
    private final PackagePart signaturePart;
    private X509Certificate signer;

    SignaturePart(PackagePart packagePart, SignatureInfo signatureInfo2) {
        this.signaturePart = packagePart;
        this.signatureInfo = signatureInfo2;
    }

    public PackagePart getPackagePart() {
        return this.signaturePart;
    }

    public X509Certificate getSigner() {
        return this.signer;
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.w3.x2000.x09.xmldsig.SignatureDocument getSignatureDocument() throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.signaturePart
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.w3.x2000.x09.xmldsig.SignatureDocument> r0 = org.w3.x2000.x09.xmldsig.SignatureDocument.Factory     // Catch:{ all -> 0x0016 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0016 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r2, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0016 }
            org.w3.x2000.x09.xmldsig.SignatureDocument r0 = (org.w3.x2000.x09.xmldsig.SignatureDocument) r0     // Catch:{ all -> 0x0016 }
            if (r2 == 0) goto L_0x0015
            r2.close()
        L_0x0015:
            return r0
        L_0x0016:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r1 = move-exception
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0023:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignaturePart.getSignatureDocument():org.w3.x2000.x09.xmldsig.SignatureDocument");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0093, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0094, code lost:
        if (r2 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean validate() {
        /*
            r8 = this;
            org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector r0 = new org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector
            r0.<init>()
            javax.xml.xpath.XPathFactory r1 = org.apache.poi.ooxml.util.XPathHelper.getFactory()
            javax.xml.xpath.XPath r1 = r1.newXPath()
            org.apache.poi.poifs.crypt.dsig.SignaturePart$XPathNSContext r2 = new org.apache.poi.poifs.crypt.dsig.SignaturePart$XPathNSContext
            r3 = 0
            r2.<init>()
            r1.setNamespaceContext(r2)
            org.apache.poi.openxml4j.opc.PackagePart r2 = r8.signaturePart     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.w3c.dom.Document r3 = org.apache.poi.ooxml.util.DocumentHelper.readDocument((java.io.InputStream) r2)     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
        L_0x0025:
            java.lang.String r2 = "//*[@Id]"
            javax.xml.xpath.XPathExpression r1 = r1.compile(r2)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            javax.xml.namespace.QName r2 = javax.xml.xpath.XPathConstants.NODESET     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.lang.Object r1 = r1.evaluate(r3, r2)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.w3c.dom.NodeList r1 = (org.w3c.dom.NodeList) r1     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            int r2 = r1.getLength()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r4 = 0
        L_0x0038:
            if (r4 >= r2) goto L_0x0049
            org.w3c.dom.Node r5 = r1.item(r4)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.w3c.dom.Element r5 = (org.w3c.dom.Element) r5     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.lang.String r6 = "Id"
            r7 = 1
            r5.setIdAttribute(r6, r7)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            int r4 = r4 + 1
            goto L_0x0038
        L_0x0049:
            javax.xml.crypto.dsig.dom.DOMValidateContext r1 = new javax.xml.crypto.dsig.dom.DOMValidateContext     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r1.<init>(r0, r3)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.lang.String r2 = "org.jcp.xml.dsig.validateManifests"
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r1.setProperty(r2, r4)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.lang.String r2 = "org.apache.jcp.xml.dsig.secureValidation"
            org.apache.poi.poifs.crypt.dsig.SignatureInfo r4 = r8.signatureInfo     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.apache.poi.poifs.crypt.dsig.SignatureConfig r4 = r4.getSignatureConfig()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            boolean r4 = r4.isSecureValidation()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r1.setProperty(r2, r4)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.apache.poi.poifs.crypt.dsig.SignatureInfo r2 = r8.signatureInfo     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            javax.xml.crypto.URIDereferencer r2 = r2.getUriDereferencer()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r1.setURIDereferencer(r2)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            org.apache.poi.poifs.crypt.dsig.SignatureInfo r2 = r8.signatureInfo     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            javax.xml.crypto.dsig.XMLSignatureFactory r2 = r2.getSignatureFactory()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            javax.xml.crypto.dsig.XMLSignature r2 = r2.unmarshalXMLSignature(r1)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            boolean r1 = r2.validate(r1)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            if (r1 == 0) goto L_0x0090
            java.security.cert.X509Certificate r4 = r0.getSigner()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r8.signer = r4     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            java.util.List r0 = r0.getCertChain()     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r8.certChain = r0     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
            r8.extractConfig(r3, r2)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
        L_0x0090:
            return r1
        L_0x0091:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r0 = move-exception
            if (r2 == 0) goto L_0x009e
            r2.close()     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r1 = move-exception
            r8.addSuppressed(r1)     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
        L_0x009e:
            throw r0     // Catch:{ IOException -> 0x00f7, SAXException -> 0x00e1, XPathExpressionException -> 0x00cb, MarshalException -> 0x00b5, XMLSignatureException -> 0x009f }
        L_0x009f:
            r8 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atError()
            org.apache.logging.log4j.LogBuilder r0 = r0.withThrowable(r8)
            java.lang.String r1 = "error in validating the signature"
            r0.log((java.lang.String) r1)
            org.apache.poi.EncryptedDocumentException r0 = new org.apache.poi.EncryptedDocumentException
            r0.<init>(r1, r8)
            throw r0
        L_0x00b5:
            r8 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atError()
            org.apache.logging.log4j.LogBuilder r0 = r0.withThrowable(r8)
            java.lang.String r1 = "error in unmarshalling the signature"
            r0.log((java.lang.String) r1)
            org.apache.poi.EncryptedDocumentException r0 = new org.apache.poi.EncryptedDocumentException
            r0.<init>(r1, r8)
            throw r0
        L_0x00cb:
            r8 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atError()
            org.apache.logging.log4j.LogBuilder r0 = r0.withThrowable(r8)
            java.lang.String r1 = "error in searching document with xpath expression"
            r0.log((java.lang.String) r1)
            org.apache.poi.EncryptedDocumentException r0 = new org.apache.poi.EncryptedDocumentException
            r0.<init>(r1, r8)
            throw r0
        L_0x00e1:
            r8 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atError()
            org.apache.logging.log4j.LogBuilder r0 = r0.withThrowable(r8)
            java.lang.String r1 = "error in parsing document"
            r0.log((java.lang.String) r1)
            org.apache.poi.EncryptedDocumentException r0 = new org.apache.poi.EncryptedDocumentException
            r0.<init>(r1, r8)
            throw r0
        L_0x00f7:
            r8 = move-exception
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atError()
            org.apache.logging.log4j.LogBuilder r0 = r0.withThrowable(r8)
            java.lang.String r1 = "error in reading document"
            r0.log((java.lang.String) r1)
            org.apache.poi.EncryptedDocumentException r0 = new org.apache.poi.EncryptedDocumentException
            r0.<init>(r1, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignaturePart.validate():boolean");
    }

    private void extractConfig(Document document, XMLSignature xMLSignature) throws XPathExpressionException {
        SignatureConfig signatureConfig = this.signatureInfo.getSignatureConfig();
        if (signatureConfig.isUpdateConfigOnValidate()) {
            signatureConfig.setSigningCertificateChain(this.certChain);
            signatureConfig.setSignatureMethodFromUri(xMLSignature.getSignedInfo().getSignatureMethod().getAlgorithm());
            XPath newXPath = XPathHelper.getFactory().newXPath();
            newXPath.setNamespaceContext(new XPathNSContext());
            HashMap hashMap = new HashMap();
            signatureConfig.getClass();
            hashMap.put("//mdssi:SignatureTime/mdssi:Value", new SignaturePart$$ExternalSyntheticLambda0(signatureConfig));
            signatureConfig.getClass();
            hashMap.put("//xd:ClaimedRole", new SignaturePart$$ExternalSyntheticLambda1(signatureConfig));
            signatureConfig.getClass();
            hashMap.put("//dsss:SignatureComments", new SignaturePart$$ExternalSyntheticLambda2(signatureConfig));
            signatureConfig.getClass();
            hashMap.put("//xd:QualifyingProperties//xd:SignedSignatureProperties//ds:DigestMethod/@Algorithm", new SignaturePart$$ExternalSyntheticLambda3(signatureConfig));
            signatureConfig.getClass();
            hashMap.put("//ds:CanonicalizationMethod", new SignaturePart$$ExternalSyntheticLambda4(signatureConfig));
            signatureConfig.getClass();
            hashMap.put("//xd:CommitmentTypeId/xd:Description", new SignaturePart$$ExternalSyntheticLambda5(signatureConfig));
            for (Map.Entry entry : hashMap.entrySet()) {
                ((Consumer) entry.getValue()).accept((String) newXPath.compile((String) entry.getKey()).evaluate(document, XPathConstants.STRING));
            }
        }
    }

    private class XPathNSContext implements NamespaceContext {
        final Map<String, String> nsMap;

        public String getPrefix(String str) {
            return null;
        }

        public Iterator getPrefixes(String str) {
            return null;
        }

        private XPathNSContext() {
            HashMap hashMap = new HashMap();
            this.nsMap = hashMap;
            SignaturePart.this.signatureInfo.getSignatureConfig().getNamespacePrefixes().forEach(new SignaturePart$XPathNSContext$$ExternalSyntheticLambda0(this));
            hashMap.put("dsss", SignatureFacet.MS_DIGSIG_NS);
            hashMap.put("ds", SignatureFacet.XML_DIGSIG_NS);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$org-apache-poi-poifs-crypt-dsig-SignaturePart$XPathNSContext  reason: not valid java name */
        public /* synthetic */ void m2222lambda$new$0$orgapachepoipoifscryptdsigSignaturePart$XPathNSContext(String str, String str2) {
            String put = this.nsMap.put(str2, str);
        }

        public String getNamespaceURI(String str) {
            return this.nsMap.get(str);
        }
    }
}
