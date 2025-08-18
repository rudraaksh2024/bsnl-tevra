package org.apache.xmlbeans.impl.store;

abstract class NamedNodeXobj extends NodeXobj {
    boolean _canHavePrefixUri = true;

    NamedNodeXobj(Locale locale, int i, int i2) {
        super(locale, i, i2);
    }

    public boolean nodeCanHavePrefixUri() {
        return this._canHavePrefixUri;
    }
}
