package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.RandomSingleton;

public class CryptoAPIEncryptor extends Encryptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public int chunkSize = 512;

    CryptoAPIEncryptor() {
    }

    CryptoAPIEncryptor(CryptoAPIEncryptor cryptoAPIEncryptor) {
        super(cryptoAPIEncryptor);
        this.chunkSize = cryptoAPIEncryptor.chunkSize;
    }

    public void confirmPassword(String str) {
        SecureRandom instance = RandomSingleton.getInstance();
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[16];
        instance.nextBytes(bArr);
        instance.nextBytes(bArr2);
        confirmPassword(str, (byte[]) null, (byte[]) null, bArr2, bArr, (byte[]) null);
    }

    public void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        CryptoAPIEncryptionVerifier cryptoAPIEncryptionVerifier = (CryptoAPIEncryptionVerifier) getEncryptionInfo().getVerifier();
        cryptoAPIEncryptionVerifier.setSalt(bArr4);
        setSecretKey(CryptoAPIDecryptor.generateSecretKey(str, cryptoAPIEncryptionVerifier));
        try {
            Cipher initCipherForBlock = initCipherForBlock((Cipher) null, 0);
            byte[] bArr6 = new byte[bArr3.length];
            initCipherForBlock.update(bArr3, 0, bArr3.length, bArr6);
            cryptoAPIEncryptionVerifier.setEncryptedVerifier(bArr6);
            cryptoAPIEncryptionVerifier.setEncryptedVerifierHash(initCipherForBlock.doFinal(CryptoFunctions.getMessageDigest(cryptoAPIEncryptionVerifier.getHashAlgorithm()).digest(bArr3)));
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    public Cipher initCipherForBlock(Cipher cipher, int i) throws GeneralSecurityException {
        return CryptoAPIDecryptor.initCipherForBlock(cipher, i, getEncryptionInfo(), getSecretKey(), 1);
    }

    public ChunkedCipherOutputStream getDataStream(DirectoryNode directoryNode) throws IOException {
        throw new IOException("not supported");
    }

    public CryptoAPICipherOutputStream getDataStream(OutputStream outputStream, int i) throws IOException, GeneralSecurityException {
        return new CryptoAPICipherOutputStream(outputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006c, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006d, code lost:
        if (r5 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0073, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0074, code lost:
        r10.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0077, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode r11, java.lang.String r12, org.apache.poi.poifs.filesystem.POIFSFileSystem r13) throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            r10 = this;
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDocumentOutputStream r0 = new org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDocumentOutputStream
            r0.<init>(r10)
            r10 = 8
            byte[] r1 = new byte[r10]
            r2 = 0
            r0.write(r1, r2, r10)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            org.apache.poi.poifs.filesystem.DirectoryNode r13 = r13.getRoot()
            java.util.Iterator r13 = r13.iterator()
            r4 = r2
        L_0x001b:
            boolean r5 = r13.hasNext()
            r6 = 1
            if (r5 == 0) goto L_0x0078
            java.lang.Object r5 = r13.next()
            org.apache.poi.poifs.filesystem.Entry r5 = (org.apache.poi.poifs.filesystem.Entry) r5
            boolean r7 = r5.isDirectoryEntry()
            if (r7 == 0) goto L_0x002f
            goto L_0x001b
        L_0x002f:
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry r7 = new org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry
            r7.<init>()
            r7.block = r4
            int r8 = r0.size()
            r7.streamOffset = r8
            java.lang.String r8 = r5.getName()
            r7.streamName = r8
            org.apache.poi.util.BitField r8 = org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor.StreamDescriptorEntry.flagStream
            int r6 = r8.setValue(r2, r6)
            r7.flags = r6
            r7.reserved2 = r2
            r0.setBlock(r4)
            org.apache.poi.poifs.filesystem.DocumentInputStream r5 = r11.createDocumentInputStream((org.apache.poi.poifs.filesystem.Entry) r5)
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r5, (java.io.OutputStream) r0)     // Catch:{ all -> 0x006a }
            if (r5 == 0) goto L_0x005b
            r5.close()
        L_0x005b:
            int r5 = r0.size()
            int r6 = r7.streamOffset
            int r5 = r5 - r6
            r7.streamSize = r5
            r3.add(r7)
            int r4 = r4 + 1
            goto L_0x001b
        L_0x006a:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x006c }
        L_0x006c:
            r11 = move-exception
            if (r5 == 0) goto L_0x0077
            r5.close()     // Catch:{ all -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r12 = move-exception
            r10.addSuppressed(r12)
        L_0x0077:
            throw r11
        L_0x0078:
            int r13 = r0.size()
            r0.setBlock(r2)
            int r4 = r3.size()
            long r4 = (long) r4
            org.apache.poi.util.LittleEndian.putUInt(r1, r2, r4)
            r4 = 4
            r0.write(r1, r2, r4)
            java.util.Iterator r3 = r3.iterator()
        L_0x008f:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x00e6
            java.lang.Object r5 = r3.next()
            org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor$StreamDescriptorEntry r5 = (org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor.StreamDescriptorEntry) r5
            int r7 = r5.streamOffset
            long r7 = (long) r7
            org.apache.poi.util.LittleEndian.putUInt(r1, r2, r7)
            r0.write(r1, r2, r4)
            int r7 = r5.streamSize
            long r7 = (long) r7
            org.apache.poi.util.LittleEndian.putUInt(r1, r2, r7)
            r0.write(r1, r2, r4)
            int r7 = r5.block
            org.apache.poi.util.LittleEndian.putUShort(r1, r2, r7)
            r7 = 2
            r0.write(r1, r2, r7)
            java.lang.String r8 = r5.streamName
            int r8 = r8.length()
            short r8 = (short) r8
            org.apache.poi.util.LittleEndian.putUByte(r1, r2, r8)
            r0.write(r1, r2, r6)
            int r8 = r5.flags
            short r8 = (short) r8
            org.apache.poi.util.LittleEndian.putUByte(r1, r2, r8)
            r0.write(r1, r2, r6)
            int r8 = r5.reserved2
            long r8 = (long) r8
            org.apache.poi.util.LittleEndian.putUInt(r1, r2, r8)
            r0.write(r1, r2, r4)
            java.lang.String r5 = r5.streamName
            byte[] r5 = org.apache.poi.util.StringUtil.getToUnicodeLE(r5)
            int r8 = r5.length
            r0.write(r5, r2, r8)
            org.apache.poi.util.LittleEndian.putShort(r1, r2, r2)
            r0.write(r1, r2, r7)
            goto L_0x008f
        L_0x00e6:
            int r3 = r0.size()
            int r5 = r3 - r13
            long r6 = (long) r13
            org.apache.poi.util.LittleEndian.putUInt(r1, r2, r6)
            long r5 = (long) r5
            org.apache.poi.util.LittleEndian.putUInt(r1, r4, r5)
            r0.reset()
            r0.setBlock(r2)
            r0.write(r1, r2, r10)
            r0.setSize(r3)
            long r1 = (long) r3
            java.io.InputStream r10 = r0.toInputStream(r1)
            r11.createDocument(r12, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor.setSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String, org.apache.poi.poifs.filesystem.POIFSFileSystem):void");
    }

    public void setChunkSize(int i) {
        this.chunkSize = i;
    }

    public CryptoAPIEncryptor copy() {
        return new CryptoAPIEncryptor(this);
    }

    protected class CryptoAPICipherOutputStream extends ChunkedCipherOutputStream {
        /* access modifiers changed from: protected */
        public void calculateChecksum(File file, int i) {
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i, boolean z) throws IOException, GeneralSecurityException {
            flush();
            return initCipherForBlockNoFlush(cipher, i, z);
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlockNoFlush(Cipher cipher, int i, boolean z) throws GeneralSecurityException {
            return CryptoAPIDecryptor.initCipherForBlock(cipher, i, CryptoAPIEncryptor.this.getEncryptionInfo(), CryptoAPIEncryptor.this.getSecretKey(), 1);
        }

        /* access modifiers changed from: protected */
        public void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) {
            throw new EncryptedDocumentException("createEncryptionInfoEntry not supported");
        }

        CryptoAPICipherOutputStream(OutputStream outputStream) throws IOException, GeneralSecurityException {
            super(outputStream, CryptoAPIEncryptor.this.chunkSize);
        }

        public void flush() throws IOException {
            writeChunk(false);
            super.flush();
        }
    }
}
