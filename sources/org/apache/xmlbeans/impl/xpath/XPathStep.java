package org.apache.xmlbeans.impl.xpath;

import javax.xml.namespace.QName;

class XPathStep {
    final boolean _attr;
    XPathStep _backtrack;
    final boolean _deep;
    int _flags;
    boolean _hasBacktrack;
    final QName _name;
    XPathStep _next;
    XPathStep _prev;

    XPathStep(boolean z, boolean z2, QName qName) {
        this._name = qName;
        this._deep = z;
        this._attr = z2;
        int i = (z || !z2) ? 2 : 0;
        this._flags = z2 ? i | 4 : i;
    }

    /* access modifiers changed from: package-private */
    public boolean isWild() {
        return this._name.getLocalPart().length() == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean match(QName qName) {
        String localPart = this._name.getLocalPart();
        String localPart2 = qName.getLocalPart();
        int length = localPart.length();
        if (length == 0) {
            String namespaceURI = this._name.getNamespaceURI();
            if (namespaceURI.isEmpty() || namespaceURI.equals(qName.getNamespaceURI())) {
                return true;
            }
            return false;
        } else if (length != localPart2.length()) {
            return false;
        } else {
            String namespaceURI2 = this._name.getNamespaceURI();
            String namespaceURI3 = qName.getNamespaceURI();
            if (namespaceURI2.length() != namespaceURI3.length()) {
                return false;
            }
            if (!localPart.equals(localPart2) || !namespaceURI2.equals(namespaceURI3)) {
                return false;
            }
            return true;
        }
    }
}
