package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.Name;

class DetailXobj extends SoapFaultElementXobj implements Detail {
    DetailXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new DetailXobj(locale, this._name);
    }

    public DetailEntry addDetailEntry(Name name) {
        return DomImpl.detail_addDetailEntry(this, name);
    }

    public Iterator getDetailEntries() {
        return DomImpl.detail_getDetailEntries(this);
    }
}
