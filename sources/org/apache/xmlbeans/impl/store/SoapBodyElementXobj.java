package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;

class SoapBodyElementXobj extends SoapElementXobj implements SOAPBodyElement {
    SoapBodyElementXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapBodyElementXobj(locale, this._name);
    }
}
