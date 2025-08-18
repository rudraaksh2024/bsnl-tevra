package org.apache.poi.poifs.crypt.dsig;

import java.net.URI;
import javax.xml.crypto.URIDereferencer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class OOXMLURIDereferencer implements URIDereferencer {
    private static final Logger LOG = LogManager.getLogger((Class<?>) OOXMLURIDereferencer.class);
    private URIDereferencer baseUriDereferencer;
    private SignatureInfo signatureInfo;

    public void setSignatureInfo(SignatureInfo signatureInfo2) {
        this.signatureInfo = signatureInfo2;
        this.baseUriDereferencer = signatureInfo2.getSignatureFactory().getURIDereferencer();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
        r5 = r6.toInputStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.xml.crypto.Data dereference(javax.xml.crypto.URIReference r5, javax.xml.crypto.XMLCryptoContext r6) throws javax.xml.crypto.URIReferenceException {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x00b5
            if (r6 == 0) goto L_0x00ad
            java.net.URI r0 = new java.net.URI     // Catch:{ URISyntaxException -> 0x0093 }
            java.lang.String r1 = r5.getURI()     // Catch:{ URISyntaxException -> 0x0093 }
            r0.<init>(r1)     // Catch:{ URISyntaxException -> 0x0093 }
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.findPart(r0)
            if (r1 != 0) goto L_0x0025
            org.apache.logging.log4j.Logger r1 = LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atDebug()
            java.lang.String r2 = "cannot resolve {}, delegating to base DOM URI dereferencer"
            r1.log((java.lang.String) r2, (java.lang.Object) r0)
            javax.xml.crypto.URIDereferencer r4 = r4.baseUriDereferencer
            javax.xml.crypto.Data r4 = r4.dereference(r5, r6)
            return r4
        L_0x0025:
            r4 = 0
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ IOException -> 0x0073 }
            org.apache.poi.openxml4j.opc.PackagePartName r6 = r1.getPartName()     // Catch:{ IOException -> 0x0071 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0071 }
            java.lang.String r1 = ".rels"
            boolean r6 = r6.endsWith(r1)     // Catch:{ IOException -> 0x0071 }
            if (r6 == 0) goto L_0x0067
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r6 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException -> 0x0071 }
            r6.<init>()     // Catch:{ IOException -> 0x0071 }
        L_0x003f:
            int r1 = r5.read()     // Catch:{ all -> 0x005b }
            r2 = -1
            if (r1 == r2) goto L_0x0053
            r2 = 10
            if (r1 == r2) goto L_0x003f
            r2 = 13
            if (r1 != r2) goto L_0x004f
            goto L_0x003f
        L_0x004f:
            r6.write((int) r1)     // Catch:{ all -> 0x005b }
            goto L_0x003f
        L_0x0053:
            java.io.InputStream r5 = r6.toInputStream()     // Catch:{ all -> 0x005b }
            r6.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0067
        L_0x005b:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x005d }
        L_0x005d:
            r0 = move-exception
            r6.close()     // Catch:{ all -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ IOException -> 0x0071 }
        L_0x0066:
            throw r0     // Catch:{ IOException -> 0x0071 }
        L_0x0067:
            javax.xml.crypto.OctetStreamData r6 = new javax.xml.crypto.OctetStreamData
            java.lang.String r0 = r0.toString()
            r6.<init>(r5, r0, r4)
            return r6
        L_0x0071:
            r4 = move-exception
            goto L_0x0077
        L_0x0073:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
        L_0x0077:
            org.apache.commons.io.IOUtils.closeQuietly((java.io.InputStream) r5)
            javax.xml.crypto.URIReferenceException r5 = new javax.xml.crypto.URIReferenceException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "I/O error: "
            r6.<init>(r0)
            java.lang.String r0 = r4.getMessage()
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6, r4)
            throw r5
        L_0x0093:
            r4 = move-exception
            javax.xml.crypto.URIReferenceException r6 = new javax.xml.crypto.URIReferenceException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "could not URL decode the uri: "
            r0.<init>(r1)
            java.lang.String r5 = r5.getURI()
            java.lang.StringBuilder r5 = r0.append(r5)
            java.lang.String r5 = r5.toString()
            r6.<init>(r5, r4)
            throw r6
        L_0x00ad:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "XMLCryptoContext cannot be null"
            r4.<init>(r5)
            throw r4
        L_0x00b5:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "URIReference cannot be null - call setSignatureInfo(...) before"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.OOXMLURIDereferencer.dereference(javax.xml.crypto.URIReference, javax.xml.crypto.XMLCryptoContext):javax.xml.crypto.Data");
    }

    private PackagePart findPart(URI uri) {
        Logger logger = LOG;
        logger.atDebug().log("dereference: {}", (Object) uri);
        String path = uri.getPath();
        if (path == null || path.isEmpty()) {
            logger.atDebug().log("illegal part name (expected): {}", (Object) uri);
            return null;
        }
        try {
            return this.signatureInfo.getOpcPackage().getPart(PackagingURIHelper.createPartName(path));
        } catch (InvalidFormatException unused) {
            LOG.atWarn().log("illegal part name (not expected) in {}", (Object) uri);
            return null;
        }
    }
}
