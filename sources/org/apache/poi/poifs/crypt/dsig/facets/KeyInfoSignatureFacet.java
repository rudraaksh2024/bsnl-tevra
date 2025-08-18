package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.Key;
import java.security.KeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import org.apache.jcp.xml.dsig.internal.dom.DOMKeyInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KeyInfoSignatureFacet implements SignatureFacet {
    private static final Logger LOG = LogManager.getLogger((Class<?>) KeyInfoSignatureFacet.class);

    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        DOMSignContext dOMSignContext;
        LOG.atDebug().log("postSign");
        NodeList elementsByTagNameNS = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "Object");
        Node item = elementsByTagNameNS.getLength() == 0 ? null : elementsByTagNameNS.item(0);
        KeyInfoFactory keyInfoFactory = signatureInfo.getKeyInfoFactory();
        ArrayList arrayList = new ArrayList();
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        X509Certificate x509Certificate = signatureConfig.getSigningCertificateChain().get(0);
        ArrayList arrayList2 = new ArrayList();
        if (signatureConfig.isIncludeKeyValue()) {
            try {
                arrayList2.add(keyInfoFactory.newKeyValue(x509Certificate.getPublicKey()));
            } catch (KeyException e) {
                throw new RuntimeException("key exception: " + e.getMessage(), e);
            }
        }
        if (signatureConfig.isIncludeIssuerSerial()) {
            arrayList.add(keyInfoFactory.newX509IssuerSerial(x509Certificate.getIssuerX500Principal().toString(), x509Certificate.getSerialNumber()));
        }
        if (signatureConfig.isIncludeEntireCertificateChain()) {
            arrayList.addAll(signatureConfig.getSigningCertificateChain());
        } else {
            arrayList.add(x509Certificate);
        }
        if (!arrayList.isEmpty()) {
            arrayList2.add(keyInfoFactory.newX509Data(arrayList));
        }
        DOMKeyInfo newKeyInfo = keyInfoFactory.newKeyInfo(arrayList2);
        AnonymousClass1 r4 = new Key() {
            private static final long serialVersionUID = 1;

            public String getAlgorithm() {
                return null;
            }

            public byte[] getEncoded() {
                return null;
            }

            public String getFormat() {
                return null;
            }
        };
        Element documentElement = document.getDocumentElement();
        if (item != null) {
            dOMSignContext = new DOMSignContext(r4, documentElement, item);
        }
        signatureConfig.getNamespacePrefixes().forEach(new KeyInfoSignatureFacet$$ExternalSyntheticLambda0(dOMSignContext));
        newKeyInfo.marshal(new DOMStructure(documentElement), dOMSignContext);
        if (item != null) {
            NodeList elementsByTagNameNS2 = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "KeyInfo");
            if (elementsByTagNameNS2.getLength() == 1) {
                item.getParentNode().insertBefore(elementsByTagNameNS2.item(0), item);
                return;
            }
            throw new RuntimeException("KeyInfo wasn't set");
        }
    }
}
