package org.apache.xmlbeans.impl.store;

import java.util.Locale;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.w3c.dom.Document;

class SoapBodyXobj extends SoapElementXobj implements SOAPBody {
    SoapBodyXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapBodyXobj(locale, this._name);
    }

    public boolean hasFault() {
        return DomImpl.soapBody_hasFault(this);
    }

    public SOAPFault addFault() throws SOAPException {
        return DomImpl.soapBody_addFault(this);
    }

    public SOAPFault getFault() {
        return DomImpl.soapBody_getFault(this);
    }

    public SOAPBodyElement addBodyElement(Name name) {
        return DomImpl.soapBody_addBodyElement(this, name);
    }

    public SOAPBodyElement addDocument(Document document) {
        return DomImpl.soapBody_addDocument(this, document);
    }

    public SOAPFault addFault(Name name, String str) throws SOAPException {
        return DomImpl.soapBody_addFault(this, name, str);
    }

    public SOAPFault addFault(Name name, String str, Locale locale) throws SOAPException {
        return DomImpl.soapBody_addFault(this, name, str, locale);
    }
}
