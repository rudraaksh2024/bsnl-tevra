package org.apache.xmlbeans.impl.store;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

final class ElementAttributes implements NamedNodeMap {
    private ElementXobj _elementXobj;

    ElementAttributes(ElementXobj elementXobj) {
        this._elementXobj = elementXobj;
    }

    public int getLength() {
        return DomImpl._attributes_getLength(this._elementXobj);
    }

    public Node getNamedItem(String str) {
        return DomImpl._attributes_getNamedItem(this._elementXobj, str);
    }

    public Node getNamedItemNS(String str, String str2) {
        return DomImpl._attributes_getNamedItemNS(this._elementXobj, str, str2);
    }

    public Node item(int i) {
        return DomImpl._attributes_item(this._elementXobj, i);
    }

    public Node removeNamedItem(String str) {
        return DomImpl._attributes_removeNamedItem(this._elementXobj, str);
    }

    public Node removeNamedItemNS(String str, String str2) {
        return DomImpl._attributes_removeNamedItemNS(this._elementXobj, str, str2);
    }

    public Node setNamedItem(Node node) {
        return DomImpl._attributes_setNamedItem(this._elementXobj, node);
    }

    public Node setNamedItemNS(Node node) {
        return DomImpl._attributes_setNamedItemNS(this._elementXobj, node);
    }
}
