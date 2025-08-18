package org.apache.poi.poifs.crypt.dsig.facets;

import javax.xml.crypto.MarshalException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.xmlbeans.XmlException;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Office2010SignatureFacet implements SignatureFacet {
    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        NodeList elementsByTagNameNS = document.getElementsByTagNameNS(SignatureFacet.XADES_132_NS, "QualifyingProperties");
        if (elementsByTagNameNS.getLength() == 1) {
            try {
                QualifyingPropertiesType parse = QualifyingPropertiesType.Factory.parse(elementsByTagNameNS.item(0), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                UnsignedPropertiesType unsignedProperties = parse.getUnsignedProperties();
                if (unsignedProperties == null) {
                    unsignedProperties = parse.addNewUnsignedProperties();
                }
                if (unsignedProperties.getUnsignedSignatureProperties() == null) {
                    unsignedProperties.addNewUnsignedSignatureProperties();
                }
                elementsByTagNameNS.item(0).getParentNode().replaceChild(document.importNode(parse.getDomNode().getFirstChild(), true), elementsByTagNameNS.item(0));
            } catch (XmlException e) {
                throw new MarshalException(e);
            }
        } else {
            throw new MarshalException("no XAdES-BES extension present");
        }
    }
}
