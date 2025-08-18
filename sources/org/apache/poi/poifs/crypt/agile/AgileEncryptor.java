package org.apache.poi.poifs.crypt.agile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;

public class AgileEncryptor extends Encryptor {
    private byte[] integritySalt;
    private byte[] pwHash;

    protected AgileEncryptor() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AgileEncryptor(org.apache.poi.poifs.crypt.agile.AgileEncryptor r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            byte[] r0 = r3.integritySalt
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x0010
        L_0x000a:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0010:
            r2.integritySalt = r0
            byte[] r3 = r3.pwHash
            if (r3 != 0) goto L_0x0017
            goto L_0x001e
        L_0x0017:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x001e:
            r2.pwHash = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.agile.AgileEncryptor.<init>(org.apache.poi.poifs.crypt.agile.AgileEncryptor):void");
    }

    public void confirmPassword(String str) {
        AgileEncryptionHeader agileEncryptionHeader = (AgileEncryptionHeader) getEncryptionInfo().getHeader();
        int blockSize = agileEncryptionHeader.getBlockSize();
        int i = agileEncryptionHeader.getHashAlgorithm().hashSize;
        int maxRecordLength = CryptoFunctions.getMaxRecordLength();
        long j = (long) blockSize;
        byte[] safelyAllocate = IOUtils.safelyAllocate(j, maxRecordLength);
        byte[] safelyAllocate2 = IOUtils.safelyAllocate(j, maxRecordLength);
        byte[] safelyAllocate3 = IOUtils.safelyAllocate(j, maxRecordLength);
        byte[] safelyAllocate4 = IOUtils.safelyAllocate((long) (agileEncryptionHeader.getKeySize() / 8), maxRecordLength);
        byte[] safelyAllocate5 = IOUtils.safelyAllocate((long) i, maxRecordLength);
        SecureRandom instance = RandomSingleton.getInstance();
        instance.nextBytes(safelyAllocate);
        instance.nextBytes(safelyAllocate2);
        instance.nextBytes(safelyAllocate3);
        instance.nextBytes(safelyAllocate4);
        instance.nextBytes(safelyAllocate5);
        confirmPassword(str, safelyAllocate4, safelyAllocate3, safelyAllocate, safelyAllocate2, safelyAllocate5);
    }

    public void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        AgileEncryptionVerifier agileEncryptionVerifier = (AgileEncryptionVerifier) getEncryptionInfo().getVerifier();
        AgileEncryptionHeader agileEncryptionHeader = (AgileEncryptionHeader) getEncryptionInfo().getHeader();
        agileEncryptionVerifier.setSalt(bArr4);
        agileEncryptionHeader.setKeySalt(bArr2);
        int blockSize = agileEncryptionHeader.getBlockSize();
        byte[] hashPassword = CryptoFunctions.hashPassword(str, agileEncryptionVerifier.getHashAlgorithm(), bArr4, agileEncryptionVerifier.getSpinCount());
        this.pwHash = hashPassword;
        agileEncryptionVerifier.setEncryptedVerifier(AgileDecryptor.hashInput(agileEncryptionVerifier, hashPassword, AgileDecryptor.kVerifierInputBlock, bArr3, 1));
        agileEncryptionVerifier.setEncryptedVerifierHash(AgileDecryptor.hashInput(agileEncryptionVerifier, this.pwHash, AgileDecryptor.kHashedVerifierBlock, CryptoFunctions.getMessageDigest(agileEncryptionVerifier.getHashAlgorithm()).digest(bArr3), 1));
        agileEncryptionVerifier.setEncryptedKey(AgileDecryptor.hashInput(agileEncryptionVerifier, this.pwHash, AgileDecryptor.kCryptoKeyBlock, bArr, 1));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, agileEncryptionHeader.getCipherAlgorithm().jceId);
        setSecretKey(secretKeySpec);
        this.integritySalt = (byte[]) bArr5.clone();
        try {
            Cipher cipher = CryptoFunctions.getCipher(secretKeySpec, agileEncryptionHeader.getCipherAlgorithm(), agileEncryptionHeader.getChainingMode(), CryptoFunctions.generateIv(agileEncryptionHeader.getHashAlgorithm(), agileEncryptionHeader.getKeySalt(), AgileDecryptor.kIntegrityKeyBlock, agileEncryptionHeader.getBlockSize()), 1);
            byte[] bArr6 = this.integritySalt;
            agileEncryptionHeader.setEncryptedHmacKey(cipher.doFinal(CryptoFunctions.getBlock0(bArr6, AgileDecryptor.getNextBlockSize(bArr6.length, blockSize))));
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    public OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
        return new AgileCipherOutputStream(directoryNode);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0082, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0087, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0088, code lost:
        r6.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008b, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateIntegrityHMAC(java.io.File r7, int r8) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r6 = this;
            org.apache.poi.poifs.crypt.EncryptionInfo r0 = r6.getEncryptionInfo()
            org.apache.poi.poifs.crypt.EncryptionHeader r0 = r0.getHeader()
            org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader r0 = (org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader) r0
            int r1 = r0.getBlockSize()
            org.apache.poi.poifs.crypt.HashAlgorithm r2 = r0.getHashAlgorithm()
            javax.crypto.Mac r3 = org.apache.poi.poifs.crypt.CryptoFunctions.getMac(r2)
            byte[] r4 = r6.integritySalt
            int r5 = r4.length
            int r5 = org.apache.poi.poifs.crypt.agile.AgileDecryptor.getNextBlockSize(r5, r1)
            byte[] r4 = org.apache.poi.poifs.crypt.CryptoFunctions.getBlock0(r4, r5)
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec
            java.lang.String r2 = r2.jceHmacId
            r5.<init>(r4, r2)
            r3.init(r5)
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]
            long r4 = (long) r8
            r8 = 0
            org.apache.poi.util.LittleEndian.putLong(r2, r8, r4)
            r4 = 8
            r3.update(r2, r8, r4)
            java.io.FileInputStream r4 = new java.io.FileInputStream
            r4.<init>(r7)
        L_0x003e:
            int r7 = r4.read(r2)     // Catch:{ all -> 0x0080 }
            r5 = -1
            if (r7 == r5) goto L_0x0049
            r3.update(r2, r8, r7)     // Catch:{ all -> 0x0080 }
            goto L_0x003e
        L_0x0049:
            r4.close()
            byte[] r7 = r3.doFinal()
            int r8 = r7.length
            int r8 = org.apache.poi.poifs.crypt.agile.AgileDecryptor.getNextBlockSize(r8, r1)
            byte[] r7 = org.apache.poi.poifs.crypt.CryptoFunctions.getBlock0(r7, r8)
            org.apache.poi.poifs.crypt.HashAlgorithm r8 = r0.getHashAlgorithm()
            byte[] r2 = r0.getKeySalt()
            byte[] r3 = org.apache.poi.poifs.crypt.agile.AgileDecryptor.kIntegrityValueBlock
            byte[] r8 = org.apache.poi.poifs.crypt.CryptoFunctions.generateIv(r8, r2, r3, r1)
            javax.crypto.SecretKey r6 = r6.getSecretKey()
            org.apache.poi.poifs.crypt.CipherAlgorithm r1 = r0.getCipherAlgorithm()
            org.apache.poi.poifs.crypt.ChainingMode r2 = r0.getChainingMode()
            r3 = 1
            javax.crypto.Cipher r6 = org.apache.poi.poifs.crypt.CryptoFunctions.getCipher(r6, r1, r2, r8, r3)
            byte[] r6 = r6.doFinal(r7)
            r0.setEncryptedHmacValue(r6)
            return
        L_0x0080:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r7 = move-exception
            r4.close()     // Catch:{ all -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r8 = move-exception
            r6.addSuppressed(r8)
        L_0x008b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.agile.AgileEncryptor.updateIntegrityHMAC(java.io.File, int):void");
    }

    /* access modifiers changed from: protected */
    public EncryptionDocument createEncryptionDocument() {
        AgileEncryptionVerifier agileEncryptionVerifier = (AgileEncryptionVerifier) getEncryptionInfo().getVerifier();
        AgileEncryptionHeader agileEncryptionHeader = (AgileEncryptionHeader) getEncryptionInfo().getHeader();
        EncryptionDocument encryptionDocument = new EncryptionDocument();
        KeyData keyData = new KeyData();
        encryptionDocument.setKeyData(keyData);
        KeyEncryptor keyEncryptor = new KeyEncryptor();
        encryptionDocument.getKeyEncryptors().add(keyEncryptor);
        PasswordKeyEncryptor passwordKeyEncryptor = new PasswordKeyEncryptor();
        keyEncryptor.setPasswordKeyEncryptor(passwordKeyEncryptor);
        passwordKeyEncryptor.setSpinCount(Integer.valueOf(agileEncryptionVerifier.getSpinCount()));
        keyData.setSaltSize(Integer.valueOf(agileEncryptionHeader.getBlockSize()));
        passwordKeyEncryptor.setSaltSize(Integer.valueOf(agileEncryptionVerifier.getBlockSize()));
        keyData.setBlockSize(Integer.valueOf(agileEncryptionHeader.getBlockSize()));
        passwordKeyEncryptor.setBlockSize(Integer.valueOf(agileEncryptionVerifier.getBlockSize()));
        keyData.setKeyBits(Integer.valueOf(agileEncryptionHeader.getKeySize()));
        passwordKeyEncryptor.setKeyBits(Integer.valueOf(agileEncryptionVerifier.getKeySize()));
        keyData.setHashSize(Integer.valueOf(agileEncryptionHeader.getHashAlgorithm().hashSize));
        passwordKeyEncryptor.setHashSize(Integer.valueOf(agileEncryptionVerifier.getHashAlgorithm().hashSize));
        if (agileEncryptionHeader.getCipherAlgorithm().xmlId.equals(agileEncryptionVerifier.getCipherAlgorithm().xmlId)) {
            keyData.setCipherAlgorithm(agileEncryptionHeader.getCipherAlgorithm());
            passwordKeyEncryptor.setCipherAlgorithm(agileEncryptionHeader.getCipherAlgorithm());
            keyData.setCipherChaining(agileEncryptionHeader.getChainingMode());
            passwordKeyEncryptor.setCipherChaining(agileEncryptionHeader.getChainingMode());
            keyData.setHashAlgorithm(agileEncryptionHeader.getHashAlgorithm());
            passwordKeyEncryptor.setHashAlgorithm(agileEncryptionVerifier.getHashAlgorithm());
            keyData.setSaltValue(agileEncryptionHeader.getKeySalt());
            passwordKeyEncryptor.setSaltValue(agileEncryptionVerifier.getSalt());
            passwordKeyEncryptor.setEncryptedVerifierHashInput(agileEncryptionVerifier.getEncryptedVerifier());
            passwordKeyEncryptor.setEncryptedVerifierHashValue(agileEncryptionVerifier.getEncryptedVerifierHash());
            passwordKeyEncryptor.setEncryptedKeyValue(agileEncryptionVerifier.getEncryptedKey());
            DataIntegrity dataIntegrity = new DataIntegrity();
            encryptionDocument.setDataIntegrity(dataIntegrity);
            dataIntegrity.setEncryptedHmacKey(agileEncryptionHeader.getEncryptedHmacKey());
            dataIntegrity.setEncryptedHmacValue(agileEncryptionHeader.getEncryptedHmacValue());
            return encryptionDocument;
        }
        throw new EncryptedDocumentException("Cipher algorithm of header and verifier have to match");
    }

    /* access modifiers changed from: protected */
    public void marshallEncryptionDocument(EncryptionDocument encryptionDocument, LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
        Document newDocument = XMLHelper.newDocumentBuilder().newDocument();
        encryptionDocument.write(newDocument);
        try {
            Transformer newTransformer = XMLHelper.newTransformer();
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("indent", "no");
            newTransformer.setOutputProperty("standalone", "yes");
            newTransformer.transform(new DOMSource(newDocument), new StreamResult(littleEndianByteArrayOutputStream));
        } catch (TransformerException e) {
            throw new EncryptedDocumentException("error marshalling encryption info document", e);
        }
    }

    private class AgileCipherOutputStream extends ChunkedCipherOutputStream {
        public AgileCipherOutputStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException {
            super(directoryNode, 4096);
        }

        /* access modifiers changed from: protected */
        public Cipher initCipherForBlock(Cipher cipher, int i, boolean z) throws GeneralSecurityException {
            return AgileDecryptor.initCipherForBlock(cipher, i, z, AgileEncryptor.this.getEncryptionInfo(), AgileEncryptor.this.getSecretKey(), 1);
        }

        /* access modifiers changed from: protected */
        public void calculateChecksum(File file, int i) throws GeneralSecurityException, IOException {
            AgileEncryptor.this.updateIntegrityHMAC(file, i);
        }

        /* access modifiers changed from: protected */
        public void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) throws IOException {
            DataSpaceMapUtils.addDefaultDataSpace(directoryNode);
            DataSpaceMapUtils.createEncryptionEntry(directoryNode, EncryptionInfo.ENCRYPTION_INFO_ENTRY, new AgileEncryptor$AgileCipherOutputStream$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: private */
        public void marshallEncryptionRecord(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
            EncryptionInfo encryptionInfo = AgileEncryptor.this.getEncryptionInfo();
            littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMajor());
            littleEndianByteArrayOutputStream.writeShort(encryptionInfo.getVersionMinor());
            littleEndianByteArrayOutputStream.writeInt(encryptionInfo.getEncryptionFlags());
            AgileEncryptor.this.marshallEncryptionDocument(AgileEncryptor.this.createEncryptionDocument(), littleEndianByteArrayOutputStream);
        }
    }

    public AgileEncryptor copy() {
        return new AgileEncryptor(this);
    }
}
