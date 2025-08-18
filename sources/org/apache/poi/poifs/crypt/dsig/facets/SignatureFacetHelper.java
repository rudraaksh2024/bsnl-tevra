package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.GeneralSecurityException;
import java.util.List;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.util.Internal;

@Internal
final class SignatureFacetHelper {
    private SignatureFacetHelper() {
    }

    static Transform newTransform(SignatureInfo signatureInfo, String str) throws XMLSignatureException {
        return newTransform(signatureInfo, str, (TransformParameterSpec) null);
    }

    static Transform newTransform(SignatureInfo signatureInfo, String str, TransformParameterSpec transformParameterSpec) throws XMLSignatureException {
        try {
            return signatureInfo.getSignatureFactory().newTransform(str, transformParameterSpec);
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException("unknown canonicalization method: " + str, e);
        }
    }

    static Reference newReference(SignatureInfo signatureInfo, String str, List<Transform> list, String str2) throws XMLSignatureException {
        String digestMethodUri = signatureInfo.getSignatureConfig().getDigestMethodUri();
        XMLSignatureFactory signatureFactory = signatureInfo.getSignatureFactory();
        try {
            return signatureFactory.newReference(str, signatureFactory.newDigestMethod(digestMethodUri, (DigestMethodParameterSpec) null), list, str2, (String) null);
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException("unknown digest method uri: " + digestMethodUri, e);
        }
    }
}
