package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;

class AttrIdXobj extends AttrXobj {
    public boolean isId() {
        return true;
    }

    AttrIdXobj(Locale locale, QName qName) {
        super(locale, qName);
    }
}
