package org.apache.poi.poifs.crypt.standard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

public class StandardEncryptor extends Encryptor {
    /* access modifiers changed from: private */
    public static final Logger LOG = LogManager.getLogger((Class<?>) StandardEncryptor.class);

    protected StandardEncryptor() {
    }

    protected StandardEncryptor(StandardEncryptor standardEncryptor) {
        super(standardEncryptor);
    }

    public void confirmPassword(String str) {
        SecureRandom instance = RandomSingleton.getInstance();
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[16];
        instance.nextBytes(bArr);
        instance.nextBytes(bArr2);
        confirmPassword(str, (byte[]) null, (byte[]) null, bArr, bArr2, (byte[]) null);
    }

    public void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        StandardEncryptionVerifier standardEncryptionVerifier = (StandardEncryptionVerifier) getEncryptionInfo().getVerifier();
        standardEncryptionVerifier.setSalt(bArr4);
        SecretKey generateSecretKey = StandardDecryptor.generateSecretKey(str, standardEncryptionVerifier, getKeySizeInBytes());
        setSecretKey(generateSecretKey);
        Cipher cipher = getCipher(generateSecretKey, (String) null);
        try {
            byte[] doFinal = cipher.doFinal(bArr3);
            byte[] doFinal2 = cipher.doFinal(Arrays.copyOf(CryptoFunctions.getMessageDigest(standardEncryptionVerifier.getHashAlgorithm()).digest(bArr3), standardEncryptionVerifier.getCipherAlgorithm().encryptedVerifierHashLength));
            standardEncryptionVerifier.setEncryptedVerifier(doFinal);
            standardEncryptionVerifier.setEncryptedVerifierHash(doFinal2);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    /* access modifiers changed from: private */
    public Cipher getCipher(SecretKey secretKey, String str) {
        EncryptionVerifier verifier = getEncryptionInfo().getVerifier();
        return CryptoFunctions.getCipher(secretKey, verifier.getCipherAlgorithm(), verifier.getChainingMode(), (byte[]) null, 1, str);
    }

    public OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        createEncryptionInfoEntry(directoryNode);
        DataSpaceMapUtils.addDefaultDataSpace(directoryNode);
        return new StandardCipherOutputStream(this, directoryNode);
    }

    protected class StandardCipherOutputStream extends FilterOutputStream implements POIFSWriterListener {
        protected long countBytes;
        protected final boolean deleteFile;
        protected final DirectoryNode dir;
        protected final File fileOut;

        private StandardCipherOutputStream(DirectoryNode directoryNode, File file, boolean z) throws IOException {
            super(new CipherOutputStream(new FileOutputStream(file), StandardEncryptor.this.getCipher(StandardEncryptor.this.getSecretKey(), "PKCS5Padding")));
            this.deleteFile = z;
            this.fileOut = file;
            this.dir = directoryNode;
        }

        protected StandardCipherOutputStream(StandardEncryptor standardEncryptor, DirectoryNode directoryNode) throws IOException {
            this(directoryNode, TempFile.createTempFile("encrypted_package", "crypt"), true);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.out.write(bArr, i, i2);
            this.countBytes += (long) i2;
        }

        public void write(int i) throws IOException {
            this.out.write(i);
            this.countBytes++;
        }

        public void close() throws IOException {
            File file;
            super.close();
            writeToPOIFS();
            if (this.deleteFile && (file = this.fileOut) != null) {
                file.delete();
            }
        }

        /* access modifiers changed from: package-private */
        public void writeToPOIFS() throws IOException {
            this.dir.createDocument(Decryptor.DEFAULT_POIFS_ENTRY, (int) (this.fileOut.length() + 8), this);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processPOIFSWriterEvent(org.apache.poi.poifs.filesystem.POIFSWriterEvent r4) {
            /*
                r3 = this;
                org.apache.poi.util.LittleEndianOutputStream r0 = new org.apache.poi.util.LittleEndianOutputStream     // Catch:{ IOException -> 0x0042 }
                org.apache.poi.poifs.filesystem.DocumentOutputStream r4 = r4.getStream()     // Catch:{ IOException -> 0x0042 }
                r0.<init>(r4)     // Catch:{ IOException -> 0x0042 }
                long r1 = r3.countBytes     // Catch:{ IOException -> 0x0042 }
                r0.writeLong(r1)     // Catch:{ IOException -> 0x0042 }
                java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0042 }
                java.io.File r1 = r3.fileOut     // Catch:{ IOException -> 0x0042 }
                r4.<init>(r1)     // Catch:{ IOException -> 0x0042 }
                org.apache.poi.util.IOUtils.copy((java.io.InputStream) r4, (java.io.OutputStream) r0)     // Catch:{ all -> 0x0036 }
                r4.close()     // Catch:{ IOException -> 0x0042 }
                java.io.File r4 = r3.fileOut     // Catch:{ IOException -> 0x0042 }
                boolean r4 = r4.delete()     // Catch:{ IOException -> 0x0042 }
                if (r4 != 0) goto L_0x0032
                org.apache.logging.log4j.Logger r4 = org.apache.poi.poifs.crypt.standard.StandardEncryptor.LOG     // Catch:{ IOException -> 0x0042 }
                org.apache.logging.log4j.LogBuilder r4 = r4.atError()     // Catch:{ IOException -> 0x0042 }
                java.lang.String r1 = "Can't delete temporary encryption file: {}"
                java.io.File r3 = r3.fileOut     // Catch:{ IOException -> 0x0042 }
                r4.log((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ IOException -> 0x0042 }
            L_0x0032:
                r0.close()     // Catch:{ IOException -> 0x0042 }
                return
            L_0x0036:
                r3 = move-exception
                throw r3     // Catch:{ all -> 0x0038 }
            L_0x0038:
                r0 = move-exception
                r4.close()     // Catch:{ all -> 0x003d }
                goto L_0x0041
            L_0x003d:
                r4 = move-exception
                r3.addSuppressed(r4)     // Catch:{ IOException -> 0x0042 }
            L_0x0041:
                throw r0     // Catch:{ IOException -> 0x0042 }
            L_0x0042:
                r3 = move-exception
                org.apache.poi.EncryptedDocumentException r4 = new org.apache.poi.EncryptedDocumentException
                r4.<init>((java.lang.Throwable) r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.standard.StandardEncryptor.StandardCipherOutputStream.processPOIFSWriterEvent(org.apache.poi.poifs.filesystem.POIFSWriterEvent):void");
        }
    }

    /* access modifiers changed from: protected */
    public int getKeySizeInBytes() {
        return getEncryptionInfo().getHeader().getKeySize() / 8;
    }

    /* access modifiers changed from: protected */
    public void createEncryptionInfoEntry(DirectoryNode directoryNode) throws IOException {
        final EncryptionInfo encryptionInfo = getEncryptionInfo();
        final StandardEncryptionHeader standardEncryptionHeader = (StandardEncryptionHeader) encryptionInfo.getHeader();
        final StandardEncryptionVerifier standardEncryptionVerifier = (StandardEncryptionVerifier) encryptionInfo.getVerifier();
        DataSpaceMapUtils.createEncryptionEntry(directoryNode, EncryptionInfo.ENCRYPTION_INFO_ENTRY, new EncryptionRecord() {
            public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
                littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMajor());
                littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMinor());
                littleEndianByteArrayOutputStream.writeInt(encryptionInfo.getEncryptionFlags());
                standardEncryptionHeader.write(littleEndianByteArrayOutputStream);
                standardEncryptionVerifier.write(littleEndianByteArrayOutputStream);
            }
        });
    }

    public StandardEncryptor copy() {
        return new StandardEncryptor(this);
    }
}
