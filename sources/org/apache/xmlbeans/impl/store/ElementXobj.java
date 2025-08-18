package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

class ElementXobj extends NamedNodeXobj implements Element {
    private ElementAttributes _attributes;

    ElementXobj(Locale locale, QName qName) {
        super(locale, 2, 1);
        this._name = qName;
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new ElementXobj(locale, this._name);
    }

    public NamedNodeMap getAttributes() {
        if (this._attributes == null) {
            this._attributes = new ElementAttributes(this);
        }
        return this._attributes;
    }

    public String getAttribute(String str) {
        return DomImpl._element_getAttribute(this, str);
    }

    public Attr getAttributeNode(String str) {
        return DomImpl._element_getAttributeNode(this, str);
    }

    public Attr getAttributeNodeNS(String str, String str2) {
        return DomImpl._element_getAttributeNodeNS(this, str, str2);
    }

    public String getAttributeNS(String str, String str2) {
        return DomImpl._element_getAttributeNS(this, str, str2);
    }

    public NodeList getElementsByTagName(String str) {
        return DomImpl._element_getElementsByTagName(this, str);
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        return DomImpl._element_getElementsByTagNameNS(this, str, str2);
    }

    public String getTagName() {
        return DomImpl._element_getTagName(this);
    }

    public boolean hasAttribute(String str) {
        return DomImpl._element_hasAttribute(this, str);
    }

    public boolean hasAttributeNS(String str, String str2) {
        return DomImpl._element_hasAttributeNS(this, str, str2);
    }

    public void removeAttribute(String str) {
        DomImpl._element_removeAttribute(this, str);
    }

    public Attr removeAttributeNode(Attr attr) {
        return DomImpl._element_removeAttributeNode(this, attr);
    }

    public void removeAttributeNS(String str, String str2) {
        DomImpl._element_removeAttributeNS(this, str, str2);
    }

    public void setAttribute(String str, String str2) {
        DomImpl._element_setAttribute(this, str, str2);
    }

    public Attr setAttributeNode(Attr attr) {
        return DomImpl._element_setAttributeNode(this, attr);
    }

    public Attr setAttributeNodeNS(Attr attr) {
        return DomImpl._element_setAttributeNodeNS(this, attr);
    }

    public void setAttributeNS(String str, String str2, String str3) {
        DomImpl._element_setAttributeNS(this, str, str2, str3);
    }

    public TypeInfo getSchemaTypeInfo() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setIdAttribute(String str, boolean z) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setIdAttributeNS(String str, String str2, boolean z) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    public void setIdAttributeNode(Attr attr, boolean z) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }
}
