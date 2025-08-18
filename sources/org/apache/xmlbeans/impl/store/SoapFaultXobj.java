package org.apache.xmlbeans.impl.store;

import java.util.Locale;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.store.DomImpl;

class SoapFaultXobj extends SoapBodyElementXobj implements SOAPFault {
    SoapFaultXobj(Locale locale, QName qName) {
        super(locale, qName);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new SoapFaultXobj(locale, this._name);
    }

    public void setFaultString(String str) {
        DomImpl.soapFault_setFaultString(this, str);
    }

    public void setFaultString(String str, Locale locale) {
        DomImpl.soapFault_setFaultString(this, str, locale);
    }

    public void setFaultCode(Name name) throws SOAPException {
        DomImpl.soapFault_setFaultCode((DomImpl.Dom) this, name);
    }

    public void setFaultActor(String str) {
        DomImpl.soapFault_setFaultActor(this, str);
    }

    public String getFaultActor() {
        return DomImpl.soapFault_getFaultActor(this);
    }

    public String getFaultCode() {
        return DomImpl.soapFault_getFaultCode(this);
    }

    public void setFaultCode(String str) throws SOAPException {
        DomImpl.soapFault_setFaultCode((DomImpl.Dom) this, str);
    }

    public Locale getFaultStringLocale() {
        return DomImpl.soapFault_getFaultStringLocale(this);
    }

    public Name getFaultCodeAsName() {
        return DomImpl.soapFault_getFaultCodeAsName(this);
    }

    public String getFaultString() {
        return DomImpl.soapFault_getFaultString(this);
    }

    public Detail addDetail() throws SOAPException {
        return DomImpl.soapFault_addDetail(this);
    }

    public Detail getDetail() {
        return DomImpl.soapFault_getDetail(this);
    }
}
