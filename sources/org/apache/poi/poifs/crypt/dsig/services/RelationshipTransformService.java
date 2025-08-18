package org.apache.poi.poifs.crypt.dsig.services;

import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.TransformService;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.ApacheNodeSetData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.RelationshipReferenceDocument;
import org.w3.x2000.x09.xmldsig.TransformDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RelationshipTransformService extends TransformService {
    private static final Logger LOG = LogManager.getLogger((Class<?>) RelationshipTransformService.class);
    public static final String TRANSFORM_URI = "http://schemas.openxmlformats.org/package/2006/RelationshipTransform";
    private final List<String> sourceIds = new ArrayList();

    public static class RelationshipTransformParameterSpec implements TransformParameterSpec {
        List<String> sourceIds = new ArrayList();

        public void addRelationshipReference(String str) {
            this.sourceIds.add(str);
        }

        public boolean hasSourceIds() {
            return !this.sourceIds.isEmpty();
        }
    }

    private static final class POIXmlDsigProvider extends Provider {
        private static final String NAME = "POIXmlDsigProvider";
        static final long serialVersionUID = 1;

        private POIXmlDsigProvider() {
            super(NAME, 1.0d, NAME);
            put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform", RelationshipTransformService.class.getName());
            put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform MechanismType", "DOM");
        }
    }

    public RelationshipTransformService() {
        LOG.atDebug().log("constructor");
    }

    public static synchronized void registerDsigProvider() {
        synchronized (RelationshipTransformService.class) {
            if (Security.getProvider("POIXmlDsigProvider") == null) {
                Security.addProvider(new POIXmlDsigProvider());
            }
        }
    }

    public void init(TransformParameterSpec transformParameterSpec) throws InvalidAlgorithmParameterException {
        LOG.atDebug().log("init(params)");
        if (transformParameterSpec instanceof RelationshipTransformParameterSpec) {
            this.sourceIds.addAll(((RelationshipTransformParameterSpec) transformParameterSpec).sourceIds);
            return;
        }
        throw new InvalidAlgorithmParameterException();
    }

    public void init(XMLStructure xMLStructure, XMLCryptoContext xMLCryptoContext) throws InvalidAlgorithmParameterException {
        Logger logger = LOG;
        logger.atDebug().log("init(parent,context)");
        logger.atDebug().log("parent java type: {}", (Object) xMLStructure.getClass().getName());
        try {
            XmlObject[] selectChildren = TransformDocument.Factory.parse(((DOMStructure) xMLStructure).getNode(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getTransform().selectChildren(RelationshipReferenceDocument.type.getDocumentElementName());
            if (selectChildren.length == 0) {
                logger.atWarn().log("no RelationshipReference/@SourceId parameters present");
            }
            for (XmlObject xmlObject : selectChildren) {
                String sourceId = ((CTRelationshipReference) xmlObject).getSourceId();
                LOG.atDebug().log("sourceId: {}", (Object) sourceId);
                this.sourceIds.add(sourceId);
            }
        } catch (XmlException e) {
            throw new InvalidAlgorithmParameterException(e);
        }
    }

    public void marshalParams(XMLStructure xMLStructure, XMLCryptoContext xMLCryptoContext) throws MarshalException {
        LOG.atDebug().log("marshallParams(parent,context)");
        Element element = (Element) ((DOMStructure) xMLStructure).getNode();
        Document ownerDocument = element.getOwnerDocument();
        for (String attribute : this.sourceIds) {
            Element createElementNS = ownerDocument.createElementNS("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi:RelationshipReference");
            createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:mdssi", "http://schemas.openxmlformats.org/package/2006/digital-signature");
            createElementNS.setAttribute("SourceId", attribute);
            element.appendChild(createElementNS);
        }
    }

    public AlgorithmParameterSpec getParameterSpec() {
        LOG.atDebug().log("getParameterSpec");
        return null;
    }

    public Data transform(Data data, XMLCryptoContext xMLCryptoContext) throws TransformException {
        Logger logger = LOG;
        logger.atDebug().log("transform(data,context)");
        logger.atDebug().log("data java type: {}", (Object) data.getClass().getName());
        OctetStreamData octetStreamData = (OctetStreamData) data;
        logger.atDebug().log("URI: {}", (Object) octetStreamData.getURI());
        try {
            Element documentElement = DocumentHelper.readDocument(octetStreamData.getOctetStream()).getDocumentElement();
            NodeList childNodes = documentElement.getChildNodes();
            TreeMap treeMap = new TreeMap();
            for (int length = childNodes.getLength() - 1; length >= 0; length--) {
                Node item = childNodes.item(length);
                if (PackageRelationship.RELATIONSHIP_TAG_NAME.equals(item.getLocalName())) {
                    Element element = (Element) item;
                    String attribute = element.getAttribute(PackageRelationship.ID_ATTRIBUTE_NAME);
                    if (this.sourceIds.contains(attribute)) {
                        String attribute2 = element.getAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME);
                        if (attribute2 == null || attribute2.isEmpty()) {
                            element.setAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME, "Internal");
                        }
                        treeMap.put(attribute, element);
                    }
                }
                documentElement.removeChild(item);
            }
            for (Element appendChild : treeMap.values()) {
                documentElement.appendChild(appendChild);
            }
            LOG.atDebug().log("# Relationship elements: {}", (Object) Unbox.box(treeMap.size()));
            return new ApacheNodeSetData(new XMLSignatureInput(documentElement));
        } catch (Exception e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    public Data transform(Data data, XMLCryptoContext xMLCryptoContext, OutputStream outputStream) throws TransformException {
        LOG.atDebug().log("transform(data,context,os)");
        return null;
    }

    public boolean isFeatureSupported(String str) {
        LOG.atDebug().log("isFeatureSupported(feature)");
        return false;
    }
}
