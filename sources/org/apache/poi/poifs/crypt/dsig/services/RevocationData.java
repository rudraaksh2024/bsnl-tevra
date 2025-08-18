package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class RevocationData {
    private final List<byte[]> crls = new ArrayList();
    private final List<byte[]> ocsps = new ArrayList();
    private final List<X509Certificate> x509chain = new ArrayList();

    public void addCRL(byte[] bArr) {
        if (this.crls.stream().noneMatch(new RevocationData$$ExternalSyntheticLambda0(bArr))) {
            this.crls.add(bArr);
        }
    }

    public void addCRL(X509CRL x509crl) {
        try {
            addCRL(x509crl.getEncoded());
        } catch (CRLException e) {
            throw new IllegalArgumentException("CRL coding error: " + e.getMessage(), e);
        }
    }

    public void addOCSP(byte[] bArr) {
        this.ocsps.add(bArr);
    }

    public void addCertificate(X509Certificate x509Certificate) {
        this.x509chain.add(x509Certificate);
    }

    public List<byte[]> getCRLs() {
        return this.crls;
    }

    public List<byte[]> getOCSPs() {
        return this.ocsps;
    }

    public boolean hasOCSPs() {
        return !this.ocsps.isEmpty();
    }

    public boolean hasCRLs() {
        return !this.crls.isEmpty();
    }

    public boolean hasRevocationDataEntries() {
        return hasOCSPs() || hasCRLs();
    }

    public List<X509Certificate> getX509chain() {
        return this.x509chain;
    }
}
