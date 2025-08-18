package org.apache.poi.poifs.crypt.dsig;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RelationshipTransformService;
import org.apache.xml.security.Init;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MutationEvent;

public class SignatureInfo {
    /* access modifiers changed from: private */
    public static final Logger LOG = LogManager.getLogger((Class<?>) SignatureInfo.class);
    private KeyInfoFactory keyInfoFactory;
    /* access modifiers changed from: private */
    public OPCPackage opcPackage;
    private Provider provider;
    private SignatureConfig signatureConfig;
    private XMLSignatureFactory signatureFactory;
    private URIDereferencer uriDereferencer;

    public SignatureConfig getSignatureConfig() {
        return this.signatureConfig;
    }

    public void setSignatureConfig(SignatureConfig signatureConfig2) {
        this.signatureConfig = signatureConfig2;
    }

    public void setOpcPackage(OPCPackage oPCPackage) {
        this.opcPackage = oPCPackage;
    }

    public OPCPackage getOpcPackage() {
        return this.opcPackage;
    }

    public URIDereferencer getUriDereferencer() {
        return this.uriDereferencer;
    }

    public void setUriDereferencer(URIDereferencer uRIDereferencer) {
        this.uriDereferencer = uRIDereferencer;
    }

    public boolean verifySignature() {
        initXmlProvider();
        Iterator<SignaturePart> it = getSignatureParts().iterator();
        return it.hasNext() && it.next().validate();
    }

    public void confirmSignature() throws XMLSignatureException, MarshalException {
        initXmlProvider();
        DOMSignContext createXMLSignContext = createXMLSignContext(DocumentHelper.createDocument());
        postSign(createXMLSignContext, signDigest(createXMLSignContext, preSign(createXMLSignContext)));
    }

    public DOMSignContext createXMLSignContext(Document document) {
        initXmlProvider();
        return new DOMSignContext(this.signatureConfig.getKey(), document);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007c, code lost:
        if (r0 != null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0086, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String signDigest(javax.xml.crypto.dsig.dom.DOMSignContext r5, org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo r6) {
        /*
            r4 = this;
            r4.initXmlProvider()
            org.apache.poi.poifs.crypt.dsig.SignatureConfig r0 = r4.signatureConfig
            java.security.PrivateKey r0 = r0.getKey()
            org.apache.poi.poifs.crypt.dsig.SignatureConfig r1 = r4.signatureConfig
            org.apache.poi.poifs.crypt.HashAlgorithm r1 = r1.getDigestAlgo()
            int r2 = r1.hashSize
            int r2 = r2 * 4
            int r2 = r2 / 3
            r3 = 76
            if (r2 <= r3) goto L_0x0047
            boolean r2 = org.apache.xml.security.utils.XMLUtils.ignoreLineBreaks()
            if (r2 == 0) goto L_0x0020
            goto L_0x0047
        L_0x0020:
            org.apache.poi.EncryptedDocumentException r4 = new org.apache.poi.EncryptedDocumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "The hash size of the chosen hash algorithm ("
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r6 = " = "
            java.lang.StringBuilder r5 = r5.append(r6)
            int r6 = r1.hashSize
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = " bytes), will motivate XmlSec to add linebreaks to the generated digest, which results in an invalid signature (... at least for Office) - please persuade it otherwise by adding '-Dorg.apache.xml.security.ignoreLineBreaks=true' to the JVM system properties."
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>((java.lang.String) r5)
            throw r4
        L_0x0047:
            org.apache.poi.poifs.crypt.dsig.DigestOutputStream r0 = getDigestStream(r1, r0)     // Catch:{ IOException | GeneralSecurityException | TransformException -> 0x0087 }
            r0.init()     // Catch:{ all -> 0x0079 }
            org.w3c.dom.Node r1 = r5.getParent()     // Catch:{ all -> 0x0079 }
            org.w3c.dom.Document r1 = (org.w3c.dom.Document) r1     // Catch:{ all -> 0x0079 }
            java.lang.String r2 = "SignedInfo"
            org.w3c.dom.Element r4 = r4.getDsigElement(r1, r2)     // Catch:{ all -> 0x0079 }
            org.apache.jcp.xml.dsig.internal.dom.DOMSubTreeData r1 = new org.apache.jcp.xml.dsig.internal.dom.DOMSubTreeData     // Catch:{ all -> 0x0079 }
            r2 = 1
            r1.<init>(r4, r2)     // Catch:{ all -> 0x0079 }
            javax.xml.crypto.dsig.CanonicalizationMethod r4 = r6.getCanonicalizationMethod()     // Catch:{ all -> 0x0079 }
            r4.transform(r1, r5, r0)     // Catch:{ all -> 0x0079 }
            java.util.Base64$Encoder r4 = java.util.Base64.getEncoder()     // Catch:{ all -> 0x0079 }
            byte[] r5 = r0.sign()     // Catch:{ all -> 0x0079 }
            java.lang.String r4 = r4.encodeToString(r5)     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x0078
            r0.close()     // Catch:{ IOException | GeneralSecurityException | TransformException -> 0x0087 }
        L_0x0078:
            return r4
        L_0x0079:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x007b }
        L_0x007b:
            r5 = move-exception
            if (r0 == 0) goto L_0x0086
            r0.close()     // Catch:{ all -> 0x0082 }
            goto L_0x0086
        L_0x0082:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ IOException | GeneralSecurityException | TransformException -> 0x0087 }
        L_0x0086:
            throw r5     // Catch:{ IOException | GeneralSecurityException | TransformException -> 0x0087 }
        L_0x0087:
            r4 = move-exception
            org.apache.poi.EncryptedDocumentException r5 = new org.apache.poi.EncryptedDocumentException
            r5.<init>((java.lang.Throwable) r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignatureInfo.signDigest(javax.xml.crypto.dsig.dom.DOMSignContext, org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo):java.lang.String");
    }

    /* renamed from: org.apache.poi.poifs.crypt.dsig.SignatureInfo$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.poifs.crypt.HashAlgorithm[] r0 = org.apache.poi.poifs.crypt.HashAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm = r0
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.md2     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.md5     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha1     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha256     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha384     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha512     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignatureInfo.AnonymousClass1.<clinit>():void");
        }
    }

    private static DigestOutputStream getDigestStream(HashAlgorithm hashAlgorithm, PrivateKey privateKey) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm[hashAlgorithm.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new SignatureOutputStream(hashAlgorithm, privateKey);
            default:
                return new DigestOutputStream(hashAlgorithm, privateKey);
        }
    }

    public Iterable<SignaturePart> getSignatureParts() {
        initXmlProvider();
        return new SignatureInfo$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getSignatureParts$0$org-apache-poi-poifs-crypt-dsig-SignatureInfo  reason: not valid java name */
    public /* synthetic */ Iterator m2218lambda$getSignatureParts$0$orgapachepoipoifscryptdsigSignatureInfo() {
        return new SignaturePartIterator(this, (AnonymousClass1) null);
    }

    private final class SignaturePartIterator implements Iterator<SignaturePart> {
        Iterator<PackageRelationship> sigOrigRels;
        private PackagePart sigPart;
        private Iterator<PackageRelationship> sigRels;

        /* synthetic */ SignaturePartIterator(SignatureInfo signatureInfo, AnonymousClass1 r2) {
            this();
        }

        private SignaturePartIterator() {
            this.sigOrigRels = SignatureInfo.this.opcPackage.getRelationshipsByType(PackageRelationshipTypes.DIGITAL_SIGNATURE_ORIGIN).iterator();
        }

        public boolean hasNext() {
            while (true) {
                Iterator<PackageRelationship> it = this.sigRels;
                if (it != null && it.hasNext()) {
                    return true;
                }
                if (!this.sigOrigRels.hasNext()) {
                    return false;
                }
                this.sigPart = SignatureInfo.this.opcPackage.getPart(this.sigOrigRels.next());
                SignatureInfo.LOG.atDebug().log("Digital Signature Origin part: {}", (Object) this.sigPart);
                try {
                    this.sigRels = this.sigPart.getRelationshipsByType(PackageRelationshipTypes.DIGITAL_SIGNATURE).iterator();
                } catch (InvalidFormatException e) {
                    SignatureInfo.LOG.atWarn().withThrowable(e).log("Reference to signature is invalid.");
                }
            }
        }

        public SignaturePart next() {
            PackagePart packagePart = null;
            do {
                try {
                    if (hasNext()) {
                        packagePart = this.sigPart.getRelatedPart(this.sigRels.next());
                        SignatureInfo.LOG.atDebug().log("XML Signature part: {}", (Object) packagePart);
                        continue;
                    } else {
                        throw new NoSuchElementException();
                    }
                } catch (InvalidFormatException e) {
                    SignatureInfo.LOG.atWarn().withThrowable(e).log("Reference to signature is invalid.");
                    continue;
                }
            } while (packagePart == null);
            return new SignaturePart(packagePart, SignatureInfo.this);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public DOMSignedInfo preSign(DOMSignContext dOMSignContext) throws XMLSignatureException, MarshalException {
        Document document = (Document) dOMSignContext.getParent();
        registerEventListener(document);
        URIDereferencer uRIDereferencer = this.uriDereferencer;
        if (uRIDereferencer != null) {
            dOMSignContext.setURIDereferencer(uRIDereferencer);
        }
        Map<String, String> namespacePrefixes = this.signatureConfig.getNamespacePrefixes();
        dOMSignContext.getClass();
        namespacePrefixes.forEach(new SignatureInfo$$ExternalSyntheticLambda2(dOMSignContext));
        dOMSignContext.setDefaultNamespacePrefix("");
        ArrayList arrayList = new ArrayList();
        ArrayList<XMLObject> arrayList2 = new ArrayList<>();
        for (SignatureFacet next : this.signatureConfig.getSignatureFacets()) {
            LOG.atDebug().log("invoking signature facet: {}", (Object) next.getClass().getSimpleName());
            next.preSign(this, document, arrayList, arrayList2);
        }
        try {
            C14NMethodParameterSpec c14NMethodParameterSpec = null;
            DOMSignedInfo newSignedInfo = this.signatureFactory.newSignedInfo(this.signatureFactory.newCanonicalizationMethod(this.signatureConfig.getCanonicalizationMethod(), (C14NMethodParameterSpec) null), this.signatureFactory.newSignatureMethod(this.signatureConfig.getSignatureMethodUri(), (SignatureMethodParameterSpec) null), arrayList);
            this.signatureFactory.newXMLSignature(newSignedInfo, (KeyInfo) null, arrayList2, this.signatureConfig.getPackageSignatureId(), this.signatureConfig.getPackageSignatureId() + "-signature-value").sign(dOMSignContext);
            for (XMLObject xMLObject : arrayList2) {
                LOG.atDebug().log("object java type: {}", (Object) xMLObject.getClass().getName());
                for (Manifest manifest : xMLObject.getContent()) {
                    LOG.atDebug().log("object content java type: {}", (Object) manifest.getClass().getName());
                    if (manifest instanceof Manifest) {
                        for (DOMReference dOMReference : manifest.getReferences()) {
                            if (dOMReference.getDigestValue() == null) {
                                dOMReference.digest(dOMSignContext);
                            }
                        }
                    }
                }
            }
            for (DOMReference dOMReference2 : newSignedInfo.getReferences()) {
                if (dOMReference2.getDigestValue() == null) {
                    dOMReference2.digest(dOMSignContext);
                }
            }
            return newSignedInfo;
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void registerEventListener(Document document) {
        SignatureMarshalListener signatureMarshalListener = this.signatureConfig.getSignatureMarshalListener();
        if (signatureMarshalListener != null) {
            EventListener[] eventListenerArr = {null};
            EventTarget eventTarget = (EventTarget) document;
            SignatureInfo$$ExternalSyntheticLambda3 signatureInfo$$ExternalSyntheticLambda3 = new SignatureInfo$$ExternalSyntheticLambda3(this, eventTarget, eventListenerArr, signatureMarshalListener, document);
            eventListenerArr[0] = signatureInfo$$ExternalSyntheticLambda3;
            eventTarget.addEventListener("DOMSubtreeModified", signatureInfo$$ExternalSyntheticLambda3, false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerEventListener$1$org-apache-poi-poifs-crypt-dsig-SignatureInfo  reason: not valid java name */
    public /* synthetic */ void m2219lambda$registerEventListener$1$orgapachepoipoifscryptdsigSignatureInfo(EventTarget eventTarget, EventListener[] eventListenerArr, SignatureMarshalListener signatureMarshalListener, Document document, Event event) {
        if ((event instanceof MutationEvent) && (event.getTarget() instanceof Document)) {
            eventTarget.removeEventListener("DOMSubtreeModified", eventListenerArr[0], false);
            signatureMarshalListener.handleElement(this, document, eventTarget, eventListenerArr[0]);
            eventTarget.addEventListener("DOMSubtreeModified", eventListenerArr[0], false);
        }
    }

    public void postSign(DOMSignContext dOMSignContext, String str) throws MarshalException {
        LOG.atDebug().log("postSign");
        Document document = (Document) dOMSignContext.getParent();
        String packageSignatureId = this.signatureConfig.getPackageSignatureId();
        if (packageSignatureId.equals(document.getDocumentElement().getAttribute(PackageRelationship.ID_ATTRIBUTE_NAME))) {
            Element dsigElement = getDsigElement(document, "SignatureValue");
            if (dsigElement != null) {
                dsigElement.setTextContent(str);
                for (SignatureFacet postSign : this.signatureConfig.getSignatureFacets()) {
                    postSign.postSign(this, document);
                }
                writeDocument(document);
                return;
            }
            throw new RuntimeException("preSign has to be called before postSign");
        }
        throw new RuntimeException("ds:Signature not found for @Id: " + packageSignatureId);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f2, code lost:
        if (r10 != null) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fc, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeDocument(org.w3c.dom.Document r11) throws javax.xml.crypto.MarshalException {
        /*
            r10 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            org.apache.poi.poifs.crypt.dsig.SignatureConfig r2 = r10.signatureConfig
            java.util.Map r2 = r2.getNamespacePrefixes()
            org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda1 r3 = new org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda1
            r3.<init>(r1)
            r2.forEach(r3)
            r0.setSaveSuggestedPrefixes(r1)
            r0.setUseDefaultNamespace()
            org.apache.logging.log4j.Logger r1 = LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atDebug()
            java.lang.String r2 = "output signed Office OpenXML document"
            r1.log((java.lang.String) r2)
            org.apache.poi.poifs.crypt.dsig.DSigRelation r1 = org.apache.poi.poifs.crypt.dsig.DSigRelation.ORIGIN_SIGS     // Catch:{ Exception -> 0x00fd }
            r2 = 0
            java.lang.String r2 = r1.getFileName(r2)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePartName r2 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r2)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.OPCPackage r3 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r3 = r3.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r2)     // Catch:{ Exception -> 0x00fd }
            if (r3 != 0) goto L_0x0051
            org.apache.poi.openxml4j.opc.OPCPackage r3 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            java.lang.String r4 = r1.getContentType()     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r3 = r3.createPart(r2, r4)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.OPCPackage r4 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.TargetMode r5 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ Exception -> 0x00fd }
            java.lang.String r1 = r1.getRelation()     // Catch:{ Exception -> 0x00fd }
            r4.addRelationship(r2, r5, r1)     // Catch:{ Exception -> 0x00fd }
        L_0x0051:
            org.apache.poi.poifs.crypt.dsig.DSigRelation r1 = org.apache.poi.poifs.crypt.dsig.DSigRelation.SIG     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.OPCPackage r2 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            java.lang.String r4 = r1.getDefaultFileName()     // Catch:{ Exception -> 0x00fd }
            int r2 = r2.getUnusedPartIndex(r4)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.poifs.crypt.dsig.SignatureConfig r4 = r10.signatureConfig     // Catch:{ Exception -> 0x00fd }
            boolean r4 = r4.isAllowMultipleSignatures()     // Catch:{ Exception -> 0x00fd }
            if (r4 != 0) goto L_0x00b1
            java.lang.String r4 = r1.getRelation()     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r4 = r3.getRelationshipsByType(r4)     // Catch:{ Exception -> 0x00fd }
            r5 = 2
        L_0x006e:
            if (r5 >= r2) goto L_0x00b0
            java.lang.String r6 = r1.getFileName(r5)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePartName r6 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r6)     // Catch:{ Exception -> 0x00fd }
            java.util.Iterator r7 = r4.iterator()     // Catch:{ Exception -> 0x00fd }
        L_0x007c:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x00fd }
            if (r8 == 0) goto L_0x00a4
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackageRelationship r8 = (org.apache.poi.openxml4j.opc.PackageRelationship) r8     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r9 = r3.getRelatedPart(r8)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePartName r9 = r9.getPartName()     // Catch:{ Exception -> 0x00fd }
            boolean r9 = r9.equals(r6)     // Catch:{ Exception -> 0x00fd }
            if (r9 == 0) goto L_0x007c
            java.lang.String r7 = r8.getId()     // Catch:{ Exception -> 0x00fd }
            r3.removeRelationship(r7)     // Catch:{ Exception -> 0x00fd }
            java.lang.String r7 = r8.getId()     // Catch:{ Exception -> 0x00fd }
            r4.removeRelationship(r7)     // Catch:{ Exception -> 0x00fd }
        L_0x00a4:
            org.apache.poi.openxml4j.opc.OPCPackage r7 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r6 = r7.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r6)     // Catch:{ Exception -> 0x00fd }
            r7.removePart((org.apache.poi.openxml4j.opc.PackagePart) r6)     // Catch:{ Exception -> 0x00fd }
            int r5 = r5 + 1
            goto L_0x006e
        L_0x00b0:
            r2 = 1
        L_0x00b1:
            java.lang.String r2 = r1.getFileName(r2)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePartName r2 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r2)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.OPCPackage r4 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r4 = r4.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r2)     // Catch:{ Exception -> 0x00fd }
            if (r4 != 0) goto L_0x00d5
            org.apache.poi.openxml4j.opc.OPCPackage r10 = r10.opcPackage     // Catch:{ Exception -> 0x00fd }
            java.lang.String r4 = r1.getContentType()     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.PackagePart r4 = r10.createPart(r2, r4)     // Catch:{ Exception -> 0x00fd }
            org.apache.poi.openxml4j.opc.TargetMode r10 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ Exception -> 0x00fd }
            java.lang.String r1 = r1.getRelation()     // Catch:{ Exception -> 0x00fd }
            r3.addRelationship((org.apache.poi.openxml4j.opc.PackagePartName) r2, (org.apache.poi.openxml4j.opc.TargetMode) r10, (java.lang.String) r1)     // Catch:{ Exception -> 0x00fd }
            goto L_0x00d8
        L_0x00d5:
            r4.clear()     // Catch:{ Exception -> 0x00fd }
        L_0x00d8:
            java.io.OutputStream r10 = r4.getOutputStream()     // Catch:{ Exception -> 0x00fd }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.w3.x2000.x09.xmldsig.SignatureDocument> r1 = org.w3.x2000.x09.xmldsig.SignatureDocument.Factory     // Catch:{ all -> 0x00ef }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00ef }
            java.lang.Object r11 = r1.parse((org.w3c.dom.Node) r11, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x00ef }
            org.w3.x2000.x09.xmldsig.SignatureDocument r11 = (org.w3.x2000.x09.xmldsig.SignatureDocument) r11     // Catch:{ all -> 0x00ef }
            r11.save((java.io.OutputStream) r10, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x00ef }
            if (r10 == 0) goto L_0x00ee
            r10.close()     // Catch:{ Exception -> 0x00fd }
        L_0x00ee:
            return
        L_0x00ef:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00f1 }
        L_0x00f1:
            r0 = move-exception
            if (r10 == 0) goto L_0x00fc
            r10.close()     // Catch:{ all -> 0x00f8 }
            goto L_0x00fc
        L_0x00f8:
            r10 = move-exception
            r11.addSuppressed(r10)     // Catch:{ Exception -> 0x00fd }
        L_0x00fc:
            throw r0     // Catch:{ Exception -> 0x00fd }
        L_0x00fd:
            r10 = move-exception
            javax.xml.crypto.MarshalException r11 = new javax.xml.crypto.MarshalException
            java.lang.String r0 = "Unable to write signature document"
            r11.<init>(r0, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignatureInfo.writeDocument(org.w3c.dom.Document):void");
    }

    static /* synthetic */ void lambda$writeDocument$2(Map map, String str, String str2) {
        String str3 = (String) map.put(str2, str);
    }

    private Element getDsigElement(Document document, String str) {
        NodeList elementsByTagNameNS = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, str);
        if (elementsByTagNameNS.getLength() == 1) {
            return (Element) elementsByTagNameNS.item(0);
        }
        LOG.atWarn().log("Signature element '{}' was {}", str, elementsByTagNameNS.getLength() == 0 ? "not found" : "multiple times");
        return null;
    }

    public void setProvider(Provider provider2) {
        this.provider = provider2;
    }

    public void setSignatureFactory(XMLSignatureFactory xMLSignatureFactory) {
        this.signatureFactory = xMLSignatureFactory;
    }

    public XMLSignatureFactory getSignatureFactory() {
        return this.signatureFactory;
    }

    public void setKeyInfoFactory(KeyInfoFactory keyInfoFactory2) {
        this.keyInfoFactory = keyInfoFactory2;
    }

    public KeyInfoFactory getKeyInfoFactory() {
        return this.keyInfoFactory;
    }

    /* access modifiers changed from: protected */
    public void initXmlProvider() {
        if (this.opcPackage == null) {
            this.opcPackage = this.signatureConfig.getOpcPackage();
        }
        if (this.provider == null) {
            Provider provider2 = this.signatureConfig.getProvider();
            this.provider = provider2;
            if (provider2 == null) {
                this.provider = XmlProviderInitSingleton.getInstance().findProvider();
            }
        }
        if (this.signatureFactory == null) {
            XMLSignatureFactory signatureFactory2 = this.signatureConfig.getSignatureFactory();
            this.signatureFactory = signatureFactory2;
            if (signatureFactory2 == null) {
                this.signatureFactory = XMLSignatureFactory.getInstance("DOM", this.provider);
            }
        }
        if (this.keyInfoFactory == null) {
            KeyInfoFactory keyInfoFactory2 = this.signatureConfig.getKeyInfoFactory();
            this.keyInfoFactory = keyInfoFactory2;
            if (keyInfoFactory2 == null) {
                this.keyInfoFactory = KeyInfoFactory.getInstance("DOM", this.provider);
            }
        }
        if (this.uriDereferencer == null) {
            URIDereferencer uriDereferencer2 = this.signatureConfig.getUriDereferencer();
            this.uriDereferencer = uriDereferencer2;
            if (uriDereferencer2 == null) {
                this.uriDereferencer = new OOXMLURIDereferencer();
            }
        }
        OOXMLURIDereferencer oOXMLURIDereferencer = this.uriDereferencer;
        if (oOXMLURIDereferencer instanceof OOXMLURIDereferencer) {
            oOXMLURIDereferencer.setSignatureInfo(this);
        }
    }

    private static final class XmlProviderInitSingleton {
        /* synthetic */ XmlProviderInitSingleton(AnonymousClass1 r1) {
            this();
        }

        private static class SingletonHelper {
            /* access modifiers changed from: private */
            public static final XmlProviderInitSingleton INSTANCE = new XmlProviderInitSingleton((AnonymousClass1) null);

            private SingletonHelper() {
            }
        }

        public static XmlProviderInitSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }

        private XmlProviderInitSingleton() {
            try {
                Init.init();
                RelationshipTransformService.registerDsigProvider();
                CryptoFunctions.registerBouncyCastle();
            } catch (Exception e) {
                throw new RuntimeException("Xml & BouncyCastle-Provider initialization failed", e);
            }
        }

        public Provider findProvider() {
            return (Provider) Stream.of(SignatureConfig.getProviderNames()).map(new SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda0(this)).filter(new SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda1()).findFirst().orElseThrow(new SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: private */
        public Provider getProvider(String str) {
            try {
                return (Provider) Class.forName(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception unused) {
                SignatureInfo.LOG.atDebug().log("XMLDsig-Provider '{}' can't be found - trying next.", (Object) str);
                return null;
            }
        }

        /* access modifiers changed from: private */
        public RuntimeException providerNotFound() {
            return new RuntimeException("JRE doesn't support default xml signature provider - set jsr105Provider system property!");
        }
    }
}
