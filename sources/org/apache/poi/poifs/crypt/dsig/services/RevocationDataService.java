package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.X509Certificate;
import java.util.List;

public interface RevocationDataService {
    RevocationData getRevocationData(List<X509Certificate> list);
}
