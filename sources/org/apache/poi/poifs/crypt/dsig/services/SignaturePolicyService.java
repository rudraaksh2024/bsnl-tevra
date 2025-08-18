package org.apache.poi.poifs.crypt.dsig.services;

public interface SignaturePolicyService {
    String getSignaturePolicyDescription();

    byte[] getSignaturePolicyDocument();

    String getSignaturePolicyDownloadUrl();

    String getSignaturePolicyIdentifier();
}
