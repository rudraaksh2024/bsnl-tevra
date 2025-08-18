package org.apache.poi.poifs.crypt.dsig.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.security.auth.x500.X500Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Selector;

public class TSPTimeStampService implements TimeStampService {
    private static final Logger LOG = LogManager.getLogger((Class<?>) TSPTimeStampService.class);

    /* renamed from: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
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
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha256     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha384     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.crypt.HashAlgorithm r1 = org.apache.poi.poifs.crypt.HashAlgorithm.sha512     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService.AnonymousClass1.<clinit>():void");
        }
    }

    public ASN1ObjectIdentifier mapDigestAlgoToOID(HashAlgorithm hashAlgorithm) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$poifs$crypt$HashAlgorithm[hashAlgorithm.ordinal()];
        if (i == 1) {
            return X509ObjectIdentifiers.id_SHA1;
        }
        if (i == 2) {
            return NISTObjectIdentifiers.id_sha256;
        }
        if (i == 3) {
            return NISTObjectIdentifiers.id_sha384;
        }
        if (i == 4) {
            return NISTObjectIdentifiers.id_sha512;
        }
        throw new IllegalArgumentException("unsupported digest algo: " + hashAlgorithm);
    }

    public byte[] timeStamp(SignatureInfo signatureInfo, byte[] bArr, RevocationData revocationData) throws Exception {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        byte[] digest = CryptoFunctions.getMessageDigest(signatureConfig.getTspDigestAlgo()).digest(bArr);
        BigInteger bigInteger = new BigInteger(128, new SecureRandom());
        TimeStampRequestGenerator timeStampRequestGenerator = new TimeStampRequestGenerator();
        timeStampRequestGenerator.setCertReq(true);
        String tspRequestPolicy = signatureConfig.getTspRequestPolicy();
        if (tspRequestPolicy != null) {
            timeStampRequestGenerator.setReqPolicy(new ASN1ObjectIdentifier(tspRequestPolicy));
        }
        TimeStampRequest generate = timeStampRequestGenerator.generate(mapDigestAlgoToOID(signatureConfig.getTspDigestAlgo()), digest, bigInteger);
        TimeStampHttpClient tspHttpClient = signatureConfig.getTspHttpClient();
        tspHttpClient.init(signatureConfig);
        tspHttpClient.setContentTypeIn(signatureConfig.isTspOldProtocol() ? "application/timestamp-request" : "application/timestamp-query");
        TimeStampHttpClient.TimeStampHttpClientResponse post = tspHttpClient.post(signatureConfig.getTspUrl(), generate.getEncoded());
        if (post.isOK()) {
            byte[] responseBytes = post.getResponseBytes();
            if (responseBytes.length != 0) {
                TimeStampResponse timeStampResponse = new TimeStampResponse(responseBytes);
                timeStampResponse.validate(generate);
                if (timeStampResponse.getStatus() != 0) {
                    Logger logger = LOG;
                    logger.atDebug().log("status: {}", (Object) Unbox.box(timeStampResponse.getStatus()));
                    logger.atDebug().log("status string: {}", (Object) timeStampResponse.getStatusString());
                    PKIFailureInfo failInfo = timeStampResponse.getFailInfo();
                    if (failInfo != null) {
                        logger.atDebug().log("fail info int value: {}", (Object) Unbox.box(failInfo.intValue()));
                        if (256 == failInfo.intValue()) {
                            logger.atDebug().log("unaccepted policy");
                        }
                    }
                    throw new RuntimeException("timestamp response status != 0: " + timeStampResponse.getStatus());
                }
                TimeStampToken timeStampToken = timeStampResponse.getTimeStampToken();
                SignerId sid = timeStampToken.getSID();
                BigInteger serialNumber = sid.getSerialNumber();
                X500Name issuer = sid.getIssuer();
                Logger logger2 = LOG;
                logger2.atDebug().log("signer cert serial number: {}", (Object) serialNumber);
                logger2.atDebug().log("signer cert issuer: {}", (Object) issuer);
                Map map = (Map) timeStampToken.getCertificates().getMatches((Selector) null).stream().collect(Collectors.toMap(new TSPTimeStampService$$ExternalSyntheticLambda2(), Function.identity()));
                X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) map.values().stream().filter(new TSPTimeStampService$$ExternalSyntheticLambda3(issuer, serialNumber)).findFirst().orElseThrow(new TSPTimeStampService$$ExternalSyntheticLambda4());
                JcaX509CertificateConverter jcaX509CertificateConverter = new JcaX509CertificateConverter();
                jcaX509CertificateConverter.setProvider("BC");
                X509Certificate certificate = jcaX509CertificateConverter.getCertificate(x509CertificateHolder);
                do {
                    revocationData.addCertificate(certificate);
                    X500Principal issuerX500Principal = certificate.getIssuerX500Principal();
                    if (certificate.getSubjectX500Principal().equals(issuerX500Principal)) {
                        break;
                    }
                    X509CertificateHolder x509CertificateHolder2 = (X509CertificateHolder) map.get(issuerX500Principal.getName());
                    if (x509CertificateHolder2 != null) {
                        certificate = jcaX509CertificateConverter.getCertificate(x509CertificateHolder2);
                    } else {
                        certificate = signatureConfig.getCachedCertificateByPrinicipal(issuerX500Principal.getName());
                    }
                    if (certificate != null) {
                        List<byte[]> retrieveCRL = retrieveCRL(signatureConfig, certificate);
                        revocationData.getClass();
                        retrieveCRL.forEach(new TSPTimeStampService$$ExternalSyntheticLambda5(revocationData));
                        continue;
                    }
                } while (certificate != null);
                timeStampToken.validate(new BcRSASignerInfoVerifierBuilder(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), new DefaultDigestAlgorithmIdentifierFinder(), new BcDigestCalculatorProvider()).build(x509CertificateHolder));
                if (signatureConfig.getTspValidator() != null) {
                    signatureConfig.getTspValidator().validate(revocationData.getX509chain(), revocationData);
                }
                LOG.atDebug().log("time-stamp token time: {}", (Object) timeStampToken.getTimeStampInfo().getGenTime());
                return timeStampToken.getEncoded();
            }
            throw new RuntimeException("Content-Length is zero");
        }
        throw new IOException("Requesting timestamp data failed");
    }

    static /* synthetic */ boolean lambda$timeStamp$1(X500Name x500Name, BigInteger bigInteger, X509CertificateHolder x509CertificateHolder) {
        return x500Name.equals(x509CertificateHolder.getIssuer()) && bigInteger.equals(x509CertificateHolder.getSerialNumber());
    }

    static /* synthetic */ RuntimeException lambda$timeStamp$2() {
        return new RuntimeException("TSP response token has no signer certificate");
    }

    /* access modifiers changed from: protected */
    public List<byte[]> retrieveCRL(SignatureConfig signatureConfig, X509Certificate x509Certificate) throws IOException {
        List<SignatureConfig.CRLEntry> crlEntries = signatureConfig.getCrlEntries();
        byte[] extensionValue = x509Certificate.getExtensionValue(Extension.cRLDistributionPoints.getId());
        if (extensionValue == null) {
            return Collections.emptyList();
        }
        return (List) Stream.of(CRLDistPoint.getInstance(JcaX509ExtensionUtils.parseExtensionValue(extensionValue)).getDistributionPoints()).map(new TSPTimeStampService$$ExternalSyntheticLambda8()).filter(new TSPTimeStampService$$ExternalSyntheticLambda9()).filter(new TSPTimeStampService$$ExternalSyntheticLambda10()).flatMap(new TSPTimeStampService$$ExternalSyntheticLambda11()).filter(new TSPTimeStampService$$ExternalSyntheticLambda12()).map(new TSPTimeStampService$$ExternalSyntheticLambda13()).flatMap(new TSPTimeStampService$$ExternalSyntheticLambda14(this, crlEntries, x509Certificate, signatureConfig)).filter(new TSPTimeStampService$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$retrieveCRL$3(DistributionPointName distributionPointName) {
        return distributionPointName.getType() == 0;
    }

    static /* synthetic */ boolean lambda$retrieveCRL$5(GeneralName generalName) {
        return generalName.getTagNo() == 6;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$retrieveCRL$9$org-apache-poi-poifs-crypt-dsig-services-TSPTimeStampService  reason: not valid java name */
    public /* synthetic */ Stream m2229lambda$retrieveCRL$9$orgapachepoipoifscryptdsigservicesTSPTimeStampService(List list, X509Certificate x509Certificate, SignatureConfig signatureConfig, String str) {
        SignatureConfig.CRLEntry downloadCRL;
        List list2 = (List) list.stream().filter(new TSPTimeStampService$$ExternalSyntheticLambda0(this, x509Certificate, str)).collect(Collectors.toList());
        Stream filter = list.stream().filter(new TSPTimeStampService$$ExternalSyntheticLambda6(this, x509Certificate, str));
        if (list2.isEmpty() && (downloadCRL = downloadCRL(signatureConfig, str)) != null) {
            list2.add(downloadCRL);
        }
        return Stream.concat(list2.stream(), filter).map(new TSPTimeStampService$$ExternalSyntheticLambda7());
    }

    /* access modifiers changed from: protected */
    /* renamed from: matchCRLbyUrl */
    public boolean m2227lambda$null$7$orgapachepoipoifscryptdsigservicesTSPTimeStampService(SignatureConfig.CRLEntry cRLEntry, X509Certificate x509Certificate, String str) {
        return str.equals(cRLEntry.getCrlURL());
    }

    /* access modifiers changed from: protected */
    /* renamed from: matchCRLbyCN */
    public boolean m2228lambda$null$8$orgapachepoipoifscryptdsigservicesTSPTimeStampService(SignatureConfig.CRLEntry cRLEntry, X509Certificate x509Certificate, String str) {
        return x509Certificate.getSubjectX500Principal().getName().equals(cRLEntry.getCertCN());
    }

    /* access modifiers changed from: protected */
    public SignatureConfig.CRLEntry downloadCRL(SignatureConfig signatureConfig, String str) {
        if (!signatureConfig.isAllowCRLDownload()) {
            return null;
        }
        TimeStampHttpClient tspHttpClient = signatureConfig.getTspHttpClient();
        tspHttpClient.init(signatureConfig);
        tspHttpClient.setBasicAuthentication((String) null, (String) null);
        try {
            TimeStampHttpClient.TimeStampHttpClientResponse timeStampHttpClientResponse = tspHttpClient.get(str);
            if (!timeStampHttpClientResponse.isOK()) {
                return null;
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                byte[] responseBytes = timeStampHttpClientResponse.getResponseBytes();
                return signatureConfig.addCRL(str, ((X509CRL) instance.generateCRL(new ByteArrayInputStream(responseBytes))).getIssuerX500Principal().getName(), responseBytes);
            } catch (GeneralSecurityException e) {
                LOG.atWarn().withThrowable(e).log("CRL download failed from {}", (Object) str);
                return null;
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
