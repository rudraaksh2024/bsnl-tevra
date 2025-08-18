package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

public class CryptoAPIDecryptor extends Decryptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public int chunkSize = -1;
    private long length = -1;

    static class StreamDescriptorEntry {
        static final BitField flagStream = BitFieldFactory.getInstance(1);
        int block;
        int flags;
        int reserved2;
        String streamName;
        int streamOffset;
        int streamSize;

        StreamDescriptorEntry() {
        }
    }

    protected CryptoAPIDecryptor() {
    }

    protected CryptoAPIDecryptor(CryptoAPIDecryptor cryptoAPIDecryptor) {
        super(cryptoAPIDecryptor);
        this.length = cryptoAPIDecryptor.length;
        this.chunkSize = cryptoAPIDecryptor.chunkSize;
    }

    public boolean verifyPassword(String str) {
        EncryptionVerifier verifier = getEncryptionInfo().getVerifier();
        SecretKey generateSecretKey = generateSecretKey(str, verifier);
        try {
            Cipher initCipherForBlock = initCipherForBlock((Cipher) null, 0, getEncryptionInfo(), generateSecretKey, 2);
            byte[] encryptedVerifier = verifier.getEncryptedVerifier();
            byte[] bArr = new byte[encryptedVerifier.length];
            initCipherForBlock.update(encryptedVerifier, 0, encryptedVerifier.length, bArr);
            setVerifier(bArr);
            if (!Arrays.equals(CryptoFunctions.getMessageDigest(verifier.getHashAlgorithm()).digest(bArr), initCipherForBlock.doFinal(verifier.getEncryptedVerifierHash()))) {
                return false;
            }
            setSecretKey(generateSecretKey);
            return true;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
        return initCipherForBlock(cipher, i, getEncryptionInfo(), getSecretKey(), 2);
    }

    protected static Cipher initCipherForBlock(Cipher cipher, int i, EncryptionInfo encryptionInfo, SecretKey secretKey, int i2) throws GeneralSecurityException {
        HashAlgorithm hashAlgorithm = encryptionInfo.getVerifier().getHashAlgorithm();
        byte[] bArr = new byte[4];
        LittleEndian.putUInt(bArr, 0, (long) i);
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(hashAlgorithm);
        messageDigest.update(secretKey.getEncoded());
        byte[] digest = messageDigest.digest(bArr);
        EncryptionHeader header = encryptionInfo.getHeader();
        int keySize = header.getKeySize();
        byte[] block0 = CryptoFunctions.getBlock0(digest, keySize / 8);
        if (keySize == 40) {
            block0 = CryptoFunctions.getBlock0(block0, 16);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(block0, secretKey.getAlgorithm());
        if (cipher == null) {
            return CryptoFunctions.getCipher(secretKeySpec, header.getCipherAlgorithm(), (ChainingMode) null, (byte[]) null, i2);
        }
        cipher.init(i2, secretKeySpec);
        return cipher;
    }

    protected static SecretKey generateSecretKey(String str, EncryptionVerifier encryptionVerifier) {
        if (str.length() > 255) {
            str = str.substring(0, 255);
        }
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(encryptionVerifier.getHashAlgorithm());
        messageDigest.update(encryptionVerifier.getSalt());
        return new SecretKeySpec(messageDigest.digest(StringUtil.getToUnicodeLE(str)), encryptionVerifier.getCipherAlgorithm().jceId);
    }

    public ChunkedCipherInputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        throw new IOException("not supported");
    }

    public ChunkedCipherInputStream getDataStream(InputStream inputStream, int i, int i2) throws IOException, GeneralSecurityException {
        return new CryptoAPICipherInputStream(inputStream, (long) i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0098, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a1, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.poifs.filesystem.POIFSFileSystem getSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode r9, java.lang.String r10) throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            r8 = this;
            r0 = 0
            org.apache.poi.poifs.filesystem.Entry r10 = r9.getEntry(r10)     // Catch:{ Exception -> 0x00e9 }
            org.apache.poi.poifs.filesystem.DocumentInputStream r9 = r9.createDocumentInputStream((org.apache.poi.poifs.filesystem.Entry) r10)     // Catch:{ Exception -> 0x00e9 }
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDocumentInputStream r10 = new org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDocumentInputStream     // Catch:{ all -> 0x00db }
            byte[] r1 = org.apache.poi.util.IOUtils.toByteArray(r9)     // Catch:{ all -> 0x00db }
            r10.<init>(r8, r1)     // Catch:{ all -> 0x00db }
            org.apache.poi.util.LittleEndianInputStream r8 = new org.apache.poi.util.LittleEndianInputStream     // Catch:{ all -> 0x00cf }
            r8.<init>(r10)     // Catch:{ all -> 0x00cf }
            long r1 = r8.readUInt()     // Catch:{ all -> 0x00c3 }
            int r1 = (int) r1     // Catch:{ all -> 0x00c3 }
            r8.readUInt()     // Catch:{ all -> 0x00c3 }
            long r1 = (long) r1     // Catch:{ all -> 0x00c3 }
            r3 = 8
            long r1 = r1 - r3
            long r3 = r10.skip(r1)     // Catch:{ all -> 0x00c3 }
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x00bb
            r1 = 0
            r10.setBlock(r1)     // Catch:{ all -> 0x00c3 }
            long r2 = r8.readUInt()     // Catch:{ all -> 0x00c3 }
            int r2 = (int) r2     // Catch:{ all -> 0x00c3 }
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry[] r3 = new org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor.StreamDescriptorEntry[r2]     // Catch:{ all -> 0x00c3 }
            r4 = r1
        L_0x0037:
            if (r4 >= r2) goto L_0x0070
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry r5 = new org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry     // Catch:{ all -> 0x00c3 }
            r5.<init>()     // Catch:{ all -> 0x00c3 }
            r3[r4] = r5     // Catch:{ all -> 0x00c3 }
            long r6 = r8.readUInt()     // Catch:{ all -> 0x00c3 }
            int r6 = (int) r6     // Catch:{ all -> 0x00c3 }
            r5.streamOffset = r6     // Catch:{ all -> 0x00c3 }
            long r6 = r8.readUInt()     // Catch:{ all -> 0x00c3 }
            int r6 = (int) r6     // Catch:{ all -> 0x00c3 }
            r5.streamSize = r6     // Catch:{ all -> 0x00c3 }
            int r6 = r8.readUShort()     // Catch:{ all -> 0x00c3 }
            r5.block = r6     // Catch:{ all -> 0x00c3 }
            int r6 = r8.readUByte()     // Catch:{ all -> 0x00c3 }
            int r7 = r8.readUByte()     // Catch:{ all -> 0x00c3 }
            r5.flags = r7     // Catch:{ all -> 0x00c3 }
            int r7 = r8.readInt()     // Catch:{ all -> 0x00c3 }
            r5.reserved2 = r7     // Catch:{ all -> 0x00c3 }
            java.lang.String r6 = org.apache.poi.util.StringUtil.readUnicodeLE(r8, r6)     // Catch:{ all -> 0x00c3 }
            r5.streamName = r6     // Catch:{ all -> 0x00c3 }
            r8.readShort()     // Catch:{ all -> 0x00c3 }
            int r4 = r4 + 1
            goto L_0x0037
        L_0x0070:
            org.apache.poi.poifs.filesystem.POIFSFileSystem r4 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x00c3 }
            r4.<init>()     // Catch:{ all -> 0x00c3 }
        L_0x0075:
            if (r1 >= r2) goto L_0x00a5
            r0 = r3[r1]     // Catch:{ all -> 0x00a2 }
            int r5 = r0.streamOffset     // Catch:{ all -> 0x00a2 }
            r10.seek(r5)     // Catch:{ all -> 0x00a2 }
            int r5 = r0.block     // Catch:{ all -> 0x00a2 }
            r10.setBlock(r5)     // Catch:{ all -> 0x00a2 }
            org.apache.commons.io.input.BoundedInputStream r5 = new org.apache.commons.io.input.BoundedInputStream     // Catch:{ all -> 0x00a2 }
            int r6 = r0.streamSize     // Catch:{ all -> 0x00a2 }
            long r6 = (long) r6     // Catch:{ all -> 0x00a2 }
            r5.<init>(r10, r6)     // Catch:{ all -> 0x00a2 }
            java.lang.String r0 = r0.streamName     // Catch:{ all -> 0x0096 }
            r4.createDocument(r5, r0)     // Catch:{ all -> 0x0096 }
            r5.close()     // Catch:{ all -> 0x00a2 }
            int r1 = r1 + 1
            goto L_0x0075
        L_0x0096:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0098 }
        L_0x0098:
            r1 = move-exception
            r5.close()     // Catch:{ all -> 0x009d }
            goto L_0x00a1
        L_0x009d:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ all -> 0x00a2 }
        L_0x00a1:
            throw r1     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r1 = move-exception
            r0 = r4
            goto L_0x00c4
        L_0x00a5:
            r8.close()     // Catch:{ all -> 0x00b8 }
            r10.close()     // Catch:{ all -> 0x00b5 }
            if (r9 == 0) goto L_0x00b4
            r9.close()     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00b4
        L_0x00b1:
            r8 = move-exception
            r0 = r4
            goto L_0x00ea
        L_0x00b4:
            return r4
        L_0x00b5:
            r8 = move-exception
            r0 = r4
            goto L_0x00dc
        L_0x00b8:
            r8 = move-exception
            r0 = r4
            goto L_0x00d0
        L_0x00bb:
            java.io.EOFException r1 = new java.io.EOFException     // Catch:{ all -> 0x00c3 }
            java.lang.String r2 = "buffer underrun"
            r1.<init>(r2)     // Catch:{ all -> 0x00c3 }
            throw r1     // Catch:{ all -> 0x00c3 }
        L_0x00c3:
            r1 = move-exception
        L_0x00c4:
            throw r1     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r2 = move-exception
            r8.close()     // Catch:{ all -> 0x00ca }
            goto L_0x00ce
        L_0x00ca:
            r8 = move-exception
            r1.addSuppressed(r8)     // Catch:{ all -> 0x00cf }
        L_0x00ce:
            throw r2     // Catch:{ all -> 0x00cf }
        L_0x00cf:
            r8 = move-exception
        L_0x00d0:
            throw r8     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r1 = move-exception
            r10.close()     // Catch:{ all -> 0x00d6 }
            goto L_0x00da
        L_0x00d6:
            r10 = move-exception
            r8.addSuppressed(r10)     // Catch:{ all -> 0x00db }
        L_0x00da:
            throw r1     // Catch:{ all -> 0x00db }
        L_0x00db:
            r8 = move-exception
        L_0x00dc:
            throw r8     // Catch:{ all -> 0x00dd }
        L_0x00dd:
            r10 = move-exception
            if (r9 == 0) goto L_0x00e8
            r9.close()     // Catch:{ all -> 0x00e4 }
            goto L_0x00e8
        L_0x00e4:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch:{ Exception -> 0x00e9 }
        L_0x00e8:
            throw r10     // Catch:{ Exception -> 0x00e9 }
        L_0x00e9:
            r8 = move-exception
        L_0x00ea:
            org.apache.poi.util.IOUtils.closeQuietly(r0)
            boolean r9 = r8 instanceof java.security.GeneralSecurityException
            if (r9 != 0) goto L_0x0100
            boolean r9 = r8 instanceof java.io.IOException
            if (r9 == 0) goto L_0x00f8
            java.io.IOException r8 = (java.io.IOException) r8
            throw r8
        L_0x00f8:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "summary entries can't be read"
            r9.<init>(r10, r8)
            throw r9
        L_0x0100:
            java.security.GeneralSecurityException r8 = (java.security.GeneralSecurityException) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor.getSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String):org.apache.poi.poifs.filesystem.POIFSFileSystem");
    }

    public long getLength() {
        long j = this.length;
        if (j != -1) {
            return j;
        }
        throw new IllegalStateException("Decryptor.getDataStream() was not called");
    }

    public void setChunkSize(int i) {
        this.chunkSize = i;
    }

    public CryptoAPIDecryptor copy() {
        return new CryptoAPIDecryptor(this);
    }

    private class CryptoAPICipherInputStream extends ChunkedCipherInputStream {
        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
            return CryptoAPIDecryptor.this.initCipherForBlock(cipher, i);
        }

        public CryptoAPICipherInputStream(InputStream inputStream, long j, int i) throws GeneralSecurityException {
            super(inputStream, j, CryptoAPIDecryptor.this.chunkSize, i);
        }
    }
}
