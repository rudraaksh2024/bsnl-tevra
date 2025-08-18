package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;

public class EncryptionInfo implements GenericRecord {
    public static final String ENCRYPTION_INFO_ENTRY = "EncryptionInfo";
    private static final int[] FLAGS_MASKS = {4, 8, 16, 32};
    private static final String[] FLAGS_NAMES = {"CRYPTO_API", "DOC_PROPS", "EXTERNAL", "AES"};
    public static final BitField flagAES = BitFieldFactory.getInstance(32);
    public static final BitField flagCryptoAPI = BitFieldFactory.getInstance(4);
    public static final BitField flagDocProps = BitFieldFactory.getInstance(8);
    public static final BitField flagExternal = BitFieldFactory.getInstance(16);
    private Decryptor decryptor;
    private final int encryptionFlags;
    private final EncryptionMode encryptionMode;
    private Encryptor encryptor;
    private EncryptionHeader header;
    private EncryptionVerifier verifier;
    private final int versionMajor;
    private final int versionMinor;

    public EncryptionInfo(POIFSFileSystem pOIFSFileSystem) throws IOException {
        this(pOIFSFileSystem.getRoot());
    }

    public EncryptionInfo(DirectoryNode directoryNode) throws IOException {
        this(directoryNode.createDocumentInputStream(ENCRYPTION_INFO_ENTRY), (EncryptionMode) null);
    }

    public EncryptionInfo(LittleEndianInput littleEndianInput, EncryptionMode encryptionMode2) throws IOException {
        if (encryptionMode2 == EncryptionMode.xor) {
            this.versionMajor = EncryptionMode.xor.versionMajor;
            this.versionMinor = EncryptionMode.xor.versionMinor;
        } else {
            this.versionMajor = littleEndianInput.readUShort();
            this.versionMinor = littleEndianInput.readUShort();
        }
        if (this.versionMajor == EncryptionMode.xor.versionMajor && this.versionMinor == EncryptionMode.xor.versionMinor) {
            this.encryptionMode = EncryptionMode.xor;
            this.encryptionFlags = -1;
        } else if (this.versionMajor == EncryptionMode.binaryRC4.versionMajor && this.versionMinor == EncryptionMode.binaryRC4.versionMinor) {
            this.encryptionMode = EncryptionMode.binaryRC4;
            this.encryptionFlags = -1;
        } else {
            int i = this.versionMajor;
            if (2 <= i && i <= 4 && this.versionMinor == 2) {
                int readInt = littleEndianInput.readInt();
                this.encryptionFlags = readInt;
                this.encryptionMode = (encryptionMode2 == EncryptionMode.cryptoAPI || !flagAES.isSet(readInt)) ? EncryptionMode.cryptoAPI : EncryptionMode.standard;
            } else if (i == EncryptionMode.agile.versionMajor && this.versionMinor == EncryptionMode.agile.versionMinor) {
                this.encryptionMode = EncryptionMode.agile;
                this.encryptionFlags = littleEndianInput.readInt();
            } else {
                int readInt2 = littleEndianInput.readInt();
                this.encryptionFlags = readInt2;
                throw new EncryptedDocumentException("Unknown encryption: version major: " + this.versionMajor + " / version minor: " + this.versionMinor + " / fCrypto: " + flagCryptoAPI.isSet(readInt2) + " / fExternal: " + flagExternal.isSet(readInt2) + " / fDocProps: " + flagDocProps.isSet(readInt2) + " / fAES: " + flagAES.isSet(readInt2));
            }
        }
        try {
            getBuilder(this.encryptionMode).initialize(this, littleEndianInput);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public EncryptionInfo(EncryptionMode encryptionMode2) {
        this(encryptionMode2, (CipherAlgorithm) null, (HashAlgorithm) null, -1, -1, (ChainingMode) null);
    }

    public EncryptionInfo(EncryptionMode encryptionMode2, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        this.encryptionMode = encryptionMode2;
        this.versionMajor = encryptionMode2.versionMajor;
        this.versionMinor = encryptionMode2.versionMinor;
        this.encryptionFlags = encryptionMode2.encryptionFlags;
        try {
            getBuilder(encryptionMode2).initialize(this, cipherAlgorithm, hashAlgorithm, i, i2, chainingMode);
        } catch (Exception e) {
            throw new EncryptedDocumentException((Throwable) e);
        }
    }

    public EncryptionInfo(EncryptionInfo encryptionInfo) {
        this.encryptionMode = encryptionInfo.encryptionMode;
        this.versionMajor = encryptionInfo.versionMajor;
        this.versionMinor = encryptionInfo.versionMinor;
        this.encryptionFlags = encryptionInfo.encryptionFlags;
        EncryptionHeader encryptionHeader = encryptionInfo.header;
        EncryptionVerifier encryptionVerifier = null;
        this.header = encryptionHeader == null ? null : encryptionHeader.copy();
        EncryptionVerifier encryptionVerifier2 = encryptionInfo.verifier;
        this.verifier = encryptionVerifier2 != null ? encryptionVerifier2.copy() : encryptionVerifier;
        Decryptor decryptor2 = encryptionInfo.decryptor;
        if (decryptor2 != null) {
            Decryptor copy = decryptor2.copy();
            this.decryptor = copy;
            copy.setEncryptionInfo(this);
        }
        Encryptor encryptor2 = encryptionInfo.encryptor;
        if (encryptor2 != null) {
            Encryptor copy2 = encryptor2.copy();
            this.encryptor = copy2;
            copy2.setEncryptionInfo(this);
        }
    }

    private static EncryptionInfoBuilder getBuilder(EncryptionMode encryptionMode2) {
        return encryptionMode2.builder.get();
    }

    public int getVersionMajor() {
        return this.versionMajor;
    }

    public int getVersionMinor() {
        return this.versionMinor;
    }

    public int getEncryptionFlags() {
        return this.encryptionFlags;
    }

    public EncryptionHeader getHeader() {
        return this.header;
    }

    public EncryptionVerifier getVerifier() {
        return this.verifier;
    }

    public Decryptor getDecryptor() {
        return this.decryptor;
    }

    public Encryptor getEncryptor() {
        return this.encryptor;
    }

    public void setHeader(EncryptionHeader encryptionHeader) {
        this.header = encryptionHeader;
    }

    public void setVerifier(EncryptionVerifier encryptionVerifier) {
        this.verifier = encryptionVerifier;
    }

    public void setDecryptor(Decryptor decryptor2) {
        this.decryptor = decryptor2;
    }

    public void setEncryptor(Encryptor encryptor2) {
        this.encryptor = encryptor2;
    }

    public EncryptionMode getEncryptionMode() {
        return this.encryptionMode;
    }

    public boolean isDocPropsEncrypted() {
        return !flagDocProps.isSet(getEncryptionFlags());
    }

    public EncryptionInfo copy() {
        return new EncryptionInfo(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("encryptionMode", new EncryptionInfo$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("versionMajor", new EncryptionInfo$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("versionMinor", new EncryptionInfo$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("encryptionFlags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new EncryptionInfo$$ExternalSyntheticLambda3(this), FLAGS_MASKS, FLAGS_NAMES));
        linkedHashMap.put("header", new EncryptionInfo$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("verifier", new EncryptionInfo$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("decryptor", new EncryptionInfo$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("encryptor", new EncryptionInfo$$ExternalSyntheticLambda7(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
