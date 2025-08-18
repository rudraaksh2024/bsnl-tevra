package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;

class DigestOutputStream extends OutputStream {
    final HashAlgorithm algo;
    final PrivateKey key;
    private MessageDigest md;

    DigestOutputStream(HashAlgorithm hashAlgorithm, PrivateKey privateKey) {
        this.algo = hashAlgorithm;
        this.key = privateKey;
    }

    public void init() throws GeneralSecurityException {
        if (!isMSCapi(this.key)) {
            this.md = CryptoFunctions.getMessageDigest(this.algo);
            return;
        }
        throw new EncryptedDocumentException("Windows keystore entries can't be signed with the " + this.algo + " hash. Please use one digest algorithm of sha1 / sha256 / sha384 / sha512.");
    }

    public void write(int i) throws IOException {
        this.md.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.md.update(bArr, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] sign() throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            r8 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            byte[] r1 = r8.getHashMagic()     // Catch:{ all -> 0x002f }
            r0.write(r1)     // Catch:{ all -> 0x002f }
            java.security.MessageDigest r1 = r8.md     // Catch:{ all -> 0x002f }
            byte[] r1 = r1.digest()     // Catch:{ all -> 0x002f }
            r0.write(r1)     // Catch:{ all -> 0x002f }
            java.security.PrivateKey r2 = r8.key     // Catch:{ all -> 0x002f }
            org.apache.poi.poifs.crypt.CipherAlgorithm r3 = org.apache.poi.poifs.crypt.CipherAlgorithm.rsa     // Catch:{ all -> 0x002f }
            org.apache.poi.poifs.crypt.ChainingMode r4 = org.apache.poi.poifs.crypt.ChainingMode.ecb     // Catch:{ all -> 0x002f }
            r5 = 0
            r6 = 1
            java.lang.String r7 = "PKCS1Padding"
            javax.crypto.Cipher r8 = org.apache.poi.poifs.crypt.CryptoFunctions.getCipher(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x002f }
            byte[] r1 = r0.toByteArray()     // Catch:{ all -> 0x002f }
            byte[] r8 = r8.doFinal(r1)     // Catch:{ all -> 0x002f }
            r0.close()
            return r8
        L_0x002f:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r8.addSuppressed(r0)
        L_0x003a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.DigestOutputStream.sign():byte[]");
    }

    static boolean isMSCapi(PrivateKey privateKey) {
        return privateKey != null && privateKey.getClass().getName().contains("mscapi");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getHashMagic() {
        /*
            r5 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException | GSSException -> 0x0059 }
            r0.<init>()     // Catch:{ IOException | GSSException -> 0x0059 }
            org.ietf.jgss.Oid r1 = new org.ietf.jgss.Oid     // Catch:{ all -> 0x004d }
            org.apache.poi.poifs.crypt.HashAlgorithm r2 = r5.algo     // Catch:{ all -> 0x004d }
            java.lang.String r2 = r2.rsaOid     // Catch:{ all -> 0x004d }
            r1.<init>(r2)     // Catch:{ all -> 0x004d }
            byte[] r1 = r1.getDER()     // Catch:{ all -> 0x004d }
            r2 = 48
            r0.write((int) r2)     // Catch:{ all -> 0x004d }
            org.apache.poi.poifs.crypt.HashAlgorithm r3 = r5.algo     // Catch:{ all -> 0x004d }
            int r3 = r3.hashSize     // Catch:{ all -> 0x004d }
            int r4 = r1.length     // Catch:{ all -> 0x004d }
            int r3 = r3 + r4
            int r3 = r3 + 6
            r0.write((int) r3)     // Catch:{ all -> 0x004d }
            r0.write((int) r2)     // Catch:{ all -> 0x004d }
            int r2 = r1.length     // Catch:{ all -> 0x004d }
            r3 = 2
            int r2 = r2 + r3
            r0.write((int) r2)     // Catch:{ all -> 0x004d }
            r0.write(r1)     // Catch:{ all -> 0x004d }
            r1 = 3
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x004d }
            r2 = 5
            r4 = 0
            r1[r4] = r2     // Catch:{ all -> 0x004d }
            r2 = 1
            r1[r2] = r4     // Catch:{ all -> 0x004d }
            r2 = 4
            r1[r3] = r2     // Catch:{ all -> 0x004d }
            r0.write(r1)     // Catch:{ all -> 0x004d }
            org.apache.poi.poifs.crypt.HashAlgorithm r5 = r5.algo     // Catch:{ all -> 0x004d }
            int r5 = r5.hashSize     // Catch:{ all -> 0x004d }
            r0.write((int) r5)     // Catch:{ all -> 0x004d }
            byte[] r5 = r0.toByteArray()     // Catch:{ all -> 0x004d }
            r0.close()     // Catch:{ IOException | GSSException -> 0x0059 }
            return r5
        L_0x004d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004f }
        L_0x004f:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ IOException | GSSException -> 0x0059 }
        L_0x0058:
            throw r1     // Catch:{ IOException | GSSException -> 0x0059 }
        L_0x0059:
            r5 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.dsig.DigestOutputStream.getHashMagic():byte[]");
    }
}
