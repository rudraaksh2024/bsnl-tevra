package org.apache.poi.poifs.crypt.dsig;

import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyInfoKeySelector extends KeySelector implements KeySelectorResult {
    private static final Logger LOG = LogManager.getLogger((Class<?>) KeyInfoKeySelector.class);
    private final List<X509Certificate> certChain = new ArrayList();

    public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod algorithmMethod, XMLCryptoContext xMLCryptoContext) throws KeySelectorException {
        LOG.atDebug().log("select key");
        if (keyInfo != null) {
            List<X509Data> content = keyInfo.getContent();
            this.certChain.clear();
            for (X509Data x509Data : content) {
                if (x509Data instanceof X509Data) {
                    for (Object next : x509Data.getContent()) {
                        if (next instanceof X509Certificate) {
                            X509Certificate x509Certificate = (X509Certificate) next;
                            LOG.atDebug().log("certificate: {}", (Object) x509Certificate.getSubjectX500Principal());
                            this.certChain.add(x509Certificate);
                        }
                    }
                }
            }
            if (!this.certChain.isEmpty()) {
                return this;
            }
            throw new KeySelectorException("No key found!");
        }
        throw new KeySelectorException("no ds:KeyInfo present");
    }

    public Key getKey() {
        if (this.certChain.isEmpty()) {
            return null;
        }
        return this.certChain.get(0).getPublicKey();
    }

    public X509Certificate getSigner() {
        if (this.certChain.isEmpty()) {
            return null;
        }
        return this.certChain.get(0);
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }
}
