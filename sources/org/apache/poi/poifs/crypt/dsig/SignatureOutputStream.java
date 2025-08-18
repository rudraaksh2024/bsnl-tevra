package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import org.apache.poi.poifs.crypt.HashAlgorithm;

class SignatureOutputStream extends DigestOutputStream {
    Signature signature;

    SignatureOutputStream(HashAlgorithm hashAlgorithm, PrivateKey privateKey) {
        super(hashAlgorithm, privateKey);
    }

    public void init() throws GeneralSecurityException {
        String str = isMSCapi(this.key) ? "SunMSCAPI" : "SunRsaSign";
        if (Security.getProvider(str) != null) {
            this.signature = Signature.getInstance(this.algo.ecmaString + "withRSA", str);
        } else {
            this.signature = Signature.getInstance(this.algo.ecmaString + "withRSA");
        }
        this.signature.initSign(this.key);
    }

    public byte[] sign() throws SignatureException {
        return this.signature.sign();
    }

    public void write(int i) throws IOException {
        try {
            this.signature.update((byte) i);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.signature.update(bArr, i, i2);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }
}
