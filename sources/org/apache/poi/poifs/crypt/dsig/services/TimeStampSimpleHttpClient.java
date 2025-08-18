package org.apache.poi.poifs.crypt.dsig.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.apache.poi.util.RandomSingleton;

public class TimeStampSimpleHttpClient implements TimeStampHttpClient {
    protected static final String BASIC_AUTH = "Authorization";
    protected static final String CONTENT_TYPE = "Content-Type";
    private static final int DEFAULT_TIMESTAMP_RESPONSE_SIZE = 10000000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) TimeStampSimpleHttpClient.class);
    private static int MAX_TIMESTAMP_RESPONSE_SIZE = 10000000;
    protected static final String REDIRECT_LOCATION = "Location";
    protected static final String USER_AGENT = "User-Agent";
    protected SignatureConfig config;
    protected String contentTypeOut = null;
    protected boolean followRedirects = false;
    protected final Map<String, String> header = new HashMap();
    protected boolean ignoreHttpsCertificates = false;
    protected Proxy proxy = Proxy.NO_PROXY;

    protected interface MethodHandler {
        void handle(HttpURLConnection httpURLConnection) throws IOException;
    }

    static /* synthetic */ void lambda$get$1(HttpURLConnection httpURLConnection) throws IOException {
    }

    static /* synthetic */ boolean lambda$recklessConnection$2(String str, SSLSession sSLSession) {
        return true;
    }

    public static void setMaxTimestampResponseSize(int i) {
        MAX_TIMESTAMP_RESPONSE_SIZE = i;
    }

    public static int getMaxTimestampResponseSize() {
        return MAX_TIMESTAMP_RESPONSE_SIZE;
    }

    private static class TimeStampSimpleHttpClientResponse implements TimeStampHttpClient.TimeStampHttpClientResponse {
        private final byte[] responseBytes;
        private final int responseCode;

        public TimeStampSimpleHttpClientResponse(int i, byte[] bArr) {
            this.responseCode = i;
            this.responseBytes = bArr;
        }

        public int getResponseCode() {
            return this.responseCode;
        }

        public byte[] getResponseBytes() {
            return this.responseBytes;
        }
    }

    public void init(SignatureConfig signatureConfig) {
        this.config = signatureConfig;
        this.header.clear();
        this.header.put(USER_AGENT, signatureConfig.getUserAgent());
        this.contentTypeOut = null;
        setProxy(signatureConfig.getProxyUrl());
        setBasicAuthentication(signatureConfig.getTspUser(), signatureConfig.getTspPass());
    }

    public void setProxy(String str) {
        if (str == null || str.isEmpty()) {
            this.proxy = Proxy.NO_PROXY;
            return;
        }
        try {
            URL url = new URL(str);
            String host = url.getHost();
            int port = url.getPort();
            Proxy.Type type = Proxy.Type.HTTP;
            InetAddress byName = InetAddress.getByName(host);
            if (port == -1) {
                port = 80;
            }
            this.proxy = new Proxy(type, new InetSocketAddress(byName, port));
        } catch (IOException unused) {
        }
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public void setContentTypeIn(String str) {
        this.header.put("Content-Type", str);
    }

    public void setContentTypeOut(String str) {
        this.contentTypeOut = str;
    }

    public void setBasicAuthentication(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            this.header.remove(BASIC_AUTH);
            return;
        }
        this.header.put(BASIC_AUTH, "Basic " + Base64.getEncoder().encodeToString((str + ParameterizedMessage.ERROR_MSG_SEPARATOR + str2).getBytes(StandardCharsets.ISO_8859_1)));
    }

    public boolean isIgnoreHttpsCertificates() {
        return this.ignoreHttpsCertificates;
    }

    public void setIgnoreHttpsCertificates(boolean z) {
        this.ignoreHttpsCertificates = z;
    }

    public boolean isFollowRedirects() {
        return this.followRedirects;
    }

    public void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public TimeStampHttpClient.TimeStampHttpClientResponse post(String str, byte[] bArr) throws IOException {
        return handleRedirect(str, new TimeStampSimpleHttpClient$$ExternalSyntheticLambda0(bArr), isFollowRedirects());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$post$0(byte[] r1, java.net.HttpURLConnection r2) throws java.io.IOException {
        /*
            java.lang.String r0 = "POST"
            r2.setRequestMethod(r0)
            r0 = 1
            r2.setDoOutput(r0)
            java.io.OutputStream r2 = r2.getOutputStream()
            r2.write(r1)     // Catch:{ all -> 0x0016 }
            if (r2 == 0) goto L_0x0015
            r2.close()
        L_0x0015:
            return
        L_0x0016:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r0 = move-exception
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0023:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient.lambda$post$0(byte[], java.net.HttpURLConnection):void");
    }

    public TimeStampHttpClient.TimeStampHttpClientResponse get(String str) throws IOException {
        return handleRedirect(str, new TimeStampSimpleHttpClient$$ExternalSyntheticLambda1(), isFollowRedirects());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f0, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f1, code lost:
        if (r5 != null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fb, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient.TimeStampHttpClientResponse handleRedirect(java.lang.String r6, org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient.MethodHandler r7, boolean r8) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "Content-Type mismatch - expected `"
            java.lang.String r1 = "Error contacting TSP server "
            java.net.URL r2 = new java.net.URL
            r2.<init>(r6)
            java.net.Proxy r3 = r5.proxy
            java.net.URLConnection r2 = r2.openConnection(r3)
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2
            boolean r3 = r5.ignoreHttpsCertificates
            if (r3 == 0) goto L_0x0018
            r5.recklessConnection(r2)
        L_0x0018:
            r3 = 20000(0x4e20, float:2.8026E-41)
            r2.setConnectTimeout(r3)
            r2.setReadTimeout(r3)
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.header
            r2.getClass()
            org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda3 r4 = new org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda3
            r4.<init>(r2)
            r3.forEach(r4)
            r7.handle(r2)     // Catch:{ all -> 0x00fc }
            r2.connect()     // Catch:{ all -> 0x00fc }
            int r3 = r2.getResponseCode()     // Catch:{ all -> 0x00fc }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == r4) goto L_0x009d
            switch(r3) {
                case 301: goto L_0x0072;
                case 302: goto L_0x0072;
                case 303: goto L_0x0072;
                default: goto L_0x003e;
            }     // Catch:{ all -> 0x00fc }
        L_0x003e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fc }
            r5.<init>(r1)     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r6 = ", had status code "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch:{ all -> 0x00fc }
            java.lang.String r6 = "/"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r6 = r2.getResponseMessage()     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00fc }
            org.apache.logging.log4j.Logger r6 = LOG     // Catch:{ all -> 0x00fc }
            org.apache.logging.log4j.LogBuilder r6 = r6.atError()     // Catch:{ all -> 0x00fc }
            r6.log((java.lang.String) r5)     // Catch:{ all -> 0x00fc }
            java.io.IOException r6 = new java.io.IOException     // Catch:{ all -> 0x00fc }
            r6.<init>(r5)     // Catch:{ all -> 0x00fc }
            throw r6     // Catch:{ all -> 0x00fc }
        L_0x0072:
            java.lang.String r0 = "Location"
            java.lang.String r0 = r2.getHeaderField(r0)     // Catch:{ all -> 0x00fc }
            if (r0 == 0) goto L_0x0090
            if (r8 == 0) goto L_0x0090
            org.apache.logging.log4j.Logger r8 = LOG     // Catch:{ all -> 0x00fc }
            org.apache.logging.log4j.LogBuilder r8 = r8.atWarn()     // Catch:{ all -> 0x00fc }
            java.lang.String r1 = "Received redirect: {} -> {}"
            r8.log(r1, r6, r0)     // Catch:{ all -> 0x00fc }
            r6 = 0
            org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient$TimeStampHttpClientResponse r5 = r5.handleRedirect(r0, r7, r6)     // Catch:{ all -> 0x00fc }
            r2.disconnect()
            return r5
        L_0x0090:
            org.apache.logging.log4j.Logger r5 = LOG     // Catch:{ all -> 0x00fc }
            org.apache.logging.log4j.LogBuilder r5 = r5.atWarn()     // Catch:{ all -> 0x00fc }
            java.lang.String r7 = "Redirect ignored - giving up: {} -> {}"
            r5.log(r7, r6, r0)     // Catch:{ all -> 0x00fc }
            r5 = 0
            goto L_0x00e5
        L_0x009d:
            java.lang.String r6 = "Content-Type"
            java.lang.String r6 = r2.getHeaderField(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r7 = r5.contentTypeOut     // Catch:{ all -> 0x00fc }
            if (r7 == 0) goto L_0x00d3
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x00fc }
            if (r7 == 0) goto L_0x00ae
            goto L_0x00d3
        L_0x00ae:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fc }
            r8.<init>(r0)     // Catch:{ all -> 0x00fc }
            java.lang.String r5 = r5.contentTypeOut     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r5 = r8.append(r5)     // Catch:{ all -> 0x00fc }
            java.lang.String r8 = "', received '"
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ all -> 0x00fc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r6 = "'"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fc }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00fc }
            r7.<init>(r5)     // Catch:{ all -> 0x00fc }
            throw r7     // Catch:{ all -> 0x00fc }
        L_0x00d3:
            java.io.InputStream r5 = r2.getInputStream()     // Catch:{ all -> 0x00fc }
            int r6 = getMaxTimestampResponseSize()     // Catch:{ all -> 0x00ee }
            byte[] r6 = org.apache.poi.util.IOUtils.toByteArrayWithMaxLength(r5, r6)     // Catch:{ all -> 0x00ee }
            if (r5 == 0) goto L_0x00e4
            r5.close()     // Catch:{ all -> 0x00fc }
        L_0x00e4:
            r5 = r6
        L_0x00e5:
            org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$TimeStampSimpleHttpClientResponse r6 = new org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$TimeStampSimpleHttpClientResponse     // Catch:{ all -> 0x00fc }
            r6.<init>(r3, r5)     // Catch:{ all -> 0x00fc }
            r2.disconnect()
            return r6
        L_0x00ee:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00f0 }
        L_0x00f0:
            r7 = move-exception
            if (r5 == 0) goto L_0x00fb
            r5.close()     // Catch:{ all -> 0x00f7 }
            goto L_0x00fb
        L_0x00f7:
            r5 = move-exception
            r6.addSuppressed(r5)     // Catch:{ all -> 0x00fc }
        L_0x00fb:
            throw r7     // Catch:{ all -> 0x00fc }
        L_0x00fc:
            r5 = move-exception
            r2.disconnect()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient.handleRedirect(java.lang.String, org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$MethodHandler, boolean):org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient$TimeStampHttpClientResponse");
    }

    /* access modifiers changed from: protected */
    public void recklessConnection(HttpURLConnection httpURLConnection) throws IOException {
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, new TrustManager[]{new UnsafeTrustManager()}, RandomSingleton.getInstance());
                httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                httpsURLConnection.setHostnameVerifier(new TimeStampSimpleHttpClient$$ExternalSyntheticLambda2());
            } catch (GeneralSecurityException e) {
                throw new IOException("Unable to reckless wrap connection.", e);
            }
        }
    }

    private static class UnsafeTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        private UnsafeTrustManager() {
        }
    }
}
