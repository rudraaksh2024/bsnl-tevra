package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;

class SoapHeaderXobj extends SoapElementXobj implements SOAPHeader {
    SoapHeaderXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapHeaderXobj(locale, this._name);
    }

    public Iterator examineAllHeaderElements() {
        return DomImpl.soapHeader_examineAllHeaderElements(this);
    }

    public Iterator extractAllHeaderElements() {
        return DomImpl.soapHeader_extractAllHeaderElements(this);
    }

    public Iterator examineHeaderElements(String str) {
        return DomImpl.soapHeader_examineHeaderElements(this, str);
    }

    public Iterator examineMustUnderstandHeaderElements(String str) {
        return DomImpl.soapHeader_examineMustUnderstandHeaderElements(this, str);
    }

    public Iterator extractHeaderElements(String str) {
        return DomImpl.soapHeader_extractHeaderElements(this, str);
    }

    public SOAPHeaderElement addHeaderElement(Name name) {
        return DomImpl.soapHeader_addHeaderElement(this, name);
    }
}
