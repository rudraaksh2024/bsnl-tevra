package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.Text;

class SaajTextNode extends TextNode implements Text {
    SaajTextNode(Locale locale) {
        super(locale);
    }

    public boolean isComment() {
        return DomImpl._soapText_isComment(this);
    }

    public void detachNode() {
        DomImpl._soapNode_detachNode(this);
    }

    public void recycleNode() {
        DomImpl._soapNode_recycleNode(this);
    }

    public String getValue() {
        return DomImpl._soapNode_getValue(this);
    }

    public void setValue(String str) {
        DomImpl._soapNode_setValue(this, str);
    }

    public SOAPElement getParentElement() {
        return DomImpl._soapNode_getParentElement(this);
    }

    public void setParentElement(SOAPElement sOAPElement) {
        DomImpl._soapNode_setParentElement(this, sOAPElement);
    }
}
