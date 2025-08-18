package org.apache.poi.poifs.crypt.dsig.facets;

import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.w3c.dom.Document;

public class EnvelopedSignatureFacet implements SignatureFacet {
    public void preSign(SignatureInfo signatureInfo, Document document, List<Reference> list, List<XMLObject> list2) throws XMLSignatureException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/2000/09/xmldsig#enveloped-signature"));
        arrayList.add(SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/2001/10/xml-exc-c14n#"));
        list.add(SignatureFacetHelper.newReference(signatureInfo, "", arrayList, (String) null));
    }
}
