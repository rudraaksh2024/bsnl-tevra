package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;

class SoapHeaderElementXobj extends SoapElementXobj implements SOAPHeaderElement {
    SoapHeaderElementXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapHeaderElementXobj(locale, this._name);
    }

    public void setMustUnderstand(boolean z) {
        DomImpl.soapHeaderElement_setMustUnderstand(this, z);
    }

    public boolean getMustUnderstand() {
        return DomImpl.soapHeaderElement_getMustUnderstand(this);
    }

    public void setActor(String str) {
        DomImpl.soapHeaderElement_setActor(this, str);
    }

    public String getActor() {
        return DomImpl.soapHeaderElement_getActor(this);
    }
}
