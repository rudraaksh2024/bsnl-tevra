package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

abstract class NodeXobj extends Xobj implements DomImpl.Dom, Node, NodeList {
    public NamedNodeMap getAttributes() {
        return null;
    }

    public NodeList getChildNodes() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public DomImpl.Dom getDom() {
        return this;
    }

    public boolean nodeCanHavePrefixUri() {
        return false;
    }

    NodeXobj(Locale locale, int i, int i2) {
        super(locale, i, i2);
    }

    public int getLength() {
        return DomImpl._childNodes_getLength(this);
    }

    public Node item(int i) {
        return DomImpl._childNodes_item(this, i);
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
}
