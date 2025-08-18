package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.store.DomImpl;

class SoapPartDocXobj extends DocumentXobj {
    SoapPartDom _soapPartDom = new SoapPartDom(this);

    SoapPartDocXobj(Locale locale) {
        super(locale);
    }

    /* access modifiers changed from: package-private */
    public DomImpl.Dom getDom() {
        return this._soapPartDom;
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapPartDocXobj(locale);
    }
}
