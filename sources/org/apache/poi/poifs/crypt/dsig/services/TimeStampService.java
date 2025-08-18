package org.apache.poi.poifs.crypt.dsig.services;

import org.apache.poi.poifs.crypt.dsig.SignatureInfo;

public interface TimeStampService {
    byte[] timeStamp(SignatureInfo signatureInfo, byte[] bArr, RevocationData revocationData) throws Exception;
}
