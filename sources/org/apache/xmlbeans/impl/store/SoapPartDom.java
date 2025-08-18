package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

class SoapPartDom extends SOAPPart implements DomImpl.Dom, Document, NodeList {
    SoapPartDocXobj _docXobj;

    public NamedNodeMap getAttributes() {
        return null;
    }

    public NodeList getChildNodes() {
        return this;
    }

    public String name() {
        return "#document";
    }

    public boolean nodeCanHavePrefixUri() {
        return true;
    }

    public int nodeType() {
        return 9;
    }

    SoapPartDom(SoapPartDocXobj soapPartDocXobj) {
        this._docXobj = soapPartDocXobj;
    }

    public Locale locale() {
        return this._docXobj._locale;
    }

    public Cur tempCur() {
        return this._docXobj.tempCur();
    }

    public QName getQName() {
        return this._docXobj._name;
    }

    public void dump() {
        dump(System.out);
    }

    public void dump(PrintStream printStream) {
        this._docXobj.dump(printStream);
    }

    public void dump(PrintStream printStream, Object obj) {
        this._docXobj.dump(printStream, obj);
    }

    public Node appendChild(Node node) {
        return DomImpl._node_appendChild(this, node);
    }

    public Node cloneNode(boolean z) {
        return DomImpl._node_cloneNode(this, z);
    }

    public Node getParentNode() {
        return DomImpl._node_getParentNode(this);
    }

    public Node removeChild(Node node) {
        return DomImpl._node_removeChild(this, node);
    }

    public Node getFirstChild() {
        return DomImpl._node_getFirstChild(this);
    }

    public Node getLastChild() {
        return DomImpl._node_getLastChild(this);
    }

    public String getLocalName() {
        return DomImpl._node_getLocalName(this);
    }

    public String getNamespaceURI() {
        return DomImpl._node_getNamespaceURI(this);
    }

    public Node getNextSibling() {
        return DomImpl._node_getNextSibling(this);
    }

    public String getNodeName() {
        return DomImpl._node_getNodeName(this);
    }

    public short getNodeType() {
        return DomImpl._node_getNodeType(this);
    }

    public String getNodeValue() {
        return DomImpl._node_getNodeValue(this);
    }

    public Document getOwnerDocument() {
        return DomImpl._node_getOwnerDocument(this);
    }

    public String getPrefix() {
        return DomImpl._node_getPrefix(this);
    }

    public Node getPreviousSibling() {
        return DomImpl._node_getPreviousSibling(this);
    }

    public boolean hasAttributes() {
        return DomImpl._node_hasAttributes(this);
    }

    public boolean hasChildNodes() {
        return DomImpl._node_hasChildNodes(this);
    }

    public Node insertBefore(Node node, Node node2) {
        return DomImpl._node_insertBefore(this, node, node2);
    }

    public boolean isSupported(String str, String str2) {
        return DomImpl._node_isSupported(this, str, str2);
    }

    public void normalize() {
        DomImpl._node_normalize(this);
    }

    public Node replaceChild(Node node, Node node2) {
        return DomImpl._node_replaceChild(this, node, node2);
    }

    public void setNodeValue(String str) {
        DomImpl._node_setNodeValue(this, str);
    }

    public void setPrefix(String str) {
        DomImpl._node_setPrefix(this, str);
    }

    public Object getUserData(String str) {
        return DomImpl._node_getUserData(this, str);
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        return DomImpl._node_setUserData(this, str, obj, userDataHandler);
    }

    public Object getFeature(String str, String str2) {
        return DomImpl._node_getFeature(this, str, str2);
    }

    public boolean isEqualNode(Node node) {
        return DomImpl._node_isEqualNode(this, node);
    }

    public boolean isSameNode(Node node) {
        return DomImpl._node_isSameNode(this, node);
    }

    public String lookupNamespaceURI(String str) {
        return DomImpl._node_lookupNamespaceURI(this, str);
    }

    public String lookupPrefix(String str) {
        return DomImpl._node_lookupPrefix(this, str);
    }

    public boolean isDefaultNamespace(String str) {
        return DomImpl._node_isDefaultNamespace(this, str);
    }

    public void setTextContent(String str) {
        DomImpl._node_setTextContent(this, str);
    }

    public String getTextContent() {
        return DomImpl._node_getTextContent(this);
    }

    public short compareDocumentPosition(Node node) {
        return DomImpl._node_compareDocumentPosition(this, node);
    }

    public String getBaseURI() {
        return DomImpl._node_getBaseURI(this);
    }

    public Node adoptNode(Node node) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public String getDocumentURI() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public DOMConfiguration getDomConfig() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public String getInputEncoding() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public boolean getStrictErrorChecking() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public String getXmlEncoding() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public boolean getXmlStandalone() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public String getXmlVersion() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void normalizeDocument() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public Node renameNode(Node node, String str, String str2) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setDocumentURI(String str) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setStrictErrorChecking(boolean z) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setXmlStandalone(boolean z) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setXmlVersion(String str) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public Attr createAttribute(String str) {
        return DomImpl._document_createAttribute(this, str);
    }

    public Attr createAttributeNS(String str, String str2) {
        return DomImpl._document_createAttributeNS(this, str, str2);
    }

    public CDATASection createCDATASection(String str) {
        return DomImpl._document_createCDATASection(this, str);
    }

    public Comment createComment(String str) {
        return DomImpl._document_createComment(this, str);
    }

    public DocumentFragment createDocumentFragment() {
        return DomImpl._document_createDocumentFragment(this);
    }

    public Element createElement(String str) {
        return DomImpl._document_createElement(this, str);
    }

    public Element createElementNS(String str, String str2) {
        return DomImpl._document_createElementNS(this, str, str2);
    }

    public EntityReference createEntityReference(String str) {
        return DomImpl._document_createEntityReference(this, str);
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        return DomImpl._document_createProcessingInstruction(this, str, str2);
    }

    public Text createTextNode(String str) {
        return DomImpl._document_createTextNode(this, str);
    }

    public DocumentType getDoctype() {
        return DomImpl._document_getDoctype(this);
    }

    public Element getDocumentElement() {
        return DomImpl._document_getDocumentElement(this);
    }

    public Element getElementById(String str) {
        return DomImpl._document_getElementById(this, str);
    }

    public NodeList getElementsByTagName(String str) {
        return DomImpl._document_getElementsByTagName(this, str);
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        return DomImpl._document_getElementsByTagNameNS(this, str, str2);
    }

    public DOMImplementation getImplementation() {
        return DomImpl._document_getImplementation(this);
    }

    public Node importNode(Node node, boolean z) {
        return DomImpl._document_importNode(this, node, z);
    }

    public int getLength() {
        return DomImpl._childNodes_getLength(this);
    }

    public Node item(int i) {
        return DomImpl._childNodes_item(this, i);
    }

    public void removeAllMimeHeaders() {
        DomImpl._soapPart_removeAllMimeHeaders(this);
    }

    public void removeMimeHeader(String str) {
        DomImpl._soapPart_removeMimeHeader(this, str);
    }

    public Iterator getAllMimeHeaders() {
        return DomImpl._soapPart_getAllMimeHeaders(this);
    }

    public SOAPEnvelope getEnvelope() {
        return DomImpl._soapPart_getEnvelope(this);
    }

    public Source getContent() {
        return DomImpl._soapPart_getContent(this);
    }

    public void setContent(Source source) {
        DomImpl._soapPart_setContent(this, source);
    }

    public String[] getMimeHeader(String str) {
        return DomImpl._soapPart_getMimeHeader(this, str);
    }

    public void addMimeHeader(String str, String str2) {
        DomImpl._soapPart_addMimeHeader(this, str, str2);
    }

    public void setMimeHeader(String str, String str2) {
        DomImpl._soapPart_setMimeHeader(this, str, str2);
    }

    public Iterator getMatchingMimeHeaders(String[] strArr) {
        return DomImpl._soapPart_getMatchingMimeHeaders(this, strArr);
    }

    public Iterator getNonMatchingMimeHeaders(String[] strArr) {
        return DomImpl._soapPart_getNonMatchingMimeHeaders(this, strArr);
    }
}
