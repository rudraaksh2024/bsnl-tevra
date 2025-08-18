package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.DetailEntry;

class DetailEntryXobj extends SoapElementXobj implements DetailEntry {
    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new DetailEntryXobj(locale, this._name);
    }

    DetailEntryXobj(Locale locale, QName qName) {
        super(locale, qName);
    }
}
