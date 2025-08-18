package org.apache.poi.poifs.crypt.agile;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.GenericRecordUtil;

public class AgileEncryptionHeader extends EncryptionHeader {
    private byte[] encryptedHmacKey;
    private byte[] encryptedHmacValue;

    public AgileEncryptionHeader(String str) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(str));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AgileEncryptionHeader(org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            byte[] r0 = r3.encryptedHmacKey
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x0010
        L_0x000a:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0010:
            r2.encryptedHmacKey = r0
            byte[] r3 = r3.encryptedHmacValue
            if (r3 != 0) goto L_0x0017
            goto L_0x001e
        L_0x0017:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x001e:
            r2.encryptedHmacValue = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader.<init>(org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader):void");
    }

    protected AgileEncryptionHeader(EncryptionDocument encryptionDocument) {
        try {
            KeyData keyData = encryptionDocument.getKeyData();
            if (keyData != null) {
                int intValue = keyData.getKeyBits().intValue();
                CipherAlgorithm cipherAlgorithm = keyData.getCipherAlgorithm();
                setCipherAlgorithm(cipherAlgorithm);
                setCipherProvider(cipherAlgorithm.provider);
                setKeySize(intValue);
                setFlags(0);
                setSizeExtra(0);
                setCspName((String) null);
                setBlockSize(keyData.getBlockSize().intValue());
                setChainingMode(keyData.getCipherChaining());
                if (getChainingMode() == ChainingMode.cbc || getChainingMode() == ChainingMode.cfb) {
                    int intValue2 = keyData.getHashSize().intValue();
                    setHashAlgorithm(keyData.getHashAlgorithm());
                    if (getHashAlgorithm().hashSize == intValue2) {
                        int intValue3 = keyData.getSaltSize().intValue();
                        setKeySalt(keyData.getSaltValue());
                        if (getKeySalt().length == intValue3) {
                            DataIntegrity dataIntegrity = encryptionDocument.getDataIntegrity();
                            setEncryptedHmacKey(dataIntegrity.getEncryptedHmacKey());
                            setEncryptedHmacValue(dataIntegrity.getEncryptedHmacValue());
                            return;
                        }
                        throw new EncryptedDocumentException("Invalid salt length");
                    }
                    throw new EncryptedDocumentException("Unsupported hash algorithm: " + keyData.getHashAlgorithm() + " @ " + intValue2 + " bytes");
                }
                throw new EncryptedDocumentException("Unsupported chaining mode - " + keyData.getCipherChaining());
            }
            throw new NullPointerException("keyData not set");
        } catch (Exception unused) {
            throw new EncryptedDocumentException("Unable to parse keyData");
        }
    }

    public AgileEncryptionHeader(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int i, int i2, ChainingMode chainingMode) {
        setCipherAlgorithm(cipherAlgorithm);
        setHashAlgorithm(hashAlgorithm);
        setKeySize(i);
        setBlockSize(i2);
        setChainingMode(chainingMode);
    }

    public void setKeySalt(byte[] bArr) {
        if (bArr == null || bArr.length != getBlockSize()) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setKeySalt(bArr);
    }

    public byte[] getEncryptedHmacKey() {
        return this.encryptedHmacKey;
    }

    /* access modifiers changed from: protected */
    public void setEncryptedHmacKey(byte[] bArr) {
        this.encryptedHmacKey = bArr == null ? null : (byte[]) bArr.clone();
    }

    public byte[] getEncryptedHmacValue() {
        return this.encryptedHmacValue;
    }

    /* access modifiers changed from: protected */
    public void setEncryptedHmacValue(byte[] bArr) {
        this.encryptedHmacValue = bArr == null ? null : (byte[]) bArr.clone();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new AgileEncryptionHeader$$ExternalSyntheticLambda0(this), "encryptedHmacKey", new AgileEncryptionHeader$$ExternalSyntheticLambda1(this), "encryptedHmacValue", new AgileEncryptionHeader$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-poifs-crypt-agile-AgileEncryptionHeader  reason: not valid java name */
    public /* synthetic */ Object m2216lambda$getGenericProperties$0$orgapachepoipoifscryptagileAgileEncryptionHeader() {
        return super.getGenericProperties();
    }

    public AgileEncryptionHeader copy() {
        return new AgileEncryptionHeader(this);
    }
}
