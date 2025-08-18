package org.apache.poi.poifs.crypt.standard;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CipherProvider;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public class StandardEncryptionHeader extends EncryptionHeader implements EncryptionRecord {
    protected StandardEncryptionHeader(StandardEncryptionHeader standardEncryptionHeader) {
        super(standardEncryptionHeader);
    }

    protected StandardEncryptionHeader(LittleEndianInput littleEndianInput) throws IOException {
        setFlags(littleEndianInput.readInt());
        setSizeExtra(littleEndianInput.readInt());
        setCipherAlgorithm(CipherAlgorithm.fromEcmaId(littleEndianInput.readInt()));
        setHashAlgorithm(HashAlgorithm.fromEcmaId(littleEndianInput.readInt()));
        int readInt = littleEndianInput.readInt();
        setKeySize(readInt == 0 ? 40 : readInt);
        setBlockSize(getKeySize());
        setCipherProvider(CipherProvider.fromEcmaId(littleEndianInput.readInt()));
        littleEndianInput.readLong();
        boolean z = littleEndianInput instanceof RecordInputStream;
        if (z) {
            ((RecordInputStream) littleEndianInput).mark(5);
        } else {
            ((InputStream) littleEndianInput).mark(5);
        }
        int readInt2 = littleEndianInput.readInt();
        if (z) {
            ((RecordInputStream) littleEndianInput).reset();
        } else {
            ((InputStream) littleEndianInput).reset();
        }
        if (readInt2 == 16) {
            setCspName("");
        } else {
            StringBuilder sb = new StringBuilder();
            while (true) {
                char readShort = (char) littleEndianInput.readShort();
                if (readShort == 0) {
                    break;
                }
                sb.append(readShort);
            }
            setCspName(sb.toString());
        }
        setChainingMode(ChainingMode.ecb);
        setKeySalt((byte[]) null);
    }

    protected StandardEncryptionHeader(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        setCipherAlgorithm(cipherAlgorithm);
        setHashAlgorithm(hashAlgorithm);
        setKeySize(i);
        setBlockSize(i2);
        setCipherProvider(cipherAlgorithm.provider);
        setFlags(EncryptionInfo.flagAES.setBoolean(0, cipherAlgorithm.provider != CipherProvider.aes ? false : true) | EncryptionInfo.flagCryptoAPI.setBoolean(0, true));
    }

    public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
        int writeIndex = littleEndianByteArrayOutputStream.getWriteIndex();
        LittleEndianOutput createDelayedOutput = littleEndianByteArrayOutputStream.createDelayedOutput(4);
        littleEndianByteArrayOutputStream.writeInt(getFlags());
        littleEndianByteArrayOutputStream.writeInt(0);
        littleEndianByteArrayOutputStream.writeInt(getCipherAlgorithm().ecmaId);
        littleEndianByteArrayOutputStream.writeInt(getHashAlgorithm().ecmaId);
        littleEndianByteArrayOutputStream.writeInt(getKeySize());
        littleEndianByteArrayOutputStream.writeInt(getCipherProvider().ecmaId);
        littleEndianByteArrayOutputStream.writeInt(0);
        littleEndianByteArrayOutputStream.writeInt(0);
        String cspName = getCspName();
        if (cspName == null) {
            cspName = getCipherProvider().cipherProviderName;
        }
        littleEndianByteArrayOutputStream.write(StringUtil.getToUnicodeLE(cspName));
        littleEndianByteArrayOutputStream.writeShort(0);
        createDelayedOutput.writeInt((littleEndianByteArrayOutputStream.getWriteIndex() - writeIndex) - 4);
    }

    public StandardEncryptionHeader copy() {
        return new StandardEncryptionHeader(this);
    }
}
