package org.apache.xmlbeans.impl.store;

import java.util.Hashtable;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

class DocumentXobj extends NodeXobj implements Document {
    private Hashtable<String, DomImpl.Dom> _idToElement;

    DocumentXobj(Locale locale) {
        super(locale, 1, 9);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new DocumentXobj(locale);
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
        Xobj xobj;
        Hashtable<String, DomImpl.Dom> hashtable = this._idToElement;
        if (hashtable == null || (xobj = (Xobj) hashtable.get(str)) == null) {
            return null;
        }
        if (!isInSameTree(xobj)) {
            this._idToElement.remove(str);
        }
        return (Element) xobj;
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

    /* access modifiers changed from: protected */
    public void addIdElement(String str, DomImpl.Dom dom) {
        if (this._idToElement == null) {
            this._idToElement = new Hashtable<>();
        }
        this._idToElement.put(str, dom);
    }

    /* access modifiers changed from: package-private */
    public void removeIdElement(String str) {
        Hashtable<String, DomImpl.Dom> hashtable = this._idToElement;
        if (hashtable != null) {
            hashtable.remove(str);
        }
    }
}
