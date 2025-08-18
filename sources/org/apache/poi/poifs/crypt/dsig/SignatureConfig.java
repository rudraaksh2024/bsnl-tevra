package org.apache.poi.poifs.crypt.dsig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampServiceValidator;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;

public class SignatureConfig {
    private static final List<Supplier<SignatureFacet>> DEFAULT_FACETS = Arrays.asList(new Supplier[]{new SignatureConfig$$ExternalSyntheticLambda3(), new SignatureConfig$$ExternalSyntheticLambda4(), new SignatureConfig$$ExternalSyntheticLambda5(), new SignatureConfig$$ExternalSyntheticLambda6()});
    private static final String DigestMethod_SHA224 = "http://www.w3.org/2001/04/xmldsig-more#sha224";
    private static final String DigestMethod_SHA384 = "http://www.w3.org/2001/04/xmldsig-more#sha384";
    private static final Logger LOG = LogManager.getLogger((Class<?>) SignatureConfig.class);
    public static final String SIGNATURE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String XMLSEC_JDK = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
    private static final String XMLSEC_SANTUARIO = "org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI";
    private boolean allowCRLDownload;
    private boolean allowMultipleSignatures;
    private String canonicalizationMethod = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
    private String commitmentType;
    private final List<CRLEntry> crlEntries;
    private HashAlgorithm digestAlgo = HashAlgorithm.sha256;
    private Date executionTime = new Date();
    private boolean includeEntireCertificateChain = true;
    private boolean includeIssuerSerial;
    private boolean includeKeyValue;
    private PrivateKey key;
    private final ThreadLocal<KeyInfoFactory> keyInfoFactory = new ThreadLocal<>();
    private final KeyStore keyStore;
    private final Map<String, String> namespacePrefixes;
    private final ThreadLocal<OPCPackage> opcPackage = new ThreadLocal<>();
    private String packageSignatureId = "idPackageSignature";
    private final ThreadLocal<Provider> provider = new ThreadLocal<>();
    private String proxyUrl;
    private RevocationDataService revocationDataService;
    private boolean secureValidation;
    private String signatureDescription = "Office OpenXML Document";
    private List<SignatureFacet> signatureFacets = new ArrayList();
    private final ThreadLocal<XMLSignatureFactory> signatureFactory = new ThreadLocal<>();
    private byte[] signatureImage;
    private byte[] signatureImageInvalid;
    private ClassID signatureImageSetupId;
    private byte[] signatureImageValid;
    private SignatureMarshalListener signatureMarshalListener = new SignatureMarshalDefaultListener();
    private SignaturePolicyService signaturePolicyService;
    private List<X509Certificate> signingCertificateChain;
    private HashAlgorithm tspDigestAlgo;
    private TimeStampHttpClient tspHttpClient = new TimeStampSimpleHttpClient();
    private boolean tspOldProtocol;
    private String tspPass;
    private String tspRequestPolicy = "1.3.6.1.4.1.13762.3";
    private TimeStampService tspService = new TSPTimeStampService();
    private String tspUrl;
    private String tspUser;
    private TimeStampServiceValidator tspValidator;
    private boolean updateConfigOnValidate;
    private URIDereferencer uriDereferencer = new OOXMLURIDereferencer();
    private String userAgent = "POI XmlSign Service TSP Client";
    private String xadesCanonicalizationMethod = "http://www.w3.org/2001/10/xml-exc-c14n#";
    private HashAlgorithm xadesDigestAlgo;
    private boolean xadesIssuerNameNoReverseOrder = true;
    private String xadesRole;
    private String xadesSignatureId = "idSignedProperties";
    private boolean xadesSignaturePolicyImplied = true;

    private static <T> T nvl(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static class CRLEntry {
        private final String certCN;
        private final byte[] crlBytes;
        private final String crlURL;

        public CRLEntry(String str, String str2, byte[] bArr) {
            this.crlURL = str;
            this.certCN = str2;
            this.crlBytes = bArr;
        }

        public String getCrlURL() {
            return this.crlURL;
        }

        public String getCertCN() {
            return this.certCN;
        }

        public byte[] getCrlBytes() {
            return this.crlBytes;
        }
    }

    public SignatureConfig() {
        HashMap hashMap = new HashMap();
        this.namespacePrefixes = hashMap;
        this.updateConfigOnValidate = false;
        this.allowMultipleSignatures = false;
        this.secureValidation = true;
        this.commitmentType = "Created and approved this document";
        this.allowCRLDownload = false;
        this.crlEntries = new ArrayList();
        this.keyStore = emptyKeyStore();
        hashMap.put("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi");
        hashMap.put(SignatureFacet.XADES_132_NS, "xd");
    }

    public void addSignatureFacet(SignatureFacet signatureFacet) {
        this.signatureFacets.add(signatureFacet);
    }

    public List<SignatureFacet> getSignatureFacets() {
        if (this.signatureFacets.isEmpty()) {
            return (List) DEFAULT_FACETS.stream().map(new SignatureConfig$$ExternalSyntheticLambda2()).collect(Collectors.toList());
        }
        return this.signatureFacets;
    }

    public void setSignatureFacets(List<SignatureFacet> list) {
        this.signatureFacets = list;
    }

    public HashAlgorithm getDigestAlgo() {
        return this.digestAlgo;
    }

    public void setDigestAlgo(HashAlgorithm hashAlgorithm) {
        this.digestAlgo = hashAlgorithm;
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public OPCPackage getOpcPackage() {
        return this.opcPackage.get();
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public void setOpcPackage(OPCPackage oPCPackage) {
        this.opcPackage.set(oPCPackage);
    }

    public PrivateKey getKey() {
        return this.key;
    }

    public void setKey(PrivateKey privateKey) {
        this.key = privateKey;
    }

    public List<X509Certificate> getSigningCertificateChain() {
        return this.signingCertificateChain;
    }

    public void setSigningCertificateChain(List<X509Certificate> list) {
        this.signingCertificateChain = list;
    }

    public Date getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(Date date) {
        this.executionTime = date;
    }

    public String formatExecutionTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
        simpleDateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
        return simpleDateFormat.format(getExecutionTime());
    }

    public void setExecutionTime(String str) {
        if (str != null && !"".equals(str)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
            simpleDateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
            try {
                this.executionTime = simpleDateFormat.parse(str);
            } catch (ParseException unused) {
                LOG.atWarn().log("Illegal execution time: {}. Must be formatted as yyyy-MM-dd'T'HH:mm:ss'Z'", (Object) str);
            }
        }
    }

    public SignaturePolicyService getSignaturePolicyService() {
        return this.signaturePolicyService;
    }

    public void setSignaturePolicyService(SignaturePolicyService signaturePolicyService2) {
        this.signaturePolicyService = signaturePolicyService2;
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public URIDereferencer getUriDereferencer() {
        return this.uriDereferencer;
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public void setUriDereferencer(URIDereferencer uRIDereferencer) {
        this.uriDereferencer = uRIDereferencer;
    }

    public String getSignatureDescription() {
        return this.signatureDescription;
    }

    public void setSignatureDescription(String str) {
        this.signatureDescription = str;
    }

    public byte[] getSignatureImage() {
        return this.signatureImage;
    }

    public byte[] getSignatureImageValid() {
        return this.signatureImageValid;
    }

    public byte[] getSignatureImageInvalid() {
        return this.signatureImageInvalid;
    }

    public ClassID getSignatureImageSetupId() {
        return this.signatureImageSetupId;
    }

    public void setSignatureImageSetupId(ClassID classID) {
        this.signatureImageSetupId = classID;
    }

    public void setSignatureImage(byte[] bArr) {
        this.signatureImage = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setSignatureImageValid(byte[] bArr) {
        this.signatureImageValid = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void setSignatureImageInvalid(byte[] bArr) {
        this.signatureImageInvalid = bArr == null ? null : (byte[]) bArr.clone();
    }

    public String getCanonicalizationMethod() {
        return this.canonicalizationMethod;
    }

    public void setCanonicalizationMethod(String str) {
        this.canonicalizationMethod = verifyCanonicalizationMethod(str, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
    }

    private static String verifyCanonicalizationMethod(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return str2;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2012395451:
                if (str.equals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315")) {
                    c = 0;
                    break;
                }
                break;
            case -785330953:
                if (str.equals("http://www.w3.org/2000/09/xmldsig#enveloped-signature")) {
                    c = 1;
                    break;
                }
                break;
            case -549269964:
                if (str.equals("http://www.w3.org/2001/10/xml-exc-c14n#")) {
                    c = 2;
                    break;
                }
                break;
            case 246158456:
                if (str.equals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments")) {
                    c = 3;
                    break;
                }
                break;
            case 1783513390:
                if (str.equals("http://www.w3.org/2001/10/xml-exc-c14n#WithComments")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return str;
            default:
                throw new EncryptedDocumentException("Unknown CanonicalizationMethod: " + str);
        }
    }

    public String getPackageSignatureId() {
        return this.packageSignatureId;
    }

    public void setPackageSignatureId(String str) {
        this.packageSignatureId = (String) nvl(str, "xmldsig-" + UUID.randomUUID());
    }

    public String getTspUrl() {
        return this.tspUrl;
    }

    public void setTspUrl(String str) {
        this.tspUrl = str;
    }

    public boolean isTspOldProtocol() {
        return this.tspOldProtocol;
    }

    public void setTspOldProtocol(boolean z) {
        this.tspOldProtocol = z;
    }

    public HashAlgorithm getTspDigestAlgo() {
        return (HashAlgorithm) nvl(this.tspDigestAlgo, this.digestAlgo);
    }

    public void setTspDigestAlgo(HashAlgorithm hashAlgorithm) {
        this.tspDigestAlgo = hashAlgorithm;
    }

    public String getProxyUrl() {
        return this.proxyUrl;
    }

    public void setProxyUrl(String str) {
        this.proxyUrl = str;
    }

    public TimeStampService getTspService() {
        return this.tspService;
    }

    public void setTspService(TimeStampService timeStampService) {
        this.tspService = timeStampService;
    }

    public TimeStampHttpClient getTspHttpClient() {
        return this.tspHttpClient;
    }

    public void setTspHttpClient(TimeStampHttpClient timeStampHttpClient) {
        this.tspHttpClient = timeStampHttpClient;
    }

    public String getTspUser() {
        return this.tspUser;
    }

    public void setTspUser(String str) {
        this.tspUser = str;
    }

    public String getTspPass() {
        return this.tspPass;
    }

    public void setTspPass(String str) {
        this.tspPass = str;
    }

    public TimeStampServiceValidator getTspValidator() {
        return this.tspValidator;
    }

    public void setTspValidator(TimeStampServiceValidator timeStampServiceValidator) {
        this.tspValidator = timeStampServiceValidator;
    }

    public RevocationDataService getRevocationDataService() {
        return this.revocationDataService;
    }

    public void setRevocationDataService(RevocationDataService revocationDataService2) {
        this.revocationDataService = revocationDataService2;
    }

    public HashAlgorithm getXadesDigestAlgo() {
        return (HashAlgorithm) nvl(this.xadesDigestAlgo, this.digestAlgo);
    }

    public void setXadesDigestAlgo(HashAlgorithm hashAlgorithm) {
        this.xadesDigestAlgo = hashAlgorithm;
    }

    public void setXadesDigestAlgo(String str) {
        this.xadesDigestAlgo = getDigestMethodAlgo(str);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public String getTspRequestPolicy() {
        return this.tspRequestPolicy;
    }

    public void setTspRequestPolicy(String str) {
        this.tspRequestPolicy = str;
    }

    public boolean isIncludeEntireCertificateChain() {
        return this.includeEntireCertificateChain;
    }

    public void setIncludeEntireCertificateChain(boolean z) {
        this.includeEntireCertificateChain = z;
    }

    public boolean isIncludeIssuerSerial() {
        return this.includeIssuerSerial;
    }

    public void setIncludeIssuerSerial(boolean z) {
        this.includeIssuerSerial = z;
    }

    public boolean isIncludeKeyValue() {
        return this.includeKeyValue;
    }

    public void setIncludeKeyValue(boolean z) {
        this.includeKeyValue = z;
    }

    public String getXadesRole() {
        return this.xadesRole;
    }

    public void setXadesRole(String str) {
        this.xadesRole = str;
    }

    public String getXadesSignatureId() {
        return (String) nvl(this.xadesSignatureId, "idSignedProperties");
    }

    public void setXadesSignatureId(String str) {
        this.xadesSignatureId = str;
    }

    public boolean isXadesSignaturePolicyImplied() {
        return this.xadesSignaturePolicyImplied;
    }

    public void setXadesSignaturePolicyImplied(boolean z) {
        this.xadesSignaturePolicyImplied = z;
    }

    public boolean isXadesIssuerNameNoReverseOrder() {
        return this.xadesIssuerNameNoReverseOrder;
    }

    public void setXadesIssuerNameNoReverseOrder(boolean z) {
        this.xadesIssuerNameNoReverseOrder = z;
    }

    public SignatureMarshalListener getSignatureMarshalListener() {
        return this.signatureMarshalListener;
    }

    public void setSignatureMarshalListener(SignatureMarshalListener signatureMarshalListener2) {
        this.signatureMarshalListener = signatureMarshalListener2;
    }

    public Map<String, String> getNamespacePrefixes() {
        return this.namespacePrefixes;
    }

    public void setNamespacePrefixes(Map<String, String> map) {
        this.namespacePrefixes.clear();
        this.namespacePrefixes.putAll(map);
    }

    /* renamed from: org.apache.poi.poifs.crypt.dsig.SignatureConfig$1  reason: invalid class name */
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
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha224     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha256     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha384     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha512     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.ripemd160     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.SignatureConfig.AnonymousClass1.<clinit>():void");
        }
    }

    public String getSignatureMethodUri() {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm[getDigestAlgo().ordinal()]) {
            case 1:
                return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
            case 2:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha224";
            case 3:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
            case 4:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384";
            case 5:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512";
            case 6:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160";
            default:
                throw new EncryptedDocumentException("Hash algorithm " + getDigestAlgo() + " not supported for signing.");
        }
    }

    public String getDigestMethodUri() {
        return getDigestMethodUri(getDigestAlgo());
    }

    public static String getDigestMethodUri(HashAlgorithm hashAlgorithm) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm[hashAlgorithm.ordinal()]) {
            case 1:
                return "http://www.w3.org/2000/09/xmldsig#sha1";
            case 2:
                return DigestMethod_SHA224;
            case 3:
                return "http://www.w3.org/2001/04/xmlenc#sha256";
            case 4:
                return DigestMethod_SHA384;
            case 5:
                return "http://www.w3.org/2001/04/xmlenc#sha512";
            case 6:
                return "http://www.w3.org/2001/04/xmlenc#ripemd160";
            default:
                throw new EncryptedDocumentException("Hash algorithm " + hashAlgorithm + " not supported for signing.");
        }
    }

    private static HashAlgorithm getDigestMethodAlgo(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1000393448:
                if (str.equals("http://www.w3.org/2001/04/xmlenc#sha256")) {
                    c = 0;
                    break;
                }
                break;
            case -1000390693:
                if (str.equals("http://www.w3.org/2001/04/xmlenc#sha512")) {
                    c = 1;
                    break;
                }
                break;
            case 1060036557:
                if (str.equals("http://www.w3.org/2000/09/xmldsig#sha1")) {
                    c = 2;
                    break;
                }
                break;
            case 1253031479:
                if (str.equals("http://www.w3.org/2001/04/xmlenc#ripemd160")) {
                    c = 3;
                    break;
                }
                break;
            case 2029689854:
                if (str.equals(DigestMethod_SHA224)) {
                    c = 4;
                    break;
                }
                break;
            case 2029691001:
                if (str.equals(DigestMethod_SHA384)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return HashAlgorithm.sha256;
            case 1:
                return HashAlgorithm.sha512;
            case 2:
                return HashAlgorithm.sha1;
            case 3:
                return HashAlgorithm.ripemd160;
            case 4:
                return HashAlgorithm.sha224;
            case 5:
                return HashAlgorithm.sha384;
            default:
                throw new EncryptedDocumentException("Hash algorithm " + str + " not supported for signing.");
        }
    }

    public void setSignatureMethodFromUri(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -871953275:
                if (str.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160")) {
                    c = 0;
                    break;
                }
                break;
            case -699582165:
                if (str.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha224")) {
                    c = 1;
                    break;
                }
                break;
            case -699582070:
                if (str.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256")) {
                    c = 2;
                    break;
                }
                break;
            case -699581018:
                if (str.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha384")) {
                    c = 3;
                    break;
                }
                break;
            case -699579315:
                if (str.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha512")) {
                    c = 4;
                    break;
                }
                break;
            case 670108474:
                if (str.equals("http://www.w3.org/2000/09/xmldsig#rsa-sha1")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                setDigestAlgo(HashAlgorithm.ripemd160);
                return;
            case 1:
                setDigestAlgo(HashAlgorithm.sha224);
                return;
            case 2:
                setDigestAlgo(HashAlgorithm.sha256);
                return;
            case 3:
                setDigestAlgo(HashAlgorithm.sha384);
                return;
            case 4:
                setDigestAlgo(HashAlgorithm.sha512);
                return;
            case 5:
                setDigestAlgo(HashAlgorithm.sha1);
                return;
            default:
                throw new EncryptedDocumentException("Hash algorithm " + str + " not supported.");
        }
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public void setSignatureFactory(XMLSignatureFactory xMLSignatureFactory) {
        this.signatureFactory.set(xMLSignatureFactory);
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public XMLSignatureFactory getSignatureFactory() {
        return this.signatureFactory.get();
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public void setKeyInfoFactory(KeyInfoFactory keyInfoFactory2) {
        this.keyInfoFactory.set(keyInfoFactory2);
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public KeyInfoFactory getKeyInfoFactory() {
        return this.keyInfoFactory.get();
    }

    @Internal
    @Deprecated
    @Removal(version = "5.0.0")
    public void setProvider(Provider provider2) {
        this.provider.set(provider2);
    }

    @Deprecated
    @Removal(version = "5.0.0")
    public Provider getProvider() {
        return this.provider.get();
    }

    public static String[] getProviderNames() {
        String property = System.getProperty("jsr105Provider");
        if (property == null || "".equals(property)) {
            return new String[]{XMLSEC_SANTUARIO, XMLSEC_JDK};
        }
        return new String[]{property, XMLSEC_SANTUARIO, XMLSEC_JDK};
    }

    public String getXadesCanonicalizationMethod() {
        return this.xadesCanonicalizationMethod;
    }

    public void setXadesCanonicalizationMethod(String str) {
        this.xadesCanonicalizationMethod = verifyCanonicalizationMethod(str, "http://www.w3.org/2001/10/xml-exc-c14n#");
    }

    public boolean isUpdateConfigOnValidate() {
        return this.updateConfigOnValidate;
    }

    public void setUpdateConfigOnValidate(boolean z) {
        this.updateConfigOnValidate = z;
    }

    public boolean isAllowMultipleSignatures() {
        return this.allowMultipleSignatures;
    }

    public void setAllowMultipleSignatures(boolean z) {
        this.allowMultipleSignatures = z;
    }

    public boolean isSecureValidation() {
        return this.secureValidation;
    }

    public void setSecureValidation(boolean z) {
        this.secureValidation = z;
    }

    public String getCommitmentType() {
        return this.commitmentType;
    }

    public void setCommitmentType(String str) {
        this.commitmentType = str;
    }

    public CRLEntry addCRL(String str, String str2, byte[] bArr) {
        CRLEntry cRLEntry = new CRLEntry(str, str2, bArr);
        this.crlEntries.add(cRLEntry);
        return cRLEntry;
    }

    public List<CRLEntry> getCrlEntries() {
        return this.crlEntries;
    }

    public boolean isAllowCRLDownload() {
        return this.allowCRLDownload;
    }

    public void setAllowCRLDownload(boolean z) {
        this.allowCRLDownload = z;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void addCachedCertificate(String str, X509Certificate x509Certificate) throws KeyStoreException {
        if (str == null) {
            str = x509Certificate.getSubjectX500Principal().getName();
        }
        KeyStore keyStore2 = this.keyStore;
        if (keyStore2 != null) {
            synchronized (keyStore2) {
                this.keyStore.setCertificateEntry(str, x509Certificate);
            }
        }
    }

    public void addCachedCertificate(String str, byte[] bArr) throws KeyStoreException, CertificateException {
        addCachedCertificate((String) null, (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr)));
    }

    public X509Certificate getCachedCertificateByPrinicipal(String str) {
        KeyStore keyStore2 = this.keyStore;
        if (keyStore2 == null) {
            return null;
        }
        try {
            Iterator<T> it = Collections.list(keyStore2.aliases()).iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                Certificate[] certificateChain = this.keyStore.getCertificateChain(str2);
                if (certificateChain == null) {
                    Certificate certificate = this.keyStore.getCertificate(str2);
                    if (certificate != null) {
                        certificateChain = new Certificate[]{certificate};
                    }
                }
                Optional findFirst = Stream.of(certificateChain).map(new SignatureConfig$$ExternalSyntheticLambda0(X509Certificate.class)).filter(new SignatureConfig$$ExternalSyntheticLambda1(str)).findFirst();
                if (findFirst.isPresent()) {
                    return (X509Certificate) findFirst.get();
                }
            }
        } catch (KeyStoreException unused) {
        }
        return null;
    }

    private static KeyStore emptyKeyStore() {
        try {
            KeyStore instance = KeyStore.getInstance("PKCS12");
            instance.load((InputStream) null, (char[]) null);
            return instance;
        } catch (IOException | GeneralSecurityException e) {
            LOG.atError().withThrowable(e).log("unable to create PKCS #12 keystore - XAdES certificate chain lookups disabled");
            return null;
        }
    }
}
