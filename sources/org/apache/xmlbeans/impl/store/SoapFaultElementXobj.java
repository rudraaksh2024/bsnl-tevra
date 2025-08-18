package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPFaultElement;

class SoapFaultElementXobj extends SoapElementXobj implements SOAPFaultElement {
    SoapFaultElementXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapFaultElementXobj(locale, this._name);
    }
}
