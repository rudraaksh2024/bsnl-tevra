package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPHeader;

class SoapEnvelopeXobj extends SoapElementXobj implements SOAPEnvelope {
    SoapEnvelopeXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapEnvelopeXobj(locale, this._name);
    }

    public SOAPBody addBody() throws SOAPException {
        return DomImpl._soapEnvelope_addBody(this);
    }

    public SOAPBody getBody() throws SOAPException {
        return DomImpl._soapEnvelope_getBody(this);
    }

    public SOAPHeader getHeader() throws SOAPException {
        return DomImpl._soapEnvelope_getHeader(this);
    }

    public SOAPHeader addHeader() throws SOAPException {
        return DomImpl._soapEnvelope_addHeader(this);
    }

    public Name createName(String str) {
        return DomImpl._soapEnvelope_createName(this, str);
    }

    public Name createName(String str, String str2, String str3) {
        return DomImpl._soapEnvelope_createName(this, str, str2, str3);
    }
}
