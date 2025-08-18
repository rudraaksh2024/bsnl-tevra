package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.store.DomImpl;

class SoapElementXobj extends ElementXobj implements SOAPElement, Node {
    SoapElementXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapElementXobj(locale, this._name);
    }

    public void detachNode() {
        DomImpl._soapNode_detachNode(this);
    }

    public void recycleNode() {
        DomImpl._soapNode_recycleNode(this);
    }

    public String getValue() {
        return DomImpl._soapNode_getValue(this);
    }

    public void setValue(String str) {
        DomImpl._soapNode_setValue(this, str);
    }

    public SOAPElement getParentElement() {
        return DomImpl._soapNode_getParentElement(this);
    }

    public void setParentElement(SOAPElement sOAPElement) {
        DomImpl._soapNode_setParentElement(this, sOAPElement);
    }

    public void removeContents() {
        DomImpl._soapElement_removeContents(this);
    }

    public String getEncodingStyle() {
        return DomImpl._soapElement_getEncodingStyle(this);
    }

    public void setEncodingStyle(String str) {
        DomImpl._soapElement_setEncodingStyle(this, str);
    }

    public boolean removeNamespaceDeclaration(String str) {
        return DomImpl._soapElement_removeNamespaceDeclaration(this, str);
    }

    public Iterator getAllAttributes() {
        return DomImpl._soapElement_getAllAttributes(this);
    }

    public Iterator getChildElements() {
        return DomImpl._soapElement_getChildElements(this);
    }

    public Iterator getNamespacePrefixes() {
        return DomImpl._soapElement_getNamespacePrefixes(this);
    }

    public SOAPElement addAttribute(Name name, String str) throws SOAPException {
        return DomImpl._soapElement_addAttribute(this, name, str);
    }

    public SOAPElement addChildElement(SOAPElement sOAPElement) throws SOAPException {
        return DomImpl._soapElement_addChildElement((DomImpl.Dom) this, sOAPElement);
    }

    public SOAPElement addChildElement(Name name) throws SOAPException {
        return DomImpl._soapElement_addChildElement((DomImpl.Dom) this, name);
    }

    public SOAPElement addChildElement(String str) throws SOAPException {
        return DomImpl._soapElement_addChildElement((DomImpl.Dom) this, str);
    }

    public SOAPElement addChildElement(String str, String str2) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, str, str2);
    }

    public SOAPElement addChildElement(String str, String str2, String str3) throws SOAPException {
        return DomImpl._soapElement_addChildElement(this, str, str2, str3);
    }

    public SOAPElement addNamespaceDeclaration(String str, String str2) {
        return DomImpl._soapElement_addNamespaceDeclaration(this, str, str2);
    }

    public SOAPElement addTextNode(String str) {
        return DomImpl._soapElement_addTextNode(this, str);
    }

    public String getAttributeValue(Name name) {
        return DomImpl._soapElement_getAttributeValue(this, name);
    }

    public Iterator getChildElements(Name name) {
        return DomImpl._soapElement_getChildElements(this, name);
    }

    public Name getElementName() {
        return DomImpl._soapElement_getElementName(this);
    }

    public String getNamespaceURI(String str) {
        return DomImpl._soapElement_getNamespaceURI(this, str);
    }

    public Iterator getVisibleNamespacePrefixes() {
        return DomImpl._soapElement_getVisibleNamespacePrefixes(this);
    }

    public boolean removeAttribute(Name name) {
        return DomImpl._soapElement_removeAttribute(this, name);
    }
}
