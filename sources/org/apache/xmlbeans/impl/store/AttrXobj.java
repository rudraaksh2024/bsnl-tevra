package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

class AttrXobj extends NamedNodeXobj implements Attr {
    public Node getNextSibling() {
        return null;
    }

    public boolean isId() {
        return false;
    }

    AttrXobj(Locale locale, QName qName) {
        super(locale, 3, 2);
        this._name = qName;
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new AttrXobj(locale, this._name);
    }

    public String getName() {
        return DomImpl._node_getNodeName(this);
    }

    public Element getOwnerElement() {
        return DomImpl._attr_getOwnerElement(this);
    }

    public boolean getSpecified() {
        return DomImpl._attr_getSpecified(this);
    }

    public String getValue() {
        return DomImpl._node_getNodeValue(this);
    }

    public void setValue(String str) {
        DomImpl._node_setNodeValue(this, str);
    }

    public TypeInfo getSchemaTypeInfo() {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }
}
