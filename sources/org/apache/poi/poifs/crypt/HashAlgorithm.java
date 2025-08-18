package org.apache.poi.poifs.crypt;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.poi.EncryptedDocumentException;

public enum HashAlgorithm {
    none("", 0, "", 0, "", false, ""),
    sha1(MessageDigestAlgorithms.SHA_1, 32772, "SHA1", 20, "HmacSHA1", false, "1.3.14.3.2.26"),
    sha256(MessageDigestAlgorithms.SHA_256, 32780, "SHA256", 32, "HmacSHA256", false, "2.16.840.1.101.3.4.2.1"),
    sha384(MessageDigestAlgorithms.SHA_384, 32781, "SHA384", 48, "HmacSHA384", false, "2.16.840.1.101.3.4.2.2"),
    sha512(MessageDigestAlgorithms.SHA_512, 32782, "SHA512", 64, "HmacSHA512", false, "2.16.840.1.101.3.4.2.3"),
    md5(MessageDigestAlgorithms.MD5, -1, MessageDigestAlgorithms.MD5, 16, "HmacMD5", false, "1.2.840.113549.2.5"),
    md2(MessageDigestAlgorithms.MD2, -1, MessageDigestAlgorithms.MD2, 16, "Hmac-MD2", true, "1.2.840.113549.2.2"),
    md4("MD4", -1, "MD4", 16, "Hmac-MD4", true, "1.2.840.113549.2.4"),
    ripemd128("RipeMD128", -1, "RIPEMD-128", 16, "HMac-RipeMD128", true, "1.3.36.3.2.2"),
    ripemd160("RipeMD160", -1, "RIPEMD-160", 20, "HMac-RipeMD160", true, "1.3.36.3.2.1"),
    whirlpool("Whirlpool", -1, "WHIRLPOOL", 64, "HMac-Whirlpool", true, "1.0.10118.3.0.55"),
    sha224(MessageDigestAlgorithms.SHA_224, -1, "SHA224", 28, "HmacSHA224", true, "2.16.840.1.101.3.4.2.4"),
    ripemd256("RipeMD256", -1, "RIPEMD-256", 32, "HMac-RipeMD256", true, "1.3.36.3.2.3");
    
    public final int ecmaId;
    public final String ecmaString;
    public final int hashSize;
    public final String jceHmacId;
    public final String jceId;
    public final boolean needsBouncyCastle;
    public final String rsaOid;

    private HashAlgorithm(String str, int i, String str2, int i2, String str3, boolean z, String str4) {
        this.jceId = str;
        this.ecmaId = i;
        this.ecmaString = str2;
        this.hashSize = i2;
        this.jceHmacId = str3;
        this.needsBouncyCastle = z;
        this.rsaOid = str4;
    }

    public static HashAlgorithm fromEcmaId(int i) {
        for (HashAlgorithm hashAlgorithm : values()) {
            if (hashAlgorithm.ecmaId == i) {
                return hashAlgorithm;
            }
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }

    public static HashAlgorithm fromEcmaId(String str) {
        for (HashAlgorithm hashAlgorithm : values()) {
            if (hashAlgorithm.ecmaString.equals(str)) {
                return hashAlgorithm;
            }
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }

    public static HashAlgorithm fromString(String str) {
        for (HashAlgorithm hashAlgorithm : values()) {
            if (hashAlgorithm.ecmaString.equalsIgnoreCase(str) || hashAlgorithm.jceId.equalsIgnoreCase(str)) {
                return hashAlgorithm;
            }
        }
        throw new EncryptedDocumentException("hash algorithm not found");
    }
}
